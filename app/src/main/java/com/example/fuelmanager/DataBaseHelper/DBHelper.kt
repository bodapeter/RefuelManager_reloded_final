package com.example.fuelmanager.DataBaseHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.icu.text.TimeZoneFormat
import com.example.fuelmanager.Model.Refuel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.Month

import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar

import android.os.Build.ID
import java.sql.Ref


class DBHelper(context: Context):
    SQLiteOpenHelper(context,DBHelper.DB_NAME,null, DBHelper.DB_VERSION) {

    companion object {

        private val DB_VERSION = 1
        private val DB_NAME = "Refuel_Database"

        private val TABLE_NAME = "Refuels"
        private val REFUELID = "Id"
        private val DATETIME = "dateTime"
        private val KILOMETER = "kiloMeter"
        private val KILOMETERBETWEENREFUEL = "kilometerBetweenRefuel"
        private val FUELQUANTITIY = "fuelQuantity"
        private val PRICEOFREFUEL = "priceOfRefuel"

    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME (" +
                REFUELID + " INTEGER PRIMARY KEY," +
                DATETIME + " TEXT," +
                KILOMETER + " INTEGER," +
                KILOMETERBETWEENREFUEL + " INTEGER," +
                FUELQUANTITIY + " INTEGER" +
                PRICEOFREFUEL + " INTEGER);"

        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME
        db.execSQL(DROP_TABLE)
        onCreate(db)
    }


    fun createTable(name:String)
    {
        var db = this.writableDatabase
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME (" +
                REFUELID + " INTEGER PRIMARY KEY," +
                DATETIME + " TEXT," +
                KILOMETER + " INTEGER," +
                KILOMETERBETWEENREFUEL + " INTEGER," +
                FUELQUANTITIY + " INTEGER," +
                PRICEOFREFUEL + " INTEGER);"

                db.execSQL(CREATE_TABLE)
        db.close()
    }


    fun dropTable(name:String)
    {
        var db = this.writableDatabase
        var tableDrop = "DROP TABLE $name"
        db.execSQL(tableDrop)
        db.close()
    }

   fun getAllRefuel():List<Refuel> {


       var refuelsList = mutableListOf<Refuel>()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {

            while (cursor.moveToNext()) {

                var refuel:Refuel = Refuel("",0.0,0.0,0.0,0.0)


                    refuel.Id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(REFUELID)))
                    refuel.dateForRefuelling = cursor.getString(cursor.getColumnIndex(DATETIME))
                    refuel.kiloMeter = cursor.getDouble(cursor.getColumnIndex(KILOMETER))
                    refuel.kilometerbetweenRefuel = cursor.getDouble(
                        cursor.getColumnIndex(
                            KILOMETERBETWEENREFUEL))
                    refuel.fuelQuantity = cursor.getDouble(cursor.getColumnIndex(FUELQUANTITIY))
                    refuel.priceOfRefuel = cursor.getDouble(cursor.getColumnIndex(PRICEOFREFUEL))

                    refuelsList.add(refuel)
            }
        }
        return refuelsList
    }

    //CRUD

    //READ
   fun getRefuel(_id: Int):Refuel

    {
        val refuel:Refuel = Refuel("",0.0,0.0,0.0,0.0)
        val db= this.writableDatabase
        val SELECT_QUERY ="SELECT * FROM $TABLE_NAME WHERE $ID = _id"
        val cursor = db.rawQuery(SELECT_QUERY,null)
        if(cursor != null)
        {
            cursor.moveToFirst()

            while(cursor.moveToNext())
            {

                refuel.Id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(REFUELID)))
                refuel.dateForRefuelling = cursor.getString( cursor.getColumnIndex(DATETIME))
                refuel.kiloMeter = cursor.getDouble(cursor.getColumnIndex(KILOMETER))
                refuel.kilometerbetweenRefuel = cursor.getDouble(
                    cursor.getColumnIndex(
                        KILOMETERBETWEENREFUEL))
                refuel.fuelQuantity = cursor.getDouble(cursor.getColumnIndex(FUELQUANTITIY))
                refuel.priceOfRefuel = cursor.getDouble(cursor.getColumnIndex(PRICEOFREFUEL))

            }
        }
        db.close()
        return refuel
    }

    //CREATE
    fun addRefuel(refuel:Refuel): Boolean {

        val db = this.writableDatabase
        val values = ContentValues()

        values.put(DATETIME, refuel.dateForRefuelling)
        values.put(KILOMETER, refuel.kiloMeter)
        values.put(KILOMETERBETWEENREFUEL, refuel.kilometerbetweenRefuel)
        values.put(FUELQUANTITIY, refuel.fuelQuantity)
        values.put(PRICEOFREFUEL, refuel.priceOfRefuel)

        val _success = db.insert(TABLE_NAME, null, values)
        db.close()
        return (Integer.parseInt("$_success") != -1)
    }

    //UPDATE
    fun updateRefuel(refuel:Refuel): Boolean {
        val db = this.writableDatabase

        // Creating content values
        val values = ContentValues()
        values.put(DATETIME,refuel.dateForRefuelling )
        values.put(KILOMETER, refuel.kiloMeter)
        values.put(KILOMETERBETWEENREFUEL,refuel.kilometerbetweenRefuel )
        values.put(FUELQUANTITIY, refuel.fuelQuantity)
        values.put(PRICEOFREFUEL,refuel.priceOfRefuel )

        val _success = db.update(TABLE_NAME, values, "$REFUELID=?", arrayOf(refuel.getID().toString()))
        db.close()
        return (Integer.parseInt("$_success") != -1)

    }

    //DELETE
    fun delteRefuel(refuel:Refuel) {
        val db = this.writableDatabase
         db.delete(
        TABLE_NAME,"$REFUELID=?", arrayOf(refuel.Id.toString()))
        db.close()

    }
}

class Column(var name:String,var type:String)