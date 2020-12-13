package com.elyneker.componentes;

public class Participante {

    private static int contadorId = 0;
    private int id;
    private String nome;

    public Participante(String nome) {
        this.id = contadorId++;
        this.nome = nome;
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

    @Override
    public String toString() {
        return getNome();
    }
}
