
package com.elkusnandi.memegenerator.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntityVotesSummary implements Parcelable{

    @SerializedName("entityName")
    @Expose
    private String entityName;
    @SerializedName("entityID")
    @Expose
    private int entityID;
    @SerializedName("totalVotesSum")
    @Expose
    private int totalVotesSum;
    @SerializedName("userID")
    @Expose
    private int userID;
    @SerializedName("userVoteScore")
    @Expose
    private int userVoteScore;

    protected EntityVotesSummary(Parcel in) {
        entityName = in.readString();
        entityID = in.readInt();
        totalVotesSum = in.readInt();
        userID = in.readInt();
        userVoteScore = in.readInt();
    }

    public static final Creator<EntityVotesSummary> CREATOR = new Creator<EntityVotesSummary>() {
        @Override
        public EntityVotesSummary createFromParcel(Parcel in) {
            return new EntityVotesSummary(in);
        }

        @Override
        public EntityVotesSummary[] newArray(int size) {
            return new EntityVotesSummary[size];
        }
    };

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public int getEntityID() {
        return entityID;
    }

    public void setEntityID(int entityID) {
        this.entityID = entityID;
    }

    public int getTotalVotesSum() {
        return totalVotesSum;
    }

    public void setTotalVotesSum(int totalVotesSum) {
        this.totalVotesSum = totalVotesSum;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserVoteScore() {
        return userVoteScore;
    }

    public void setUserVoteScore(int userVoteScore) {
        this.userVoteScore = userVoteScore;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(entityName);
        dest.writeInt(entityID);
        dest.writeInt(totalVotesSum);
        dest.writeInt(userID);
        dest.writeInt(userVoteScore);
    }
}
