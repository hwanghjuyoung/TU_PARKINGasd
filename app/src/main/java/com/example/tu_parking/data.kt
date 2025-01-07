package com.example.tu_parking



data class data(
    // Parking
    val id :            Int,
    val spot_number :   String,
    val status :        Boolean,
    //val add_id :        String,

    // User
    val car_num :       String,
    //val spot_number :  String,

    // Parking_add
    //val add_id :        String,
    val adress :        String, // 주차장 이름

    // Log
    //val car_num :       String
    //val event_type :    String,
    val event_time :    String,
    //val sport_number:   String
    //val add_id :        String
)
