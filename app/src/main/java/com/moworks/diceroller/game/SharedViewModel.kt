package com.moworks.diceroller.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moworks.diceroller.rollIt

class SharedViewModel :ViewModel(){

    private val _diceRollSide :MutableLiveData<List<Int>> = MutableLiveData()

    val diceRollSide : LiveData<List<Int>>
        get() = _diceRollSide





    private val _totalScore:MutableLiveData<Int> = MutableLiveData()

    val totalScore : LiveData<Int>
        get() = _totalScore



    fun rollTheDice(diceNum :Int ){
        val list = mutableListOf<Int>()
        _totalScore.value = 0
        repeat(diceNum) {
            val random = rollIt()
            list.add(random)
            _totalScore.value = _totalScore.value?.plus(random)
        }
        _diceRollSide.value = list
    }





}


