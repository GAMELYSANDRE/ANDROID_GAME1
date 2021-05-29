package com.gamelysandre.ti_ta_to;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        m_SpinnerNbrPlayer = findViewById(R.id.spinner1);
        m_Player1= (EditText) findViewById(R.id.Player1);
        m_Player2= (EditText) findViewById(R.id.Player2);
        //create a list of items for the spinner.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, m_items);
        m_SpinnerNbrPlayer.setAdapter(adapter);
        //listener
        m_SpinnerNbrPlayer.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        m_SpinnerText = m_SpinnerNbrPlayer.getSelectedItem().toString();
        if ( m_SpinnerText.equals(m_items[0]))
        {
            Log.d("DEBUG", "ok ");
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
}