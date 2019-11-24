package com.github.myapplication.ui.detail.detailtvshow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.github.myapplication.R
import com.github.myapplication.data.model.MoviesModel
import com.github.myapplication.ui.detail.DetailViewModel
import com.github.myapplication.utils.Constants
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.item_list.*

class DetailTvShowActivity : AppCompatActivity() {

    private val mViewModel by lazy {
        ViewModelProviders.of(this).get(DetailViewModel::class.java)
    }
    private val idTvShow by lazy {
        intent.getIntExtra(Constants.KEY_TVSHOW, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar?.title = ""
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        showData(mViewModel.getDetailTvShow(idTvShow))
    }

    private fun showData(itemTvShow: MoviesModel?) {
        itemTvShow?.let {
            text_name.text = it.title
            text_rate.text = it.rating
            text_year.text = it.date
            text_desc_detail.text = it.description
            Glide.with(this).load(it.image).placeholder(R.drawable.ic_broken_image)
                .transition(DrawableTransitionOptions.withCrossFade()).into(image_photo)
        }
    }
}
