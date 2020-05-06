package fabriqueurTicketsPackage

class Ticket(date: String, numeroChambre : Int,nom : String,type : String,taille : String,entree : String?,plat : Array<String>?,laitage : String?,dessert :String?,comment : String?) {
    val date : String = date
    val numeroChambre : Int = numeroChambre
    val nom : String = nom
    val type : String = type
    val taille : String = taille
    val entree : String? = entree
    val plat : Array<String>? = plat
    val laitage : String? = laitage
    val dessert :String? = dessert
    val comment : String? = comment
}