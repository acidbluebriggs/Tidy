package com.acidblue.collections.binpack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FirstFitBinPacker<T extends Block<T>> extends AbstractBinPacker<T> {

    private final Comparator<Block<T>> BLOCK_COMPARATOR = new Comparator<Block<T>>() {

        public int compare(Block<T> firstBlock, Block<T> secondBlock) {
            return Long.valueOf(secondBlock.getSize()).compareTo(firstBlock.getSize());
        }
    };


    public FirstFitBinPacker(long maxBinSize) {
        super(maxBinSize);
    }

    private void sort(List<Block<T>> blocks) {
        Collections.sort(blocks, BLOCK_COMPARATOR);
    }

    public Bin<T> add(Block<T> item) {
        if (item == null) {
            throw new NullPointerException("item cannot be null");
        }

        Bin<T> targetBin = null;
        boolean added = false;
        int i = 0;
        int j = binList.size();

        do {
            if (i >= j)
                break;
            if (added = binList.get(i).addItem(item)) {
                targetBin = binList.get(i);
                break;
            }
            i++;
        } while (true);

        if (!added) {
            targetBin = createBin();
            targetBin.addItem(item);
            binList.add(targetBin);
        }
        fireAddedEvent(new BinEvent<T>(targetBin, item));

        return targetBin;
    }

    public void addAll(final List<Block<T>> items) {

        //someone could have sent an immutable list, don't alter theirs anyway.
        final List<Block<T>> copy = new ArrayList<Block<T>>();
        copy.addAll(items);
        sort(copy);
        //do the work
        for (final Block<T> tBlock : copy) {
            add(tBlock);
        }
    }
}
