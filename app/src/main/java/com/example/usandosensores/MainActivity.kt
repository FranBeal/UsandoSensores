package com.example.usandosensores

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var tvListaSensores: TextView
    private lateinit var sensorManager: SensorManager
    private lateinit var btDadosDoAcelerometro: Button
    private lateinit var btMostrarLuminosidade: Button
    private lateinit var btMostrarProximidade: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvListaSensores = findViewById(R.id.tvListaSensores)
        btDadosDoAcelerometro = findViewById(R.id.btMostrarDadosAcelerômetro)
        btMostrarLuminosidade = findViewById(R.id.btMostrarLuminosidade)
        btMostrarProximidade = findViewById(R.id.btMostrarProximidade)

        sensorManager = getSystemService(Context.SENSOR_SERVICE)
                as SensorManager

        btDadosDoAcelerometro.setOnClickListener {
            btAceleromentroOnClick()
        }

        btMostrarLuminosidade.setOnClickListener {
            btLuminosidadeOnClick()
        }

        btMostrarProximidade.setOnClickListener {
            btProxmidadeOnClick()
        }

        recuperaListaDeSensores()
    }

    private fun recuperaListaDeSensores() {
        val sensorList: List<Sensor> =
            sensorManager.getSensorList(Sensor.TYPE_ALL)
        val sb = StringBuilder()
        sb.append("Total de sensores: ${sensorList.size}\n\n")
        sensorList.forEachIndexed{index, sensor ->
            sb.append("${index+1}. Nome: ${sensor.name}\n")
            sb.append("    Tipo: ${sensor.stringType}\n")
            sb.append("    Fabricante: ${sensor.vendor}\n")
            sb.append("----------------------------------\n")
        }
        tvListaSensores.text = sb.toString()
    }

    private fun btAceleromentroOnClick() {
        val intent = Intent(this, AcelerometroActivity::class.java)
        startActivity(intent)
    }

    private fun btProxmidadeOnClick() {

    }

    private fun btLuminosidadeOnClick() {

    }
}