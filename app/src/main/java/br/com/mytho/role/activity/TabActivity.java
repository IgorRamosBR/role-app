package br.com.mytho.role.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import br.com.mytho.role.R;


public abstract class TabActivity extends AppCompatActivity {
    private static TabLayout tabLayout;
    private int layout;

    protected TabActivity(int layout) {
        this.layout = layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tabanim_tabs);

        prepareTabs();
    }

    public static class TabAssembler {
        private int resource;
        private String label;

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

    public abstract void prepareTabs();

}
