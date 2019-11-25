package com.github.myapplication.ui.content.tvshow


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.myapplication.R
import com.github.myapplication.ui.content.ContentAdapter
import com.github.myapplication.ui.content.ContentViewModel
import com.github.myapplication.ui.detail.detailmovie.DetailMovieActivity
import com.github.myapplication.utils.Constants
import com.github.myapplication.utils.gone
import com.github.myapplication.utils.obtainViewModel
import com.github.myapplication.utils.visible
import kotlinx.android.synthetic.main.fragment_movie.*
import org.jetbrains.anko.startActivity

class TvShowFragment : Fragment() {

    private lateinit var mViewModel: ContentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null){
            mViewModel = obtainVm()
        }
        onCreateObserver()
        mViewModel.getAllTvShows()

        swipe_refresh.setOnRefreshListener {
            swipe_refresh.isRefreshing = false
            mViewModel.getAllTvShows()
            goneAll()
        }
    }

    private fun onCreateObserver() {
        mViewModel.apply {
            getTvShowList().observe(this@TvShowFragment, Observer {
                constrain_data_not_found.gone()
                recycler_movie.visible()
                recycler_movie.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = ContentAdapter(it) { idMovie ->
                        context?.startActivity<DetailMovieActivity>(Constants.KEY_MOVIE to idMovie)
                    }
                }
            })

            eventGlobalMessage.observe(this@TvShowFragment, Observer {
                if (it != null) {
                    recycler_movie.gone()
                    constrain_data_not_found.visible()
                    text_sad.text = it
                }
            })

            eventShowProgress.observe(this@TvShowFragment, Observer {
                if (it == true) {
                    progress_bar.visible()
                    constrain_data_not_found.gone()
                } else {
                    progress_bar.gone()
                }
            })
        }
    }

    private fun obtainVm(): ContentViewModel = obtainViewModel(ContentViewModel::class.java)

    private fun goneAll() {
        recycler_movie.gone()
        constrain_data_not_found.gone()
    }
}
