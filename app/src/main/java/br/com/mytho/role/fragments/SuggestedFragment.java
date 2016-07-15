package br.com.mytho.role.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

import br.com.mytho.role.R;
import br.com.mytho.role.adapter.RecyclerEventsAdapter;
import br.com.mytho.role.model.Event;

public class SuggestedFragment extends Fragment {

    private List<Event> mEvents;
    private RecyclerView mRecyclerView;
    public SuggestedFragment(){
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_suggested, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.events);

        fillList();

        prepareRecyclerView();

        return view;
    }

    public void prepareRecyclerView() {
        mRecyclerView.setAdapter(new RecyclerEventsAdapter(mEvents));
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void fillList() {
        Event event1 = new Event();
        event1.setTitle("Big Title for event title size test - With Darker BG");
        event1.setImageLink("android.resource://br.com.mytho.role/" + R.drawable.event_image3);
        event1.setAbout("Etiam posuere quam ac quam. Maecenas aliquet accumsan leo. Etiam posuere quam ac quam. Maecenas aliquet accumsan leo.");

        Event event2 = new Event();
        event2.setTitle("Medium Title with whiter image");
        event2.setImageLink("android.resource://br.com.mytho.role/" + R.drawable.event_image2);
        event2.setAbout("Etiam posuere quam ac quam. Maecenas aliquet accumsan leo.");

        Event event3 = new Event();
        event3.setTitle("Small Title");
        event3.setImageLink("android.resource://br.com.mytho.role/" + R.drawable.event_image1);
        event3.setAbout("Etiam posuere quam.");

        mEvents = Arrays.asList(event1, event2, event3);
    }
}