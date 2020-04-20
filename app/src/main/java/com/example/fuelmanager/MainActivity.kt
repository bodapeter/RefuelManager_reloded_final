package com.example.fuelmanager

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import com.example.fuelmanager.DataBaseHelper.DBHelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.opengl.Visibility
import android.view.Menu
import android.view.MenuItem

import android.view.View
import android.widget.*
import com.example.fuelmanager.Adapter.RefuelAdapter
import com.example.fuelmanager.Model.Refuel
import com.example.fuelmanager.R.id.layout_helpMenu
import kotlinx.android.synthetic.main.add_new_refuel_layout.*
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
        //up to date the database
        Refreshdata()


        //save it button
        val saveRecordInDB: Button = findViewById(R.id.btn_saveRecord)
        saveRecordInDB.setOnClickListener()
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

            findViewById<View>(R.id.addNewRefuelLayout).visibility = View.INVISIBLE
            findViewById<View>(R.id.showAllRefuel).visibility = View.VISIBLE
        }

        //drop it button
        val dropRecordFromBlank: Button = findViewById(R.id.btn_dropRecord)
        dropRecordFromBlank.setOnClickListener()
        {
            visibilityInit()
        }


        //delete record form database
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

        //update record in database
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
            showAllRefuelLayout()
        }


        //skip button
        val showYourRecords:ImageButton = findViewById(R.id.btn_new_refuel_layout)
        showYourRecords.setOnClickListener()
        {
            skipTheAddNewRefuelLayout()
        }

        val createDB:Button = findViewById(R.id.btn_createdb)
        createDB.setOnClickListener()
        {
            databaseHandler.createTable("Refuels")
        }

        //back button from show all layout
        val backToMain:Button = findViewById(R.id.btn_backToMain)
        backToMain.setOnClickListener()
        {
            visibilityInit()
        }

        val backToMainFromHelp:Button = findViewById(R.id.btn_backtoMainFromHelp)
        backToMainFromHelp.setOnClickListener()
        {
            visibilityInit()
        }
    }

    fun helpMenuVisibility()
    {
        findViewById<View>(R.id.helpmenu).visibility = View.VISIBLE
        findViewById<View>(R.id.addNewRefuelLayout).visibility = View.INVISIBLE
        findViewById<View>(R.id.mainLayout).visibility = View.INVISIBLE
        findViewById<View>(R.id.showAllRefuel).visibility = View.INVISIBLE
    }

    /*fun openNewTabWindow(urls: String, context : Context) {
        val uris = Uri.parse(urls)
        val intents = Intent(Intent.ACTION_VIEW, uris)
        val b = Bundle()
        b.putBoolean("new_window", true)
        intents.putExtras(b)
        context.startActivity(intents)
    }*/


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        var intent: Intent? = null
        return when (item?.itemId){
            R.id.menu_help -> {
               helpMenuVisibility()
                true
            }
            R.id.menu_holtankoljak_webPage -> {
                val urls:String = "https://holtankoljak.hu/arvaltozasok"
                val uris = Uri.parse(urls)
                intent = Intent(Intent.ACTION_VIEW,uris)
                startActivity(intent)

                true
            }
            else ->
            {
                /*val uri:Uri = Uri.parse("google.streetview:cbll=46.414382,10.013988")
                intent = Intent(Intent.ACTION_VIEW,uri)
                intent.setPackage()*/

                return super.onOptionsItemSelected(item)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       menuInflater.inflate(R.menu.manu_main,menu)
        return true
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
        findViewById<View>(R.id.addNewRefuelLayout).visibility = View.VISIBLE
        findViewById<View>(R.id.mainLayout).visibility = View.INVISIBLE
    }

     fun visibilityInit() {
         findViewById<View>(R.id.helpmenu).visibility = View.INVISIBLE
         findViewById<View>(R.id.addNewRefuelLayout).visibility = View.INVISIBLE
        findViewById<View>(R.id.mainLayout).visibility = View.VISIBLE
        findViewById<View>(R.id.showAllRefuel).visibility = View.INVISIBLE
    }

    fun showAllRefuelLayout()
    {
        findViewById<View>(R.id.mainLayout).visibility = View.INVISIBLE
        findViewById<View>(R.id.showAllRefuel).visibility = View.VISIBLE
    }


    fun showToast(message:String)
    {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}
