package com.example.databasetest


import android.annotation.SuppressLint

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable

import androidx.recyclerview.widget.RecyclerView
import com.example.databasetest.databinding.NoteItemBinding
import com.example.databasetest.db.Entities.Hero
import com.example.databasetest.db.Entities.HeroWithFraction
import com.example.databasetest.viewmodel.SharedViewModel


class NoteAdapter(var viewModel : SharedViewModel) : RecyclerView.Adapter<NoteAdapter.NoteHolder>(), Filterable {
    private var heroes : List<HeroWithFraction> = mutableListOf()

    class NoteHolder : RecyclerView.ViewHolder {
         lateinit var binding : NoteItemBinding

        constructor( binding : NoteItemBinding) : super(binding.root) {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val current: HeroWithFraction = heroes[position]
        holder.binding.textViewTitle.text = current.hero.name
        holder.binding.textViewSubtitle.text = current.hero.description
        holder.binding.textViewId.text = current.hero.id.toString()
        holder.binding.textViewFraction.text = current.fraction.name
        holder.binding.deleteBtn.setOnClickListener {
            viewModel.deleteHero(getHeroAt(position))
        }


        holder.binding.editBtn.setOnClickListener{
            val intent = Intent(holder.itemView.context, EditHeroActivity::class.java)
            var hero = getHeroAt(position)
            intent.putExtra("id",hero.id)
            intent.putExtra("name",hero.name)
            intent.putExtra("description",hero.description)
            intent.putExtra("fractionId",hero.fraction_id)

            holder.itemView.context.startActivity(intent)
        }

    }





    @SuppressLint("NotifyDataSetChanged")
    public fun setHeroes(heroes: List<HeroWithFraction>)
    {
        this.heroes = heroes
        notifyDataSetChanged()
    }

    fun getHeroAt(position: Int) : Hero
    {
        return heroes[position].hero
    }


    override fun getItemCount(): Int {
        return heroes.size
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }
}