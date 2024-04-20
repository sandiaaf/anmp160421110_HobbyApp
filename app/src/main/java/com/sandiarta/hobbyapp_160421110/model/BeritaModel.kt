package com.sandiarta.hobbyapp_160421110.model

data class Berita(
    val id:Int,
    val name:String?,
    val title:String?,
    val subtitle:String?,
    val descriptions:List<String>,
    val image:String?,
    val created_at:String?,
)
