package connection

import java.io.IOException
import java.io.PrintStream
import java.net.ServerSocket
import java.net.Socket
import java.util.*

/**
 * Created by Aedan Smith.
 */

class Connection(private val port: Int) {
    private var socket: Socket? = null
    private var input: Scanner? = null
    private var output: PrintStream? = null

    fun connect() {
        println("Connecting to " + port)
        Thread {
            try {
                socket = ServerSocket(port).accept()
                input = Scanner(socket!!.getInputStream())
                output = PrintStream(socket!!.getOutputStream())
                println("Connected to " + port)
            } catch (e: IOException) {
                e.printStackTrace()
                System.exit(-1)
            }
        }.start()
    }

    fun waitUntilConnected() {
        while (socket == null) {
            Thread.sleep(500)
            print(".")
        }
    }

    fun close() = socket!!.close()

    fun getInput() = input!!

    fun getOutput() = output!!
}

fun open(port: Int, num: Int): Array<Connection> {
    val connections = arrayOfNulls<Connection>(num)
    for (i in connections.indices) {
        connections[i] = Connection(port + i)
    }
    for (connection in connections) {
        connection!!.connect()
    }
    for (connection in connections) {
        connection!!.waitUntilConnected()
    }
    println("\nEstablished connections")
    return connections.requireNoNulls()
}
