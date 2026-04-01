package androidx.media3.exoplayer.source;

import androidx.media3.common.DataReader;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.extractor.TrackOutput;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SampleQueue implements TrackOutput {
    private int absoluteFirstIndex;
    private boolean allSamplesAreSyncSamples = true;
    private int capacity = 1000;
    private TrackOutput.CryptoData[] cryptoDatas = new TrackOutput.CryptoData[1000];
    private DrmSession currentDrmSession;
    private Format downstreamFormat;
    private final DrmSessionEventListener.EventDispatcher drmEventDispatcher;
    private final DrmSessionManager drmSessionManager;
    private final SampleExtrasHolder extrasHolder = new SampleExtrasHolder();
    private int[] flags = new int[1000];
    private boolean isLastSampleQueued;
    private long largestDiscardedTimestampUs = Long.MIN_VALUE;
    private long largestQueuedTimestampUs = Long.MIN_VALUE;
    private int length;
    private boolean loggedUnexpectedNonSyncSample;
    private long[] offsets = new long[1000];
    private boolean pendingSplice;
    private int readPosition;
    private int relativeFirstIndex;
    private final SampleDataQueue sampleDataQueue;
    private long sampleOffsetUs;
    private final SpannedData<SharedSampleMetadata> sharedSampleMetadata = new SpannedData<>(new Object());
    private int[] sizes = new int[1000];
    private long[] sourceIds = new long[1000];
    private long startTimeUs = Long.MIN_VALUE;
    private long[] timesUs = new long[1000];
    private Format unadjustedUpstreamFormat;
    private Format upstreamFormat;
    private boolean upstreamFormatAdjustmentRequired;
    private UpstreamFormatChangedListener upstreamFormatChangeListener;
    private boolean upstreamFormatRequired = true;
    private boolean upstreamKeyframeRequired = true;
    private long upstreamSourceId;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SampleExtrasHolder {
        public TrackOutput.CryptoData cryptoData;
        public long offset;
        public int size;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SharedSampleMetadata {
        public final DrmSessionManager.DrmSessionReference drmSessionReference;
        public final Format format;

        private SharedSampleMetadata(Format format2, DrmSessionManager.DrmSessionReference drmSessionReference2) {
            this.format = format2;
            this.drmSessionReference = drmSessionReference2;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface UpstreamFormatChangedListener {
        void onUpstreamFormatChanged(Format format);
    }

    /* JADX WARNING: type inference failed for: r2v7, types: [androidx.media3.common.util.Consumer, java.lang.Object] */
    public SampleQueue(Allocator allocator, DrmSessionManager drmSessionManager2, DrmSessionEventListener.EventDispatcher eventDispatcher) {
        this.drmSessionManager = drmSessionManager2;
        this.drmEventDispatcher = eventDispatcher;
        this.sampleDataQueue = new SampleDataQueue(allocator);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000f, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean attemptSplice(long r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            int r0 = r5.length     // Catch:{ all -> 0x0010 }
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0012
            long r3 = r5.largestDiscardedTimestampUs     // Catch:{ all -> 0x0010 }
            int r6 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r6 <= 0) goto L_0x000e
            r1 = r2
        L_0x000e:
            monitor-exit(r5)
            return r1
        L_0x0010:
            r6 = move-exception
            goto L_0x0028
        L_0x0012:
            long r3 = r5.getLargestReadTimestampUs()     // Catch:{ all -> 0x0010 }
            int r0 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r0 < 0) goto L_0x001c
            monitor-exit(r5)
            return r1
        L_0x001c:
            int r6 = r5.countUnreadSamplesBefore(r6)     // Catch:{ all -> 0x0010 }
            int r7 = r5.absoluteFirstIndex     // Catch:{ all -> 0x0010 }
            int r7 = r7 + r6
            r5.discardUpstreamSampleMetadata(r7)     // Catch:{ all -> 0x0010 }
            monitor-exit(r5)
            return r2
        L_0x0028:
            monitor-exit(r5)     // Catch:{ all -> 0x0010 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.SampleQueue.attemptSplice(long):boolean");
    }

    private synchronized void commitSample(long j2, int i2, long j3, int i7, TrackOutput.CryptoData cryptoData) {
        boolean z;
        DrmSessionManager.DrmSessionReference drmSessionReference;
        boolean z3;
        try {
            int i8 = this.length;
            if (i8 > 0) {
                int relativeIndex = getRelativeIndex(i8 - 1);
                if (this.offsets[relativeIndex] + ((long) this.sizes[relativeIndex]) <= j3) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                Assertions.checkArgument(z3);
            }
            if ((536870912 & i2) != 0) {
                z = true;
            } else {
                z = false;
            }
            this.isLastSampleQueued = z;
            this.largestQueuedTimestampUs = Math.max(this.largestQueuedTimestampUs, j2);
            int relativeIndex2 = getRelativeIndex(this.length);
            this.timesUs[relativeIndex2] = j2;
            this.offsets[relativeIndex2] = j3;
            this.sizes[relativeIndex2] = i7;
            this.flags[relativeIndex2] = i2;
            this.cryptoDatas[relativeIndex2] = cryptoData;
            this.sourceIds[relativeIndex2] = this.upstreamSourceId;
            if (this.sharedSampleMetadata.isEmpty() || !this.sharedSampleMetadata.getEndValue().format.equals(this.upstreamFormat)) {
                Format format = (Format) Assertions.checkNotNull(this.upstreamFormat);
                DrmSessionManager drmSessionManager2 = this.drmSessionManager;
                if (drmSessionManager2 != null) {
                    drmSessionReference = drmSessionManager2.preacquireSession(this.drmEventDispatcher, format);
                } else {
                    drmSessionReference = DrmSessionManager.DrmSessionReference.EMPTY;
                }
                this.sharedSampleMetadata.appendSpan(getWriteIndex(), new SharedSampleMetadata(format, drmSessionReference));
            }
            int i10 = this.length + 1;
            this.length = i10;
            int i11 = this.capacity;
            if (i10 == i11) {
                int i12 = i11 + 1000;
                long[] jArr = new long[i12];
                long[] jArr2 = new long[i12];
                long[] jArr3 = new long[i12];
                int[] iArr = new int[i12];
                int[] iArr2 = new int[i12];
                TrackOutput.CryptoData[] cryptoDataArr = new TrackOutput.CryptoData[i12];
                int i13 = this.relativeFirstIndex;
                int i14 = i11 - i13;
                System.arraycopy(this.offsets, i13, jArr2, 0, i14);
                System.arraycopy(this.timesUs, this.relativeFirstIndex, jArr3, 0, i14);
                System.arraycopy(this.flags, this.relativeFirstIndex, iArr, 0, i14);
                System.arraycopy(this.sizes, this.relativeFirstIndex, iArr2, 0, i14);
                System.arraycopy(this.cryptoDatas, this.relativeFirstIndex, cryptoDataArr, 0, i14);
                System.arraycopy(this.sourceIds, this.relativeFirstIndex, jArr, 0, i14);
                int i15 = this.relativeFirstIndex;
                System.arraycopy(this.offsets, 0, jArr2, i14, i15);
                System.arraycopy(this.timesUs, 0, jArr3, i14, i15);
                System.arraycopy(this.flags, 0, iArr, i14, i15);
                System.arraycopy(this.sizes, 0, iArr2, i14, i15);
                System.arraycopy(this.cryptoDatas, 0, cryptoDataArr, i14, i15);
                System.arraycopy(this.sourceIds, 0, jArr, i14, i15);
                this.offsets = jArr2;
                this.timesUs = jArr3;
                this.flags = iArr;
                this.sizes = iArr2;
                this.cryptoDatas = cryptoDataArr;
                this.sourceIds = jArr;
                this.relativeFirstIndex = 0;
                this.capacity = i12;
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    private int countUnreadSamplesBefore(long j2) {
        int i2 = this.length;
        int relativeIndex = getRelativeIndex(i2 - 1);
        while (i2 > this.readPosition && this.timesUs[relativeIndex] >= j2) {
            i2--;
            relativeIndex--;
            if (relativeIndex == -1) {
                relativeIndex = this.capacity - 1;
            }
        }
        return i2;
    }

    public static SampleQueue createWithDrm(Allocator allocator, DrmSessionManager drmSessionManager2, DrmSessionEventListener.EventDispatcher eventDispatcher) {
        return new SampleQueue(allocator, (DrmSessionManager) Assertions.checkNotNull(drmSessionManager2), (DrmSessionEventListener.EventDispatcher) Assertions.checkNotNull(eventDispatcher));
    }

    private synchronized long discardSampleMetadataTo(long j2, boolean z, boolean z3) {
        Throwable th;
        SampleQueue sampleQueue;
        try {
            int i2 = this.length;
            if (i2 != 0) {
                long[] jArr = this.timesUs;
                int i7 = this.relativeFirstIndex;
                if (j2 >= jArr[i7]) {
                    if (z3) {
                        try {
                            int i8 = this.readPosition;
                            if (i8 != i2) {
                                i2 = i8 + 1;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            sampleQueue = this;
                            throw th;
                        }
                    }
                    sampleQueue = this;
                    try {
                        int findSampleBefore = sampleQueue.findSampleBefore(i7, i2, j2, z);
                        if (findSampleBefore == -1) {
                            return -1;
                        }
                        long discardSamples = sampleQueue.discardSamples(findSampleBefore);
                        return discardSamples;
                    } catch (Throwable th3) {
                        th = th3;
                        th = th;
                        throw th;
                    }
                }
            }
        } catch (Throwable th4) {
            th = th4;
            sampleQueue = this;
            th = th;
            throw th;
        }
        return -1;
    }

    private synchronized long discardSampleMetadataToEnd() {
        int i2 = this.length;
        if (i2 == 0) {
            return -1;
        }
        return discardSamples(i2);
    }

    private long discardSamples(int i2) {
        this.largestDiscardedTimestampUs = Math.max(this.largestDiscardedTimestampUs, getLargestTimestamp(i2));
        this.length -= i2;
        int i7 = this.absoluteFirstIndex + i2;
        this.absoluteFirstIndex = i7;
        int i8 = this.relativeFirstIndex + i2;
        this.relativeFirstIndex = i8;
        int i10 = this.capacity;
        if (i8 >= i10) {
            this.relativeFirstIndex = i8 - i10;
        }
        int i11 = this.readPosition - i2;
        this.readPosition = i11;
        if (i11 < 0) {
            this.readPosition = 0;
        }
        this.sharedSampleMetadata.discardTo(i7);
        if (this.length != 0) {
            return this.offsets[this.relativeFirstIndex];
        }
        int i12 = this.relativeFirstIndex;
        if (i12 == 0) {
            i12 = this.capacity;
        }
        int i13 = i12 - 1;
        return this.offsets[i13] + ((long) this.sizes[i13]);
    }

    private long discardUpstreamSampleMetadata(int i2) {
        boolean z;
        int writeIndex = getWriteIndex() - i2;
        boolean z3 = false;
        if (writeIndex < 0 || writeIndex > this.length - this.readPosition) {
            z = false;
        } else {
            z = true;
        }
        Assertions.checkArgument(z);
        int i7 = this.length - writeIndex;
        this.length = i7;
        this.largestQueuedTimestampUs = Math.max(this.largestDiscardedTimestampUs, getLargestTimestamp(i7));
        if (writeIndex == 0 && this.isLastSampleQueued) {
            z3 = true;
        }
        this.isLastSampleQueued = z3;
        this.sharedSampleMetadata.discardFrom(i2);
        int i8 = this.length;
        if (i8 == 0) {
            return 0;
        }
        int relativeIndex = getRelativeIndex(i8 - 1);
        return this.offsets[relativeIndex] + ((long) this.sizes[relativeIndex]);
    }

    private int findSampleAfter(int i2, int i7, long j2, boolean z) {
        for (int i8 = 0; i8 < i7; i8++) {
            if (this.timesUs[i2] >= j2) {
                return i8;
            }
            i2++;
            if (i2 == this.capacity) {
                i2 = 0;
            }
        }
        if (z) {
            return i7;
        }
        return -1;
    }

    private int findSampleBefore(int i2, int i7, long j2, boolean z) {
        int i8 = -1;
        for (int i10 = 0; i10 < i7; i10++) {
            long j3 = this.timesUs[i2];
            if (j3 > j2) {
                break;
            }
            if (!z || (this.flags[i2] & 1) != 0) {
                if (j3 == j2) {
                    return i10;
                }
                i8 = i10;
            }
            i2++;
            if (i2 == this.capacity) {
                i2 = 0;
            }
        }
        return i8;
    }

    private long getLargestTimestamp(int i2) {
        long j2 = Long.MIN_VALUE;
        if (i2 == 0) {
            return Long.MIN_VALUE;
        }
        int relativeIndex = getRelativeIndex(i2 - 1);
        for (int i7 = 0; i7 < i2; i7++) {
            j2 = Math.max(j2, this.timesUs[relativeIndex]);
            if ((this.flags[relativeIndex] & 1) != 0) {
                return j2;
            }
            relativeIndex--;
            if (relativeIndex == -1) {
                relativeIndex = this.capacity - 1;
            }
        }
        return j2;
    }

    private int getRelativeIndex(int i2) {
        int i7 = this.relativeFirstIndex + i2;
        int i8 = this.capacity;
        if (i7 < i8) {
            return i7;
        }
        return i7 - i8;
    }

    private boolean hasNextSample() {
        if (this.readPosition != this.length) {
            return true;
        }
        return false;
    }

    private boolean mayReadSample(int i2) {
        DrmSession drmSession = this.currentDrmSession;
        if (drmSession == null || drmSession.getState() == 4) {
            return true;
        }
        if ((this.flags[i2] & 1073741824) != 0 || !this.currentDrmSession.playClearSamplesWithoutKeys()) {
            return false;
        }
        return true;
    }

    private void onFormatResult(Format format, FormatHolder formatHolder) {
        boolean z;
        DrmInitData drmInitData;
        Format format2;
        Format format3 = this.downstreamFormat;
        if (format3 == null) {
            z = true;
        } else {
            z = false;
        }
        if (format3 == null) {
            drmInitData = null;
        } else {
            drmInitData = format3.drmInitData;
        }
        this.downstreamFormat = format;
        DrmInitData drmInitData2 = format.drmInitData;
        DrmSessionManager drmSessionManager2 = this.drmSessionManager;
        if (drmSessionManager2 != null) {
            format2 = format.copyWithCryptoType(drmSessionManager2.getCryptoType(format));
        } else {
            format2 = format;
        }
        formatHolder.format = format2;
        formatHolder.drmSession = this.currentDrmSession;
        if (this.drmSessionManager != null) {
            if (z || !Objects.equals(drmInitData, drmInitData2)) {
                DrmSession drmSession = this.currentDrmSession;
                DrmSession acquireSession = this.drmSessionManager.acquireSession(this.drmEventDispatcher, format);
                this.currentDrmSession = acquireSession;
                formatHolder.drmSession = acquireSession;
                if (drmSession != null) {
                    drmSession.release(this.drmEventDispatcher);
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002e, code lost:
        return -3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized int peekSampleMetadata(androidx.media3.exoplayer.FormatHolder r6, androidx.media3.decoder.DecoderInputBuffer r7, boolean r8, boolean r9, androidx.media3.exoplayer.source.SampleQueue.SampleExtrasHolder r10) {
        /*
            r5 = this;
            monitor-enter(r5)
            r0 = 0
            r7.waitingForKeys = r0     // Catch:{ all -> 0x001f }
            boolean r0 = r5.hasNextSample()     // Catch:{ all -> 0x001f }
            r1 = -4
            r2 = -3
            r3 = -5
            if (r0 != 0) goto L_0x0039
            if (r9 != 0) goto L_0x002f
            boolean r9 = r5.isLastSampleQueued     // Catch:{ all -> 0x001f }
            if (r9 == 0) goto L_0x0014
            goto L_0x002f
        L_0x0014:
            androidx.media3.common.Format r7 = r5.upstreamFormat     // Catch:{ all -> 0x001f }
            if (r7 == 0) goto L_0x002d
            if (r8 != 0) goto L_0x0022
            androidx.media3.common.Format r8 = r5.downstreamFormat     // Catch:{ all -> 0x001f }
            if (r7 == r8) goto L_0x002d
            goto L_0x0022
        L_0x001f:
            r6 = move-exception
            goto L_0x0097
        L_0x0022:
            java.lang.Object r7 = androidx.media3.common.util.Assertions.checkNotNull(r7)     // Catch:{ all -> 0x001f }
            androidx.media3.common.Format r7 = (androidx.media3.common.Format) r7     // Catch:{ all -> 0x001f }
            r5.onFormatResult(r7, r6)     // Catch:{ all -> 0x001f }
            monitor-exit(r5)
            return r3
        L_0x002d:
            monitor-exit(r5)
            return r2
        L_0x002f:
            r6 = 4
            r7.setFlags(r6)     // Catch:{ all -> 0x001f }
            r8 = -9223372036854775808
            r7.timeUs = r8     // Catch:{ all -> 0x001f }
            monitor-exit(r5)
            return r1
        L_0x0039:
            androidx.media3.exoplayer.source.SpannedData<androidx.media3.exoplayer.source.SampleQueue$SharedSampleMetadata> r0 = r5.sharedSampleMetadata     // Catch:{ all -> 0x001f }
            int r4 = r5.getReadIndex()     // Catch:{ all -> 0x001f }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ all -> 0x001f }
            androidx.media3.exoplayer.source.SampleQueue$SharedSampleMetadata r0 = (androidx.media3.exoplayer.source.SampleQueue.SharedSampleMetadata) r0     // Catch:{ all -> 0x001f }
            androidx.media3.common.Format r0 = r0.format     // Catch:{ all -> 0x001f }
            if (r8 != 0) goto L_0x0092
            androidx.media3.common.Format r8 = r5.downstreamFormat     // Catch:{ all -> 0x001f }
            if (r0 == r8) goto L_0x004e
            goto L_0x0092
        L_0x004e:
            int r6 = r5.readPosition     // Catch:{ all -> 0x001f }
            int r6 = r5.getRelativeIndex(r6)     // Catch:{ all -> 0x001f }
            boolean r8 = r5.mayReadSample(r6)     // Catch:{ all -> 0x001f }
            r0 = 1
            if (r8 != 0) goto L_0x005f
            r7.waitingForKeys = r0     // Catch:{ all -> 0x001f }
            monitor-exit(r5)
            return r2
        L_0x005f:
            int[] r8 = r5.flags     // Catch:{ all -> 0x001f }
            r8 = r8[r6]     // Catch:{ all -> 0x001f }
            r7.setFlags(r8)     // Catch:{ all -> 0x001f }
            int r8 = r5.readPosition     // Catch:{ all -> 0x001f }
            int r2 = r5.length     // Catch:{ all -> 0x001f }
            int r2 = r2 - r0
            if (r8 != r2) goto L_0x0078
            if (r9 != 0) goto L_0x0073
            boolean r8 = r5.isLastSampleQueued     // Catch:{ all -> 0x001f }
            if (r8 == 0) goto L_0x0078
        L_0x0073:
            r8 = 536870912(0x20000000, float:1.0842022E-19)
            r7.addFlag(r8)     // Catch:{ all -> 0x001f }
        L_0x0078:
            long[] r8 = r5.timesUs     // Catch:{ all -> 0x001f }
            r8 = r8[r6]     // Catch:{ all -> 0x001f }
            r7.timeUs = r8     // Catch:{ all -> 0x001f }
            int[] r7 = r5.sizes     // Catch:{ all -> 0x001f }
            r7 = r7[r6]     // Catch:{ all -> 0x001f }
            r10.size = r7     // Catch:{ all -> 0x001f }
            long[] r7 = r5.offsets     // Catch:{ all -> 0x001f }
            r7 = r7[r6]     // Catch:{ all -> 0x001f }
            r10.offset = r7     // Catch:{ all -> 0x001f }
            androidx.media3.extractor.TrackOutput$CryptoData[] r7 = r5.cryptoDatas     // Catch:{ all -> 0x001f }
            r6 = r7[r6]     // Catch:{ all -> 0x001f }
            r10.cryptoData = r6     // Catch:{ all -> 0x001f }
            monitor-exit(r5)
            return r1
        L_0x0092:
            r5.onFormatResult(r0, r6)     // Catch:{ all -> 0x001f }
            monitor-exit(r5)
            return r3
        L_0x0097:
            monitor-exit(r5)     // Catch:{ all -> 0x001f }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.SampleQueue.peekSampleMetadata(androidx.media3.exoplayer.FormatHolder, androidx.media3.decoder.DecoderInputBuffer, boolean, boolean, androidx.media3.exoplayer.source.SampleQueue$SampleExtrasHolder):int");
    }

    private void releaseDrmSessionReferences() {
        DrmSession drmSession = this.currentDrmSession;
        if (drmSession != null) {
            drmSession.release(this.drmEventDispatcher);
            this.currentDrmSession = null;
            this.downstreamFormat = null;
        }
    }

    private synchronized void rewind() {
        this.readPosition = 0;
        this.sampleDataQueue.rewind();
    }

    private synchronized boolean setUpstreamFormat(Format format) {
        try {
            this.upstreamFormatRequired = false;
            if (Objects.equals(format, this.upstreamFormat)) {
                return false;
            }
            if (this.sharedSampleMetadata.isEmpty() || !this.sharedSampleMetadata.getEndValue().format.equals(format)) {
                this.upstreamFormat = format;
            } else {
                this.upstreamFormat = this.sharedSampleMetadata.getEndValue().format;
            }
            boolean z = this.allSamplesAreSyncSamples;
            Format format2 = this.upstreamFormat;
            this.allSamplesAreSyncSamples = z & MimeTypes.allSamplesAreSyncSamples(format2.sampleMimeType, format2.codecs);
            this.loggedUnexpectedNonSyncSample = false;
            return true;
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public final void discardTo(long j2, boolean z, boolean z3) {
        this.sampleDataQueue.discardDownstreamTo(discardSampleMetadataTo(j2, z, z3));
    }

    public final void discardToEnd() {
        this.sampleDataQueue.discardDownstreamTo(discardSampleMetadataToEnd());
    }

    public final void format(Format format) {
        Format adjustedUpstreamFormat = getAdjustedUpstreamFormat(format);
        this.upstreamFormatAdjustmentRequired = false;
        this.unadjustedUpstreamFormat = format;
        boolean upstreamFormat2 = setUpstreamFormat(adjustedUpstreamFormat);
        UpstreamFormatChangedListener upstreamFormatChangedListener = this.upstreamFormatChangeListener;
        if (upstreamFormatChangedListener != null && upstreamFormat2) {
            upstreamFormatChangedListener.onUpstreamFormatChanged(adjustedUpstreamFormat);
        }
    }

    public Format getAdjustedUpstreamFormat(Format format) {
        if (this.sampleOffsetUs == 0 || format.subsampleOffsetUs == Long.MAX_VALUE) {
            return format;
        }
        return format.buildUpon().setSubsampleOffsetUs(format.subsampleOffsetUs + this.sampleOffsetUs).build();
    }

    public final int getFirstIndex() {
        return this.absoluteFirstIndex;
    }

    public final synchronized long getLargestQueuedTimestampUs() {
        return this.largestQueuedTimestampUs;
    }

    public final synchronized long getLargestReadTimestampUs() {
        return Math.max(this.largestDiscardedTimestampUs, getLargestTimestamp(this.readPosition));
    }

    public final int getReadIndex() {
        return this.absoluteFirstIndex + this.readPosition;
    }

    public final synchronized Format getUpstreamFormat() {
        Format format;
        if (this.upstreamFormatRequired) {
            format = null;
        } else {
            format = this.upstreamFormat;
        }
        return format;
    }

    public final int getWriteIndex() {
        return this.absoluteFirstIndex + this.length;
    }

    public final synchronized boolean isLastSampleQueued() {
        return this.isLastSampleQueued;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001b, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean isReady(boolean r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.hasNextSample()     // Catch:{ all -> 0x0017 }
            r1 = 1
            if (r0 != 0) goto L_0x001c
            if (r3 != 0) goto L_0x001a
            boolean r3 = r2.isLastSampleQueued     // Catch:{ all -> 0x0017 }
            if (r3 != 0) goto L_0x001a
            androidx.media3.common.Format r3 = r2.upstreamFormat     // Catch:{ all -> 0x0017 }
            if (r3 == 0) goto L_0x0019
            androidx.media3.common.Format r0 = r2.downstreamFormat     // Catch:{ all -> 0x0017 }
            if (r3 == r0) goto L_0x0019
            goto L_0x001a
        L_0x0017:
            r3 = move-exception
            goto L_0x003c
        L_0x0019:
            r1 = 0
        L_0x001a:
            monitor-exit(r2)
            return r1
        L_0x001c:
            androidx.media3.exoplayer.source.SpannedData<androidx.media3.exoplayer.source.SampleQueue$SharedSampleMetadata> r3 = r2.sharedSampleMetadata     // Catch:{ all -> 0x0017 }
            int r0 = r2.getReadIndex()     // Catch:{ all -> 0x0017 }
            java.lang.Object r3 = r3.get(r0)     // Catch:{ all -> 0x0017 }
            androidx.media3.exoplayer.source.SampleQueue$SharedSampleMetadata r3 = (androidx.media3.exoplayer.source.SampleQueue.SharedSampleMetadata) r3     // Catch:{ all -> 0x0017 }
            androidx.media3.common.Format r3 = r3.format     // Catch:{ all -> 0x0017 }
            androidx.media3.common.Format r0 = r2.downstreamFormat     // Catch:{ all -> 0x0017 }
            if (r3 == r0) goto L_0x0030
            monitor-exit(r2)
            return r1
        L_0x0030:
            int r3 = r2.readPosition     // Catch:{ all -> 0x0017 }
            int r3 = r2.getRelativeIndex(r3)     // Catch:{ all -> 0x0017 }
            boolean r3 = r2.mayReadSample(r3)     // Catch:{ all -> 0x0017 }
            monitor-exit(r2)
            return r3
        L_0x003c:
            monitor-exit(r2)     // Catch:{ all -> 0x0017 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.SampleQueue.isReady(boolean):boolean");
    }

    public void maybeThrowError() {
        DrmSession drmSession = this.currentDrmSession;
        if (drmSession != null && drmSession.getState() == 1) {
            throw ((DrmSession.DrmSessionException) Assertions.checkNotNull(this.currentDrmSession.getError()));
        }
    }

    public void preRelease() {
        discardToEnd();
        releaseDrmSessionReferences();
    }

    public int read(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i2, boolean z) {
        boolean z3;
        boolean z7 = false;
        if ((i2 & 2) != 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        DecoderInputBuffer decoderInputBuffer2 = decoderInputBuffer;
        int peekSampleMetadata = peekSampleMetadata(formatHolder, decoderInputBuffer2, z3, z, this.extrasHolder);
        if (peekSampleMetadata == -4 && !decoderInputBuffer2.isEndOfStream()) {
            if ((i2 & 1) != 0) {
                z7 = true;
            }
            if ((i2 & 4) == 0) {
                if (z7) {
                    this.sampleDataQueue.peekToBuffer(decoderInputBuffer2, this.extrasHolder);
                } else {
                    this.sampleDataQueue.readToBuffer(decoderInputBuffer2, this.extrasHolder);
                }
            }
            if (!z7) {
                this.readPosition++;
            }
        }
        return peekSampleMetadata;
    }

    public void release() {
        reset(true);
        releaseDrmSessionReferences();
    }

    public final void reset() {
        reset(false);
    }

    public final int sampleData(DataReader dataReader, int i2, boolean z, int i7) {
        return this.sampleDataQueue.sampleData(dataReader, i2, z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0055  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void sampleMetadata(long r12, int r14, int r15, int r16, androidx.media3.extractor.TrackOutput.CryptoData r17) {
        /*
            r11 = this;
            boolean r1 = r11.upstreamFormatAdjustmentRequired
            if (r1 == 0) goto L_0x000f
            androidx.media3.common.Format r1 = r11.unadjustedUpstreamFormat
            java.lang.Object r1 = androidx.media3.common.util.Assertions.checkStateNotNull(r1)
            androidx.media3.common.Format r1 = (androidx.media3.common.Format) r1
            r11.format(r1)
        L_0x000f:
            r1 = r14 & 1
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x0017
            r4 = r3
            goto L_0x0018
        L_0x0017:
            r4 = r2
        L_0x0018:
            boolean r5 = r11.upstreamKeyframeRequired
            if (r5 == 0) goto L_0x0021
            if (r4 != 0) goto L_0x001f
            goto L_0x0061
        L_0x001f:
            r11.upstreamKeyframeRequired = r2
        L_0x0021:
            long r5 = r11.sampleOffsetUs
            long r5 = r5 + r12
            boolean r7 = r11.allSamplesAreSyncSamples
            if (r7 == 0) goto L_0x0050
            long r7 = r11.startTimeUs
            int r7 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r7 >= 0) goto L_0x002f
            goto L_0x0061
        L_0x002f:
            if (r1 != 0) goto L_0x0050
            boolean r1 = r11.loggedUnexpectedNonSyncSample
            if (r1 != 0) goto L_0x004c
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r7 = "Overriding unexpected non-sync sample for format: "
            r1.<init>(r7)
            androidx.media3.common.Format r7 = r11.upstreamFormat
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            java.lang.String r7 = "SampleQueue"
            androidx.media3.common.util.Log.w(r7, r1)
            r11.loggedUnexpectedNonSyncSample = r3
        L_0x004c:
            r1 = r14 | 1
            r3 = r1
            goto L_0x0051
        L_0x0050:
            r3 = r14
        L_0x0051:
            boolean r1 = r11.pendingSplice
            if (r1 == 0) goto L_0x0062
            if (r4 == 0) goto L_0x0061
            boolean r1 = r11.attemptSplice(r5)
            if (r1 != 0) goto L_0x005e
            goto L_0x0061
        L_0x005e:
            r11.pendingSplice = r2
            goto L_0x0062
        L_0x0061:
            return
        L_0x0062:
            androidx.media3.exoplayer.source.SampleDataQueue r1 = r11.sampleDataQueue
            long r1 = r1.getTotalBytesWritten()
            long r7 = (long) r15
            long r1 = r1 - r7
            r7 = r16
            long r7 = (long) r7
            long r1 = r1 - r7
            r9 = r5
            r4 = r1
            r1 = r9
            r0 = r11
            r6 = r15
            r7 = r17
            r0.commitSample(r1, r3, r4, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.SampleQueue.sampleMetadata(long, int, int, int, androidx.media3.extractor.TrackOutput$CryptoData):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean seekTo(int r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            r3.rewind()     // Catch:{ all -> 0x0018 }
            int r0 = r3.absoluteFirstIndex     // Catch:{ all -> 0x0018 }
            if (r4 < r0) goto L_0x001a
            int r1 = r3.length     // Catch:{ all -> 0x0018 }
            int r1 = r1 + r0
            if (r4 <= r1) goto L_0x000e
            goto L_0x001a
        L_0x000e:
            r1 = -9223372036854775808
            r3.startTimeUs = r1     // Catch:{ all -> 0x0018 }
            int r4 = r4 - r0
            r3.readPosition = r4     // Catch:{ all -> 0x0018 }
            monitor-exit(r3)
            r3 = 1
            return r3
        L_0x0018:
            r4 = move-exception
            goto L_0x001d
        L_0x001a:
            monitor-exit(r3)
            r3 = 0
            return r3
        L_0x001d:
            monitor-exit(r3)     // Catch:{ all -> 0x0018 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.SampleQueue.seekTo(int):boolean");
    }

    public final void setStartTimeUs(long j2) {
        this.startTimeUs = j2;
    }

    public final void setUpstreamFormatChangeListener(UpstreamFormatChangedListener upstreamFormatChangedListener) {
        this.upstreamFormatChangeListener = upstreamFormatChangedListener;
    }

    public void reset(boolean z) {
        this.sampleDataQueue.reset();
        this.length = 0;
        this.absoluteFirstIndex = 0;
        this.relativeFirstIndex = 0;
        this.readPosition = 0;
        this.upstreamKeyframeRequired = true;
        this.startTimeUs = Long.MIN_VALUE;
        this.largestDiscardedTimestampUs = Long.MIN_VALUE;
        this.largestQueuedTimestampUs = Long.MIN_VALUE;
        this.isLastSampleQueued = false;
        this.sharedSampleMetadata.clear();
        if (z) {
            this.unadjustedUpstreamFormat = null;
            this.upstreamFormat = null;
            this.upstreamFormatRequired = true;
            this.allSamplesAreSyncSamples = true;
        }
    }

    public final void sampleData(ParsableByteArray parsableByteArray, int i2, int i7) {
        this.sampleDataQueue.sampleData(parsableByteArray, i2);
    }

    public final synchronized boolean seekTo(long j2, boolean z) {
        SampleQueue sampleQueue;
        int i2;
        long j3;
        try {
            rewind();
            int relativeIndex = getRelativeIndex(this.readPosition);
            if (hasNextSample() && j2 >= this.timesUs[relativeIndex] && (j2 <= this.largestQueuedTimestampUs || z)) {
                if (this.allSamplesAreSyncSamples) {
                    sampleQueue = this;
                    j3 = j2;
                    i2 = sampleQueue.findSampleAfter(relativeIndex, this.length - this.readPosition, j3, z);
                } else {
                    sampleQueue = this;
                    j3 = j2;
                    i2 = sampleQueue.findSampleBefore(relativeIndex, sampleQueue.length - sampleQueue.readPosition, j3, true);
                }
                if (i2 == -1) {
                    return false;
                }
                sampleQueue.startTimeUs = j3;
                sampleQueue.readPosition += i2;
                return true;
            }
        } catch (Throwable th) {
            th = th;
            Throwable th2 = th;
            throw th2;
        }
        return false;
    }
}
