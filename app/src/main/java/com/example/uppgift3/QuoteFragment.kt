package com.example.uppgift3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley


class QuoteFragment : Fragment(R.layout.fragment_quote) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        view.findViewById<Button>(R.id.btnGoToDetailsFromQuote).setOnClickListener {
            navController.navigate(R.id.action_quoteFragment_to_detailsFragment)
        }

        view.findViewById<Button>(R.id.btnBackToStartFromQuote).setOnClickListener {
            navController.navigate(R.id.action_quoteFragment_to_startFragment)
        }

        fetchQuoteData()
    }

    private fun fetchQuoteData() {
        val queue = Volley.newRequestQueue(requireContext())

        val url = "https://api.quotable.io/random"

        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val quote = response.getString("content")

                updateUI(quote)
            },
            { error ->
                Log.w("content", "error")
            })

        queue.add(jsonRequest)
    }

    private fun updateUI(quote: String) {
        view?.findViewById<TextView>(R.id.quoteName)?.text = quote
    }

}