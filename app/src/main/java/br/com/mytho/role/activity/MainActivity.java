package br.com.mytho.role.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import br.com.mytho.role.R;
import br.com.mytho.role.adapter.EventsAdapter;
import br.com.mytho.role.adapter.RecyclerEventsAdapter;
import br.com.mytho.role.model.Event;

/**
 * Created by leonardocordeiro on 26/06/16.
 */
public class MainActivity extends TabActivity {

    private RecyclerView.Adapter mAdapter;
    public MainActivity() {
        super(R.layout.activity_main);
    }
    private List<Event> events;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.events);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Event event1 = new Event();
        event1.setTitle("Teatro");

        Event event2 = new Event();
        event2.setTitle("Culto");

         events = Arrays.asList(event1, event2);

        EventsAdapter eventsAdapter = new EventsAdapter(this, events);
        mAdapter = new RecyclerEventsAdapter(events);

        recyclerView.setAdapter(mAdapter);


    }

    public void prepareTabs() {
        TabAssembler assembler = new TabAssembler();

        assembler.withIcon(R.drawable.ic_party).add();
        assembler.withIcon(R.drawable.ic_filter).add();
    }
}
