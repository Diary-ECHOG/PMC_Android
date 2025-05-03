package com.app.pmc.data.diary

import com.app.pmc.core.model.Diary
import com.app.pmc.data.model.DiaryListResponse

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
}