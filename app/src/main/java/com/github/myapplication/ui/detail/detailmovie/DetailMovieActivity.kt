package com.github.myapplication.ui.detail.detailmovie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.github.myapplication.R
import com.github.myapplication.data.model.MovieModel
import com.github.myapplication.ui.detail.DetailViewModel
import com.github.myapplication.utils.*
import com.github.myapplication.utils.Constants.KEY_MOVIE
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var mViewModel: DetailViewModel

    private val idMovie by lazy {
        intent.getIntExtra(KEY_MOVIE, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        setupToolbar()
        setupViewModel()
        getData()
    }

    private fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            mViewModel.getDetailMovie(idMovie)
        }
    }

    private fun setupViewModel() {
        mViewModel = obtainVm()
        setupObserver()
    }

    private fun setupObserver() {
        mViewModel.apply {
            getMovieData().observe(this@DetailMovieActivity, Observer {
                visibleData(true)
                showData(it)
            })

            eventGlobalMessage.observe(this@DetailMovieActivity, Observer {
                if (it != null) {
                    visibleData(false)
                    text_sad_detail.text = it
                }
            })

            eventShowProgress.observe(this@DetailMovieActivity, Observer {
                if (it == true) {
                    progress_bar_detail.visible()
                    constrain_data_not_found_detail.gone()
                } else {
                    progress_bar_detail.gone()
                }
            })
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar?.title = ""
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun showData(itemMovie: MovieModel?) {
        itemMovie?.let {
            text_name.text = it.title
            text_rate.text = it.vote_average.toString()
            text_year.text = it.release_date
            text_desc_detail.text = it.overview
            Glide.with(this).load(Constants.BASE_IMAGE_URL_MOVIE_DB + it.poster_path).apply(
                RequestOptions()
                    .placeholder(createCircularProgressDrawable(this))
                    .error(R.drawable.ic_broken_image)
            )
                .transition(DrawableTransitionOptions.withCrossFade()).into(image_photo)
        }
    }

    private fun obtainVm(): DetailViewModel = obtainViewModel(DetailViewModel::class.java)

    private fun visibleData(isVisible: Boolean) {
        if (isVisible) {
            constrain_data_not_found_detail.gone()
            constrain_content_detail.visible()
        } else {
            constrain_content_detail.gone()
            constrain_data_not_found_detail.visible()
        }
    }
}
