package com.aluracursos.challengeliteralura.principal;

import com.aluracursos.challengeliteralura.model.*;
import com.aluracursos.challengeliteralura.repository.IAuthorRepository;
import com.aluracursos.challengeliteralura.repository.IBookRepository;
import com.aluracursos.challengeliteralura.service.ConsumoAPI;
import com.aluracursos.challengeliteralura.service.ConvierteDatos;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_CONSULTAS = "https://gutendex.com/books/";
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private List<Book> books;
    private List<Author> authors;
    private IBookRepository bookRepository;
    private IAuthorRepository authorRepository;
    public Principal(IBookRepository bookRepositorio, IAuthorRepository authorRepositorio) {
        this.bookRepository = bookRepositorio;
        this.authorRepository = authorRepositorio;
    }

    public void mostrarMenu(){
        var json = consumoAPI.obtenerDatos(URL_CONSULTAS);
        var datos = convierteDatos.obtenerDatos(json, Datos.class);

        var opcion  = -1;

        while (opcion != 0) {
            var menu = """
                    Bienvenido, ¿qué deseas hacer hoy?
                    1. Buscar libros por título
                    2. Listar libros registrados
                    3. listar autores registrados
                    4. listar autores vivos en un determinado año
                    5. listar libros por idioma
                    
                    0. Salir
                    """;
            System.out.println(menu);
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            }catch (Exception e){
                System.out.println("Ingrese un numero valido");
                scanner.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:
                    getbuscarDetallesLibro();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Gracias por usar la aplicación. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida, intenta nuevamente.");
            }
            System.out.println();
        }
    }

    public void getbuscarDetallesLibro(){
        System.out.println("Ingrese el titulo a buscar: ");
        String titulo = scanner.nextLine();

        var libroJson = consumoAPI.obtenerDatos(URL_CONSULTAS + "?search=" + URLEncoder.encode(titulo, StandardCharsets.UTF_8));
        var datosBusqueda = convierteDatos.obtenerDatos(libroJson, Datos.class);

        System.out.println("--- RESULTADOS DE BÚSQUEDA ---");
        if (datosBusqueda != null && datosBusqueda.resultados() != null) {
            Optional<DatosBook> resultado = datosBusqueda.resultados().stream().filter(l -> l.title().toUpperCase().contains(titulo.toUpperCase())).findFirst();
            if (resultado.isPresent()) {
                System.out.println("Libro encontrado: " + resultado.get().title().toUpperCase());
                System.out.println(resultado.get());
                DatosBook datosBook = resultado.get();
                Book libro = new Book(datosBook);
                bookRepository.save(libro);
            }
        } else {
            System.out.println("No se encontraron resultados.");
        }
    }

    public void listarLibros(){
        books = bookRepository.findAll();
        if (!books.isEmpty()){
        books.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .forEach(System.out::println);}
        else {
            System.out.println("No hay libros encontrados. Ingrese al sistema por la opcion 1");
        }
    }
    public void listarAutores(){
        authors = authorRepository.findAll();
        if(!authors.isEmpty()){
            System.out.println("Autores encontrados:");
            authors.stream()
                    .sorted(Comparator.comparing(Author::getName))
                    .forEach(System.out::println);
        }else
            System.out.println("SIN REGISTROS DE AUTORES. ingrese primero al sistema por la opcion 1");

    }

    public void listarLibrosPorIdioma(){
        System.out.println("Ingrese el idioma a buscar: ");
        String idioma = scanner.nextLine();
        if (idioma != null && !idioma.isEmpty() && idiomaExiste(idioma)) {
            Idiomas idiomaEnum = Idiomas.fromTexto(idioma);
            System.out.println("Idioma válido: " + idiomaEnum);
            books = bookRepository.buscarLibrosPorIdioma(idiomaEnum);
            if(!books.isEmpty()){
            books.stream()
                    .sorted(Comparator.comparing(Book::getTitle))
                    .forEach(System.out::println);}
            else{
                System.out.println("No se encontraron resultados. Ingrese primero libros al sistema con la opcion 1");
            }
        } else {
            System.out.println("Idioma inválido.");
        }
    }

    public void listarAutoresVivos(){
        System.out.println("Ingrese el año a buscar: ");
        Integer anio = scanner.nextInt();

        authors = authorRepository.buscarAutoresVivo(anio);
        if(!authors.isEmpty()){
            authors.stream()
                    .sorted(Comparator.comparing(Author::getName))
                    .forEach(System.out::println);
        } else {
            System.out.println("No se encontraron resultados.");
        }
    }

    private boolean idiomaExiste(String texto) {
        for (Idiomas idioma : Idiomas.values()) {
            if (idioma.getCodigo().equalsIgnoreCase(texto) ||
                    idioma.getTexto().equalsIgnoreCase(texto)) {
                return true;
            }
        }
        return false;
    }
}
