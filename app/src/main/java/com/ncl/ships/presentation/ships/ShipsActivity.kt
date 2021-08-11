package com.ncl.ships.presentation.ships

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ncl.ships.databinding.ActivityShipsBinding
import com.ncl.ships.presentation.ship.ShipDetailsActivity.Companion.getShipDetailsActivityIntent
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShipsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShipsBinding
    private val viewModel: ShipsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShipsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFunctionality()
    }

    private fun setFunctionality() {
        binding.btnGoToSkyShipDetails.setOnClickListener {
            // todo get name dynamically, depending on button tapped
            startActivity(getShipDetailsActivityIntent(this@ShipsActivity, "SKY")) // todo could create enum to keep consistent, not global string
        }
        binding.btnGoToBlissShipDetails.setOnClickListener { startActivity(getShipDetailsActivityIntent(this@ShipsActivity, "BLISS")) }
        binding.btnGoToEscapeShipDetails.setOnClickListener { startActivity(getShipDetailsActivityIntent(this@ShipsActivity,  "ESCAPE")) }
    }
}
