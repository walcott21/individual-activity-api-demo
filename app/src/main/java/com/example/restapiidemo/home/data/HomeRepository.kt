package com.example.restapiidemo.home.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository {

    private val apiInterface: ApiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)

    fun fetchAllPosts(): LiveData<List<PostModel>> {
        val data = MutableLiveData<List<PostModel>>()

        apiInterface.fetchAllPosts().enqueue(object : Callback<List<PostModel>> {
            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<List<PostModel>>, response: Response<List<PostModel>>) {
                if (response.isSuccessful) {
                    val res = response.body()
                    data.value = res
                } else {
                    data.value = null
                }
            }
        })

        return data
    }

    fun createPost(postModel: PostModel): LiveData<PostModel> {
        val data = MutableLiveData<PostModel>()

        apiInterface.createPost(postModel).enqueue(object : Callback<PostModel> {
            override fun onFailure(call: Call<PostModel>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<PostModel>, response: Response<PostModel>) {
                if (response.isSuccessful) {
                    val res = response.body()
                    data.value = res
                } else {
                    data.value = null
                }
            }
        })

        return data
    }

    fun deletePost(id: Int): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()

        apiInterface.deletePost(id).enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                data.value = false
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                data.value = response.isSuccessful
            }
        })

        return data
    }
}
