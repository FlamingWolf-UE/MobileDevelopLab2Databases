package com.example.databasetest.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.databasetest.db.Entities.Fraction
import com.example.databasetest.db.Entities.Hero
import com.example.databasetest.db.Entities.HeroWithFraction
import com.example.databasetest.repository.FractionRepository
import com.example.databasetest.repository.HeroRepository

class SharedViewModel(application : Application) : AndroidViewModel(application){
    private var repository: HeroRepository
    private var fractionRepository: FractionRepository
    private var heroesList : LiveData<List<HeroWithFraction>>
    private var fractionsList : LiveData<List<Fraction>>
    private var filter = MutableLiveData("%")

    init {
        fractionRepository = FractionRepository(application)
        repository = HeroRepository(application)
        heroesList = Transformations.switchMap(filter) { filter -> repository.getItemsFiltered(filter) }
        fractionsList = fractionRepository.getFractions()
    }

    fun setFilter(newFilter: String) {

        val f = when {
            newFilter.isEmpty() -> "%"
            else -> "%$newFilter%"
        }
        filter.postValue(f)
    }

    fun insertHero(hero : Hero)
    {
        repository.insert(hero)
    }

    fun updateHero(hero: Hero)
    {
        repository.update(hero)
    }

    fun deleteHero(hero: Hero)
    {
        repository.delete(hero)
    }

    fun getAllHeroesWithFraction() : LiveData<List<HeroWithFraction>>
    {
        return heroesList
    }

    fun getAllFractions() : LiveData<List<Fraction>>
    {
        return fractionRepository.getFractions()
    }

    fun getFractionById(id : Int) : Fraction?
    {
        return fractionRepository.getFractionById(id)
    }






}