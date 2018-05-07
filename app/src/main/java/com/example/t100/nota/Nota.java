package com.example.t100.nota;

import java.io.Serializable;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;


@Entity
public class Nota implements Serializable {

    public Nota(){
    }

    @Id(assignable = true)
    public long id;
    private String titulo;
    private String nota;
    private String dataHora;

    public Nota(String titulo, String nota, String dataHora) {
        this.titulo = titulo;
        this.nota = nota;
        this.dataHora = dataHora;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setNota(String nota) {
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

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
}
