package fabriqueurTicketsPackage.ticket

import javafx.collections.FXCollections


class Plat (entree : String?,feculent : String?,legume : String?,viande : String?,laitage : String?,dessert :String?,cafe : String?){
    val entree = entree
    val feculent  = feculent
    val legume  = legume
    val viande  = viande
    val laitage  = laitage
    val dessert  = dessert
    val cafe  = cafe
    companion object{
        val possibleSuggestionsEntree = FXCollections.observableArrayList<String>("Carrotes", "Salade" , "Tomate")
        val possibleSuggestionsCafe = FXCollections.observableArrayList<String>("Deca", "Normal" , "Thé" , "Long")
    }

}