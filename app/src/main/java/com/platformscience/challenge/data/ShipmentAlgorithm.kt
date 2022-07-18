package com.platformscience.challenge.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.platformscience.challenge.data.model.FleetData
import com.platformscience.challenge.data.model.Shipment
import java.util.*
import java.util.concurrent.ConcurrentSkipListMap

@RequiresApi(Build.VERSION_CODES.N)
class ShipmentAlgorithm constructor(
    private val fleetData: FleetData?
){
    // driver+address score
    private val _shipmentMap = ConcurrentSkipListMap<String, Double>()
    // set sorted using SS
    private val _sortedShipmentSet : MutableList<Map.Entry<String, Double>> = ArrayList<Map.Entry<String, Double>>()
    // driver/address map that maximize SS
    private val _matchedMap = mutableMapOf<String, Pair<String, Double>>()

    init {
        if (fleetData != null) {
            if (fleetData.drivers != null) {
                // calculate score for each driver+address combination
                for (driver in fleetData.drivers) {
                    if (driver.isNotEmpty()) {
                        val baseScores = getBaseSuitabilityScores(driver)
                        if (fleetData.shipments != null) {
                            for (shipment in fleetData.shipments) {
                                if (shipment.isNotEmpty()) {
                                    _shipmentMap["$driver+$shipment"] = getSuitabilityScore(shipment, driver, baseScores)
                                }
                            }
                        }
                    }
                }

                // sort all entries using SS
                _sortedShipmentSet.addAll(_shipmentMap.entries)
                _sortedShipmentSet.sortWith { o1, o2 ->
                    o2.value.compareTo(o1.value)
                }

                // filter that maximize SS
                while (_sortedShipmentSet.isNotEmpty()) {
                    val keyValue = _sortedShipmentSet.first().key.split("+")
                    _matchedMap[keyValue[0]] = Pair<String, Double>(keyValue[1], _sortedShipmentSet.first().value)

                    _sortedShipmentSet.removeIf {
                        it.key.startsWith("${keyValue[0]}+") || it.key.endsWith("+${keyValue[1]}")
                    }
                }
            }
        }
    }

    /**
     * Retrieves final matched list of shipment info that maximize suitability score.
     *
     * @return List of shipment data.
     * @see Shipment
     */
    fun getShipments(): List<Shipment> {
        val shipments = mutableListOf<Shipment>()
        if (fleetData?.drivers?.isNotEmpty() == true) {
            for (driver in fleetData.drivers) {
                if (_matchedMap.containsKey(driver)) {
                    shipments.add(Shipment(driver, _matchedMap.getValue(driver).first))
                }
            }
        }
        return shipments
    }

    /**
     * Returns suitability score for a driver/address pair
     *
     * @param streetAddress Street address.
     * @param driverName Driver name.
     * @param baseScores Base scores.
     * @return Suitability score.
     */
    private fun getSuitabilityScore(streetAddress: String, driverName: String,
                                    baseScores: Pair<Double, Double>): Double {
        var score = if (isLengthEven(streetAddress)) {
            baseScores.first
        } else {
            baseScores.second
        }

        if (commonDiv(streetAddress.length, driverName.length)) {
            score *= 1.5
        }
        return score
    }

    /**
     * Retrieves base Suitability scores
     *
     * @param driverName Driver name.
     * @return Base scores (first: score based on vowels, second: score based on consonants)
     */
    private fun getBaseSuitabilityScores(driverName: String):Pair<Double, Double> {
        val vowelCount = vowelCount(driverName)
        return Pair(vowelCount*1.5, (driverName.length-vowelCount).toDouble())
    }

    private fun isLengthEven(name: String): Boolean {
        return name.length % 2 == 0
    }

    /**
     * Returns number of vowels in string
     *
     * @param name String to be examined.
     * @return Count of vowels.
     */
    private fun vowelCount(name: String): Int {
        val lowerCase = name.lowercase()
        var count = 0
        for (char in lowerCase) {
            if (char == 'a' || char == 'e' || char == 'i' || char == 'o' || char == 'u') {
                ++count
            }
        }
        return count
    }

    /**
     * Determine if x and y has common denominator
     */
    private fun commonDiv(x: Int, y: Int): Boolean {
        if (x<=1 || y <= 1) {
            return false
        }

        if (x == y) {
            return true
        }

        for (factor in 2..x.coerceAtMost(y)) {
            if (x % factor == 0 && y % factor == 0) {
                return true
            }
        }
        return false
    }
}
