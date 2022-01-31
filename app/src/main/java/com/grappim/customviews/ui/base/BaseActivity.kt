package com.grappim.customviews.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.*
import androidx.fragment.app.strictmode.FragmentStrictMode
import androidx.viewbinding.ViewBinding
import com.grappim.customviews.utils.viewBinding

open class BaseActivity<VB : ViewBinding>(
    bindingInflater: (LayoutInflater) -> VB
) : AppCompatActivity() {

    init {
        supportFragmentManager.strictModePolicy =
            FragmentStrictMode.Policy.Builder()
                .penaltyDeath()
                .detectFragmentReuse()
                .detectFragmentTagUsage()
                .detectWrongFragmentContainer()
                .build()
    }

    internal val viewBinding: VB by viewBinding(bindingInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
    }


    inline fun <reified FragmentToShow : Fragment> showFirstFragment(
        @IdRes containerViewId: Int,
    ) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<FragmentToShow>(containerViewId, FragmentToShow::class.java.canonicalName)
        }
    }

    inline fun <reified FragmentToShow : Fragment> showFragmentAndClearBackStack(
        @IdRes containerViewId: Int,
    ) {
        val tagToShow = FragmentToShow::class.java.canonicalName
        supportFragmentManager.popBackStack(
            null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            replace<FragmentToShow>(
                containerViewId = containerViewId,
                tag = tagToShow
            )
        }
    }

    inline fun <reified FragmentToShow : Fragment, reified FragmentToHide : Fragment> makeStandardFragmentTransaction(
        @IdRes containerViewId: Int,
        bundle: Bundle? = null,
        transactionType: FragmentTransactionType = FragmentTransactionType.REPLACE
    ) {
        val tagToShow = FragmentToShow::class.java.canonicalName
        val tagToHide = FragmentToHide::class.java.canonicalName

        val fragmentToMoveTo = supportFragmentManager.findFragmentByTag(tagToShow)
        if (fragmentToMoveTo != null &&
            fragmentToMoveTo::class.java == FragmentToShow::class.java
        ) {
// do nothing, because somehow the same fragment was opened
        } else {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                when (transactionType) {
                    FragmentTransactionType.REPLACE -> {
                        replace<FragmentToShow>(
                            containerViewId = containerViewId,
                            tag = tagToShow,
                            args = bundle
                        )
                    }
                    FragmentTransactionType.ADD_HIDE -> {
                        add<FragmentToShow>(
                            containerViewId = containerViewId,
                            tag = tagToShow,
                            args = bundle
                        )
                        hide(supportFragmentManager.findFragmentByTag(tagToHide)!!)
                    }
                }
                addToBackStack(tagToHide)
            }
        }
    }

    enum class FragmentTransactionType {
        REPLACE,
        ADD_HIDE
    }

}