from influxdb import InfluxDBClient
import json
 
# InfluxDB配置
client = InfluxDBClient(host='localhost', port=8086)
client.switch_database('sensor_data')
 
def store_data(data):
    # 将数据转换为InfluxDB所需的格式
    json_body = [
        {
            "measurement": "temperature",
            "tags": {
                "location": "room1"
            },
            "fields": {
                "value": float(data)
            }
        }
    ]
    client.write_points(json_body)
    print("Data stored successfully!")
 
# 示例：从MQTT接收数据
def on_message(client, userdata, msg):
    data = msg.payload.decode()
    print(f"Received data: {data}")
    store_data(data)
 
# MQTT客户端
import paho.mqtt.client as mqtt
 
mqtt_client = mqtt.Client()
mqtt_client.connect("broker.hivemq.com", 1883, 60)
mqtt_client.subscribe("sensor/temperature")
mqtt_client.on_message = on_message
 
mqtt_client.loop_forever()
