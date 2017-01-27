package logic.player;

/**
 * Created by Aedan Smith.
 */

public class PlayerAction {
    private ActionType actionType;
    private String value;

    public PlayerAction(ActionType actionType, String value){
        this.actionType = actionType;
        this.value = value;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public String getValue() {
        return value;
    }

    public enum ActionType {
        END_TURN,
        DO_ACTION
    }
}
