package com.acidblue.collections.binpack;

/**
 * Simply begins the definition of a {@link Block} by encapsulating the data accociated with the block.
 * Sublcasses need to implement the {@link #getSize()} method.
 * @param <E>
 */
public abstract class DataBlock<E> implements Block<E> {

    private final E data;

    public DataBlock(final E data) {
        this.data = data;
    }

    public String toString() {
        
        final StringBuilder builder = new StringBuilder("DataBlock[");
        
        if (data == null) {
            builder.append("null");
        } else {
            builder.append("size=")
                    .append(getSize())
                    .append(", data=")
                    .append(data.toString());
        }

        builder.append("]");

        return builder.toString();
    }

    public E getData() {
        return data;
    }

}
