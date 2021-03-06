package com.gamelysandre.ti_ta_to;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Board implements View.OnClickListener
{
    // Variable for the dimensions of the screen
    private Activity m_Activity;
    private DisplayMetrics m_DimensionScreen;
    private int m_WidthScreen;
    private int m_HeightScreen;

    // variable for the dimensions of the cases
    private Case[] m_Case;
    private int m_SideLength;
    private int m_SpacingVertical;
    private static final int SPACING = 50;
    private ConstraintLayout m_AppScreen;

    // possibility of winning
    private int [][] win = {
            {0,1,2},
            {3,4,5},
            {6,7,8},
            {0,3,6},
            {1,4,7},
            {2,5,8},
            {0,4,8},
            {6,4,2}
    };

    public boolean isPlayer()
    {
        return m_Player;
    }

    // variable player
    private boolean m_Player;

    // sounds
    private MediaPlayer m_SndCross;
    private MediaPlayer m_SndCircle;

    /**
     * Constructor
     *
     * @param activity
     */
    public Board(Activity activity)
    {
        m_Player = true;
        m_Activity = activity;
        DimensionScreen(activity);
        m_AppScreen = (ConstraintLayout) activity.findViewById(R.id.ScreenApp);
        m_SndCross = MediaPlayer.create(m_Activity, R.raw.cross);
        m_SndCircle = MediaPlayer.create(m_Activity, R.raw.circle);
        CreateBoard();
    }

    public void DimensionScreen(Activity activity)
    {
        m_DimensionScreen = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(m_DimensionScreen);
        m_WidthScreen = m_DimensionScreen.widthPixels;
        m_HeightScreen = m_DimensionScreen.heightPixels;

        Log.d("DEBUG", "Largeur : " + m_WidthScreen);
        Log.d("DEBUG", "Hauteur : " + m_HeightScreen);
    }

    public void CreateBoard()
    {
        // define space and side box
        m_SideLength = (m_WidthScreen - (4 * SPACING)) / 3;
        m_SpacingVertical = (m_HeightScreen - (m_SideLength * 3)) / 3;

        m_Case = new Case[9];

        for (int i = 0; i < 9; i++)
        {
            m_Case[i] = new Case(m_Activity);
            m_Case[i].setSide(m_SideLength);
            m_Case[i].setTag(i);
            m_Case[i].setOnClickListener((View.OnClickListener) m_Activity);
        }

        m_Case[0].setX(SPACING);
        m_Case[1].setX(SPACING + m_SideLength + SPACING);
        m_Case[2].setX(SPACING + m_SideLength + SPACING + m_SideLength + SPACING);
        m_Case[3].setX(SPACING);
        m_Case[4].setX(SPACING + m_SideLength + SPACING);
        m_Case[5].setX(SPACING + m_SideLength + SPACING + m_SideLength + SPACING);
        m_Case[6].setX(SPACING);
        m_Case[7].setX(SPACING + m_SideLength + SPACING);
        m_Case[8].setX(SPACING + m_SideLength + SPACING + m_SideLength + SPACING);
        m_Case[0].setY(m_SpacingVertical);
        m_Case[1].setY(m_SpacingVertical);
        m_Case[2].setY(m_SpacingVertical);
        m_Case[3].setY(m_SpacingVertical + m_SideLength + SPACING);
        m_Case[4].setY(m_SpacingVertical + m_SideLength + SPACING);
        m_Case[5].setY(m_SpacingVertical + m_SideLength + SPACING);
        m_Case[6].setY(m_SpacingVertical + m_SideLength + SPACING + m_SideLength + SPACING);
        m_Case[7].setY(m_SpacingVertical + m_SideLength + SPACING + m_SideLength + SPACING);
        m_Case[8].setY(m_SpacingVertical + m_SideLength + SPACING + m_SideLength + SPACING);

        for (Case index : m_Case)
        {
            m_AppScreen.addView(index);
        }
    }

    @Override
    public void onClick(View v)
    {
        int caseIndex = (int) v.getTag();
        Log.d("DEBUG", "case n??" + caseIndex + " Pressez ");
        if (m_Player == true)
        {
            m_Case[caseIndex].setSymbol(Symbol.CIRCLE);
            m_SndCircle.start();
        } else
        {
            m_Case[caseIndex].setSymbol(Symbol.CROSS);
            m_SndCross.start();
        }
        m_Player = !m_Player;
        winner();
    }

    public int winner()
    {
        for (int i = 0; i < win.length; i++)
        {
            if (( m_Case[win[i][0]].getType() == Symbol.CIRCLE &&
                  m_Case[win[i][1]].getType() == Symbol.CIRCLE &&
                  m_Case[win[i][2]].getType() == Symbol.CIRCLE)
            )
            {
                //Log.d("DEBUG", " Win joueur 1");
                //endGame("joueur 1");
                return 1;
            }
            if (( m_Case[win[i][0]].getType() == Symbol.CROSS &&
                    m_Case[win[i][1]].getType() == Symbol.CROSS &&
                    m_Case[win[i][2]].getType() == Symbol.CROSS)
            )
            {
                //Log.d("DEBUG", " Win joueur 2");
                //endGame("joueur 2");
                return 2;
            }
        }
        return 0;
    }

    public void reset()
    {
        for (Case index : m_Case)
        {
            index.setType(Symbol.EMPTY);
        }
    }

}
