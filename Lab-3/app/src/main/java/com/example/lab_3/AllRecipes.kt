package com.example.lab_3

import android.content.Context
import android.provider.Settings.Global.getString

class AllRecipes(context : Context?) {
    public val listOfRecipes = arrayOf(
        Recipe(context?.resources!!.getString(R.string.r1), R.drawable.lemon_chicken),
        Recipe(context?.resources!!.getString(R.string.r2), R.drawable.caprese_shell),
        Recipe(context?.resources!!.getString(R.string.r3), R.drawable.penne_alla_vodka),
        Recipe(context?.resources!!.getString(R.string.r4), R.drawable.grilled_tuna),
        Recipe(context?.resources!!.getString(R.string.r5), R.drawable.pupusa),
        Recipe(context?.resources!!.getString(R.string.r6), R.drawable.shrimp_dumplings),
        Recipe(context?.resources!!.getString(R.string.r7), R.drawable.grilled_radicchio_salad),
        Recipe(context?.resources!!.getString(R.string.r8), R.drawable.mixed_green),
        Recipe(context?.resources!!.getString(R.string.r9), R.drawable.pepp_pizza),
        Recipe(context?.resources!!.getString(R.string.r10), R.drawable.sausage_and_peppers),
    );
}