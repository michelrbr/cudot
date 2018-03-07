package br.com.mxel.cuedot.movies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.mxel.cuedot.R;
import br.com.mxel.cuedot.data.model.Movie;

/**
 * Created by michelribeiro on 20/01/2018.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder {

    private Movie _movie;

    public MovieViewHolder(View itemView) {
        super(itemView);
    }

    public Movie getMovie() {
        return _movie;
    }

    public void setMovie(Movie movie) {

        _movie = movie;
        ((TextView) itemView.findViewById(R.id.movie_title)).setText(movie.getTitle());
        ((TextView) itemView.findViewById(R.id.release_date_text_view)).setText(movie.getReleaseDate());
        ((RatingBar) itemView.findViewById(R.id.rating)).setRating(movie.getRating() / 2);
        Picasso.with(itemView.getContext())
                .load(movie.getPosterPath())
                //.centerCrop()
                .into((ImageView) itemView.findViewById(R.id.movie_cover));
    }
}
