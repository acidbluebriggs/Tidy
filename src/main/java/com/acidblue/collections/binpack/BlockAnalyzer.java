package com.acidblue.collections.binpack;

public class BlockAnalyzer<T> {

    protected static final float ONE_HUNDRED_PERCENT = 100.0F;
    //don't mutate these.
    private float fragmentationThreshold;
    private long optimalSize;

    public BlockAnalyzer(final float fragmentationThreshold, final long optimalBlockSize) {
        setFragmentationThreshold(fragmentationThreshold);
        setOptimalSize(optimalBlockSize);
    }

    public boolean isFragment(final Block<T> block) {
        return (block.getSize() < getOptimalSize()) &&
                (percentage((float) getOptimalSize(), (float) block.getSize()) >= getFragmentationThreshold());
    }

    public static float percentage(final float a, final float b) {
        return a == b ? ONE_HUNDRED_PERCENT : (a - b) / a * ONE_HUNDRED_PERCENT;
    }

    public float getFragmentationThreshold() {
        return this.fragmentationThreshold;
    }

    private void setFragmentationThreshold(float fragmentationThreshold) {
        if (fragmentationThreshold < 0.0F) {
            throw new IllegalArgumentException("fragmentationThreshold must be a positive value. Value passed was = "
                    + fragmentationThreshold);
        }

        this.fragmentationThreshold = fragmentationThreshold;
    }

    private void setOptimalSize(long optimalSize) {
        if (optimalSize < 1L) {
            throw new IllegalArgumentException("optimalSize must be a positive value. Value passed was = " + optimalSize);
        }

        this.optimalSize = optimalSize;
    }

    public long getOptimalSize() {
        return this.optimalSize;
    }
}