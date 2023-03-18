package com.example.databasetest

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.databasetest.databinding.ActivityAddHeroBinding
import com.example.databasetest.db.Entities.Fraction
import com.example.databasetest.db.Entities.Hero
import com.example.databasetest.db.Entities.HeroWithFraction
import com.example.databasetest.viewmodel.SharedViewModel


class AddHeroActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddHeroBinding
    lateinit var viewModel: SharedViewModel
    private lateinit var selectedFractionItem : Fraction
    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[SharedViewModel::class.java]
        binding = ActivityAddHeroBinding.inflate(layoutInflater)
        var list = listOf<Fraction>()
            setContentView(binding.root)
        viewModel.getAllFractions().observe(this) {
            binding.spinner.adapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, it)
        }

        super.onCreate(savedInstanceState)



        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            )
            {
                selectedFractionItem = viewModel.getAllFractions().value?.get(position)!!
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_delete)

        title = "Add hero"

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(com.example.databasetest.R.menu.add_item_menu, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            com.example.databasetest.R.id.save -> { saveHero()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveHero()
    {
        var name = binding.etName.text.toString()
        var description = binding.etDescription.text.toString()
        var fractionID = selectedFractionItem.id
        if (name.trim().isEmpty() || description.trim().isEmpty())
        {
            Toast.makeText(this, "Please insert name and description", Toast.LENGTH_SHORT).show()
            return
        }
        viewModel.insertHero(Hero(0, name, 200,200,description,fractionID))
        Toast.makeText(this, "Hero added", Toast.LENGTH_SHORT).show()
        finish()
    }



}