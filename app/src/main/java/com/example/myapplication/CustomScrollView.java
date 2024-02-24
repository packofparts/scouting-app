package com.example.myapplication;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class CustomScrollView extends ScrollView {
        public CustomScrollView(Context context)  {
            super(context);
        }
        public CustomScrollView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }
        public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }
        @Override
        public int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
            return 0;
        }
}
