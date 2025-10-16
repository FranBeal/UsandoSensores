package com.example.usandosensores

import android.content.Context
import android.graphics.Color
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

class ProximidadeActivity : AppCompatActivity(), SensorEventListener{
    private lateinit var llTela: LinearLayout
    private lateinit var ivMicrofone: ImageView
    private lateinit var sensorManager: SensorManager
    private var prox: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proximidade)

        llTela = findViewById(R.id.llTela)
        ivMicrofone = findViewById(R.id.ivMicrofone)

        sensorManager = getSystemService(Context.SENSOR_SERVICE)
        as SensorManager

        prox = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?){
        if(event?.sensor?.type == Sensor.TYPE_PROXIMITY){
            val vl = event.values[0]

            prox?.let{ sensor ->
                val maxRange = sensor.maximumRange

                if(vl >= maxRange){ //longe
                    ivMicrofone.setImageResource(R.drawable.microfoneoff)
                    llTela.setBackgroundColor(Color.WHITE)
                }else{
                    ivMicrofone.setImageResource(R.drawable.microfoneon)
                    llTela.setBackgroundColor(Color.BLACK)
                }
            }
        }

    }
    override fun onResume(){
        super.onResume()
        sensorManager.registerListener(
            this,
            prox,
            SensorManager.SENSOR_DELAY_UI)

    }

    override fun onPause(){
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}