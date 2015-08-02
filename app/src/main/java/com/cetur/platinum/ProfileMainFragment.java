package com.cetur.platinum;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ProfileMainFragment extends Fragment implements View.OnClickListener {


    private View mView;
    private TextView profileMainFragmentNameTV,profileMainFragmentMailTV,profileMainFragmentBirthDateTV,profileMainFragmentPhoneTV,profileMainFragmentDriverLicenceTV,profileMainFragmentSrc2LicenceTV;
    public static ProfileMainFragment newInstance() {
        ProfileMainFragment fragment = new ProfileMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_profile_main, container, false);
        initViews();
        return  mView;
    }

    private void initViews() {

        profileMainFragmentNameTV =(TextView)mView.findViewById(R.id.profileMainFragmentNameTV);
        profileMainFragmentMailTV =(TextView)mView.findViewById(R.id.profileMainFragmentMailTV);
        profileMainFragmentBirthDateTV =(TextView)mView.findViewById(R.id.profileMainFragmentBirthDateTV);
        profileMainFragmentPhoneTV =(TextView)mView.findViewById(R.id.profileMainFragmentPhoneTV);
        profileMainFragmentDriverLicenceTV =(TextView)mView.findViewById(R.id.profileMainFragmentDriverLicenceTV);
        profileMainFragmentDriverLicenceTV.setOnClickListener(this);
        profileMainFragmentSrc2LicenceTV =(TextView)mView.findViewById(R.id.profileMainFragmentSrc2LicenceTV);
        profileMainFragmentSrc2LicenceTV.setOnClickListener(this);

        Typeface face = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Helvetica.ttf");
        profileMainFragmentNameTV.setTypeface(face);
        profileMainFragmentMailTV.setTypeface(face);
        profileMainFragmentBirthDateTV.setTypeface(face);
        profileMainFragmentPhoneTV.setTypeface(face);
        profileMainFragmentDriverLicenceTV.setTypeface(face);
        profileMainFragmentSrc2LicenceTV.setTypeface(face);

        profileMainFragmentNameTV.setText(AppController.getInstance().getUser().getName() + " " + AppController.getInstance().getUser().getSurname());
        profileMainFragmentMailTV.setText(AppController.getInstance().getUser().getMail());
        profileMainFragmentPhoneTV.setText(AppController.getInstance().getUser().getPhone1());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.profileMainFragmentDriverLicenceTV:
                openLicencesFragment();
                break;
            case R.id.profileMainFragmentSrc2LicenceTV:
                openLicencesFragment();
                break;
        }
    }

    private void openLicencesFragment() {

    }
}
