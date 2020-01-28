package com.iutdeck.server;

import java.util.HashMap;

public interface Generalizable {
    /**
     * Generalize, flatten into a map
     */
    HashMap<String, Object> generalize();
}
