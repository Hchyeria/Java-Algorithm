package Math;

/*
    Consider an unlimited flow of data elements. How do you sample one element from this flow,
    such that at any point during the processing of the flow, you can return a random element from the n
    elements read so far.

    You will implement two methods for a sampling class:
    read(int value) - read one number from the flow
    getSample() - return at any time the sample, if n values have been read,
    the probability of returning any one of the n values is 1/n, return null(Java)/INT_MIN(C++)
    if there is no value read so far
    You may need to add more fields for the class.
*/

import java.util.ArrayList;
import java.util.List;

public class ReservoirSampling {
    private int counter;
    private Integer sample;
    private List<Integer> sample2;
    private int k;

    public ReservoirSampling(int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("k <= 0");
        }
        this.counter = 0;
        this.sample = null;
        this.sample2 = new ArrayList<>();
        this.k = k;
    }

    public void read(int value) {
        counter++;
        int prob = (int)(Math.random() * counter);
        if (prob == 0) {
            this.sample = value;
        }
    }

    public Integer getSample() {
        return this.sample;
    }

    // How about return k sample?
    public void read2(int value) {
        counter++;
        if (counter - 1 < k) {
            this.sample2.add(value);
        }

        int prob = (int)(Math.random() * counter);
        if (prob < k) {
            this.sample2.set(prob, value);
        }
    }
}
