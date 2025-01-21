CREATE TABLE sensor_data (
    id INT AUTO_INCREMENT PRIMARY KEY,  -- 主键，自动递增
    sensor_type VARCHAR(50) NOT NULL,   -- 传感器类型，如温度、湿度、光线等
    location VARCHAR(100) NOT NULL,     -- 传感器位置，如房间号或设备位置
    value FLOAT NOT NULL,               -- 传感器测量值
    unit VARCHAR(20),                   -- 测量单位，如°C、%、lux等
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  -- 更新日期和时间
);
