package dev.kalendula.listofcountry.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
class CountryEntity (
    @PrimaryKey
    @ColumnInfo(name = "name")
    var name : String,
    @ColumnInfo(name = "language")
    var lang : String,
    @ColumnInfo(name = "currency")
    var currency : String,
    @ColumnInfo(name = "timezone")
    var timezone : String,
    @ColumnInfo(name = "flag")
    var flagUrl : String
)

