package com.sameer.lightbulbchallenge.pick

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sameer.lightbulbchallenge.R
import com.sameer.lightbulbchallenge.databinding.ActivityPickSimulationBinding

class PickSimulationActivity : AppCompatActivity(), PickSimulationView {
    var presenter: PickSimulationPresenter? = null
    lateinit var binding: ActivityPickSimulationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickSimulationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = PickSimulationPresenter(this)

        binding.button.setOnClickListener {
            runSimulation()
        }
    }

    fun runSimulation() {
        try {
            val numColors = Integer.parseInt(binding.numberBulbColorsEditText.text.toString())
            val numEachColor = Integer.parseInt(binding.numbEachColorEditText.text.toString())
            val numPicks = Integer.parseInt(binding.numberPicksEditText.text.toString())
            val numSimulations = Integer.parseInt(binding.numberSimulationsEditText.text.toString())
            presenter?.runMultipleSimulations(numColors, numEachColor, numPicks, numSimulations)
        } catch (e: NumberFormatException) {
            showParsingError()
        }

    }

    private fun showParsingError() {
        MaterialAlertDialogBuilder(this)
                .setTitle(R.string.error)
                .setMessage(R.string.error_message)
                .show()
    }

    override fun displaySimulationOutput(numUniqueColours: Double) {
        binding.outputText.text = getString(R.string.results, String.format("%.2f", numUniqueColours))
    }
}