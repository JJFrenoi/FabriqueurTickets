package fabriqueurTicketsPackage.pdf

import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.*
import com.itextpdf.layout.property.HorizontalAlignment
import com.itextpdf.layout.property.TextAlignment
import fabriqueurTicketsPackage.ticket.Ticket
import javafx.collections.ObservableList
import javafx.scene.control.Alert
import tornadofx.*

class Pdf(residentsList:ObservableList<Ticket> ) {
    private val destination = "/home/jean/Documents/test.pdf"
    private val center = TextAlignment.CENTER
    init {
        try {

            /*val dir = chooseDirectory("Select Target Directory").toString()

            println(dir)*/

            val pdfDoc = PdfDocument(PdfWriter(destination))

            val doc = Document(pdfDoc)

            val pointColumnWidths = floatArrayOf(150f , 150f ,150f )
            val table = Table(pointColumnWidths)
            if (residentsList.size > 0 ){
                residentsList.forEach {
                    val cell = Cell()
                    val image = Image(ImageDataFactory.create(it.avatar.url))
                    image.scaleToFit(128f,97f)
                    image.setHorizontalAlignment(HorizontalAlignment.CENTER)
                    cell.add(image)
                    cell.add(Paragraph(it.date.toString()) )
                    cell.add(Paragraph("${it.numeroChambre} ${it.nom}"))
                    cell.add(Paragraph("${it.taille} ${it.type}"))
                    cell.add(Paragraph(it.plat?.entree ))
                    cell.add(Paragraph("${it.plat?.feculent} ${it.plat?.viande} ${it.plat?.legume}"))
                    cell.add(Paragraph(it.plat?.laitage))
                    cell.add(Paragraph("${it.plat?.dessert} ${it.plat?.cafe}"))
                    cell.add(Paragraph(it.comment))
                    cell.setTextAlignment(center)
                    table.addCell(cell)
                }

            }
            doc.add(table)

            doc.close()

        }catch (e : Exception){
            e.printStackTrace()
        }
    }
    fun whereIsMyFile(){
        alert(
                Alert.AlertType.INFORMATION,
                "Sauvegardé dans",
                destination
        )
    }
}