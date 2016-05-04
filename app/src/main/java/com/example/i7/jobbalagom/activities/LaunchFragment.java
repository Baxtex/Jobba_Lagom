package com.example.i7.jobbalagom.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.i7.jobbalagom.R;
import com.example.i7.jobbalagom.activities.callback_interfaces.LaunchFragmentCallback;

/**
 * Created by Kajsa on 2016-05-02.
 */

public class LaunchFragment extends Fragment {

   private LaunchFragmentCallback callback;
    private ButtonListener buttonListener;
    private ImageButton btnLogo;
    private ImageButton btnNew;
    private ImageButton btnKey;
    private ImageButton btnInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_launch, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponents(view);
    }

    public void initComponents(View view) {
        buttonListener = new ButtonListener();
        btnLogo = (ImageButton) view.findViewById(R.id.btnLogo);
        btnLogo.setOnClickListener(buttonListener);
        btnNew = (ImageButton) view.findViewById(R.id.btnNew);
        btnNew.setOnClickListener(buttonListener);
        btnKey = (ImageButton) view.findViewById(R.id.btnKey);
        btnKey.setOnClickListener(buttonListener);
        btnInfo = (ImageButton) view.findViewById(R.id.btnInfo);
        btnInfo.setOnClickListener(buttonListener);
    }

    public void setCallBack(LaunchFragmentCallback callback){
        this.callback = callback;
    }

    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btnLogo) {
                callback.launch("btnLogo");
                Log.d("LaunchFragment", "btnLogo pressed");
            } else if(v.getId() == R.id.btnNew) {
                callback.launch("btnNew");
                Log.d("LaunchFragment", "btnNew pressed");
            } else if(v.getId() == R.id.btnKey) {
                callback.launch("btnKey");
                Log.d("LaunchFragment", "btnKey pressed");
            } else if(v.getId() == R.id.btnInfo) {
                callback.launch("btnInfo");
                Log.d("LaunchFragment", "btnInfo pressed");
            }
        }
    }
}
