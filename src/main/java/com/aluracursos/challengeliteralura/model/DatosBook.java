package com.aluracursos.challengeliteralura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosBook(
        @JsonAlias("title") String title,
        @JsonAlias("authors") List<DatosAuthor> authorList,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("download_count") Double download
) {
}
