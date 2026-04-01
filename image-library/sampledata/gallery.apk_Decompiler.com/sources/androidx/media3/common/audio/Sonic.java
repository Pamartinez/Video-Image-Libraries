package androidx.media3.common.audio;

import androidx.media3.common.util.Assertions;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sivs.ai.sdkcommon.tts.TextToSpeechConst;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.ShortBuffer;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class Sonic {
    private double accumulatedSpeedAdjustmentError;
    private final int channelCount;
    private final short[] downSampleBuffer;
    private short[] inputBuffer;
    private int inputFrameCount;
    private final int inputSampleRateHz;
    private int maxDiff;
    private final int maxPeriod;
    private final int maxRequiredFrameCount;
    private int minDiff;
    private final int minPeriod;
    private int newRatePosition;
    private int oldRatePosition;
    private short[] outputBuffer;
    private int outputFrameCount;
    private final float pitch;
    private short[] pitchBuffer;
    private int pitchFrameCount;
    private int prevMinDiff;
    private int prevPeriod;
    private final float rate;
    private int remainingInputToCopyFrameCount;
    private final float speed;

    public Sonic(int i2, int i7, float f, float f5, int i8) {
        this.inputSampleRateHz = i2;
        this.channelCount = i7;
        this.speed = f;
        this.pitch = f5;
        this.rate = ((float) i2) / ((float) i8);
        this.minPeriod = i2 / 400;
        int i10 = i2 / 65;
        this.maxPeriod = i10;
        int i11 = i10 * 2;
        this.maxRequiredFrameCount = i11;
        this.downSampleBuffer = new short[i11];
        this.inputBuffer = new short[(i11 * i7)];
        this.outputBuffer = new short[(i11 * i7)];
        this.pitchBuffer = new short[(i11 * i7)];
    }

    private void adjustRate(float f, int i2) {
        int i7;
        int i8;
        if (this.outputFrameCount != i2) {
            int i10 = this.inputSampleRateHz;
            long j2 = (long) (((float) i10) / f);
            long j3 = (long) i10;
            while (j2 != 0 && j3 != 0 && j2 % 2 == 0 && j3 % 2 == 0) {
                j2 /= 2;
                j3 /= 2;
            }
            moveNewSamplesToPitchBuffer(i2);
            int i11 = 0;
            while (true) {
                int i12 = this.pitchFrameCount;
                boolean z = true;
                if (i11 < i12 - 1) {
                    while (true) {
                        i7 = this.oldRatePosition;
                        i8 = this.newRatePosition;
                        if (((long) (i7 + 1)) * j2 <= ((long) i8) * j3) {
                            break;
                        }
                        this.outputBuffer = this.ensureSpaceForAdditionalFrames(this.outputBuffer, this.outputFrameCount, 1);
                        int i13 = 0;
                        while (true) {
                            int i14 = this.channelCount;
                            if (i13 >= i14) {
                                break;
                            }
                            Sonic sonic = this;
                            this.outputBuffer[(this.outputFrameCount * i14) + i13] = sonic.interpolate(this.pitchBuffer, (i14 * i11) + i13, j3, j2);
                            i13++;
                            this = sonic;
                        }
                        Sonic sonic2 = this;
                        sonic2.newRatePosition++;
                        sonic2.outputFrameCount++;
                        this = sonic2;
                    }
                    Sonic sonic3 = this;
                    int i15 = i7 + 1;
                    sonic3.oldRatePosition = i15;
                    if (((long) i15) == j3) {
                        sonic3.oldRatePosition = 0;
                        if (((long) i8) != j2) {
                            z = false;
                        }
                        Assertions.checkState(z);
                        sonic3.newRatePosition = 0;
                    }
                    i11++;
                    this = sonic3;
                } else {
                    this.removePitchFrames(i12 - 1);
                    return;
                }
            }
        }
    }

    public static long calculateAccumulatedTruncationErrorForResampling(BigDecimal bigDecimal, BigDecimal bigDecimal2, BigDecimal bigDecimal3) {
        RoundingMode roundingMode = RoundingMode.HALF_EVEN;
        BigDecimal divide = bigDecimal.divide(bigDecimal2, 20, roundingMode);
        BigDecimal divide2 = bigDecimal2.divide(bigDecimal3, 20, roundingMode);
        RoundingMode roundingMode2 = RoundingMode.FLOOR;
        return divide.multiply(divide2.subtract(divide2.setScale(0, roundingMode2))).setScale(0, roundingMode2).longValueExact();
    }

    private void changeSpeed(double d) {
        double d2;
        Sonic sonic;
        int i2 = this.inputFrameCount;
        if (i2 >= this.maxRequiredFrameCount) {
            int i7 = 0;
            while (true) {
                if (this.remainingInputToCopyFrameCount > 0) {
                    i7 += this.copyInputToOutput(i7);
                    sonic = this;
                    d2 = d;
                } else {
                    int findPitchPeriod = this.findPitchPeriod(this.inputBuffer, i7);
                    if (d > 1.0d) {
                        sonic = this;
                        d2 = d;
                        i7 = findPitchPeriod + sonic.skipPitchPeriod(this.inputBuffer, i7, d2, findPitchPeriod) + i7;
                    } else {
                        sonic = this;
                        d2 = d;
                        i7 += sonic.insertPitchPeriod(sonic.inputBuffer, i7, d2, findPitchPeriod);
                    }
                }
                if (sonic.maxRequiredFrameCount + i7 > i2) {
                    sonic.removeProcessedInputFrames(i7);
                    return;
                } else {
                    this = sonic;
                    d = d2;
                }
            }
        }
    }

    private int copyInputToOutput(int i2) {
        int min = Math.min(this.maxRequiredFrameCount, this.remainingInputToCopyFrameCount);
        copyToOutput(this.inputBuffer, i2, min);
        this.remainingInputToCopyFrameCount -= min;
        return min;
    }

    private void copyToOutput(short[] sArr, int i2, int i7) {
        short[] ensureSpaceForAdditionalFrames = ensureSpaceForAdditionalFrames(this.outputBuffer, this.outputFrameCount, i7);
        this.outputBuffer = ensureSpaceForAdditionalFrames;
        int i8 = this.channelCount;
        System.arraycopy(sArr, i2 * i8, ensureSpaceForAdditionalFrames, this.outputFrameCount * i8, i8 * i7);
        this.outputFrameCount += i7;
    }

    private void downSampleInput(short[] sArr, int i2, int i7) {
        int i8 = this.maxRequiredFrameCount / i7;
        int i10 = this.channelCount;
        int i11 = i7 * i10;
        int i12 = i2 * i10;
        for (int i13 = 0; i13 < i8; i13++) {
            int i14 = 0;
            for (int i15 = 0; i15 < i11; i15++) {
                i14 += sArr[(i13 * i11) + i12 + i15];
            }
            this.downSampleBuffer[i13] = (short) (i14 / i11);
        }
    }

    private short[] ensureSpaceForAdditionalFrames(short[] sArr, int i2, int i7) {
        int length = sArr.length;
        int i8 = this.channelCount;
        int i10 = length / i8;
        if (i2 + i7 <= i10) {
            return sArr;
        }
        return Arrays.copyOf(sArr, (((i10 * 3) / 2) + i7) * i8);
    }

    private int findPitchPeriod(short[] sArr, int i2) {
        int i7;
        int i8;
        int i10;
        int i11 = this.inputSampleRateHz;
        if (i11 > 4000) {
            i7 = i11 / TextToSpeechConst.MAX_SPEECH_INPUT;
        } else {
            i7 = 1;
        }
        if (this.channelCount == 1 && i7 == 1) {
            i8 = findPitchPeriodInRange(sArr, i2, this.minPeriod, this.maxPeriod);
        } else {
            downSampleInput(sArr, i2, i7);
            int findPitchPeriodInRange = findPitchPeriodInRange(this.downSampleBuffer, 0, this.minPeriod / i7, this.maxPeriod / i7);
            if (i7 != 1) {
                int i12 = findPitchPeriodInRange * i7;
                int i13 = i7 * 4;
                int i14 = i12 - i13;
                int i15 = i12 + i13;
                int i16 = this.minPeriod;
                if (i14 < i16) {
                    i14 = i16;
                }
                int i17 = this.maxPeriod;
                if (i15 > i17) {
                    i15 = i17;
                }
                if (this.channelCount == 1) {
                    i8 = findPitchPeriodInRange(sArr, i2, i14, i15);
                } else {
                    downSampleInput(sArr, i2, 1);
                    i8 = findPitchPeriodInRange(this.downSampleBuffer, 0, i14, i15);
                }
            } else {
                i8 = findPitchPeriodInRange;
            }
        }
        if (previousPeriodBetter(this.minDiff, this.maxDiff)) {
            i10 = this.prevPeriod;
        } else {
            i10 = i8;
        }
        this.prevMinDiff = this.minDiff;
        this.prevPeriod = i8;
        return i10;
    }

    private int findPitchPeriodInRange(short[] sArr, int i2, int i7, int i8) {
        int i10 = i2 * this.channelCount;
        int i11 = ScoverState.TYPE_NFC_SMART_COVER;
        int i12 = 1;
        int i13 = 0;
        int i14 = 0;
        while (i7 <= i8) {
            int i15 = 0;
            for (int i16 = 0; i16 < i7; i16++) {
                i15 += Math.abs(sArr[i10 + i16] - sArr[(i10 + i7) + i16]);
            }
            if (i15 * i13 < i12 * i7) {
                i13 = i7;
                i12 = i15;
            }
            if (i15 * i11 > i14 * i7) {
                i11 = i7;
                i14 = i15;
            }
            i7++;
        }
        this.minDiff = i12 / i13;
        this.maxDiff = i14 / i11;
        return i13;
    }

    public static long getExpectedFrameCountAfterProcessorApplied(int i2, int i7, float f, float f5, long j2) {
        float f8 = (((float) i2) / ((float) i7)) * f5;
        double d = (double) (f / f5);
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(f8));
        BigDecimal valueOf = BigDecimal.valueOf(j2);
        if (d > 1.0000100135803223d || d < 0.9999899864196777d) {
            valueOf = valueOf.divide(BigDecimal.valueOf(d), RoundingMode.HALF_EVEN);
        }
        if (f8 == 1.0f) {
            return valueOf.longValueExact();
        }
        return valueOf.divide(bigDecimal, RoundingMode.HALF_EVEN).longValueExact() - calculateAccumulatedTruncationErrorForResampling(valueOf, BigDecimal.valueOf((long) i2), bigDecimal);
    }

    private int insertPitchPeriod(short[] sArr, int i2, double d, int i7) {
        int i8;
        int i10 = i7;
        if (d < 0.5d) {
            double d2 = ((((double) i10) * d) / (1.0d - d)) + this.accumulatedSpeedAdjustmentError;
            int round = (int) Math.round(d2);
            this.accumulatedSpeedAdjustmentError = d2 - ((double) round);
            i8 = round;
        } else {
            double d3 = ((((2.0d * d) - 1.0d) * ((double) i10)) / (1.0d - d)) + this.accumulatedSpeedAdjustmentError;
            int round2 = (int) Math.round(d3);
            this.remainingInputToCopyFrameCount = round2;
            this.accumulatedSpeedAdjustmentError = d3 - ((double) round2);
            i8 = i10;
        }
        int i11 = i10 + i8;
        short[] ensureSpaceForAdditionalFrames = ensureSpaceForAdditionalFrames(this.outputBuffer, this.outputFrameCount, i11);
        this.outputBuffer = ensureSpaceForAdditionalFrames;
        int i12 = this.channelCount;
        short[] sArr2 = sArr;
        System.arraycopy(sArr2, i2 * i12, ensureSpaceForAdditionalFrames, this.outputFrameCount * i12, i12 * i10);
        overlapAdd(i8, this.channelCount, this.outputBuffer, this.outputFrameCount + i10, sArr2, i2 + i10, sArr, i2);
        this.outputFrameCount += i11;
        return i8;
    }

    private short interpolate(short[] sArr, int i2, long j2, long j3) {
        short s = sArr[i2];
        short s5 = sArr[i2 + this.channelCount];
        int i7 = this.oldRatePosition;
        long j8 = ((long) (i7 + 1)) * j3;
        long j10 = j8 - (((long) this.newRatePosition) * j2);
        long j11 = j8 - (((long) i7) * j3);
        return (short) ((int) ((((j11 - j10) * ((long) s5)) + (((long) s) * j10)) / j11));
    }

    private void moveNewSamplesToPitchBuffer(int i2) {
        int i7 = this.outputFrameCount - i2;
        short[] ensureSpaceForAdditionalFrames = ensureSpaceForAdditionalFrames(this.pitchBuffer, this.pitchFrameCount, i7);
        this.pitchBuffer = ensureSpaceForAdditionalFrames;
        short[] sArr = this.outputBuffer;
        int i8 = this.channelCount;
        System.arraycopy(sArr, i2 * i8, ensureSpaceForAdditionalFrames, this.pitchFrameCount * i8, i8 * i7);
        this.outputFrameCount = i2;
        this.pitchFrameCount += i7;
    }

    private static void overlapAdd(int i2, int i7, short[] sArr, int i8, short[] sArr2, int i10, short[] sArr3, int i11) {
        for (int i12 = 0; i12 < i7; i12++) {
            int i13 = (i8 * i7) + i12;
            int i14 = (i11 * i7) + i12;
            int i15 = (i10 * i7) + i12;
            for (int i16 = 0; i16 < i2; i16++) {
                sArr[i13] = (short) (((sArr3[i14] * i16) + ((i2 - i16) * sArr2[i15])) / i2);
                i13 += i7;
                i15 += i7;
                i14 += i7;
            }
        }
    }

    private boolean previousPeriodBetter(int i2, int i7) {
        if (i2 == 0 || this.prevPeriod == 0 || i7 > i2 * 3 || i2 * 2 <= this.prevMinDiff * 3) {
            return false;
        }
        return true;
    }

    private void processStreamInput() {
        int i2 = this.outputFrameCount;
        float f = this.speed;
        float f5 = this.pitch;
        double d = (double) (f / f5);
        float f8 = this.rate * f5;
        if (d > 1.0000100135803223d || d < 0.9999899864196777d) {
            changeSpeed(d);
        } else {
            copyToOutput(this.inputBuffer, 0, this.inputFrameCount);
            this.inputFrameCount = 0;
        }
        if (f8 != 1.0f) {
            adjustRate(f8, i2);
        }
    }

    private void removePitchFrames(int i2) {
        if (i2 != 0) {
            short[] sArr = this.pitchBuffer;
            int i7 = this.channelCount;
            System.arraycopy(sArr, i2 * i7, sArr, 0, (this.pitchFrameCount - i2) * i7);
            this.pitchFrameCount -= i2;
        }
    }

    private void removeProcessedInputFrames(int i2) {
        int i7 = this.inputFrameCount - i2;
        short[] sArr = this.inputBuffer;
        int i8 = this.channelCount;
        System.arraycopy(sArr, i2 * i8, sArr, 0, i8 * i7);
        this.inputFrameCount = i7;
    }

    private int skipPitchPeriod(short[] sArr, int i2, double d, int i7) {
        int i8;
        if (d >= 2.0d) {
            double d2 = (((double) i7) / (d - 1.0d)) + this.accumulatedSpeedAdjustmentError;
            int round = (int) Math.round(d2);
            this.accumulatedSpeedAdjustmentError = d2 - ((double) round);
            i8 = round;
        } else {
            double d3 = (((2.0d - d) * ((double) i7)) / (d - 1.0d)) + this.accumulatedSpeedAdjustmentError;
            int round2 = (int) Math.round(d3);
            this.remainingInputToCopyFrameCount = round2;
            this.accumulatedSpeedAdjustmentError = d3 - ((double) round2);
            i8 = i7;
        }
        short[] ensureSpaceForAdditionalFrames = ensureSpaceForAdditionalFrames(this.outputBuffer, this.outputFrameCount, i8);
        this.outputBuffer = ensureSpaceForAdditionalFrames;
        overlapAdd(i8, this.channelCount, ensureSpaceForAdditionalFrames, this.outputFrameCount, sArr, i2, sArr, i2 + i7);
        this.outputFrameCount += i8;
        return i8;
    }

    public void flush() {
        this.inputFrameCount = 0;
        this.outputFrameCount = 0;
        this.pitchFrameCount = 0;
        this.oldRatePosition = 0;
        this.newRatePosition = 0;
        this.remainingInputToCopyFrameCount = 0;
        this.prevPeriod = 0;
        this.prevMinDiff = 0;
        this.minDiff = 0;
        this.maxDiff = 0;
        this.accumulatedSpeedAdjustmentError = MapUtil.INVALID_LOCATION;
    }

    public void getOutput(ShortBuffer shortBuffer) {
        boolean z;
        if (this.outputFrameCount >= 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        int min = Math.min(shortBuffer.remaining() / this.channelCount, this.outputFrameCount);
        shortBuffer.put(this.outputBuffer, 0, this.channelCount * min);
        int i2 = this.outputFrameCount - min;
        this.outputFrameCount = i2;
        short[] sArr = this.outputBuffer;
        int i7 = this.channelCount;
        System.arraycopy(sArr, min * i7, sArr, 0, i2 * i7);
    }

    public int getOutputSize() {
        boolean z;
        if (this.outputFrameCount >= 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        return this.outputFrameCount * this.channelCount * 2;
    }

    public int getPendingInputBytes() {
        return this.inputFrameCount * this.channelCount * 2;
    }

    public void queueEndOfStream() {
        int i2;
        int i7 = this.inputFrameCount;
        float f = this.speed;
        float f5 = this.pitch;
        int i8 = this.remainingInputToCopyFrameCount;
        int i10 = this.outputFrameCount + ((int) ((((((((double) (i7 - i8)) / ((double) (f / f5))) + ((double) i8)) + this.accumulatedSpeedAdjustmentError) + ((double) this.pitchFrameCount)) / ((double) (this.rate * f5))) + 0.5d));
        this.accumulatedSpeedAdjustmentError = MapUtil.INVALID_LOCATION;
        this.inputBuffer = ensureSpaceForAdditionalFrames(this.inputBuffer, i7, (this.maxRequiredFrameCount * 2) + i7);
        int i11 = 0;
        while (true) {
            i2 = this.maxRequiredFrameCount;
            int i12 = this.channelCount;
            if (i11 >= i2 * 2 * i12) {
                break;
            }
            this.inputBuffer[(i12 * i7) + i11] = 0;
            i11++;
        }
        this.inputFrameCount = (i2 * 2) + this.inputFrameCount;
        processStreamInput();
        if (this.outputFrameCount > i10) {
            this.outputFrameCount = Math.max(i10, 0);
        }
        this.inputFrameCount = 0;
        this.remainingInputToCopyFrameCount = 0;
        this.pitchFrameCount = 0;
    }

    public void queueInput(ShortBuffer shortBuffer) {
        int remaining = shortBuffer.remaining();
        int i2 = this.channelCount;
        int i7 = remaining / i2;
        short[] ensureSpaceForAdditionalFrames = ensureSpaceForAdditionalFrames(this.inputBuffer, this.inputFrameCount, i7);
        this.inputBuffer = ensureSpaceForAdditionalFrames;
        shortBuffer.get(ensureSpaceForAdditionalFrames, this.inputFrameCount * this.channelCount, ((i2 * i7) * 2) / 2);
        this.inputFrameCount += i7;
        processStreamInput();
    }
}
