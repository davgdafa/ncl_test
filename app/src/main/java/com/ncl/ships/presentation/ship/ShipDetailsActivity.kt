package com.ncl.ships.presentation.ship

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.ncl.ships.databinding.ActivityShipDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShipDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShipDetailsBinding
    private val viewModel: ShipDetailsViewModel by viewModel()
    private lateinit var shipCode: String
    private lateinit var loader: ProgressBar

    companion object {
        private const val INTENT_EXTRA_SHIP_NAME = "ship_name"
        fun getShipDetailsActivityIntent(context: Context, shipCode: String) =
            Intent(context, ShipDetailsActivity::class.java).apply { putExtra(INTENT_EXTRA_SHIP_NAME, shipCode) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShipDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFunctionality()

        shipCode = intent.extras?.getString(INTENT_EXTRA_SHIP_NAME)
            ?: throw IllegalArgumentException("$INTENT_EXTRA_SHIP_NAME not passed")

        viewModel.onShipInformationLoaded.observe(this, Observer { (shipName, passengerCapacity, crew, inauguralDate, imagePathUrl) ->
            binding.tvShipName.text = shipName
            binding.tvShipPassengerCapacityText.text = passengerCapacity
            binding.tvShipCrewText.text = crew
            binding.tvShipInauguralDateText.text = inauguralDate
            imagePathUrl?.let {
                Glide.with(this)
                    .load(imagePathUrl)
                    .into(binding.ivCruiseShipImage)
            }
            loader.hide()
        })
        viewModel.onShipInformationNotLoaded.observe(this, Observer { error ->
            Toast.makeText(this, "There was an error processing your request, please try again later", Toast.LENGTH_SHORT).show()
            loader.hide()
            this.finish()
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadShipInformation(shipCode)
        loader.show()
    }

    private fun setFunctionality() {
        loader = binding.pbLoading
    }

    private fun ProgressBar.show() {
        visibility = View.VISIBLE
    }

    private fun ProgressBar.hide() {
        visibility = View.GONE
    }
}
