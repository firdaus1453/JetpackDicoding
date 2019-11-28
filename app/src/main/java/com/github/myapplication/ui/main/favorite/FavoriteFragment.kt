package com.github.myapplication.ui.main.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.myapplication.R
import com.github.myapplication.ui.detail.detailmovie.DetailMovieActivity
import com.github.myapplication.ui.main.MainAdapter
import com.github.myapplication.utils.Constants
import com.github.myapplication.utils.Constants.CATEGORY_MOVIE
import com.github.myapplication.utils.Constants.CATEGORY_TV
import com.github.myapplication.utils.gone
import com.github.myapplication.utils.obtainViewModel
import com.github.myapplication.utils.visible
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_movie.constrain_data_not_found
import kotlinx.android.synthetic.main.fragment_movie.progress_bar
import kotlinx.android.synthetic.main.fragment_movie.text_sad
import org.jetbrains.anko.startActivity

class FavoriteFragment : Fragment() {

    private lateinit var mViewModel: FavoriteViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
        setupRecycler()
    }

    private fun setupViewModel() {
        mViewModel = obtainVm()
        setupObserver()
        getData()
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    private fun getData() {
        when (arguments?.getInt(ARG_SECTION_NUMBER) ?: 1) {
            CATEGORY_MOVIE -> {
                mViewModel.getAllMovies(CATEGORY_MOVIE)
            }
            CATEGORY_TV -> {
                mViewModel.getAllMovies(CATEGORY_TV)
            }
        }
    }

    private fun setupObserver() {
        mViewModel.apply {
            getMovieList().observe(this@FavoriteFragment, Observer {
                constrain_data_not_found.gone()
                recycler_favorite.visible()
                adapter.setContentList(it)
            })

            eventGlobalMessage.observe(this@FavoriteFragment, Observer {
                if (it != null) {
                    recycler_favorite.gone()
                    constrain_data_not_found.visible()
                    text_sad.text = it
                }
            })

            eventShowProgress.observe(this@FavoriteFragment, Observer {
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
            context?.startActivity<DetailMovieActivity>(Constants.KEY_MOVIE to idMovie)
        }
        recycler_favorite.let {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun obtainVm(): FavoriteViewModel = obtainViewModel(FavoriteViewModel::class.java)


    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): FavoriteFragment {
            return FavoriteFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}
