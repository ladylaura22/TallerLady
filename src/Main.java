import java.util.*;

public class Main {
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

    public static void main(String[] args) {
        Main app = new Main();
        app.ingresarAnimales();
        System.out.println("\nClasificación de animales:");
        app.mostrarClasificacion();
    }
}