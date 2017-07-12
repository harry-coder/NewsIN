package com.okhlee.capp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.okhlee.capp.R;

public class signupFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView im_backbutton;
    private View fragmentContainerView;


    public signupFragment() {
        // Required empty public constructor
    }

    public static signupFragment newInstance(String param1, String param2) {
        signupFragment fragment = new signupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        fragmentContainerView=getActivity().findViewById(R.id.container_frame);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View signUpview = inflater.inflate(R.layout.fragment_signup, container, false);

        bindViews(signUpview);


        im_backbutton.setOnClickListener(this);
        return signUpview;
    }

    public void bindViews(View view) {
        im_backbutton = (ImageView) view.findViewById(R.id.im_backbutton);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.im_backbutton) {

            getActivity().getSupportFragmentManager().beginTransaction().replace(fragmentContainerView.getId(), new loginFragment()).commit();

        }
    }
}
