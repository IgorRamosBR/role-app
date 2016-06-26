package br.com.mytho.role.activity;

import android.os.Bundle;

import br.com.mytho.role.R;

/**
 * Created by leonardocordeiro on 26/06/16.
 */
public class MainActivity extends TabActivity {

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public void prepareTabs() {
        TabAssembler assembler = new TabAssembler();

        assembler.withIcon(R.drawable.ic_party).add();
        assembler.withIcon(R.drawable.ic_filter).add();
    }
}
