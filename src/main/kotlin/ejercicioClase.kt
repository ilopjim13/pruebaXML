package org.example

import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.io.path.Path

fun main() {

    val fileXml = Path("src/main/resources/empleados.xml")

    val lista = listaEmpleado(fileXml)

    lista.forEachIndexed { index, empleado ->
        if (empleado.salario >= 2000) println("Empleado $index\n\t - Nombre: ${empleado.apellido}\n\t - Dep: ${empleado.dep}\n\t - Salario: ${empleado.salario}")

    }
}

private fun listaEmpleado(fileXml: Path) : List<Empleado> {
    val dbf = DocumentBuilderFactory.newInstance()
    val db = dbf.newDocumentBuilder()
    val document = db.parse(fileXml.toFile())
    val root  = document.documentElement
    root.normalize()
    val listaNodos = root.getElementsByTagName("empleado")
    val listaEmpleados:MutableList<Empleado> = mutableListOf()

    for (i in 0..<listaNodos.length) {

        val nodo = listaNodos.item(i)

        if (nodo.nodeType == Node.ELEMENT_NODE) {

            val nodoElemento = nodo as Element

            val elementoNombre = nodoElemento.getElementsByTagName("apellido")
            val elementoDev = nodoElemento.getElementsByTagName("dep")
            val elementoSalario = nodoElemento.getElementsByTagName("salario")


            val textContentNombre = elementoNombre.item(0).textContent
            val textContentDev = elementoDev.item(0).textContent.toInt()
            val textContentSalario = elementoSalario.item(0).textContent.toDouble()

            listaEmpleados.add(Empleado(textContentNombre, textContentDev, textContentSalario))


        }
    }
    return listaEmpleados
}