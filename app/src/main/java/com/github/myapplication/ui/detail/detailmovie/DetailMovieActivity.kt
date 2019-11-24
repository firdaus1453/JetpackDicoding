package com.github.myapplication.ui.detail.detailmovie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.github.myapplication.R
import com.github.myapplication.data.model.MoviesModel
import com.github.myapplication.ui.detail.DetailViewModel
import com.github.myapplication.utils.Constants.Companion.KEY_MOVIE
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.item_list.*

class DetailMovieActivity : AppCompatActivity() {

    private val mViewModel by lazy {
        ViewModelProviders.of(this).get(DetailViewModel::class.java)
    }
    private val idMovie by lazy {
        intent.getIntExtra(KEY_MOVIE, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar?.title = ""
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        showData(mViewModel.getDetailMovie(idMovie))
    }

    private fun showData(itemMovie: MoviesModel?) {
        itemMovie?.let {
            text_name.text = it.title
            text_rate.text = it.rating
            text_year.text = it.date
            text_desc_detail.text = it.description
            Glide.with(this).load(it.image).placeholder(R.drawable.ic_broken_image)
                .transition(DrawableTransitionOptions.withCrossFade()).into(image_photo)
        }
    }
}
