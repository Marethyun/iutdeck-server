package fr.iutdeck.messages.mapping;

import fr.iutdeck.messages.FormalizedMessage;

// TODO Peut-être vérifier les erreurs de cast et sortir une exception en rapport pour codifier la cause
public interface MessageMapper<T> {
    public abstract FormalizedMessage formalize(T message) throws MappingException;
    public abstract T specialize(FormalizedMessage message) throws MappingException;
}
