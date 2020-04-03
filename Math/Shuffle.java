package Math;


public class Shuffle {
    public void shuffle(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        int len = array.length;
        for (int i = 0; i < len; ++i) {
            int index = (int) (Math.random() * (len - i) + i);
            swap(array, i, index);
        }
    }

    private void swap(int[] array, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
