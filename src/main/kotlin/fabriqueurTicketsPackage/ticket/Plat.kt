package fabriqueurTicketsPackage.ticket

import javafx.collections.FXCollections


data class Plat (val entree: String?, val feculent: String?, val legume: String?, val viande: String?, val laitage: String?, val dessert: String?, val cafe: String?){

    companion object{
        val possibleSuggestionsEntree = FXCollections.observableArrayList<String>("Carrotes", "Salade" , "Tomate")
        val possibleSuggestionsCafe = FXCollections.observableArrayList<String>("Deca", "Normal" , "Thé" , "Long")
    }
    fun print() = toString()
}