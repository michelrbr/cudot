package br.com.mxel.cuedot.movies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.mxel.cuedot.R;
import br.com.mxel.cuedot.data.model.Movie;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by michelribeiro on 20/01/2018.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<Movie> _data;
    private PublishSubject<Movie> _notifier = PublishSubject.create();

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_movie, parent, false);
        MovieViewHolder holder = new MovieViewHolder(view);

        holder.itemView.setOnClickListener(v -> {

            _notifier.onNext(holder.getMovie());
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

        Movie movie = _data.get(position);
        holder.setMovie(movie);

    }

    @Override
    public int getItemCount() {
        return (_data == null) ? 0 : _data.size();
    }

    public void setData(List<Movie> data) {
        _data = data;
        notifyDataSetChanged();
    }

    public Observable<Movie> asObservable() {
        return _notifier;
    }
}
