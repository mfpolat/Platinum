package com.cetur.platinum;

import android.graphics.Typeface;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;


public class ProfileActivity extends ActionBarActivity {

    private FrameLayout profileActivityMainContainerFL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setToolbar();
        initViews();
    }

    private void initViews() {
        profileActivityMainContainerFL =(FrameLayout)findViewById(R.id.profileActivityMainContainerFL);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.profileActivityMainContainerFL, ProfileMainFragment.newInstance());
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        TextView toolbarMainHeaderTV =(TextView)toolbar.findViewById(R.id.toolbarMainHeaderTV);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Helvetica.ttf");
        toolbarMainHeaderTV.setTypeface(face);
        toolbarMainHeaderTV.setText(getString(R.string.profile));
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
