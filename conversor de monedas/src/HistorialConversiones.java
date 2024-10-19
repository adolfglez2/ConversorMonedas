import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class HistorialConversiones {
    private Map<LocalDateTime, String> historial;

    public HistorialConversiones() {
        historial = new HashMap<>();
    }

    public void agregarHistorial(double cantidad, String de, String a, double resultado) {
        LocalDateTime ahora = LocalDateTime.now();
        historial.put(ahora, cantidad + " " + de + " -> " + resultado + " " + a);
    }

    public void mostrarHistorial() {
        System.out.println("Historial de conversiones:");
        for (Map.Entry<LocalDateTime, String> entrada : historial.entrySet()) {
            System.out.println(entrada.getKey() + " - " + entrada.getValue());
        }
    }
}
