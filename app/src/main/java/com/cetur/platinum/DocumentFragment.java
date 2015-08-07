package com.cetur.platinum;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DocumentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DocumentFragment extends Fragment {

    private String documentUrl;
    private View mView;
    private ImageView driverDocumentFragmentDocumentIV;

    public static DocumentFragment newInstance(String picUrl) {
        DocumentFragment fragment = new DocumentFragment();
        Bundle args = new Bundle();
        args.putString("url", picUrl);
        fragment.setArguments(args);
        return fragment;
    }

    public DocumentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            documentUrl = getArguments().getString("url");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_dirver_document, container, false);
        driverDocumentFragmentDocumentIV = (ImageView) mView.findViewById(R.id.driverDocumentFragmentDocumentIV);
        Picasso.with(getActivity()).load(documentUrl).into(driverDocumentFragmentDocumentIV);
        return mView;
    }


}
