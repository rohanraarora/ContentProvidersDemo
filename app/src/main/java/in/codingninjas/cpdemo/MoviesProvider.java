package in.codingninjas.cpdemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class MoviesProvider extends ContentProvider {

    public static final UriMatcher URI_MATCHER = buildUriMatcher();

    public static MoviesOpenHelper openHelper;

    public static final int MOVIES = 100;
    public static final int MOVIE_ITEM = 101;
    public static final int REVIEWS = 200;

    static UriMatcher buildUriMatcher(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(MoviesContract.AUTHORITY,MoviesContract.PATH_MOVIES,MOVIES);
        uriMatcher.addURI(MoviesContract.AUTHORITY,MoviesContract.PATH_MOVIES + "/#",MOVIE_ITEM);
        uriMatcher.addURI(MoviesContract.AUTHORITY,MoviesContract.PATH_REVIEW,REVIEWS);


        return uriMatcher;
    }

    public MoviesProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        int match = URI_MATCHER.match(uri);
        switch (match){
            case MOVIES:
                return openHelper.getWritableDatabase().delete(MoviesContract.Movie.TABLE_NAME,selection,selectionArgs);
            case REVIEWS:
                return openHelper.getWritableDatabase().delete(MoviesContract.Review.TABLE_NAME,selection,selectionArgs);
        }
        return  -1;
    }

    @Override
    public String getType(Uri uri) {
        int match = URI_MATCHER.match(uri);
        switch (match){
            case MOVIES:
                return MoviesContract.Movie.CONTENT_TYPE;
            case MOVIE_ITEM:
                return MoviesContract.Movie.CONTENT_ITEM_TYPE;
            case REVIEWS:
                return MoviesContract.Review.CONTENT_TYPE;
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        Uri returnUri = null;
        int match = URI_MATCHER.match(uri);
        switch (match){
            case MOVIES:
                long id = openHelper.getWritableDatabase().insert(MoviesContract.Movie.TABLE_NAME,null, values);
                returnUri =   MoviesContract.Movie.buildMovieUri(id);
                break;
            case REVIEWS:
                long rid = openHelper.getWritableDatabase().insert(MoviesContract.Review.TABLE_NAME,null, values);
                returnUri =  MoviesContract.Movie.buildMovieUri(rid);
            break;
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return returnUri;
    }

    @Override
    public boolean onCreate() {
        openHelper = MoviesOpenHelper.getInstance(getContext().getApplicationContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        Cursor returnCursor = null;
        int match = URI_MATCHER.match(uri);
        switch (match){
            case MOVIES:
                returnCursor = openHelper.getReadableDatabase()
                        .query(MoviesContract.Movie.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case MOVIE_ITEM:
                String selectionString = MoviesContract.Movie._ID + " = ?";
                String id = uri.getLastPathSegment();
                String[] selArgs = new String[]{id};
                returnCursor = openHelper.getReadableDatabase()
                        .query(MoviesContract.Movie.TABLE_NAME,projection,selectionString,selArgs,null,null,sortOrder);
                break;
            case REVIEWS:
                returnCursor = openHelper.getReadableDatabase()
                        .query(MoviesContract.Review.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
            default: return null;
        }
        returnCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return returnCursor;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int match = URI_MATCHER.match(uri);
        switch (match){
            case MOVIES:
                return openHelper.getWritableDatabase().update(MoviesContract.Movie.TABLE_NAME,values,selection,selectionArgs);
            case REVIEWS:
                return openHelper.getWritableDatabase().update(MoviesContract.Review.TABLE_NAME,values,selection,selectionArgs);
        }
        return  -1;
    }
}
