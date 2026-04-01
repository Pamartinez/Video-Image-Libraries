package androidx.media3.container;

import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Mp4LocationData implements Metadata.Entry {
    public final float latitude;
    public final float longitude;

    public Mp4LocationData(float f, float f5) {
        boolean z;
        if (f < -90.0f || f > 90.0f || f5 < -180.0f || f5 > 180.0f) {
            z = false;
        } else {
            z = true;
        }
        Assertions.checkArgument(z, "Invalid latitude or longitude");
        this.latitude = f;
        this.longitude = f5;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && Mp4LocationData.class == obj.getClass()) {
            Mp4LocationData mp4LocationData = (Mp4LocationData) obj;
            if (this.latitude == mp4LocationData.latitude && this.longitude == mp4LocationData.longitude) {
                return true;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        return Float.valueOf(this.longitude).hashCode() + ((Float.valueOf(this.latitude).hashCode() + 527) * 31);
    }

    public String toString() {
        return "xyz: latitude=" + this.latitude + ", longitude=" + this.longitude;
    }
}
