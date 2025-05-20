package fr.utc.miage.shares;

import java.util.ArrayList;
import java.util.List;


public class Portfeuille {

    List<Action> actions;

    public Portfeuille() {
        this.actions = new ArrayList<>();
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }


}
