import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class ApiMonedas {
    private final String API_URL = "https://v6.exchangerate-api.com/v6/96788510f0d008126cdf35d5/latest/USD";
    private final HttpClient client;

    public ApiMonedas() {
        client = HttpClient.newHttpClient();
    }

    public Moneda obtenerMoneda(String codigo) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject json = new JSONObject(response.body());
        JSONObject rates = json.getJSONObject("conversion_rates");

        if (!rates.has(codigo)) {
            throw new IllegalArgumentException("Moneda no soportada: " + codigo);
        }

        double tasaCambio = rates.getDouble(codigo);
        return new Moneda(codigo, tasaCambio);
    }
}
