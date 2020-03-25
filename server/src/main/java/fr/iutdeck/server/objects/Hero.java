package fr.iutdeck.server.objects;

import fr.iutdeck.objects.GameObject;

import java.util.HashMap;

public final class Hero extends GameObject {

    // package-level access
    int hp = 20; // TODO S'occuper de ça

    // TODO De ça aussi
    @Override
    public HashMap<String, Object> parameters() {
        return null;
    }
}
