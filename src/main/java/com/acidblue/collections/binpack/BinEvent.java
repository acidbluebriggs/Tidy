package com.acidblue.collections.binpack;

import java.util.EventObject;

public class BinEvent<T extends Block<T>> extends EventObject {

    private final Block<T> block;

    public BinEvent(final Bin<T> source) {
        this(source, null);
    }

    public BinEvent(final Bin<T> source, final Block<T> block) {
        super(source);
        this.block = block;
    }

    public Block<T> getBlock() {
        return block;
    }
}
