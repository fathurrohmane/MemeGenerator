
package com.elkusnandi.memegenerator.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import com.elkusnandi.memegenerator.data.model.Result;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemePopularRespond implements Parcelable {

    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("elapsedMS")
    @Expose
    private int elapsedMS;
    @SerializedName("warning")
    @Expose
    private String warning;
    @SerializedName("result")
    @Expose
    private List<Result> result = null;

    protected MemePopularRespond(Parcel in) {
        success = in.readByte() != 0;
        elapsedMS = in.readInt();
        warning = in.readString();
        result = in.createTypedArrayList(Result.CREATOR);
    }

    public static final Creator<MemePopularRespond> CREATOR = new Creator<MemePopularRespond>() {
        @Override
        public MemePopularRespond createFromParcel(Parcel in) {
            return new MemePopularRespond(in);
        }

        @Override
        public MemePopularRespond[] newArray(int size) {
            return new MemePopularRespond[size];
        }
    };

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getElapsedMS() {
        return elapsedMS;
    }

    public void setElapsedMS(int elapsedMS) {
        this.elapsedMS = elapsedMS;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (success ? 1 : 0));
        dest.writeInt(elapsedMS);
        dest.writeString(warning);
        dest.writeTypedList(result);
    }
}
