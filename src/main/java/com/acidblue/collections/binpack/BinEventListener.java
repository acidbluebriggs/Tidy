package com.acidblue.collections.binpack;

import java.util.EventListener;

/**
    Describes the callbacks that an instance of a {@link BinPacker} can invoke on its listeners.
    Clients can inspect the {@link BinEvent} to determine the data and the bins associated with the
    event.
 */
public interface BinEventListener extends EventListener {

    /**
     * A new bin was created.
     *
     * @param event The event
     */
    public abstract void binCreated(BinEvent event);

    /**
     * An item added to a bin.
     *
     * @param event The event
     */
    public abstract void itemAdded(BinEvent event);

    /**
     * An item was ignored during the process. Clients can inspect the event's
     * {@link com.acidblue.collections.binpack.BinEvent#getBlock()} value to determine why it was ignored.
     *
     * @param event The event
     */
    public abstract void itemIgnored(BinEvent event);
}
