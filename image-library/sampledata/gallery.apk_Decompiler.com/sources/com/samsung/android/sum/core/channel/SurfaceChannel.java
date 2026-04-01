package com.samsung.android.sum.core.channel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SurfaceChannel extends BufferChannel {
    public static final int DEFAULT_IMAGES = 16;
    public static final int MAX_IMAGES = 62;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum State {
        OPEN,
        CANCELED,
        CLOSED,
        SUSPEND
    }

    void configure(SurfaceChannelConfig surfaceChannelConfig);

    int getFormat();

    int getHeight();

    long getUsage();

    int getWidth();

    boolean isRequireToConfigure();

    boolean isRequireToConfigure(int i2, int i7, int i8, long j2);

    void reset();
}
