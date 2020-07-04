package fabriqueurTicketsPackage.views

import fabriqueurTicketsPackage.database.DatabaseControl
import fabriqueurTicketsPackage.pdf.Pdf
import fabriqueurTicketsPackage.ticket.Plat
import fabriqueurTicketsPackage.ticket.Ticket
import javafx.scene.control.Alert
import javafx.scene.control.ListView
import javafx.scene.control.TextField
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.stage.FileChooser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.controlsfx.control.textfield.TextFields
import tornadofx.*


class CreatorView : View("Creator View :: Tickets Maker") {
    override val root: BorderPane by fxml("/CreatorView.fxml")
    private val listView: ListView<Ticket> by fxid()
    private val center: VBox by fxid()
    private val avatar: ImageView by fxid()
    private val centerPlat: HBox by fxid()
    private val hboxNomChambre: HBox by fxid()
    private val hboxTailleType: HBox by fxid()
    private val hboxDessertCafe: HBox by fxid()
    private val enterNom: TextField by fxid()
    private val enterChambre: TextField by fxid()
    private val enterType: TextField by fxid()
    private val enterTaille: TextField by fxid()
    private val enterEntre: TextField by fxid()
    private val enterFeculent: TextField by fxid()
    private val enterViande: TextField by fxid()
    private val enterLegume: TextField by fxid()
    private val enterLaitage: TextField by fxid()
    private val enterDessert: TextField by fxid()
    private val enterComment: TextField by fxid()
    private val enterCafe: TextField by fxid()
    private val databaseControl = DatabaseControl()

    init {
        addSuggestion()
        listView.cellFormat { text = "${it.nom} ${it.numeroChambre}" }
        listView.onUserSelect(1) {
            listView.items.remove(it)
            databaseControl.removeItem(it.numeroChambre)
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
        }
        GlobalScope.launch {
            listView.items = withContext(Dispatchers.Default) { databaseControl.pullDatabase() }.toObservable()
        }
    }

    fun addToListView() {
        val filter = center.children.filterIsInstance<TextField>() + centerPlat.children.filterIsInstance<TextField>() +
                hboxNomChambre.children.filterIsInstance<TextField>() + hboxTailleType.children.filterIsInstance<TextField>() +
                hboxDessertCafe.children.filterIsInstance<TextField>()
        val plat = Plat(enterEntre.text, enterFeculent.text, enterLegume.text, enterViande.text, enterLaitage.text, enterDessert.text, enterCafe.text).apply {
            print()
        }
        val ticket = Ticket(
                avatar.image,
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
        databaseControl.pushtoDatabase(ticket, plat)
    }

    private fun addSuggestion() {
        TextFields.bindAutoCompletion(enterEntre, Plat.possibleSuggestionsEntree)
        TextFields.bindAutoCompletion(enterType, Ticket.possibleSuggestionsType)
        TextFields.bindAutoCompletion(enterTaille, Ticket.possibleSuggestionsTaille)
        TextFields.bindAutoCompletion(enterCafe, Plat.possibleSuggestionsCafe)
    }

    fun toPDF() {

        Pdf(listView.items).apply {
            whereIsMyFile()
        }

    }

    fun onClicAvatar() {
        val selectPhoto = arrayOf(FileChooser.ExtensionFilter("Fichiers Photos (*.jpeg, *.png, *.gif, *.bmp)", "*.jpeg", "*.png", "*.gif, *.bmp"))
        try {
            val chooseFile = chooseFile("Selectionner une photo", selectPhoto)
            val url = chooseFile.first().toURI().toURL().toString()
            println(url)
            avatar.image = Image(url)

        } catch (e: Exception) {
            alert(Alert.AlertType.ERROR, "Le lien vers image n'est pas valide")
        }
    }
}


