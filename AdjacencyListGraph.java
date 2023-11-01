import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AdjacencyListGraph implements Graph<vertex> {
    // Declaramos las variables, arreglos y tablas que vayamos a utilizar
    private HashMap<String, vertex> vertices;
    private ArrayList<edges> connect;
    public static final int inf = Integer.MAX_VALUE;

    public AdjacencyListGraph() {
        // Constructor de la clase.
        vertices = new HashMap<>();
        connect = new ArrayList<>();
    }

    // Método para agregar un vértice al grafo
    public boolean add(vertex vertex) {
        if (!vertices.containsKey(vertex.getId())) {
            // Agregar el vértice al hashtable
            vertices.put(vertex.getId(), vertex);
            // Vértice agregado exitosamente
            return true;
        }
        // El vértice ya existe en el grafo
        return false;
    }

    // Método para verificar si un vértice existe en el grafo dado su identificador
    public boolean contains(vertex vertex) {
        if (vertices.containsKey(vertex.getId())) {
            // El vértice existe en el grafo
            return true;
        }
        // El vértice no existe en el grafo
        return false;
    }

    // Método para verificar si existe un lado entre dos vértices
    public boolean containsconnect(vertex from, vertex to) {
        // Iterar sobre todos los lados
        for (edges a : connect) {
            // Verificar si los vértices extremos coinciden en cualquier dirección
            if (a.getExtremoInicial().equals(from) && a.getExtremoFinal().equals(to)
                    || a.getExtremoInicial().equals(to) && a.getExtremoFinal().equals(from)) {
                // Existe un lado entre los vértices
                return true;
            }
        }
        // No existe un lado entre los vértices
        return false;
    }

    public boolean connect(vertex from, vertex to) {
        // Esta función establece la relación entre dos vértices si existe una conexión
        // entre ellos.
        if (!containsconnect(from, to)) {
            edges arista = new edges("" + from.getId() + to.getId() + "", "0", from, to);
            connect.add(arista);
            return true;
        }
        return false;
    }

    public boolean disconnect(vertex from, vertex to) {
        // Esta función elimina la relación entre dos vértices si existe una conexión
        // entre ellos.
        if (contains(from) && contains(to) && containsconnect(from, to)) {
            for (edges a : connect) {
                if (a.getExtremoInicial().equals(from) && a.getExtremoFinal().equals(to)
                        || a.getExtremoInicial().equals(to) && a.getExtremoFinal().equals(from)) {
                    connect.remove(a);
                    return true;
                }
            }
        }
        return false;
    }

    public List<vertex> getInwardEdges(vertex to) {
        // Esta función devuelve una lista de vértices predecesores que tienen una
        // conexión con el vértice dado.
        List<vertex> inwardEdges = new ArrayList<>();
        for (edges a : connect) {
            if (a.getExtremoFinal().getId().equals(to.getId())) {
                inwardEdges.add(a.getExtremoInicial());
            }
        }
        return inwardEdges;
    }

    public List<vertex> getOutwardEdges(vertex from) {
        // Esta función devuelve una lista de vértices sucesores que tienen una conexión
        // con el vértice dado.
        List<vertex> outwardEdges = new ArrayList<>();
        for (edges a : connect) {
            if (a.getExtremoInicial().getId().equals(from.getId())) {
                outwardEdges.add(a.getExtremoFinal());
            }
        }
        return outwardEdges;
    }

    public List<vertex> getVerticesConnectedTo(vertex vertex) {
        // Esta función devuelve una lista de vértices que tienen una conexión con el
        // vértice dado.
        List<vertex> verticesConnectedTo = new ArrayList<>();
        for (edges a : connect) {
            if (a.getExtremoInicial().getId().equals(vertex.getId())) {
                verticesConnectedTo.add(a.getExtremoFinal());
            } else if (a.getExtremoFinal().equals(vertex)) {
                verticesConnectedTo.add(a.getExtremoInicial());
            }
        }
        return verticesConnectedTo;
    }

    public List<vertex> getAllVertices() {
        // Esta función devuelve una lista de todos los vértices del grafo.
        List<vertex> allVertices = new ArrayList<>();
        for (String key : vertices.keySet()) {
            allVertices.add(vertices.get(key));
        }
        return allVertices;
    }

    public boolean remove(vertex vertex) {
        // Esta función elimina un vértice del grafo.
        if (contains(vertex)) {
            vertices.remove(vertex.getId());
            return true;
        }
        return false;
    }

    public int size() {
        // Esta función devuelve el número de vértices en el grafo.
        return vertices.size();
    }

    public Graph<vertex> subgraph(Collection<vertex> vertices) {
        // Esta función devuelve un subgrafo del grafo dado un conjunto de vértices.
        Graph<vertex> subgraph = new AdjacencyListGraph();
        for (vertex v : vertices) {
            subgraph.add(v);
        }
        for (edges a : connect) {
            if (subgraph.contains(a.getExtremoInicial()) && subgraph.contains(a.getExtremoFinal())) {
                subgraph.connect(a.getExtremoInicial(), a.getExtremoFinal());
            }
        }
        return subgraph;
    }

    @Override
    public String toString() {
        // Este método devuelve una representación en forma de cadena del grafo.
        StringBuilder sb = new StringBuilder();
        // Iterar sobre todos los vertices en el grafo
        for (String a : vertices.keySet()) {
            sb.append("Persona: ").append(a).append("\n");
        }
        // Iterar sobre todas las aristas en el grafo
        for (edges l : connect) {
            sb.append(l).append("\n");
        }
        // Devolver la representación en forma de cadena del grafo
        return sb.toString();
    }

    public boolean cargarGrafo(String dirArchivo) {
        // Esta función cargará los datos de un .txt
        try (BufferedReader lista = new BufferedReader(new FileReader(dirArchivo))) {
            String linea;
            // Dividir la línea en datos separados por '|'
            while ((linea = lista.readLine()) != null) {
                String[] datos = linea.split(" ");
                String A = datos[0];
                String B = datos[1];
                vertex a1 = new vertex(A);
                vertex a2 = new vertex(B);
                // Agregar el vértices y la conexión al grafo
                add(a1);
                add(a2);
                connect(a1, a2);
            }
            return true;
        } catch (IOException | NumberFormatException e) {
            return false;
        }
    }

    public List<Integer> recorridoAB(vertex vertex1, vertex vertex2) {
        List<Integer> conexiones = new ArrayList<>();
        Queue<vertex> queue = new LinkedList<>();
        vertex1.setPeso(0);
        queue.add(vertex1);

        while (!queue.isEmpty()) {
            vertex currentVertex = queue.poll();
            // System.out.println(currentVertex.getId());
            if (currentVertex.getId().equals(vertex2.getId())) {
                conexiones.add(currentVertex.getPeso());
            }

            for (vertex v : getOutwardEdges(currentVertex)) {
                // System.out.println(getOutwardEdges(currentVertex));
                if (v.getPeso() == -1) {
                    // System.out.println(v.getId());
                    v.setPeso(currentVertex.getPeso() + 1);
                    queue.add(v);
                    // System.out.println(queue);
                }
            }
        }
        return conexiones;
    }

    // end
}
