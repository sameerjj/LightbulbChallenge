package com.sameer.lightbulbchallenge.pick

import kotlin.random.Random

class PickSimulationPresenter(val view: PickSimulationView) {

    fun runSingleSimulation(numColors: Int, numOfEachColor: Int, numPicks: Int) {
        val uniqueColors = runSimulation(numColors, numOfEachColor, numPicks)
        view.displaySimulationOutput(uniqueColors.toDouble())
    }

    fun runMultipleSimulations(numColors: Int, numOfEachColor: Int, numPicks: Int, numSimulations: Int) {
        var uniqueColorsSum = 0.0
        for (i in 0 until numSimulations) {
            uniqueColorsSum += runSimulation(numColors, numOfEachColor, numPicks)
        }

        view.displaySimulationOutput(uniqueColorsSum/numSimulations)
    }

    private fun runSimulation(numColors: Int, numOfEachColor: Int, numPicks: Int): Int {
        // array where each index represents a color and the number of bulbs left in that color
        val marbleArray = IntArray(numColors)
        for (i in 0 until numColors) {
            marbleArray[i] = numOfEachColor
        }


        var numBulbs = numColors * numOfEachColor
        // pick a random number out of the bulbs remaining, using the array as a sorting bucket to determine what color the bulb is
        for (i in 0 until numPicks) {
            var pick = Random.nextInt(numBulbs)
            for (j in 0 until numColors) {
                pick -= marbleArray[j]
                if (pick < 0) {
                    // found the bucket in which this bulb belongs, subtract it from the bucket count
                    marbleArray[j] -= 1
                    break;
                }
            }
            // decrement the number of bulbs remaining
            numBulbs -= 1
        }

        var uniqueColors = 0
        for (i in 0 until numColors) {
            // any bucket with less than the number of each color has been selected and therefore goes towards the unique count
            if (marbleArray[i] < numOfEachColor) {
                uniqueColors += 1
            }
        }

        return uniqueColors
    }
}