package com.example.algolia.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NetworkResponse implements Parcelable {

    //Fields
<<<<<<< HEAD
    @SerializedName("posts")
    private List<Post> mPosts;
    @SerializedName("nbposts")
    private int mNbposts;
||||||| merged common ancestors
    @SerializedName("hits")
    private List<Hit> mHits;
    @SerializedName("nbHits")
    private int mNbHits;
=======
    @SerializedName("hits")
    private List<Post> mPosts;
    @SerializedName("nbHits")
    private int mNbHits;
>>>>>>> 8d532be2b5beffea8669007a98b7cc2918e6f9e0
    @SerializedName("pages")
    private int mNbPages;

    protected NetworkResponse(Parcel in) {
<<<<<<< HEAD
        mPosts = in.createTypedArrayList(Post.CREATOR);
        mNbposts = in.readInt();
||||||| merged common ancestors
        mHits = in.createTypedArrayList(Hit.CREATOR);
        mNbHits = in.readInt();
=======
        mPosts = in.createTypedArrayList(Post.CREATOR);
        mNbHits = in.readInt();
>>>>>>> 8d532be2b5beffea8669007a98b7cc2918e6f9e0
        mNbPages = in.readInt();
    }

    public List<Post> getmPosts() {
        return mPosts;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
<<<<<<< HEAD
        dest.writeTypedList(mPosts);
        dest.writeInt(mNbposts);
||||||| merged common ancestors
        dest.writeTypedList(mHits);
        dest.writeInt(mNbHits);
=======
        dest.writeTypedList(mPosts);
        dest.writeInt(mNbHits);
>>>>>>> 8d532be2b5beffea8669007a98b7cc2918e6f9e0
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
