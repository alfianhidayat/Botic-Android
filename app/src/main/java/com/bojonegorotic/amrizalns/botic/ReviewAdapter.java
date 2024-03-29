package com.bojonegorotic.amrizalns.botic;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.botic.coreapps.models.Review;

import java.util.List;

/**
 * Created by user on 12/07/2017.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private List<Review> mReviewsList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nama, review, response;
        public RatingBar rating;

        public ViewHolder(View view) {
            super(view);
            nama = (TextView) view.findViewById(R.id.txt_nama_review);
            review = (TextView) view.findViewById(R.id.txt_review_review);
            rating = (RatingBar) view.findViewById(R.id.rating_review);
            response = (TextView) view.findViewById(R.id.txt_response);
        }
    }

    public ReviewAdapter(List<Review> reviewsList) {
        this.mReviewsList = reviewsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_list_row, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Review review = mReviewsList.get(position);
        holder.nama.setText(review.getUser().getName());
        holder.review.setText(review.getReview());
        holder.rating.setRating(review.getRating());

        if (review.getResponse().toString().trim().equals(""))
            holder.response.setText(R.string.respontidak);
        else {
            holder.response.setText(review.getResponse());
        }
    }

    @Override
    public int getItemCount() {
        return mReviewsList.size();
    }
}
