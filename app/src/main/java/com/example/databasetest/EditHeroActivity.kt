package com.example.databasetest

import android.R
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.databasetest.databinding.ActivityEditHeroBinding
import com.example.databasetest.db.Entities.Fraction
import com.example.databasetest.db.Entities.Hero
import com.example.databasetest.viewmodel.SharedViewModel

class EditHeroActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditHeroBinding
    lateinit var viewModel: SharedViewModel
    var editedItemid : Int = 0
    private lateinit var selectedFractionItem : Fraction
    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[SharedViewModel::class.java]
        binding = ActivityEditHeroBinding.inflate(layoutInflater)
        var list = listOf<Fraction>()
        setContentView(binding.root)
        viewModel.getAllFractions().observe(this) {
            binding.spinner.adapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, it)
            for (i in 0 until binding.spinner.adapter.count) {
                if (binding.spinner.getItemAtPosition(i).equals(selectedFractionItem)) {
                    binding.spinner.setSelection(i)
                    break
                }
            }
        }


        editedItemid = intent.getIntExtra("id", 0)
        binding.etName.setText(intent.getStringExtra("name"))
        binding.etDescription.setText(intent.getStringExtra("description"))
        selectedFractionItem = viewModel.getFractionById( intent.getIntExtra("fractionId", 0))!!

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

        title = "Edit hero"

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
        val name = binding.etName.text.toString()
        val description = binding.etDescription.text.toString()
        val fractionID = selectedFractionItem.id
        if (name.trim().isEmpty() || description.trim().isEmpty())
        {
            Toast.makeText(this, "Please insert name and description", Toast.LENGTH_SHORT).show()
            return
        }
        viewModel.updateHero(Hero(editedItemid, name, 200,200,description,fractionID))
        Toast.makeText(this, "Hero edited", Toast.LENGTH_SHORT).show()
        finish()
    }



}