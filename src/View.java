import java.util.List;
import java.util.Scanner;

/**
 * Vista del patron MVC. Muestra el menu por consola y lee lo que escribe el usuario.
 * Llama al Controller para las operaciones, nunca toca el Model directamente.
 *
 * @author Felipe
 * @version 2.0
 */
public class View {

    /**
     * Muestra la velocidad de un coche (metodo original)
     * @param matricula del coche
     * @param v velocidad
     * @return true si se ha mostrado correctamente
     */
    public boolean muestraVelocidad(String matricula, Integer v){
        System.out.println(matricula + ": " + v + "km/hr");
        return true;
    }

    /**
     * Menu principal de la aplicacion. Se queda en bucle hasta que el usuario elige salir (opcion 8).
     *
     * Opciones:
     * 1. Agregar Coche
     * 2. Quitar Coche
     * 3. Avanzar Coche (metros) - suma km y gasta gasolina segun velocidad
     * 4. Cargar Gasolina (litros) - rellena el deposito
     * 5. Cambiar Velocidad
     * 6. Mostrar Velocidad
     * 7. Coches en el parking
     * 8. Salir
     */
    public void menu() {
        Controller c = new Controller();
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n=== GESTIÓN DE PARKING ===");
            System.out.println("1. Agregar Coche");
            System.out.println("2. Quitar Coche");
            System.out.println("3. Avanzar Coche (metros)");
            System.out.println("4. Cargar Gasolina (litros)");
            System.out.println("5. Cambiar Velocidad");
            System.out.println("6. Mostrar Velocidad del Coche");
            System.out.println("7. Coches en el parking");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(teclado.nextLine().trim());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {

                case 1:
                    System.out.println("\n--- Agregar Coche ---");
                    System.out.print("Introduce el modelo: ");
                    String modelo = teclado.nextLine();
                    System.out.print("Introduce la matrícula: ");
                    String matricula = teclado.nextLine();
                    boolean agregado = c.AgregarCoche(modelo, matricula);
                    if (agregado) {
                        System.out.println("¡Coche aparcado con éxito!");
                    } else {
                        System.out.println("¡FALLO FATAL!");
                    }
                    break;

                case 2:
                    System.out.println("\n--- Quitar Coche ---");
                    System.out.print("Introduce la matrícula del coche para quitar: ");
                    String matricula2 = teclado.nextLine();
                    Coche cocheQuitado = c.atenderQuitarCoche(matricula2);
                    if (cocheQuitado != null) {
                        System.out.println("Éxito: El coche con matrícula " + matricula2 + " ha sido retirado.");
                    } else {
                        System.out.println("Error: No se encontró ningún coche con la matrícula " + matricula2 + " en el parking.");
                    }
                    break;

                case 3:
                    // avanzar: suma los metros al contador y gasta gasolina segun la velocidad
                    System.out.println("\n--- Avanzar Coche ---");
                    System.out.print("Introduce la matrícula: ");
                    String matricula3 = teclado.nextLine();
                    System.out.print("Introduce los metros a avanzar: ");
                    try {
                        int metros = Integer.parseInt(teclado.nextLine().trim());
                        Coche avanzado = c.atenderAvanzarCoche(matricula3, metros);
                        if (avanzado != null) {
                            System.out.printf("¡Avanzado! Kilómetros acumulados: %d m | Gasolina restante: %.2f L%n",
                                    avanzado.km, avanzado.gasolina);
                        } else {
                            System.out.println("El coche con matrícula " + matricula3 + " no está en el parking.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: introduce un número entero de metros válido.");
                    }
                    break;

                case 4:
                    // cargar gasolina: añade litros al deposito
                    System.out.println("\n--- Cargar Gasolina ---");
                    System.out.print("Introduce la matrícula: ");
                    String matricula4 = teclado.nextLine();
                    System.out.print("Introduce los litros a añadir: ");
                    try {
                        double litros = Double.parseDouble(teclado.nextLine().trim().replace(",", "."));
                        Coche cargado = c.atenderCargarGasolina(matricula4, litros);
                        if (cargado != null) {
                            System.out.printf("¡Gasolina cargada! Nivel actual del depósito: %.2f L%n",
                                    cargado.gasolina);
                        } else {
                            System.out.println("El coche con matrícula " + matricula4 + " no está en el parking.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: introduce un número de litros válido (ej. 30 o 30.5).");
                    }
                    break;

                case 5:
                    System.out.println("\n--- Cambiar Velocidad ---");
                    System.out.print("Introduce la matrícula: ");
                    String matricula5 = teclado.nextLine();
                    System.out.print("Introduce la nueva velocidad (km/h): ");
                    try {
                        int vel = Integer.parseInt(teclado.nextLine().trim());
                        int velNueva = c.atenderCambiarVelocidad(matricula5, vel);
                        System.out.println("Velocidad actualizada a: " + velNueva + " km/h.");
                    } catch (NumberFormatException e) {
                        System.out.println("Error: introduce un número entero de velocidad válido.");
                    } catch (NullPointerException e) {
                        System.out.println("Error: no se encontró ningún coche con esa matrícula.");
                    }
                    break;

                case 6:
                    System.out.println("\n--- Mostrar velocidad del coche ---");
                    System.out.print("Introduce la matrícula del coche: ");
                    String matricula6 = teclado.nextLine();
                    try {
                        int velocidad = c.atenderMostrarVelocidad(matricula6);
                        System.out.println("Velocidad actual del coche: " + velocidad + " km/h.");
                    } catch (NullPointerException e) {
                        System.out.println("Error: no se encontró ningún coche con la matrícula " + matricula6 + ".");
                    }
                    break;

                case 7:
                    System.out.println("\n--- Coches en el parking ---");
                    List<Coche> lista = c.atenderMostrarLista();
                    if (lista.isEmpty()) {
                        System.out.println("El parking está vacío.");
                    } else {
                        for (Coche ch : lista) {
                            System.out.println("Modelo: " + ch.modelo + " | Matrícula: " + ch.matricula
                                    + " | Velocidad: " + ch.velocidad + " km/h"
                                    + " | Recorrido: " + ch.km + " m"
                                    + " | Gasolina: " + String.format("%.2f", ch.gasolina) + " L");
                        }
                        System.out.println("----------------------------");
                    }
                    break;

                case 8:
                    System.out.println("\nSaliendo del programa... ¡Hasta pronto!");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, elige un número del 1 al 8.");
                    break;
            }

        } while (opcion != 8);

        teclado.close();
    }
}
