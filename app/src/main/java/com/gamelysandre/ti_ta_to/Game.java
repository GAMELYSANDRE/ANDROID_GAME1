package com.gamelysandre.ti_ta_to;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Game extends AppCompatActivity implements View.OnClickListener
{
        private Board m_Board;
        private int m_NbrOfUser;
        private TextView m_TxtNamePlayer;
        // players' names
        private String m_NamePlayer1;
        private String m_NamePlayer2;

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.game);
            m_TxtNamePlayer = (TextView) findViewById(R.id.txtNamePlayer);
            m_Board = new Board(this);
            Log.d("DEBUG", "GAME ACTIVITE -> ONCREATE");
            Intent intent = getIntent();
            if (intent != null){
                m_NbrOfUser = intent.getIntExtra(MainActivity.NBR_OF_USER,0);
                m_NamePlayer1 = intent.getStringExtra(MainActivity.PLAYER_ONE);
                m_NamePlayer2 = intent.getStringExtra(MainActivity.PLAYER_TWO);
                Log.d("DEBUG", "onCreate: "+ m_NbrOfUser);
            }
            changePlayer();
        }

        @Override
        protected void onResume()
        {
            super.onResume();
            Log.d("DEBUG", "ETAT ONRESUME");
        }

        @Override
        protected void onPause()
        {
            super.onPause();
            Log.d("DEBUG", "ETAT ONPAUSE");
        }

        @Override
        public void onClick(View v)
        {
            m_Board.onClick(v);
            changePlayer();
            if ( m_Board.winner() != 0 )
            {
                if ( m_Board.winner() == 1)
                {
                    endGame(m_NamePlayer1);
                }
                else
                {
                    endGame(m_NamePlayer2);
                }

            }
        }

        private void changePlayer()
        {
            if (m_Board.isPlayer() == true)
            {
                m_TxtNamePlayer.setText(m_NamePlayer1);
            }
            else
            {
                m_TxtNamePlayer.setText(m_NamePlayer2);
            }
        }

    private void endGame(String namePlayer)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(namePlayer)
                .setMessage("Tu gagnes cette partie !")
                .setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        m_Board.reset();
                    }
                })
                .create()
                .show();

    }
}