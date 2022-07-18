package com.platformscience.challenge.data

import com.platformscience.challenge.data.model.FleetData
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ShipmentAlgorithmTest {
    private val data = "{\n" +
            "    \"shipments\": [\n" +
            "        \"215 Osinski Manors\",\n" +
            "        \"9856 Marvin Stravenue\",\n" +
            "        \"7127 Kathlyn Ferry\",\n" +
            "        \"987 Chaplin Lake\",\n" +
            "        \"63187 Volkman Garden Suite 447\",\n" +
            "        \"75855 Dessie Lights\",\n" +
            "        \"1797 Adolf Island Apt. 744\",\n" +
            "        \"2431 Lindgren Corners\",\n" +
            "        \"8725 Aufderhar River Suite 859\",\n" +
            "        \"79035 Shanna Light Apt. 322\"\n" +
            "    ],\n" +
            "    \"drivers\": [\n" +
            "        \"Everardo Welch\",\n" +
            "        \"Orval Mayert\",\n" +
            "        \"Howard Emmerich\",\n" +
            "        \"Izaiah Lowe\",\n" +
            "        \"Monica Hermann\",\n" +
            "        \"Ellis Wisozk\",\n" +
            "        \"Noemie Murphy\",\n" +
            "        \"Cleve Durgan\",\n" +
            "        \"Murphy Mosciski\",\n" +
            "        \"Kaiser Sose\"\n" +
            "    ]\n" +
            "}"
    var algorithm: ShipmentAlgorithm? = null

    @Before
    fun setup() {
        algorithm = ShipmentAlgorithm(Json.decodeFromString<FleetData>(data))
    }

    @Test
    fun isAlgorithmValid() {
        val data = Json.decodeFromString<FleetData>(data)
        if (data.drivers?.isNotEmpty() == true && data.drivers?.size == data.shipments?.size) {
            val shipments = algorithm?.getShipments()
            Assert.assertEquals(shipments?.size, data.drivers?.size)

            for (shipment in shipments!!) {
                Assert.assertTrue(data.drivers!!.contains(shipment.driver))
            }
        }
    }
}