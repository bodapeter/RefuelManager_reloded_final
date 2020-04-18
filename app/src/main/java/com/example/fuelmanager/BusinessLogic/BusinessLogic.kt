package com.example.fuelmanager.BusinessLogic

import com.example.fuelmanager.Model.Refuel
import java.math.RoundingMode
import com.example.fuelmanager.DataBaseHelper.DBHelper

/*class BusinessLogic {

   lateinit var allRefuels: List<Refuel>

    fun loadREfuelsInMain(getRefuels:List<Refuel>)
    {
        allRefuels =  getRefuels
    }


    //fogyasztás kiszámítása
    fun AvarageFuel( fueled:Double,  kilometer:Double):Double
    {

        var roundnumber =   (fueled/kilometer)*100

        var roundednumber = roundnumber.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()

        return roundednumber
    }


    // mennyit kültöttünk benzinre eddig
    fun SumFuelPrice( ):Int{
        var sum:Int = 0

        for (refuel in allRefuels)
        {
            sum += refuel.priceOfRefuel
        }
        return sum
    }

    //mennyit tankoltunk öszesen eddig a kocsiba
    fun SumFuelQuantity():Double{
        var sum:Double = 0.0

        for (refuel in allRefuels)
        {
            sum += refuel.fuelQuantity!!
        }
        return sum
    }

   /* //kiiratás txt-be
    fun SaveOut()
    {
        val myFile = File("saves.txt")
        myFile.delete()
        for (fuel in allRefuels)
        {
            myFile.appendText(fuel.toSaveFormat())
        }
    }

    fun LoadIn()
    {
        val refuels:List<String> = File("saves.txt").readLines()
        var carTempList = mutableListOf<Refuel>()
        var splittedRefuel:List<String>
        for (refuelsor in refuels)
        {
            splittedRefuel = refuelsor.split(",")
            carTempList.add(Refuel(splittedRefuel[0],splittedRefuel[1].toDouble(),splittedRefuel[2].toDouble(),splittedRefuel[3].toInt()))
        }
        allRefuels=carTempList
    }

    override fun toString(): String {
        var refuels: String = ""
        for (refuel in allRefuels) {
            refuels += refuel.toString() + "\n"
        }
        return refuels
    }*/
}*/