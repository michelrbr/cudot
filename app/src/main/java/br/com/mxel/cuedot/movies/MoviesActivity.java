package br.com.mxel.cuedot.movies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import br.com.mxel.cuedot.CueDotApplication;
import br.com.mxel.cuedot.R;
import br.com.mxel.cuedot.data.RepositoryDataSource;
import br.com.mxel.cuedot.data.model.Movie;
import br.com.mxel.cuedot.util.CueDotConstants;

public class MoviesActivity extends AppCompatActivity {

    @Inject
    RepositoryDataSource _repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        DaggerMoviesComponent.builder()
                .dataComponent(((CueDotApplication) getApplication()).getDataComponent())
                .build()
                .inject(this);

        _repository.getMoviesOrderBy(
                CueDotConstants.ORDER_BY_POPULAR).subscribe(
                result -> {

                    for (Movie m : result.results) {
                        System.out.println(m.title);
                    }
                },
                throwable -> {
                    System.out.println(throwable.toString());
                }
        );
    }
}
