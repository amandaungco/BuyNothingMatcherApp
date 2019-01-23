package com.example.amandaungco.buynothingmatcher.model;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

public class swipingTextView extends android.support.v7.widget.AppCompatTextView {

    private Boolean isOffer;


    public Boolean getOffer() {
        return isOffer;
    }

    public void setOffer(Boolean offer) {
        isOffer = offer;
    }


    public swipingTextView(Context context, AttributeSet attrs ) {
        super(context, attrs);
    }





}
