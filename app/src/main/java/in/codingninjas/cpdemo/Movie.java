package in.codingninjas.cpdemo;

import android.content.ContentValues;

/**
 * Created by ralph on 25/07/17.
 */

public class Movie {

    private String title;
    private String description;

    public Movie(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        cv.put(MoviesContract.Movie.TITLE,title);
        cv.put(MoviesContract.Movie.DESCRIPTION,description);
        return cv;
    }
}
