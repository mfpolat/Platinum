package com.cetur.platinum;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.cetur.model.Demand;

import java.util.ArrayList;


public class NotificationsActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private ListView notificationActivityLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        setToolbar();
        notificationActivityLV = (ListView) findViewById(R.id.demandListActivityLV);
        ArrayList<Demand> demands = new ArrayList<>();
        for (int x = 0; x < 6; x++) {
            Demand demand = new Demand();
            demands.add(demand);
        }
        notificationActivityLV=(ListView)findViewById(R.id.notificationActivityLV);
        DemandListAdapter adapter = new DemandListAdapter(this, demands);
        notificationActivityLV.setAdapter(adapter);
        notificationActivityLV.setOnItemClickListener(this);
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        toolbar.setTitle("");
        TextView toolbarHeader = (TextView) toolbar.findViewById(R.id.toolbarMainHeaderTV);
        toolbarHeader.setText("Bildirimler");
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Helvetica.ttf");
        toolbarHeader.setTypeface(face);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notifications, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        startActivity(new Intent(this, DemandDetailActivity.class));
    }
}
