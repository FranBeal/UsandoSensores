package com.example.usandosensores

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LuminosidadeActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var llTela: LinearLayout
    private lateinit var sensorManager: SensorManager
    private var lumi: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_luminosidade)
        llTela = findViewById(R.id.llTela)

        sensorManager = getSystemService(
            Context.SENSOR_SERVICE)
                as SensorManager

        lumi = sensorManager.getDefaultSensor(
            Sensor.TYPE_LIGHT
        )
    }

    override fun onAccuracyChanged(event: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {

    }
}