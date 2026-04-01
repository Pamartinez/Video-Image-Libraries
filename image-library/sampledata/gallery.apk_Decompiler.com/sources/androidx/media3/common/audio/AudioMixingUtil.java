package androidx.media3.common.audio;

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Util;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AudioMixingUtil {
    public static boolean canMix(AudioProcessor.AudioFormat audioFormat) {
        if (audioFormat.sampleRate == -1 || audioFormat.channelCount == -1) {
            return false;
        }
        int i2 = audioFormat.encoding;
        if (i2 == 2 || i2 == 4) {
            return true;
        }
        return false;
    }

    private static float floatSampleToInt16Pcm(float f) {
        int i2;
        if (f < 0.0f) {
            i2 = 32768;
        } else {
            i2 = 32767;
        }
        return Util.constrainValue(f * ((float) i2), -32768.0f, 32767.0f);
    }

    private static float getPcmSample(ByteBuffer byteBuffer, boolean z, boolean z3) {
        if (z3) {
            if (z) {
                return (float) byteBuffer.getShort();
            }
            return floatSampleToInt16Pcm(byteBuffer.getFloat());
        } else if (z) {
            return int16SampleToFloatPcm(byteBuffer.getShort());
        } else {
            return byteBuffer.getFloat();
        }
    }

    private static float int16SampleToFloatPcm(short s) {
        int i2;
        float f = (float) s;
        if (s < 0) {
            i2 = 32768;
        } else {
            i2 = 32767;
        }
        return f / ((float) i2);
    }

    public static ByteBuffer mix(ByteBuffer byteBuffer, AudioProcessor.AudioFormat audioFormat, ByteBuffer byteBuffer2, AudioProcessor.AudioFormat audioFormat2, ChannelMixingMatrix channelMixingMatrix, int i2, boolean z, boolean z3) {
        boolean z7;
        float f;
        ByteBuffer byteBuffer3 = byteBuffer2;
        boolean z9 = true;
        if (audioFormat.encoding == 2) {
            z7 = true;
        } else {
            z7 = false;
        }
        if (audioFormat2.encoding != 2) {
            z9 = false;
        }
        int inputChannelCount = channelMixingMatrix.getInputChannelCount();
        int outputChannelCount = channelMixingMatrix.getOutputChannelCount();
        float[] fArr = new float[inputChannelCount];
        float[] fArr2 = new float[outputChannelCount];
        int i7 = i2;
        for (int i8 = 0; i8 < i7; i8++) {
            if (z) {
                int position = byteBuffer3.position();
                for (int i10 = 0; i10 < outputChannelCount; i10++) {
                    fArr2[i10] = getPcmSample(byteBuffer3, z9, z9);
                }
                byteBuffer3.position(position);
            }
            for (int i11 = 0; i11 < inputChannelCount; i11++) {
                fArr[i11] = getPcmSample(byteBuffer, z7, z9);
            }
            ByteBuffer byteBuffer4 = byteBuffer;
            for (int i12 = 0; i12 < outputChannelCount; i12++) {
                for (int i13 = 0; i13 < inputChannelCount; i13++) {
                    fArr2[i12] = (channelMixingMatrix.getMixingCoefficient(i13, i12) * fArr[i13]) + fArr2[i12];
                }
                ChannelMixingMatrix channelMixingMatrix2 = channelMixingMatrix;
                if (z9) {
                    byteBuffer3.putShort((short) ((int) Util.constrainValue(fArr2[i12], -32768.0f, 32767.0f)));
                } else {
                    if (z3) {
                        f = Util.constrainValue(fArr2[i12], -1.0f, 1.0f);
                    } else {
                        f = fArr2[i12];
                    }
                    byteBuffer3.putFloat(f);
                }
                fArr2[i12] = 0.0f;
            }
            ChannelMixingMatrix channelMixingMatrix3 = channelMixingMatrix;
        }
        return byteBuffer3;
    }

    public static boolean canMix(AudioProcessor.AudioFormat audioFormat, AudioProcessor.AudioFormat audioFormat2) {
        if (audioFormat.sampleRate == audioFormat2.sampleRate && canMix(audioFormat) && canMix(audioFormat2)) {
            return true;
        }
        return false;
    }
}
