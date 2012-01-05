package com.acidblue.collections.binpack;

import java.util.EventListener;

public interface BinEventListener extends EventListener {

    public abstract void binCreated(BinEvent binevent);

    public abstract void itemAdded(BinEvent binevent);

    public abstract void itemIgnored(BinEvent binevent);
}
