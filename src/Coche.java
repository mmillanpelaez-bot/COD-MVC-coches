/**
 * Clase que representa un coche con sus atributos básicos.
 * Guarda el modelo, la matrícula, la velocidad, los kilómetros recorridos
 * y la gasolina que le queda en el depósito.
 *
 * @author Felipe
 * @version 2.0
 */
public class Coche {

    /** Matrícula del coche, se usa para identificarlo. */
    String matricula;

    /** Modelo del coche (por ejemplo "Seat Ibiza"). */
    String modelo;

    /** Velocidad actual del coche en km/h. Empieza en 0. */
    Integer velocidad;

    /** Kilómetros (en metros) que lleva recorridos el coche en total. Empieza en 0. */
    Integer km;

    /**
     * Litros de gasolina que tiene el coche en el depósito.
     * Se va gastando cada vez que avanza según la velocidad.
     * Empieza en 0.0 (depósito vacío).
     */
    double gasolina;

    /**
     * Crea un coche nuevo con el modelo y la matrícula que le pasemos.
     * La velocidad, los km y la gasolina arrancan a 0.
     *
     * @param modelo    Modelo del coche (ej. "Seat Ibiza").
     * @param matricula Matrícula del coche (ej. "1234-BBB").
     */
    public Coche(String modelo, String matricula) {
        this.modelo    = modelo;
        this.matricula = matricula;
        this.velocidad = 0;
        this.km        = 0;
        this.gasolina  = 0.0;
    }
}
