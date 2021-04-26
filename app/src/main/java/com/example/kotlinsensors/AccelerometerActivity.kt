package com.example.kotlinsensors

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class AccelerometerActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var tvAccelerometer: TextView
    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accelerometer)

        tvAccelerometer = findViewById(R.id.tvAcceleroemter)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        if (!checkSensor())
            return
        else {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    private fun checkSensor(): Boolean {
        var flag = true
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) == null) {
            flag = false
        }
        return flag
    }

    override fun onSensorChanged(event: SensorEvent?) {
            val values = event!!.values
            val xAxis = values[0]
            val yAxis = values[1]
            val zAxis = values[2]

            tvAccelerometer.text = "x: $xAxis,y:$yAxis,z:$zAxis"

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }


}