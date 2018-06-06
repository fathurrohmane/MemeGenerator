package com.elkusnandi.memegenerator.feature.photo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.elkusnandi.memegenerator.R;
import com.elkusnandi.memegenerator.feature.edit.EditActivity;
import com.elkusnandi.memegenerator.feature.edit.EditActivityFragment;
import com.elkusnandi.memegenerator.util.PhotoFragmentListener;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhotoActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_IMAGE_TYPE = "image_type";
    public static final int EXTRA_IMAGE_TYPE_BITMAP = 0;
    public static final int EXTRA_IMAGE_TYPE_URI = 1;
    public static final int EXTRA_IMAGE_TYPE_URL = 2;
    public static final String EXTRA_IMAGE = "extra_image";

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    private String url;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        PhotoActivityFragment fragment = PhotoActivityFragment.newInstance();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    @OnClick({R.id.fab})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                startActivity(intent);
                break;
        }
    }

    private void createIntent(Intent intent, Bitmap bitmap) {
        intent = new Intent(this, EditActivity.class);
        intent.putExtra(EXTRA_IMAGE_TYPE, EXTRA_IMAGE_TYPE_BITMAP);
        intent.putExtra(EXTRA_IMAGE, bitmap);
    }

    private void createIntent(Intent intent, Uri uri) {
        intent = new Intent(this, EditActivity.class);
        intent.putExtra(EXTRA_IMAGE_TYPE, EXTRA_IMAGE_TYPE_URI);
        intent.putExtra(EXTRA_IMAGE, uri);
    }
}
