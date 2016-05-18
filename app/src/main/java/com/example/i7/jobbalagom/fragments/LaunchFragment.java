package com.example.i7.jobbalagom.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.i7.jobbalagom.R;
import com.example.i7.jobbalagom.callback_interfaces.LaunchFragmentCallback;

/**
 * Created by Kajsa on 2016-05-02.
 */

public class LaunchFragment extends Fragment {

    private LaunchFragmentCallback callback;
    private ButtonListener btnListener;
    private ImageButton btnLogo, btnNew, btnKey, btnInfo;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_launch, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponents(view);
    }

    public void initComponents(View view) {
        btnListener = new ButtonListener();
        btnLogo = (ImageButton) view.findViewById(R.id.btnLogo);
        btnLogo.setOnClickListener(btnListener);
        btnNew = (ImageButton) view.findViewById(R.id.btnNew);
        btnNew.setOnClickListener(btnListener);
        btnKey = (ImageButton) view.findViewById(R.id.btnKey);
        btnKey.setOnClickListener(btnListener);
        btnInfo = (ImageButton) view.findViewById(R.id.btnInfo);
        btnInfo.setOnClickListener(btnListener);
    }

    public void setCallBack(LaunchFragmentCallback callback){
        this.callback = callback;
    }

    private class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btnLogo) {
                callback.navigate("btnLogo");
            } else if(v.getId() == R.id.btnNew) {
                if(callback.checkConnection()){
                    callback.navigate("btnNew");
                }
                Toast.makeText(getActivity(), "Appen saknar anslutning till servern.", Toast.LENGTH_LONG).show();


            } else if(v.getId() == R.id.btnKey) {
                callback.navigate("btnKey");
            } else if(v.getId() == R.id.btnInfo) {
                callback.navigate("btnInfo");
            }
        }
    }

}
