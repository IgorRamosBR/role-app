package br.com.mytho.role.activity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import br.com.mytho.role.R;
import br.com.mytho.role.adapter.EventsAdapter;
import br.com.mytho.role.model.Event;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by leonardocordeiro on 26/06/16.
 */
public class MainActivity extends TabActivity {

    @BindView(R.id.events) ListView listView;

    public MainActivity() {super(R.layout.activity_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        Event event1 = new Event();
        event1.setTitle("Teatro");

        Event event2 = new Event();
        event2.setTitle("Culto");

        List<Event> events = Arrays.asList(event1, event2);

        EventsAdapter eventsAdapter = new EventsAdapter(this, events);

        listView.setAdapter(eventsAdapter);
    }

    public void prepareTabs() {
        TabAssembler assembler = new TabAssembler();

        assembler.withIcon(R.drawable.ic_party).add();
        assembler.withIcon(R.drawable.ic_filter).add();
    }
}
