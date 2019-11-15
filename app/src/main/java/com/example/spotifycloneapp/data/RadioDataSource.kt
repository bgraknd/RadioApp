package com.example.spotifycloneapp.data

import com.example.spotifycloneapp.data.remote.Radio
import com.example.spotifycloneapp.data.remote.RadioServiceProvider
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class RadioDataSource {

    private val radioServiceProvider = RadioServiceProvider()

    fun fetchPopularRadios(): Observable<Resource<List<Radio>>> {
        return Observable.create { emitter ->
            emitter.onNext(Resource.loading())

            radioServiceProvider
                .getRadioService()
                .getPopularRadios()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { radioList ->
                        emitter.onNext(Resource.success(radioList))
                        emitter.onComplete()
                    },
                    { error ->
                        emitter.onNext(Resource.error(error.message ?: ""))
                        emitter.onComplete()
                    })
        }
    }

    fun fetchLocationRadios(): Observable<Resource<List<Radio>>> {
        return Observable.create { emitter ->

            emitter.onNext(Resource.loading())

            radioServiceProvider
                .getRadioService()
                .getLocationRadios()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        emitter.onNext(Resource.success(it))
                        emitter.onComplete()
                    },
                    { error ->
                        emitter.onNext(Resource.error(error.message ?: ""))
                        emitter.onComplete()
                    }

                )


        }

    }
}