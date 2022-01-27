package com.moworks.diceroller.game

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.moworks.diceroller.R
import com.moworks.diceroller.animateUI
import com.moworks.diceroller.databinding.FragmentOneDiceRollerBinding
import com.moworks.diceroller.getSideImage
import com.moworks.diceroller.textGenerator


class OneDiceFragment : Fragment() {


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentOneDiceRollerBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_one_dice_roller, container, false
        )



        val viewModel =  ViewModelProvider(this).get(SharedViewModel::class.java)


        binding.lifecycleOwner = this
        binding.viewModel = viewModel



        viewModel.diceRollSide.observe(viewLifecycleOwner , {  list ->
            binding.apply {
                repeat(list.size) {
                    diceRoller.setImageResource(getSideImage(list[it]))
                    luckStatement.text = textGenerator(requireContext(), list[it]/list.size)
                    score.text = getString(R.string.score , viewModel.totalScore.value)
                    animateUI(binding.luckStatement, binding.diceRoller, binding.diceRoller)
                }
                invalidateAll()
        }

        })

        return binding.root
    }

}
