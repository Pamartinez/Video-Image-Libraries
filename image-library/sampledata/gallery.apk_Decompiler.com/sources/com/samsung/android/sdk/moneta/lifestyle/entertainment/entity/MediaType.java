package com.samsung.android.sdk.moneta.lifestyle.entertainment.entity;

import com.samsung.android.sdk.moneta.common.Logger;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import te.C1295a;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0002\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/MediaType;", "", "value", "", "<init>", "(Ljava/lang/String;II)V", "getValue", "()I", "MUSIC", "VIDEO", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum MediaType {
    MUSIC(1),
    VIDEO(2);
    
    public static final Companion Companion = null;
    public static final String TAG = "MediaType";
    private final int value;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/MediaType$Companion;", "", "<init>", "()V", "TAG", "", "fromValue", "Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/MediaType;", "value", "", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final MediaType fromValue(int i2) {
            for (MediaType mediaType : MediaType.getEntries()) {
                if (mediaType.getValue() == i2) {
                    return mediaType;
                }
            }
            Logger logger = Logger.INSTANCE;
            logger.e$pde_sdk_1_0_40_release(MediaType.TAG, "No matching MediaType found for value " + i2 + ". > Return MUSIC instead.");
            return MediaType.MUSIC;
        }

        private Companion() {
        }
    }

    static {
        MediaType[] $values;
        $ENTRIES = c.t($values);
        Companion = new Companion((e) null);
    }

    private MediaType(int i2) {
        this.value = i2;
    }

    public static C1295a getEntries() {
        return $ENTRIES;
    }

    public final int getValue() {
        return this.value;
    }
}
