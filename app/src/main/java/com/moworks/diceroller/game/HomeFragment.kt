package com.moworks.diceroller.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import androidx.navigation.findNavController
import com.moworks.diceroller.R
import com.moworks.diceroller.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
     val binding :FragmentHomeBinding = DataBindingUtil.inflate(inflater ,
         R.layout.fragment_home, container , false)

        binding.oneDice.setOnClickListener {
            view:View -> view.findNavController().navigate(R.id.action_home_to_oneDiceRoller)
        }


        binding.twoDices.setOnClickListener {
            view:View -> view.findNavController().navigate(R.id.action_home_to_twoDiceRoller)
        }

        binding.tripleDices.setOnClickListener {
            view:View -> view.findNavController().navigate(R.id.action_home_to_trippleDiceRoller)
        }

        return binding.root
    }


}