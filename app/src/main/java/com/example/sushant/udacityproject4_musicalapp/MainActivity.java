package com.example.sushant.udacityproject4_musicalapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import it.gmariotti.cardslib.library.view.CardListView;
import it.gmariotti.cardslib.library.view.CardView;
import it.gmariotti.cardslib.library.view.CardViewNative;
import it.gmariotti.cardslib.library.view.component.CardHeaderView;

public class MainActivity extends AppCompatActivity implements Card.OnCardClickListener{

    int thumbnail_id[]=new int[]    {R.drawable.thumbnail_eminem,R.drawable.thumbnail_coldplay,R.drawable.thumbnail_djsnake,R.drawable.thumbnail_imaginedragons};
    String headerNameList[]=new String[]{"Eminem","Coldplay","DJ Snake","Imagine Dragons"};
    String songNameList[]=new String[]{"Rap God","Fix You","You know you like it","Demons"};
    Button button_play;
    static MediaPlayer mediaPlayer;
    CardHeaderView cardHeaderView;
    CardView cardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Card> cards=new ArrayList<Card>();

            for(int i=0;i<=3;i++) {
                Card card = new Card(this);
                card.setOnClickListener(this);
                CardHeader cardHeader = new CardHeader(this);
                cardHeader.setTitle(headerNameList[i]);
                card.setTitle(songNameList[i]);
                card.addCardHeader(cardHeader);

                CardThumbnail thumbnail = new CardThumbnail(this);
                thumbnail.setDrawableResource(thumbnail_id[i]);

                card.addCardThumbnail(thumbnail);
                cards.add(card);


            }

        CardArrayAdapter mCardArrayAdapter=new CardArrayAdapter(this,cards);
        CardListView cardListView=(CardListView)findViewById(R.id.cardListView);
        if(cardListView!=null)
        {
            cardListView.setAdapter(mCardArrayAdapter);
        }


        mediaPlayer=new MediaPlayer();

    }

    @Override
    public void onClick(Card card, View view) {
        String HeaderName=card.getCardHeader().getTitle();
        Intent intent=new Intent(this,SongsManager.class);
        intent.putExtra("songselected",HeaderName.toLowerCase());
        startActivity(intent);


    }

}
