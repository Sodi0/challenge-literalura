package com.aluracursos.challengeliteralura.repository;

import com.aluracursos.challengeliteralura.model.Book;
import com.aluracursos.challengeliteralura.model.Idiomas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT books FROM Book books WHERE books.languages = :idioma")
    List<Book> buscarLibrosPorIdioma(Idiomas idioma);

}
