package com.example.blackjack;

import java.util.ArrayList;

public abstract class JoueurAbs {
    public ArrayList<Card> main;
    public int scoreMain;
    public int asInMain;

    public JoueurAbs(){
        main = new ArrayList<Card>();
        scoreMain = 0;
    }

    public void ajtCarte(Card c){
        main.add(c);
        this.valeurMain();
    }

    public void reset(){
        main.clear();
        scoreMain = 0;
    }

    public int valeurMain(){
        scoreMain = 0;
        asInMain = 0;
        for(Card c : main){
            if(c.getVlr() == 1){
                asInMain++;
            }else{
                scoreMain = scoreMain + c.getVlr();
            }
        }
        switch (asInMain) {
            case 0:
                break;
            case 1:
                if (scoreMain + 11 <= 21) {
                    scoreMain = scoreMain + 11;
                }else{
                    scoreMain = scoreMain + 1;
                }
                break;
            default:
                if (scoreMain + 11 + (asInMain - 1) > 21) {
                    scoreMain = scoreMain + asInMain;
                } else {
                    scoreMain = scoreMain + 11 + (asInMain - 1);
                }
                break;
        }
        return scoreMain;
    }
}
