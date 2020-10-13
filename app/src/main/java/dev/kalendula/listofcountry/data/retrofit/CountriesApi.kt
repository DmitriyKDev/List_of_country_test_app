package dev.kalendula.listofcountry.data.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface CountriesApi {
    @GET("all/")
  fun getAll(): Call<List<CountryObject>>
}