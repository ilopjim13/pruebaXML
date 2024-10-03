package org.example

import org.w3c.dom.Element
import org.w3c.dom.Node
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.io.path.Path

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    // LECTURA DE FICHERO XML
    // El objetivo es parsear un arcivo xml a arbol DOM
    // instanciar un objeto documentBuilderFactory
    val dbf = DocumentBuilderFactory.newInstance()

    val db = dbf.newDocumentBuilder()

    // Creamos el fichero con la ruta
    val fileXml = Path("src/main/resources/productos.xml")

    // Parsea el fichero
    val document = db.parse(fileXml.toFile())

    // Dentro de la clase document tenemos dos metodos importantes

    // 1 para obtener el elemento root
    val root  = document.documentElement

    // Dentro de la clase element tenemos vario smetodos importantes
    // 1 para normalizar el arbol
    root.normalize()

    // 2 para obtener elementos por su nombre de etiqueta
    val listaNodos = root.getElementsByTagName("producto")


    // Cuando tenemos la nodeList, podemos iterar sobre ella
    for (i in 0..<listaNodos.length) {
        // Para acceder a un item en particular es  a traves del index
        val nodo = listaNodos.item(i)

        // Para acceder al tipo de del nodo
        if (nodo.nodeType == Node.ELEMENT_NODE) {

            // Casteamos a ELement
            val nodoElemento = nodo as Element

            // Podemos buscar los elementos que nos convienen
            val elementoNombre = nodoElemento.getElementsByTagName("nombre")
            val elementoPrecio = nodoElemento.getElementsByTagName("precio")


            // una vez tengamos el elemento, podemos acceder a su conteniod
            val textContentNombre = elementoNombre.item(0).textContent
            val textContentPrecio = elementoPrecio.item(0).textContent.toDouble()

            //imprimir
            println("Producto $i\n\t - Nombre: $textContentNombre\n\t - Precio: $textContentPrecio")

        }
    }
}
