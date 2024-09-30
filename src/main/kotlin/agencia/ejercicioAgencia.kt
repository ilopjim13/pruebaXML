package org.example.agencia

import org.w3c.dom.Element
import org.w3c.dom.Node
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.io.path.Path

fun main() {

    val fileXml = Path("src/main/resources/agencia.xml")
    val lista = listaNoticia(fileXml)

    lista.forEachIndexed  { index, noticia ->
    println("Noticia $index\n\t - Title: ${noticia.title}\n\t - Link: ${noticia.link}\n\t - Guid: ${noticia.guid}\n\t - PubDate: ${noticia.pubDate}\n\t - Description:  ${noticia.description.replace("<p>", "").replace("</p>", "")}")
    }

}

private fun listaNoticia(fileXml:Path) :List<Noticia> {
    val dbf = DocumentBuilderFactory.newInstance()
    val db = dbf.newDocumentBuilder()
    val document = db.parse(fileXml.toFile())
    val root  = document.documentElement
    root.normalize()
    val listaNodos = root.getElementsByTagName("item")

    val listaNoticia:MutableList<Noticia> = mutableListOf()

    for (i in 0..<listaNodos.length) {

        val nodo = listaNodos.item(i)

        if (nodo.nodeType == Node.ELEMENT_NODE) {

            val nodoElemento = nodo as Element

            val elementoTitle = nodoElemento.getElementsByTagName("title")
            val elementoLink = nodoElemento.getElementsByTagName("link")
            val elementoGuid = nodoElemento.getElementsByTagName("guid")
            val elementoPubDate = nodoElemento.getElementsByTagName("pubDate")
            val elementoDescription = nodoElemento.getElementsByTagName("description")

            val textContentTitle = elementoTitle.item(0).textContent
            val textContentLink = elementoLink.item(0).textContent
            val textContentGuid = elementoGuid.item(0).textContent
            val textContentPubDate = elementoPubDate.item(0).textContent
            val textContentDescription = elementoDescription.item(0).textContent

            listaNoticia.add(Noticia(textContentTitle, textContentLink, textContentGuid, textContentPubDate, textContentDescription))

        }
    }
    return listaNoticia
}

