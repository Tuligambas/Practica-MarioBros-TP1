package tp1.logic.gameobjects;

import tp1.logic.Position;

public class Land {
    private Position pos;

    public Land(Position pos){
        this.pos = pos;
    }

    public String getIcon(){
        return "▓";
    }
}
