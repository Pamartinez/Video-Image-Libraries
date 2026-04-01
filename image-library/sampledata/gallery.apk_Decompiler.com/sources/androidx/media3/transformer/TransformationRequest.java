package androidx.media3.transformer;

import N2.j;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TransformationRequest {
    public final String audioMimeType;
    public final int hdrMode;
    public final int outputHeight;
    public final String videoMimeType;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private String audioMimeType;
        private int hdrMode;
        private int outputHeight;
        private String videoMimeType;

        public TransformationRequest build() {
            return new TransformationRequest(this.outputHeight, this.audioMimeType, this.videoMimeType, this.hdrMode);
        }

        public Builder setAudioMimeType(String str) {
            boolean z;
            String normalizeMimeType = MimeTypes.normalizeMimeType(str);
            if (normalizeMimeType == null || MimeTypes.isAudio(normalizeMimeType)) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z, "Not an audio MIME type: " + normalizeMimeType);
            this.audioMimeType = normalizeMimeType;
            return this;
        }

        public Builder setHdrMode(int i2) {
            this.hdrMode = i2;
            return this;
        }

        public Builder setResolution(int i2) {
            this.outputHeight = i2;
            return this;
        }

        public Builder setVideoMimeType(String str) {
            boolean z;
            String normalizeMimeType = MimeTypes.normalizeMimeType(str);
            if (normalizeMimeType == null || MimeTypes.isVideo(normalizeMimeType)) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z, "Not a video MIME type: " + normalizeMimeType);
            this.videoMimeType = normalizeMimeType;
            return this;
        }

        public Builder() {
            this.outputHeight = -1;
        }

        private Builder(TransformationRequest transformationRequest) {
            this.outputHeight = transformationRequest.outputHeight;
            this.audioMimeType = transformationRequest.audioMimeType;
            this.videoMimeType = transformationRequest.videoMimeType;
            this.hdrMode = transformationRequest.hdrMode;
        }
    }

    public Builder buildUpon() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TransformationRequest)) {
            return false;
        }
        TransformationRequest transformationRequest = (TransformationRequest) obj;
        if (this.outputHeight != transformationRequest.outputHeight || !Objects.equals(this.audioMimeType, transformationRequest.audioMimeType) || !Objects.equals(this.videoMimeType, transformationRequest.videoMimeType) || this.hdrMode != transformationRequest.hdrMode) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i2;
        int i7 = this.outputHeight * 31;
        String str = this.audioMimeType;
        int i8 = 0;
        if (str != null) {
            i2 = str.hashCode();
        } else {
            i2 = 0;
        }
        int i10 = (i7 + i2) * 31;
        String str2 = this.videoMimeType;
        if (str2 != null) {
            i8 = str2.hashCode();
        }
        return ((i10 + i8) * 31) + this.hdrMode;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("TransformationRequest{outputHeight=");
        sb2.append(this.outputHeight);
        sb2.append(", audioMimeType='");
        sb2.append(this.audioMimeType);
        sb2.append("', videoMimeType='");
        sb2.append(this.videoMimeType);
        sb2.append("', hdrMode=");
        return j.e(sb2, this.hdrMode, '}');
    }

    private TransformationRequest(int i2, String str, String str2, int i7) {
        this.outputHeight = i2;
        this.audioMimeType = str;
        this.videoMimeType = str2;
        this.hdrMode = i7;
    }
}
