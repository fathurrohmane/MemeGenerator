package com.elkusnandi.memegenerator.feature.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.elkusnandi.memegenerator.R;
import com.elkusnandi.memegenerator.data.model.Result;
import com.elkusnandi.memegenerator.feature.edit.EditActivity;
import com.elkusnandi.memegenerator.feature.photo.PhotoActivity;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_RESULT = "extra_result";
    public Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        result = getIntent().getParcelableExtra(EXTRA_RESULT);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(result.getDisplayName());
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DetailActivityFragment fragment =
                DetailActivityFragment.newInstance(result);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Intent intent = new Intent(this, EditActivity.class);
                intent.putExtra(PhotoActivity.EXTRA_IMAGE_TYPE, PhotoActivity.EXTRA_IMAGE_TYPE_URL);
                intent.putExtra(PhotoActivity.EXTRA_IMAGE, result);
                startActivity(intent);
                break;
        }
    }
}
