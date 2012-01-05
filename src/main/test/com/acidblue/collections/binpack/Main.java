package com.acidblue.collections.binpack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * One crappy test class. No junit at the moment.
 *
 * @author Marc Miller
 * @since 2.0
 */
public class Main {

    private static class StringBlock implements Block<String> {
        
        private String data;
        
        public StringBlock(final String str) {
            this.data = str;
        }
        
        public String getData() {
            return data;
        }

        public long getSize() {
            return data.length();
        }
    }
    
    public static void main(String[] args) {

        final RandomString[] random = {new RandomString(10), new RandomString(20),new RandomString(30)};
        
        final List<StringBlock> blocks = new ArrayList<StringBlock>();
        
        for (int i = 0; i < 400; i++) {
            final int ran = (int) (Math.random() * 3);
            blocks.add(new StringBlock(random[ran].nextString()));
        }

        VetoableBlockBinPacker<StringBlock> packer = new VetoableBlockBinPacker<StringBlock>(blocks, 60, 10);

        packer.addBinEventListener(new BinEventListener() {
            public void binCreated(final BinEvent event) {
                System.out.println("Bin Created");
            }

            public void itemAdded(final BinEvent event) {
                System.out.println("Item added");
            }

            public void itemIgnored(final BinEvent event) {
                System.out.println("Item ignored");
            }
        });

        List<Bin<StringBlock>> bins = packer.packBlocks().getBins();

        System.out.println(bins.size());

    }



    public static class RandomString
    {

      private static final char[] symbols = new char[36];

      static {
        for (int idx = 0; idx < 10; ++idx)
          symbols[idx] = (char) ('0' + idx);
        for (int idx = 10; idx < 36; ++idx)
          symbols[idx] = (char) ('a' + idx - 10);
      }

      private final Random random = new Random();

      private final char[] buf;

      public RandomString(int length)
      {
        if (length < 1)
          throw new IllegalArgumentException("length < 1: " + length);
        buf = new char[length];
      }

      public String nextString()
      {
        for (int idx = 0; idx < buf.length; ++idx)
          buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
      }

    }
}
