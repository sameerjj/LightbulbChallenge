package com.sameer.lightbulbchallenge.pick

import kotlin.random.Random

class PickSimulationPresenter(val view: PickSimulationView) {

    /// run Single simulation, determine and display number of unique colors
    fun runSingleSimulation(numColors: Int, numOfEachColor: Int, numPicks: Int) {
        //map to keep track of numColor bulbs
        val pickMap: MutableMap<Int, Int> = mutableMapOf()
//        for (i in 1..numColors){
//            pickMap[i] = 0
//        }

        val numBulbs = numColors*numOfEachColor
        for (i in 1..numPicks){
            val pick = Random.nextInt(1, numBulbs)
            val color = pick/numOfEachColor
            pickMap[color] = (pickMap[color] ?: 0) + 1
        }

        var uniqueColors = 0
        for (i in 0..numColors){
            val colorCount = pickMap[i]
            if (colorCount != null && colorCount > 0){
                uniqueColors += 1
            }
        }

        view.displaySingleSimOutput(uniqueColors.toDouble())
    }
}