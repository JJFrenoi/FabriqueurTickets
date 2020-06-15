package fabriqueurTicketsPackage.ticket

import fabriqueurTicketsPackage.ticket.Plat
import javafx.collections.FXCollections
import javafx.scene.image.Image
import java.time.LocalDate

data class Ticket(val avatar : Image, val date: LocalDate, val numeroChambre : Int, val nom : String, val type : String, val taille : String, val plat : Plat?, val comment : String?) {
    companion object{
        val possibleSuggestionsType = FXCollections.observableArrayList<String>("VH", "MIXE" , "COUPE")
        val possibleSuggestionsTaille = FXCollections.observableArrayList<String>("Grand", "Petit" , "1/2")
    }
    fun print() = toString()
    fun avatarPath() = avatar.url.toString()
}