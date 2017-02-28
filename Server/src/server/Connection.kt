package server

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
        var connected = false
        Thread {
            Thread.sleep(10000)
            if (!connected) {
                println("Connection $port timed out")
                System.exit(-1)
            }
        }.start()
        socket = ServerSocket(port).accept()
        connected = true
        input = Scanner(socket!!.getInputStream())
        output = PrintStream(socket!!.getOutputStream())
        println("Connected to " + port)
    }

    fun close() = socket?.close()

    fun getInput() = input!!

    fun getOutput() = output!!
}

fun open(port: Int, num: Int): Array<Connection> {
    val connections = arrayOfNulls<Connection>(num)
    try {
        for (i in connections.indices) {
            connections[i] = Connection(port + i)
        }
        for (connection in connections) {
            connection!!.connect()
        }
        for (i in connections.indices) {
            connections[i]!!.getOutput().println(i)
        }
        println("\nEstablished connections")
        return connections.requireNoNulls()
    } catch (e: Throwable) {
        println("Exception: ${e.message}, closing connections.")
        connections.forEach { it?.close() }
        throw e
    }
}
