package dev.kalendula.listofcountry.data

import androidx.lifecycle.MutableLiveData
import dev.kalendula.listofcountry.data.database.CountriesDao
import dev.kalendula.listofcountry.data.database.CountryEntity
import dev.kalendula.listofcountry.data.retrofit.NetworkHelper
import dev.kalendula.listofcountry.utils.Resource
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Singleton
class Repository @Inject constructor(
   private val remoteData : NetworkHelper,
   private val localData: CountriesDao
) {
    private val ioScope= CoroutineScope(Dispatchers.IO)
    private val liveData : MutableLiveData<Resource<List<CountryEntity>>> = MutableLiveData()

    fun getLd() = liveData

    fun getData(){
        ioScope.launch {
            val t = getCache() ?: getRemoteData()
            liveData.postValue(t)
        }
    }

    fun update(){
        ioScope.launch {
            liveData.postValue(getRemoteData())
        }
    }
    private suspend fun getRemoteData():Resource<List<CountryEntity>>{
        val t = remoteData.refresh()
        when (t.status) {
            Resource.Status.SUCCESS -> setCache(t.data)
            Resource.Status.ERROR -> {
                liveData.postValue(t)
                return Resource.success(localData.getAll())
            }
        }
        return t
    }

    private suspend fun getCache(): Resource<List<CountryEntity>>?{
        return suspendCoroutine { continuation ->
            val result = localData.getAll()
            if (result.isEmpty()){
                continuation.resume(null)
            }else{
                continuation.resume(Resource.success(result))
            }
        }
    }

    private fun setCache(list: List<CountryEntity>?){
        if (!list.isNullOrEmpty()){
            localData.insertAll(list)
        }

    }
}