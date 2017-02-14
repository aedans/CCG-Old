package logic.player.externalplayer

import logic.player.Mana
import server.Connection
import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty

/**
 * Created by Aedan Smith.
 */

class ExternalMana(connection: Connection) : Mana {
    override var u by writeOnChange(0, connection) { new -> commands.getString("SET_MANA_U") + new }
    override var b by writeOnChange(0, connection) { new -> commands.getString("SET_MANA_B") + new }
    override var r by writeOnChange(0, connection) { new -> commands.getString("SET_MANA_R") + new }
    override var g by writeOnChange(0, connection) { new -> commands.getString("SET_MANA_G") + new }
    override var amount by writeOnChange(0, connection) { new -> commands.getString("SET_MANA_AMOUNT") + new }
    override var current by writeOnChange(0, connection) { new -> commands.getString("SET_MANA_CURRENT") + new }
}

fun <T> writeOnChange(initial: T, connection: Connection, toWrite: (newValue: T) -> String): ReadWriteProperty<Any?, T> {
    return Delegates.observable(initial) {
        _, _, new ->
        connection.getOutput().println(toWrite.invoke(new))
    }
}
