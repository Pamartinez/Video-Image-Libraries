package androidx.media3.extractor.metadata.id3;

import F2.U;
import androidx.media3.common.util.Assertions;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TextInformationFrame extends Id3Frame {
    public final String description;
    @Deprecated
    public final String value;
    public final U values;

    public TextInformationFrame(String str, String str2, List<String> list) {
        super(str);
        Assertions.checkArgument(!list.isEmpty());
        this.description = str2;
        U y = U.y(list);
        this.values = y;
        this.value = (String) y.get(0);
    }

    private static List<Integer> parseId3v2point4TimestampFrameForDate(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            if (str.length() >= 10) {
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(0, 4))));
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(5, 7))));
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(8, 10))));
                return arrayList;
            } else if (str.length() >= 7) {
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(0, 4))));
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(5, 7))));
                return arrayList;
            } else {
                if (str.length() >= 4) {
                    arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(0, 4))));
                }
                return arrayList;
            }
        } catch (NumberFormatException unused) {
            return new ArrayList();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && TextInformationFrame.class == obj.getClass()) {
            TextInformationFrame textInformationFrame = (TextInformationFrame) obj;
            if (!Objects.equals(this.id, textInformationFrame.id) || !Objects.equals(this.description, textInformationFrame.description) || !this.values.equals(textInformationFrame.values)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i2;
        int d = C0212a.d(527, 31, this.id);
        String str = this.description;
        if (str != null) {
            i2 = str.hashCode();
        } else {
            i2 = 0;
        }
        return this.values.hashCode() + ((d + i2) * 31);
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void populateMediaMetadata(androidx.media3.common.MediaMetadata.Builder r9) {
        /*
            r8 = this;
            java.lang.String r0 = r8.id
            r0.getClass()
            int r1 = r0.hashCode()
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            r7 = -1
            switch(r1) {
                case 82815: goto L_0x0133;
                case 82878: goto L_0x0128;
                case 82897: goto L_0x011d;
                case 83253: goto L_0x0112;
                case 83254: goto L_0x0107;
                case 83255: goto L_0x00fc;
                case 83341: goto L_0x00f1;
                case 83378: goto L_0x00e6;
                case 83536: goto L_0x00d8;
                case 83552: goto L_0x00ca;
                case 2567331: goto L_0x00bc;
                case 2569357: goto L_0x00ae;
                case 2569358: goto L_0x00a0;
                case 2569891: goto L_0x0092;
                case 2570401: goto L_0x0084;
                case 2570410: goto L_0x0076;
                case 2571565: goto L_0x0068;
                case 2575251: goto L_0x005a;
                case 2581512: goto L_0x004c;
                case 2581513: goto L_0x003e;
                case 2581514: goto L_0x0030;
                case 2583398: goto L_0x0022;
                case 2590194: goto L_0x0014;
                default: goto L_0x0012;
            }
        L_0x0012:
            goto L_0x013d
        L_0x0014:
            java.lang.String r1 = "TYER"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x001e
            goto L_0x013d
        L_0x001e:
            r7 = 22
            goto L_0x013d
        L_0x0022:
            java.lang.String r1 = "TRCK"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x002c
            goto L_0x013d
        L_0x002c:
            r7 = 21
            goto L_0x013d
        L_0x0030:
            java.lang.String r1 = "TPE3"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x003a
            goto L_0x013d
        L_0x003a:
            r7 = 20
            goto L_0x013d
        L_0x003e:
            java.lang.String r1 = "TPE2"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0048
            goto L_0x013d
        L_0x0048:
            r7 = 19
            goto L_0x013d
        L_0x004c:
            java.lang.String r1 = "TPE1"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0056
            goto L_0x013d
        L_0x0056:
            r7 = 18
            goto L_0x013d
        L_0x005a:
            java.lang.String r1 = "TIT2"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0064
            goto L_0x013d
        L_0x0064:
            r7 = 17
            goto L_0x013d
        L_0x0068:
            java.lang.String r1 = "TEXT"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0072
            goto L_0x013d
        L_0x0072:
            r7 = 16
            goto L_0x013d
        L_0x0076:
            java.lang.String r1 = "TDRL"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0080
            goto L_0x013d
        L_0x0080:
            r7 = 15
            goto L_0x013d
        L_0x0084:
            java.lang.String r1 = "TDRC"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x008e
            goto L_0x013d
        L_0x008e:
            r7 = 14
            goto L_0x013d
        L_0x0092:
            java.lang.String r1 = "TDAT"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x009c
            goto L_0x013d
        L_0x009c:
            r7 = 13
            goto L_0x013d
        L_0x00a0:
            java.lang.String r1 = "TCON"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00aa
            goto L_0x013d
        L_0x00aa:
            r7 = 12
            goto L_0x013d
        L_0x00ae:
            java.lang.String r1 = "TCOM"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00b8
            goto L_0x013d
        L_0x00b8:
            r7 = 11
            goto L_0x013d
        L_0x00bc:
            java.lang.String r1 = "TALB"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00c6
            goto L_0x013d
        L_0x00c6:
            r7 = 10
            goto L_0x013d
        L_0x00ca:
            java.lang.String r1 = "TYE"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00d4
            goto L_0x013d
        L_0x00d4:
            r7 = 9
            goto L_0x013d
        L_0x00d8:
            java.lang.String r1 = "TXT"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00e2
            goto L_0x013d
        L_0x00e2:
            r7 = 8
            goto L_0x013d
        L_0x00e6:
            java.lang.String r1 = "TT2"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00ef
            goto L_0x013d
        L_0x00ef:
            r7 = 7
            goto L_0x013d
        L_0x00f1:
            java.lang.String r1 = "TRK"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00fa
            goto L_0x013d
        L_0x00fa:
            r7 = 6
            goto L_0x013d
        L_0x00fc:
            java.lang.String r1 = "TP3"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0105
            goto L_0x013d
        L_0x0105:
            r7 = 5
            goto L_0x013d
        L_0x0107:
            java.lang.String r1 = "TP2"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0110
            goto L_0x013d
        L_0x0110:
            r7 = r2
            goto L_0x013d
        L_0x0112:
            java.lang.String r1 = "TP1"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x011b
            goto L_0x013d
        L_0x011b:
            r7 = r3
            goto L_0x013d
        L_0x011d:
            java.lang.String r1 = "TDA"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0126
            goto L_0x013d
        L_0x0126:
            r7 = r4
            goto L_0x013d
        L_0x0128:
            java.lang.String r1 = "TCM"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0131
            goto L_0x013d
        L_0x0131:
            r7 = r5
            goto L_0x013d
        L_0x0133:
            java.lang.String r1 = "TAL"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x013c
            goto L_0x013d
        L_0x013c:
            r7 = r6
        L_0x013d:
            switch(r7) {
                case 0: goto L_0x0285;
                case 1: goto L_0x0279;
                case 2: goto L_0x0251;
                case 3: goto L_0x0245;
                case 4: goto L_0x0239;
                case 5: goto L_0x022d;
                case 6: goto L_0x01fe;
                case 7: goto L_0x01f2;
                case 8: goto L_0x01e6;
                case 9: goto L_0x01d2;
                case 10: goto L_0x0285;
                case 11: goto L_0x0279;
                case 12: goto L_0x01aa;
                case 13: goto L_0x0251;
                case 14: goto L_0x0176;
                case 15: goto L_0x0142;
                case 16: goto L_0x01e6;
                case 17: goto L_0x01f2;
                case 18: goto L_0x0245;
                case 19: goto L_0x0239;
                case 20: goto L_0x022d;
                case 21: goto L_0x01fe;
                case 22: goto L_0x01d2;
                default: goto L_0x0140;
            }
        L_0x0140:
            goto L_0x0278
        L_0x0142:
            F2.U r8 = r8.values
            java.lang.Object r8 = r8.get(r6)
            java.lang.String r8 = (java.lang.String) r8
            java.util.List r8 = parseId3v2point4TimestampFrameForDate(r8)
            int r0 = r8.size()
            if (r0 == r5) goto L_0x016c
            if (r0 == r4) goto L_0x0163
            if (r0 == r3) goto L_0x015a
            goto L_0x0278
        L_0x015a:
            java.lang.Object r0 = r8.get(r4)
            java.lang.Integer r0 = (java.lang.Integer) r0
            r9.setReleaseDay(r0)
        L_0x0163:
            java.lang.Object r0 = r8.get(r5)
            java.lang.Integer r0 = (java.lang.Integer) r0
            r9.setReleaseMonth(r0)
        L_0x016c:
            java.lang.Object r8 = r8.get(r6)
            java.lang.Integer r8 = (java.lang.Integer) r8
            r9.setReleaseYear(r8)
            return
        L_0x0176:
            F2.U r8 = r8.values
            java.lang.Object r8 = r8.get(r6)
            java.lang.String r8 = (java.lang.String) r8
            java.util.List r8 = parseId3v2point4TimestampFrameForDate(r8)
            int r0 = r8.size()
            if (r0 == r5) goto L_0x01a0
            if (r0 == r4) goto L_0x0197
            if (r0 == r3) goto L_0x018e
            goto L_0x0278
        L_0x018e:
            java.lang.Object r0 = r8.get(r4)
            java.lang.Integer r0 = (java.lang.Integer) r0
            r9.setRecordingDay(r0)
        L_0x0197:
            java.lang.Object r0 = r8.get(r5)
            java.lang.Integer r0 = (java.lang.Integer) r0
            r9.setRecordingMonth(r0)
        L_0x01a0:
            java.lang.Object r8 = r8.get(r6)
            java.lang.Integer r8 = (java.lang.Integer) r8
            r9.setRecordingYear(r8)
            return
        L_0x01aa:
            F2.U r0 = r8.values
            java.lang.Object r0 = r0.get(r6)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Integer r0 = o1.C0246a.n0(r0)
            if (r0 != 0) goto L_0x01c4
            F2.U r8 = r8.values
            java.lang.Object r8 = r8.get(r6)
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            r9.setGenre(r8)
            return
        L_0x01c4:
            int r8 = r0.intValue()
            java.lang.String r8 = androidx.media3.extractor.metadata.id3.Id3Util.resolveV1Genre(r8)
            if (r8 == 0) goto L_0x0278
            r9.setGenre(r8)
            return
        L_0x01d2:
            F2.U r8 = r8.values     // Catch:{  }
            java.lang.Object r8 = r8.get(r6)     // Catch:{  }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{  }
            int r8 = java.lang.Integer.parseInt(r8)     // Catch:{  }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{  }
            r9.setRecordingYear(r8)     // Catch:{  }
            return
        L_0x01e6:
            F2.U r8 = r8.values
            java.lang.Object r8 = r8.get(r6)
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            r9.setWriter(r8)
            return
        L_0x01f2:
            F2.U r8 = r8.values
            java.lang.Object r8 = r8.get(r6)
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            r9.setTitle(r8)
            return
        L_0x01fe:
            F2.U r8 = r8.values
            java.lang.Object r8 = r8.get(r6)
            java.lang.String r8 = (java.lang.String) r8
            java.lang.String r0 = "/"
            java.lang.String[] r8 = androidx.media3.common.util.Util.split(r8, r0)
            r0 = r8[r6]     // Catch:{  }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{  }
            int r1 = r8.length     // Catch:{  }
            if (r1 <= r5) goto L_0x0220
            r8 = r8[r5]     // Catch:{  }
            int r8 = java.lang.Integer.parseInt(r8)     // Catch:{  }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{  }
            goto L_0x0221
        L_0x0220:
            r8 = 0
        L_0x0221:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{  }
            androidx.media3.common.MediaMetadata$Builder r9 = r9.setTrackNumber(r0)     // Catch:{  }
            r9.setTotalTrackCount(r8)     // Catch:{  }
            return
        L_0x022d:
            F2.U r8 = r8.values
            java.lang.Object r8 = r8.get(r6)
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            r9.setConductor(r8)
            return
        L_0x0239:
            F2.U r8 = r8.values
            java.lang.Object r8 = r8.get(r6)
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            r9.setAlbumArtist(r8)
            return
        L_0x0245:
            F2.U r8 = r8.values
            java.lang.Object r8 = r8.get(r6)
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            r9.setArtist(r8)
            return
        L_0x0251:
            F2.U r8 = r8.values     // Catch:{ NumberFormatException -> 0x0278 }
            java.lang.Object r8 = r8.get(r6)     // Catch:{ NumberFormatException -> 0x0278 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ NumberFormatException -> 0x0278 }
            java.lang.String r0 = r8.substring(r4, r2)     // Catch:{ NumberFormatException -> 0x0278 }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x0278 }
            java.lang.String r8 = r8.substring(r6, r4)     // Catch:{ NumberFormatException -> 0x0278 }
            int r8 = java.lang.Integer.parseInt(r8)     // Catch:{ NumberFormatException -> 0x0278 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ NumberFormatException -> 0x0278 }
            androidx.media3.common.MediaMetadata$Builder r9 = r9.setRecordingMonth(r0)     // Catch:{ NumberFormatException -> 0x0278 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ NumberFormatException -> 0x0278 }
            r9.setRecordingDay(r8)     // Catch:{ NumberFormatException -> 0x0278 }
        L_0x0278:
            return
        L_0x0279:
            F2.U r8 = r8.values
            java.lang.Object r8 = r8.get(r6)
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            r9.setComposer(r8)
            return
        L_0x0285:
            F2.U r8 = r8.values
            java.lang.Object r8 = r8.get(r6)
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            r9.setAlbumTitle(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.metadata.id3.TextInformationFrame.populateMediaMetadata(androidx.media3.common.MediaMetadata$Builder):void");
    }

    public String toString() {
        return this.id + ": description=" + this.description + ": values=" + this.values;
    }
}
