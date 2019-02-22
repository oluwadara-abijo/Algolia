package com.example.algolia.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NetworkResponse implements Parcelable {

    //Fields
    @SerializedName("hits")
    private List<Post> mPosts;
    @SerializedName("pages")
    private int mPage;
    @SerializedName("nbPages")
    private int mNbPages;
    @SerializedName("hitsPerPage")
    private int mHitsPerPage;

    public List<Post> getPosts() {
        return mPosts;
    }

    protected NetworkResponse(Parcel in) {
        mPosts = in.createTypedArrayList(Post.CREATOR);
        mPage = in.readInt();
        mNbPages = in.readInt();
        mHitsPerPage = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mPosts);
        dest.writeInt(mPage);
        dest.writeInt(mNbPages);
        dest.writeInt(mHitsPerPage);
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
