package dev.kalendula.listofcountry.data.retrofit

import dev.kalendula.listofcountry.data.database.CountryEntity
import dev.kalendula.listofcountry.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class NetworkHelper @Inject constructor(
    private val countriesApi: CountriesApi
) {
    suspend fun refresh(): Resource<List<CountryEntity>>{
        return suspendCoroutine { continuation ->
            val messages = countriesApi.getAll()
            messages.enqueue(object : Callback<List<CountryObject>> {
                override fun onResponse(
                    call: Call<List<CountryObject>>,
                    response: Response<List<CountryObject>>
                ) {
                    if (response.isSuccessful) {
                        continuation.resume(Resource.success(mapping(response.body()!!)))
                    } else {continuation.resume(Resource.error(response.code().toString(), null))

                    }
                }

                override fun onFailure(call: Call<List<CountryObject>>, t: Throwable) {
                    continuation.resume(Resource.error("", null))
                }
            })
        }

    }

    private fun mapping(baseList : List<CountryObject>): List<CountryEntity>{
        return baseList.map { r ->
            CountryEntity(
                r.name,
                r.getLang(),
                r.getCurrency(),
                r.getTimezone(),
                r.flagUrl) }
    }
}
