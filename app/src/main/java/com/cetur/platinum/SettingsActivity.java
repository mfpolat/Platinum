package com.cetur.platinum;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class SettingsActivity extends ActionBarActivity implements View.OnClickListener {

    private TextView settingsActivityFeedbackTV,settingsActivityTermsOfUseTV,settingsActivityLogoutTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initViews();
        setToolbar();
    }

    private void initViews() {
        settingsActivityLogoutTV =(TextView)findViewById(R.id.settingsActivityLogoutTV);
        settingsActivityLogoutTV.setOnClickListener(this);
        settingsActivityTermsOfUseTV =(TextView)findViewById(R.id.settingsActivityTermsOfUseTV);
        settingsActivityTermsOfUseTV.setOnClickListener(this);
        settingsActivityFeedbackTV =(TextView)findViewById(R.id.settingsActivityFeedbackTV);
        settingsActivityFeedbackTV.setOnClickListener(this);
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        toolbar.setTitle(getString(R.string.settings));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.settingsActivityLogoutTV:
                break;
            case R.id.settingsActivityTermsOfUseTV:
                break;
            case R.id.settingsActivityFeedbackTV:
                break;
        }
    }
}
