package com.acidblue.collections.binpack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.event.EventListenerList;

public abstract class AbstractBinPacker<T extends Block<?>> implements BinPacker<T> {

    //todo make immutable
    protected long maxBinSize;
    protected final List<Bin<T>> binList;
    private int binCount;
    protected EventListenerList listenerList;

    public AbstractBinPacker() {
        binList = new ArrayList<Bin<T>>();
        binCount = 0;
        listenerList = new EventListenerList();
    }

    public AbstractBinPacker(final long maxBinSize) {
        binList = new ArrayList<Bin<T>>();
        binCount = 0;
        listenerList = new EventListenerList();
        setMaxBinSize(maxBinSize);
        this.maxBinSize = maxBinSize;
    }

    public long getSize() {
        long size = 0L;

        for (Bin<T> aBinList : binList) {
            size += ((Bin) aBinList).getSize();
        }

        return size;
    }

    protected final Bin<T> createBin() {
        Bin<T> bin = new Bin<T>(maxBinSize, binCount);
        fireCreatedEvent(new BinEvent<T>(bin));
        binCount++;
        return bin;
    }

    public long getMaxBinSize() {
        return maxBinSize;
    }

    private void setMaxBinSize(final long maxBinSize) {
        if (maxBinSize < 1) {
            throw new IllegalArgumentException("maxBinSize was less than 1");
        } else {
            this.maxBinSize = maxBinSize;
        }
    }

    public List<Bin<T>> getBins() {
        return Collections.unmodifiableList(binList);
    }

    public void addBinEventListener(final BinEventListener listener) {
        listenerList.add(BinEventListener.class, listener);
    }

    public void removeBinEventListener(final BinEventListener listener) {
        listenerList.remove(BinEventListener.class, listener);
    }

    //look at all these 'for' loops, they all do the almost the same thing except for the final
    //call on the listener.  First class functions would be nice. But, now to do this, I have to
    //either create a stupid framework/utility whatever for it.

    protected void fireCreatedEvent(final BinEvent<T> evt) {
        final Object listeners[] = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == BinEventListener.class) {
                ((BinEventListener) listeners[i + 1]).binCreated(evt);
            }
        }
    }

    protected void fireAddedEvent(final BinEvent<T> evt) {
        final Object listeners[] = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == BinEventListener.class) {
                ((BinEventListener) listeners[i + 1]).itemAdded(evt);
            }
        }
    }

    protected void fireIgnoredEvent(final BinEvent<Block<T>> evt) {
        final Object listeners[] = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == BinEventListener.class) {
                ((BinEventListener) listeners[i + 1]).itemIgnored(evt);
            }
        }
    }
}
