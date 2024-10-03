package org.example.teoriaEscrituraXml

import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

fun main() {
    // instanciar la clase documentBuilder

    val dbf = DocumentBuilderFactory.newInstance()

    val db = dbf.newDocumentBuilder()

    val dip = db.domImplementation

    // creamos un document vacio
    val document = dip.createDocument(null, "productos", null)

    // el nodo root ya está creado
    // solo tenemos que adjuntar los hijos al nodo root

    // primero creamos el element
    val producto1 = document.createElement("producto")
    document.documentElement.appendChild(producto1)

    // vamos a crear los hijos de producto 1
    val nombreProducto1 = document.createElement("nombre")
    val precioProducto1 = document.createElement("precio")

    // unimos el producto
    producto1.appendChild(nombreProducto1)
    producto1.appendChild(precioProducto1)

    val textoNombreT1 = document.createTextNode("Agua")
    val textoPrecioT1 = document.createTextNode("1.50")

    // unimos el textNote al elemento correspondiente
    nombreProducto1.appendChild(textoNombreT1)
    precioProducto1.appendChild(textoPrecioT1)
    // añadiriamos todos los nodos que quisieramos

    val producto2 = document.createElement("producto")
    document.documentElement.appendChild(producto2)

    // vamos a crear los hijos de producto 1
    val nombreProducto2 = document.createElement("nombre")
    val precioProducto2 = document.createElement("precio")

    // unimos el producto
    producto2.appendChild(nombreProducto2)
    producto2.appendChild(precioProducto2)

    val textoNombreT2 = document.createTextNode("Pan sin gluteb")
    val textoPrecioT2 = document.createTextNode("3.50")

    nombreProducto2.appendChild(textoNombreT2)
    precioProducto2.appendChild(textoPrecioT2)


    // por último procederemos a crear el xml
    // 1. lo que queremos escribir
    val source = DOMSource(document)

    //2. que clase usamos para escribir
    val result = StreamResult(Path.of("src/main/resources/document.xml").toFile())

    val transformer = TransformerFactory.newInstance().newTransformer()

    //bonus point
    //para indentar el xml correctamente
    transformer.setOutputProperty(OutputKeys.INDENT, "yes")

    transformer.transform(source, result)

}