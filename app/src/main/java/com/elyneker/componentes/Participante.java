package com.elyneker.componentes;

public class Participante {

    private static int contadorId = 0;
    private int id;
    private String nome;
    private String categoria;

    public Participante(String nome, String categoria) {
        this.id = contadorId++;
        this.nome = nome;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() { return categoria; }

    public void setCategoria(String categoria) { this.categoria = categoria; }

    @Override
    public String toString() {
        return getNome();
    }
}
