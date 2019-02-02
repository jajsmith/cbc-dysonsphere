package com.example.dysonsphere;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class HeaderFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.header_fragment,
                container, false);
        final RadioGroup radioGroup = rootView.findViewById(R.id.radio);

        radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        View radioButton = radioGroup.findViewById(checkedId);
                        int index = radioGroup.indexOfChild(radioButton);

                        boolean checked = ((RadioButton) radioButton).isChecked();

                        // Check which radio button was clicked
                        switch(radioButton.getId()) {
                            case R.id.radio_pirates:
                                if (checked)
                                    // Pirates are the best
                                    changeFragment(false);
                                    break;
                            case R.id.radio_ninjas:
                                if (checked)
                                    // Ninjas rule
                                    changeFragment(true);
                                    break;
                        }
                    }
                });

        // Return the View for the fragment's UI.
        return rootView;
    }

    public void changeFragment(boolean isDetails) {
        // Make new fragment to show this selection.
        Fragment frag;
        if (isDetails) {
            frag = DetailsFragment.newInstance(0);
        } else {
            frag  = new TitlesFragment();
        }

        // Execute a transaction, replacing any existing fragment
        // with this one inside the frame.
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.details, frag);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
}
