import java.util.*;
import java.util.stream.Collectors;

public class Main {
    // --- Bloque de animales ---
    private Map<String, List<Animal>> clasificacion;
    private List<Animal> animales;

    public Main() {
        clasificacion = new TreeMap<>();
        animales = new ArrayList<>();
    }

    public void ingresarAnimales() {
        Scanner sc = new Scanner(System.in);
        String continuar;
        do {
            System.out.print("Nombre del animal: ");
            String nombre = sc.nextLine();
            System.out.print("Tipo (terrestre, aéreo, acuático): ");
            String tipo = sc.nextLine().toLowerCase();
            System.out.print("Género (macho, hembra): ");
            String genero = sc.nextLine().toLowerCase();

            Animal animal = new Animal(nombre, tipo, genero);
            animales.add(animal);

            clasificacion.computeIfAbsent(tipo, k -> new ArrayList<>()).add(animal);

            System.out.print("¿Desea ingresar otro animal? (solo escriba: si/no): ");
            continuar = sc.nextLine().toLowerCase();
        } while (continuar.equals("si"));
    }

    public void mostrarClasificacion() {
        for (String tipo : clasificacion.keySet()) {
            System.out.println(capitalizar(tipo) + "s:");
            for (Animal animal : clasificacion.get(tipo)) {
                System.out.println("    " + animal.getNombre());
            }
        }
    }

    private String capitalizar(String texto) {
        if (texto == null || texto.isEmpty()) return texto;
        return texto.substring(0, 1).toUpperCase() + texto.substring(1);
    }

    // --- Bloque de personas ---
    public static void ingresarYProcesarPersonas() {
        Scanner scanner = new Scanner(System.in);
        List<Persona> personas = new ArrayList<>();

        String continuar;
        do {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Apellido: ");
            String apellido = scanner.nextLine();

            System.out.print("Edad: ");
            int edad = Integer.parseInt(scanner.nextLine());

            System.out.print("Género: (M/F");
            String genero = scanner.nextLine().toLowerCase();


            System.out.print("Sueldo por hora: ");
            double sueldoHora = Double.parseDouble(scanner.nextLine());

            System.out.print("Cargo: ");
            String cargo = scanner.nextLine();

            personas.add(new Persona(nombre, apellido, edad, genero, sueldoHora, cargo));

            System.out.print("¿Desea ingresar otra persona? (SI/NO): ");
            continuar = scanner.nextLine().toLowerCase();

        } while (continuar.equalsIgnoreCase("si"));

        // a. Cantidad de personas
        long cantidad = personas.stream().count();

        // b. Promedio de edades
        OptionalDouble promedioEdades = personas.stream()
                .mapToInt(Persona::getEdad)
                .average();

        // c. Personas mayores de edad (>= 18)
        List<Persona> mayoresEdad = personas.stream()
                .filter(p -> p.getEdad() >= 18)
                .collect(Collectors.toList());

        // d. Personas cuyos nombres empiezan con "A"
        List<Persona> nombresConA = personas.stream()
                .filter(p -> p.getNombre().startsWith("A"))
                .collect(Collectors.toList());

        // e. Personas cuyos apellidos contienen "M"
        List<Persona> apellidosContienenM = personas.stream()
                .filter(p -> p.getApellido().contains("M"))
                .collect(Collectors.toList());

        // Mostrar resultados
        System.out.println("\nResumen:");
        System.out.println("Cantidad de personas: " + cantidad);
        if (promedioEdades.isPresent()) {
            System.out.println("Edad promedio: " + promedioEdades.getAsDouble());
        } else {
            System.out.println("No hay personas ingresadas.");
        }

        System.out.println("\nPersonas mayores de edad:");
        mayoresEdad.forEach(System.out::println);

        System.out.println("\nPersonas cuyos nombres empiezan con 'A':");
        nombresConA.forEach(System.out::println);

        System.out.println("\nPersonas cuyos apellidos contienen 'M':");
        apellidosContienenM.forEach(System.out::println);


        personas.stream()
                .filter(p -> p.getCargo().equalsIgnoreCase("director") && p.getGenero().equalsIgnoreCase("m"))
                .map(p -> p.getSueldoHora() * 8)
                .forEach(sueldo -> System.out.println("Sueldo por 8 horas: $" + (long)sueldo));


    }


    public static void main(String[] args) {
        // Animales
        Main app = new Main();
        app.ingresarAnimales();
        System.out.println("\nClasificación de animales:");
        app.mostrarClasificacion();

        // Personas
        ingresarYProcesarPersonas();

    }

