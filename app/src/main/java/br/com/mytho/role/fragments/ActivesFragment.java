package br.com.mytho.role.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
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
import br.com.mytho.role.adapter.RecyclerMyEventsAdapter;
import br.com.mytho.role.model.Event;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivesFragment extends Fragment {

    RecyclerView recyclerView;

    private List<Event> mEvents;

    public ActivesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_active, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.active_events);
        fillList();

        prepareRecyclerView();

        return view;
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

    public void prepareRecyclerView() {
        recyclerView.setAdapter(new RecyclerMyEventsAdapter(mEvents));
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
