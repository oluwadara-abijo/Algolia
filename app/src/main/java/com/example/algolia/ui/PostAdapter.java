package com.example.algolia.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.algolia.R;
import com.example.algolia.data.model.Post;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostAdapterViewHolder> {

    //List object that holds Posts
    private List<Post> mPosts;

    //Class constructor which creates a PostAdapter
    PostAdapter(List<Post> Posts) {
        mPosts = Posts;
    }

    @NonNull
    @Override
    public PostAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layout = R.layout.list_item_post;
        View view = LayoutInflater.from(context).inflate(layout, viewGroup, false);
        return new PostAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapterViewHolder PostAdapterViewHolder, int i) {
        Post currentPost = mPosts.get(i);
        PostAdapterViewHolder.postTitle.setText(currentPost.getTitle());
        PostAdapterViewHolder.postDate.setText(currentPost.getCreatedAt());
    }

    /**
     * When data changes, this method updates the list of Posts
     * and notifies the adapter to use the new values on it
     */
    void setPosts(List<Post> Posts) {
        mPosts = Posts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (null == mPosts) return 0;
        return mPosts.size();
    }

    //ViewHolder class
    public class PostAdapterViewHolder extends RecyclerView.ViewHolder {
        //UI elements
        @BindView(R.id.post_title) TextView postTitle;
        @BindView(R.id.post_date) TextView postDate;


        //ViewHolder class constructor
        PostAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

    }
}
