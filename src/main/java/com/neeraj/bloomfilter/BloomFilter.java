package com.neeraj.bloomfilter;

import java.util.BitSet;
import java.util.function.Function;

public class BloomFilter<T> {

    private final BitSet bitSet;
    private final int bitSetSize;
    private final Function<T, Integer>[] hashFunctions;

    public BloomFilter(int bitSetSize, Function<T, Integer>... hashFunctions) {
        this.bitSetSize = bitSetSize;
        this.hashFunctions = hashFunctions;
        this.bitSet = new BitSet(bitSetSize);
    }

    // Add item to bloomFilter
    public void add(T element) {
        for(Function<T, Integer> hashFunction : hashFunctions) {
            int hash = hashFunction.apply(element) % bitSetSize;
            bitSet.set(Math.abs(hash));
        }
    }

    // If Item Exist
    public boolean mightContain(T element) {
        for(Function<T, Integer> hashFunction : hashFunctions) {
            int hash = hashFunction.apply(element) % bitSetSize;
            if (!bitSet.get(Math.abs(hash))) {
                return false;
            }
        }
        return true;
    }
}
