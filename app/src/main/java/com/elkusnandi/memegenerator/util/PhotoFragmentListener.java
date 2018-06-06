package com.elkusnandi.memegenerator.util;

import android.os.Parcelable;

public interface PhotoFragmentListener {

    void onImageLoaded(Parcelable image, int imageType);
}
