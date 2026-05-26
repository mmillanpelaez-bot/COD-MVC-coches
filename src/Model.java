import java.util.ArrayList;
import java.util.List;

/**
 * Clase Model del patrón MVC. Aquí es donde se guardan y gestionan los datos
 * de los coches. Funciona como si fuera una base de datos en memoria usando
 * un ArrayList llamado parking.
 *
 * @author Felipe
 * @version 2.0
 */
public class Model {

    /** Lista donde se guardan todos los coches que hay en el parking. */
    static ArrayList<Coche> parking = new ArrayList<>();

    /**
     * Crea un coche nuevo y lo mete en el parking.
     *
     * @param modelo    Modelo del coche.
     * @param matricula Matrícula del coche.
     * @return El coche que se acaba de crear.
     */
    public Coche crearCoche(String modelo, String matricula){
        Coche aux = new Coche(modelo, matricula);
        parking.add(aux);
        return aux;
    }

    /**
     * Busca un coche en el parking por su matrícula.
     *
     * @param matricula Matrícula del coche que queremos encontrar.
     * @return El coche si existe, o null si no está en el parking.
     */
    public Coche getCoche(String matricula){
        Coche aux = null;
        for (Coche e: parking) {
            if (e.matricula.equals(matricula)) {
                aux = e;
            }
        }
        return aux;
    }

    /**
     * Quita un coche del parking usando su matrícula.
     *
     * @param matricula Matrícula del coche a eliminar.
     * @return El coche eliminado, o null si no lo encontró.
     */
    public Coche quitarCoche(String matricula) {
        Coche cocheEncontrado = getCoche(matricula);
        if (cocheEncontrado != null) {
            parking.remove(cocheEncontrado);
            return cocheEncontrado;
        }
        return null;
    }

    /**
     * Cambia la velocidad de un coche.
     *
     * @param matricula Matrícula del coche.
     * @param v         Nueva velocidad en km/h.
     * @return La velocidad nueva que se le ha puesto.
     */
    public int cambiarVelocidad(String matricula, Integer v) {
        getCoche(matricula).velocidad = v;
        return getCoche(matricula).velocidad;
    }

    /**
     * Hace avanzar el coche los metros que le indiquemos.
     * Suma esos metros al contador de km del coche y le resta la gasolina
     * que gasta según la velocidad que lleva.
     *
     * La fórmula de consumo es: consumo = (metros / 1000.0) * (velocidad / 100.0) * 8.0
     * Esto son unos 8 litros cada 100 km a 100 km/h.
     * Si la gasolina baja de 0 se queda en 0 (no puede ser negativa).
     *
     * @param matricula Matrícula del coche que va a avanzar.
     * @param metros    Metros que avanza el coche.
     * @return El coche con los km y la gasolina actualizados, o null si no existe.
     */
    public Coche hacerAvanzarCoche(String matricula, Integer metros) {
        Coche aux = getCoche(matricula);
        if (aux != null) {
            aux.km += metros;
            double consumo = (metros / 1000.0) * (aux.velocidad / 100.0) * 8.0;
            aux.gasolina = Math.max(0.0, aux.gasolina - consumo);
        }
        return aux;
    }

    /**
     * Carga gasolina en el depósito del coche.
     * Simplemente le suma los litros que le digamos al nivel actual.
     *
     * @param matricula Matrícula del coche al que le ponemos gasolina.
     * @param litros    Litros que queremos añadir al depósito.
     * @return El coche con la gasolina actualizada, o null si no existe.
     */
    public Coche cargarGasolina(String matricula, double litros) {
        Coche aux = getCoche(matricula);
        if (aux != null) {
            aux.gasolina += litros;
        }
        return aux;
    }

    /**
     * Devuelve la velocidad actual de un coche.
     *
     * @param matricula Matrícula del coche.
     * @return Velocidad del coche en km/h.
     */
    public int getVelocidad(String matricula) {
        return getCoche(matricula).velocidad;
    }

    /**
     * Devuelve la lista completa de coches que hay en el parking.
     *
     * @return Lista con todos los coches del parking.
     */
    public List<Coche> getListaParking() {
        return parking;
    }
}
