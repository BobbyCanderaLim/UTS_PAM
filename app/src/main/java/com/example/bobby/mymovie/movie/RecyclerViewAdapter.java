package com.example.bobby.mymovie.movie;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bobby.mymovie.R;
import com.example.bobby.mymovie.model.GenreMovieModel;
import com.example.bobby.mymovie.model.PopularMovieModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{


    private static final String TAG = RecyclerViewAdapter.class.getSimpleName();
    //construct
    private List<PopularMovieModel> movieModels;
    private List<GenreMovieModel> genreMovieModels;
    PopularMovieModel popularMovieModel;


    //list save match genreName
    private List<String> genreName = new ArrayList<>();



    public RecyclerViewAdapter(List<PopularMovieModel> movieModels,List<GenreMovieModel> genreMovieModels) {

        this.movieModels = movieModels;
        this.genreMovieModels=genreMovieModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        popularMovieModel = movieModels.get(position);


        String genre = null;

        final String title = popularMovieModel.getOriginalTitle();
        final String overview = popularMovieModel.getOverview();
        final String urlImage = popularMovieModel.getPosterPath();
        final String urlBackdrop = popularMovieModel.getBackdropPath();
        final String rating = popularMovieModel.getVoteAverage();
        final Integer id = popularMovieModel.getId();


        holder.txtReleaseDate.setText(popularMovieModel.getReleaseDate());

        holder.txtTitle.setText(title);
        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w185"+popularMovieModel.getPosterPath())
                .into(holder.imgPoster);

        Glide.with(holder.itemView.getContext()).
                load("https://image.tmdb.org/t/p/w500"+urlBackdrop);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext() , DetailMovieActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("id",id);
                intent.putExtra("overview",overview);
                intent.putExtra("urlimage",urlImage);
                intent.putExtra("urlBackdrop",urlBackdrop);
                intent.putExtra("rating",rating);

                holder.itemView.getContext().startActivity(intent);

            }




        });
    }



    @Override
    public int getItemCount() {
        return movieModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        ImageView imgPoster;
        TextView txtTitle, txtReleaseDate;

        public ViewHolder(View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.image_view_movie);
            txtTitle = itemView.findViewById(R.id.txt_view_title);
            txtReleaseDate = itemView.findViewById(R.id.txt_view_release_date);

        }
    }

    public String searchGenre(int position){

        for(Integer i=0;i<movieModels.get(position).getGenreIds().size();i++){

            for (Integer j=0;j<genreMovieModels.size();j++){

                if(movieModels.get(position).getGenreIds().get(i).equals(String.valueOf(genreMovieModels.get(j).getId())));
                {
                    return (genreMovieModels.get(j).getName());

                }
            }
        }
        return null;

    }

}
