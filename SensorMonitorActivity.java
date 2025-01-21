package mobile.android.sensor;
 
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
 
import java.util.List;
 
public class Main extends Activity implements SensorEventListener {
 
    private TextView tvAccelerometer;
    private TextView tvMagnetic;
    private TextView tvLight;
    private TextView tvOrientation;
    private TextView tvSensors;
 
    private SensorManager sensorManager;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
 
        // 初始化TextView组件
        tvAccelerometer = (TextView) findViewById(R.id.tvAccelerometer);
        tvMagnetic = (TextView) findViewById(R.id.tvMagnetic);
        tvLight = (TextView) findViewById(R.id.tvLight);
        tvOrientation = (TextView) findViewById(R.id.tvOrientation);
        tvSensors = (TextView) findViewById(R.id.tvSensors);
 
        // 获取SensorManager对象
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
 
        // 注册传感器监听器
        registerSensors();
    }
 
    private void registerSensors() {
        // 注册加速度传感器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
 
        // 注册磁场传感器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_NORMAL);
 
        // 注册光线传感器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_NORMAL);
 
        // 注册方向传感器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_NORMAL);
 
        // 获取当前手机支持的所有传感器并显示名称
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : sensors) {
            tvSensors.append(sensor.getName() + "\n");
        }
    }
 
    @Override
    public void onSensorChanged(SensorEvent event) {
        // 处理传感器数据
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                tvAccelerometer.setText("加速度：" + "X：" + event.values[0] + "，Y：" + event.values[1] + "，Z：" + event.values[2]);
                break;
 
            case Sensor.TYPE_LIGHT:
                tvLight.setText("亮度：" + event.values[0]);
                break;
 
            case Sensor.TYPE_MAGNETIC_FIELD:
                tvMagnetic.setText("磁场：" + "X：" + event.values[0] + "，Y：" + event.values[1] + "，Z：" + event.values[2]);
                break;
 
            case Sensor.TYPE_ORIENTATION:
                tvOrientation.setText("方向：" + "X：" + event.values[0] + "，Y：" + event.values[1] + "，Z：" + event.values[2]);
                break;
        }
    }
 
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // 传感器精度变化时的处理（可选实现）
    }
 
    @Override
    protected void onPause() {
        super.onPause();
        // 在Activity暂停时取消传感器监听
        sensorManager.unregisterListener(this);
    }
 
    @Override
    protected void onResume() {
        super.onResume();
        // 在Activity恢复时重新注册传感器监听
        registerSensors();
    }
}
