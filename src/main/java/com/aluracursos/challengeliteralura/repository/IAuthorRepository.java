package com.aluracursos.challengeliteralura.repository;

import com.aluracursos.challengeliteralura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT author FROM Author author WHERE :anio <= author.deathYear")
    List<Author> buscarAutoresVivo(Integer anio);
}
