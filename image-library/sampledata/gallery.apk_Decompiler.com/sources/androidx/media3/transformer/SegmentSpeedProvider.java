package androidx.media3.transformer;

import D6.a;
import F2.C0016f0;
import F2.C0040v;
import F2.F0;
import F2.G;
import F2.U;
import F2.Y;
import F2.v0;
import androidx.media3.common.Metadata;
import androidx.media3.common.audio.SpeedProvider;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.metadata.mp4.SlowMotionData;
import androidx.media3.extractor.metadata.mp4.SmtaMetadataEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SegmentSpeedProvider implements SpeedProvider {
    private final float baseSpeedMultiplier;
    private final C0016f0 speedsByStartTimeUs;

    public SegmentSpeedProvider(Metadata metadata) {
        float f;
        float captureFrameRate = getCaptureFrameRate(metadata);
        if (captureFrameRate == -3.4028235E38f) {
            f = 1.0f;
        } else {
            f = captureFrameRate / 30.0f;
        }
        this.baseSpeedMultiplier = f;
        this.speedsByStartTimeUs = buildSpeedByStartTimeUsMap(metadata, f);
    }

    private static C0016f0 buildSpeedByStartTimeUsMap(Metadata metadata, float f) {
        boolean z;
        U extractSlowMotionSegments = extractSlowMotionSegments(metadata);
        if (extractSlowMotionSegments.isEmpty()) {
            return C0016f0.k;
        }
        TreeMap treeMap = new TreeMap();
        for (int i2 = 0; i2 < extractSlowMotionSegments.size(); i2++) {
            SlowMotionData.Segment segment = (SlowMotionData.Segment) extractSlowMotionSegments.get(i2);
            treeMap.put(Long.valueOf(Util.msToUs(segment.startTimeMs)), Float.valueOf(f / ((float) segment.speedDivisor)));
        }
        for (int i7 = 0; i7 < extractSlowMotionSegments.size(); i7++) {
            SlowMotionData.Segment segment2 = (SlowMotionData.Segment) extractSlowMotionSegments.get(i7);
            if (!treeMap.containsKey(Long.valueOf(Util.msToUs(segment2.endTimeMs)))) {
                treeMap.put(Long.valueOf(Util.msToUs(segment2.endTimeMs)), Float.valueOf(f));
            }
        }
        C0016f0 f0Var = C0016f0.k;
        v0 v0Var = v0.e;
        Comparator comparator = treeMap.comparator();
        int i8 = 1;
        if (comparator == null) {
            z = true;
        } else {
            z = v0Var.equals(comparator);
        }
        Collection entrySet = treeMap.entrySet();
        if (entrySet == null) {
            entrySet = C0040v.m(entrySet.iterator());
        }
        Map.Entry[] entryArr = (Map.Entry[]) entrySet.toArray(Y.g);
        int length = entryArr.length;
        if (length == 0) {
            return C0016f0.j(v0Var);
        }
        if (length != 1) {
            Object[] objArr = new Object[length];
            Object[] objArr2 = new Object[length];
            if (z) {
                for (int i10 = 0; i10 < length; i10++) {
                    Map.Entry entry = entryArr[i10];
                    Objects.requireNonNull(entry);
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    C0040v.b(key, value);
                    objArr[i10] = key;
                    objArr2[i10] = value;
                }
            } else {
                Arrays.sort(entryArr, 0, length, new a(2));
                Map.Entry entry2 = entryArr[0];
                Objects.requireNonNull(entry2);
                Object key2 = entry2.getKey();
                objArr[0] = key2;
                Object value2 = entry2.getValue();
                objArr2[0] = value2;
                C0040v.b(objArr[0], value2);
                while (i8 < length) {
                    Map.Entry entry3 = entryArr[i8 - 1];
                    Objects.requireNonNull(entry3);
                    Map.Entry entry4 = entryArr[i8];
                    Objects.requireNonNull(entry4);
                    Object key3 = entry4.getKey();
                    Object value3 = entry4.getValue();
                    C0040v.b(key3, value3);
                    objArr[i8] = key3;
                    objArr2[i8] = value3;
                    if (v0Var.compare(key2, key3) != 0) {
                        i8++;
                        key2 = key3;
                    } else {
                        throw new IllegalArgumentException("Multiple entries with same key: " + entry3 + " and " + entry4);
                    }
                }
            }
            return new C0016f0(new F0(U.w(length, objArr), v0Var), U.w(length, objArr2), (C0016f0) null);
        }
        Map.Entry entry5 = entryArr[0];
        Objects.requireNonNull(entry5);
        return new C0016f0(new F0(U.B(entry5.getKey()), v0Var), U.B(entry5.getValue()), (C0016f0) null);
    }

    private static U extractSlowMotionSegments(Metadata metadata) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < metadata.length(); i2++) {
            Metadata.Entry entry = metadata.get(i2);
            if (entry instanceof SlowMotionData) {
                arrayList.addAll(((SlowMotionData) entry).segments);
            }
        }
        Comparator<SlowMotionData.Segment> comparator = SlowMotionData.Segment.BY_START_THEN_END_THEN_DIVISOR;
        G g = U.e;
        comparator.getClass();
        Object[] array = arrayList.toArray();
        C0040v.a(array.length, array);
        Arrays.sort(array, comparator);
        return U.w(array.length, array);
    }

    private static float getCaptureFrameRate(Metadata metadata) {
        for (int i2 = 0; i2 < metadata.length(); i2++) {
            Metadata.Entry entry = metadata.get(i2);
            if (entry instanceof SmtaMetadataEntry) {
                return ((SmtaMetadataEntry) entry).captureFrameRate;
            }
        }
        return -3.4028235E38f;
    }

    public long getNextSpeedChangeTimeUs(long j2) {
        boolean z;
        Object obj;
        if (j2 >= 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        Map.Entry firstEntry = this.speedsByStartTimeUs.tailMap(Long.valueOf(j2), false).firstEntry();
        if (firstEntry == null) {
            obj = null;
        } else {
            obj = firstEntry.getKey();
        }
        Long l = (Long) obj;
        if (l != null) {
            return l.longValue();
        }
        return -9223372036854775807L;
    }

    public float getSpeed(long j2) {
        boolean z;
        if (j2 >= 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        Map.Entry lastEntry = this.speedsByStartTimeUs.headMap(Long.valueOf(j2), true).lastEntry();
        if (lastEntry != null) {
            return ((Float) lastEntry.getValue()).floatValue();
        }
        return this.baseSpeedMultiplier;
    }
}
