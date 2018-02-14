package br.com.mxel.cuedot.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

import br.com.mxel.cuedot.data.remote.model.Movie;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import timber.log.Timber;

/**
 * Created by michelribeiro on 01/08/17.
 */

public class CueDotLocalData extends OrmLiteSqliteOpenHelper implements ILocalDataSource {

    private Dao<Movie, Integer> movieDao = null;

    public CueDotLocalData(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
        try {
            movieDao = getDao(Movie.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Movie.class);

        } catch (SQLException error) {
            Timber.e(error, "Unable to create database");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Movie.class, true);
            onCreate(database, connectionSource);

        } catch (SQLException error) {
            Timber.e(error, "Unable to upgrade database from version %d to new %d", oldVersion, newVersion);
        }
    }

    @Override
    public void close() {
        super.close();
        movieDao = null;
    }

    @Override
    public Maybe<List<Movie>> getFavoriteMoviesList() {

        try {
            List<Movie> list = movieDao.queryForAll();
            return Maybe.just(list);
        } catch (SQLException e) {
            e.printStackTrace();
            return Maybe.empty();
        }
    }

    @Override
    public Single<Movie> getMovie(Long movieId) {

        try {
            Movie movie = movieDao.queryBuilder().where().eq("id", movieId).queryForFirst();
            if(movie != null) {
                return Single.just(movie);
            } else {
                return Single.just(new Movie());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Single.error(e);
        }
    }

    @Override
    public Completable insertMovieToFavorites(Movie movie) {

        try {
            movie.setIsFavorite(true);
            movieDao.create(movie);
            return Completable.complete();
        } catch (SQLException e) {
            e.printStackTrace();
            return Completable.error(e);
        }
    }

    @Override
    public Completable removeMovieFromFavorite(Long movieId) {
        try {

            Movie movie = movieDao.queryForId(movieId.intValue());
            if(movie != null) {
                movie.setIsFavorite(false);
                movieDao.delete(movie);
                return Completable.complete();
            } else {
                return Completable.error(new Exception("Movie not found"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Completable.error(e);
        }
    }
}
