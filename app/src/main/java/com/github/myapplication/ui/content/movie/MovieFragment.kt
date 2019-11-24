package com.github.myapplication.ui.content.movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.myapplication.R
import com.github.myapplication.data.model.MoviesModel
import com.github.myapplication.ui.content.ContentViewModel
import com.github.myapplication.ui.detail.detailmovie.DetailMovieActivity
import com.github.myapplication.ui.content.ContentAdapter
import com.github.myapplication.utils.Constants.Companion.KEY_MOVIE
import kotlinx.android.synthetic.main.fragment_movie.*
import org.jetbrains.anko.startActivity

class MovieFragment : Fragment() {

    private var data = listOf<MoviesModel>()
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

        data = mViewModel.getAllMovies

        recycler_movie.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ContentAdapter(data) { idMovie ->
                context?.startActivity<DetailMovieActivity>(KEY_MOVIE to idMovie)
            }
        }
    }
}
