package androidx.media3.common;

import android.media.AudioAttributes;
import android.os.Build;
import androidx.media3.common.util.Util;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AudioAttributes {
    public static final AudioAttributes DEFAULT = new Builder().build();
    private static final String FIELD_ALLOWED_CAPTURE_POLICY = Util.intToStringMaxRadix(3);
    private static final String FIELD_CONTENT_TYPE = Util.intToStringMaxRadix(0);
    private static final String FIELD_FLAGS = Util.intToStringMaxRadix(1);
    private static final String FIELD_IS_CONTENT_SPATIALIZED = Util.intToStringMaxRadix(5);
    private static final String FIELD_SPATIALIZATION_BEHAVIOR = Util.intToStringMaxRadix(4);
    private static final String FIELD_USAGE = Util.intToStringMaxRadix(2);
    public final int allowedCapturePolicy;
    private AudioAttributesV21 audioAttributesV21;
    public final int contentType;
    public final int flags;
    public final boolean isContentSpatialized;
    public final int spatializationBehavior;
    public final int usage;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Api29 {
        public static void setAllowedCapturePolicy(AudioAttributes.Builder builder, int i2) {
            builder.setAllowedCapturePolicy(i2);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Api32 {
        public static void setIsContentSpatialized(AudioAttributes.Builder builder, boolean z) {
            builder.setIsContentSpatialized(z);
        }

        public static void setSpatializationBehavior(AudioAttributes.Builder builder, int i2) {
            builder.setSpatializationBehavior(i2);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AudioAttributesV21 {
        public final android.media.AudioAttributes audioAttributes;

        private AudioAttributesV21(AudioAttributes audioAttributes2) {
            AudioAttributes.Builder usage = new AudioAttributes.Builder().setContentType(audioAttributes2.contentType).setFlags(audioAttributes2.flags).setUsage(audioAttributes2.usage);
            int i2 = Build.VERSION.SDK_INT;
            Api29.setAllowedCapturePolicy(usage, audioAttributes2.allowedCapturePolicy);
            if (i2 >= 32) {
                Api32.setSpatializationBehavior(usage, audioAttributes2.spatializationBehavior);
                Api32.setIsContentSpatialized(usage, audioAttributes2.isContentSpatialized);
            }
            this.audioAttributes = usage.build();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private int allowedCapturePolicy = 1;
        private int contentType = 0;
        private int flags = 0;
        private boolean isContentSpatialized = false;
        private int spatializationBehavior = 0;
        private int usage = 1;

        public AudioAttributes build() {
            return new AudioAttributes(this.contentType, this.flags, this.usage, this.allowedCapturePolicy, this.spatializationBehavior, this.isContentSpatialized);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && AudioAttributes.class == obj.getClass()) {
            AudioAttributes audioAttributes = (AudioAttributes) obj;
            if (this.contentType == audioAttributes.contentType && this.flags == audioAttributes.flags && this.usage == audioAttributes.usage && this.allowedCapturePolicy == audioAttributes.allowedCapturePolicy && this.spatializationBehavior == audioAttributes.spatializationBehavior && this.isContentSpatialized == audioAttributes.isContentSpatialized) {
                return true;
            }
            return false;
        }
        return false;
    }

    public AudioAttributesV21 getAudioAttributesV21() {
        if (this.audioAttributesV21 == null) {
            this.audioAttributesV21 = new AudioAttributesV21();
        }
        return this.audioAttributesV21;
    }

    public int getStreamType() {
        if ((this.flags & 1) == 1) {
            return 1;
        }
        switch (this.usage) {
            case 2:
                return 0;
            case 3:
                return 8;
            case 4:
                return 4;
            case 5:
            case 7:
            case 8:
            case 9:
            case 10:
                return 5;
            case 6:
                return 2;
            case 11:
                return 10;
            case 13:
                return 1;
            default:
                return 3;
        }
    }

    public int hashCode() {
        return ((((((((((527 + this.contentType) * 31) + this.flags) * 31) + this.usage) * 31) + this.allowedCapturePolicy) * 31) + this.spatializationBehavior) * 31) + (this.isContentSpatialized ? 1 : 0);
    }

    private AudioAttributes(int i2, int i7, int i8, int i10, int i11, boolean z) {
        this.contentType = i2;
        this.flags = i7;
        this.usage = i8;
        this.allowedCapturePolicy = i10;
        this.spatializationBehavior = i11;
        this.isContentSpatialized = z;
    }
}
