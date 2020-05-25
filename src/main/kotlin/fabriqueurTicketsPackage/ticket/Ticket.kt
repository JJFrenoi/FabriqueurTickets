package fabriqueurTicketsPackage.ticket

import fabriqueurTicketsPackage.ticket.Plat
import javafx.collections.FXCollections
import javafx.scene.image.Image
import java.time.LocalDate

class Ticket(avatar : Image, date: LocalDate, numeroChambre : Int, nom : String, type : String, taille : String, plat : Plat?, comment : String?) {
    val avatar = avatar
    val date = date
    val numeroChambre = numeroChambre
    val nom = nom
    val type = type
    val taille = taille
    val plat = plat
    val comment = comment
    companion object{
        val possibleSuggestionsType = FXCollections.observableArrayList<String>("VH", "MIXE" , "COUPE")
        val possibleSuggestionsTaille = FXCollections.observableArrayList<String>("Grand", "Petit" , "1/2")
    }
}