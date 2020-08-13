package com.flx.covid_19.models

data class Summary (val total_cases: String?,
                    val active_cases: String?,
                    val deaths: String?,
                    val recovered: String?,
                    val critical: String?,
                    val tested: String?,
                    val death_ratio: String?,
                    val recovery_ratio: String?)