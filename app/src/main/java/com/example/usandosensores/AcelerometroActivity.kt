package com.example.usandosensores

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class AcelerometroActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var tvX: TextView
    private lateinit var tvY: TextView
    private lateinit var tvZ: TextView
    private lateinit var sensorManager: SensorManager
    private var acelerometro: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acelerometro)
        tvX = findViewById(R.id.tvX)
        tvY = findViewById(R.id.tvY)
        tvZ = findViewById(R.id.tvZ)

        sensorManager = getSystemService(Context.SENSOR_SERVICE)
        as SensorManager

        acelerometro =
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        if(acelerometro == null){
            Toast.makeText(this, "Acelerômetro não disponível",
                Toast.LENGTH_LONG).show()
            tvX.text = "Acelerômetro não encontrado"
            tvY.text = ""
            tvZ.text = ""
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event?.sensor?.type == Sensor.TYPE_ACCELEROMETER){
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            tvX.text = String.format(Locale.getDefault(),
                "Eixo X: %.2f m/s²", x)
            tvY.text = String.format(Locale.getDefault(),
                "Eixo Y: %.2f m/s²", y)
            tvZ.text = String.format(Locale.getDefault(),
                "Eixo Z: %.2f m/s²", z)
        }
    }

    override fun onResume(){
        super.onResume()
        sensorManager.registerListener(this,
            acelerometro,
            SensorManager.SENSOR_DELAY_UI)
    }

    override fun onPause(){
        super.onPause()
        sensorManager.unregisterListener(this)
    }

}