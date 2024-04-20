package com.sandiarta.hobbyapp_160421110.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.sandiarta.hobbyapp_160421110.model.Berita

class DetailBeritaViewModel (application: Application): AndroidViewModel(application){
    val beritaLD = MutableLiveData<Berita>()
//    val beritaLoadErrorLD = MutableLiveData<Boolean>()
//    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(id:Int) {
        queue = Volley.newRequestQueue(getApplication())

        Log.d("test",id.toString())
        val url = "http://10.0.2.2/hobby_news_sandi/berita.php?id=${id}"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val berita = Gson().fromJson(response, Berita::class.java)
                beritaLD.value = berita
//                loadingLD.value = false

                Log.d("showvoley", berita.toString())
            },
            {
                Log.d("showvoley", it.toString())
//                studentLoadErrorLD.value = false
//                loadingLD.value = false
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}