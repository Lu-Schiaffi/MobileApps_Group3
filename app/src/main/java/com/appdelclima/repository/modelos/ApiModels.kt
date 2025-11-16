package com.appdelclima.repository.modelos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@kotlinx.serialization.Serializable
data class GeoCityDto(val name: String, val country: String, val lat: Double, val lon: Double)

@kotlinx.serialization.Serializable
data class CurrentDto(val weather: List<WeatherDescDto> = emptyList(), val main: MainDto)

@kotlinx.serialization.Serializable
data class WeatherDescDto(val main: String, val description: String)

@kotlinx.serialization.Serializable
data class MainDto(val temp: Double, val humidity: Int)

@kotlinx.serialization.Serializable
data class ForecastDto(val list: List<ForecastItemDto> = emptyList())

@kotlinx.serialization.Serializable
data class ForecastItemDto(
    @SerialName("dt_txt") val dtTxt: String,
    val main: MainForecastDto,
    val weather: List<WeatherDescDto> = emptyList()
)

@Serializable
data class MainForecastDto(
    @SerialName("temp_min") val tempMin: Double,
    @SerialName("temp_max") val tempMax: Double,
    val humidity: Int
)