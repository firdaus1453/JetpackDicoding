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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.anko.startActivity

class TvShowFragment : Fragment() {

    private lateinit var mViewModel: TvShowViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
        setupRecycler()
        setupRefresh()
    }

    private fun getData() {
        CoroutineScope(Dispatchers.Main).launch {
            mViewModel.getAllTvShows()
        }
    }

    private fun setupViewModel() {
        mViewModel = obtainVm()
        setupObserver()
        getData()
    }

    private fun setupObserver() {
        mViewModel.apply {
            getTvShowList().observe(this@TvShowFragment, Observer {
                constrain_data_not_found.gone()
                recycler_movie.visible()
                adapter.setContentList(it)
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

    private fun setupRecycler() {
        adapter = MainAdapter { idMovie ->
            context?.startActivity<DetailTvShowActivity>(Constants.KEY_TVSHOW to idMovie)
        }
        recycler_movie.let {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setupRefresh() {
        swipe_refresh.setOnRefreshListener {
            swipe_refresh.isRefreshing = false
            getData()
            goneAll()
        }
    }

    private fun obtainVm(): TvShowViewModel = obtainViewModel(TvShowViewModel::class.java)

    private fun goneAll() {
        recycler_movie.gone()
        constrain_data_not_found.gone()
    }

    companion object {
        fun newInstance() = TvShowFragment()
    }
}
