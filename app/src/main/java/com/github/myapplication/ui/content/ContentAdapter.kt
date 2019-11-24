package com.github.myapplication.ui.content

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.github.myapplication.R
import com.github.myapplication.data.model.MoviesModel
import com.github.myapplication.utils.inflate
import kotlinx.android.synthetic.main.item_list.view.*

/**
 * Created by Muhammad Firdaus on 24/11/2019.
 */
class ContentAdapter(private val contentList: List<MoviesModel>, private val listener: (Int) -> Unit) :
    RecyclerView.Adapter<ContentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_list))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(contentList[position], listener)

    override fun getItemCount() = contentList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: MoviesModel, listener: (Int) -> Unit) = with(itemView) {
            text_title.text = item.title
            text_desc.text = item.description
            text_date.text = item.date
            Glide.with(context).load(item.image).placeholder(R.drawable.ic_broken_image).transition(
                DrawableTransitionOptions.withCrossFade()
            ).into(image_content)
            setOnClickListener { listener(item.id?:0) }
        }
    }
}