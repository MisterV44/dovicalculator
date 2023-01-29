package com.example.dovicaluculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var ajouterOperation = false
    private var ajouterDecimal = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun numberAction(view: View) {
        if (view is Button ){
            if (view.text == ".")
            {
                if (ajouterDecimal){
                    fonctionnementEcrans.append(view.text)
                    ajouterDecimal = false
                }
            }
            else
                fonctionnementEcrans.append(view.text)
            ajouterOperation = true
        }
    }

    fun operationAction(view: View) {
        if (view is Button && ajouterOperation){
            fonctionnementEcrans.append(view.text)
            ajouterOperation = false
            ajouterDecimal = true
        }
    }


    fun allClearAction(view: View) {
        fonctionnementEcrans.text = ""
        resultatEcrans.text = ""
    }

    fun backSpaceAction(view: View) {
        var taille = resultatEcrans.length()
        if (taille > 0){
            resultatEcrans.text = fonctionnementEcrans.text.subSequence(0, taille - 1)
        }
    }


    fun equalsAction(view: View) {
        resultatEcrans.text = resultatCalcul()
    }

    private fun resultatCalcul(): String {

        val operateurNumerique = operateurNumerique()
        if (operateurNumerique.isEmpty()) return "vide"

        val division = divisionCalculate(operateurNumerique)
        if (division.isEmpty()) return "vide"

        val resultat = ajoutSubtractCalculate(division)
        return resultat.toString()
    }

    private fun ajoutSubtractCalculate(passedList: MutableList<Any>): Int {
        var resultat = passedList[0] as Int

        for (i in passedList.indices){
            if (passedList[i] is Char && i != passedList.lastIndex)
            {
                val operateur = passedList[i]
                val nextDigit = passedList[i + 1] as Int
                if (operateur == '+'){
                    resultat += nextDigit
                }
                if (operateur == '-'){
                    resultat -= nextDigit
                }
            }
        }
        return resultat
    }

    private fun divisionCalculate(passedList: MutableList<Any>): MutableList<Any> {
        var liste = passedList
        while (liste.contains('x')|| liste.contains('/')){
            liste = calcDivision(liste)
        }
        return liste
    }

   /* private fun calcDivision(passedList: MutableList<Any>): MutableList<Any> {

        val nouvelleListe = mutableListOf<Any>()
        var restartIndex = passedList.size

        for (i in passedList.indices)
        {
            if (passedList[i] is Char && i != passedList.lastIndex && i < restartIndex)
            {
                val operateur = passedList[i]
                val prevDigit = passedList[i - 1] is Int
                val nextDigit = passedList[i + 1] is Int

                when(operateur){
                    'x' -> {
                        nouvelleListe.add(prevDigit * nextDigit)
                        restartIndex = i + 1
                    }
                    '/' -> {
                        nouvelleListe.add(prevDigit / nextDigit)
                        restartIndex = i + 1
                    }
                    else ->
                    {
                        nouvelleListe.add(prevDigit)
                        nouvelleListe.add(operateur)
                    }
                }
            }
            if (i > restartIndex)
                nouvelleListe.add(passedList[i])
        }

        return nouvelleListe
    } */

    private fun calcDivision(passedList: MutableList<Any>): MutableList<Any>
    {
        val newList = mutableListOf<Any>()
        var restartIndex = passedList.size

        for(i in passedList.indices)
        {
            if(passedList[i] is Char && i != passedList.lastIndex && i < restartIndex)
            {
                val operator = passedList[i]
                val prevDigit = passedList[i - 1] as Int
                val nextDigit = passedList[i + 1] as Int
                when(operator)
                {
                    'x' ->
                    {
                        newList.add(prevDigit * nextDigit)
                        restartIndex = i + 1
                    }
                    '/' ->
                    {
                        newList.add(prevDigit / nextDigit)
                        restartIndex = i + 1
                    }
                    else ->
                    {
                        newList.add(prevDigit)
                        newList.add(operator)
                    }
                }
            }

            if(i > restartIndex)
                newList.add(passedList[i])
        }

        return newList
    }



    private fun operateurNumerique(): MutableList<Any>{
        val liste = mutableListOf<Any>()
        var operateurCourant = ""

        for (character in fonctionnementEcrans.text){
            if (character.isDigit() || character == '.'){
                operateurCourant += character
            }
            else{
                liste.add(operateurCourant.toInt())
                operateurCourant = ""
                liste.add(character)
            }
        }
        if (operateurCourant != ""){
            liste.add(operateurCourant.toInt())
        }

        return liste
    }
}