import java.io.IOException;



public class ConversorMonedas {
    private ApiMonedas apiMonedas;
    private HistorialConversiones historial;

    public ConversorMonedas() {
        apiMonedas = new ApiMonedas();
        historial = new HistorialConversiones();
    }

    public double convertir(double cantidad, Moneda de, Moneda a) {
        double tasaCambio = a.getTasaCambio() / de.getTasaCambio();
        double resultado = cantidad * tasaCambio;
        historial.agregarHistorial(cantidad, de.getCodigo(), a.getCodigo(), resultado);
        return resultado;
    }

    public Moneda obtenerMoneda(String codigo) throws IOException, InterruptedException {
        return apiMonedas.obtenerMoneda(codigo);
    }

    public void mostrarHistorial() {
        historial.mostrarHistorial();
    }
}
