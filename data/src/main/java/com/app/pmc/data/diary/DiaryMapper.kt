package com.app.pmc.data.diary

import com.app.pmc.core.model.Diary
import com.app.pmc.data.model.DiaryListResponse
import com.app.pmc.data.model.DiaryResponse

object DiaryMapper {
    fun DiaryListResponse.toDomain(): List<Diary> {
        return this.content.map { diary ->
            Diary(
                id = diary.id,
                title = diary.title,
                content = diary.content,
                date = diary.createdAt
            )
        }
    }

    fun DiaryResponse.toDomain(): Diary {
        return Diary(
            id = this.id,
            title = this.title,
            content = this.content,
            date = this.createdAt
        )
    }
}