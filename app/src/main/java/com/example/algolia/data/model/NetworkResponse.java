package com.example.algolia.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NetworkResponse implements Parcelable {

    //Fields
    @SerializedName("hits")
    private List<Post> mPosts;
    @SerializedName("nbHits")
    private int mNbHits;
    @SerializedName("pages")
    private int mNbPages;

    protected NetworkResponse(Parcel in) {
        mPosts = in.createTypedArrayList(Post.CREATOR);
        mNbHits = in.readInt();
        mNbPages = in.readInt();
    }

    public List<Post> getmPosts() {
        return mPosts;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mPosts);
        dest.writeInt(mNbHits);
        dest.writeInt(mNbPages);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NetworkResponse> CREATOR = new Creator<NetworkResponse>() {
        @Override
        public NetworkResponse createFromParcel(Parcel in) {
            return new NetworkResponse(in);
        }

        @Override
        public NetworkResponse[] newArray(int size) {
            return new NetworkResponse[size];
        }
    };
}
