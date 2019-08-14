package com.leonel.databinding

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    /**
     * Responde a los cambios en el nombre de fruta que ocurran en el repositorio
     * Por el databinding, el cambio también se refleja en la activity
     */
    val currentRandomFruitName : LiveData<String>
        get() = FruitRepository.currentRandomFruitName

    /**
     * Llama a la función para cambiar la fruta en el repositorio,
     * lo cual actualizará el valor de la variable de arriba
     */
    fun onChangeRandomFruitClick() = FruitRepository.changeCurrentRandomFruitName()

    /**
     * Contenido del editText enlazado con Two-Way DataBinding.
     * Como puede cambiar desde la activity, es de tipo Mutable.
     */
    @Bindable
    val editTextContent = MutableLiveData<String>()


    private val _displayedEditTextContent = MutableLiveData<String>()
    val displayedEditTextContent : LiveData<String>
        get() = _displayedEditTextContent

    /**
     * Asigna el contenido actual del EditText a la variable que
     * lo mostrará
     */
    fun onDisplayEditTextContentClick(){
        _displayedEditTextContent.value = editTextContent.value
    }

    /**
     * Asigna al contenido del EditText una fruta traída del repositorio
     */
    fun onSelectRandomEditTextFruit() {
        editTextContent.value = FruitRepository.getRandomFruitName()
    }    
}