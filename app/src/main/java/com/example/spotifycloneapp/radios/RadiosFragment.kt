package com.example.spotifycloneapp.radios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.spotifycloneapp.R

class RadiosFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_radios, container, false)
    }

    companion object {
        fun newInstance() = RadiosFragment()
    }
}