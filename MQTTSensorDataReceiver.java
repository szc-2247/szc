import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
 
public class SensorDataReceiver {
    public static void main(String[] args) {
        String broker = "tcp://broker.hivemq.com:1883"; // MQTT服务器地址
        String topic = "sensor/temperature"; // 订阅的主题
        String clientId = "JavaClient";
        MemoryPersistence persistence = new MemoryPersistence();
 
        try {
            MqttClient client = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
 
            System.out.println("Connecting to broker: " + broker);
            client.connect(connOpts);
            System.out.println("Connected");
 
            client.subscribe(topic, new IMqttMessageListener() {
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    System.out.println("Received message: " + new String(message.getPayload()));
                    // 将数据存储到数据库（如MySQL）
                    storeDataToDatabase(new String(message.getPayload()));
                }
            });
 
            // 等待消息
            Thread.sleep(60000);
 
            client.disconnect();
            System.out.println("Disconnected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    private static void storeDataToDatabase(String data) {
        // 数据存储逻辑（见SQL部分）
    }
}
