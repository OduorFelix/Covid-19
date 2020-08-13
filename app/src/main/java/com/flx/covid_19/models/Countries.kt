package com.flx.covid_19.models

data class Countries (val name: String,
                      val iso3166a2: String,
                      val iso3166a3: String,
                      val iso3166numeric: String,
                      val total_cases: String,
                      val active_cases: String,
                      val deaths: String,
                      val recovered: String,
                      val critical: String,
                      val tested: String,
                      val death_ratio: String,
                      val recovery_ratio: String,
                      val change: Change)