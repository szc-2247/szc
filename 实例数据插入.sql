-- 插入温度传感器数据
INSERT INTO sensor_data (sensor_type, location, value, unit) 
VALUES ('temperature', 'room1', 25.5, '°C');

-- 插入湿度传感器数据
INSERT INTO sensor_data (sensor_type, location, value, unit) 
VALUES ('humidity', 'room1', 60.2, '%');

-- 插入光线传感器数据
INSERT INTO sensor_data (sensor_type, location, value, unit) 
VALUES ('light', 'room1', 500, 'lux');
