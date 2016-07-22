package br.com.mytho.role.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import br.com.mytho.role.R;
import br.com.mytho.role.adapter.ViewPagerAdapter;
import br.com.mytho.role.fragments.ActivesFragment;
import br.com.mytho.role.fragments.InactivesFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyEventsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_main)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tabs_myevents)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_events);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupViewPager();

    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ActivesFragment(), getResources().getString(R.string.actives));
        adapter.addFragment(new InactivesFragment(), getResources().getString(R.string.inactives));

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }
}
