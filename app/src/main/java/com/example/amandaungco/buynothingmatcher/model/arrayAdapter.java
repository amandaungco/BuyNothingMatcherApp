package com.example.amandaungco.buynothingmatcher.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.amandaungco.buynothingmatcher.R;

import java.util.List;

public class arrayAdapter extends ArrayAdapter<Card> {

    Context context;

    public arrayAdapter(Context context, int resourceId, List<Card> items) {
        super(context, resourceId, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Card card_item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        swipingTextView swipeItemTitile = convertView.findViewById(R.id.swipeItemTitle);
        ImageView image = convertView.findViewById(R.id.swipingImage);
        Boolean isOffer;
        isOffer = card_item.getOffer();
        String type;

        if (isOffer) {
            type = "Offer";
            swipeItemTitile.setOffer(true);
        } else {
            type = "Request";
            swipeItemTitile.setOffer(false);
        }

        swipeItemTitile.setText(type + " " + card_item.getItemId()+ " : " + card_item.getTitle());
        if (card_item.getImageUrl() instanceof String) {
            switch (card_item.getImageUrl()) {
                case "default":
                    Glide.with(convertView.getContext()).load(R.mipmap.ic_launcher).into(image);
                    break;
                default:
//                Glide.clear(image);
                    Glide.with(convertView.getContext()).load(card_item.getImageUrl()).into(image);
                    break;
            }
        }


        return convertView;

    }
}
