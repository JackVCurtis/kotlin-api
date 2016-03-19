package com.elasticm2m.kotlin.api

import com.elasticm2m.kotlin.api.HealthStatus
import com.elasticm2m.kotlin.services.HealthService
import com.github.jknack.handlebars.Handlebars
import com.typesafe.config.Config
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("health")
class Health @Inject constructor(val config: Config, val healthService: HealthService) {

    val handlebars = Handlebars()
    val template = handlebars.compileInline("\"status\": \"{{this}}\"")

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun healthCheck(): String {
        template.apply("Handlebars.java")
        return template.apply(config.getString("health.message"))
    }

    @GET
    @Path("json")
    @Produces(MediaType.APPLICATION_JSON)
    fun healthCheckJson(): HealthStatus {
        return healthService.healthCheck()
    }
}
