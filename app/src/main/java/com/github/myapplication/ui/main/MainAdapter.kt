package com.github.myapplication.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.github.myapplication.R
import com.github.myapplication.data.model.MovieModel
import com.github.myapplication.utils.Constants.BASE_IMAGE_URL_MOVIE_DB
import com.github.myapplication.utils.createCircularProgressDrawable
import com.github.myapplication.utils.inflate
import kotlinx.android.synthetic.main.item_list.view.*

/**
 * Created by Muhammad Firdaus on 24/11/2019.
 */
class MainAdapter(private val listener: (Int) -> Unit) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val contentList = ArrayList<MovieModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.item_list))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(contentList[position], listener)

    override fun getItemCount() = contentList.size

    fun setContentList(data: List<MovieModel>?) {
        data?.let {
            this.contentList.apply {
                clear()
                addAll(it)
            }
            notifyDataSetChanged()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: MovieModel, listener: (Int) -> Unit) = with(itemView) {
            if (item.title != null) {
                text_title.text = item.title
            } else {
                text_title.text = item.name
            }
            text_desc.text = item.overview
            if (item.release_date != null) {
                text_date.text = item.release_date
            } else {
                text_date.text = item.first_air_date
            }
            Glide.with(context).load(BASE_IMAGE_URL_MOVIE_DB + item.poster_path).apply(
                RequestOptions()
                    .placeholder(createCircularProgressDrawable(context))
                    .error(R.drawable.ic_broken_image)
            ).transition(
                DrawableTransitionOptions.withCrossFade()
            ).into(image_content)
            setOnClickListener { listener(item.id ?: 0) }
        }
    }
}