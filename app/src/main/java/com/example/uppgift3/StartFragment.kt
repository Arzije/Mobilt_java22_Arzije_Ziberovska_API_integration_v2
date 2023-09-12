package com.example.uppgift3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import androidx.navigation.fragment.findNavController

class StartFragment : Fragment(R.layout.fragment_start) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        view.findViewById<Button>(R.id.btnWeather).setOnClickListener {
            navController.navigate(R.id.action_startFragment_to_weatherFragment)
        }

        view.findViewById<Button>(R.id.btnQuote).setOnClickListener {
            navController.navigate(R.id.action_startFragment_to_quoteFragment)
        }
    }
}
