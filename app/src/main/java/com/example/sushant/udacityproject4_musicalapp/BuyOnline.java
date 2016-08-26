package com.example.sushant.udacityproject4_musicalapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class BuyOnline extends AppCompatActivity implements View.OnClickListener {
    ImageView buy_itunes,buy_amazon,buy_napster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_online);

        buy_itunes=(ImageView)findViewById(R.id.image_itunes);
        buy_itunes.setOnClickListener(this);

        buy_amazon=(ImageView)findViewById(R.id.image_amazon);
        buy_amazon.setOnClickListener(this);

        buy_napster=(ImageView)findViewById(R.id.image_napster);
        buy_napster.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.image_itunes:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.apple.com/itunes/"));
                startActivity(browserIntent);
                break;
            case R.id.image_amazon:
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.com/b?node=8335758011"));
                startActivity(browserIntent);
                break;
            case R.id.image_napster:
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://us.napster.com/"));
                startActivity(browserIntent);
                break;

        }

    }
}
