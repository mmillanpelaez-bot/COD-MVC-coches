import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de manejar los datos
 */
public class Model {
    static ArrayList<Coche> parking = new ArrayList<>();

    /**
     * Crea un coche y lo mete en el parking
     * @param modelo del coche
     * @param matricula identificador unico
     * @return el coche creado
     */
    public Coche crearCoche(String modelo, String matricula){
        Coche aux = new Coche(modelo, matricula);
        parking.add(aux);
        return aux;
    }

    /**
     * Busca coche segun matricula
     * @param matricula a buscar
     * @return coche o null si no existe
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
     * Elimina un coche del parking
     * @param matricula del coche a eliminar
     * @return el coche eliminado o null si no existia
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
     * Cambia la velocidad de un coche
     * @param matricula
     * @param v nueva velocidad
     * @return velocidad modificada
     */
    public int cambiarVelocidad(String matricula, Integer v) {
        getCoche(matricula).velocidad = v;
        return getCoche(matricula).velocidad;
    }

    /**
     * Hace avanzar el coche los metros indicados.
     * Suma los metros al km del coche y descuenta la gasolina segun la velocidad.
     * La formula es: consumo = (metros / 1000.0) * (velocidad / 100.0) * 8.0
     * @param matricula del coche
     * @param metros que avanza
     * @return el coche actualizado o null si no existe
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
     * Carga gasolina en el deposito del coche
     * @param matricula del coche
     * @param litros a añadir
     * @return el coche con la gasolina actualizada o null si no existe
     */
    public Coche cargarGasolina(String matricula, double litros) {
        Coche aux = getCoche(matricula);
        if (aux != null) {
            aux.gasolina += litros;
        }
        return aux;
    }

    /**
     * Devuelve la velocidad segun la matricula
     * @param matricula
     * @return velocidad actual
     */
    public int getVelocidad(String matricula) {
        return getCoche(matricula).velocidad;
    }

    /**
     * Devuelve todos los coches del parking
     * @return lista de coches
     */
    public List<Coche> getListaParking() {
        return parking;
    }
}
