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
}
