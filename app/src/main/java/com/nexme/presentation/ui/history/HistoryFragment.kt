package com.nexme.presentation.ui.explore

import androidx.lifecycle.ViewModelProviders
import com.nexme.R
import com.nexme.presentation.ui.BaseLiveDataFragment

class HistoryFragment: BaseLiveDataFragment() {
    private lateinit var historyViewModel: HistoryViewModel

    companion object {
        fun newInstance(): HistoryFragment {
            return HistoryFragment()
        }
    }

    override fun registerViewModels() {
        historyViewModel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
    }

    override fun getCurrentViewModel() = historyViewModel

    override fun subscribeObservers() {
    }

    override fun getLayoutId() = R.layout.history_layout

    override fun setupViews() {
    }
}