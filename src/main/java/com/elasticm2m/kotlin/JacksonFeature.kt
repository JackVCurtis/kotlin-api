package com.elasticm2m.kotlin

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

import javax.ws.rs.core.Feature
import javax.ws.rs.core.FeatureContext

class JacksonFeature : Feature {

    private val mapper = object : ObjectMapper() {
        init {
            configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
        }
    }.registerKotlinModule()

    private val provider = object : JacksonJaxbJsonProvider() {
        init {
            setMapper(mapper)
        }
    }

    override fun configure(context: FeatureContext): Boolean {
        context.register(provider)
        return true
    }
}