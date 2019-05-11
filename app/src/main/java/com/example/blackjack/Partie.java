package com.example.blackjack;

import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;


public class Partie {
    public Joueur joueur;
    public Banque banque;
    public Deck deck;
    public int gagnant;

    public LinearLayout banqueLayout;
    public LinearLayout joueurLayout;

    public Resources res;

    public Partie(){
        joueur = new Joueur();
        banque = new Banque();
        deck = new Deck();
        gagnant = 0;
    }

    public void start(){
        gagnant = 0;
        joueur.main.clear();
        banque.main.clear();
        joueur.ajtCarte(deck.draw());
        joueur.ajtCarte(deck.draw());
        banque.ajtCarte(deck.draw());
        banque.ajtCarte(deck.draw());
    }

    /* TODO: Mettre tout ça dans le mainActivity parce que bon. */
    /*
    public void resetVues(){
        joueurLayout.removeAllViews();
        banqueLayout.removeAllViews();
    }

    public void updateVueJoueur(){
        joueurLayout.removeAllViews();
        for(Card c: joueur.main){
            int card_id = ((Resources) res).getIdentifier(c.nameFile(),"drawable", null);
            ImageView card_pic = new ImageView(null);
            card_pic.setImageResource(card_id);
            joueurLayout.addView(card_pic);
        }
    }

    public void updateVueJoueur(Card c){
        int card_id = ((Resources) res).getIdentifier(c.nameFile(),"drawable", null);
        ImageView card_pic = new ImageView(null);
        card_pic.setImageResource(card_id);
        joueurLayout.addView(card_pic);
    }

    public void updateVueBanque(){
        banqueLayout.removeAllViews();
        for(Card c: banque.main){
            int card_id = ((Resources) res).getIdentifier(c.nameFile(),"drawable", null);
            ImageView card_pic = new ImageView(null);
            card_pic.setImageResource(card_id);
            banqueLayout.addView(card_pic);
        }
    }

    public void updateVueBanque(Card c){
        int card_id = ((Resources) res).getIdentifier(c.nameFile(),"drawable", null);
        ImageView card_pic = new ImageView(null);
        card_pic.setImageResource(card_id);
        banqueLayout.addView(card_pic);
    }
    */

    public int joueurDraw() {
        if(gagnant == 0) {
            joueur.ajtCarte(deck.draw());
            if (joueur.valeurMain() > 21) {
                gagnant = 2;
            }
            return gagnant;
        }else{
            return gagnant;
        }
    }

    public int banqueDraw() {
        if(gagnant == 0) {
            banque.ajtCarte(deck.draw());
            if (banque.valeurMain(true) > 21) {
                gagnant = 1;
            }
            return gagnant;
        }else{
            return gagnant;
        }
    }

    public int fin(){
        if(gagnant != 0){
            return gagnant;
        }
        if(banque.valeurMain(true) < joueur.valeurMain()){
            gagnant = 1;
        }else if(banque.valeurMain(true) > joueur.valeurMain()){
            gagnant = 2;
        }else{
            gagnant = 0;
        }
        return gagnant;
    }
    /*
    public void banqueIA(){
        while(banque.valeurMain(true) < 17 || banque.valeurMain(true) < joueur.valeurMain()){
            banque.ajtCarte(deck.draw());
        }
    }
    */

}
