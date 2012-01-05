package com.acidblue.collections.binpack;

/**
 * A block is a simple abstraction of something that can be measured as a long value.  A block
 * has two simple methods. First is {@link #getSize()}, which returns the size (whatever this means
 * in the context that is being used) and {@link #getData()}  which returns the actual thing which was
 * measured.
 * 
 * @param <T> The type for which the block represents.
 */
public interface Block<T> {

    /**
     * Returns the size of the block.
     *
     * @return A non-negative value.
     */
    public abstract long getSize();

    /**
     * Returns actual data represented by the Block.
     *
     * @return The data of the block
     */
    public abstract T getData();
}
