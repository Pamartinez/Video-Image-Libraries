package androidx.media3.exoplayer.source;

import androidx.media3.common.util.Assertions;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.mp3.Mp3Extractor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BundledExtractorsAdapter implements ProgressiveMediaExtractor {
    private Extractor extractor;
    private ExtractorInput extractorInput;
    private final ExtractorsFactory extractorsFactory;

    public BundledExtractorsAdapter(ExtractorsFactory extractorsFactory2) {
        this.extractorsFactory = extractorsFactory2;
    }

    public void disableSeekingOnMp3Streams() {
        Extractor extractor2 = this.extractor;
        if (extractor2 != null) {
            Extractor underlyingImplementation = extractor2.getUnderlyingImplementation();
            if (underlyingImplementation instanceof Mp3Extractor) {
                ((Mp3Extractor) underlyingImplementation).disableSeeking();
            }
        }
    }

    public long getCurrentInputPosition() {
        ExtractorInput extractorInput2 = this.extractorInput;
        if (extractorInput2 != null) {
            return extractorInput2.getPosition();
        }
        return -1;
    }

    /* JADX WARNING: type inference failed for: r11v3, types: [F2.N, F2.Q] */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0056, code lost:
        if (r1.getPosition() != r3) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0059, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0081, code lost:
        if (r1.getPosition() != r3) goto L_0x0059;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void init(androidx.media3.common.DataReader r8, android.net.Uri r9, java.util.Map<java.lang.String, java.util.List<java.lang.String>> r10, long r11, long r13, androidx.media3.extractor.ExtractorOutput r15) {
        /*
            r7 = this;
            androidx.media3.extractor.DefaultExtractorInput r1 = new androidx.media3.extractor.DefaultExtractorInput
            r2 = r8
            r3 = r11
            r5 = r13
            r1.<init>(r2, r3, r5)
            r7.extractorInput = r1
            androidx.media3.extractor.Extractor r8 = r7.extractor
            if (r8 == 0) goto L_0x000f
            return
        L_0x000f:
            androidx.media3.extractor.ExtractorsFactory r8 = r7.extractorsFactory
            androidx.media3.extractor.Extractor[] r8 = r8.createExtractors(r9, r10)
            int r10 = r8.length
            F2.G r11 = F2.U.e
            java.lang.String r11 = "expectedSize"
            F2.C0040v.c(r10, r11)
            F2.Q r11 = new F2.Q
            r11.<init>(r10)
            int r10 = r8.length
            r12 = 0
            r13 = 1
            if (r10 != r13) goto L_0x002d
            r8 = r8[r12]
            r7.extractor = r8
            goto L_0x008b
        L_0x002d:
            int r10 = r8.length
            r14 = r12
        L_0x002f:
            if (r14 >= r10) goto L_0x0087
            r0 = r8[r14]
            boolean r2 = r0.sniff(r1)     // Catch:{ EOFException -> 0x0077, all -> 0x0042 }
            if (r2 == 0) goto L_0x0045
            r7.extractor = r0     // Catch:{ EOFException -> 0x0077, all -> 0x0042 }
            androidx.media3.common.util.Assertions.checkState(r13)
            r1.resetPeekPosition()
            goto L_0x0087
        L_0x0042:
            r0 = move-exception
            r8 = r0
            goto L_0x0063
        L_0x0045:
            java.util.List r0 = r0.getSniffFailureDetails()     // Catch:{ EOFException -> 0x0077, all -> 0x0042 }
            r11.c(r0)     // Catch:{ EOFException -> 0x0077, all -> 0x0042 }
            androidx.media3.extractor.Extractor r0 = r7.extractor
            if (r0 != 0) goto L_0x005b
            long r5 = r1.getPosition()
            int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r0 != 0) goto L_0x0059
            goto L_0x005b
        L_0x0059:
            r0 = r12
            goto L_0x005c
        L_0x005b:
            r0 = r13
        L_0x005c:
            androidx.media3.common.util.Assertions.checkState(r0)
            r1.resetPeekPosition()
            goto L_0x0084
        L_0x0063:
            androidx.media3.extractor.Extractor r7 = r7.extractor
            if (r7 != 0) goto L_0x006f
            long r9 = r1.getPosition()
            int r7 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r7 != 0) goto L_0x0070
        L_0x006f:
            r12 = r13
        L_0x0070:
            androidx.media3.common.util.Assertions.checkState(r12)
            r1.resetPeekPosition()
            throw r8
        L_0x0077:
            androidx.media3.extractor.Extractor r0 = r7.extractor
            if (r0 != 0) goto L_0x005b
            long r5 = r1.getPosition()
            int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r0 != 0) goto L_0x0059
            goto L_0x005b
        L_0x0084:
            int r14 = r14 + 1
            goto L_0x002f
        L_0x0087:
            androidx.media3.extractor.Extractor r10 = r7.extractor
            if (r10 == 0) goto L_0x0091
        L_0x008b:
            androidx.media3.extractor.Extractor r7 = r7.extractor
            r7.init(r15)
            return
        L_0x0091:
            androidx.media3.exoplayer.source.UnrecognizedInputFormatException r7 = new androidx.media3.exoplayer.source.UnrecognizedInputFormatException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r12 = "None of the available extractors ("
            r10.<init>(r12)
            E2.i r12 = new E2.i
            java.lang.String r13 = ", "
            r12.<init>((java.lang.String) r13)
            F2.y0 r8 = F2.U.z(r8)
            K.a r13 = new K.a
            r14 = 5
            r13.<init>(r14)
            java.util.AbstractList r8 = F2.C0040v.t(r8, r13)
            java.util.Iterator r8 = r8.iterator()
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r12.a(r13, r8)
            java.lang.String r8 = r13.toString()
            r10.append(r8)
            java.lang.String r8 = ") could read the stream."
            r10.append(r8)
            java.lang.String r8 = r10.toString()
            java.lang.Object r9 = androidx.media3.common.util.Assertions.checkNotNull(r9)
            android.net.Uri r9 = (android.net.Uri) r9
            F2.y0 r10 = r11.f()
            r7.<init>(r8, r9, r10)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.BundledExtractorsAdapter.init(androidx.media3.common.DataReader, android.net.Uri, java.util.Map, long, long, androidx.media3.extractor.ExtractorOutput):void");
    }

    public int read(PositionHolder positionHolder) {
        return ((Extractor) Assertions.checkNotNull(this.extractor)).read((ExtractorInput) Assertions.checkNotNull(this.extractorInput), positionHolder);
    }

    public void release() {
        Extractor extractor2 = this.extractor;
        if (extractor2 != null) {
            extractor2.release();
            this.extractor = null;
        }
        this.extractorInput = null;
    }

    public void seek(long j2, long j3) {
        ((Extractor) Assertions.checkNotNull(this.extractor)).seek(j2, j3);
    }
}
