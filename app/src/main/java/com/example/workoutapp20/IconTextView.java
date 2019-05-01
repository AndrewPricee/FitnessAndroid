package com.example.workoutapp20;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;

public class IconTextView extends AppCompatTextView {

    private Context context;
    private Typeface font = Typeface.createFromAsset(getContext().getAssets(), "FontAwesome.otf");


    public IconTextView(Context context) {
        super(context);
        this.context = context;
        createView();
    }

    public IconTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        createView();
    }

    private void createView(){
        setGravity(Gravity.CENTER);
        setTypeface(font);
    }
}
