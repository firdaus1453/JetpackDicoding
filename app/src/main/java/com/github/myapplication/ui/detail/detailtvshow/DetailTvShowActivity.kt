package com.github.myapplication.ui.detail.detailtvshow

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
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailTvShowActivity : AppCompatActivity() {

    private lateinit var mViewModel: DetailViewModel

    private val idTvShow by lazy {
        intent.getIntExtra(Constants.KEY_TVSHOW, 0)
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
            mViewModel.getDetailTvShow(idTvShow)
        }
    }

    private fun setupViewModel() {
        mViewModel = obtainVm()
        setupObserver()
    }

    private fun setupObserver() {
        mViewModel.apply {
            getTvShowData().observe(this@DetailTvShowActivity, Observer {
                visibleData(true)
                showData(it)
            })

            eventGlobalMessage.observe(this@DetailTvShowActivity, Observer {
                if (it != null) {
                    visibleData(false)
                    text_sad_detail.text = it
                }
            })

            eventShowProgress.observe(this@DetailTvShowActivity, Observer {
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

    private fun showData(itemTvShow: MovieModel?) {
        itemTvShow?.let {
            text_name.text = it.name
            text_rate.text = it.vote_average.toString()
            text_year.text = it.first_air_date
            text_desc_detail.text = it.overview
            Glide.with(this).load(Constants.BASE_IMAGE_URL_MOVIE_DB + it.poster_path)
                .apply(
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
