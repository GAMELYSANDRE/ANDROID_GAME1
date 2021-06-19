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

    /**
     *  type of symbol
     * @return
     */
    public Symbol getType()
    {
        return m_Type;
    }

    /**
     * change symbol
     * @param type
     */
    public void setType(Symbol type)
    {
        m_Type = type;
        postInvalidate();
    }

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
                rect1.moveTo((int) (m_Side*0.2),(int) (m_Side*0.15));
                rect1.lineTo((int) (m_Side*0.85),(int) (m_Side*0.8));
                rect1.lineTo((int) (m_Side*0.8),(int) (m_Side*0.85));
                rect1.lineTo((int) (m_Side*0.15),(int) (m_Side*0.2));
                m_Canvas.drawPath(rect1,m_Paint);
                Path rect2 = new Path();
                rect2.moveTo((int) (m_Side*0.8),(int) (m_Side*0.15));
                rect2.lineTo((int) (m_Side*0.85),(int) (m_Side*0.2));
                rect2.lineTo((int) (m_Side*0.2),(int) (m_Side*0.85));
                rect2.lineTo((int) (m_Side*0.15),(int) (m_Side*0.8));
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
