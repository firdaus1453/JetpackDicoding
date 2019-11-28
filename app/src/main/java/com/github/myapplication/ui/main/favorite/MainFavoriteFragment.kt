package com.github.myapplication.ui.main.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.myapplication.R
import com.github.myapplication.utils.Constants.CATEGORY_MOVIE
import com.github.myapplication.utils.Constants.CATEGORY_TV
import kotlinx.android.synthetic.main.fragment_main_favorite.*

class MainFavoriteFragment : Fragment() {

    private lateinit var favoritePagerAdapter: FavoritePagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragments = listOf<Fragment>(
            FavoriteFragment.newInstance(CATEGORY_MOVIE),
            FavoriteFragment.newInstance(CATEGORY_TV)
        )

        favoritePagerAdapter = FavoritePagerAdapter(context, fragments, childFragmentManager)
        pager_main_favorite.adapter = favoritePagerAdapter
        tl_main_favorite.setupWithViewPager(pager_main_favorite)
    }

    companion object {
        fun newInstance() = MainFavoriteFragment()
    }
}
