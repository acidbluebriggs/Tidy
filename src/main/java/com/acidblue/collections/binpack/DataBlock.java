package com.acidblue.collections.binpack;

public abstract class DataBlock<T> implements Block<T> {

    private final T data;

    public DataBlock(final T data) {
        this.data = data;
    }

    public abstract long getSize();

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

    public T getData() {
        return data;
    }

}
