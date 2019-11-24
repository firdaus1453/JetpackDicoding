package com.github.myapplication.base

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.github.myapplication.utils.extension.replaceFragmentInActivity

/**
 * Created by Muhammad Firdaus on 23/11/2019.
 */
abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    lateinit var mActivity: AppCompatActivity
    lateinit var viewBinding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, bindLayoutRes())
        viewBinding.apply {
            mActivity = this@BaseActivity

            setupToolbar()
            onStartWork()
            replaceFragment()
        }
    }

    private fun setupToolbar() {
        if (bindToolbarId() != 0) {
            setSupportActionBar(findViewById(bindToolbarId()))
            supportActionBar?.apply {
                setDisplayShowTitleEnabled(false)
            }
        }
    }

    private fun replaceFragment() {
        replaceFragmentInActivity(bindFragmentInstance(), bindFragmentContainerId())
    }

    @LayoutRes
    abstract fun bindLayoutRes(): Int

    @IdRes
    abstract fun bindToolbarId(): Int

    @IdRes
    abstract fun bindFragmentContainerId(): Int

    abstract fun bindFragmentInstance(): Fragment

    abstract fun onStartWork()

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = if (item?.itemId == android.R.id.home) {
        onBackPressed()
        true
    } else {
        false
    }
}