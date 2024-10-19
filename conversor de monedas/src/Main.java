import java.util.Scanner;

public class Main {
    // Monedas populares con sus descripciones
    private static final String[][] MONEDAS_POPULARES = {
            {"MXN", "Peso Mexicano"},
            {"USD", "Dólar Estadounidense"},
            {"EUR", "Euro"},
            {"GBP", "Libra Esterlina"},
            {"JPY", "Yen Japonés"},
            {"CAD", "Dólar Canadiense"},
            {"AUD", "Dólar Australiano"},
            {"CHF", "Franco Suizo"}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConversorMonedas conversor = new ConversorMonedas();

        while (true) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    try {
                        System.out.println("Selecciona la moneda de origen:");
                        Moneda monedaOrigen = seleccionarMoneda(scanner, conversor);
                        System.out.println("Selecciona la moneda de destino:");
                        Moneda monedaDestino = seleccionarMoneda(scanner, conversor);

                        System.out.println("Ingrese la cantidad en " + monedaOrigen.getCodigo() + ":");
                        double cantidad = scanner.nextDouble();

                        double resultado = conversor.convertir(cantidad, monedaOrigen, monedaDestino);
                        System.out.println(cantidad + " " + monedaOrigen.getCodigo() + " = " + resultado + " " + monedaDestino.getCodigo());
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    conversor.mostrarHistorial();
                    break;

                case 3:
                    System.out.println("Saliendo del programa...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opción inválida, intenta de nuevo.");
                    break;
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Conversor de Monedas ---");
        System.out.println("1. Realizar conversión");
        System.out.println("2. Ver historial de conversiones");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static Moneda seleccionarMoneda(Scanner scanner, ConversorMonedas conversor) throws Exception {
        // Mostrar lista de monedas con nombre completo
        for (int i = 0; i < MONEDAS_POPULARES.length; i++) {
            System.out.println((i + 1) + ". " + MONEDAS_POPULARES[i][0] + " (" + MONEDAS_POPULARES[i][1] + ")");
        }

        int eleccion = scanner.nextInt();
        if (eleccion < 1 || eleccion > MONEDAS_POPULARES.length) {
            throw new IllegalArgumentException("Selección inválida");
        }

        String codigoMoneda = MONEDAS_POPULARES[eleccion - 1][0];
        return conversor.obtenerMoneda(codigoMoneda);
    }
}
