package com.neeraj.bloomfilter;

import java.util.function.Function;

public class BloomFilterConsumer {

    public static void main(String[] args) {
        // Example HashFunctions
        Function<String, Integer> hash1 = String::hashCode;
        Function<String, Integer> hash2 = s -> s.hashCode() * 31;
        Function<String, Integer> hash3 = s -> s.hashCode() * 17;

        // Create a bloom filter
        BloomFilter<String> bloomFilter = new BloomFilter<>(1000,  hash1, hash2, hash3);

        // Add new Item
        bloomFilter.add("hello");
        bloomFilter.add("world");

        // Check elements
        System.out.println("Might contain 'hello': " + bloomFilter.mightContain("hello")); // true
        System.out.println("Might contain 'world': " + bloomFilter.mightContain("world")); // true
        System.out.println("Might contain 'java': " + bloomFilter.mightContain("java"));   // false (possibly true)
    }
}
