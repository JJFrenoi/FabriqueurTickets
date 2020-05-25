package fabriqueurTicketsPackage.database

import org.jetbrains.exposed.dao.id.IntIdTable

object PlatObj : IntIdTable("Plat") {
    val sequelId = integer("sequel_id")
            .uniqueIndex()
            .references(TicketObj.numeroChambre)
    val entree = varchar("Entrée" , 100)
    val feculent  = varchar("Féculent" , 100 )
    val legume  = varchar("Légume" , 100)
    val viande  = varchar("Viande" , 100)
    val laitage  = varchar("Laitage" , 100)
    val dessert  = varchar("Dessert" , 100)
    val cafe  = varchar("Café" , 100)

}