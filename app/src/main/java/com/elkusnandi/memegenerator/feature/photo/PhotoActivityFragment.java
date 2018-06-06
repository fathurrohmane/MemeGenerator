package com.elkusnandi.memegenerator.feature.photo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.elkusnandi.memegenerator.R;
import com.elkusnandi.memegenerator.feature.edit.EditActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.elkusnandi.memegenerator.feature.photo.PhotoActivity.EXTRA_IMAGE;
import static com.elkusnandi.memegenerator.feature.photo.PhotoActivity.EXTRA_IMAGE_TYPE;

/**
 * A placeholder fragment containing a simple view.
 */
public class PhotoActivityFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_SAVED_INSTANCE = "my_intent";

    public static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final int REQUEST_CAMERA_PERMISSION = 2;
    public static final int REQUEST_BROWSE_IMAGE = 3;

    @BindView(R.id.image_view_thumbnail)
    ImageView imageViewThumbnail;
    @BindView(R.id.button_camera)
    Button buttonCamera;
    @BindView(R.id.button_edit)
    Button buttonEdit;

    private Intent myIntent;

    public PhotoActivityFragment() {
    }

    public static PhotoActivityFragment newInstance() {
        PhotoActivityFragment fragment = new PhotoActivityFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, container, false);
        ButterKnife.bind(this, view);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(ARG_SAVED_INSTANCE)) {
                myIntent = savedInstanceState.getParcelable(ARG_SAVED_INSTANCE);
                Picasso.get().load(myIntent.getData()).into(imageViewThumbnail);
                buttonEdit.setEnabled(true);
            }
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (myIntent != null) {
            outState.putParcelable(ARG_SAVED_INSTANCE, myIntent);
        }

        super.onSaveInstanceState(outState);
    }

    @OnClick({R.id.button_camera, R.id.button_browse, R.id.button_edit})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_camera:
                takePictureIntent();
                break;
            case R.id.button_browse:
                browserImageIntent();
                break;
            case R.id.button_edit:
                startActivity(myIntent);
                break;
        }
    }

    private void takePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (getActivity() != null && getContext() != null) {
            if (ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                        Manifest.permission.CAMERA)) {
                    // show permission rationale
                } else {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CAMERA},
                            REQUEST_CAMERA_PERMISSION);
                }
            } else {
                if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        }
    }

    private void browserImageIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_BROWSE_IMAGE);
    }

    private Intent createIntent(Bitmap bitmap) {
        Intent intent = new Intent(getContext(), EditActivity.class);
        intent.putExtra(EXTRA_IMAGE_TYPE, PhotoActivity.EXTRA_IMAGE_TYPE_BITMAP);
        intent.putExtra(EXTRA_IMAGE, bitmap);
        return intent;
    }

    private Intent createIntent(Uri uri) {
        Intent intent = new Intent(getContext(), EditActivity.class);
        intent.putExtra(EXTRA_IMAGE_TYPE, PhotoActivity.EXTRA_IMAGE_TYPE_URI);
        intent.putExtra(EXTRA_IMAGE, uri);
        return intent;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CAMERA_PERMISSION:
                    takePictureIntent();
                    break;
                case REQUEST_IMAGE_CAPTURE:
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = null;
                    if (bundle != null) {
                        bitmap = (Bitmap) bundle.get("data");
                    }
                    imageViewThumbnail.setImageBitmap(bitmap);
                    myIntent = createIntent(bitmap);
                    if (myIntent != null) {
                        buttonEdit.setEnabled(true);
                    }
                    break;
                case REQUEST_BROWSE_IMAGE:
                    Picasso.get().load(data.getData()).into(imageViewThumbnail);
                    myIntent = createIntent(data.getData());
                    if (myIntent != null) {
                        buttonEdit.setEnabled(true);
                    }
                    break;
            }
        } else {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePictureIntent();
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
