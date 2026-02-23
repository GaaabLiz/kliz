package io.github.gaaabliz.kliz.android.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.gaaabliz.kliz.android.util.LogUtils

abstract class BaseFragment(private val appLogTag : String) : Fragment() {

    val logger = LogUtils.Logger(appLogTag)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logger.verbose("onCreate di ${this.javaClass.simpleName} chiamata.")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        logger.verbose("onCreateView di ${this.javaClass.simpleName} chiamata.")
        return inflater.inflate(getContentView(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logger.verbose("onViewCreated di ${this.javaClass.simpleName} chiamata.")
        execFindIds(view)
        execListeners()
        execStartupCode()
    }

    override fun onAttach(context: android.content.Context) {
        super.onAttach(context)
        logger.verbose("onAttach di ${this.javaClass.simpleName} chiamata.")
    }

    override fun onDetach() {
        super.onDetach()
        logger.verbose("onDetach di ${this.javaClass.simpleName} chiamata.")
    }

    override fun onPause() {
        super.onPause()
        logger.verbose("onPause di ${this.javaClass.simpleName} chiamata.")
    }

    override fun onResume() {
        super.onResume()
        logger.verbose("onResume di ${this.javaClass.simpleName} chiamata.")
    }

    override fun onStart() {
        super.onStart()
        logger.verbose("onStart di ${this.javaClass.simpleName} chiamata.")
    }

    override fun onStop() {
        super.onStop()
        logger.verbose("onStop di ${this.javaClass.simpleName} chiamata.")
    }

    protected abstract fun getContentView():Int
    protected open fun execFindIds(view: View) {}
    protected open fun execListeners() {}
    protected open fun execStartupCode() {}

}