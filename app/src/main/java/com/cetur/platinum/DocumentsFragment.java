package com.cetur.platinum;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cetur.model.GetDriverDocumentsResponse;
import com.cetur.service.Network;
import com.cetur.service.NetworkListeners;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DocumentsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DocumentsFragment extends Fragment implements NetworkListeners.OnGetDriverDocumentsResponseRecievedListener {

    private int documentType;
    private View mView;
    private ViewPager documentsFragmentViewPager;

    public static DocumentsFragment newInstance(int documentType) {
        DocumentsFragment fragment = new DocumentsFragment();
        Bundle args = new Bundle();
        args.putInt("type", documentType);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.documentType = getArguments().getInt("type");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_documents, container, false);
        documentsFragmentViewPager = (ViewPager) mView.findViewById(R.id.documentsFragmentViewPager);
        Network network = new Network();
        network.getDriverDocumentsByType(AppController.getInstance().getAccesToken(), String.valueOf(documentType), getActivity());
        return mView;
    }

    @Override
    public void OnGetDriverDocumentsResponseRecieved(GetDriverDocumentsResponse response) {

    }


    private class DriverDocumentsAdapter extends FragmentPagerAdapter {

        private ArrayList<String> documentUrls;

        public DriverDocumentsAdapter(FragmentManager fm, ArrayList<String> urls) {
            super(fm);
            documentUrls = urls;
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            DocumentFragment documentFragment = DocumentFragment.newInstance(documentUrls.get(position));
            return documentFragment;
        }

        @Override
        public int getCount() {
            return documentUrls.size();
        }
    }
}
