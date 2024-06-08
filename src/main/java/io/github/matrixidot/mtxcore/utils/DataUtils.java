package io.github.matrixidot.mtxcore.utils;

import java.util.*;

public class DataUtils {
    public static <T> boolean addIfAbsent(Collection<T> coll, T element) {
        if (coll.contains(element)) return false;
        return coll.add(element);
    }

    public static String listElementFormat(Collection<?> coll, String format) {
        StringBuilder names = new StringBuilder();
        coll.forEach(o -> names.append(o.toString()).append(format));
        return names.toString().trim();
    }
}
