package com.sandiarta.hobbyapp_160421110.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.sandiarta.hobbyapp_160421110.model.Account
import com.sandiarta.hobbyapp_160421110.model.Berita
import org.json.JSONObject

class AccountViewModel(application: Application): AndroidViewModel(application) {
    val navigatePage = MutableLiveData<Boolean>()
    val accountLD = MutableLiveData<Account>()
    var result = ""
    var id = 0
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun login(username: String, password: String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/hobby_news_sandi/login_account.php"

        val stringRequest = object : StringRequest(
            Method.POST, url,
            { response ->
                val resJson = JSONObject(response)

                if (resJson.getString("result") == "OK") {
                    val account = Gson().fromJson(resJson.getString("data"), Account::class.java)
                    accountLD.value = account
//                loadingLD.value = false

                    Log.d("showAccount", account.toString())
                    Log.d("showAccount", account.toString())
                    navigatePage.value = true
                } else {
                    navigatePage.value = false
                    Toast.makeText(getApplication(), "Username atau Password salah", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                Log.d("showAccount", error.toString())
//                accountLoadErrorLD.value = true
//                loadingLD.value = false
            }) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["username"] = username
                params["password"] = password
                return params
            }
        }

        stringRequest.tag = "loginRequest"
        queue?.add(stringRequest)
    }
    fun register(username:String, nama_depan:String,nama_belakang:String,email:String, password:String) {
        queue = Volley.newRequestQueue(getApplication())

        val url = "http://10.0.2.2/hobby_news_sandi/add_account.php"

        val stringRequest = object : StringRequest(
            Method.POST, url,
            { response ->
                val resJson = JSONObject(response)

                if (resJson != null) {
                    this.result = resJson.getString("result")
                }
                Log.d("showRegister", this.result)

                if (this.result == "OK") {
                    navigatePage.value = true
                } else {
                    navigatePage.value = false
                    Toast.makeText(getApplication<Application>(), "Gagal Menambahkan Account", Toast.LENGTH_SHORT).show()
                }
            },
            {
                Log.d("showRegister", it.toString())
            }) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["username"] = username
                params["nama_depan"] = nama_depan
                params["nama_belakang"] = nama_belakang
                params["email"] = email
                params["password"] = password
                return params
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    fun updateAccount(id:Int,username:String, nama_depan:String,nama_belakang:String,email:String, password:String) {
        queue = Volley.newRequestQueue(getApplication())

        val url = "http://10.0.2.2/hobby_news_sandi/update_account.php"

        val stringRequest = object : StringRequest(
            Method.POST, url,
            { response ->
                val resJson = JSONObject(response)

                if (resJson != null) {
                    this.result = resJson.getString("result")
                }
                Log.d("showRegister", this.result)

                if (this.result == "OK") {
                    Toast.makeText(getApplication<Application>(), "Berhasil Update Account", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(getApplication<Application>(), "Gagal Update Account", Toast.LENGTH_SHORT).show()
                }
            },
            {
                Log.d("showRegister", it.toString())
            }) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["id"] = id.toString()
                params["username"] = username
                params["nama_depan"] = nama_depan
                params["nama_belakang"] = nama_belakang
                params["email"] = email
                params["password"] = password
                return params
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}