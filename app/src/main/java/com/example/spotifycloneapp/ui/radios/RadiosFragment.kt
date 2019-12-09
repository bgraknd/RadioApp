package com.example.spotifycloneapp.ui.radios

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.spotifycloneapp.R
import com.example.spotifycloneapp.data.RadioDataSource
import com.example.spotifycloneapp.data.Status.LOADING
import com.example.spotifycloneapp.data.Status.SUCCESS
import com.example.spotifycloneapp.databinding.FragmentRadiosBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RadiosFragment : Fragment() {

    private lateinit var binding: FragmentRadiosBinding

    val radioDataSource = RadioDataSource()

    private val popularRadiosAdapter = RadiosAdapter()
    private val locationRadiosAdapter = RadiosAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_radios, container, false)

        binding.recyclerViewPopular.adapter = popularRadiosAdapter
        binding.recyclerViewLocation.adapter = locationRadiosAdapter

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fetchRadioPage()

        //val list = arrayListOf(1, 2, 3)
        //Observable.fromIterable(list).subscribe { number -> Log.v("TEST", "$number") }
    }

    @SuppressLint("CheckResult")
    private fun fetchRadioPage() {

        val popularObservable = radioDataSource.fetchPopularRadios()
        val locationObservable = radioDataSource.fetchLocationRadios()

        Observable.combineLatest(popularObservable, locationObservable, RadioPageCombiner())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                renderUI(it)
            }
    }

    private fun renderUI(radiosFragmentViewState: RadiosFragmentViewState) {

        when (radiosFragmentViewState.popularRadios.status) {
            SUCCESS -> {
                binding.progressPopularRadios.visibility = View.GONE
                popularRadiosAdapter.setRadioList(radiosFragmentViewState.popularRadios.data!!)
            }
            LOADING -> {
                binding.progressPopularRadios.visibility = View.VISIBLE
            }
        }

        when (radiosFragmentViewState.locationRadios.status) {
            SUCCESS -> {
                binding.progressLocationRadios.visibility = View.GONE
                locationRadiosAdapter.setRadioList(radiosFragmentViewState.locationRadios.data!!)
            }
            LOADING -> {
                binding.progressLocationRadios.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        fun newInstance() = RadiosFragment()
    }
}