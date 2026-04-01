package com.samsung.android.sum.core.functional;

import android.util.Pair;
import java.util.regex.Pattern;

@FunctionalInterface
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ModelPathLoader {
    Pair<String, Pattern> load(String str);
}
