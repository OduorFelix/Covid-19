package com.flx.covid_19.response

import com.flx.covid_19.models.Covid_model

data class CovidResponse (val status: String?,
                          val type: String?,
                          val data: Covid_model)