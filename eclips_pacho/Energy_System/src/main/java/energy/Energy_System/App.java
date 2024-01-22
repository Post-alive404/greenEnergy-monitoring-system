package energy.Energy_System;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class App 
{
	
	public static void main(String[] args) {
        String broker = "tcp://localhost:1883";
        String clientId = "SensorEmulator";
        String topic = "sensor/data";
        
        //позначаємо датчики станцій зеленої енргетики 
        String hydroStationTopic = "hydro_station_sensor/data";
        String solarStationTopic = "solar_station_sensor/data";
        String windStationTopic = "wind_station_sensor/data";
        String geothermalStationTopic = "geothermal_station_sensor/data";
        String bioStationTopic = "bio_station_sensor/data";
        
        //емулюємо запити від датчиків
        try {
        	System.out.println("Connecting to broker: " + broker);
            MqttClient client = new MqttClient(broker, clientId);
            client.connect();

            while (true) {
            	String message = " ";
            	
            	//HydroStation
            	message = sensorData(80, 100);
                MqttMessage mqttMessageHydro = new MqttMessage(message.getBytes());
                client.publish(hydroStationTopic, mqttMessageHydro);
                System.out.println("Sent: "+ hydroStationTopic + " " + message);
                
                //SolarStation
                message = sensorData(50, 80);
                MqttMessage mqttMessageSolar = new MqttMessage(message.getBytes());
                client.publish(solarStationTopic, mqttMessageSolar);
                System.out.println("Sent: "+ solarStationTopic + " " + message);
                
                //WindStation
                message = sensorData(40, 70);
                MqttMessage mqttMessageWind = new MqttMessage(message.getBytes());
                client.publish(windStationTopic, mqttMessageWind);
                System.out.println("Sent: "+ windStationTopic + " " + message);
                
                //GeothermalStation
                message = sensorData(10, 30);
                MqttMessage mqttMessageGeothermal = new MqttMessage(message.getBytes());
                client.publish(geothermalStationTopic, mqttMessageGeothermal);
                System.out.println("Sent: "+ geothermalStationTopic + " " + message);
                
                //BioStation
                message = sensorData(2, 20);
                MqttMessage mqttMessageBio = new MqttMessage(message.getBytes());
                client.publish(bioStationTopic, mqttMessageBio);
                System.out.println("Sent: "+ bioStationTopic + " " + message);
                
                // Очікування 5 секунд перед наступною відправкою даних
                Thread.sleep(5000); 
                
            }
            
        } catch (MqttException | InterruptedException e) {
            e.printStackTrace();
        }
    }
	
	//Генерація даних для вузлів станцій
	private static String sensorData(int min, int max) {
		int range = (max - min) + 1;     
	    return String.valueOf((Math.random() * range) + min);
	}
    
}
