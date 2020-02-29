package fr.iutdeck.messages;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Classe intermédiaire, représente un message formalisé (nom et paramètres).
 * C'est des instances de cette classe qui vont être sérialisées en JSON
 */
public final class FormalizedMessage {
    @SerializedName("name")
    private String name;

    @SerializedName("properties")
    private HashMap<String, Object> parameters;

    public FormalizedMessage(String name) {
        this.name = name;
        this.parameters = new HashMap<>(); // empty hashmap
    }

    public FormalizedMessage(String name, HashMap<String, Object> parameters) {
        this.name = name;
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Object> getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        return "FormalizedMessage{" +
                "name='" + name + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}
