package com.acidblue.collections.binpack;

import java.util.List;

public interface BinPacker<T extends Block<?>> {

    public abstract long getSize();

    public abstract Bin<T> add(T block);

    public abstract void addAll(List<T> list);

    public abstract List<Bin<T>> getBins();

    public abstract void addBinEventListener(BinEventListener listener);

    public abstract void removeBinEventListener(BinEventListener listener);
}
