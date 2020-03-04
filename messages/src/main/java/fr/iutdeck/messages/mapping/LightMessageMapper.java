package fr.iutdeck.messages.mapping;

import fr.iutdeck.messages.FormalizedMessage;

public final class LightMessageMapper<T> extends MessageMapper<T> {

    private final String name;
    private final InstanceSupplier<T> supplier;

    public LightMessageMapper(String name, InstanceSupplier<T> supplier) {
        this.name = name;
        this.supplier = supplier;
    }

    @Override
    public FormalizedMessage formalize(T message) throws MappingException {
        return new FormalizedMessage(this.name, null);
    }

    @Override
    public T specialize(FormalizedMessage message) throws MappingException {
        if (!message.getName().equals(this.name)) throw new NameMismatchException(this);
        return this.supplier.supply();
    }

    public interface InstanceSupplier<M> {
        M supply();
    }
}
