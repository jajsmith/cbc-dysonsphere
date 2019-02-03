package com.example.dysonsphere;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class ListViewFragment extends ListFragment
        implements AdapterView.OnItemClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
//                R.layout.list_item, R.id.article_title, R.array.ArticleTitles);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.ArticleTitles, android.R.layout.simple_list_item_1);

        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        // Make new fragment to show this selection.
        Fragment frag;
        frag = DetailsFragment.newInstance(0);

        // Execute a transaction, replacing any existing fragment
        // with this one inside the frame.
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.body, frag);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
}
