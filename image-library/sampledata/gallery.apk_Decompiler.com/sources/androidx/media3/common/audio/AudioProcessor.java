package androidx.media3.common.audio;

import N2.j;
import androidx.media3.common.Format;
import androidx.media3.common.util.Util;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface AudioProcessor {
    public static final ByteBuffer EMPTY_BUFFER = ByteBuffer.allocateDirect(0).order(ByteOrder.nativeOrder());

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AudioFormat {
        public static final AudioFormat NOT_SET = new AudioFormat(-1, -1, -1);
        public final int bytesPerFrame;
        public final int channelCount;
        public final int encoding;
        public final int sampleRate;

        public AudioFormat(Format format) {
            this(format.sampleRate, format.channelCount, format.pcmEncoding);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AudioFormat)) {
                return false;
            }
            AudioFormat audioFormat = (AudioFormat) obj;
            if (this.sampleRate == audioFormat.sampleRate && this.channelCount == audioFormat.channelCount && this.encoding == audioFormat.encoding) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{Integer.valueOf(this.sampleRate), Integer.valueOf(this.channelCount), Integer.valueOf(this.encoding)});
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("AudioFormat[sampleRate=");
            sb2.append(this.sampleRate);
            sb2.append(", channelCount=");
            sb2.append(this.channelCount);
            sb2.append(", encoding=");
            return j.e(sb2, this.encoding, ']');
        }

        public AudioFormat(int i2, int i7, int i8) {
            this.sampleRate = i2;
            this.channelCount = i7;
            this.encoding = i8;
            this.bytesPerFrame = Util.isEncodingLinearPcm(i8) ? Util.getPcmFrameSize(i8, i7) : -1;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class UnhandledAudioFormatException extends Exception {
        public final AudioFormat inputAudioFormat;

        public UnhandledAudioFormatException(AudioFormat audioFormat) {
            this("Unhandled input format:", audioFormat);
        }

        public UnhandledAudioFormatException(String str, AudioFormat audioFormat) {
            super(str + " " + audioFormat);
            this.inputAudioFormat = audioFormat;
        }
    }

    AudioFormat configure(AudioFormat audioFormat);

    void flush();

    ByteBuffer getOutput();

    boolean isActive();

    boolean isEnded();

    void queueEndOfStream();

    void queueInput(ByteBuffer byteBuffer);

    void reset();

    long getDurationAfterProcessorApplied(long j2) {
        return j2;
    }
}
