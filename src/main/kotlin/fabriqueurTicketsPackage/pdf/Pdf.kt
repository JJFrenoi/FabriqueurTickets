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
    private val destination = "/home/jeanjean/Documents/test.pdf"
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
                    val image = Image(ImageDataFactory.create(it.avatar.url)).apply {
                        scaleToFit(128f,97f)
                        setHorizontalAlignment(HorizontalAlignment.CENTER)
                    }
                    cell.apply{
                        add(image)
                        add(Paragraph(it.date.toString()) )
                        add(Paragraph("${it.numeroChambre} ${it.nom}"))
                        add(Paragraph("${it.taille} ${it.type}"))
                        add(Paragraph(it.plat?.entree ))
                        add(Paragraph("${it.plat?.feculent} ${it.plat?.viande} ${it.plat?.legume}"))
                        add(Paragraph(it.plat?.laitage))
                        add(Paragraph("${it.plat?.dessert} ${it.plat?.cafe}"))
                        add(Paragraph(it.comment))
                        setTextAlignment(center)
                    }
                    table.addCell(cell)
                }

            }
            doc.apply {
                add(table)
                close()
            }

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