package com.sameer.lightbulbchallenge.pick

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
            presenter?.runSingleSimulation(4, 20, 4)
        }
    }

    override fun displaySingleSimOutput(numUniqueColours: Double) {
        binding.textview.text = numUniqueColours.toString()
    }
}