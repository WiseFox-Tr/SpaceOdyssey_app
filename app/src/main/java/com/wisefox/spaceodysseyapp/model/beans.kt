package com.wisefox.spaceodysseyapp.model

data class QuestionBean(
    val quest_id: Long,
    val quest_content: String,
    val quest_answer1: String,
    val quest_answer2: String,
    val quest_answer3: String?,
    val quest_answer4: String?,
    val quest_explanation: String,
    val levelBean: LevelBean,
    val themeBean: ThemeBean,
)

data class ParamsBean(
    val levelBean: LevelBean,
    val themeBean: ThemeBean,
)

data class LevelBean(
    val lvl_id: Byte,
    val lvl_name: String,
)

data class ThemeBean(
    val theme_id: Byte,
    val theme_name: String,
)

data class ResponseCodeBean<T>(
    val code: Int,
    val message: String?,
    val data: T? = null
)