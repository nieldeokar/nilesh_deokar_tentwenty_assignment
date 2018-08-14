package com.tentwenty.movieticket.di.component

import com.tentwenty.movieticket.di.module.ApplicationModule
import com.tentwenty.movieticket.feature.main.MainActivity
import com.tentwenty.movieticket.feature.seatselection.SeatSelectionActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(baseActivity: MainActivity)

    fun inject(seatSelectionActivity: SeatSelectionActivity)
}