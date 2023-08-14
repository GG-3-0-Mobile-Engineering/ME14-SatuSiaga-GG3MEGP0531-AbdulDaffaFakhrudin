package com.abduladf.satusiaga.utils

object SearchItemsUtil {

    val searchItemsMap = mapOf(
        "Aceh" to "ID-AC",
        "Bali" to "ID-BA",
        "Kep Bangka Belitung" to "ID-BB",
        "Banten" to "ID-BT",
        "Bengkulu" to "ID-BE",
        "Jawa Tengah" to "ID-JT",
        "Kalimantan Tengah" to "ID-KT",
        "Sulawesi Tengah" to "ID-ST",
        "Jawa Timur" to "ID-JI",
        "Kalimantan Timur" to "ID-KI",
        "Nusa Tenggara Timur" to "ID-NT",
        "Gorontalo" to "ID-GO",
        "DKI Jakarta" to "ID-JK",
        "Jambi" to "ID-JA",
        "Lampung" to "ID-LA",
        "Maluku" to "ID-MA",
        "Kalimantan Utara" to "ID-KU",
        "Maluku Utara" to "ID-MU",
        "Sulawesi Utara" to "ID-SA",
        "Sumatera Utara" to "ID-SU",
        "Papua" to "ID-PA",
        "Riau" to "ID-RI",
        "Kepulauan Riau" to "ID-KR",
        "Sulawesi Tenggara" to "ID-SG",
        "Sulawesi Selatan" to "ID-SN",
        "Sumatera Selatan" to "ID-SS",
        "DI Yogyakarta" to "ID-YO",
        "Jawa Barat" to "ID-JB",
        "Kalimantan Barat" to "ID-KB",
        "Nusa Tenggara Barat" to "ID-NB",
        "Papua Barat" to "ID-PB",
        "Sulawesi Barat" to "ID-SR",
        "Sumatera Barat" to "ID-SB",
    )

    fun getKeyBySearchItemValue(value: String?): String? {
        for ((key, value1) in searchItemsMap) {
            if (value1 == value) {
                return key
            }
        }
        return null
    }

}