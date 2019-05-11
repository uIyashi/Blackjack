package com.example.blackjack;

public class Banque extends JoueurAbs {
    public boolean carteCachee;

    private boolean ignore;
    public Banque(){
        super();
        ignore = true;
    }

    public int valeurMain(boolean bypass){
        scoreMain = 0;
        asInMain = 0;
        for(Card c : main){
            if(bypass == false){
                bypass = true;
            }else {
                if (c.getVlr() == 1) {
                    asInMain++;
                } else {
                    scoreMain = scoreMain + c.getVlr();
                }
            }
        }
        switch (asInMain) {
            case 0:
                break;
            case 1:
                if (scoreMain + 11 <= 21) {
                    scoreMain = scoreMain + 11;
                }
                break;
            case 2:
                if (scoreMain + 11 + (asInMain - 1) > 21) {
                    scoreMain = scoreMain + asInMain;
                } else {
                    scoreMain = scoreMain + 11 + (asInMain - 1);
                }
                break;
        }
        return scoreMain;
    }

    public boolean isCarteCachee() {
        return carteCachee;
    }
}
