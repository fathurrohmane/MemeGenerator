package com.elkusnandi.memegenerator.feature.detail;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.elkusnandi.memegenerator.R;
import com.elkusnandi.memegenerator.data.model.Result;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    public static final String EXTRA_IMAGE_URL = "extra_image_url";
    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_DESC_1 = "extra_desc_1";
    public static final String EXTRA_DESC_2 = "extra_desc_2";
    public static final String EXTRA_VOTES = "extra_votes";

    @BindView(R.id.image_view_thumbnail)
    ImageView imageViewThumbail;
    @BindView(R.id.text_view_meme_title)
    TextView textViewTitle;
    @BindView(R.id.text_view_meme_description)
    TextView textViewDescription;
    @BindView(R.id.text_view_votes)
    TextView textViewVotes;

    private String imageUrl;
    private String title;
    private String desc1;
    private String desc2;
    private int votes;

    public DetailActivityFragment() {
    }

    public static DetailActivityFragment newInstance(Result result) {
        DetailActivityFragment fragment = new DetailActivityFragment();

        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_IMAGE_URL, result.getImageUrl());
        bundle.putString(EXTRA_TITLE, result.getDisplayName());
        bundle.putString(EXTRA_DESC_1, result.getText0());
        bundle.putString(EXTRA_DESC_2, result.getText1());
        bundle.putInt(EXTRA_VOTES, result.getTotalVotesScore());

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            imageUrl = getArguments().getString(EXTRA_IMAGE_URL);
            title = getArguments().getString(EXTRA_TITLE);
            desc1 = getArguments().getString(EXTRA_DESC_1);
            desc2 = getArguments().getString(EXTRA_DESC_2);
            votes = getArguments().getInt(EXTRA_VOTES);
        }

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);

        // set views
        Picasso.get().load(imageUrl).into(imageViewThumbail);
        textViewTitle.setText(title);
        textViewDescription.setText(getString(R.string.meme_detail_description, desc1, desc2));
        textViewVotes.setText(getString(R.string.meme_detail_votes, votes));

        return view;
    }
}
