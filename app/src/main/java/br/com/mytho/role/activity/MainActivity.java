package br.com.mytho.role.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.wuman.android.auth.AuthorizationFlow;
import com.wuman.android.auth.OAuthManager;
import com.wuman.android.auth.oauth2.store.SharedPreferencesCredentialStore;

import java.io.IOException;
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

        SharedPreferencesCredentialStore credentialStore = new SharedPreferencesCredentialStore(this, "token-store", new JacksonFactory());

        AuthorizationFlow authorizationFlow = new AuthorizationFlow.Builder(BearerToken.authorizationHeaderAccessMethod(),
                                                    AndroidHttp.newCompatibleTransport(),
                                                    new JacksonFactory(),
                                                    new GenericUrl("http://role-lema.rhcloud.com/rolebackend/"),
                                                    new ClientParametersAuthentication("mobile-client", "08282424-432a-11e6-beb8-9e71128cae77"),
                                                    "mobile-client", null)
                                                    .setScopes(Arrays.asList("public-area"))
                                                    .setCredentialStore(credentialStore)
                                                    .build();

        OAuthManager manager = new OAuthManager(authorizationFlow, null);
        try {
            Credential credentials = manager.authorizeImplicitly("userId", null, null).getResult();

        } catch (IOException e) {
            e.printStackTrace();
        }

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

        AccountHeader accountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                //Imagem do account header
                .withHeaderBackground(R.drawable.account_header_image)
                .addProfiles(
                        new ProfileDrawerItem().withName("Ban Ban")
                                .withEmail("BamBam@monstro.com")
                                //Imagem do perfil
                                .withIcon((R.drawable.perfil_image_3)
                                ))
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        //
                        Toast.makeText(getApplication(),"acc",Toast.LENGTH_SHORT);
                        return false;
                    }
                })
                .build();
        PrimaryDrawerItem item1 = new PrimaryDrawerItem()
                .withIdentifier(1)
                .withName("Minha conta");

        SecondaryDrawerItem item2 = (SecondaryDrawerItem) new SecondaryDrawerItem()
                .withIdentifier(2)
                .withIcon(R.drawable.ic_history_black_24dp)
                .withName("Historico");

        SecondaryDrawerItem item3 = (SecondaryDrawerItem) new SecondaryDrawerItem()
                .withIdentifier(3)
                .withIcon(R.drawable.ic_settings_black_24dp)
                .withName("Configurções");

        SecondaryDrawerItem item4 = (SecondaryDrawerItem) new SecondaryDrawerItem()
                .withIdentifier(4)
                .withIcon(R.drawable.ic_close_black_24dp)
                .withName("Sair");

        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(accountHeader)
                .addDrawerItems(
                        item1,
                        new DividerDrawerItem(),
                        item2,
                        item3,
                        item4
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        Toast.makeText(getApplication(),"biurl :"+ position,Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .build();

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
