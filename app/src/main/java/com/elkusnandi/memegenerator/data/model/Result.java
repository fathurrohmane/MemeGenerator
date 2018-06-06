
package com.elkusnandi.memegenerator.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Parcelable {

    @SerializedName("generatorID")
    @Expose
    private int generatorID;
    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("urlName")
    @Expose
    private String urlName;
    @SerializedName("totalVotesScore")
    @Expose
    private int totalVotesScore;
    @SerializedName("imageID")
    @Expose
    private int imageID;
    @SerializedName("instanceID")
    @Expose
    private int instanceID;
    @SerializedName("text0")
    @Expose
    private String text0;
    @SerializedName("text1")
    @Expose
    private String text1;
    @SerializedName("commentsCount")
    @Expose
    private int commentsCount;
    @SerializedName("mgUserID")
    @Expose
    private int mgUserID;
    @SerializedName("username")
    @Expose
    private Object username;
    @SerializedName("entityVotesSummary")
    @Expose
    private EntityVotesSummary entityVotesSummary;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("instanceImageUrl")
    @Expose
    private String instanceImageUrl;
    @SerializedName("instanceUrl")
    @Expose
    private String instanceUrl;

    protected Result(Parcel in) {
        generatorID = in.readInt();
        displayName = in.readString();
        urlName = in.readString();
        totalVotesScore = in.readInt();
        imageID = in.readInt();
        instanceID = in.readInt();
        text0 = in.readString();
        text1 = in.readString();
        commentsCount = in.readInt();
        mgUserID = in.readInt();
        entityVotesSummary = in.readParcelable(EntityVotesSummary.class.getClassLoader());
        imageUrl = in.readString();
        instanceImageUrl = in.readString();
        instanceUrl = in.readString();
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    public int getGeneratorID() {
        return generatorID;
    }

    public void setGeneratorID(int generatorID) {
        this.generatorID = generatorID;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public int getTotalVotesScore() {
        return totalVotesScore;
    }

    public void setTotalVotesScore(int totalVotesScore) {
        this.totalVotesScore = totalVotesScore;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public int getInstanceID() {
        return instanceID;
    }

    public void setInstanceID(int instanceID) {
        this.instanceID = instanceID;
    }

    public String getText0() {
        return text0;
    }

    public void setText0(String text0) {
        this.text0 = text0;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public int getMgUserID() {
        return mgUserID;
    }

    public void setMgUserID(int mgUserID) {
        this.mgUserID = mgUserID;
    }

    public Object getUsername() {
        return username;
    }

    public void setUsername(Object username) {
        this.username = username;
    }

    public EntityVotesSummary getEntityVotesSummary() {
        return entityVotesSummary;
    }

    public void setEntityVotesSummary(EntityVotesSummary entityVotesSummary) {
        this.entityVotesSummary = entityVotesSummary;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getInstanceImageUrl() {
        return instanceImageUrl;
    }

    public void setInstanceImageUrl(String instanceImageUrl) {
        this.instanceImageUrl = instanceImageUrl;
    }

    public String getInstanceUrl() {
        return instanceUrl;
    }

    public void setInstanceUrl(String instanceUrl) {
        this.instanceUrl = instanceUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(generatorID);
        dest.writeString(displayName);
        dest.writeString(urlName);
        dest.writeInt(totalVotesScore);
        dest.writeInt(imageID);
        dest.writeInt(instanceID);
        dest.writeString(text0);
        dest.writeString(text1);
        dest.writeInt(commentsCount);
        dest.writeInt(mgUserID);
        dest.writeParcelable(entityVotesSummary, flags);
        dest.writeString(imageUrl);
        dest.writeString(instanceImageUrl);
        dest.writeString(instanceUrl);
    }
}
