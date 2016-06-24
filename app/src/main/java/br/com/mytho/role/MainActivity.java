package br.com.mytho.role;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabanim_tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Eventos"));
        tabLayout.addTab(tabLayout.newTab().setText("Filtro"));

    }

    private void setupViewPager(ViewPager viewPager) {
//        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
//        adapter.addFrag(new DummyFragment(getResources().getColor(R.color.accent_material_light)), "CAT");
//        adapter.addFrag(new DummyFragment(getResources().getColor(R.color.ripple_material_light)), "DOG");
//        adapter.addFrag(new DummyFragment(getResources().getColor(R.color.button_material_dark)), "MOUSE");
//        viewPager.setAdapter(adapter);
    }

}
