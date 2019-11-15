package com.example.spotifycloneapp.ui.radios

import com.example.spotifycloneapp.data.Resource
import com.example.spotifycloneapp.data.remote.Radio

data class RadiosFragmentViewState(
    val popularRadios: Resource<List<Radio>>,
    val locationRadios: Resource<List<Radio>>
)