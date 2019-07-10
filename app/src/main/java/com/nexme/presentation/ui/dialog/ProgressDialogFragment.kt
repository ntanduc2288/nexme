package com.nexme.presentation.ui.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.nexme.R

const val PROGRESS_DIALOG_TAG = "ProgressDialogFragment"
class ProgressDialogFragment: DialogFragment() {

    private var mOnDismissListener: DialogInterface.OnDismissListener? = null

    companion object {

        private val MESSAGE_ID_KEY = "MESSAGE_ID"

        private val CANCELABLE_KEY = "CANCELABLE"
        /**
         * Creates a new instance with default parameters
         */
        fun newInstance(): ProgressDialogFragment {
            return newInstance(R.string.loading, true)
        }

        /**
         * Creates a new instance with default message parameter
         */
        fun newInstance(cancelable: Boolean): ProgressDialogFragment {
            return newInstance(R.string.loading, cancelable)
        }

        /**
         * Creates a new instance with default cancelable parameter
         */
        fun newInstance(messageId: Int): ProgressDialogFragment {
            return newInstance(messageId, true)
        }

        /**
         * Creates a new instance
         */
        fun newInstance(messageId: Int, cancelable: Boolean): ProgressDialogFragment {
            val args = Bundle()
            args.putInt(MESSAGE_ID_KEY, messageId)
            args.putBoolean(CANCELABLE_KEY, cancelable)

            val fragment = ProgressDialogFragment()
            fragment.arguments = args

            return fragment
        }

    }

    fun setOnDismissListener(onDismissListener: DialogInterface.OnDismissListener) {
        mOnDismissListener = onDismissListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val v = LayoutInflater.from(context).inflate(R.layout.dialog_progress, null, false)
        (v.findViewById(R.id.message) as TextView).text = resources.getString(arguments!!.getInt(MESSAGE_ID_KEY))

        val dialog = AlertDialog.Builder(context!!, R.style.Theme_Pattern_Dialog)
            .setView(v)
            .create()

        isCancelable = arguments!!.getBoolean(CANCELABLE_KEY)

        return dialog
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        if (mOnDismissListener != null) {
            mOnDismissListener!!.onDismiss(dialog)
            mOnDismissListener = null
        }
    }
}