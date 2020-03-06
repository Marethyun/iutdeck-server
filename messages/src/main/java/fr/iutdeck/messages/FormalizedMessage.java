package fr.iutdeck.messages;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Classe intermédiaire, représente un message formalisé (nom et paramètres).
 * C'est des instances de cette classe qui vont être sérialisées en JSON
 */
public final class FormalizedMessage {
    @SerializedName("name") // TODO CHANGE
    private String name;

    @SerializedName("properties") // TODO CHANGE
    private HashMap<String, Object> parameters;

    public FormalizedMessage(String name) {
        this.name = name;
        this.parameters = new HashMap<>(); // empty hashmap
    }

    public FormalizedMessage(String name, HashMap<String, Object> parameters) {
        this.name = name;
        this.parameters = parameters;
    }

    public final String toJson() {

        return null; // TODO Implémenter: problème: cela détache la mécanique de sérialisation qui devrait être gérée uniquement par le serveur
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Object> getParameters() {
        return parameters;
    }

    // TODO Change
    @Override
    public String toString() {
        return "FormalizedMessage{" +
                "name='" + name + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}
