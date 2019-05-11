package com.example.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    public int nombreDeDecks;
    private Random randomGen;
    private List<Card> deck;
    private List<Card> defausse;
    private int randCard;

    public Deck(int nbDeck){
        deck = new ArrayList<Card>();
        defausse = new ArrayList<Card>();
        randomGen = new Random();
        for(int i = 0; i < nbDeck; i++){
            for(Couleur c : Couleur.values()){
                for(Valeur v : Valeur.values()){
                    deck.add(new Card(c, v));
                }
            }
        }
    }

    public Deck(){
        deck = new ArrayList<Card>();
        defausse = new ArrayList<Card>();
        randomGen = new Random();
        for(int i = 0; i < 1; i++){
            for(Couleur c : Couleur.values()){
                for(Valeur v : Valeur.values()){
                    deck.add(new Card(c, v));
                }
            }
        }
    }

    public Card draw(){
        if(deck.isEmpty()){
            deck.addAll(defausse);
            defausse.clear();
        }
        randCard = randomGen.nextInt(deck.size());
        defausse.add(deck.get(randCard));
        return deck.remove(randCard);
    }
}
