package com.wisefox.spaceodysseyapp.model

import java.io.Serializable

class Quiz (
        val questions: List<QuestionBean>,
        val nbQuestions: Int = 10,
        var nbGoodAnswer: Int = 0,
        var score: Int = 0,
        val time : Double = 10.0
        ) : Serializable