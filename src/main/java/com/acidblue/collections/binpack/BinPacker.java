package com.acidblue.collections.binpack;

import java.util.List;

public interface BinPacker<T extends Block<T>> {

    public abstract long getSize();

    public abstract Bin<T> add(Block<T> block);

    public abstract void addAll(List<Block<T>> list);

    public abstract List<Bin<T>> getBins();

    public abstract void addBinEventListener(BinEventListener bineventlistener);

    public abstract void removeBinEventListener(BinEventListener bineventlistener);
}
