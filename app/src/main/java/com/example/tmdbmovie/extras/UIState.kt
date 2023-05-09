package com.example.tmdbmovie.extras

data class UIState(
    val isloading: Boolean,
    val isSuccess: Boolean,
    val isError: Boolean,
    var errorText: String? = null
){
    companion object{
        fun loadingState(isInitial: Boolean = true) =UIState(isloading = true, isSuccess = !isInitial, isError = false)
        fun successState() = UIState(isloading = false, isSuccess = true, isError = false)
        fun errorState(isInitial: Boolean = true, errorText: String) = UIState(isloading = false,
            isSuccess = false, isError = true, errorText = errorText)
    }
}