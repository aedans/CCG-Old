package logic.player

/**
 * Created by Aedan Smith.
 */

class PlayerAction(val actionType: PlayerAction.ActionType, val value: String) {
    enum class ActionType {
        END_TURN,
        DO_ACTION
    }
}
