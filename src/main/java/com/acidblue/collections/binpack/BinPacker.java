package com.acidblue.collections.binpack;

import java.util.List;

/**
 * Describes the operations needed for a a Bin Packing API.
 * @param <T>
 */
public interface BinPacker<T extends Block<?>> {

    /**
     * The number of bins currently in use by the bin packer.
     *
     * @return A non-negative value
     */
    public abstract long getSize();

    /**
     * Adds the given block to the bin packer.
     *
     * @param block A block (non-null)
     * @return A reference to the Bin which the block was added
     */
    public abstract Bin<T> add(T block);

    /**
     * Adds all the given items to the bin packer.
     *
     * @param list A list of items (non-null)
     */
    public abstract void addAll(List<T> list);

    /**
     * Returns all the bins (with their associated blocks) in the bin packer.
     *
     * @return A non-null value
     */
    public abstract List<Bin<T>> getBins();

    /**
     * Adds an event listener to the bin packer.
     *
     * @param listener A listener (non-null)
     */
    public abstract void addBinEventListener(BinEventListener listener);

    /**
     * Removes an event listener from the bin packer.
     *
     * @param listener A listener (non-null)
     */
    public abstract void removeBinEventListener(BinEventListener listener);
}
