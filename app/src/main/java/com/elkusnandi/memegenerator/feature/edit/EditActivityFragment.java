package com.elkusnandi.memegenerator.feature.edit;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.elkusnandi.memegenerator.R;
import com.elkusnandi.memegenerator.data.model.Result;
import com.elkusnandi.memegenerator.feature.photo.PhotoActivity;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class EditActivityFragment extends Fragment {

    public static final String EXTRA_IMAGE_URL = "image_url";

    @BindView(R.id.layout_editor)
    ConstraintLayout constraintLayout;
    @BindView(R.id.image_view_background)
    ImageView imageViewBackground;
    @BindView(R.id.text_view_caption)
    TextView textViewCaption;

    private int imageType = -1;
    private Bitmap backgroundBitmap;
    private Uri backgroundUri;
    private Result backgroundResult;

    public EditActivityFragment() {
    }

    public static EditActivityFragment newInstance(Parcelable image, int imageType) {
        EditActivityFragment fragment = new EditActivityFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(PhotoActivity.EXTRA_IMAGE, image);
        bundle.putInt(PhotoActivity.EXTRA_IMAGE_TYPE, imageType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            imageType = getArguments().getInt(PhotoActivity.EXTRA_IMAGE_TYPE);
            switch (imageType) {
                case PhotoActivity.EXTRA_IMAGE_TYPE_BITMAP:
                    backgroundBitmap = getArguments().getParcelable(PhotoActivity.EXTRA_IMAGE);
                    break;
                case PhotoActivity.EXTRA_IMAGE_TYPE_URI:
                    backgroundUri = getArguments().getParcelable(PhotoActivity.EXTRA_IMAGE);
                    break;
                case PhotoActivity.EXTRA_IMAGE_TYPE_URL:
                    backgroundResult = getArguments().getParcelable(PhotoActivity.EXTRA_IMAGE);
                    break;
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        ButterKnife.bind(this, view);

        switch (imageType) {
            case PhotoActivity.EXTRA_IMAGE_TYPE_BITMAP:
                addBackground(backgroundBitmap, constraintLayout);
                break;
            case PhotoActivity.EXTRA_IMAGE_TYPE_URI:
                addBackground(backgroundUri, constraintLayout);
                break;
            case PhotoActivity.EXTRA_IMAGE_TYPE_URL:
                Picasso.get().load(backgroundResult.getImageUrl()).into(imageViewBackground);
                break;
        }

        return view;
    }

    private void addBackground(Bitmap bitmap, ConstraintLayout layout) {
        ImageView imageView = new ImageView(getContext());
        FrameLayout.LayoutParams params =
                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        imageView.setImageBitmap(bitmap);
        imageView.setLayoutParams(params);
        layout.addView(imageView);

    }

    private void addBackground(Uri uri, ConstraintLayout layout) {
        ImageView imageView = new ImageView(getContext());
        FrameLayout.LayoutParams params =
                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);

        Picasso.get().load(uri).into(imageView);
        imageView.setLayoutParams(params);
        layout.addView(imageView);

    }
}
