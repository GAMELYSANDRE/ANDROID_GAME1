package com.gamelysandre.ti_ta_to;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;



public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private Board m_Board;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_Board = new Board(this);
        Log.d("DEBUG", "ETAT ONCREATE");
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
    }
}