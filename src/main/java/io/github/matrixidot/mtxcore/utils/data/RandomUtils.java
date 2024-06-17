package io.github.matrixidot.mtxcore.utils.data;

import java.util.List;
import java.util.Random;

public class RandomUtils {
    private static final Random random = new Random();
    public static <T> T randomItem(List<T> list) {
        return list.get(random.nextInt(0, list.size()));
    }

    public static <T> T randomItem(T[] arr) {
        return arr[random.nextInt(0, arr.length)];
    }

    public static <T> T pullRandomItem(List<T> list) {
        int index = random.nextInt(0, list.size());
        T obj = list.get(index);
        list.remove(index);
        return obj;
    }

    public static int random(int min, int max) {
        return random.nextInt(min, max);
    }

    public static int[] randomNums(int min, int max, int size) {
        int[] nums = new int[size];
        for (int i = 0; i < size; i++)
            nums[i] = random(min, max);
        return nums;
    }
}
