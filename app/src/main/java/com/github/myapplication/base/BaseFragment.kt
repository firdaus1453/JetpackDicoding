package com.github.myapplication.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.myapplication.R
import com.github.myapplication.utils.extension.showSnackbarDefault
import com.github.myapplication.utils.extension.showSnackbarWithCustomColor
import com.github.myapplication.utils.extension.showToast

/**
 * Created by Muhammad Firdaus on 23/11/2019.
 */
abstract class BaseFragment<T : BaseViewModel> : Fragment() {
    lateinit var mParentVM: T
    private var mMessageType = MESSAGE_TYPE_SNACK


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mParentVM = onCreateViewModel()
    }

    override fun onViewCreated(paramView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(paramView, savedInstanceState)
        mParentVM.apply {
            eventGlobalMessage.observe(this@BaseFragment, Observer {
                if (it != null) {
                    when (mMessageType) {
                        MESSAGE_TYPE_SNACK_CUSTOM -> {
                            view?.showSnackbarWithCustomColor(it,
                                R.color.colorAccent,
                                R.color.greyBackgroundDefault)
                        }
                        MESSAGE_TYPE_SNACK -> {
                            view?.showSnackbarDefault(it)
                        }
                        else -> {
                            requireContext().showToast(it)
                        }
                    }
                }
            })
        }

        onCreateObserver(mParentVM)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setContentData()
        mMessageType = setMessageType()
        mParentVM.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDestroyObserver(mParentVM)
    }

    abstract fun onCreateViewModel(): T
    abstract fun onCreateObserver(viewModel: T)
    abstract fun setContentData()
    abstract fun setMessageType(): String
    abstract fun onDestroyObserver(viewModel: T)

    companion object {
        const val MESSAGE_TYPE_SNACK = "SNACK_TYPE"
        const val MESSAGE_TYPE_SNACK_CUSTOM = "SNACK_CUSTOM_TYPE"
    }
}