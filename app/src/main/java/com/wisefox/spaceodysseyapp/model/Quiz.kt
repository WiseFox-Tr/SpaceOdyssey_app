package com.wisefox.spaceodysseyapp.model

import java.io.Serializable

class Quiz (
        val questions: List<Question>,
        val params: Params,
        val nbQuestions: Int = questions.size,
        var nbGoodAnswer: Int = 0,
        var score: Int = 0,
        val time : Int = 10
        ) : Serializable {
                fun incrementNbGoodAnswers() {
                        this.nbGoodAnswer++
                }

                fun updateScore(score: Int) {
                        this.score += score
                }

                override fun toString(): String {
                        val description = "This quiz contain ${this.nbQuestions} questions.It's response duration is ${this.time} sec.\n" +
                                "The current score is ${this.score} for ${this.nbGoodAnswer} good answers.\n"
                        var questionDescription = "This questions are : \n\n"

                        for((index, question) in this.questions.withIndex()) {
                                questionDescription += "question n°${index} : $question... \n"
                        }
                        return  description + questionDescription
                }
        }