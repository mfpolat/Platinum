package com.cetur.platinum;

import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.rey.material.widget.Switch;


public class DriverStatusActivity extends ActionBarActivity implements View.OnClickListener, Switch.OnCheckedChangeListener {

    private TextView driverStatusActivityStatusOtherTV,driverStatusActivityAvailableTV,driverStatusActivityTrafficTV,driverStatusActivityServiceTV;
    private Switch driverStatusActivityStatusOkSW, driverStatusActivityStatusInTrafficSW, driverStatusActivityStatusInRepairSW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_status);
        setToolbar();
        initViews();
    }

    private void initViews() {
        driverStatusActivityStatusOtherTV = (TextView) findViewById(R.id.driverStatusActivityStatusOtherTV);
        driverStatusActivityStatusOtherTV.setOnClickListener(this);

        driverStatusActivityStatusInRepairSW = (Switch) findViewById(R.id.driverStatusActivityStatusInRepairSW);
        driverStatusActivityStatusInRepairSW.setOnCheckedChangeListener(this);
        driverStatusActivityStatusInTrafficSW = (Switch) findViewById(R.id.driverStatusActivityStatusInTrafficSW);
        driverStatusActivityStatusInTrafficSW.setOnCheckedChangeListener(this);
        driverStatusActivityStatusOkSW = (Switch) findViewById(R.id.driverStatusActivityStatusOkSW);
        driverStatusActivityStatusOkSW.setOnCheckedChangeListener(this);

        driverStatusActivityAvailableTV =(TextView)findViewById(R.id.driverStatusActivityAvailableTV);
        driverStatusActivityTrafficTV =(TextView)findViewById(R.id.driverStatusActivityTrafficTV);
        driverStatusActivityServiceTV =(TextView)findViewById(R.id.driverStatusActivityServiceTV);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Helvetica.ttf");

        driverStatusActivityStatusOtherTV.setTypeface(face);
        driverStatusActivityAvailableTV.setTypeface(face);
        driverStatusActivityTrafficTV.setTypeface(face);
        driverStatusActivityServiceTV.setTypeface(face);

    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        toolbar.setTitle(getString(R.string.driver_status));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_driver_status, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.driverStatusActivityStatusOtherTV:
                showOtherDialog();
                break;
        }
    }

    private void showOtherDialog() {

    }

    @Override
    public void onCheckedChanged(Switch aSwitch, boolean b) {
        switch (aSwitch.getId()) {
            case R.id.driverStatusActivityStatusInRepairSW:
                Log.e("XXXXXXX", "Serviste  " + String.valueOf(b));
                break;
            case R.id.driverStatusActivityStatusInTrafficSW:
                Log.e("XXXXXXX", "Trafikte  " + String.valueOf(b));
                break;
            case R.id.driverStatusActivityStatusOkSW:
                Log.e("XXXXXXX", "Uygu  " + String.valueOf(b));
                break;
        }
    }
}
