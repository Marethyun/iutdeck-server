package fr.iutdeck.messages;

import fr.iutdeck.objects.GameObject;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class InitializeMessage implements GameMessage {
    public static final String NAME = "initialize";

    private static final String PARAMETER_IDENTIFIER = "identifier";
    private static final String PARAMETER_PARENT = "parent";
    private static final String PARAMETER_TYPE = "type";
    private static final String PARAMETER_PROPERTIES = "type";
                    // parent, object
    public final HashMap<GameObject, Map.Entry<Integer, Integer>> objects;

    public InitializeMessage(HashMap<GameObject, Map.Entry<Integer, Integer>> objects) {
        this.objects = objects;
    }

    public static final class Formalizer implements MessageFormalizer<InitializeMessage> {
        @Override
        public FormalizedMessage formalize(InitializeMessage message) {
            HashMap<String, Object>

            return new FormalizedMessage(NAME, );
        }
    }
}
