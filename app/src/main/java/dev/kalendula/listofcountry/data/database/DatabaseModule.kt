package dev.kalendula.listofcountry.data.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): CountriesDatabase {
        return Room.databaseBuilder(appContext,
            CountriesDatabase::class.java, "countriesDB")
            .build()
    }

    @Provides
    @Singleton
    fun provideCountriesDao(database: CountriesDatabase): CountriesDao {
        return database.countriesDao()
    }
}