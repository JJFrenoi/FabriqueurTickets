package fabriqueurTicketsPackage.views

import fabriqueurTicketsPackage.pdf.Pdf
import fabriqueurTicketsPackage.ticket.Plat
import fabriqueurTicketsPackage.ticket.Ticket
import javafx.scene.control.Alert
import javafx.scene.control.DatePicker
import javafx.scene.control.ListView
import javafx.scene.control.TextField
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.stage.FileChooser
import javafx.util.converter.LocalDateStringConverter
import org.controlsfx.control.textfield.TextFields
import tornadofx.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class CreatorView: View("Creator View :: Tickets Maker") {
    override val root : BorderPane by fxml("/CreatorView.fxml")
    private val listView : ListView<Ticket> by fxid()
    private val center : VBox by fxid()
    private val avatar : ImageView by fxid()
    private val centerPlat : HBox by fxid()
    private val hboxNomChambre : HBox by fxid()
    private val hboxTailleType : HBox by fxid()
    private val hboxDessertCafe : HBox by fxid()
    private val enterNom: TextField by fxid()
    private val enterChambre: TextField by fxid()
    private val enterType : TextField by fxid()
    private val enterTaille : TextField by fxid()
    private val enterEntre: TextField by fxid()
    private val enterFeculent: TextField by fxid()
    private val enterViande: TextField by fxid()
    private val enterLegume: TextField by fxid()
    private val enterLaitage: TextField by fxid()
    private val enterDessert: TextField by fxid()
    private val enterComment: TextField by fxid()
    private val enterDate : DatePicker by fxid()
    private val enterCafe : TextField by fxid()
    init {
        val pattern = "dd-MM-yyyy"
        val dateFormatter = DateTimeFormatter.ofPattern(pattern)
        enterDate.converter = LocalDateStringConverter(dateFormatter, dateFormatter)
        addSuggestion()
        enterDate.value = LocalDate.now().plusDays(1)
        listView.cellFormat { text = "${it.nom} ${it.numeroChambre}" }
        listView.onUserSelect(1) {
            listView.items.remove(it)
            enterNom.text = it.nom
            enterChambre.text = it.numeroChambre.toString()
            enterType.text = it.type
            enterTaille.text = it.taille
            enterEntre.text = it.plat?.entree
            enterFeculent.text = it.plat?.feculent
            enterViande.text = it.plat?.viande
            enterLegume.text = it.plat?.legume
            enterDessert.text = it.plat?.dessert
            enterComment.text = it.comment
            enterLaitage.text = it.plat?.laitage
            enterDate.value = it.date
        }
    }
    fun addToListView() {
        val filter = center.children.filterIsInstance<TextField>() + centerPlat.children.filterIsInstance<TextField>() +
                hboxNomChambre.children.filterIsInstance<TextField>() + hboxTailleType.children.filterIsInstance<TextField>()+
                hboxDessertCafe.children.filterIsInstance<TextField>()
        val plat = Plat(enterEntre.text, enterFeculent.text, enterLegume.text, enterViande.text, enterLaitage.text, enterDessert.text, enterCafe.text).apply {
            print()
        }
        val ticket = Ticket(
                avatar.image,
                enterDate.value,
                enterChambre.text.toInt(),
                enterNom.text.toUpperCase(),
                enterType.text,
                enterTaille.text,
                plat,
                enterComment.text)
        listView.items.add(ticket)
        filter.forEach {
            it.clear()
        }
        enterDate.value = LocalDate.now().plusDays(1)
    }
    fun addSuggestion(){
        TextFields.bindAutoCompletion(enterEntre, Plat.possibleSuggestionsEntree)
        TextFields.bindAutoCompletion(enterType, Ticket.possibleSuggestionsType)
        TextFields.bindAutoCompletion(enterTaille, Ticket.possibleSuggestionsTaille)
        TextFields.bindAutoCompletion(enterCafe, Plat.possibleSuggestionsCafe)
    }
    fun toPDF(){
        val pdf = Pdf(listView.items)
        pdf.whereIsMyFile()
    }
    fun onClicAvatar(){
        val selectPhoto = arrayOf(FileChooser.ExtensionFilter("Fichiers Photos (*.jpeg, *.png, *.gif, *.bmp)", "*.jpeg", "*.png", "*.gif, *.bmp"))
        try {
            val chooseFile = chooseFile("Selectionner une photo",selectPhoto)
            val url = chooseFile.first().toURI().toURL().toString()
            println(url)
            val image = Image(url)
            avatar.image = image

        }catch (e : Exception){
            alert(Alert.AlertType.ERROR , "Le lien vers image n'est pas valide")
        }
    }

}


