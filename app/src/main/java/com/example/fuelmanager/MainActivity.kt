package com.example.fuelmanager

import com.example.fuelmanager.DataBaseHelper.DBHelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.database.sqlite.SQLiteDatabase
import android.opengl.Visibility

import android.view.View
import android.widget.*
import com.example.fuelmanager.Adapter.RefuelAdapter
import com.example.fuelmanager.Model.Refuel
import kotlinx.android.synthetic.main.add_new_refuel_layout.*
import kotlinx.android.synthetic.main.main_layout.*
import kotlinx.android.synthetic.main.row_layout.*
import kotlinx.android.synthetic.main.show_all_layout.*
import java.lang.Exception

import java.time.LocalDateTime




class MainActivity : AppCompatActivity() {

    val databaseHandler : DBHelper = DBHelper(this)

    lateinit var lstRefuel:List<Refuel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lstRefuel=databaseHandler.getAllRefuel()
        setContentView(R.layout.activity_main)

        //first visitibility
        visibilityInit()


        Refreshdata()


        //add button
        //val addButton: Button = findViewById(R.id.btn_add)
        btn_add.setOnClickListener()
        {


            val refuel = Refuel(
                edit_Date.text.toString(),
                edit_KilometerClock.text.toString().toDouble(),
                edit_KilometersBetweenFillIngUpFuelTank.text.toString().toDouble(),
                edit_fuelQuantity.text.toString().toDouble(),
                edit_fuelprice.text.toString().toDouble()
            )
            databaseHandler.addRefuel(refuel)
            Refreshdata()


            findViewById<View>(R.id.includes_layout_addNewRefuelLayout).visibility = View.INVISIBLE
            findViewById<View>(R.id.incluides_layout_showAllRefuel).visibility = View.VISIBLE
        }

        //delete button
        val deleteButton: Button = findViewById(R.id.btn_Delete)

        deleteButton.setOnClickListener {
            val refuel = Refuel(
                showlayout_edit_date.text.toString(),
                showlayout_edit_kilometer.text.toString().toDouble(),
                showlayout_edit_2refuel.text.toString().toDouble(),
                showlayout_edit_fuelquantity.text.toString().toDouble(),
                showlayout_edit_pricerefuel.text.toString().toDouble(),
                Integer.parseInt(showlayout_Id.text.toString())
            )
            databaseHandler.delteRefuel(refuel)
            Refreshdata()
        }

        //update button
        val updateButton: Button = findViewById(R.id.btn_Update)

        updateButton.setOnClickListener {
            val refuel = Refuel(
                showlayout_edit_date.text.toString(),
                showlayout_edit_kilometer.text.toString().toDouble(),
                showlayout_edit_2refuel.text.toString().toDouble(),
                showlayout_edit_fuelquantity.text.toString().toDouble(),
                showlayout_edit_pricerefuel.text.toString().toDouble(),
                Integer.parseInt(showlayout_Id.text.toString())
            )
            showToast( databaseHandler.updateRefuel(refuel).toString())
            Refreshdata()
        }

        //Show all refuel
        val showAllRefuelButton:Button = findViewById(R.id.btn_showAll)
        showAllRefuelButton.setOnClickListener()
        {
            val refuels = databaseHandler.getAllRefuel()
            val allRefuelListView:ListView = findViewById(R.id.listViewShowAllRefuel)
            //allRefuelListView.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,refuels)
            findViewById<View>(R.id.includes_starter_layout).visibility = View.INVISIBLE

            findViewById<View>(R.id.incluides_layout_showAllRefuel).visibility = View.VISIBLE
        }


        //skip button
        imageB_new_record.setOnClickListener()
        {
            skipTheAddNewRefuelLayout()
        }

        //Create DB
        btn_createDb.setOnClickListener()
        {
            databaseHandler.createTable("Refuels")
        }

        //back button from show all layout
        val backToMain:Button = findViewById(R.id.btn_backToMain)
        backToMain.setOnClickListener()
        {
            visibilityInit()
        }

        //back button from add layout
        val backToMainFromShowLayout:Button = findViewById(R.id.btn_backToMainFromTheAddLayout)
        backToMainFromShowLayout.setOnClickListener()
        {
            visibilityInit()
        }

        //next to new refuel layout button
       fun skipTheAddNewRefuelLayout()
        {
            findViewById<View>(R.id.includes_layout_addNewRefuelLayout).visibility = View.VISIBLE
            findViewById<View>(R.id.includes_starter_layout).visibility = View.INVISIBLE
        }

    }

    fun Refreshdata() {
        lstRefuel = databaseHandler.getAllRefuel()
        val adapter = RefuelAdapter(this@MainActivity,lstRefuel,
            showlayout_edit_date,showlayout_edit_kilometer,showlayout_edit_2refuel,showlayout_edit_fuelquantity,showlayout_edit_pricerefuel,showlayout_Id)
        listViewShowAllRefuel.adapter = adapter
    }

    fun dropTable(view:View)
    {
        databaseHandler.dropTable("Refuels")
    }

    fun createTable(view:View)
    {
        databaseHandler.createTable("Refuels")
    }

     fun skipTheAddNewRefuelLayout() {
        findViewById<View>(R.id.includes_layout_addNewRefuelLayout).visibility = View.VISIBLE
        findViewById<View>(R.id.includes_starter_layout).visibility = View.INVISIBLE
    }

     fun visibilityInit() {
        findViewById<View>(R.id.includes_layout_addNewRefuelLayout).visibility = View.INVISIBLE
        findViewById<View>(R.id.includes_starter_layout).visibility = View.VISIBLE
        findViewById<View>(R.id.incluides_layout_showAllRefuel).visibility = View.INVISIBLE
    }

    fun showAllRefuelLayout()
    {
        findViewById<View>(R.id.includes_starter_layout).visibility = View.INVISIBLE
        findViewById<View>(R.id.incluides_layout_showAllRefuel).visibility = View.VISIBLE
    }

    fun backToMainLayout()
    {
        findViewById<View>(R.id.includes_layout_addNewRefuelLayout).visibility = View.VISIBLE
        findViewById<View>(R.id.incluides_layout_showAllRefuel).visibility = View.INVISIBLE
    }

    fun showToast(message:String)
    {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}