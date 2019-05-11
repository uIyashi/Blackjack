package com.example.blackjack;

import android.support.v4.util.SimpleArrayMap;

import java.lang.Integer;

public class Card {
    public Couleur clr;
    public Valeur vlr;

    public Card(Couleur c, Valeur v){
        /* Definition de l'arraymap si c'est pas déjà fait */
        this.clr = c;
        this.vlr = v;
    }

    public String nameFile(){
        return "card_b_" + clr.getColor() + vlr.toString();
    }

    public Couleur getClr() {
        return clr;
    }

    public int getVlr() {
        return vlr.getValue();
    }
}
