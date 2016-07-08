package br.com.mytho.role.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.mytho.role.R;
import br.com.mytho.role.model.Event;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EventActivity extends AppCompatActivity {

    @BindView(R.id.tv_data_hora) TextView mEventDateTime;
    @BindView(R.id.tv_local) TextView mEventLocal;
    @BindView(R.id.tv_aboutt) TextView mAbout;
    @BindView(R.id.iv_event) ImageView mEventPicture;
    private Event event;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        event = receiveEvent();
        fillViews();

    }


    //AFTER, THIS METHOD WILL GET THE RESULT FROM ANOTHER ACTIVITY
    private Event receiveEvent() {
        Event event = new Event();
        event.setTitle("Wesley Safadao");
        event.setImageUri(Uri.parse("http://www.festanazoom.com.br/wp-content/uploads/2015/02/banner1.jpg"));
        return event;
    }

    //FILL THE VIEWS WITH CONTENTS
    private void fillViews() {
        Picasso
                .with(this)
                .load(event.getImageUri())
                .placeholder(R.drawable.role)
                .fit()
                .into(mEventPicture);
        
        mEventDateTime.setText("Segunda, 27 de Junho às 20h");
        mEventLocal.setText("Palace Hall");
        mAbout.setText("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxl");
    }

    //CLICK IMAGE EVENT
    public void imageEventClick(View view) {
                Intent pictureIntent = new Intent(EventActivity.this, PictureActivity.class);
                pictureIntent.putExtra("uri", event.getImageUri().toString());
                pictureIntent.putExtra("title", event.getTitle());

                startActivity(pictureIntent);
    }

}
