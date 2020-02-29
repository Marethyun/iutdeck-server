package com.iutdeck.server.objects;

import java.util.List;

public interface Modifiable<T> {
    void modify(Modifier<T> modifier);

}
