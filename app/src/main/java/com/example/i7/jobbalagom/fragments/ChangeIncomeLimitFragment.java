package com.example.i7.jobbalagom.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.i7.jobbalagom.R;
import com.example.i7.jobbalagom.callbacks.ChangeIncomeLimitFragmentCallback;

/**
 * Created by Anton, Christoffer, Kajsa, Jakup and Morgan.
 * Handles the Change income limit screen.
 */

public class ChangeIncomeLimitFragment extends Fragment {
    private ChangeIncomeLimitFragmentCallback callback;
    private TextView currentIncomeLimitText;
    private Button btnChangeIncomeLimit;
    private EditText newIncomeLimitField;


    /**
     * Initializes fragment.
     * @param inflater layout object that is used to show the layout of fragment.
     * @param container the parent view this fragment is added to.
     * @param savedInstanceState used for saving non persistent data that get's restored if the fragment needs to be recreated.
     * @return view hierarchu associated with fragment.
     */

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_income_limit, container, false);
    }


    /**
     * Called after the onCreateView has executed makes final UI initializations.
     * @param  view  this fragment view.
     * @param savedInstanceState used for saving non persistent data that get's restored if the fragment needs to be recreated.
     * @return view hierarchu associated with fragment.
     */
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponents(view);
        setTextEditTax();
    }

    /**
     * Initializes components.
     * @param  v  this fragment v.
     */

    public void initComponents(View v) {
        currentIncomeLimitText = (TextView) v.findViewById(R.id.currentIncomeLimitText);
        btnChangeIncomeLimit = (Button) v.findViewById(R.id.btnChangeIncomeLimit);
        newIncomeLimitField = (EditText) v.findViewById(R.id.newIncomeLimitField);
        btnChangeIncomeLimit.setOnClickListener(new IncomeLimitListener());
    }

    /**
     * Listener for chaning the incomeLimit.
     */

    private class IncomeLimitListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            float newLimit = Float.parseFloat(newIncomeLimitField.getText().toString());
            if(newLimit >0f && newLimit<200000f) {
                callback.setIncomeLimit(newLimit);
                setTextEditTax();
                Toast.makeText(getActivity(), "Fribeloppet är ändrat!", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getActivity(), "Fribeloppet måste vara mellan 0-200 000kr.", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * Sets callback.
     * @param callback listener.
     */

    public void setCallBack(ChangeIncomeLimitFragmentCallback callback){
        this.callback=callback;
    }

    /**
     * Sets the current income limit to the income limit retrived from the internal database.
     */

    private void setTextEditTax(){
        currentIncomeLimitText.setText(String.valueOf(callback.getIncomeLimit()));
    }
}