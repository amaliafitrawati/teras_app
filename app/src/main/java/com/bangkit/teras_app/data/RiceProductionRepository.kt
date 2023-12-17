package com.bangkit.teras_app.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.bangkit.teras_app.data.api.ApiConfig
import com.bangkit.teras_app.data.api.ApiService
import com.bangkit.teras_app.model.DummyForumData
import com.bangkit.teras_app.model.DummyRiceData
import com.bangkit.teras_app.model.Forum
import com.bangkit.teras_app.model.RiceProduction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RiceProductionRepository() {
    private val apiService = ApiConfig.api
    private val listRiceProduction = mutableListOf<RiceProduction>()
    private val listForum = mutableListOf<Forum>()

    init{
        if (listRiceProduction.isEmpty()) {
            DummyRiceData.rice.forEach {
                listRiceProduction.add(it)

            }
        }
        if (listForum.isEmpty()) {
            DummyForumData.forum.forEach {
                listForum.add(it)

            }
        }
    }

    fun getRiceProduction(): List<RiceProduction> {
        return DummyRiceData.rice
    }

    fun getRiceByYear(query: Int?) = flow {
        val riceProduction =
            if(query != null){
                DummyRiceData.rice.filter {
                    it.year == query
                }
            }else{
                DummyRiceData.rice
            }

        val sortedRice = riceProduction.sortedBy { it.ranking }
        val listRiceFinal = sortedRice.take(3) + sortedRice.takeLast(3)
        emit(listRiceFinal)
    }

    fun getAllForumData() = flow{
        emit(DummyForumData.forum)
    }

//    fun getForumApi() = flow{
//        try{
//            val response = apiService.getForum()
////            if(response != null){
////                emit(apiService.getForum())
////            }
//        }catch(e : Exception){
//            Log.e("GET FORUM DATA FROM API", e.message.toString())
//        }
//    }

//     fun getForumFromApi(): Flow<List<Forum>> = flow{
//        Log.e("get forum data", apiService.getForum().toString())
//        apiService.getForum().forEach {
//            Log.e("TEST FORUM DATA", it.title)
//        }
//        emit(apiService.getForum())
//    }


    companion object {
        @Volatile
        private var instance: RiceProductionRepository? = null

        fun getInstance(): RiceProductionRepository =
            instance ?: synchronized(this) {
                RiceProductionRepository().apply {
                    instance = this
                }
            }
    }
}