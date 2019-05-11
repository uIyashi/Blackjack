package com.example.blackjack;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;
/*

    - - - Blackjack app - - -
    - Pelerin Matthieu
             Ledieu Clement -

    Source des images: https://opengameart.org/content/playing-card-assets-52-cards-deck-chips


 */
public class MainActivity extends AppCompatActivity {

    public Partie bjPartie;
    public LinearLayout banqueLayout;
    public LinearLayout joueurLayout;

    public TextView scoreJoueur;
    public TextView scoreBanque;

    public Button dealBtn;
    public Button hitBtn;
    public Button ddownBtn;
    public Button standBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bjPartie = new Partie();

        banqueLayout = (LinearLayout)findViewById(R.id.banquierMain);
        joueurLayout = (LinearLayout)findViewById(R.id.joueurMain);

        scoreJoueur = (TextView)findViewById(R.id.scoreJ);
        scoreBanque = (TextView)findViewById(R.id.scoreB);

        dealBtn = (Button)findViewById(R.id.buttonStart);
        hitBtn = (Button)findViewById(R.id.buttonHit);
        ddownBtn = (Button)findViewById(R.id.buttonDouble);
        ddownBtn.setTextColor(Color.GRAY);
        standBtn = (Button)findViewById(R.id.buttonStand);
        standBtn.setTextColor(Color.GRAY);
    }

    public void dealBtn(View view){
        bjPartie.start();
        standBtn.setTextColor(Color.BLACK);
        standBtn.setClickable(true);
        ddownBtn.setTextColor(Color.BLACK);
        standBtn.setClickable(true);
        color(0);
        refreshHands(0, false);
    }

    public void hitBtn(View view){
        if(bjPartie.joueurDraw() == 2){
            Toast.makeText(getApplicationContext(), "Burn!", Toast.LENGTH_LONG).show();
            hitBtn.setClickable(false);
            standBtn(view);
        }
        ddownBtn.setTextColor(Color.GRAY);
        ddownBtn.setClickable(false);
        refreshHands(1);
        // color(bjPartie.gagnant);
    }

    public void ddownBtn(View view){
        // bjPartie.joueurDraw();
        hitBtn(view);
        refreshHands(1);
        standBtn(view);
    }

    public void standBtn(View view){
        /* "IA" banque : Piocher jusqu'a taper 17+ */
        // bjPartie.banqueIA();
        refreshHands(2, true);
        while(bjPartie.banque.valeurMain(true) < 17 && bjPartie.gagnant == 0) {
            try {
                Thread.sleep(1000);
                bjPartie.banqueDraw();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            refreshHands(2, true);
        }
        bjPartie.fin();
        refreshHands(2, true);
        // color(bjPartie.gagnant);
    }

    public void color(int status){
        /* 1: Joueur gagne
           2: Banque gagne
           0: Match nul // Inconnu
         */
        switch (status){
            case 1:
                scoreJoueur.setTextColor(Color.GREEN);
                scoreBanque.setTextColor(Color.RED);
                break;
            case 2:
                scoreJoueur.setTextColor(Color.RED);
                scoreBanque.setTextColor(Color.GREEN);
                break;
            case 0:
                scoreJoueur.setTextColor(Color.BLACK);
                scoreBanque.setTextColor(Color.BLACK);
                break;
        }
    }

    public void refreshHands(int j){
        if(j == 1 || j == 0){
            joueurLayout.removeAllViews();
            Resources res = getResources();
            for(Card c: bjPartie.joueur.main) {
                int card_id = ((Resources) res).getIdentifier(c.nameFile(), "drawable", getPackageName());
                ImageView card_pic = new ImageView(this);
                card_pic.setImageResource(card_id);
                joueurLayout.addView(card_pic);
            }
            scoreJoueur.setText("Score: " + bjPartie.joueur.scoreMain);
        }
        if(j == 2 || j == 0){
            banqueLayout.removeAllViews();
            Resources res = getResources();
            for(Card c: bjPartie.banque.main){
                int card_id = ((Resources) res).getIdentifier(c.nameFile(),"drawable", getPackageName());
                ImageView card_pic = new ImageView(this);
                card_pic.setImageResource(card_id);
                banqueLayout.addView(card_pic);
            }
            scoreBanque.setText("Score: " + bjPartie.banque.valeurMain());
        }
        color(bjPartie.gagnant);
    }

    public void refreshHands(int j, boolean bypass){
        if(j == 1 || j == 0){
            joueurLayout.removeAllViews();
            Resources res = getResources();
            for(Card c: bjPartie.joueur.main) {
                int card_id = ((Resources) res).getIdentifier(c.nameFile(), "drawable", getPackageName());
                ImageView card_pic = new ImageView(this);
                card_pic.setImageResource(card_id);
                joueurLayout.addView(card_pic);
            }
            scoreJoueur.setText("Score: " + bjPartie.joueur.scoreMain);
        }
        if(j == 2 || j == 0){
            banqueLayout.removeAllViews();
            Resources res = getResources();
            boolean bypassMem = bypass;
            for(Card c: bjPartie.banque.main){
                int card_id;
                if(bypass == false){
                    card_id = ((Resources) res).getIdentifier("deck_5","drawable", getPackageName());
                    bypass = true;
                }else {
                    card_id = ((Resources) res).getIdentifier(c.nameFile(), "drawable", getPackageName());
                }
                ImageView card_pic = new ImageView(this);
                card_pic.setImageResource(card_id);
                banqueLayout.addView(card_pic);
            }
            scoreBanque.setText("Score: " + bjPartie.banque.valeurMain(bypassMem));
        }
        color(bjPartie.gagnant);
    }
}
