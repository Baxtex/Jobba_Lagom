package com.example.i7.jobbalagom.callback_interfaces;

import java.util.ArrayList;

/**
 * Created by Kajsa on 2016-05-04.
 */

public interface AddJobFragmentCallback {
    public void addJob(String title, Float wage);
    public void addOB(String jobTitle, String day, String fromTime, String toTime, float obIndex);
    public ArrayList<String> checkOB(String jobTitle, String day);
}
