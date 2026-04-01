package com.samsung.scsp.common;

import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface Journal {
    public static final int BEGIN = 1;
    public static final int END = 2;
    public static final int ERROR = 11;

    void apply(Consumer<List<JournalItem>> consumer);

    void begin(String str, String str2);

    void end(String str, String str2);

    void error(String str, String str2, int i2);
}
