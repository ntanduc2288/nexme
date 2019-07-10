package com.nexme.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel(){
    val progressDialogLiveData: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val alertLiveData: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val backLiveData: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val toastLiveData: MutableLiveData<String> by lazy { MutableLiveData<String>() }


    protected fun showProgressDialog(){
        progressDialogLiveData.postValue(true)
    }

    protected fun hideProgressDialog() {
        progressDialogLiveData.postValue(false)
    }

    protected fun showAlert(message: String) {
        alertLiveData.postValue(message)
    }

    protected fun backToPreviousScreen(){
        backLiveData.postValue("")
    }

    protected fun showToast(message: String) {
        toastLiveData.postValue(message)
    }
}