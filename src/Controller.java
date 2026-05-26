import java.util.List;

/**
 * Controlador del patron MVC. Conecta la vista con el modelo.
 * Tambien tiene el main para arrancar la app.
 *
 * @author Felipe
 * @version 2.0
 */
public class Controller {

    static View miView = new View();
    Model database = new Model();

    /**
     * Punto de entrada de la aplicacion. Arranca el menu.
     * @param args no se usan
     */
    public static void main(String[] args) {
        miView.menu();
    }

    /**
     * Crea un coche nuevo y comprueba que se ha guardado bien.
     * @param modelo del coche
     * @param matricula del coche
     * @return true si se creo correctamente
     */
    public boolean AgregarCoche(String modelo, String matricula) {
        Coche creado = database.crearCoche(modelo, matricula);
        return creado == database.getCoche(matricula);
    }

    /**
     * Quita un coche del parking.
     * @param matricula del coche a quitar
     * @return el coche eliminado o null si no existia
     */
    public Coche atenderQuitarCoche(String matricula) {
        return database.quitarCoche(matricula);
    }

    /**
     * Hace avanzar el coche los metros indicados.
     * El model suma los km y descuenta la gasolina.
     * @param matricula del coche
     * @param metros que avanza
     * @return el coche actualizado o null si no existe
     */
    public Coche atenderAvanzarCoche(String matricula, Integer metros) {
        return database.hacerAvanzarCoche(matricula, metros);
    }

    /**
     * Carga gasolina en el coche indicado.
     * @param matricula del coche
     * @param litros a añadir
     * @return el coche con el deposito actualizado o null si no existe
     */
    public Coche atenderCargarGasolina(String matricula, double litros) {
        return database.cargarGasolina(matricula, litros);
    }

    /**
     * Cambia la velocidad de un coche.
     * @param matricula del coche
     * @param nuevaVelocidad en km/h
     * @return la velocidad que se ha puesto
     */
    public int atenderCambiarVelocidad(String matricula, Integer nuevaVelocidad) {
        return database.cambiarVelocidad(matricula, nuevaVelocidad);
    }

    /**
     * Devuelve la velocidad actual de un coche.
     * @param matricula del coche
     * @return velocidad en km/h
     */
    public int atenderMostrarVelocidad(String matricula) {
        return database.getVelocidad(matricula);
    }

    /**
     * Devuelve la lista de todos los coches del parking.
     * @return lista de coches
     */
    public List<Coche> atenderMostrarLista() {
        return database.getListaParking();
    }
}
