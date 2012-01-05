package com.acidblue.collections.binpack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * One crappy test class. No junit at the moment.
 *
 * @author Briggs
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

        final RandomStringGenerator[] random = {new RandomStringGenerator(3), new RandomStringGenerator(20),new RandomStringGenerator(30)};
        
        final List<StringBlock> blocks = new ArrayList<StringBlock>();
        
        for (int i = 0; i < 400; i++) {
            final int ran = (int) (Math.random() * 3);
            final String randomString = random[ran].nextString();
            blocks.add(new StringBlock(randomString));
        }
        
        //add one long string that can't be added.
        
        final String ignoreMe = "This is an annoyingly long string that will no fit in any bins, so" +
                        " we should have an ignored block";

        blocks.add(new StringBlock("This is an annoyingly long string that will no fit in any bins, so" +
                        " we should have an ignored block"));

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
                if (ignoreMe.equals(event.getBlock().getData())) {
                    System.out.println("Expected item was ignored.");
                } else {
                    System.out.println("Whe we have tests, this would be a failure");
                }
            }
        });

        List<Bin<StringBlock>> bins = packer.packBlocks().getBins();

        System.out.println(bins.size());

    }



    public static class RandomStringGenerator
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

      public RandomStringGenerator(int length)
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
