package com.nexme.presentation.ui.home

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavSwitchController
import com.ncapdevi.fragnav.FragNavTransactionOptions
import com.ncapdevi.fragnav.tabhistory.UniqueTabHistoryStrategy
import com.nexme.R
import com.nexme.presentation.ui.BaseActivity
import com.nexme.presentation.ui.FragmentNavigation
import com.nexme.presentation.ui.account.AccountFragment
import com.nexme.presentation.ui.explore.ExploreFragment
import com.nexme.presentation.ui.explore.FavoritesFragment
import com.nexme.presentation.ui.explore.HistoryFragment
import com.nexme.presentation.ui.explore.ServicesFragment
import com.nexme.presentation.ui.onboarding.tour.TourFragment
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : BaseActivity(), FragNavController.RootFragmentListener, FragNavController.TransactionListener, FragmentNavigation {



    private val INDEX_EXPLORE = 0
    private val INDEX_FAVORITES = 1
    private val INDEX_HISTORY = 2
    private val INDEX_SERVICES = 3
    private val INDEX_MY_NEXME = 4

    private val fragNavController: FragNavController = FragNavController(supportFragmentManager, R.id.container)

    override fun getLayoutId() = R.layout.home_activity


    override val numberOfRootFragments: Int
        get() = 5

    override fun getRootFragment(index: Int): Fragment {
        when (index) {
            INDEX_EXPLORE -> return ExploreFragment.newInstance()
            INDEX_FAVORITES -> return FavoritesFragment.newInstance()
            INDEX_HISTORY -> return HistoryFragment.newInstance()
            INDEX_SERVICES -> return ServicesFragment.newInstance()
            INDEX_MY_NEXME -> return AccountFragment.newInstance()
        }
        throw IllegalStateException("Need to send an index that we know")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBottomTabs(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        fragNavController.onSaveInstanceState(outState)
    }

    private fun initBottomTabs(savedInstanceState: Bundle?) {
        fragNavController.transactionListener = this
        fragNavController.rootFragmentListener = this
        fragNavController.createEager = true
        fragNavController.fragmentHideStrategy = FragNavController.DETACH_ON_NAVIGATE_HIDE_ON_SWITCH
        fragNavController.navigationStrategy = UniqueTabHistoryStrategy(object : FragNavSwitchController {
            override fun switchTab(index: Int, transactionOptions: FragNavTransactionOptions?) { changeTab(index) }
        })

        fragNavController.initialize(INDEX_EXPLORE, savedInstanceState)

        val initial = savedInstanceState == null
        bottomBar.setOnTabSelectListener({ tabId ->
            when (tabId) {
                R.id.explore -> {
                    fragNavController.switchTab(INDEX_EXPLORE)
                }
                R.id.favorites -> {
                    fragNavController.switchTab(INDEX_FAVORITES)
                }
                R.id.history -> {
                    fragNavController.switchTab(INDEX_HISTORY)
                }
                R.id.service -> {
                    fragNavController.switchTab(INDEX_SERVICES)
                }
                R.id.account -> {
                    fragNavController.switchTab(INDEX_MY_NEXME)
                }
            }
        }, initial)
    }

    override fun onFragmentTransaction(fragment: Fragment?, transactionType: FragNavController.TransactionType) {
    }

    override fun onTabTransaction(fragment: Fragment?, index: Int) {
    }

    override fun pushFragment(fragment: Fragment) {
        val builder = FragNavTransactionOptions.Builder().allowStateLoss(false)
        fragNavController.pushFragment(fragment, builder.build())
    }
    override fun popCurrentFragmentOutOfStack() {
        onBackPressed()
    }

    override fun backToSpecificScreen(fragmentClass: Class<*>) {
        var currentLoopNumber = 0
        while (fragNavController.currentStack!!.size > 0) {
            if (fragNavController.currentFrag!!.javaClass != fragmentClass) {
                popCurrentFragmentOutOfStack()
                currentLoopNumber++
            } else if (currentLoopNumber >= fragNavController.currentStack!!.size) {
                break
            } else {
                break
            }
        }
    }

    override fun getSpecificFragmentInStack(fragmentClass: Class<*>): Fragment? {
        for (fragment in fragNavController.currentStack!!) {
            if (fragment.javaClass == fragmentClass) {
                return fragment
            }
        }

        return null
    }

    override fun clearStack() {
        val builder = FragNavTransactionOptions.Builder().allowStateLoss(true)
        fragNavController.clearStack(builder.build())
    }

    override fun showBottomBar(isNeedToShowBottomBar: Boolean) {
        bottomBar.visibility = if (isNeedToShowBottomBar) View.VISIBLE else View.GONE
    }

    override fun changeTab(tabIndex: Int) {
        bottomBar.selectTabAtPosition(tabIndex, false)
    }

}