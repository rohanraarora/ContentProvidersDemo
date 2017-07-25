package in.codingninjas.cpdemo;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by ralph on 25/07/17.
 */

public class MoviesContract {

    public static final String AUTHORITY = "in.codingninjas.cpdemo";
    public static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_MOVIES = "movies";
    public static final String PATH_REVIEW = "reviews";


    public static class Movie implements BaseColumns{

        public static final String TABLE_NAME = "movies";

        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";


        public static final Uri CONTENT_URI = BASE_URI
                .buildUpon().appendPath(PATH_MOVIES).build();

        public static Uri buildMovieUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
                + "/" + AUTHORITY
                + "/" + PATH_MOVIES;

        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
                + "/" + AUTHORITY
                + "/" + PATH_MOVIES;

    }

    public static class Review implements BaseColumns{

        public static final String TABLE_NAME = "reviews";

        public static final String AUTHOR_NAME = "author_name";
        public static final String REVIEW = "review";

        public static final Uri CONTENT_URI = BASE_URI
                .buildUpon().appendPath(PATH_REVIEW).build();

        public static Uri buildReviewUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
                + "/" + AUTHORITY
                + "/" + PATH_REVIEW;

    }
}
