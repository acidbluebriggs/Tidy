package com.acidblue.collections.binpack;

import java.util.EventObject;

/**
 * Represents an event during the bin packing process.
 *
 * @param <T>
 * @see BinEventListener
 */
public class BinEvent<T extends Block<?>> extends EventObject {

    private final T block;

    public BinEvent(final Bin<T> source) {
        this(source, null);
    }

    public BinEvent(final Object source, final T block) {
        super(source);
        this.block = block;
    }

    public T getBlock() {
        return block;
    }
}
