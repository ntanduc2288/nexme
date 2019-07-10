package com.nexme.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.nexme.R
import com.nexme.presentation.ui.dialog.PROGRESS_DIALOG_TAG
import com.nexme.presentation.ui.dialog.ProgressDialogFragment
import com.nexme.presentation.utils.pushFragment

abstract class BaseLiveDataFragment: BaseFragment() {
    abstract fun registerViewModels()

    abstract fun getCurrentViewModel(): BaseViewModel

    abstract fun subscribeObservers()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerViewModels()
        subscribeBaseObservers()
        subscribeObservers()
    }

    private fun subscribeBaseObservers() {
        getCurrentViewModel().progressDialogLiveData.observe(this, showLoadingDialogObserver)
        getCurrentViewModel().alertLiveData.observe(this, showAlertDialogObserver)
        getCurrentViewModel().backLiveData.observe(this, backObserver)
        getCurrentViewModel().toastLiveData.observe(this, toastObserver)
    }

    private val showLoadingDialogObserver = Observer<Boolean> {
        if (it!!) {
            showProgressDialog(R.string.loading, true, PROGRESS_DIALOG_TAG)
        } else {
            hideProgressDialog()
        }
    }

    private val showAlertDialogObserver = Observer<String> {
//        showAlert(it)
    }

    private val backObserver = Observer<String> {
        activity?.onBackPressed()
    }

    private val toastObserver = Observer<String> { message ->
        run {
            context?.let { Toast.makeText(context, message, Toast.LENGTH_SHORT).show() }
        }
    }
}