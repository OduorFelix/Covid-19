package com.flx.covid_19.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flx.covid_19.R
import com.flx.covid_19.adapters.OnCountryItemClickListner
import com.flx.covid_19.adapters.RegionalAdapter
import com.flx.covid_19.api.RetrofitClient
import com.flx.covid_19.models.Countries
import com.flx.covid_19.response.CovidResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Regional : AppCompatActivity(), OnCountryItemClickListner {

    //Jobs Adapter
    private var countryAdapter: RegionalAdapter? = null
    private lateinit var recyclerView: RecyclerView
    private var countryData: List<Countries>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regional)

        //Recycler View
        recyclerView = findViewById(R.id.recycler_view_regional)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this@Regional, RecyclerView.HORIZONTAL, false)

        //Function to Get All Regional Data
        getAllRegionalData()
    }

    private fun getAllRegionalData() {
        val retIn = RetrofitClient.RetrofitInstance.getRetrofitInstance().create(RetrofitClient.ApiInterface::class.java)
        retIn.fetchData().enqueue(object : Callback<CovidResponse> {
            override fun onFailure(call: Call<CovidResponse>, t: Throwable) {
                Toast.makeText(this@Regional, "No Internet Connection, Please Try Again", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<CovidResponse>, response: Response<CovidResponse>) {
                if (response.code() == 200) {
                    countryData = response.body()?.data?.regions
                    val y = countryData!!.size.toString()
                    Toast.makeText(this@Regional, y, Toast.LENGTH_LONG).show()
                    //countryAdapter = RegionalAdapter(this.toString(), countryData!!, true, this@Regional)
                    //recyclerView.adapter = countryAdapter
                }
                else{
                    Toast.makeText(this@Regional, "No Data, Please Try Again", Toast.LENGTH_LONG).show()
                }
            }
        })

    }

    override fun onItemClick(items: Countries, position: Int) {
        Toast.makeText(this@Regional, "More Data Coming Soon!", Toast.LENGTH_LONG).show()
    }
}