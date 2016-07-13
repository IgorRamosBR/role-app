package br.com.mytho.role.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import br.com.mytho.role.R;
import br.com.mytho.role.adapter.RecyclerEventsAdapter;
import br.com.mytho.role.domain.service.EventService;
import br.com.mytho.role.model.Event;
import br.com.mytho.role.security.OAuthAccessTokenService;
import br.com.mytho.role.security.model.AccessToken;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by leonardocordeiro on 26/06/16.
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.events)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar_main)
    Toolbar toolbar;
    @BindView(R.id.tabanim_tabs)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        prepareTabs();

        prepareRecyclerView();

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

        List<Event> events = Arrays.asList(event1, event2, event3);

        recyclerView.setAdapter(new RecyclerEventsAdapter(events));

        OAuthAccessTokenService oAuthAccessTokenService = new OAuthAccessTokenService.Builder().build();
        Call<AccessToken> callForAccessToken = oAuthAccessTokenService.getAccessToken("public-area", "client_credentials");
        callForAccessToken.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                if(!response.isSuccessful()) {
                    // error handling
                } else {
                    process(response);
                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void process(Response<AccessToken> response) {
        AccessToken token = response.body();
        EventService service = new EventService.Builder().accessToken(token).build();
        Call<List<Event>> callForList = service.list();
        callForList.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if(!response.isSuccessful()) {
                    // error handling
                } else {
                    for(Event event : response.body()) {
                        Toast.makeText(MainActivity.this, event.getTitle() + " - " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(event.getDate().getTime()), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void prepareTabs() {
        TabAssembler assembler = new TabAssembler(tabLayout);

        assembler.withIcon(R.drawable.ic_party).add();
        assembler.withIcon(R.drawable.ic_filter).add();
    }

    public void prepareRecyclerView() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
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

            if (label == null || label.isEmpty())
                tab.setIcon(resource);
            else if (resource == 0)
                tab.setText(label);

            tabLayout.addTab(tab);
        }

    }
}
