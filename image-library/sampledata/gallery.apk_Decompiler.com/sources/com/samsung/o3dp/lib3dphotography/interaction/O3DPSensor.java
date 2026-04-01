package com.samsung.o3dp.lib3dphotography.interaction;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Pair;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class O3DPSensor {
    private static final int SAMPLING_PERIOD_US = 16666;
    private static final String TAG = "O3DPSensor";
    private Pair<SensorEventListener, Sensor> mActivatedSensor = null;
    private final SensorManager mSensorManager;

    public O3DPSensor(SensorManager sensorManager) {
        this.mSensorManager = sensorManager;
    }

    public void pause() {
        Pair<SensorEventListener, Sensor> pair = this.mActivatedSensor;
        if (pair != null) {
            this.mSensorManager.unregisterListener((SensorEventListener) pair.first, (Sensor) pair.second);
        }
    }

    public void registerListener(SensorEventListener sensorEventListener, int i2) {
        Sensor defaultSensor = this.mSensorManager.getDefaultSensor(i2);
        if (defaultSensor == null) {
            LogUtil.w(TAG, "listener is not registered at registerListener(): sensor is null");
            return;
        }
        unregisterListener();
        this.mActivatedSensor = new Pair<>(sensorEventListener, defaultSensor);
        this.mSensorManager.registerListener(sensorEventListener, defaultSensor, SAMPLING_PERIOD_US);
    }

    public void resume() {
        Pair<SensorEventListener, Sensor> pair = this.mActivatedSensor;
        if (pair != null) {
            this.mSensorManager.registerListener((SensorEventListener) pair.first, (Sensor) pair.second, SAMPLING_PERIOD_US);
        }
    }

    public void unregisterListener() {
        Pair<SensorEventListener, Sensor> pair = this.mActivatedSensor;
        if (pair != null) {
            this.mSensorManager.unregisterListener((SensorEventListener) pair.first, (Sensor) pair.second);
            this.mActivatedSensor = null;
        }
    }
}
