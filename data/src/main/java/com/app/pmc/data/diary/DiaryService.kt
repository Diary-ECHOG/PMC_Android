package com.app.pmc.data.diary

import com.app.pmc.core.model.Diary
import com.app.pmc.data.model.BaseResponse
import com.app.pmc.data.model.DiaryListResponse
import com.app.pmc.data.model.DiaryRequest
import com.app.pmc.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface DiaryService {
    @POST("api/diary/write")
    suspend fun saveDiary(@Body request: DiaryRequest): BaseResponse<DiaryRequest>

    @GET("api/diary/view")
    suspend fun getDiaries(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("sort") sort: String
    ): BaseResponse<DiaryListResponse>

    @DELETE("api/diary/delete/{id}")
    suspend fun deleteDiary(@Path("id") diaryId: String): Unit
}
