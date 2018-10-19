package br.com.mxel.cuedot.movies.adapter;

import android.support.annotation.NonNull;
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

public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int MOVIE_VIEW = 0;
    private final int LOADING_VIEW = 1;

    private List<Movie> _data;
    private final PublishSubject<Movie> _notifier = PublishSubject.create();

    public boolean canLoadMore;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == MOVIE_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_movie, parent, false);

            final MovieViewHolder holder = new MovieViewHolder(view);
            holder.itemView.setOnClickListener(v -> {

                Movie movie = holder.getMovie();
                if(movie != null) {
                    _notifier.onNext(holder.getMovie());
                }
            });

            return holder;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if( holder instanceof MovieViewHolder ) {
            Movie movie = _data.get(position);
            ((MovieViewHolder) holder).setMovie(movie);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (canLoadMore && _data != null && position > _data.size() - 1) ? LOADING_VIEW : MOVIE_VIEW;
    }

    @Override
    public int getItemCount() {

        int count = 0;

        if(_data != null) {
            count = (canLoadMore) ? _data.size() + 1 : _data.size();
        }

        return count;
    }

    public void setData(List<Movie> data) {
        _data = data;
        notifyDataSetChanged();
    }

    public Observable<Movie> asObservable() {
        return _notifier;
    }
}
