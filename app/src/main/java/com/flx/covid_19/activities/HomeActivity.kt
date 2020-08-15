package com.flx.covid_19.activities

import android.content.Intent
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.flx.covid_19.R
import com.flx.covid_19.api.RetrofitClient
import com.flx.covid_19.models.Change
import com.flx.covid_19.models.Summary
import com.flx.covid_19.response.CovidResponse
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initToolbar()
        getAllData()

        //View Regional Data
        viewCountryStat.setOnClickListener {
            val intent = Intent (baseContext, Regional::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }

    private fun getAllData() {
        val retIn = RetrofitClient.RetrofitInstance.getRetrofitInstance().create(RetrofitClient.ApiInterface::class.java)
        retIn.fetchData().enqueue(object : Callback<CovidResponse>{
            override fun onFailure(call: Call<CovidResponse>, t: Throwable) {
                Toast.makeText(this@HomeActivity, t.message, Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<CovidResponse>, response: Response<CovidResponse>) {
                if (response.code() == 200) {
                    val changeData = response.body()?.data?.change as Change
                    val currentData = response.body()?.data?.summary as Summary

                    //Passing Data to UI Components
                    totalCasesText.text = NumberFormat.getNumberInstance().format(currentData.total_cases?.toInt())
                    activeCasesText.text = NumberFormat.getNumberInstance().format(currentData.active_cases?.toInt())
                    criticalCasesText.text = NumberFormat.getNumberInstance().format(currentData.critical?.toInt())
                    deathCasesText.text = NumberFormat.getNumberInstance().format(currentData.deaths?.toInt())
                    testedCasesText.text = NumberFormat.getNumberInstance().format(currentData.tested?.toInt())
                    recoveredCasesText.text = NumberFormat.getNumberInstance().format(currentData.recovered?.toInt())

                    //Pass Data to Change Activity UI
                    changeTotalCases.text = NumberFormat.getNumberInstance().format(changeData.total_cases?.toInt())
                    changeActiveCases.text = NumberFormat.getNumberInstance().format(changeData.active_cases?.toInt())
                    changeDeathCases.text = NumberFormat.getNumberInstance().format(changeData.deaths?.toInt())
                    changeRecoveredCases.text = NumberFormat.getNumberInstance().format(changeData.recovered?.toInt())

                }
                else{
                    Toast.makeText(this@HomeActivity, "No Data, Please Try Again", Toast.LENGTH_LONG).show()
                }
            }
        })

    }

    private fun initToolbar() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.setNavigationIcon(R.drawable.ic_menu)
        toolbar.navigationIcon!!.setColorFilter(resources.getColor(R.color.grey_60), PorterDuff.Mode.SRC_ATOP)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search_setting, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else {
            Toast.makeText(applicationContext, item.title, Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}