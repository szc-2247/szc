package mobile.android.sensor;
 
import java.util.List;
 
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
 
public class Main extends Activity implements SensorEventListener {
 
    private TextView tvAccelerometer;
    private TextView tvMagnetic;
    private TextView tvLight;
    private TextView tvOrientation;
    private TextView tvSensors;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
 
        // 获取SensorManager对象
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
 
        // 注册加速度传感器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_FASTEST);
 
        // 注册磁场传感器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_FASTEST);
 
        // 注册光线传感器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_FASTEST);
 
        // 注册方向传感器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_FASTEST);
 
        // 初始化TextView组件
        tvAccelerometer = (TextView) findViewById(R.id.tvAccelerometer);
        tvMagnetic = (TextView) findViewById(R.id.tvMagnetic);
        tvLight = (TextView) findViewById(R.id.tvLight);
        tvOrientation = (TextView) findViewById(R.id.tvOrientation);
        tvSensors = (TextView) findViewById(R.id.tvSensors);
 
        // 获取当前手机支持的所有传感器
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : sensors) {
            // 输出当前传感器的名称
            tvSensors.append(sensor.getName() + "\n");
        }
    }
 
    @Override
    public void onSensorChanged(SensorEvent event) {
        // 通过getType方法获取当前传回数据的传感器类型
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER: // 处理加速度传感器传回的数据
                String accelerometer = "加速度：" + "X：" + event.values[0] + "，Y：" + event.values[1] + "，Z：" + event.values[2];
                tvAccelerometer.setText(accelerometer);
                break;
 
            case Sensor.TYPE_LIGHT: // 处理光线传感器传回的数据
                tvLight.setText("亮度：" + event.values[0]);
                break;
 
            case Sensor.TYPE_MAGNETIC_FIELD: // 处理磁场传感器传回的数据
                String magnetic = "磁场：" + "X：" + event.values[0] + "，Y：" + event.values[1] + "，Z：" + event.values[2];
                tvMagnetic.setText(magnetic);
                break;
 
            case Sensor.TYPE_ORIENTATION: // 处理方向传感器传回的数据
                String orientation = "方向：" + "X：" + event.values[0] + "，Y：" + event.values[1] + "，Z：" + event.values[2];
                tvOrientation.setText(orientation);
                break;
        }
    }
}
