package com.example.k2023_01_19b

class Score(val _score : Int) {
    private var score : Int = _score

    public fun getScore() : Int {
        return score
    }

    public fun incrementScore(by: Int) : Int {
        score += by
        return score
    }

}