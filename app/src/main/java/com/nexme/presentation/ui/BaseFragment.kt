package com.nexme.presentation.ui

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.nexme.presentation.ui.dialog.PROGRESS_DIALOG_TAG
import com.nexme.presentation.ui.dialog.ProgressDialogFragment
import java.lang.ref.WeakReference

abstract class BaseFragment : Fragment() {
    abstract fun getLayoutId(): Int
    abstract fun setupViews()

    private var mCurrentDialog: WeakReference<ProgressDialogFragment>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    fun showProgressDialog(messageId: Int, cancelable: Boolean, tag: String) {
        if (isProgressDialogShowing() || isDetached || isRemoving || activity == null || activity!!.isFinishing
        ) {
            return
        }

        val dialog: ProgressDialogFragment
        if (messageId > 0) {
            dialog = ProgressDialogFragment.newInstance(messageId, cancelable)
        } else {
            dialog = ProgressDialogFragment.newInstance(cancelable)
        }
        mCurrentDialog = WeakReference(dialog)
        dialog.isCancelable = cancelable
        dialog.show(childFragmentManager, tag)
    }


    private fun isProgressDialogShowing(): Boolean {
        return getExistingDialog() != null
    }

    private fun getExistingDialog(): DialogFragment? {
        if (!isAdded) return null

        val manager = childFragmentManager

        val dialog = manager
            .findFragmentByTag(PROGRESS_DIALOG_TAG) as DialogFragment?
        if (dialog != null) {
            return dialog
        }

        return if (mCurrentDialog != null) {
            mCurrentDialog?.get()
        } else null

    }

    fun hideProgressDialog() {
        val dialog = getExistingDialog()

        if (dialog != null) {
            if (activity is BaseActivity) {
                val activity = activity as BaseActivity?
                if (activity!!.isActive) {
                    dialog.dismissAllowingStateLoss()
                }
            } else if (activity != null) {
                dialog.dismissAllowingStateLoss()
            }
            mCurrentDialog = null
        }
    }

    fun getCurrentActivity(): AppCompatActivity {
        return activity as AppCompatActivity
    }
}