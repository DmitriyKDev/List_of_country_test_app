package dev.kalendula.listofcountry.data.retrofit

import com.google.gson.annotations.SerializedName

class CountryObject (
    @SerializedName("name")
    val name : String,
    @SerializedName("languages")
    val lang : List<Languages>,
    @SerializedName("currencies")
    val currency : List<Currencies>,
    @SerializedName("timezones")
    val timezone : List<String>,
    @SerializedName("flag")
    val flagUrl : String
){
    fun getCurrency(): String{
        val list : MutableList<String> = mutableListOf()
        for (i in currency){
            list.add(i.name)
        }
        return  list.joinToString()
    }
    fun getLang(): String{
        val list : MutableList<String> = mutableListOf()
        for (i in lang){
            list.add(i.name)
        }
        return  list.joinToString()
    }
    fun getTimezone(): String{
        return  timezone.joinToString()
    }

    data class Languages(
        @SerializedName("name")
        val name: String
    )
    data class Currencies(
        @SerializedName("name")
        val name: String
    )
}

