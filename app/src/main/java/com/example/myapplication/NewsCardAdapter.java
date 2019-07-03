package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class NewsCardAdapter extends RecyclerView.Adapter<NewsCardAdapter.CardHolderView> {

    ArrayList<CardDetail> mCardDetailArrayList;

    public NewsCardAdapter(ArrayList<CardDetail> cardDetailArrayList) {
        mCardDetailArrayList = cardDetailArrayList;
    }

    public class CardHolderView extends RecyclerView.ViewHolder {

        View newsCardView;


        public CardHolderView(@NonNull View itemView) {
            super(itemView);
            newsCardView = itemView;
        }
    }

    @NonNull
    @Override
    public NewsCardAdapter.CardHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View newsCardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card, parent, false);
        CardHolderView cardHolderView = new CardHolderView(newsCardView);
        return cardHolderView;

    }

    @Override
    public void onBindViewHolder(@NonNull CardHolderView holder, int position) {
        ImageView cardImageView = holder.newsCardView.findViewById(R.id.cardImageTextView);
        TextView articleHeadingTextView = holder.newsCardView.findViewById(R.id.cardHeadingTextView);
        TextView articleTextView = holder.newsCardView.findViewById(R.id.cardArticleTextView);

        CardDetail card = mCardDetailArrayList.get(position);
        cardImageView.setImageResource(card.getImgRscId());
        articleHeadingTextView.setText(card.getArticleHeading());
        articleTextView.setText(card.getArticle());

    }


    @Override
    public int getItemCount() {
        return mCardDetailArrayList.size();
    }
}
