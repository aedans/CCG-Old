package logic.player

/**
 * Created by Aedan Smith.
 */

interface Hand {
    fun add(s: String)

    fun remove(n: Int): String

    operator fun get(n: Int): String

    val hand: List<String>
}
