Estudiante: Kevin Briceño
Carnet: 15-11661
Laboratorio #1

El programa realiza el recorrido entre los vértices A y B en el grafo. 
Luego, encuentra el valor mínimo de las conexiones y lo almacena en la variable X. 
Finalmente, imprime el valor de X.

cargarGrafo se encarga de leer un archivo de texto y cargar los datos en el grafo. Aquí está una explicación paso a paso de cómo funciona:
Se recibe como parámetro la dirección del archivo de texto a cargar (dirArchivo).
Se crea un objeto BufferedReader para leer el archivo de texto utilizando FileReader y BufferedReader.
Se declara una variable linea para almacenar cada línea leída del archivo.
Se utiliza un bucle while para leer cada línea del archivo hasta que no haya más líneas.
Dentro del bucle, se divide la línea en datos separados por espacios utilizando el método split(" "), y se almacenan en un arreglo datos.
Se extraen los nombres de los vertices (A y B) de los datos obtenidos.
Se crean objetos vertex para representar a los vertices (a1 y a2).
Se llama al método add para agregar los vértices al grafo.
Se llama al método connect para establecer la conexión entre los vértices en el grafo.
Se repite el bucle hasta que se hayan leído todas las líneas del archivo.
Se retorna true para indicar que la carga del grafo fue exitosa.
La función recorridoAB realiza un recorrido en anchura (BFS) desde vertex1 hasta vertex2 y devuelve una lista de los pesos de las conexiones encontradas. Aquí está una explicación paso a paso de cómo funciona:
Se crea una lista conexiones para almacenar los pesos de las conexiones encontradas.
Se crea una cola queue para realizar el recorrido en anchura.
Se establece el peso de vertex1 como 0 y se agrega a la cola.
Se inicia un bucle while que se ejecuta mientras la cola no esté vacía.
Dentro del bucle, se extrae el vértice actual de la cola utilizando el método poll().
Se verifica si el vértice actual es igual a vertex2. Si es así, se agrega su peso a la lista conexiones.
Se recorren todos los vértices sucesores del vértice actual utilizando el método getOutwardEdges.
Si el peso del vértice sucesor es -1 (indicando que no ha sido visitado), se actualiza su peso sumando 1 al peso del vértice actual, se agrega a la cola y se continúa el recorrido.
Se repite el bucle hasta que la cola esté vacía.
Se retorna la lista conexiones que contiene los pesos de las conexiones encontradas.