package com.example.t100.nota;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;


@Entity
public class Nota {

    public Nota(){

    }

    @Id(assignable = true)
    public long id;
    private String titulo;
    private String nota;

    public Nota(String titulo, String nota) {
        this.titulo = titulo;
        this.nota = nota;
    }

    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getNota() {
        return nota;
    }

}
