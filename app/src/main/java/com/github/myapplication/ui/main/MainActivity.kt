package com.github.myapplication.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.github.myapplication.R.layout.activity_main)

        // Set pager
        view_pager.adapter = MainPagerAdapter(this, supportFragmentManager)
        tabs.setupWithViewPager(view_pager)
    }
}