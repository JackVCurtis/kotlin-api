package com.elasticm2m.kotlin.services

import com.elasticm2m.kotlin.api.HealthStatus
import javax.inject.Singleton

@Singleton
class HealthService {

    fun healthCheck() : HealthStatus {
        return HealthStatus("ok")
    }
}