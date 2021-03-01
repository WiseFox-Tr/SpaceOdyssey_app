package com.wisefox.spaceodysseyapp.model

import java.io.Serializable

data class Question(
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

data class Level(
    val lvl_id: Byte,
    val lvl_name: String,
) : Serializable

data class Theme(
    val theme_id: Byte,
    val theme_name: String,
) : Serializable

data class Params(
    val levels: List<Level>,
    val themes: List<Theme>,
) : Serializable

data class ServerResponse<T>(
        val code: Int,
        val message: String?,
        val data: T? = null
)
