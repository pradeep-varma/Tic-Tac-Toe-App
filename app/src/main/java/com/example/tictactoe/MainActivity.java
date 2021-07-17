package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int activeplayer=0;
    boolean gameIsActive=true;
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int[][] winningpositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropin(View view){
        ImageView counter=(ImageView) view;
        System.out.println(counter.getTag().toString());
        int tappedCounter=Integer.parseInt(counter.getTag().toString());
        if (gamestate[tappedCounter]==2&&gameIsActive) {
            gamestate[tappedCounter]=activeplayer;
            counter.setTranslationY(-1000f);
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activeplayer = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(300);
            for (int[] winningposition:winningpositions){
                if(gamestate[winningposition[0]]==gamestate[winningposition[1]]&&
                gamestate[winningposition[1]]==gamestate[winningposition[2]]&&
                gamestate[winningposition[0]]!=2){
                    gameIsActive=false;
                    String winner="Red";
                    if(gamestate[winningposition[0]]==0){
                        winner="Yellow";
                    }
                    TextView WinnerMessage=(TextView) findViewById(R.id.WinnerMessage);
                    WinnerMessage.setText(winner+"Has Won");
                    LinearLayout layout=(LinearLayout)findViewById(R.id.playagainlayout);
                    layout.setVisibility(view.VISIBLE);
                }
            }
        }else{
            boolean gameIsOver=true;
            for(int counterstate: gamestate){
                if(counterstate==2) gameIsOver=false;
            }
            if(gameIsOver){
                TextView WinnerMessage=(TextView) findViewById(R.id.WinnerMessage);
                WinnerMessage.setText("It's a draw");
                LinearLayout layout=(LinearLayout)findViewById(R.id.playagainlayout);
                layout.setVisibility(view.VISIBLE);
            }
        }

    }
    public void playAgain(View view){
        gameIsActive=true;
        LinearLayout layout=(LinearLayout)findViewById(R.id.playagainlayout);
        layout.setVisibility(view.INVISIBLE);
        activeplayer=0;
        for (int i=0;i<gamestate.length;i++)
        {
            gamestate[i]=2;
        }
        GridLayout gridlayout=(GridLayout)findViewById(R.id.gridlayout);
        for(int i=0;i<gridlayout.getChildCount();i++)
        {
            ((ImageView) gridlayout.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}