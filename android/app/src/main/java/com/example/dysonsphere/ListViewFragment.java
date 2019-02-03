package com.example.dysonsphere;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListViewFragment extends ListFragment {

    private PlayerAdapter mPlayerAdapter;
    private int mCurrentIndex = -1;
    private PodcastAdapter mAdapter;

    private final ArrayList<Podcast> mPcasts = new ArrayList<Podcast>();

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

        // Construct the data source
        mPcasts.add(new Podcast(Data.TITLES[0], Data.CONTENT[0], R.drawable.square_1, R.raw.clip1_claire));
        mPcasts.add(new Podcast(Data.TITLES[1], Data.CONTENT[1], R.drawable.square_2, R.raw.clip2_claire));
        mPcasts.add(new Podcast(Data.TITLES[2], Data.CONTENT[2], R.drawable.square_3, R.raw.clip3_claire));

        // Create the adapter to convert the array to views
        mAdapter = new PodcastAdapter(getContext(), mPcasts);
        // Attach the adapter to a ListView
        ListView listView = getActivity().findViewById(R.id.listview);
        listView.setAdapter(mAdapter);
    }

    public void setPlayerAdapter(PlayerAdapter playerAdapter) {
        this.mPlayerAdapter = playerAdapter;
    }

    private void play() {
        if (mCurrentIndex >= 0) {
            mPlayerAdapter.loadMedia(mPcasts.get(mCurrentIndex).resource);
            mPlayerAdapter.reset();
            mPlayerAdapter.play();
            mPcasts.get(mCurrentIndex);

            ImageView img = getActivity().findViewById(R.id.header_image);
            img.setImageResource(R.drawable.white);

            TextView txt = getActivity().findViewById(R.id.header_text);
            txt.setText(Data.HEADER_TEXT[mCurrentIndex]);
            txt.setTextColor(getResources().getColor(R.color.black));
        } else {
            ImageView img = getActivity().findViewById(R.id.header_image);
            img.setImageResource(R.drawable.white);

            TextView txt = getActivity().findViewById(R.id.header_text);
            txt.setText("");
        }

        mAdapter.notifyDataSetChanged();
    }

    public void playItem(int position) {
        mCurrentIndex = position;
        play();
    }

    public void playNext() {
        if (mCurrentIndex < mPcasts.size() - 1) {
            mCurrentIndex++;
        } else {
            mCurrentIndex = -1;
        }
        play();
    }

    public void playPrevious() {
        if (mCurrentIndex > 0) {
            mCurrentIndex--;
        } else {
            mCurrentIndex = -1;
        }

        play();
    }

    public class Podcast {
        public String title;
        public String content;
        public int image;
        public int resource;

        public Podcast(String title, String content, int image, int resource) {
            this.title = title;
            this.content = content;
            this.image = image;
            this.resource = resource;
        }
    }

    public class PodcastAdapter extends ArrayAdapter<Podcast> {
        public PodcastAdapter(Context context, ArrayList<Podcast> podcasts) {
            super(context, 0, podcasts);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            final Podcast podcast = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            }

            // click listener for the view
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playItem(position);
                }
            });

            // Lookup view for data population
            TextView title = (TextView) convertView.findViewById(R.id.article_title);
            title.setText(podcast.title);
            if (mCurrentIndex == position) {
                title.setTypeface(null, Typeface.BOLD);
            } else {
                title.setTypeface(null, Typeface.NORMAL);
            }

            ImageView img = convertView.findViewById(R.id.image_view);
            img.setImageResource(podcast.image);

            ImageButton btButton = (ImageButton) convertView.findViewById(R.id.button);
            btButton.setTag(position);
            btButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Access user from within the tag
                    int position = (Integer) view.getTag();

                    // Make new fragment to show this selection.
                    Fragment frag;
                    frag = DetailsFragment.newInstance(position);

                    // Execute a transaction, replacing any existing fragment
                    // with this one inside the frame.
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.body, frag);
                    ft.addToBackStack(null);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.commit();

                    ImageView img = getActivity().findViewById(R.id.header_image);
                    img.setImageResource(Data.HEADER_IMAGES[position]);
                    TextView txt = getActivity().findViewById(R.id.header_text);
                    txt.setText("");
                }
            });

            // Return the completed view to render on screen
            return convertView;
        }
    }
}
