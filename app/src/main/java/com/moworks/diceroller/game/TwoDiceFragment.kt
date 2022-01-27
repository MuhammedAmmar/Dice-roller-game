package com.moworks.diceroller.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.moworks.diceroller.R
import com.moworks.diceroller.animateUI
import com.moworks.diceroller.databinding.FragmentTwoDiceRollerBinding
import com.moworks.diceroller.getSideImage
import com.moworks.diceroller.textGenerator

class TwoDiceFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding :FragmentTwoDiceRollerBinding = DataBindingUtil.inflate(inflater ,
            R.layout.fragment_two_dice_roller,container ,false)

        val dice = listOf(binding.diceRoller1 , binding.diceRoller2)

        val viewModel =  ViewModelProvider(this).get(SharedViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        viewModel.diceRollSide.observe(viewLifecycleOwner , {  list ->
            binding.apply {
                repeat(list.size) {
                    dice[it].setImageResource(getSideImage(list[it]))
                }
                luckStatement.text = textGenerator(requireContext() , viewModel.totalScore.value?.div(list.size)!!)
                score.text = getString(R.string.score , viewModel.totalScore.value)
                animateUI(binding.luckStatement , anchorView = binding.diceRollerContainer , *dice.toTypedArray())
                invalidateAll()
            }

        })


        return binding.root
    }



}