package com.example.dysonsphere;

import android.app.Activity;
import android.os.Bundle;

public class DetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            // During initial setup, plug in the details fragment.
            DetailsFragment details = new DetailsFragment();
            details.setArguments(getIntent().getExtras());
//            getFragmentManager().beginTransaction().add(android.R.id.details, details).commit();
        }
    }
}
