package fabriqueurTicketsPackage.database

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.jodatime.date

object TicketObj : IntIdTable("Ticket") {
    val numeroChambre= integer("numeroChambre").uniqueIndex()
    val nom= varchar("Nom", 50)
    val type = varchar("Type" , 20)
    val taille = varchar("Taille",20)
    val pathToAvatar = varchar("Avatar" , 100).nullable()
    val comment = varchar("Commentaire", 100).nullable()

}