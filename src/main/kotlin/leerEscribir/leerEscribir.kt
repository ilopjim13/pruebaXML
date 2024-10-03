package org.example.leerEscribir


import org.w3c.dom.Element
import org.w3c.dom.Node
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import kotlin.io.path.Path

fun main() {
    val dbf = DocumentBuilderFactory.newInstance()
    val db = dbf.newDocumentBuilder()
    val fileXml = Path("src/main/resources/document.xml")
    val dct = db.parse(fileXml.toFile())
    val root = dct.documentElement
    root.normalize()

    val listaNodos = root.getElementsByTagName("producto")
    val listaProductos: MutableList<NuevoXml> = mutableListOf()

    for (i in 0..<listaNodos.length) {
        val nodo = listaNodos.item(i)

        if (nodo.nodeType == Node.ELEMENT_NODE) {
            val nodoElemento = nodo as Element
            val elementoNombre = nodoElemento.getElementsByTagName("nombre")
            val elementoPrecio = nodoElemento.getElementsByTagName("precio")


            // una vez tengamos el elemento, podemos acceder a su conteniod
            val textContentNombre = elementoNombre.item(0).textContent
            val textContentPrecio = elementoPrecio.item(0).textContent.toDouble()
            val producto = NuevoXml(textContentNombre, (textContentPrecio * 1.21))
            listaProductos.add(producto)
        }
    }

    val dip = db.domImplementation

    val document = dip.createDocument(null, "productos", null)

    listaProductos.forEach {
        val product = document.createElement("producto")
        document.documentElement.appendChild(product)

        val nombre = document.createElement("nombre")
        val precio = document.createElement("precio")

        product.appendChild(nombre)
        product.appendChild(precio)

        val textNombre = document.createTextNode(it.nombre)
        val textPreio = document.createTextNode(it.precio.toString())

        nombre.appendChild(textNombre)
        precio.appendChild(textPreio)
    }

    val source = DOMSource(document)
    val result = StreamResult(java.nio.file.Path.of("src/main/resources/document2.xml").toFile())
    val transform = TransformerFactory.newInstance().newTransformer()
    transform.setOutputProperty(OutputKeys.INDENT, "yes")
    transform.transform(source, result)
}