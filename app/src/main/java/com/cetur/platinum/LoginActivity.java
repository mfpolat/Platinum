package com.cetur.platinum;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.ksoap2.transport.HttpTransportSE;

import java.net.Proxy;


public class LoginActivity extends ActionBarActivity implements View.OnClickListener {

    private EditText loginActivityMailET,loginActivityPassWordET;
    private TextView loginActivityLoginTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {
        loginActivityMailET =(EditText)findViewById(R.id.loginActivityMailET);
        loginActivityPassWordET =(EditText)findViewById(R.id.loginActivityPassWordET);
        loginActivityLoginTV =(TextView)findViewById(R.id.loginActivityLoginTV);
        loginActivityLoginTV.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
            case R.id.loginActivityLoginTV:
                checkFields();
                break;
        }
    }

    private void checkFields() {
        startActivity(new Intent(LoginActivity.this,DemandListActivity.class));
    }

    private final HttpTransportSE getHttpTransportSE() {
        HttpTransportSE ht = new HttpTransportSE(Proxy.NO_PROXY,Constants.SERVICE_BASE_URL,60000);
        ht.debug = true;
        ht.setXmlVersionTag("<!--?xml version=\"1.0\" encoding= \"UTF-8\" ?-->");
        return ht;
    }
}
