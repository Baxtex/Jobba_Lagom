package com.example.i7.jobbalagom.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.i7.jobbalagom.R;
import com.example.i7.jobbalagom.callback_interfaces.InitialFragmentCallback;

/**
 * Created by Kajsa on 2016-05-18.
 */

public class InitialFragment extends Fragment {

    private RelativeLayout layout;
    private InitialFragmentCallback callback;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_initial, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layout = (RelativeLayout) view.findViewById(R.id.layout);
        layout.setOnClickListener(new LayoutListener());
    }

    public void setCallBack(InitialFragmentCallback callback){
        this.callback = callback;
    }

    private class LayoutListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            callback.showLaunchFragment();
        }
    }
}