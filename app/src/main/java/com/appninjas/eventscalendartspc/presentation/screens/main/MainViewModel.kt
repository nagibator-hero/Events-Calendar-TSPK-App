package com.appninjas.eventscalendartspc.presentation.screens.main

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import com.appninjas.data.repository.EventRepositoryImpl
import com.appninjas.data.repository.UserRepositoryImplementation
import com.appninjas.domain.model.Event
import com.appninjas.domain.model.User
import com.appninjas.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): ViewModel() {

    private val appContext: Context = application.applicationContext

    private val userRepoImpl = UserRepositoryImplementation()
    private val eventsRepoImpl = EventRepositoryImpl(appContext)

    private val logoutUseCase = LogoutUseCase(userRepoImpl)
    private val getUserDataUseCase = GetUserDataUseCase(userRepoImpl)
    private val getEventsListUseCase = GetEventsListUseCase(eventsRepoImpl)
    private val saveEventToStorageUseCase = SaveEventToStorageUseCase(eventsRepoImpl)
    private val endEventUseCase = EndEventUseCase(eventsRepoImpl)

    private val _userData: MutableLiveData<User> = MutableLiveData()
    val userData: LiveData<User> = _userData

    private val _eventsList: MutableLiveData<Map<String, List<Event>>> = MutableLiveData()
    val eventsList: LiveData<Map<String, List<Event>>> = _eventsList

    fun logoutUser() {
        viewModelScope.launch(Dispatchers.IO) {
            logoutUseCase.invoke()
        }
    }

    fun getUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getUserDataUseCase.invoke()
            _userData.postValue(result)
        }
    }

    fun getEventsList() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getEventsListUseCase.invoke()
            _eventsList.postValue(result)
        }
    }

    fun addEventToStorage(event: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            saveEventToStorageUseCase.invoke(event)
        }
    }

    fun endEvent(event: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            endEventUseCase.invoke(event)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                return MainViewModel(application) as T
            }
        }
    }

}