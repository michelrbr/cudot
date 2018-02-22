package br.com.mxel.cuedot.movies.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.mxel.cuedot.R;
import br.com.mxel.cuedot.data.model.Movie;
import br.com.mxel.cuedot.movieDetail.MovieDetailActivity;
import timber.log.Timber;

/**
 * Created by michelribeiro on 20/01/2018.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<Movie> _data;

    public MoviesAdapter() {
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_movie, parent, false);
        MovieViewHolder holder = new MovieViewHolder(view);
        holder.itemView.setOnClickListener(v -> {

            Movie movie = _data.get((Integer) v.getTag());

            Timber.tag("MoviesAdapter").d("Movie id: %d", movie.getId());

            Intent intent = new Intent(v.getContext(), MovieDetailActivity.class);
            intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, (Movie)movie);
            v.getContext().startActivity(intent);
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

        Movie movie = _data.get(position);
        holder.itemView.setTag(position);
        ((TextView) holder.itemView.findViewById(R.id.movie_title)).setText(movie.getTitle());
        ((TextView) holder.itemView.findViewById(R.id.release_date_text_view)).setText(movie.getReleaseDate());
        ((RatingBar) holder.itemView.findViewById(R.id.rating)).setRating(movie.getRating() / 2);
        Picasso.with(holder.itemView.getContext())
                .load(movie.getPosterPath())
                //.centerCrop()
                .into((ImageView) holder.itemView.findViewById(R.id.movie_cover));
    }

    @Override
    public int getItemCount() {
        return (_data != null) ? _data.size() : 0;
    }

    public void setData(List<Movie> data) {
        _data = data;
        notifyDataSetChanged();
    }
}
