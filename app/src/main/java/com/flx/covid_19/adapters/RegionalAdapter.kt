package com.flx.covid_19.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.flx.covid_19.R
import com.flx.covid_19.models.Countries

class RegionalAdapter (
    private var mContext: String,
    private var countryData: List<Countries>,
    private var isFragment: Boolean = false, private var clickListner: OnCountryItemClickListner) : RecyclerView.Adapter<RegionalAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.regional_country_card_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = countryData[position]
        holder.initialize(data, clickListner)
    }

    class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val countryName: TextView = itemView.findViewById(R.id.countryNameText)
        private val currentTotalCases: TextView = itemView.findViewById(R.id.currentTotalCases)
        private val changeTotalCases: TextView = itemView.findViewById(R.id.changeTotalCases)
        private val currentActiveCasesText: TextView = itemView.findViewById(R.id.currentActiveCasesText)
        private val changeActiveCasesText: TextView = itemView.findViewById(R.id.changeActiveCasesText)
        private val deathActiveCasesText: TextView = itemView.findViewById(R.id.deathActiveCasesText)
        private val deathChangeCasesText: TextView = itemView.findViewById(R.id.deathChangeCasesText)
        private val currentRecoveredCasesText: TextView = itemView.findViewById(R.id.currentRecoveredCasesText)
        private val changeRecoveredCasesText: TextView = itemView.findViewById(R.id.changeRecoveredCasesText)
        private val currentTestedCasesText: TextView = itemView.findViewById(R.id.currentTestedCasesText)
        private val changeTestedCasesText: TextView = itemView.findViewById(R.id.changeTestedCasesText)

        fun initialize(items: Countries, action: OnCountryItemClickListner){
            countryName.text = items.name
            currentTotalCases.text = items.total_cases
            changeTotalCases.text = "+0"
            currentActiveCasesText.text = items.active_cases
            changeActiveCasesText.text = "+0"
            deathActiveCasesText.text = items.deaths
            deathChangeCasesText.text = "+0"
            currentRecoveredCasesText.text = items.recovered
            changeRecoveredCasesText.text = "+0"
            currentTestedCasesText.text = items.tested
            changeTestedCasesText.text = "+0"

            itemView.setOnClickListener {
                action.onItemClick(items, adapterPosition)
            }

        }

    }

}

interface OnCountryItemClickListner{
    fun onItemClick(items: Countries, position: Int)
}