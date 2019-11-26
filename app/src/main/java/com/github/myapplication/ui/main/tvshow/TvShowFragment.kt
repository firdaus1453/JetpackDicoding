package com.github.myapplication.ui.main.tvshow


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.myapplication.R
import com.github.myapplication.ui.detail.detailtvshow.DetailTvShowActivity
import com.github.myapplication.ui.main.MainAdapter
import com.github.myapplication.utils.Constants
import com.github.myapplication.utils.gone
import com.github.myapplication.utils.obtainViewModel
import com.github.myapplication.utils.visible
import kotlinx.android.synthetic.main.fragment_movie.*
import org.jetbrains.anko.startActivity

class TvShowFragment : Fragment() {

    private lateinit var mViewModel: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupRefresh()
    }

    private fun getData() {
        mViewModel.getAllTvShows()
    }

    private fun setupViewModel() {
        mViewModel = obtainVm()
        setupObserver()
    }

    private fun setupRefresh() {
        swipe_refresh.setOnRefreshListener {
            swipe_refresh.isRefreshing = false
            getData()
            goneAll()
        }
    }

    private fun setupObserver() {
        mViewModel.apply {
            getTvShowList().observe(viewLifecycleOwner, Observer {
                constrain_data_not_found.gone()
                recycler_movie.visible()
                recycler_movie.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = MainAdapter(it) { idTvShow ->
                        context?.startActivity<DetailTvShowActivity>(Constants.KEY_TVSHOW to idTvShow)
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

    private fun obtainVm(): TvShowViewModel = obtainViewModel(TvShowViewModel::class.java)

    private fun goneAll() {
        recycler_movie.gone()
        constrain_data_not_found.gone()
    }
}
