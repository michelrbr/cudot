package br.com.mxel.cuedot.movies.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.mxel.cuedot.data.model.IMovie;
import br.com.mxel.cuedot.data.remote.model.Movie;
import br.com.mxel.cuedot.movieDetail.MovieDetailActivity;
import timber.log.Timber;

/**
 * Created by michelribeiro on 20/01/2018.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<IMovie> _data;

    public MoviesAdapter() {
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.activity_list_item, parent, false);
        MovieViewHolder holder = new MovieViewHolder(view);
        holder.itemView.setOnClickListener(v -> {

            IMovie movie = _data.get((Integer) v.getTag());

            Timber.tag("MoviesAdapter").d("Movie id: %d", movie.getId());

            Intent intent = new Intent(v.getContext(), MovieDetailActivity.class);
            intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, (Movie)movie);
            v.getContext().startActivity(intent);
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

        IMovie movie = _data.get(position);
        holder.itemView.setTag(position);
        ((TextView) holder.itemView.findViewById(android.R.id.text1)).setText(movie.getTitle());
        Picasso.with(holder.itemView.getContext())
                .load(movie.getPosterPath())
                //.centerCrop()
                .into((ImageView) holder.itemView.findViewById(android.R.id.icon));
    }

    @Override
    public int getItemCount() {
        return (_data != null) ? _data.size() : 0;
    }

    public void setData(List<IMovie> data) {
        _data = data;
        notifyDataSetChanged();
    }
}
