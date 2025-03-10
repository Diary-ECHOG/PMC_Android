package com.app.pmc.data

import com.app.pmc.core.model.Diary
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

class DiaryService {
    interface DiaryService {
        @POST("api/diary/write")
        suspend fun saveDiary(diary: Diary): Diary

        @GET("api/diary/view")
        suspend fun getDiaries(): List<Diary>

        @DELETE
        suspend fun deleteDiary(diary: Diary)
    }
}