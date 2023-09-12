package com.example.uppgift3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController

import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser

class WeatherFragment : Fragment(R.layout.fragment_weather) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        view.findViewById<Button>(R.id.btnGoToDetails).setOnClickListener {
            navController.navigate(R.id.action_weatherFragment_to_detailsFragment)
        }

        view.findViewById<Button>(R.id.btnBackToStartFromWeather).setOnClickListener {
            navController.navigate(R.id.action_weatherFragment_to_startFragment)
        }

        fetchWeatherData()

    }

    private fun fetchWeatherData() {
        val queue = Volley.newRequestQueue(requireContext())

        val apiKey = "785bb11c17b230ef015b1fd008762e2a"
        val url = "https://api.openweathermap.org/data/2.5/weather?q=Malmo&appid=${apiKey}"

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                Log.d("weather", "Response: $response")

                val parser = JSONParser()
                val jsonObject = parser.parse(response) as JSONObject

                val cityName = jsonObject["name"] as String
                val weatherArray = jsonObject["weather"] as JSONArray
                val weatherDescription = if (weatherArray.size > 0) (weatherArray[0] as JSONObject)["description"] as String else "N/A"

                val mainObject = jsonObject["main"] as JSONObject
                val temperature = mainObject["temp"] as Double

                val temperatureInCelsius = temperature - 273.15

                updateUI(cityName, temperatureInCelsius, weatherDescription)
            },
            { error ->
                Log.w("weather", "error: {$error}")
            })

        queue.add(stringRequest)
    }


    private fun updateUI(city: String, temp: Double, description: String) {
        view?.findViewById<TextView>(R.id.tvCityName)?.text = city
        view?.findViewById<TextView>(R.id.tvTemperature)?.text = String.format("%.1f°C", temp)
        view?.findViewById<TextView>(R.id.tvWeatherDescription)?.text = description
    }

}
