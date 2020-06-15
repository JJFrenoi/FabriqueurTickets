package fabriqueurTicketsPackage.database

import org.jetbrains.exposed.dao.id.IntIdTable

object PlatObj : IntIdTable("Plat") {

    val sequelId = integer("sequel_id")
            .uniqueIndex()
            .references(TicketObj.numeroChambre)
    val entree = varchar("Entrée" , 100).nullable()
    val feculent  = varchar("Féculent" , 100 ).nullable()
    val legume  = varchar("Légume" , 100).nullable()
    val viande  = varchar("Viande" , 100).nullable()
    val laitage  = varchar("Laitage" , 100).nullable()
    val dessert  = varchar("Dessert" , 100).nullable()
    val cafe  = varchar("Café" , 100).nullable()

}