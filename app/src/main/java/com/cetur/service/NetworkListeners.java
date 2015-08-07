package com.cetur.service;

import com.cetur.model.GetDriverDocumentsResponse;
import com.cetur.model.LoginResponse;

/**
 * Created by Fatih on 2.8.2015.
 */
public class NetworkListeners {
    public interface OnLoginResponseRecievedListener {
        public void OnLoginResponseRecieved(LoginResponse response);
    }

    public interface OnGetDriverDocumentsResponseRecievedListener {
        public void OnGetDriverDocumentsResponseRecieved(GetDriverDocumentsResponse response);
    }
}
