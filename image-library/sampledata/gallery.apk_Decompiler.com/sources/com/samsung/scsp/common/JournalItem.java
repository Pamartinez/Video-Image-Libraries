package com.samsung.scsp.common;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class JournalItem {
    public final int code;
    public final String from;
    public final String name;
    public final long timestamp;
    public final int type;

    private JournalItem(String str, String str2, int i2) {
        this.from = str;
        this.name = str2;
        this.timestamp = System.currentTimeMillis();
        this.code = 0;
        this.type = i2;
    }

    public static JournalItem begin(String str, String str2) {
        return new JournalItem(str, str2, 1);
    }

    public static JournalItem end(String str, String str2) {
        return new JournalItem(str, str2, 2);
    }

    public static JournalItem error(String str, String str2, int i2) {
        return new JournalItem(str, str2, 11, i2);
    }

    public String toString() {
        return String.format("%s, %s, %d, %d, %d", new Object[]{this.from, this.name, Integer.valueOf(this.type), Integer.valueOf(this.code), Long.valueOf(this.timestamp)});
    }

    private JournalItem(String str, String str2, int i2, int i7) {
        this.from = str;
        this.name = str2;
        this.timestamp = System.currentTimeMillis();
        this.code = i7;
        this.type = i2;
    }
}
