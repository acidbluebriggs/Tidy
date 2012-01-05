package com.acidblue.collections.binpack;

/**
 *  Simply adds a new {@link #getMessage()} to a block for returning a human, or machine,
 *  readable reason that the block could not be added.
 */
public interface BadBlock<E> extends Block<E> {


    /**
     * Returns a message for the reason the block could not be used.
     *
     * @return A string
     */
    public abstract String getMessage();
}
