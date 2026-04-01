package androidx.media3.common;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DeviceInfo {
    private static final String FIELD_MAX_VOLUME = Util.intToStringMaxRadix(2);
    private static final String FIELD_MIN_VOLUME = Util.intToStringMaxRadix(1);
    private static final String FIELD_PLAYBACK_TYPE = Util.intToStringMaxRadix(0);
    private static final String FIELD_ROUTING_CONTROLLER_ID = Util.intToStringMaxRadix(3);
    public static final DeviceInfo UNKNOWN = new Builder(0).build();
    public final int maxVolume;
    public final int minVolume;
    public final int playbackType;
    public final String routingControllerId;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        /* access modifiers changed from: private */
        public int maxVolume;
        /* access modifiers changed from: private */
        public int minVolume;
        /* access modifiers changed from: private */
        public final int playbackType;
        /* access modifiers changed from: private */
        public String routingControllerId;

        public Builder(int i2) {
            this.playbackType = i2;
        }

        public DeviceInfo build() {
            boolean z;
            if (this.minVolume <= this.maxVolume) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z);
            return new DeviceInfo(this);
        }

        public Builder setMaxVolume(int i2) {
            this.maxVolume = i2;
            return this;
        }

        public Builder setMinVolume(int i2) {
            this.minVolume = i2;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeviceInfo)) {
            return false;
        }
        DeviceInfo deviceInfo = (DeviceInfo) obj;
        if (this.playbackType == deviceInfo.playbackType && this.minVolume == deviceInfo.minVolume && this.maxVolume == deviceInfo.maxVolume && Objects.equals(this.routingControllerId, deviceInfo.routingControllerId)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i2;
        int i7 = (((((527 + this.playbackType) * 31) + this.minVolume) * 31) + this.maxVolume) * 31;
        String str = this.routingControllerId;
        if (str == null) {
            i2 = 0;
        } else {
            i2 = str.hashCode();
        }
        return i7 + i2;
    }

    private DeviceInfo(Builder builder) {
        this.playbackType = builder.playbackType;
        this.minVolume = builder.minVolume;
        this.maxVolume = builder.maxVolume;
        this.routingControllerId = builder.routingControllerId;
    }
}
