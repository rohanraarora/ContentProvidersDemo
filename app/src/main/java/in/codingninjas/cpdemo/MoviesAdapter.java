package in.codingninjas.cpdemo;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ralph on 25/07/17.
 */

public class MoviesAdapter extends CursorAdapter {


    MovieChangeListener movieChangeListener;

    public interface MovieChangeListener {

        void onChange();
    }


    public MoviesAdapter(Context context, Cursor c,MovieChangeListener movieChangeListener) {
        super(context, c, FLAG_REGISTER_CONTENT_OBSERVER);
        this.movieChangeListener = movieChangeListener;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.movie_item,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textView = view.findViewById(R.id.textView);
        TextView desTextView = view.findViewById(R.id.textView2);

        String title = cursor.getString(cursor.getColumnIndex(MoviesContract.Movie.TITLE));
        String desc = cursor.getString(cursor.getColumnIndex(MoviesContract.Movie.DESCRIPTION));

        textView.setText(title);
        desTextView.setText(desc);
    }

    @Override
    protected void onContentChanged() {
        super.onContentChanged();
        if(movieChangeListener != null){
            movieChangeListener.onChange();
        }
        Log.d("TAG","change");

    }
}
