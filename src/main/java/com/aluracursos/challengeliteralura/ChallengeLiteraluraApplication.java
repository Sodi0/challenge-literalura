package com.aluracursos.challengeliteralura;

import com.aluracursos.challengeliteralura.principal.Principal;
import com.aluracursos.challengeliteralura.repository.IAuthorRepository;
import com.aluracursos.challengeliteralura.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiteraluraApplication implements CommandLineRunner {
    @Autowired
    private IBookRepository bookRepository;
    @Autowired
    private IAuthorRepository authorRepository;

    public static void main(String[] args) {
        SpringApplication.run(ChallengeLiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(bookRepository, authorRepository);
        principal.mostrarMenu();
    }
}
