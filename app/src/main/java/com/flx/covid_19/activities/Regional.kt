package com.flx.covid_19.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flx.covid_19.R
import com.flx.covid_19.adapters.OnCountryItemClickListner
import com.flx.covid_19.adapters.RegionalAdapter
import com.flx.covid_19.api.RetrofitClient
import com.flx.covid_19.models.Regions
import com.flx.covid_19.response.CovidResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Regional : AppCompatActivity(), OnCountryItemClickListner {

    //Jobs Adapter
    //private var countryAdapter: RegionalAdapter? = null
    private lateinit var recyclerView: RecyclerView
    private var countryData: MutableList<Regions> = mutableListOf<Regions>()
    //private var regions: List<Regions>? = null

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
                   //countryData = response.body()?.data?.regions?.values?.toMutableList()
                    countryData.add(
                        Regions("usa", null, null, null, "1","2","3","4","5","6",7.0,8.0, null)
                    )
                    countryData.add(
                        Regions("Kenya", null, null, null, "1","2","3","4","5","6",7.0,8.0, null)
                    )

                            val countryAdapter = RegionalAdapter(this.toString(), countryData!! , true, this@Regional)
                            recyclerView.adapter = countryAdapter
                           Toast.makeText(this@Regional, countryData!!.toString(), Toast.LENGTH_LONG).show()

                }
                else{
                    Toast.makeText(this@Regional, "No Data, Please Try Again", Toast.LENGTH_LONG).show()
                }
            }
        })

    }


    override fun onItemClick(items:  Regions, position: Int) {
        Toast.makeText(this@Regional, "More Data Coming Soon!", Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent (baseContext, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

}