package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;


public class CustomSeekBar extends androidx.appcompat.widget.AppCompatSeekBar {
    public CustomSeekBar (Context context) {
        super(context);
    }

    public CustomSeekBar (Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CustomSeekBar (Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected synchronized void onDraw(Canvas c) {
        super.onDraw(c);
        int thumb_x = (int) (( (double)this.getProgress()/this.getMax() ) * (double)this.getWidth() * 0.8);
        float middle = (float) (this.getHeight()/2.0 + 8);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(24);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        String text = this.getProgress() + "s";
        c.drawText(text , this.getProgress() > 25 ? (thumb_x + 15 * -(text.length() - 3)) : (thumb_x + 15), middle, paint);
    }
}