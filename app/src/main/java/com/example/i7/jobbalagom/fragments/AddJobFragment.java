package com.example.i7.jobbalagom.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.i7.jobbalagom.R;
import com.example.i7.jobbalagom.callbacks.AddJobFragmentCallback;

import java.util.LinkedList;

/**
 * Created by Kajsa, Anton, Christoffer, Jakup and Morgan.
 * Handles the add job screen.
 */

public class AddJobFragment extends Fragment {
    private AddJobFragmentCallback callback;
    private Button inputCreateOB, btnAddJob;
    private RelativeLayout obLayout;
    private EditText inputFromTime, inputToTime, inputOB, inputTitle, inputWage;
    private RadioButton rbWorkday, rbSaturday, rbSunday, rbPercent, rbKronor;
    private RadioGroup rgDay, rgType;
    private Button btnAddOB, btnExit;
    private LinkedList<String> obRates;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_addjob, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponents(view);
    }

    /**
     * Initialzes componenets.
     * @param view the view this class has.
     */

    public void initComponents(View view) {


        obLayout = (RelativeLayout) view.findViewById(R.id.obLayout);
        inputTitle = (EditText) view.findViewById(R.id.inputTitle);
        inputWage = (EditText) view.findViewById(R.id.inputWage);
        btnAddJob = (Button) view.findViewById(R.id.btnAddShift);
        inputFromTime = (EditText) view.findViewById(R.id.inputFromTime);
        inputToTime = (EditText) view.findViewById(R.id.inputToTime);
        inputOB = (EditText) view.findViewById(R.id.inputOB);
        rbWorkday = (RadioButton) view.findViewById(R.id.rbWorkday);
        rbSaturday = (RadioButton) view.findViewById(R.id.rbSaturday);
        rbSunday = (RadioButton) view.findViewById(R.id.rbSunday);
        rbPercent = (RadioButton) view.findViewById(R.id.rbPercent);
        rbKronor = (RadioButton) view.findViewById(R.id.rbKronor);
        rgDay = (RadioGroup) view.findViewById(R.id.rgDay);
        rgType = (RadioGroup) view.findViewById(R.id.rgType);
        inputCreateOB = (Button) view.findViewById(R.id.btnCreateOB);
        btnAddOB = (Button) view.findViewById(R.id.btnAddOB);
        btnExit = (Button) view.findViewById(R.id.btnExit);
        obRates = new LinkedList<String>();
        obLayout.setVisibility(View.INVISIBLE);
        inputCreateOB.setOnClickListener(new BtnCreateOBListener());
        btnExit.setOnClickListener(new ReturnListener());
        btnAddJob.setOnClickListener(new BtnAddJobListener());
        btnAddOB.setOnClickListener(new BtnAddOBListener());
    }

    /**
     * Sets callback for this class
     * @param callback a new AddJobFragmentListener.
     */

    public void setCallBack(AddJobFragmentCallback callback){
        this.callback = callback;
    }

    /**
     * Clears all inputfields.
     */

    public void clearAll() {
        inputTitle.setText("");
        inputWage.setText("");
        obRates.clear();
    }

    /**
     * Shows the OB view.
     */

    public void showOBLayout() {
        obLayout.setVisibility(View.VISIBLE);
    }

    /**
     * Hides the OB view.
     */

    public void hideOBLayout() {
        obLayout.setVisibility(View.INVISIBLE);
        inputFromTime.setText("");
        inputToTime.setText("");
        inputOB.setText("");
        rgDay.clearCheck();
        rgType.clearCheck();
    }

    /**
     * Adds buttonlistener for the ob buttons and also checks if input is valid.
     */

    private class BtnAddOBListener implements View.OnClickListener {
        String emptyInputMsg;
        String invalidInputMsg;
        public void onClick(View v) {

            emptyInputMsg = "Vänta lite, du glömde fylla i";

            if (rgDay.getCheckedRadioButtonId() == -1) {
                addError("dag");
            }
            if (inputFromTime.getText().toString().equals("") || inputToTime.getText().toString().equals("")) {
                addError("tid");
            }
            if (inputOB.getText().toString().equals("") || rgType.getCheckedRadioButtonId() == -1) {
                addError("OB");
            }
            emptyInputMsg = emptyInputMsg + ".";
            if(!emptyInputMsg.equals("Vänta lite, du glömde fylla i.")) {
                Toast.makeText(getActivity(), emptyInputMsg, Toast.LENGTH_LONG).show();
                return;
            }
            String fromTime = inputFromTime.getText().toString();
            String toTime = inputToTime.getText().toString();

            if(fromTime.length() != 5 || toTime.length() != 5) {
                invalidInputMsg = "Vänligen ange tid i formatet HH:MM";
                Toast.makeText(getActivity(), invalidInputMsg, Toast.LENGTH_LONG).show();
                return;
            }
            if(fromTime.charAt(2) != ':' || toTime.charAt(2) != ':') {
                invalidInputMsg = "Vänligen ange tid i formatet HH:MM.";
                Toast.makeText(getActivity(), invalidInputMsg, Toast.LENGTH_LONG).show();
                return;
            }
            if(fromTime.equals(toTime)) {
                invalidInputMsg = "De angivna tiderna stämmer inte.";
                Toast.makeText(getActivity(), invalidInputMsg, Toast.LENGTH_LONG).show();
                return;
            }

            int fromTimeHour = Integer.parseInt(fromTime.substring(0,2));
            int fromTimeMin = Integer.parseInt(fromTime.substring(3));
            int toTimeHour = Integer.parseInt(toTime.substring(0,2));
            int toTimeMin = Integer.parseInt(toTime.substring(3));

            if(fromTimeHour > 24 || fromTimeMin > 59 || toTimeHour > 24 || toTimeMin > 59) {
                invalidInputMsg = "Tiden som angetts är inte en giltig tid.";
                Toast.makeText(getActivity(), invalidInputMsg, Toast.LENGTH_LONG).show();
                return;
            }
            if(fromTimeHour > toTimeHour || (fromTimeHour == toTimeHour && fromTimeMin > toTimeMin)) {
                invalidInputMsg = "De angivna tiderna stämmer inte.";
                Toast.makeText(getActivity(), invalidInputMsg, Toast.LENGTH_LONG).show();
                return;
            }

            int id = rgDay.getCheckedRadioButtonId();
            RadioButton rb = (RadioButton) rgDay.findViewById(id);
            String day = rb.getText().toString();

            int id2 = rgType.getCheckedRadioButtonId();
            RadioButton rb2 = (RadioButton) rgType.findViewById(id2);
            String type = rb2.getText().toString();
            String ob = inputOB.getText().toString();

            String obRate = day + "," + fromTime + "," + toTime + "," + ob + "," + type;
            obRates.add(obRate);
            hideOBLayout();
            Toast.makeText(getActivity(), "OB har registrerats: " + ob + " " + type + " tillägg, " + day + ", från " + fromTime
                    + " till " + toTime, Toast.LENGTH_LONG).show();
            if(day.equals("Vardag")) {
                rbWorkday.setEnabled(false);
                rbWorkday.setTextColor(getResources().getColor(R.color.grey));
            } else if (day.equals("Lördag")) {
                rbSaturday.setEnabled(false);
                rbWorkday.setTextColor(getResources().getColor(R.color.grey));
            }else if(day.equals("Söndag")) {
                rbSunday.setEnabled(false);
                rbWorkday.setTextColor(getResources().getColor(R.color.grey));
            }
        }

        /**
         * Sets the error message.
         * @param error the string containing the error message.
         */

        public void addError(String error) {
            if(emptyInputMsg.charAt(emptyInputMsg.length()-1) == 'i') {
                emptyInputMsg = emptyInputMsg + " " + error;
            } else {
                emptyInputMsg = emptyInputMsg + ", " + error;
            }
        }
    }

    /**
     * Displays a gui where the user can provide information about the OB of the new job
     */

    private class BtnCreateOBListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            showOBLayout();
        }
    }

    /**
     * Enables the user to press outside of the OB layout box to return to the main AddJobFragment
     */

    private class ReturnListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            hideOBLayout();
        }
    }

    /**
     * Gathers input information and sends to MainActivity via callback
     */

    private class BtnAddJobListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            CharSequence emptyInputMsg = null;
            String jobTitle = inputTitle.getText().toString();
            String wage = inputWage.getText().toString();
            if(jobTitle.equals("")) {
                emptyInputMsg = "Vänta lite, du glömde fylla i jobbtitel.";
            }
            if (wage.equals("")) {
                emptyInputMsg = "Vänta lite, du glömde fylla i din timlön.";
            }
            if (jobTitle.equals("") && wage.equals("")) {
                emptyInputMsg = "Vänta lite, du glömde fylla i jobbtitel och timlön.";
            }

            if(emptyInputMsg != null) {
                Toast.makeText(getActivity(), emptyInputMsg, Toast.LENGTH_LONG).show();
                return;
            }
            callback.addJob(jobTitle, Float.parseFloat(wage));
            for(String obRate : obRates) {
                String[] parts = obRate.split(",");
                String day = parts[0];
                String fromTime = parts[1];
                String toTime = parts[2];
                String ob = parts[3];
                String type = parts[4];
                float obIndex = 0;

                if(type.equals("Kronor")) {
                    obIndex = 1 + Float.parseFloat(ob)/Float.parseFloat(wage);
                } else if(type.equals("Procent")) {
                    obIndex = 1 + Float.parseFloat(ob)/100;
                }
                callback.addOB(jobTitle, day, fromTime, toTime, obIndex);
            }
            clearAll();
            Toast.makeText(getActivity(), jobTitle + " är tillagt som ett nytt jobb.", Toast.LENGTH_LONG).show();
        }
    }
}
