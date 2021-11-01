package com.e.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*Renders view */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
        0 = X
        1 = 0
    */
    int activePlayer = 0 ;
    int gameState[] = {2,2,2,2,2,2,2,2,2} ;
    /*
        Game States
        2 = Blank , 1 = 0 , 0 = X
    */
    int[][] winPosition = { {0,1,2}, {3,4,5},{6,7,8},{0,3,6},
                            {1,4,7},{2,5,8},{0,4,8},{2,4,6} } ;
    /*
        Contains all the winning Position .
    */
    boolean gameActive = true ;
    public void playerTap(View view)
    {
        ImageView img = (ImageView)view ;
        TextView status = findViewById(R.id.turnStatus) ;
        int tappedImg = Integer.parseInt(img.getTag().toString()) ;

        LinearLayout statuss = findViewById(R.id.linear) ;
        System.out.println(statuss.getLeft());
        System.out.println(statuss.getRight());
        System.out.println(statuss.getTop());
        System.out.println(statuss.getBottom());

        System.out.println(status.getText());

        if( gameActive == false )
        {
            gameReset(view) ;
            return ;
        }

        if( gameState[tappedImg] == 2 )
        {
            gameState[tappedImg] = activePlayer ;

            if( activePlayer == 0 )
            {
                img.setImageResource(R.drawable.x) ;
                status.setText("O Turn : Tap To Play") ;
                activePlayer = 1 ;
            }
            else
            {
                img.setImageResource(R.drawable.zero) ;
                status.setText("X Turn : Tap To Play") ;
                activePlayer = 0 ;
            }

            for( int i = 0 ; i < winPosition.length ; i++ )
            {
                /* check if all the three winning position contains the same value or not ( i.e either 0 or 1 ). */
                if (gameState[winPosition[i][0]] == gameState[winPosition[i][1]] && gameState[winPosition[i][1]] == gameState[winPosition[i][2]] &&
                        gameState[winPosition[i][0]] != 2) {
                    if (gameState[winPosition[i][0]] == 0) {
                        status.setText("X has won");
                    } else {
                        status.setText("O has won");
                    }
                    gameActive = false;
                }
            }
            /*
                Loop to check if no one wins .
            */
            int emptyCell = 9 ;
            for( int i = 0 ; i < gameState.length ; i++ )
            {
                if(gameState[i] != 2 ) emptyCell-- ;
            }
            if( emptyCell == 0 && gameActive != false)
            {
                status.setText( " Game Over ! " ) ;
                gameActive = false ;
            }


        }
    } /* End of playerTap function */

    public void gameReset(View view)
    {
        gameActive = true ;
        activePlayer = 0 ;

        for( int  i = 0 ; i < gameState.length ; i++ ) gameState[i] = 2 ;
        /*
            Below code resets every image to null . Hence 0 means empty image(No Image) .
        */
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0) ;
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0) ;
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0) ;
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0) ;
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0) ;
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0) ;
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0) ;
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0) ;
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0) ;

        /*
             The turnStatus is again set to "X turn  "
        */
        TextView status = findViewById(R.id.turnStatus) ;
        status.setText("X Turn : Tap To Play") ;
    }
}
