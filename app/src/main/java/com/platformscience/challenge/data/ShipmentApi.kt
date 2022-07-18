package com.platformscience.challenge.data

import android.content.Context
import com.platformscience.challenge.data.model.FleetData
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

/**
 * API used to retrieve shipment data.
 * Uses Ktor with OkHttp
 *
 * @param context Context for MockClient
 * @see HttpClient
 */
class ShipmentApi(context: Context) {
    companion object {
        private const val BASE_URL = "http://api.platformscience.com/v1"
    }

    private val client = HttpClient(OkHttp) {
        engine {
            addInterceptor(MockClient(context))
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    /**
     * Retrieve all shipment data.
     * Suspended function for use with coroutines.
     *
     * @return FleetData
     * @see FleetData
     */
    suspend fun getShipments(): FleetData = client.get<FleetData> {
        endpoint("/shipments")
    }

    private fun HttpRequestBuilder.endpoint(endpoint: String) = this.url(BASE_URL + endpoint)
}