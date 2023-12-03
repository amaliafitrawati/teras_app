package com.bangkit.teras_app.data

class RiceProductionRepository {

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