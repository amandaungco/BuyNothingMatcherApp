package com.example.amandaungco.buynothingmatcher.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.amandaungco.buynothingmatcher.R;

import java.util.List;

import static java.security.AccessController.getContext;

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

        TextView swipeItemTitile = convertView.findViewById(R.id.swipeItemTitle);
        ImageView image =  convertView.findViewById(R.id.image);

        swipeItemTitile.setText("Item: " + card_item.getRequestId() + " " + card_item.getTitle());
//        switch (card_item.getProfileImageUrl()) {
//            case "default":
//                Glide.with(convertView.getContext()).load(R.mipmap.ic_launcher).into(image);
//                break;
//            default:
//                Glide.clear(image);
//                Glide.with(convertView.getContext()).load(card_item.getProfileImageUrl()).into(image);
//                break;
//        }


        return convertView;

    }
}
