package com.example.fuelmanager.Model

import java.io.Serializable
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.round
import kotlin.math.roundToInt


class Refuel(

    var dateForRefuelling:String,
    var kiloMeter:Double,
    var kilometerbetweenRefuel:Double,
    var fuelQuantity:Double,
    var priceOfRefuel:Double,
    var Id:Int = 0):Serializable

{

    fun getID():Int
    {
        return Id
    }

    fun getDateForRefulling():String
    {
        return dateForRefuelling.toString()
    }

    fun getKilometers():Int
    {
        return kiloMeter.toInt()
    }

    fun getKilometerBetweenRefuel():Int
    {
       return kilometerbetweenRefuel.toInt()
    }

    fun getFuelQuantity():Int
    {
        return fuelQuantity.toInt()
    }

    fun getPriceOfRefuel():Int
    {
        return priceOfRefuel.toInt()
    }


    fun getavarageConsuption():Double
    {
        return round( fuelQuantity/kilometerbetweenRefuel*100)
    }



    override fun toString(): String {
        return "Date for refueling: $dateForRefuelling \n" +
                "Kilometers in car: $kiloMeter KM\n" +
                "Kilometers in car between two refuel: $kilometerbetweenRefuel KM\n" +
                "Quantity of fuel:  $fuelQuantity LITER\n" +
                "Price of refueling: $priceOfRefuel FT\n" +
                "Consuption: ${getavarageConsuption()} LITER\n"
    }

}