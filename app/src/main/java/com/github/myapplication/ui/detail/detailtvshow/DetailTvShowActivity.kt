package com.github.myapplication.ui.detail.detailtvshow

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
    private var menu: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var mDataMovie: MovieModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        setupToolbar()
        setupViewModel()
        getData()
    }

    private fun getData() {
        intent.getIntExtra(Constants.KEY_TVSHOW, 0).let {
            CoroutineScope(Dispatchers.Main).launch {
                mViewModel.getDetailTvShow(it)
            }
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
                mDataMovie = it
                isFavorite = mViewModel.checkFavorite(it?.id ?: 0)
                setFavorite()
                menu?.getItem(0)?.isVisible = true
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


            check.observe(this@DetailTvShowActivity, Observer {
                if (it == true) {
                    progress_bar_detail.showSnackbarDefault(getString(R.string.save_success))
                } else {
                    progress_bar_detail.showSnackbarDefault(getString(R.string.remove_success))
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menu = menu
        menuInflater.inflate(R.menu.menu_favorite, menu)
        menu?.getItem(0)?.isVisible = false
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.action_favorite -> {
                if (isFavorite) {
                    mViewModel.removeFavorite(mDataMovie.id ?: 0)
                } else {
                    mViewModel.addToFavorite(mDataMovie)
                }
                isFavorite = mViewModel.checkFavorite(mDataMovie.id ?: 0)
                setFavorite()
            }
        }
        return true
    }

    private fun setFavorite() {
        if (isFavorite) {
            menu?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_check)
        } else {
            menu?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_uncheck)
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
