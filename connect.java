import java.util.ArrayList;

public abstract class connect {
  // Clase abstracta

  // Propiedades de la clase
  private String id;
  private ArrayList<String> peso;

  public connect(String id, String peso) {
    // Constructor de la clase abstracta
    this.id = id;
    this.peso = new ArrayList<>();
    this.peso.add(peso);
  }

  public String getId() {
    // obtener el id
    return id;
  }

  public ArrayList<String> getPeso() {
    // Obtener el Peso
    return peso;
  }

  public ArrayList<String> setPeso(String x) {
    // Cambiar el peso
    this.peso.add(x);
    return peso;
  }

  public abstract String toString();
}