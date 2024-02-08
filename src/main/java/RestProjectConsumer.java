import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class RestProjectConsumer {
    public static void main(String[] args) {
        String sensorName = "Main Sensor";

        //registrateSensor(sensorName);

        Random rn = new Random();

        for(int i = 0; i < 1000; i++) {
            addMeasurement(rn.nextDouble(31.0), rn.nextBoolean(), sensorName);
        }
    }

    private static void registrateSensor(String sensorName) {
        String url = "http://localhost:8080/sensors/registration";

        Map<String, Object> jsonSensorData = new HashMap<>();
        jsonSensorData.put("name", sensorName);

        sendRequest(url, jsonSensorData);
    }

    private static void addMeasurement(double value, boolean raining, String sensorName) {
        String url = "http://localhost:8080/measurements/add";

        Map<String, Object> jsonMeasurementsData = new HashMap<>();
        jsonMeasurementsData.put("value", value);
        jsonMeasurementsData.put("raining", raining);
        jsonMeasurementsData.put("sensor", Map.of("name", sensorName));

        sendRequest(url, jsonMeasurementsData);
    }

    private static void sendRequest(String url, Map<String, Object> jsonData) {
        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request= new HttpEntity<>(jsonData, headers);

        try {
            restTemplate.postForObject(url, request, String.class);

            System.out.println("Изменение успешно отправлено на сервер");
        } catch (HttpClientErrorException e) {
            System.out.println("Error!");
            System.out.println(e.getMessage());
        }
    }
}
