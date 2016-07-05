package br.com.mytho.role.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import br.com.mytho.role.R;
import br.com.mytho.role.adapter.EventsAdapter;
import br.com.mytho.role.adapter.RecyclerEventsAdapter;
import br.com.mytho.role.model.Event;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by leonardocordeiro on 26/06/16.
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.events) ListView listView;
    @BindView(R.id.toolbar_main) Toolbar toolbar;
    @BindView(R.id.tabanim_tabs) TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabanim_tabs);

        prepareTabs();

        Event event1 = new Event();
        event1.setTitle("Teatro");

        Event event2 = new Event();
        event2.setTitle("Culto");

        List<Event> events = Arrays.asList(event1, event2);

        listView.setAdapter(new EventsAdapter(this, events));

    }

    public void prepareTabs() {
        TabAssembler assembler = new TabAssembler(tabLayout);

        assembler.withIcon(R.drawable.ic_party).add();
        assembler.withIcon(R.drawable.ic_filter).add();
    }

    public static class TabAssembler {
        private int resource;
        private String label;
        private TabLayout tabLayout;

        public TabAssembler(TabLayout layout) {
            this.tabLayout = layout;
        }

        public TabAssembler withIcon(int resource) {
            this.resource = resource;
            return this;
        }

        public TabAssembler withLabel(String label) {
            this.label = label;
            return this;
        }

        public void add() {
            TabLayout.Tab tab = tabLayout.newTab();

            if(label == null || label.isEmpty())
                tab.setIcon(resource);
            else if(resource == 0)
                tab.setText(label);

            tabLayout.addTab(tab);
        }

    }
}
