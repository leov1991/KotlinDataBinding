package com.leonel.databinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

/**
 * Un objeto que simula una base de datos falsa con nombres de frutas
 */
object FruitRepository {
    private val fruitNames : List<String> = listOf(
        "Manzana", "Banana", "Naranja", "Kiwi", "Pera",
        "Cereza", "Mandarina"
    )

    /**
     * Creamos un objeto privado MutableLiveData y uno público
     * porque los valores del Mutable pueden cambiar.
     * Solamente exponemos LiveData (valores que no pueden ser cambiados)
     * a los viewmodels.
     * La idea es implementar la lógica que cambia los valores dentro del
     * repositorio y que el cliente (viewmodel) sólo acceda a la información modificada.
     * Por eso la propiedad pública solamente tiene un getter donde el Mutable se convierte
     */
    private val _currentRandomFruitName = MutableLiveData<String>()
    val currentRandomFruitName : LiveData<String>
    get() = _currentRandomFruitName

    // init es como un contructor. Se ejecuta cuando el objeto es creado
    init {
        // Obtenemos el primer nombre de la lista
        _currentRandomFruitName.value = fruitNames.first()
    }

    fun getRandomFruitName(): String {
        val random = Random()
        return fruitNames[random.nextInt(fruitNames.size)]        
    }

    fun changeCurrentRandomFruitName() {
        _currentRandomFruitName.value = getRandomFruitName()
    }
}