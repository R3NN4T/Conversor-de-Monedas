
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsultaMoneda consulta = new ConsultaMoneda();
//        Generador generador = new Generador();

        while (true) {
            // Mostrar el menú de opciones
            System.out.println("===========================================");
            System.out.println("Seleccione una moneda para convertir:");
            System.out.println("===========================================");
            System.out.println("1. Dólar EEUU      => Peso Chileno");
            System.out.println("2. Peso Chileno    => Dólar EEUU");
            System.out.println("3. Dólar EEUU      => Peso Argentino");
            System.out.println("4. Peso Argentino  => Dólar EEUU");
            System.out.println("5. Dólar EEUU      => Soles Peruanos");
            System.out.println("6. Peso Colombiano => Dólar EEUU");
            System.out.println("7. Salir");
            System.out.println("===========================================");

            System.out.print("Ingrese su opción: ");
            int opcion = scanner.nextInt();

            if (opcion == 7) {
                System.out.println("Gracias por usar el conversor. ¡Adiós!");
                break;
            } else if (opcion < 1 || opcion > 7) {
                System.out.println("Opción inválida. Intente de nuevo.");
                continue;
            }

            System.out.print("Ingrese la cantidad a convertir: ");
            double cantidad = scanner.nextDouble();

            String base = "";
            String destino = "";

            // Asignar las monedas según la opción seleccionada
            switch (opcion) {
                case 1 -> { base = "USD"; destino = "CLP"; }
                case 2 -> { base = "CLP"; destino = "USD"; }
                case 3 -> { base = "USD"; destino = "ARS"; }
                case 4 -> { base = "ARS"; destino = "USD"; }
                case 5 -> { base = "USD"; destino = "PEN"; }
                case 6 -> { base = "COP"; destino = "USD"; }
            }

            // Obtener la tasa de conversión desde la API
            Moneda moneda = consulta.convertirMoneda(base, destino);
            double resultado = moneda.conversion_rate() * cantidad;

            // Mostrar los resultados
            System.out.printf("El valor de %.2f [%s] es %.2f [%s]%n",
                    cantidad, moneda.base_code(), resultado, moneda.target_code());

            // Guardar el resultado en un archivo JSON
            //generador.guardarJson(moneda);
            System.out.println("Conversión guardada en archivo JSON.");
        }

        scanner.close();
    }
}