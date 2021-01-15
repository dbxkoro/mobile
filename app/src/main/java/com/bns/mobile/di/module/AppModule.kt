package com.bns.mobile.di.module

import android.content.Context
import androidx.preference.PreferenceManager
import com.bns.mobile.presenter.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context) : BaseApplication {
        return app as BaseApplication
    }

    @Singleton
    class PreferenceProvide
    @Inject
    constructor(@ApplicationContext context: Context) {
        private val prefs = PreferenceManager.getDefaultSharedPreferences(context)

        fun getPrefString(tag: String): String {
            return prefs.getString(tag, "")!!
        }

        fun setPrefString(tag: String, value: String?) {
            prefs.edit().putString(tag, value).apply()
        }

        fun deletePrefString(tag: String) {
            prefs.edit().putString(tag, "").apply()
        }
    }

}