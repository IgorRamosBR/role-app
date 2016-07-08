package br.com.mytho.role.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
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
    @BindView(R.id.tv_seemore) TextView mSeemore;

    private Event event;

    private boolean expandable = false, expand = false;

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

        aboutExpandable();
        imageEventClick();

    }

    //ABOUT VIEW EXPANDABLE
    private void aboutExpandable(){
        mAbout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(expandable){
                    expandable = false;
                    if(mAbout.getLineCount() > 4) {
                        mSeemore.setVisibility(View.VISIBLE);
                        ObjectAnimator animation = ObjectAnimator.ofInt(mAbout, "maxLines", 4);
                        animation.setDuration(0).start();
                    }
                }
            }
        });

        mSeemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!expand) {
                    expand = true;
                    ObjectAnimator animation = ObjectAnimator.ofInt(mAbout, "maxLines", 40);
                    animation.setDuration(100).start();
                    mSeemore.setText("Ver menos");
                } else {
                    expand = false;
                    ObjectAnimator animation = ObjectAnimator.ofInt(mAbout, "maxLines", 4);
                    animation.setDuration(100).start();
                    mSeemore.setText("Ver mais");
                }
            }
        });
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
    public void imageEventClick() {
        mEventPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pictureIntent = new Intent(EventActivity.this, PictureActivity.class);
                pictureIntent.putExtra("uri", event.getImageUri().toString());
                pictureIntent.putExtra("title", event.getTitle());

                startActivity(pictureIntent);
            }
        });
    }

}
