package com.cetur.platinum;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.cetur.model.LoginResponse;
import com.cetur.service.Network;
import com.cetur.service.NetworkListeners;


public class LoginActivity extends ActionBarActivity implements View.OnClickListener, NetworkListeners.OnLoginResponseRecievedListener {

    private EditText loginActivityMailET, loginActivityPassWordET;
    private TextView loginActivityLoginTV, loginActivityForgetPassTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {
        loginActivityMailET = (EditText) findViewById(R.id.loginActivityMailET);
        loginActivityPassWordET = (EditText) findViewById(R.id.loginActivityPassWordET);
        loginActivityLoginTV = (TextView) findViewById(R.id.loginActivityLoginTV);
        loginActivityLoginTV.setOnClickListener(this);
        loginActivityMailET.setText("urenkanaatli@aysasoft.com");
        loginActivityPassWordET.setText("123456");

        loginActivityForgetPassTV = (TextView) findViewById(R.id.loginActivityForgetPassTV);
        loginActivityForgetPassTV.setOnClickListener(this);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Helvetica.ttf");
        loginActivityForgetPassTV.setTypeface(face);
        loginActivityLoginTV.setTypeface(face);
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
        switch (view.getId()) {
            case R.id.loginActivityLoginTV:
                checkFields();
                break;
            case R.id.loginActivityForgetPassTV:
                showForgetPassDialog();
                break;
        }
    }

    EditText loginPassdialogMailET;

    private void showForgetPassDialog() {
        MaterialDialog forgetPassDialog = new MaterialDialog.Builder(this)
                .title("Parola Hatırlatma")
                .customView(R.layout.login_pass_dialog, true)
                .positiveText(getString(R.string.send))
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {

                        super.onPositive(dialog);
                        String userMail = loginPassdialogMailET.getText().toString();
                        if (userMail.isEmpty())
                            return;
                        else {

                        }
                    }
                })
                .build();
        forgetPassDialog.show();
        loginPassdialogMailET = (EditText) forgetPassDialog.findViewById(R.id.loginPassdialogMailET);

    }

    private void checkFields() {
//        startActivity(new Intent(LoginActivity.this, DemandListActivity.class));
        String userName = loginActivityMailET.getText().toString();
        String passWord = loginActivityPassWordET.getText().toString();
        if (userName.isEmpty() || passWord.isEmpty())
            return;
        Network network = new Network();
        network.login(loginActivityMailET.getText().toString(), loginActivityPassWordET.getText().toString(), "false", this);

    }

    @Override
    public void OnLoginResponseRecieved(LoginResponse response) {
        if (response != null) {
            if (response.getCode() != null) {
                if (response.getCode().equals("0")) {
                    AppController.getInstance().setUser(response.getPerson());
                    startActivity(new Intent(LoginActivity.this, DemandListActivity.class));
                } else {

                    new MaterialDialog.Builder(this)
                            .title(getString(R.string.error))
                            .content("Service ulaşırken hata meydana geldi")
                            .positiveText(getString(R.string.okey))
                            .callback(new MaterialDialog.ButtonCallback() {
                                @Override
                                public void onPositive(MaterialDialog dialog) {
                                    super.onPositive(dialog);
                                }
                            })
                            .show();
                }
            } else {
                new MaterialDialog.Builder(this)
                        .title(getString(R.string.error))
                        .content("Service ulaşırken hata meydana geldi")
                        .positiveText(getString(R.string.okey))
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                super.onPositive(dialog);
                            }
                        })
                        .show();
            }

        } else {
            new MaterialDialog.Builder(this)
                    .title(getString(R.string.error))
                    .content(getString(R.string.generel_error))
                    .positiveText(getString(R.string.okey))
                    .callback(new MaterialDialog.ButtonCallback() {
                        @Override
                        public void onPositive(MaterialDialog dialog) {
                            super.onPositive(dialog);
                        }
                    })
                    .show();
        }

    }
}
