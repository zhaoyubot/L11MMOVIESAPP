package sg.edu.rp.c346.id20011119.mymoviesapp;

import android.content.Context;
import android.media.Rating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Movie> MovieList;

    public CustomAdapter(@NonNull Context context, int resource,
                         ArrayList<Movie> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        MovieList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) parent_context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvTitle = rowView.findViewById(R.id.tvTitle);
        TextView tvGenre = rowView.findViewById(R.id.tvGenre);
        TextView tvYear = rowView.findViewById(R.id.tvYear);
        ImageView ivRating = rowView.findViewById(R.id.imageView);

        Movie currentMovie = MovieList.get(position);

        tvTitle.setText(currentMovie.getTitle());
        tvGenre.setText(currentMovie.getGenre());
        tvYear.setText(currentMovie.getYear() + "");
        String imageURL = "https://s1.zerochan.net/Rengoku.Kyoujurou.600.2843119.jpg";
        Picasso.with(parent_context).load(imageURL).into(ivRating);
        String currentMovieRating = currentMovie.getRating();
        if(currentMovieRating.equals("G")) {
            ivRating.setImageResource(R.drawable.rating_g);
        } else if(currentMovieRating.equals("PG")) {
            ivRating.setImageResource(R.drawable.rating_pg);
        } else if(currentMovieRating.equals("PG13")) {
            ivRating.setImageResource(R.drawable.rating_pg13);
        } else if(currentMovieRating.equals("NC16")) {
            ivRating.setImageResource(R.drawable.rating_nc16);
        } else if(currentMovieRating.equals("M18")) {
            ivRating.setImageResource(R.drawable.rating_m18);
        } else {
            ivRating.setImageResource(R.drawable.rating_r21);
        }
        return rowView;
    }
}