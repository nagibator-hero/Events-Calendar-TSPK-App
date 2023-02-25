package com.appninjas.domain.usecase

import com.appninjas.domain.model.Event
import com.appninjas.domain.repository.EventsRepository

class SaveEventToStorageUseCase(private val repository: EventsRepository) {
    suspend fun invoke(event: Event) = repository.saveEventToStorage(event)
}