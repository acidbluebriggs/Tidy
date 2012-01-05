package com.acidblue.collections.binpack;

/**
 * A block which encapsulates data which could not be used by a {@link BinPacker} instance.
 * Clients inspect the {@link #getMessage()} to determine the reason.
 * @param <E>
 */
public class BadDataBlock<E> extends DataBlock<E> implements BadBlock<E> {

    private String message;

    /**
     * Creates a new block with the data to be encapsulated and a mandatory
     * message as to why it could not be added.
     * @param data The data, which failed packing, to encapsulate
     * @param message The reason the block could not be packed
     */
    public BadDataBlock(final E data, final String message) {
        super(data);
        this.setMessage(message);
    }

    /**
     * Returns a reason, either human or machine readable, why the encapsulated data could not packed by the
     * bin packer.
     *
     * @return Some string message
     */
    public String getMessage() {
        return message;
    }

    private void setMessage(final String message) {
        if (message == null || message.length() == 0) {
            throw new IllegalArgumentException("message was not supplied.");
        }

        this.message = message;
    }

    public long getSize() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot call size on a bad block");
    }

    public String toString() {

        final StringBuilder builder = new StringBuilder("BadDataBlock[size=UNKNOWN, data=");

        if (getData() != null) {
            builder.append(getData().toString());
        } else {
            builder.append("null");
        }
        
        builder.append("]");

        return builder.toString();
    }

}
