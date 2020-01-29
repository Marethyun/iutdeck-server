package com.iutdeck.server.objects;

public interface Castable {
    /**
     * @param target L'objet sur lequel on cast l'élément
     * @return true si le cast est réalisable, false sinon
     */
    boolean cast(/* ctx ou game */GameObject target);
}
