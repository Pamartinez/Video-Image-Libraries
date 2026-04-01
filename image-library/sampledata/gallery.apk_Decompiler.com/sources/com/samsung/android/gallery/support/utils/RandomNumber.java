package com.samsung.android.gallery.support.utils;

import java.util.Random;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class RandomNumber {
    private static final Random sRandom = new Random(System.currentTimeMillis());

    public static boolean nextBoolean() {
        return sRandom.nextBoolean();
    }

    public static int nextInt() {
        return sRandom.nextInt();
    }

    public static int nextInt(int i2) {
        return sRandom.nextInt(i2);
    }
}
