package com.app.pmc.data

import com.app.pmc.core.model.Diary
import retrofit2.http.GET

class DiaryService {
    interface DiaryService {
        @GET("diary")
        suspend fun getDiaryList(): List<Diary>
    }
}