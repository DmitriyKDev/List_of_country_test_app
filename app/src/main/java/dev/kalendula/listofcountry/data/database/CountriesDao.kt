package dev.kalendula.listofcountry.data.database

import androidx.room.*

@Dao
interface CountriesDao {
    @Query("SELECT * FROM countries")
    fun getAll(): List<CountryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(countries : List<CountryEntity>)

    @Query("DELETE FROM countries")
    fun nuke()
}