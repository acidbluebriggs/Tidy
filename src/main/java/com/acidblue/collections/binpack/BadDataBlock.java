package com.acidblue.collections.binpack;

public class BadDataBlock<E> extends DataBlock<E> implements BadBlock {

    private String message;

    public BadDataBlock(final E data, final String message) {
        super(data);
        this.setMessage(message);
    }

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
