package com.github.myapplication.ui.content.movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.myapplication.R
import com.github.myapplication.ui.content.ContentAdapter
import com.github.myapplication.ui.content.ContentViewModel
import com.github.myapplication.ui.detail.detailmovie.DetailMovieActivity
import com.github.myapplication.utils.Constants.KEY_MOVIE
import com.github.myapplication.utils.gone
import com.github.myapplication.utils.visible
import kotlinx.android.synthetic.main.fragment_movie.*
import org.jetbrains.anko.startActivity

class MovieFragment : Fragment() {

    private val mViewModel by lazy {
        ViewModelProviders.of(this).get(ContentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel.getAllMovies()
        onCreateObserver()

        swipe_refresh.setOnRefreshListener {
            swipe_refresh.isRefreshing = false
            mViewModel.getAllMovies()
            goneAll()
        }
    }

    private fun onCreateObserver() {
        mViewModel.apply {
            movieList.observe(this@MovieFragment, Observer {
                constrain_data_not_found.gone()
                recycler_movie.visible()
                recycler_movie.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = ContentAdapter(it) { idMovie ->
                        context?.startActivity<DetailMovieActivity>(KEY_MOVIE to idMovie)
                    }
                }

            })

            eventGlobalMessage.observe(this@MovieFragment, Observer {
                if (it != null) {
                    recycler_movie.gone()
                    constrain_data_not_found.visible()
                    text_sad.text = it
                }
            })

            eventShowProgress.observe(this@MovieFragment, Observer {
                if (it == true) {
                    progress_bar.visible()
                    constrain_data_not_found.gone()
                } else {
                    progress_bar.gone()
                }
            })
        }
    }

    private fun goneAll() {
        recycler_movie.gone()
        constrain_data_not_found.gone()
    }
}
