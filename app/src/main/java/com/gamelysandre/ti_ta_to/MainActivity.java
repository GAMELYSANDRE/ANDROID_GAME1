package com.gamelysandre.ti_ta_to;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    // management Spinner
    private Spinner m_SpinnerNbrPlayer;
    private String[] m_items = new String[]{"1 Joueur", "2 Joueurs"};
    private String m_SpinnerText;
    // others widgets
    private EditText m_Player1;
    private EditText m_Player2;
    private Button m_BtnValidate;
    // players' names
    private String m_NamePlayer1;
    private String m_NamePlayer2;
    // Constants
    private static final int ACTIVITY_REQUEST_CODE = 10001;
    public static final String NBR_OF_USER = "nbrOfUser";
    public static final String PLAYER_ONE = "PlayerOne";
    public static final String PLAYER_TWO = "PlayerTwo";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        m_SpinnerNbrPlayer = findViewById(R.id.spinner1);
        m_Player1= (EditText) findViewById(R.id.Player1);
        m_Player2= (EditText) findViewById(R.id.Player2);
        m_BtnValidate = (Button) findViewById(R.id.btnValidate);
        //create a list of items for the spinner.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, m_items);
        m_SpinnerNbrPlayer.setAdapter(adapter);
        //listener
        m_SpinnerNbrPlayer.setOnItemSelectedListener(this);
        m_BtnValidate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                playerName();
                Intent secondWindow = new Intent(MainActivity.this, Game.class);
                secondWindow.putExtra(NBR_OF_USER,1);
                secondWindow.putExtra(PLAYER_ONE,m_NamePlayer1);
                secondWindow.putExtra(PLAYER_TWO,m_NamePlayer2);
                startActivityForResult(secondWindow,ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        m_SpinnerText = m_SpinnerNbrPlayer.getSelectedItem().toString();
        if ( m_SpinnerText.equals(m_items[0]))
        {
            Log.d("DEBUG", "1 Joueur + ordi");
            m_Player2.setText("ORDINATEUR");
            m_Player2.setFocusable(false);
        }
        else if ( m_SpinnerText.equals(m_items[1]))
        {
            if ( m_Player2.getText().toString().equals("ORDINATEUR") )
            {
                m_Player2.setText("");
            }
            m_Player2.setFocusableInTouchMode(true);
        }
        Log.d("DEBUG", "onItemSelected: " + m_SpinnerText);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }

    private void playerName()
    {
        m_NamePlayer1 = m_Player1.getText().toString();
        m_NamePlayer2 = m_Player2.getText().toString();
    }
}