package fabriqueurTicketsPackage.database

import fabriqueurTicketsPackage.database.TicketObj.nom
import fabriqueurTicketsPackage.ticket.Plat
import fabriqueurTicketsPackage.ticket.Ticket
import javafx.scene.image.Image
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDate


class DatabaseControl  {

    val db = Database.connect("jdbc:h2:./ticketsDatabase", driver = "org.h2.Driver")


    fun pushtoDatabase(ticket: Ticket, plat: Plat){
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(TicketObj,PlatObj)

            val ticketID = TicketObj.insertAndGetId {
                it[numeroChambre] = ticket.numeroChambre
                it[pathToAvatar] = ticket.avatarPath()
                it[nom] = ticket.nom
                it[type] = ticket.type
                it[taille] = ticket.taille
                it[comment] = ticket.comment
            }

            PlatObj.insert {
                it[sequelId] = ticket.numeroChambre
                it[entree] = plat.entree
                it[feculent] = plat.feculent
                it[viande] = plat.viande
                it[legume] = plat.legume
                it[laitage] = plat.laitage
                it[cafe] = plat.cafe
                it[dessert] = plat.dessert
            }
        }
    }
    fun pullDatabase() : ArrayList<Ticket>?{
        val listTicket = ArrayList<Ticket>()
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(TicketObj,PlatObj)
            TicketObj.selectAll().forEach {
                val ticket = Ticket(
                        avatar = Image(it[TicketObj.pathToAvatar]),
                        numeroChambre = it[TicketObj.numeroChambre],
                        nom = it[TicketObj.nom],
                        type = it[TicketObj.type],
                        taille = it[TicketObj.taille],
                        plat = null,
                        comment = null
                )
                listTicket.add(ticket)
            }
        }
        return listTicket
    }
    fun destroyDataBase(){
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.drop(TicketObj,PlatObj)
        }
    }
    fun removeItem(i: Int ){
        transaction {
            addLogger(StdOutSqlLogger)
            PlatObj.deleteWhere {
                PlatObj.sequelId eq i
            }
            TicketObj.deleteWhere{
                TicketObj.numeroChambre eq i
            }
        }
    }
}