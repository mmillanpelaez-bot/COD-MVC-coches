public class Coche {
    String matricula;
    String modelo;
    Integer velocidad;
    double gasolina;

    public Coche(String modelo, String matricula) {
        this.modelo = modelo;
        this.matricula = matricula;
        this.velocidad = 0;
        this.gasolina = 0.0;
    }
}
