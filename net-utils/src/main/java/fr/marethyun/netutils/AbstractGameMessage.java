package fr.marethyun.netutils;

import java.lang.reflect.Field;
import java.util.HashMap;

public class AbstractGameMessage {

    private String name;
    private HashMap<String, Object> properties;

    private AbstractGameMessage(String name, HashMap<String, Object> properties) {
        this.name = name;
        this.properties = properties;
    }

    public static AbstractGameMessage serialize(GameMessage message) {

        Class<? extends GameMessage> messageClass = message.getClass();

        MessageName nameAnnotation = messageClass.getAnnotation(MessageName.class);
        String messageName = nameAnnotation == null ?
                messageClass.isAnonymousClass() ? "anonymous_message" : messageClass.getSimpleName()
                : nameAnnotation.value();

        HashMap<String, Object> messageProperties = new HashMap<>();

        for (Field field : messageClass.getFields()) {
            MessageProperty propertyAnnotation = field.getAnnotation(MessageProperty.class);
            if (propertyAnnotation == null) continue;


        }

        return new AbstractGameMessage(null, null);
    }
}
