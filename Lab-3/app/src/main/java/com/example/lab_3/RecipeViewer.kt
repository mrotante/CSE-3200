package com.example.lab_3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecipeViewer(private val recipeArray : AllRecipes) :
    RecyclerView.Adapter<RecipeViewer.RecipeViewHolder>() {
    class RecipeViewHolder(private val itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val recipeTextHolder : TextView = itemView.findViewById(R.id.recipe_text)
        private val recipeImageHolder : ImageView = itemView.findViewById(R.id.recipe_image)

        init { itemView.setOnClickListener(this) }

        fun bindWord(word : String) {
            recipeTextHolder.text = word
        }

        fun bindImage(img : Int) {
            recipeImageHolder.setImageResource(img)
        }

        override fun onClick(p0: View?) {
            p0?.setOnClickListener {
                Toast.makeText(itemView.context, "Clicked " + recipeTextHolder.text.toString(),
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_layout, parent, false)
        return RecipeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recipeArray.listOfRecipes.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bindWord(recipeArray.listOfRecipes[position].recipeID)
        holder.bindImage(recipeArray.listOfRecipes[position].imageID)
    }

}
