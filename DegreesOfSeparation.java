public class DegreesOfSeparation {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println(
                    "Se deben proporcionar al menos 2 argumentos: <nombre del primer vértice> <nombre del segundo vértice>");
            return;
        }
        String a1 = args[0];
        String a2 = args[1];
        vertex A = new vertex(a1);
        vertex B = new vertex(a2);
        AdjacencyListGraph graph = new AdjacencyListGraph();
        String archivo = "input.txt";
        graph.cargarGrafo(archivo);
        int X = 1000;
        for (int i : graph.recorridoAB(A, B)) {
            if (i < X) {
                X = i;
            }
        }
        if (X == 1000) {
            X = -1;
        }
        System.out.println(X);
    }
}