package br.com.mytho.role.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import br.com.mytho.role.R;

public class PictureActivity extends AppCompatActivity {

    private ImageView mEventFullPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));

        mEventFullPicture = (ImageView) findViewById(R.id.iv_fullPicture);

        Picasso.with(this).setIndicatorsEnabled(true);

        Picasso
                .with(this)
                .load(Uri.parse(getIntent().getStringExtra("uri")))
                .placeholder(R.drawable.role)
                .into(mEventFullPicture);

    }



}
