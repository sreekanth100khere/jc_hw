package com.android.activities

import java.io.Serializable

class RegionalBloc:Serializable {
    var acronym: String? = null
    var name: String? = null
    var otherAcronyms: List<String>? = null
    var otherNames: List<String>? = null
}