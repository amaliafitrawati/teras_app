package com.bangkit.teras_app.data

import com.bangkit.teras_app.model.DummyForumData
import com.bangkit.teras_app.model.DummyRiceData
import com.bangkit.teras_app.model.Forum
import com.bangkit.teras_app.model.RiceProduction
import kotlinx.coroutines.flow.flow

class RiceProductionRepository {
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