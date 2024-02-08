import dto.MeasurementDTO;
import dto.MeasurementResponse;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GetMeasurementsClient {
    public static void main(String[] args) {
        List<Double> measurements = getMeasurementsFromServer();
        printTemperatureChart(measurements);
    }

    public static List<Double> getMeasurementsFromServer() {
        final String serverUrl = "http://localhost:8080/measurements";
        final  RestTemplate restTemplate = new RestTemplate();

        MeasurementResponse jsonResponse = restTemplate.getForObject(serverUrl, MeasurementResponse.class);

        if(jsonResponse == null || jsonResponse.getMeasurements().isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        return jsonResponse.getMeasurements().stream().map(MeasurementDTO :: getValue).collect(Collectors.toList());
    }

    public static void printTemperatureChart(List<Double> temperatures) {
        double[] x = IntStream.range(0, temperatures.size()).asDoubleStream().toArray();
        double[] y = temperatures.stream().mapToDouble(z -> z).toArray();

        XYChart xyChart = QuickChart.getChart("Temperature Chart", "x", "y", "Temperature", x, y);
        new SwingWrapper(xyChart).displayChart();
    }

}
