package com.wisefox.spaceodysseyapp.model

import java.io.Serializable

data class QuestionBean(
    val quest_id: Int,
    val quest_content: String,
    val quest_answer1: String,
    val quest_answer2: String,
    val quest_answer3: String?,
    val quest_answer4: String?,
    val quest_explanation: String,
    val lvl_id: Byte,
    val theme_id: Byte,
) : Serializable

data class LevelBean(
    val lvl_id: Byte,
    val lvl_name: String,
) : Serializable

data class ThemeBean(
    val theme_id: Byte,
    val theme_name: String,
) : Serializable

data class ResponseCodeBean<T>(
        val code: Int,
        val message: String?,
        val data: T? = null
)

data class ParamsBean(
        val level: LevelBean,
        val theme: ThemeBean,
)