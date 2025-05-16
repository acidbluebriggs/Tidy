package com.acidblue.collections.binpack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bin<T extends Block<?>> {

    private final long limit;
    private long size;
    private int id;
    private final List<T> items = new ArrayList<T>();

    protected Bin(long sizeLimit, int id) {
        this.id = -1;
        if (sizeLimit < 1L) {
            throw new IllegalArgumentException("sizeLimit must be a positive value");
        } else {
            limit = sizeLimit;
            setId(id);
        }
    }

    public boolean addItem(T item) {
        final boolean added;
        
        if (item == null) {
            added = false;
        } else if (isRoom(item)) {
            size += item.getSize();
            added = items.add(item);
        } else {
            added = false;
        }

        return added;
    }

    public List<T> getItems() {
        return Collections.unmodifiableList(items);
    }

    public long getSize() {
        return size;
    }

    protected boolean isRoom(Block<?> item) {
        return size + item.getSize() <= limit;
    }

    protected final void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
