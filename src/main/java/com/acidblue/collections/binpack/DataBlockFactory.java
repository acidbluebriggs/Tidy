package com.acidblue.collections.binpack;

/**
 * A factory which returns encapsulated data items within a block. A client simply invokes the
 * {@link #dataBlock(Object, com.acidblue.collections.binpack.DataBlockFactory.LengthCalc)}
 * while providing the factor an instance of a {@link LengthCalc}.
 *
 * @author Briggs
 * @since 2.0
 */
public class DataBlockFactory {

    private DataBlockFactory(){
        //Singleton
    }


    /**
     * Defines a single method ({@link #getLength(Object)} which returns the length of the Object provided.
     *
     * @param <E> The type of data encapsulated within the block.
     */
    public static interface LengthCalc<E> {
        public long getLength(E item);
    }

    /**
     * Returns a data block ({@link DataBlock}.
     *
     * @param data The data encapsulated within the block
     * @param calc The calculation for determining the length of the block
     * @param <E> The type encapsulated within the block
     * @return A newly created data block
     */
    public static <E> DataBlock<E> dataBlock(final E data, final LengthCalc<E> calc) {
        return new InternalDataBlock<E>(data, calc);
    }

    private static class InternalDataBlock<E> extends DataBlock<E> {
        private final LengthCalc<E> calc;
        
        public InternalDataBlock(final E data, final LengthCalc<E> calc) {
            super(data);
            if (calc == null) {
                throw new NullPointerException("calc was null");
            }

            this.calc = calc;
        }

        @Override
        public long getSize() {
            return calc.getLength(this.getData());
        }
    }
    

}
