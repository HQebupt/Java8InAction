package lambdasinaction.chap6;

import java.util.function.*;

public class CollectorHarness {

    public static void main(String[] args) {
        long methodLegency = execute(PartitionPrimeNumbers::partitionPrimes);
        long methodCustom = execute(PartitionPrimeNumbers:: partitionPrimesWithCustomCollector);
        System.out.println("Partitioning done in: " + methodLegency + " msecs");
        System.out.println("Partitioning done with customCollector in: " + methodCustom + " msecs" );
        System.out.println("System level update:"+ ((methodLegency - methodCustom  ) / (double)methodLegency) * 100  + "%");
    }

    private static long execute(Consumer<Integer> primePartitioner) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            primePartitioner.accept(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) {
                fastest = duration;
            }
            // System.out.println("done in " + duration);
        }
        return fastest;
    }
}
