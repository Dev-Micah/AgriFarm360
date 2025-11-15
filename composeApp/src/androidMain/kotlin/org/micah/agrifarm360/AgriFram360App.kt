package org.micah.agrifarm360

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.micah.agrifarm360.di.initKoin


class AgriFarm360Application: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin{
            androidLogger()
            androidContext(this@AgriFarm360Application)
        }
    }
}