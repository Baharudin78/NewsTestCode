package com.baharudin.newstestcode.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baharudin.newstestcode.data.remote.model.NewsResponse
import com.baharudin.newstestcode.repository.NewsRepo
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import com.baharudin.newstestcode.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response

@HiltViewModel
class NewViewModel @Inject constructor(
    private val newsRepo: NewsRepo
) : ViewModel(){
    val getHealthCategory : MutableLiveData<Result<NewsResponse>> = MutableLiveData()
    private var categoryHealth = "health"

    init {
        getHealthCategory("id")
    }

    fun getHealthCategory(countryCode: String) = viewModelScope.launch {
        getHealthCategory.postValue(Result.Loading())
        val response = newsRepo.getHealthCategory(countryCode, categoryHealth)
        getHealthCategory.postValue(handleHealth(response))
    }
    private fun handleHealth(response: Response<NewsResponse>): Result<NewsResponse>? {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Result.Sucess(result)
            }
        }
        return Result.Error(response.message())
    }

}