package androidx.media3.extractor.ts;

import F2.G;
import F2.U;
import F2.y0;
import android.util.SparseArray;
import androidx.media3.common.Format;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ts.TsPayloadReader;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DefaultTsPayloadReaderFactory implements TsPayloadReader.Factory {
    private final List<Format> closedCaptionFormats;
    private final int flags;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DefaultTsPayloadReaderFactory(int i2) {
        this(i2, y0.f278h);
        G g = U.e;
    }

    private SeiReader buildSeiReader(TsPayloadReader.EsInfo esInfo) {
        return new SeiReader(getClosedCaptionFormats(esInfo), "video/mp2t");
    }

    private UserDataReader buildUserDataReader(TsPayloadReader.EsInfo esInfo) {
        return new UserDataReader(getClosedCaptionFormats(esInfo), "video/mp2t");
    }

    private List<Format> getClosedCaptionFormats(TsPayloadReader.EsInfo esInfo) {
        ArrayList arrayList;
        boolean z;
        String str;
        int i2;
        List<byte[]> list;
        if (isSet(32)) {
            return this.closedCaptionFormats;
        }
        ParsableByteArray parsableByteArray = new ParsableByteArray(esInfo.descriptorBytes);
        List<Format> list2 = this.closedCaptionFormats;
        while (parsableByteArray.bytesLeft() > 0) {
            int readUnsignedByte = parsableByteArray.readUnsignedByte();
            int position = parsableByteArray.getPosition() + parsableByteArray.readUnsignedByte();
            if (readUnsignedByte == 134) {
                ArrayList arrayList2 = new ArrayList();
                int readUnsignedByte2 = parsableByteArray.readUnsignedByte() & 31;
                for (int i7 = 0; i7 < readUnsignedByte2; i7++) {
                    String readString = parsableByteArray.readString(3);
                    int readUnsignedByte3 = parsableByteArray.readUnsignedByte();
                    boolean z3 = true;
                    if ((readUnsignedByte3 & 128) != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        i2 = readUnsignedByte3 & 63;
                        str = "application/cea-708";
                    } else {
                        str = "application/cea-608";
                        i2 = 1;
                    }
                    byte readUnsignedByte4 = (byte) parsableByteArray.readUnsignedByte();
                    parsableByteArray.skipBytes(1);
                    if (z) {
                        if ((readUnsignedByte4 & 64) == 0) {
                            z3 = false;
                        }
                        list = CodecSpecificDataUtil.buildCea708InitializationData(z3);
                    } else {
                        list = null;
                    }
                    arrayList2.add(new Format.Builder().setSampleMimeType(str).setLanguage(readString).setAccessibilityChannel(i2).setInitializationData(list).build());
                }
                arrayList = arrayList2;
            } else {
                arrayList = list2;
            }
            parsableByteArray.setPosition(position);
            list2 = arrayList;
        }
        return list2;
    }

    private boolean isSet(int i2) {
        if ((this.flags & i2) != 0) {
            return true;
        }
        return false;
    }

    public SparseArray<TsPayloadReader> createInitialPayloadReaders() {
        return new SparseArray<>();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x006a, code lost:
        return new androidx.media3.extractor.ts.PesReader(new androidx.media3.extractor.ts.Ac3Reader(r7.language, r7.getRoleFlags(), "video/mp2t"));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.media3.extractor.ts.TsPayloadReader createPayloadReader(int r6, androidx.media3.extractor.ts.TsPayloadReader.EsInfo r7) {
        /*
            r5 = this;
            java.lang.String r0 = "video/mp2t"
            r1 = 2
            if (r6 == r1) goto L_0x0153
            r2 = 3
            if (r6 == r2) goto L_0x0142
            r2 = 4
            if (r6 == r2) goto L_0x0142
            r3 = 21
            if (r6 == r3) goto L_0x0137
            r3 = 27
            r4 = 0
            if (r6 == r3) goto L_0x0116
            r2 = 36
            if (r6 == r2) goto L_0x0107
            r2 = 45
            if (r6 == r2) goto L_0x00fc
            r2 = 89
            if (r6 == r2) goto L_0x00ef
            r2 = 172(0xac, float:2.41E-43)
            if (r6 == r2) goto L_0x00de
            r2 = 257(0x101, float:3.6E-43)
            if (r6 == r2) goto L_0x00d1
            r2 = 138(0x8a, float:1.93E-43)
            if (r6 == r2) goto L_0x00be
            r2 = 139(0x8b, float:1.95E-43)
            if (r6 == r2) goto L_0x00ab
            switch(r6) {
                case 15: goto L_0x0092;
                case 16: goto L_0x0083;
                case 17: goto L_0x006b;
                default: goto L_0x0034;
            }
        L_0x0034:
            switch(r6) {
                case 128: goto L_0x0153;
                case 129: goto L_0x005a;
                case 130: goto L_0x0051;
                default: goto L_0x0037;
            }
        L_0x0037:
            switch(r6) {
                case 134: goto L_0x003b;
                case 135: goto L_0x005a;
                case 136: goto L_0x00be;
                default: goto L_0x003a;
            }
        L_0x003a:
            return r4
        L_0x003b:
            r6 = 16
            boolean r5 = r5.isSet(r6)
            if (r5 == 0) goto L_0x0044
            return r4
        L_0x0044:
            androidx.media3.extractor.ts.SectionReader r5 = new androidx.media3.extractor.ts.SectionReader
            androidx.media3.extractor.ts.PassthroughSectionPayloadReader r6 = new androidx.media3.extractor.ts.PassthroughSectionPayloadReader
            java.lang.String r7 = "application/x-scte35"
            r6.<init>(r7, r0)
            r5.<init>(r6)
            return r5
        L_0x0051:
            r6 = 64
            boolean r5 = r5.isSet(r6)
            if (r5 != 0) goto L_0x00be
            return r4
        L_0x005a:
            androidx.media3.extractor.ts.PesReader r5 = new androidx.media3.extractor.ts.PesReader
            androidx.media3.extractor.ts.Ac3Reader r6 = new androidx.media3.extractor.ts.Ac3Reader
            java.lang.String r1 = r7.language
            int r7 = r7.getRoleFlags()
            r6.<init>(r1, r7, r0)
            r5.<init>(r6)
            return r5
        L_0x006b:
            boolean r5 = r5.isSet(r1)
            if (r5 == 0) goto L_0x0072
            return r4
        L_0x0072:
            androidx.media3.extractor.ts.PesReader r5 = new androidx.media3.extractor.ts.PesReader
            androidx.media3.extractor.ts.LatmReader r6 = new androidx.media3.extractor.ts.LatmReader
            java.lang.String r1 = r7.language
            int r7 = r7.getRoleFlags()
            r6.<init>(r1, r7, r0)
            r5.<init>(r6)
            return r5
        L_0x0083:
            androidx.media3.extractor.ts.PesReader r6 = new androidx.media3.extractor.ts.PesReader
            androidx.media3.extractor.ts.H263Reader r1 = new androidx.media3.extractor.ts.H263Reader
            androidx.media3.extractor.ts.UserDataReader r5 = r5.buildUserDataReader(r7)
            r1.<init>(r5, r0)
            r6.<init>(r1)
            return r6
        L_0x0092:
            boolean r5 = r5.isSet(r1)
            if (r5 == 0) goto L_0x0099
            return r4
        L_0x0099:
            androidx.media3.extractor.ts.PesReader r5 = new androidx.media3.extractor.ts.PesReader
            androidx.media3.extractor.ts.AdtsReader r6 = new androidx.media3.extractor.ts.AdtsReader
            java.lang.String r1 = r7.language
            int r7 = r7.getRoleFlags()
            r2 = 0
            r6.<init>(r2, r1, r7, r0)
            r5.<init>(r6)
            return r5
        L_0x00ab:
            androidx.media3.extractor.ts.PesReader r5 = new androidx.media3.extractor.ts.PesReader
            androidx.media3.extractor.ts.DtsReader r6 = new androidx.media3.extractor.ts.DtsReader
            java.lang.String r1 = r7.language
            int r7 = r7.getRoleFlags()
            r2 = 5408(0x1520, float:7.578E-42)
            r6.<init>(r1, r7, r2, r0)
            r5.<init>(r6)
            return r5
        L_0x00be:
            androidx.media3.extractor.ts.PesReader r5 = new androidx.media3.extractor.ts.PesReader
            androidx.media3.extractor.ts.DtsReader r6 = new androidx.media3.extractor.ts.DtsReader
            java.lang.String r1 = r7.language
            int r7 = r7.getRoleFlags()
            r2 = 4096(0x1000, float:5.74E-42)
            r6.<init>(r1, r7, r2, r0)
            r5.<init>(r6)
            return r5
        L_0x00d1:
            androidx.media3.extractor.ts.SectionReader r5 = new androidx.media3.extractor.ts.SectionReader
            androidx.media3.extractor.ts.PassthroughSectionPayloadReader r6 = new androidx.media3.extractor.ts.PassthroughSectionPayloadReader
            java.lang.String r7 = "application/vnd.dvb.ait"
            r6.<init>(r7, r0)
            r5.<init>(r6)
            return r5
        L_0x00de:
            androidx.media3.extractor.ts.PesReader r5 = new androidx.media3.extractor.ts.PesReader
            androidx.media3.extractor.ts.Ac4Reader r6 = new androidx.media3.extractor.ts.Ac4Reader
            java.lang.String r1 = r7.language
            int r7 = r7.getRoleFlags()
            r6.<init>(r1, r7, r0)
            r5.<init>(r6)
            return r5
        L_0x00ef:
            androidx.media3.extractor.ts.PesReader r5 = new androidx.media3.extractor.ts.PesReader
            androidx.media3.extractor.ts.DvbSubtitleReader r6 = new androidx.media3.extractor.ts.DvbSubtitleReader
            java.util.List<androidx.media3.extractor.ts.TsPayloadReader$DvbSubtitleInfo> r7 = r7.dvbSubtitleInfos
            r6.<init>(r7, r0)
            r5.<init>(r6)
            return r5
        L_0x00fc:
            androidx.media3.extractor.ts.PesReader r5 = new androidx.media3.extractor.ts.PesReader
            androidx.media3.extractor.ts.MpeghReader r6 = new androidx.media3.extractor.ts.MpeghReader
            r6.<init>(r0)
            r5.<init>(r6)
            return r5
        L_0x0107:
            androidx.media3.extractor.ts.PesReader r6 = new androidx.media3.extractor.ts.PesReader
            androidx.media3.extractor.ts.H265Reader r1 = new androidx.media3.extractor.ts.H265Reader
            androidx.media3.extractor.ts.SeiReader r5 = r5.buildSeiReader(r7)
            r1.<init>(r5, r0)
            r6.<init>(r1)
            return r6
        L_0x0116:
            boolean r6 = r5.isSet(r2)
            if (r6 == 0) goto L_0x011d
            return r4
        L_0x011d:
            androidx.media3.extractor.ts.PesReader r6 = new androidx.media3.extractor.ts.PesReader
            androidx.media3.extractor.ts.H264Reader r1 = new androidx.media3.extractor.ts.H264Reader
            androidx.media3.extractor.ts.SeiReader r7 = r5.buildSeiReader(r7)
            r2 = 1
            boolean r2 = r5.isSet(r2)
            r3 = 8
            boolean r5 = r5.isSet(r3)
            r1.<init>(r7, r2, r5, r0)
            r6.<init>(r1)
            return r6
        L_0x0137:
            androidx.media3.extractor.ts.PesReader r5 = new androidx.media3.extractor.ts.PesReader
            androidx.media3.extractor.ts.Id3Reader r6 = new androidx.media3.extractor.ts.Id3Reader
            r6.<init>(r0)
            r5.<init>(r6)
            return r5
        L_0x0142:
            androidx.media3.extractor.ts.PesReader r5 = new androidx.media3.extractor.ts.PesReader
            androidx.media3.extractor.ts.MpegAudioReader r6 = new androidx.media3.extractor.ts.MpegAudioReader
            java.lang.String r1 = r7.language
            int r7 = r7.getRoleFlags()
            r6.<init>(r1, r7, r0)
            r5.<init>(r6)
            return r5
        L_0x0153:
            androidx.media3.extractor.ts.PesReader r6 = new androidx.media3.extractor.ts.PesReader
            androidx.media3.extractor.ts.H262Reader r1 = new androidx.media3.extractor.ts.H262Reader
            androidx.media3.extractor.ts.UserDataReader r5 = r5.buildUserDataReader(r7)
            r1.<init>(r5, r0)
            r6.<init>(r1)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.ts.DefaultTsPayloadReaderFactory.createPayloadReader(int, androidx.media3.extractor.ts.TsPayloadReader$EsInfo):androidx.media3.extractor.ts.TsPayloadReader");
    }

    public DefaultTsPayloadReaderFactory(int i2, List<Format> list) {
        this.flags = i2;
        this.closedCaptionFormats = list;
    }
}
