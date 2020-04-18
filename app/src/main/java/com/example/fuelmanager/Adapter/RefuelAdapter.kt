package com.example.fuelmanager.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.TextView
import com.example.fuelmanager.Model.Refuel
import com.example.fuelmanager.R
import kotlinx.android.synthetic.main.row_layout.view.*

class RefuelAdapter(private val activity:Activity,
                             private val refuelList: List<Refuel>,
                             private val edittextDatetime:EditText,
                             private val edittextKilometers:EditText,
                             private val edittextKilometerbetweenRefuel:EditText,
                             private val edittextFuelQuantity:EditText,
                             private val edittextPriceOfRefuel:EditText,
                             private val textViewRefuelID:TextView):BaseAdapter(){

    internal val inflater: LayoutInflater = activity.getSystemService( Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val rowView:View = inflater.inflate(R.layout.row_layout,null)


        rowView.txt_row_id.text = refuelList[position].getID().toString()
        rowView.txt_row_date_now.text = refuelList[position].getDateForRefulling().toString()
        rowView.txt_row_kilometers.text = refuelList[position].getKilometers().toString()
        rowView.txt_row_between2refuel.text = refuelList[position].getKilometerBetweenRefuel().toString()
        rowView.txt_row_fuel_quantity.text = refuelList[position].getFuelQuantity().toString()
        rowView.txt_price_of_refuel.text = refuelList[position].getPriceOfRefuel().toString()

        rowView.setOnClickListener{
            textViewRefuelID.setText(rowView.txt_row_id .text.toString())
            edittextDatetime.setText(rowView.txt_row_date_now.text.toString())
            edittextKilometers.setText(rowView.txt_row_kilometers.text.toString())
            edittextKilometerbetweenRefuel.setText(rowView.txt_row_between2refuel.text.toString())
            edittextFuelQuantity.setText(rowView.txt_row_fuel_quantity.text.toString())
            edittextPriceOfRefuel.setText(rowView.txt_row_fuel_quantity.text.toString())
        }
        return rowView
    }

    override fun getCount(): Int {
        return refuelList.size
    }

    override fun getItemId(position: Int): Long {
       return refuelList[position].getID().toLong()
    }

    override fun getItem(position: Int): Any {
        return refuelList[position]
    }
}

