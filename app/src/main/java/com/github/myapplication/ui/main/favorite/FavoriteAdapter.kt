package com.github.myapplication.ui.main.favorite

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.github.myapplication.R
import com.github.myapplication.data.model.MovieModel
import com.github.myapplication.utils.Constants
import com.github.myapplication.utils.createCircularProgressDrawable
import com.github.myapplication.utils.inflate
import kotlinx.android.synthetic.main.item_list.view.*

/**
 * Created by Muhammad Firdaus on 29/11/2019.
 */
class FavoriteAdapter(private val listener: (Int) -> Unit) :
    PagedListAdapter<MovieModel, FavoriteAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.item_list))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position), listener)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: MovieModel?, listener: (Int) -> Unit) = with(itemView) {
            if (item?.title != null) {
                text_title.text = item.title
            } else {
                text_title.text = item?.name
            }
            text_desc.text = item?.overview
            if (item?.release_date != null) {
                text_date.text = item.release_date
            } else {
                text_date.text = item?.first_air_date
            }
            Glide.with(context).load(Constants.BASE_IMAGE_URL_MOVIE_DB + item?.poster_path).apply(
                RequestOptions()
                    .placeholder(createCircularProgressDrawable(context))
                    .error(R.drawable.ic_broken_image)
            ).transition(
                DrawableTransitionOptions.withCrossFade()
            ).into(image_content)
            setOnClickListener { listener(item?.id ?: 0) }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieModel>() {
            override fun areItemsTheSame(oldFavorite: MovieModel, newFavorite: MovieModel) =
                oldFavorite.idMovie == newFavorite.idMovie

            override fun areContentsTheSame(oldFavorite: MovieModel, newFavorite: MovieModel) =
                oldFavorite == newFavorite
        }
    }
}