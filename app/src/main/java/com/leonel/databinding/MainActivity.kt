package com.leonel.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.leonel.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Obtiene o crea la instancia del viewmodel
         */
        val mainViewModel = ViewModelProviders.of(this)
            .get(MainViewModel::class.java)

        /**
         * Setea la vista R.layout.activity_main como el contenido de DataBinding para esta clase
         * (Esto es autogenerado porque la vista empieza con <layout> y tiene <data>)
         *
         * También indica que la variable viewmodel que aparece en el layout sea el MainViewModel
         */
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        ).apply{
            this.lifecycleOwner = this@MainActivity
            this.viewmodel  = mainViewModel
        }

        // Cada vez que cambia el texto en el EditText mostramos un toast
        mainViewModel.editTextContent.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }
}
