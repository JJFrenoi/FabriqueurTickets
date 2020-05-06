package fabriqueurTicketsPackage

import javafx.scene.control.DatePicker
import javafx.scene.control.ListView
import javafx.scene.control.SelectionMode
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import tornadofx.*


class CreatorView: View("Creator View :: Tickets Maker") {
    override val root : BorderPane by fxml("/CreatorView.fxml")
    private val listView : ListView<Ticket> by fxid()
    private val center : VBox by fxid()
    private val centerPlat : HBox by fxid()
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
    init {
        button( messages["OK"]) {
            setOnAction { addToListView() }
        }
    }
    fun addToListView() {
        val filter = center.children.filterIsInstance<TextField>() + centerPlat.children.filterIsInstance<TextField>()
        val plat = arrayOf(enterFeculent.text,enterViande.text,enterLegume.text)
        val ticket = Ticket(enterDate.toString(),
                enterChambre.text.toInt(),
                enterNom.text,
                enterType.text,
                enterEntre.text,
                enterTaille.text,
                plat,
                enterLaitage.text,
                enterDessert.text,
                enterComment.text)
        listView.items.add(ticket)
        listView.cellFormat { text = "${it.nom} ${it.numeroChambre}" }
        filter.forEach {
            it.clear()
        }
    }

}


