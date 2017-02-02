package es.dpinfo.recuapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import es.dpinfo.recuapp.fragments.ConfigFragment;
import es.dpinfo.recuapp.fragments.ListProductsFragment;

/**
 * Created by dprimenko on 2/02/17.
 */
public class MainActivity extends AppCompatActivity implements ConfigFragment.ConfigFragmentListener {

    private FrameLayout flMain;
    private ConfigFragment configFragment;
    private ListProductsFragment listProductsFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flMain = (FrameLayout) findViewById(R.id.fl_main);
        configFragment = ConfigFragment.newInstance();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.fl_main, configFragment);
        ft.commit();
    }

    @Override
    public void onListProductFragment() {
        listProductsFragment = ListProductsFragment.newInstance();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_main, listProductsFragment);
        ft.commit();
    }
}
