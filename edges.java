public class edges extends connect {
  // Variables de Arista.
  private vertex extremoInicial;
  private vertex extremoFinal;

  // Crear Arista.
  public edges(String id, String peso, vertex extremoInicial, vertex extremoFinal) {
    super(id, peso);
    this.extremoInicial = extremoInicial;
    this.extremoFinal = extremoFinal;
  }

  // Obtener Extremo1.
  public vertex getExtremoInicial() {
    return this.extremoInicial;
  }

  // Obtener Extremo2.
  public vertex getExtremoFinal() {
    return this.extremoFinal;
  }

  // Mostrar la arista.
  @Override
  public String toString() {
    int[] listaElementosValor = new int[getPeso().size()];
    for (int ite = 0; ite < getPeso().size(); ite++) {
      listaElementosValor[ite] = getPeso().get(ite).length();
    }

    return "Aristas: (" + extremoInicial.getId() + " <->" + extremoFinal.getId() + ") peso: "
        + getPeso();
  }
}
