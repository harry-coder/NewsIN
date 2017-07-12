package com.okhlee.capp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import com.okhlee.capp.CustomElements.CustomFontTextView;
import com.okhlee.capp.R;

public class loginFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private CustomFontTextView tv_login, tv_facebook, tv_forgetpassword, tv_createaccount;
    private EditText et_username, et_password;
    private View fragmentContainerView;


    private String mParam1;
    private String mParam2;


    public loginFragment() {
        // Required empty public constructor
    }

    public static loginFragment newInstance(String param1, String param2) {
        loginFragment fragment = new loginFragment();
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
        // Inflate the layout for this fragment
        View login_view = inflater.inflate(R.layout.fragment_login, container, false);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        bindViews(login_view);
        tv_createaccount.setOnClickListener(this);
        return login_view;
    }

    private void bindViews(View view) {
        //this will bind all the values
        tv_login = (CustomFontTextView) view.findViewById(R.id.tv_login);
       tv_createaccount = (CustomFontTextView)view. findViewById(R.id.tv_createaccount);
        tv_facebook = (CustomFontTextView)view. findViewById(R.id.tv_facebook);
        tv_forgetpassword = (CustomFontTextView)view. findViewById(R.id.tv_forgetpassword);
        et_username = (EditText)view. findViewById(R.id.et_username);
        et_password = (EditText)view. findViewById(R.id.et_password);

    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.tv_createaccount)
        {

        getActivity().getSupportFragmentManager().beginTransaction().replace(fragmentContainerView.getId(),new signupFragment()).commit();
        }

    }
}
