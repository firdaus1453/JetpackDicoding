package com.github.myapplication.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.myapplication.R
import com.github.myapplication.ui.main.favorite.MainFavoriteFragment
import com.github.myapplication.ui.main.movie.MovieFragment
import com.github.myapplication.ui.main.tvshow.TvShowFragment
import com.github.myapplication.utils.Constants.KEY_FRAGMENT
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var pageContent: Fragment = MovieFragment()
    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_movies -> {
                    loadFragment(MovieFragment.newInstance())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_tv -> {
                    loadFragment(TvShowFragment.newInstance())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_favorite -> {
                    loadFragment(MainFavoriteFragment.newInstance())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        if (savedInstanceState == null) {
            loadFragment(pageContent)
        } else {
            pageContent = supportFragmentManager.getFragment(savedInstanceState, KEY_FRAGMENT) ?: MovieFragment()
            loadFragment(pageContent)
        }
    }

    private fun loadFragment(selectedFragment: Fragment) {
        pageContent = selectedFragment
        // Show fragment with fragment transaction
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_container, selectedFragment)
        transaction.commit()
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        supportFragmentManager.putFragment(outState, KEY_FRAGMENT, pageContent)
        super.onSaveInstanceState(outState)
    }
}