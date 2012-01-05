package com.acidblue.collections.binpack;

import java.util.EventListener;

public interface BinEventListener extends EventListener {

    public abstract void binCreated(BinEvent event);

    public abstract void itemAdded(BinEvent event);

    public abstract void itemIgnored(BinEvent event);
}
