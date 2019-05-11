package com.example.blackjack;

public enum Couleur {
    PIQUE("s"),
    COEUR("h"),
    CARREAU("d"),
    TREFLE("c");

    private String color;

    private Couleur(String toString){
        color = toString;
    }

    public String getColor() {
        return color;
    }
}

