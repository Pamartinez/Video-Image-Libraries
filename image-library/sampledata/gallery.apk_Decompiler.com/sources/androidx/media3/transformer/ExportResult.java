package androidx.media3.transformer;

import F2.N;
import F2.Q;
import F2.U;
import F2.y0;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import java.util.List;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ExportResult {
    public final int audioConversionProcess;
    public final String audioEncoderName;
    public final String audioMimeType;
    public final int averageAudioBitrate;
    public final int averageVideoBitrate;
    public final int channelCount;
    public final ColorInfo colorInfo;
    public final long durationMs;
    public final ExportException exportException;
    public final long fileSizeBytes;
    public final int height;
    public final int optimizationResult;
    final U processedInputs;
    public final int sampleRate;
    public final int videoConversionProcess;
    public final String videoEncoderName;
    public final int videoFrameCount;
    public final String videoMimeType;
    public final int width;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private String audioEncoderName;
        private String audioMimeType;
        private int averageAudioBitrate;
        private int averageVideoBitrate;
        private int channelCount;
        private ColorInfo colorInfo;
        private long durationMs;
        private ExportException exportException;
        private long fileSizeBytes;
        private int height;
        private int optimizationResult;
        private Q processedInputsBuilder;
        private int sampleRate;
        private String videoEncoderName;
        private int videoFrameCount;
        private String videoMimeType;
        private int width;

        public Builder() {
            reset();
        }

        public Builder addProcessedInputs(List<ProcessedInput> list) {
            this.processedInputsBuilder.c(list);
            return this;
        }

        public ExportResult build() {
            y0 f = this.processedInputsBuilder.f();
            long j2 = this.durationMs;
            long j3 = this.fileSizeBytes;
            int i2 = this.averageAudioBitrate;
            int i7 = this.channelCount;
            int i8 = this.sampleRate;
            String str = this.audioEncoderName;
            String str2 = this.audioMimeType;
            int i10 = this.averageVideoBitrate;
            ColorInfo colorInfo2 = this.colorInfo;
            int i11 = this.height;
            int i12 = this.width;
            int i13 = this.videoFrameCount;
            y0 y0Var = f;
            String str3 = this.videoEncoderName;
            String str4 = this.videoMimeType;
            return new ExportResult(y0Var, j2, j3, i2, i7, i8, str, str2, i10, colorInfo2, i11, i12, i13, str3, str4, this.optimizationResult, this.exportException);
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [F2.N, F2.Q] */
        public void reset() {
            this.processedInputsBuilder = new N(4);
            this.durationMs = -9223372036854775807L;
            this.fileSizeBytes = -1;
            this.averageAudioBitrate = -2147483647;
            this.channelCount = -1;
            this.sampleRate = -2147483647;
            this.audioEncoderName = null;
            this.averageVideoBitrate = -2147483647;
            this.colorInfo = null;
            this.height = -1;
            this.width = -1;
            this.videoFrameCount = 0;
            this.videoEncoderName = null;
            this.optimizationResult = 0;
            this.exportException = null;
        }

        public Builder setAudioEncoderName(String str) {
            this.audioEncoderName = str;
            return this;
        }

        public Builder setAudioMimeType(String str) {
            this.audioMimeType = str;
            return this;
        }

        public Builder setAverageAudioBitrate(int i2) {
            boolean z;
            if (i2 > 0 || i2 == -2147483647) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z);
            this.averageAudioBitrate = i2;
            return this;
        }

        public Builder setAverageVideoBitrate(int i2) {
            boolean z;
            if (i2 > 0 || i2 == -2147483647) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z);
            this.averageVideoBitrate = i2;
            return this;
        }

        public Builder setChannelCount(int i2) {
            boolean z;
            if (i2 > 0 || i2 == -1) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z);
            this.channelCount = i2;
            return this;
        }

        public Builder setColorInfo(ColorInfo colorInfo2) {
            this.colorInfo = colorInfo2;
            return this;
        }

        public Builder setDurationMs(long j2) {
            boolean z;
            if (j2 >= 0 || j2 == -9223372036854775807L) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z);
            this.durationMs = j2;
            return this;
        }

        public Builder setExportException(ExportException exportException2) {
            this.exportException = exportException2;
            return this;
        }

        public Builder setFileSizeBytes(long j2) {
            boolean z;
            if (j2 > 0 || j2 == -1) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z, "Invalid file size = " + j2);
            this.fileSizeBytes = j2;
            return this;
        }

        public Builder setHeight(int i2) {
            boolean z;
            if (i2 > 0 || i2 == -1) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z);
            this.height = i2;
            return this;
        }

        public Builder setOptimizationResult(int i2) {
            this.optimizationResult = i2;
            return this;
        }

        public Builder setSampleRate(int i2) {
            boolean z;
            if (i2 > 0 || i2 == -2147483647) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z);
            this.sampleRate = i2;
            return this;
        }

        public Builder setVideoEncoderName(String str) {
            this.videoEncoderName = str;
            return this;
        }

        public Builder setVideoFrameCount(int i2) {
            boolean z;
            if (i2 >= 0) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z);
            this.videoFrameCount = i2;
            return this;
        }

        public Builder setVideoMimeType(String str) {
            this.videoMimeType = str;
            return this;
        }

        public Builder setWidth(int i2) {
            boolean z;
            if (i2 > 0 || i2 == -1) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z);
            this.width = i2;
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ProcessedInput {
        public final String audioDecoderName;
        public final Format audioFormat;
        public final long durationUs;
        public final MediaItem mediaItem;
        public final String videoDecoderName;
        public final Format videoFormat;

        public ProcessedInput(MediaItem mediaItem2, long j2, Format format, Format format2, String str, String str2) {
            this.mediaItem = mediaItem2;
            this.durationUs = j2;
            this.audioFormat = format;
            this.videoFormat = format2;
            this.audioDecoderName = str;
            this.videoDecoderName = str2;
        }
    }

    private static int getConversionProcess(String str, int i2, List<ProcessedInput> list, int i7) {
        String str2;
        int i8 = 0;
        if (str == null) {
            return 0;
        }
        if (i2 != 1) {
            for (ProcessedInput next : list) {
                if (i7 == 1) {
                    str2 = next.audioDecoderName;
                } else {
                    str2 = next.videoDecoderName;
                }
                if (str2 == null) {
                    if (i8 == 1) {
                        return 3;
                    }
                    i8 = 2;
                } else if (i8 == 2) {
                    return 3;
                } else {
                    i8 = 1;
                }
            }
            return i8;
        } else if (i7 == 1) {
            return 2;
        } else {
            return 3;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ExportResult)) {
            return false;
        }
        ExportResult exportResult = (ExportResult) obj;
        if (Objects.equals(this.processedInputs, exportResult.processedInputs) && this.durationMs == exportResult.durationMs && this.fileSizeBytes == exportResult.fileSizeBytes && this.averageAudioBitrate == exportResult.averageAudioBitrate && this.channelCount == exportResult.channelCount && this.sampleRate == exportResult.sampleRate && Objects.equals(this.audioEncoderName, exportResult.audioEncoderName) && Objects.equals(this.audioMimeType, exportResult.audioMimeType) && this.averageVideoBitrate == exportResult.averageVideoBitrate && Objects.equals(this.colorInfo, exportResult.colorInfo) && this.height == exportResult.height && this.width == exportResult.width && this.videoFrameCount == exportResult.videoFrameCount && Objects.equals(this.videoEncoderName, exportResult.videoEncoderName) && Objects.equals(this.videoMimeType, exportResult.videoMimeType) && this.optimizationResult == exportResult.optimizationResult && Objects.equals(this.exportException, exportResult.exportException)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = Objects.hashCode(this.audioEncoderName);
        int hashCode2 = Objects.hashCode(this.audioMimeType);
        int hashCode3 = Objects.hashCode(this.colorInfo);
        int hashCode4 = Objects.hashCode(this.videoEncoderName);
        int hashCode5 = Objects.hashCode(this.videoMimeType);
        return Objects.hashCode(this.exportException) + ((((hashCode5 + ((hashCode4 + ((((((((hashCode3 + ((((hashCode2 + ((hashCode + (((((((((((Objects.hashCode(this.processedInputs) * 31) + ((int) this.durationMs)) * 31) + ((int) this.fileSizeBytes)) * 31) + this.averageAudioBitrate) * 31) + this.channelCount) * 31) + this.sampleRate) * 31)) * 31)) * 31) + this.averageVideoBitrate) * 31)) * 31) + this.height) * 31) + this.width) * 31) + this.videoFrameCount) * 31)) * 31)) * 31) + this.optimizationResult) * 31);
    }

    private ExportResult(U u, long j2, long j3, int i2, int i7, int i8, String str, String str2, int i10, ColorInfo colorInfo2, int i11, int i12, int i13, String str3, String str4, int i14, ExportException exportException2) {
        String str5 = str4;
        int i15 = i14;
        this.processedInputs = u;
        this.durationMs = j2;
        this.fileSizeBytes = j3;
        this.averageAudioBitrate = i2;
        this.channelCount = i7;
        this.sampleRate = i8;
        this.audioEncoderName = str;
        this.audioMimeType = str2;
        this.averageVideoBitrate = i10;
        this.colorInfo = colorInfo2;
        this.height = i11;
        this.width = i12;
        this.videoFrameCount = i13;
        this.videoEncoderName = str3;
        this.videoMimeType = str5;
        this.optimizationResult = i15;
        this.exportException = exportException2;
        this.audioConversionProcess = getConversionProcess(str2, i15, u, 1);
        this.videoConversionProcess = getConversionProcess(str5, i15, u, 2);
    }
}
