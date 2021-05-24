package com.gamelysandre.ti_ta_to;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.View;


public class Case extends View
{
    private Paint m_Paint;
    private Canvas m_Canvas;
    private int m_Side;
    private Symbol m_Type;

    public int getSide()
    {
        return m_Side;
    }

    public void setSide(int side)
    {
        m_Side = side;
    }


    // Constructors
    public Case(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public Case(Context context)
    {
        super(context);
        setFocusable(true);
        setClickable(true);
        m_Paint = new Paint();
        m_Type = Symbol.EMPTY;
    }


    @Override
    protected void onDraw(Canvas canvas)
    {
        m_Canvas = canvas;
        m_Paint.setColor(0xff979797);
        m_Canvas.drawRoundRect(0, 0, m_Side, m_Side, 50, 50, m_Paint);

        m_Paint.setColor(0xffBBBBBB);
        m_Canvas.drawRoundRect(10, 10, m_Side - 10, m_Side - 10, 50, 50, m_Paint);

        switch (m_Type)
        {
            case EMPTY:
                break;
            case CIRCLE:
                this.m_Paint.setColor(0xff00BB00);
                m_Canvas.drawCircle (
                        (float) (m_Side / 2),
                        (float) (m_Side / 2),
                        (float) ((m_Side / 2) * 0.8),
                        this.m_Paint);
                this.m_Paint.setColor(0xffBBBBBB);
                m_Canvas.drawCircle (
                        (float) (m_Side / 2),
                        (float) (m_Side / 2),
                        (float) ((m_Side / 2) * 0.65),
                        this.m_Paint);
                break;
            case CROSS:
                this.m_Paint.setColor(0xff880000);
                Path rect1 = new Path();
                rect1.moveTo(70,50);
                rect1.lineTo(m_Side-50,m_Side-70);
                rect1.lineTo(m_Side-70,m_Side-50);
                rect1.lineTo(50,70);
                m_Canvas.drawPath(rect1,m_Paint);
                Path rect2 = new Path();
                rect2.moveTo(m_Side-70,50);
                rect2.lineTo(m_Side-50,70);
                rect2.lineTo(70,m_Side-50);
                rect2.lineTo(50,m_Side-70);
                m_Canvas.drawPath(rect2,m_Paint);
                break;
        }

        super.onDraw(m_Canvas);
    }

    public void setSymbol(Symbol symbol)
    {
        if ( m_Type == Symbol.EMPTY )
        {
            m_Type = symbol;
        }
        postInvalidate();
    }

}
