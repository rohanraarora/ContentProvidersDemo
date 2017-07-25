package in.codingninjas.cpdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ralph on 25/07/17.
 *
 */

public class MoviesOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "movies_db";
    private static final int DB_VERSION = 1;
    private static MoviesOpenHelper INSTANCE;

    public static MoviesOpenHelper getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new MoviesOpenHelper(context);
        }
        return INSTANCE;
    }

    private MoviesOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String moviesSQL = "CREATE TABLE " + MoviesContract.Movie.TABLE_NAME + " ( "
                + MoviesContract.Movie._ID + " INTEGER PRIMARY KEY, "
                + MoviesContract.Movie.TITLE + " TEXT, "
                + MoviesContract.Movie.DESCRIPTION + " TEXT)";

        String reviewsSQL = "CREATE TABLE " + MoviesContract.Review.TABLE_NAME + " ( "
                + MoviesContract.Review._ID + " INTEGER PRIMARY KEY, "
                + MoviesContract.Review.AUTHOR_NAME + " TEXT, "
                + MoviesContract.Review.REVIEW + " TEXT)";
        sqLiteDatabase.execSQL(moviesSQL);
        sqLiteDatabase.execSQL(reviewsSQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
