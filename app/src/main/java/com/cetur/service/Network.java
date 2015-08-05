package com.cetur.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.cetur.model.LoginResponse;
import com.cetur.model.Person;
import com.cetur.platinum.Constants;
import com.cetur.platinum.LoginActivity;
import com.cetur.platinum.R;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Fatih on 30.7.2015.
 */
public class Network {
    private String name, password,isWronCount;
    private Context mContext;


    public void login(String userName, String pass, String isWronCount,Context context) {
        name = userName;
        this.password = pass;
        this.isWronCount = isWronCount;
        mContext = context;
        new LoginTask().execute();
    }

    private class LoginTask extends AsyncTask<Void, Void, LoginResponse> {
        ProgressDialog dialog = new ProgressDialog(mContext);

        @Override
        protected void onPreExecute() {
            dialog.setCancelable(false);
            dialog.setMessage(mContext.getString(R.string.loading));
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected LoginResponse doInBackground(Void... voids) {
            Person user = new Person();
            LoginResponse response = new LoginResponse();
            SoapObject request = new SoapObject(Constants.NAME_SPACE, Constants.LOGIN_METHOD_NAME);
            request.addProperty("userName", name);
            request.addProperty("password", password);
            request.addProperty("isWrongCount", isWronCount);
            request.addProperty("platformType", "3");
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            envelope.dotNet = true;
            try {
                HttpTransportSE androidHttpTransport = new HttpTransportSE(Constants.SERVICE_BASE_URL);
                androidHttpTransport.call(Constants.LOGIN_SERVICE_SOAP_ACTION, envelope);
                SoapObject result = (SoapObject) envelope.bodyIn;

                if (result != null) {
                    Log.i("Network Response :   " ,result.toString());
                    //Get the first property and change the label text
                    SoapObject loginResult = (SoapObject) result.getProperty("loginResult");
                    response.setCode(loginResult.getProperty("code").toString());
                    response.setMessage(loginResult.getProperty("message").toString());
                    response.setDescription(loginResult.getProperty("description").toString());
                    response.setAcToken(loginResult.getProperty("acToken").toString());
                    if (response.getCode().equals("0")) {
                        user.setName(((SoapObject) loginResult.getProperty("person")).getProperty("name").toString());
                        user.setSurname(((SoapObject) loginResult.getProperty("person")).getProperty("surname").toString());
                       // user.setBirth(((SoapObject) loginResult.getProperty("person")).getProperty("birth").toString());
                        user.setMail(((SoapObject) loginResult.getProperty("person")).getProperty("mail").toString());
                        user.setPhone1(((SoapObject) loginResult.getProperty("person")).getProperty("phone1").toString());
                        response.setPerson(user);
                        return response;
                    }
                    else{
                        return response;
                    }
                } else {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(LoginResponse response) {
            dialog.dismiss();
            ((LoginActivity)mContext).OnLoginResponseRecieved(response);
            super.onPostExecute(response);
        }
    }
}
