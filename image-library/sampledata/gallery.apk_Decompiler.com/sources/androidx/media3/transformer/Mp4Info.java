package androidx.media3.transformer;

import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import com.samsung.android.imagetranslation.common.Config;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class Mp4Info {
    public final Format audioFormat;
    public final long durationUs;
    public final long firstSyncSampleTimestampUsAfterTimeUs;
    public final long firstVideoSampleTimestampUs;
    public final boolean isFirstVideoSampleAfterTimeUsSyncSample;
    public final long lastSyncSampleTimestampUs;
    public final Format videoFormat;

    private Mp4Info(long j2, long j3, long j8, long j10, boolean z, Format format, Format format2) {
        this.durationUs = j2;
        this.lastSyncSampleTimestampUs = j3;
        this.firstVideoSampleTimestampUs = j8;
        this.firstSyncSampleTimestampUsAfterTimeUs = j10;
        this.isFirstVideoSampleAfterTimeUsSyncSample = z;
        this.videoFormat = format;
        this.audioFormat = format2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: androidx.media3.common.Format} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.media3.transformer.Mp4Info create(android.content.Context r23, java.lang.String r24, long r25) {
        /*
            r0 = r24
            r1 = r25
            java.lang.String r3 = "The MP4 file is invalid"
            androidx.media3.extractor.mp4.Mp4Extractor r4 = new androidx.media3.extractor.mp4.Mp4Extractor
            androidx.media3.extractor.text.SubtitleParser$Factory r5 = androidx.media3.extractor.text.SubtitleParser.Factory.UNSUPPORTED
            r6 = 16
            r4.<init>(r5, r6)
            androidx.media3.transformer.Mp4Info$ExtractorOutputImpl r5 = new androidx.media3.transformer.Mp4Info$ExtractorOutputImpl
            r5.<init>()
            androidx.media3.datasource.DefaultDataSource r7 = new androidx.media3.datasource.DefaultDataSource
            r12 = 0
            r6 = r23
            r7.<init>((android.content.Context) r6, (boolean) r12)
            androidx.media3.datasource.DataSpec$Builder r6 = new androidx.media3.datasource.DataSpec$Builder
            r6.<init>()
            androidx.media3.datasource.DataSpec$Builder r6 = r6.setUri((java.lang.String) r0)
            androidx.media3.datasource.DataSpec r6 = r6.build()
            long r10 = r7.open(r6)     // Catch:{ all -> 0x0080 }
            r8 = 0
            int r6 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            r13 = 1
            if (r6 == 0) goto L_0x0036
            r6 = r13
            goto L_0x0037
        L_0x0036:
            r6 = r12
        L_0x0037:
            androidx.media3.common.util.Assertions.checkState(r6)     // Catch:{ all -> 0x0080 }
            androidx.media3.extractor.DefaultExtractorInput r6 = new androidx.media3.extractor.DefaultExtractorInput     // Catch:{ all -> 0x0080 }
            r8 = 0
            r6.<init>(r7, r8, r10)     // Catch:{ all -> 0x0080 }
            boolean r8 = r4.sniff(r6)     // Catch:{ all -> 0x0080 }
            androidx.media3.common.util.Assertions.checkState(r8, r3)     // Catch:{ all -> 0x0080 }
            r4.init(r5)     // Catch:{ all -> 0x0080 }
            androidx.media3.extractor.PositionHolder r14 = new androidx.media3.extractor.PositionHolder     // Catch:{ all -> 0x0080 }
            r14.<init>()     // Catch:{ all -> 0x0080 }
        L_0x0050:
            boolean r8 = r5.seekMapInitialized     // Catch:{ all -> 0x0080 }
            r9 = -1
            if (r8 != 0) goto L_0x0098
            int r8 = r4.read(r6, r14)     // Catch:{ all -> 0x0080 }
            if (r8 != r13) goto L_0x008b
            r7.close()     // Catch:{ all -> 0x0080 }
            androidx.media3.datasource.DataSpec$Builder r6 = new androidx.media3.datasource.DataSpec$Builder     // Catch:{ all -> 0x0080 }
            r6.<init>()     // Catch:{ all -> 0x0080 }
            androidx.media3.datasource.DataSpec$Builder r6 = r6.setUri((java.lang.String) r0)     // Catch:{ all -> 0x0080 }
            long r8 = r14.position     // Catch:{ all -> 0x0080 }
            androidx.media3.datasource.DataSpec$Builder r6 = r6.setPosition(r8)     // Catch:{ all -> 0x0080 }
            androidx.media3.datasource.DataSpec r6 = r6.build()     // Catch:{ all -> 0x0080 }
            long r8 = r7.open(r6)     // Catch:{ all -> 0x0080 }
            r10 = -1
            int r6 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r6 == 0) goto L_0x007e
            long r10 = r14.position     // Catch:{ all -> 0x0080 }
            long r8 = r8 + r10
        L_0x007e:
            r10 = r8
            goto L_0x0083
        L_0x0080:
            r0 = move-exception
            goto L_0x015f
        L_0x0083:
            androidx.media3.extractor.DefaultExtractorInput r6 = new androidx.media3.extractor.DefaultExtractorInput     // Catch:{ all -> 0x0080 }
            long r8 = r14.position     // Catch:{ all -> 0x0080 }
            r6.<init>(r7, r8, r10)     // Catch:{ all -> 0x0080 }
            goto L_0x0050
        L_0x008b:
            if (r8 != r9) goto L_0x0050
            boolean r8 = r5.seekMapInitialized     // Catch:{ all -> 0x0080 }
            if (r8 == 0) goto L_0x0092
            goto L_0x0050
        L_0x0092:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0080 }
            r0.<init>(r3)     // Catch:{ all -> 0x0080 }
            throw r0     // Catch:{ all -> 0x0080 }
        L_0x0098:
            long r10 = r4.getDurationUs()     // Catch:{ all -> 0x0080 }
            int r0 = r5.videoTrackId     // Catch:{ all -> 0x0080 }
            r3 = 0
            r14 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r0 == r9) goto L_0x011b
            java.util.Map<java.lang.Integer, androidx.media3.transformer.Mp4Info$ExtractorOutputImpl$TrackOutputImpl> r0 = r5.trackTypeToTrackOutput     // Catch:{ all -> 0x0080 }
            r6 = 2
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0080 }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x0080 }
            androidx.media3.transformer.Mp4Info$ExtractorOutputImpl$TrackOutputImpl r0 = (androidx.media3.transformer.Mp4Info.ExtractorOutputImpl.TrackOutputImpl) r0     // Catch:{ all -> 0x0080 }
            java.lang.Object r0 = androidx.media3.common.util.Assertions.checkNotNull(r0)     // Catch:{ all -> 0x0080 }
            androidx.media3.transformer.Mp4Info$ExtractorOutputImpl$TrackOutputImpl r0 = (androidx.media3.transformer.Mp4Info.ExtractorOutputImpl.TrackOutputImpl) r0     // Catch:{ all -> 0x0080 }
            androidx.media3.common.Format r0 = r0.format     // Catch:{ all -> 0x0080 }
            java.lang.Object r0 = androidx.media3.common.util.Assertions.checkNotNull(r0)     // Catch:{ all -> 0x0080 }
            androidx.media3.common.Format r0 = (androidx.media3.common.Format) r0     // Catch:{ all -> 0x0080 }
            int r6 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1))
            if (r6 == 0) goto L_0x00c7
            r6 = r13
            goto L_0x00c8
        L_0x00c7:
            r6 = r12
        L_0x00c8:
            androidx.media3.common.util.Assertions.checkState(r6)     // Catch:{ all -> 0x0080 }
            int r6 = r5.videoTrackId     // Catch:{ all -> 0x0080 }
            androidx.media3.extractor.SeekMap$SeekPoints r6 = r4.getSeekPoints(r10, r6)     // Catch:{ all -> 0x0080 }
            androidx.media3.extractor.SeekPoint r6 = r6.first     // Catch:{ all -> 0x0080 }
            r23 = r14
            long r14 = r6.timeUs     // Catch:{ all -> 0x0080 }
            int r6 = (r1 > r23 ? 1 : (r1 == r23 ? 0 : -1))
            if (r6 == 0) goto L_0x0114
            int r6 = r5.videoTrackId     // Catch:{ all -> 0x0080 }
            androidx.media3.extractor.SeekMap$SeekPoints r6 = r4.getSeekPoints(r1, r6)     // Catch:{ all -> 0x0080 }
            androidx.media3.extractor.SeekPoint r8 = r6.first     // Catch:{ all -> 0x0080 }
            r17 = r10
            long r9 = r8.timeUs     // Catch:{ all -> 0x0080 }
            int r8 = (r1 > r9 ? 1 : (r1 == r9 ? 0 : -1))
            if (r8 != 0) goto L_0x00ec
            goto L_0x00f7
        L_0x00ec:
            androidx.media3.extractor.SeekPoint r6 = r6.second     // Catch:{ all -> 0x0080 }
            long r9 = r6.timeUs     // Catch:{ all -> 0x0080 }
            int r6 = (r1 > r9 ? 1 : (r1 == r9 ? 0 : -1))
            if (r6 > 0) goto L_0x00f5
            goto L_0x00f7
        L_0x00f5:
            r9 = -9223372036854775808
        L_0x00f7:
            int r6 = r5.videoTrackId     // Catch:{ all -> 0x0080 }
            long[] r6 = r4.getSampleTimestampsUs(r6)     // Catch:{ all -> 0x0080 }
            int r8 = r6.length     // Catch:{ all -> 0x0080 }
            if (r8 <= 0) goto L_0x0103
            r19 = r6[r12]     // Catch:{ all -> 0x0080 }
            goto L_0x0105
        L_0x0103:
            r19 = r23
        L_0x0105:
            int r1 = androidx.media3.common.util.Util.binarySearchCeil(r6, r1, r13, r12)     // Catch:{ all -> 0x0080 }
            int r2 = r6.length     // Catch:{ all -> 0x0080 }
            if (r1 >= r2) goto L_0x0125
            r1 = r6[r1]     // Catch:{ all -> 0x0080 }
            int r1 = (r1 > r9 ? 1 : (r1 == r9 ? 0 : -1))
            if (r1 != 0) goto L_0x0125
            r12 = r13
            goto L_0x0125
        L_0x0114:
            r17 = r10
            r9 = r23
            r19 = r9
            goto L_0x0125
        L_0x011b:
            r17 = r10
            r23 = r14
            r9 = r23
            r14 = r9
            r19 = r14
            r0 = r3
        L_0x0125:
            int r1 = r5.audioTrackId     // Catch:{ all -> 0x0080 }
            r2 = -1
            if (r1 == r2) goto L_0x0145
            java.util.Map<java.lang.Integer, androidx.media3.transformer.Mp4Info$ExtractorOutputImpl$TrackOutputImpl> r1 = r5.trackTypeToTrackOutput     // Catch:{ all -> 0x0080 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r13)     // Catch:{ all -> 0x0080 }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x0080 }
            androidx.media3.transformer.Mp4Info$ExtractorOutputImpl$TrackOutputImpl r1 = (androidx.media3.transformer.Mp4Info.ExtractorOutputImpl.TrackOutputImpl) r1     // Catch:{ all -> 0x0080 }
            java.lang.Object r1 = androidx.media3.common.util.Assertions.checkNotNull(r1)     // Catch:{ all -> 0x0080 }
            androidx.media3.transformer.Mp4Info$ExtractorOutputImpl$TrackOutputImpl r1 = (androidx.media3.transformer.Mp4Info.ExtractorOutputImpl.TrackOutputImpl) r1     // Catch:{ all -> 0x0080 }
            androidx.media3.common.Format r1 = r1.format     // Catch:{ all -> 0x0080 }
            java.lang.Object r1 = androidx.media3.common.util.Assertions.checkNotNull(r1)     // Catch:{ all -> 0x0080 }
            r3 = r1
            androidx.media3.common.Format r3 = (androidx.media3.common.Format) r3     // Catch:{ all -> 0x0080 }
        L_0x0145:
            androidx.media3.transformer.Mp4Info r8 = new androidx.media3.transformer.Mp4Info     // Catch:{ all -> 0x0080 }
            r21 = r17
            r17 = r12
            r11 = r14
            r15 = r9
            r9 = r21
            r18 = r0
            r13 = r19
            r19 = r3
            r8.<init>(r9, r11, r13, r15, r17, r18, r19)     // Catch:{ all -> 0x0080 }
            androidx.media3.datasource.DataSourceUtil.closeQuietly(r7)
            r4.release()
            return r8
        L_0x015f:
            androidx.media3.datasource.DataSourceUtil.closeQuietly(r7)
            r4.release()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.transformer.Mp4Info.create(android.content.Context, java.lang.String, long):androidx.media3.transformer.Mp4Info");
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ExtractorOutputImpl implements ExtractorOutput {
        public int audioTrackId = -1;
        public boolean seekMapInitialized;
        final Map<Integer, TrackOutputImpl> trackTypeToTrackOutput = new HashMap();
        public int videoTrackId = -1;

        public void seekMap(SeekMap seekMap) {
            this.seekMapInitialized = true;
        }

        public TrackOutput track(int i2, int i7) {
            if (i7 == 2) {
                this.videoTrackId = i2;
            } else if (i7 == 1) {
                this.audioTrackId = i2;
            }
            TrackOutputImpl trackOutputImpl = this.trackTypeToTrackOutput.get(Integer.valueOf(i7));
            if (trackOutputImpl != null) {
                return trackOutputImpl;
            }
            TrackOutputImpl trackOutputImpl2 = new TrackOutputImpl();
            this.trackTypeToTrackOutput.put(Integer.valueOf(i7), trackOutputImpl2);
            return trackOutputImpl2;
        }

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class TrackOutputImpl implements TrackOutput {
            private final byte[] byteArray = new byte[Config.MAX_RESOLUTION_SUPPORTED];
            public Format format;

            public void format(Format format2) {
                this.format = format2;
            }

            public int sampleData(DataReader dataReader, int i2, boolean z, int i7) {
                int i8 = i2;
                while (i8 > 0) {
                    boolean z3 = false;
                    int read = dataReader.read(this.byteArray, 0, Math.min(i8, this.byteArray.length));
                    if (read != -1) {
                        z3 = true;
                    }
                    Assertions.checkState(z3);
                    i8 -= read;
                }
                return i2;
            }

            public void sampleData(ParsableByteArray parsableByteArray, int i2, int i7) {
                while (i2 > 0) {
                    int min = Math.min(i2, this.byteArray.length);
                    parsableByteArray.readBytes(this.byteArray, 0, min);
                    i2 -= min;
                }
            }

            public void sampleMetadata(long j2, int i2, int i7, int i8, TrackOutput.CryptoData cryptoData) {
            }
        }

        public void endTracks() {
        }
    }
}
