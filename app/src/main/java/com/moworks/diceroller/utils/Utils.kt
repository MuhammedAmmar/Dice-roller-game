package com.moworks.diceroller

import android.animation.*
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.util.*


fun rollIt() = Random().nextInt(6) + 1



fun getSideImage(diceRollerSide : Int ) :  Int {
    return when(diceRollerSide){
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

}


fun textGenerator(context: Context , diceRollerSide :Int) : String{
    return when(diceRollerSide){
        1 -> context.getString(R.string.luck_at_1)
        2 -> context.getString(R.string.luck_at_2)
        3 -> context.getString(R.string.luck_at_3)
        4 -> context.getString(R.string.luck_at_4)
        5 -> context.getString(R.string.luck_at_5)
        else-> context.getString(R.string.luck_at_6)
    }
}



private fun Animator.disableViewDuringAnimation(view : View){

    addListener(object : AnimatorListenerAdapter(){
        override fun onAnimationStart(animation: Animator?) {
            view.isEnabled = false
        }

        override fun onAnimationEnd(animation: Animator?) {
            view.isEnabled = true
        }
    })
}


fun animateUI(textView: TextView, anchorView :View , vararg imageView: ImageView) {
    /// Dice animation
    for (image in imageView) {
        val imageRotator =
            ObjectAnimator.ofFloat(image, View.ROTATION, -720f, -360F, 0f).apply {
                repeatCount = 1
            }
        val imageMover = ObjectAnimator.ofFloat(image, View.TRANSLATION_Y, -200f).apply {
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
        }

        val set = AnimatorSet()
        set.playTogether(imageRotator, imageMover)
        set.disableViewDuringAnimation(anchorView)
        set.duration = 500
        set.start()

        // text animation
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 2f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 2f)
        val textAnimator = ObjectAnimator.ofPropertyValuesHolder(textView, scaleX, scaleY)
        textAnimator.duration = 500
        textAnimator.repeatCount = 1
        textAnimator.repeatMode = ValueAnimator.REVERSE
        textAnimator.start()
    }



}


