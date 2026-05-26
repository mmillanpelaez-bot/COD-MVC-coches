# Arquitectura MVC

Aplicación en Java para gestionar un parking de coches usando el patrón **MVC (Modelo-Vista-Controlador)**.

---

## Diagrama de clases

```mermaid
classDiagram
    class Coche {
        String matricula
        String modelo
        Integer velocidad
        Integer km
        double gasolina
    }
    class Controller {
        +main()
        +AgregarCoche(String, String) bool
        +atenderAvanzarCoche(String, Integer) Coche
        +atenderCargarGasolina(String, double) Coche
        +atenderQuitarCoche(String) Coche
        +atenderCambiarVelocidad(String, Integer) int
        +atenderMostrarVelocidad(String) int
        +atenderMostrarLista() List
    }
    class View {
        +muestraVelocidad(String, Integer) bool
        +menu()
    }
    class Model {
        ArrayList~Coche~ parking
        +crearCoche(String, String) Coche
        +getCoche(String) Coche
        +quitarCoche(String) Coche
        +cambiarVelocidad(String, Integer) int
        +hacerAvanzarCoche(String, Integer) Coche
        +cargarGasolina(String, double) Coche
        +getVelocidad(String) int
        +getListaParking() List
    }
    Controller "1" *-- "1" Model : association
    Controller "1" *-- "1" View : association
    Model "1" *-- "1..n" Coche : association
```

---

## Diagramas de secuencia

### Avanzar Coche

```mermaid
sequenceDiagram
    actor Usuario
    participant View
    participant Controller
    participant Model
    participant Coche

    Usuario->>View: Selecciona opción 3 (Avanzar)
    View->>Usuario: Pide matrícula y metros
    Usuario->>View: Introduce matrícula y metros
    View->>Controller: atenderAvanzarCoche(matricula, metros)
    Controller->>Model: hacerAvanzarCoche(matricula, metros)
    Model->>Model: getCoche(matricula)
    Model->>Coche: coche.km += metros
    Model->>Coche: consumo = (metros/1000) * (velocidad/100) * 8
    Model->>Coche: coche.gasolina = max(0, gasolina - consumo)
    Model-->>Controller: Coche actualizado
    Controller-->>View: Coche actualizado
    View->>Usuario: Muestra km acumulados y gasolina restante
```

### Cargar Gasolina

```mermaid
sequenceDiagram
    actor Usuario
    participant View
    participant Controller
    participant Model
    participant Coche

    Usuario->>View: Selecciona opción 4 (Cargar Gasolina)
    View->>Usuario: Pide matrícula y litros
    Usuario->>View: Introduce matrícula y litros
    View->>Controller: atenderCargarGasolina(matricula, litros)
    Controller->>Model: cargarGasolina(matricula, litros)
    Model->>Model: getCoche(matricula)
    Model->>Coche: coche.gasolina += litros
    Model-->>Controller: Coche actualizado
    Controller-->>View: Coche actualizado
    View->>Usuario: Muestra el nuevo nivel de gasolina (L)
```

---

## Opciones del menú

| Opción | Acción |
|--------|--------|
| 1 | Agregar Coche |
| 2 | Quitar Coche |
| 3 | **Avanzar Coche (metros)** — suma km y gasta gasolina |
| 4 | **Cargar Gasolina (litros)** — rellena el depósito |
| 5 | Cambiar Velocidad |
| 6 | Mostrar Velocidad |
| 7 | Coches en el parking |
| 8 | Salir |

## Fórmula de consumo

```
consumo (L) = (metros / 1000.0) * (velocidad / 100.0) * 8.0
```

Cuanto más rápido va el coche, más gasta.

## Cómo compilar y ejecutar

```bash
cd src
javac *.java
java Controller
```

## Javadoc

```bash
javadoc -d docs -author -version src/*.java
```

## Autor

Felipe · v2.0
