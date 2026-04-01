package com.samsung.android.motionphoto.utils.ex;

import N2.j;
import android.util.Log;
import com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Vector;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HEIFParser {
    private static final String TAG = "HEIFParser";
    Vector<ItemLocation> locationList = new Vector<>();
    int mCoverImageID = -1;
    InputStream mData;
    long mFileSize = 0;
    int mFlags = 0;
    int mFoundiinfChunk = 0;
    int mFoundilocChunk = 0;
    int mFoundirefChunk = 0;
    int mFoundpitmChunk = 0;
    long mOffset = 0;
    int mRemainChunkSize = 0;
    int mVersion = 0;
    Vector<Integer> mXMPMetadataIDs = new Vector<>();
    long mXMPOffset = -1;
    long mXMPSize = -1;
    Vector<ItemReference> referenceList = new Vector<>();

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static long byte2toUInt32(byte[] r8) {
        /*
            r0 = 0
            byte r0 = r8[r0]
            if (r0 >= 0) goto L_0x0007
            int r0 = r0 + 256
        L_0x0007:
            long r0 = (long) r0
            r2 = 1
            byte r2 = r8[r2]
            if (r2 >= 0) goto L_0x000f
            int r2 = r2 + 256
        L_0x000f:
            long r2 = (long) r2
            r4 = 2
            byte r4 = r8[r4]
            if (r4 >= 0) goto L_0x0017
            int r4 = r4 + 256
        L_0x0017:
            long r4 = (long) r4
            r6 = 3
            byte r8 = r8[r6]
            if (r8 >= 0) goto L_0x001f
            int r8 = r8 + 256
        L_0x001f:
            long r6 = (long) r8
            r8 = 24
            long r0 = r0 << r8
            r8 = 16
            long r2 = r2 << r8
            long r0 = r0 + r2
            r8 = 8
            long r2 = r4 << r8
            long r0 = r0 + r2
            long r0 = r0 + r6
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.ex.HEIFParser.byte2toUInt32(byte[]):long");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int bytetoUInt16(byte[] r2) {
        /*
            r1 = this;
            r1 = 0
            byte r1 = r2[r1]
            if (r1 >= 0) goto L_0x0007
            int r1 = r1 + 256
        L_0x0007:
            r0 = 1
            byte r2 = r2[r0]
            if (r2 >= 0) goto L_0x000e
            int r2 = r2 + 256
        L_0x000e:
            int r1 = r1 << 8
            int r1 = r1 + r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.ex.HEIFParser.bytetoUInt16(byte[]):int");
    }

    public static long getVideoOffset(InputStream inputStream) {
        long available = (long) inputStream.available();
        byte[] bArr = new byte[4];
        long j2 = 0;
        while (available > j2) {
            try {
                if (inputStream.read(bArr, 0, 4) != 4) {
                    return 0;
                }
                long byte2toUInt32 = byte2toUInt32(bArr);
                try {
                    if (inputStream.read(bArr, 0, 4) != 4) {
                        return 0;
                    }
                    long j3 = j2 + 8;
                    long j8 = byte2toUInt32 - 8;
                    if (new String(bArr).equals(MediaDefs.Image.HEIF.HEIF_MPVD_BOX)) {
                        Log.d(TAG, "Found mpvd");
                        return j3;
                    }
                    try {
                        inputStream.skip(j8);
                        j2 = j3 + j8;
                    } catch (Exception e) {
                        Log.e(TAG, "Exception: " + e.toString());
                        return 0;
                    }
                } catch (Exception unused) {
                    Log.w(TAG, "read fail");
                    return 0;
                }
            } catch (Exception unused2) {
                return 0;
            }
        }
        StringBuilder j10 = j.j(available, "Finished, filesize:", " offset:");
        j10.append(j2);
        Log.d(TAG, j10.toString());
        return 0;
    }

    private int parseFullBoxHeader() {
        byte[] bArr = new byte[4];
        try {
            if (this.mData.read(bArr, 0, 4) != 4) {
                return -1;
            }
            this.mOffset += 4;
            this.mVersion = ((int) byte2toUInt32(bArr)) >> 24;
            return 0;
        } catch (Exception unused) {
            return -1;
        }
    }

    private int parseIinfBox() {
        int i2;
        int i7;
        int i8;
        int i10;
        int i11 = -1;
        if (parseFullBoxHeader() != 0) {
            return -1;
        }
        this.mRemainChunkSize -= 4;
        byte[] bArr = new byte[4];
        if (this.mVersion == 0) {
            i2 = 2;
        } else {
            i2 = 4;
        }
        try {
            if (this.mData.read(bArr, 0, i2) != i2) {
                return -1;
            }
            this.mOffset += (long) i2;
            this.mRemainChunkSize -= i2;
            if (i2 == 2) {
                i7 = bytetoUInt16(bArr);
            } else {
                i7 = (int) byte2toUInt32(bArr);
            }
            int i12 = 0;
            while (i12 < i7) {
                try {
                    if (this.mData.read(bArr, 0, 4) != 4) {
                        return i11;
                    }
                    long byte2toUInt32 = byte2toUInt32(bArr);
                    this.mOffset += 4;
                    this.mRemainChunkSize = (int) (((long) this.mRemainChunkSize) - byte2toUInt32);
                    try {
                        if (this.mData.read(bArr, 0, 4) != 4) {
                            return i11;
                        }
                        this.mOffset += 4;
                        long j2 = byte2toUInt32 - 8;
                        int i13 = i11;
                        if (!new String(bArr).equals(MediaDefs.Image.HEIF.HEIF_INFE_BOX)) {
                            this.mOffset += j2;
                            try {
                                this.mData.skip(j2);
                            } catch (Exception e) {
                                Log.e(TAG, "Exception: " + e.toString());
                                return i13;
                            }
                        } else if (parseFullBoxHeader() != 0) {
                            return i13;
                        } else {
                            long j3 = byte2toUInt32 - 12;
                            int i14 = this.mVersion;
                            if (i14 < 2) {
                                return i13;
                            }
                            if (i14 == 2) {
                                i8 = 2;
                            } else {
                                i8 = 4;
                            }
                            try {
                                if (this.mData.read(bArr, 0, i8) != i8) {
                                    return i13;
                                }
                                long j8 = (long) i8;
                                this.mOffset += j8;
                                long j10 = j3 - j8;
                                if (i8 == 2) {
                                    i10 = bytetoUInt16(bArr);
                                } else {
                                    i10 = (int) byte2toUInt32(bArr);
                                }
                                this.mOffset += 2;
                                try {
                                    this.mData.skip(2);
                                    try {
                                        if (this.mData.read(bArr, 0, 4) != 4) {
                                            return i13;
                                        }
                                        this.mOffset += 4;
                                        long j11 = j10 - 6;
                                        if (new String(bArr).equals(MediaDefs.Image.HEIF.HEIF_MIME_BOX)) {
                                            this.mXMPMetadataIDs.add(Integer.valueOf(i10));
                                            this.mOffset += j11;
                                        } else {
                                            this.mOffset += j11;
                                        }
                                        try {
                                            this.mData.skip(j11);
                                        } catch (Exception e7) {
                                            Log.e(TAG, "Exception: " + e7.toString());
                                        }
                                    } catch (Exception unused) {
                                        return i13;
                                    }
                                } catch (Exception e8) {
                                    Log.e(TAG, "Exception: " + e8.toString());
                                    return i13;
                                }
                            } catch (Exception unused2) {
                                return i13;
                            }
                        }
                        i12++;
                        i11 = i13;
                    } catch (Exception unused3) {
                        return i11;
                    }
                } catch (Exception unused4) {
                    return i11;
                }
            }
            this.mFoundiinfChunk = 1;
            Log.i(TAG, "Found iinf Chunk");
            return 0;
        } catch (Exception unused5) {
            return -1;
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int parseIlocBox() {
        /*
            r24 = this;
            r0 = r24
            r1 = 4
            byte[] r2 = new byte[r1]
            int r3 = r0.parseFullBoxHeader()
            r4 = -1
            if (r3 == 0) goto L_0x000d
            return r4
        L_0x000d:
            int r3 = r0.mVersion
            r5 = 2
            if (r3 <= r5) goto L_0x0013
            return r4
        L_0x0013:
            java.io.InputStream r3 = r0.mData     // Catch:{ Exception -> 0x01ab }
            r6 = 1
            r7 = 0
            int r3 = r3.read(r2, r7, r6)     // Catch:{ Exception -> 0x01ab }
            byte r8 = r2[r7]
            if (r3 == r6) goto L_0x0020
            return r4
        L_0x0020:
            long r9 = r0.mOffset
            r11 = 1
            long r9 = r9 + r11
            r0.mOffset = r9
            r3 = r8 & 15
            int r8 = r8 >> r1
            java.io.InputStream r9 = r0.mData     // Catch:{ Exception -> 0x01a8 }
            int r9 = r9.read(r2, r7, r6)     // Catch:{ Exception -> 0x01a8 }
            byte r10 = r2[r7]
            if (r9 == r6) goto L_0x0035
            return r4
        L_0x0035:
            long r13 = r0.mOffset
            long r13 = r13 + r11
            r0.mOffset = r13
            int r9 = r0.mVersion
            if (r9 == 0) goto L_0x0043
            if (r9 != r6) goto L_0x0041
            goto L_0x0043
        L_0x0041:
            r11 = r7
            goto L_0x0045
        L_0x0043:
            r11 = r10 & 15
        L_0x0045:
            int r10 = r10 >> r1
            if (r9 >= r5) goto L_0x0049
            r1 = r5
        L_0x0049:
            java.io.InputStream r9 = r0.mData     // Catch:{ Exception -> 0x01a5 }
            int r9 = r9.read(r2, r7, r1)     // Catch:{ Exception -> 0x01a5 }
            if (r9 == r1) goto L_0x0052
            return r4
        L_0x0052:
            long r12 = r0.mOffset
            long r14 = (long) r1
            long r12 = r12 + r14
            r0.mOffset = r12
            if (r1 != r5) goto L_0x005f
            int r9 = r0.bytetoUInt16(r2)
            goto L_0x0064
        L_0x005f:
            long r12 = byte2toUInt32(r2)
            int r9 = (int) r12
        L_0x0064:
            r12 = 0
            r16 = r4
            r4 = r7
        L_0x0069:
            java.lang.String r6 = "HEIFParser"
            if (r4 >= r9) goto L_0x019a
            java.io.InputStream r5 = r0.mData     // Catch:{ Exception -> 0x0199 }
            int r5 = r5.read(r2, r7, r1)     // Catch:{ Exception -> 0x0199 }
            if (r5 == r1) goto L_0x0076
            return r16
        L_0x0076:
            r17 = r8
            long r7 = r0.mOffset
            long r7 = r7 + r14
            r0.mOffset = r7
            r7 = 2
            if (r1 != r7) goto L_0x008a
            int r8 = r0.bytetoUInt16(r2)
            r23 = r8
            r8 = r6
            r6 = r23
            goto L_0x0091
        L_0x008a:
            r8 = r6
            long r5 = byte2toUInt32(r2)
            int r5 = (int) r5
            r6 = r5
        L_0x0091:
            int r5 = r0.mVersion
            java.lang.String r7 = "Exception: "
            r19 = r8
            r18 = r9
            r8 = 1
            r20 = 2
            if (r5 == r8) goto L_0x00a1
            r8 = 2
            if (r5 != r8) goto L_0x00b0
        L_0x00a1:
            long r8 = r0.mOffset
            long r8 = r8 + r20
            r0.mOffset = r8
            java.io.InputStream r5 = r0.mData     // Catch:{ Exception -> 0x0183 }
            r8 = r20
            r5.skip(r8)     // Catch:{ Exception -> 0x0183 }
            r20 = r8
        L_0x00b0:
            long r8 = r0.mOffset
            long r8 = r8 + r20
            r0.mOffset = r8
            java.io.InputStream r5 = r0.mData     // Catch:{ Exception -> 0x016c }
            r8 = r20
            r5.skip(r8)     // Catch:{ Exception -> 0x016c }
            if (r10 <= 0) goto L_0x00df
            java.io.InputStream r5 = r0.mData     // Catch:{ Exception -> 0x00de }
            r7 = 0
            int r8 = r5.read(r2, r7, r10)     // Catch:{ Exception -> 0x00de }
            r7 = 2
            if (r1 != r7) goto L_0x00cf
            int r7 = r0.bytetoUInt16(r2)
            long r12 = (long) r7
            goto L_0x00d3
        L_0x00cf:
            long r12 = byte2toUInt32(r2)
        L_0x00d3:
            if (r8 == r10) goto L_0x00d6
            return r16
        L_0x00d6:
            long r7 = r0.mOffset
            r9 = r6
            long r5 = (long) r10
            long r7 = r7 + r5
            r0.mOffset = r7
            goto L_0x00e0
        L_0x00de:
            return r16
        L_0x00df:
            r9 = r6
        L_0x00e0:
            java.io.InputStream r5 = r0.mData     // Catch:{ Exception -> 0x016b }
            r6 = 0
            r7 = 2
            int r8 = r5.read(r2, r6, r7)     // Catch:{ Exception -> 0x016b }
            int r6 = r0.bytetoUInt16(r2)
            if (r8 == r7) goto L_0x00ef
            return r16
        L_0x00ef:
            long r7 = r0.mOffset
            r20 = 2
            long r7 = r7 + r20
            r0.mOffset = r7
            r8 = 1
            if (r6 == r8) goto L_0x00fb
            return r16
        L_0x00fb:
            java.io.InputStream r6 = r0.mData     // Catch:{  }
            r5 = 0
            int r6 = r6.read(r2, r5, r11)     // Catch:{  }
            byte2toUInt32(r2)
            if (r6 == r11) goto L_0x0108
            return r16
        L_0x0108:
            long r6 = r0.mOffset
            r19 = r6
            long r5 = (long) r11
            long r6 = r19 + r5
            r0.mOffset = r6
            java.io.InputStream r5 = r0.mData     // Catch:{  }
            r6 = r17
            r7 = 0
            int r8 = r5.read(r2, r7, r6)     // Catch:{  }
            r17 = r4
            long r4 = byte2toUInt32(r2)
            if (r8 == r6) goto L_0x0123
            return r16
        L_0x0123:
            long r7 = r0.mOffset
            r19 = r7
            long r7 = (long) r6
            long r7 = r19 + r7
            r0.mOffset = r7
            java.io.InputStream r7 = r0.mData     // Catch:{  }
            r8 = 0
            int r7 = r7.read(r2, r8, r3)     // Catch:{  }
            r20 = r1
            r8 = r2
            long r1 = byte2toUInt32(r8)
            if (r7 == r3) goto L_0x013d
            return r16
        L_0x013d:
            r19 = r6
            long r6 = r0.mOffset
            r21 = r6
            long r6 = (long) r3
            long r6 = r21 + r6
            r0.mOffset = r6
            com.samsung.android.motionphoto.utils.ex.ItemLocation r6 = new com.samsung.android.motionphoto.utils.ex.ItemLocation
            r6.<init>()
            r6.base_offset = r12
            r21 = r8
            long r7 = (long) r9
            r6.itemID = r7
            r6.offset = r4
            r6.length = r1
            java.util.Vector<com.samsung.android.motionphoto.utils.ex.ItemLocation> r1 = r0.locationList
            r1.add(r6)
            int r4 = r17 + 1
            r9 = r18
            r8 = r19
            r1 = r20
            r2 = r21
            r5 = 2
            r7 = 0
            goto L_0x0069
        L_0x016b:
            return r16
        L_0x016c:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r7)
            java.lang.String r0 = r0.toString()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r8 = r19
            android.util.Log.e(r8, r0)
            return r16
        L_0x0183:
            r0 = move-exception
            r8 = r19
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r7)
            java.lang.String r0 = r0.toString()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            android.util.Log.e(r8, r0)
        L_0x0199:
            return r16
        L_0x019a:
            r8 = r6
            r1 = 1
            r0.mFoundilocChunk = r1
            java.lang.String r0 = "Found iloc Chunk"
            android.util.Log.i(r8, r0)
            r5 = 0
            return r5
        L_0x01a5:
            r16 = r4
            return r16
        L_0x01a8:
            r16 = r4
            return r16
        L_0x01ab:
            r16 = r4
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.ex.HEIFParser.parseIlocBox():int");
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int parseIrefBox(long r20) {
        /*
            r19 = this;
            r0 = r19
            r1 = 4
            byte[] r2 = new byte[r1]
            int r3 = r0.parseFullBoxHeader()
            r4 = -1
            if (r3 == 0) goto L_0x000d
            return r4
        L_0x000d:
            r5 = 4
            long r7 = r20 - r5
            int r3 = r0.mVersion
            r9 = 2
            if (r3 != 0) goto L_0x0018
            r3 = r9
            goto L_0x0019
        L_0x0018:
            r3 = r1
        L_0x0019:
            r10 = 0
            int r10 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            java.lang.String r11 = "HEIFParser"
            r12 = 0
            if (r10 <= 0) goto L_0x00e7
            java.io.InputStream r10 = r0.mData     // Catch:{ Exception -> 0x00e5 }
            int r10 = r10.read(r2, r12, r1)     // Catch:{ Exception -> 0x00e5 }
            if (r10 == r1) goto L_0x002b
            return r4
        L_0x002b:
            long r13 = byte2toUInt32(r2)
            r15 = r4
            r16 = r5
            long r4 = r0.mOffset
            long r4 = r4 + r16
            r0.mOffset = r4
            java.io.InputStream r4 = r0.mData     // Catch:{ Exception -> 0x00e4 }
            int r4 = r4.read(r2, r12, r1)     // Catch:{ Exception -> 0x00e4 }
            if (r4 == r1) goto L_0x0041
            return r15
        L_0x0041:
            long r4 = r0.mOffset
            long r4 = r4 + r16
            r0.mOffset = r4
            r4 = 8
            long r7 = r7 - r4
            java.lang.String r6 = new java.lang.String
            r6.<init>(r2)
            java.lang.String r10 = "cdsc"
            boolean r6 = r6.equals(r10)
            if (r6 == 0) goto L_0x00b8
            java.io.InputStream r4 = r0.mData     // Catch:{ Exception -> 0x00b7 }
            int r4 = r4.read(r2, r12, r3)     // Catch:{ Exception -> 0x00b7 }
            if (r4 == r3) goto L_0x0060
            return r15
        L_0x0060:
            long r4 = r0.mOffset
            long r10 = (long) r3
            long r4 = r4 + r10
            r0.mOffset = r4
            long r7 = r7 - r10
            if (r3 != r9) goto L_0x006e
            int r4 = r0.bytetoUInt16(r2)
            goto L_0x0073
        L_0x006e:
            long r4 = byte2toUInt32(r2)
            int r4 = (int) r4
        L_0x0073:
            long r5 = r0.mOffset
            r13 = 2
            long r5 = r5 + r13
            r0.mOffset = r5
            long r7 = r7 - r13
            java.io.InputStream r5 = r0.mData     // Catch:{  }
            int r5 = r5.read(r2, r12, r9)     // Catch:{  }
            if (r5 == r9) goto L_0x0084
            return r15
        L_0x0084:
            java.io.InputStream r5 = r0.mData     // Catch:{  }
            int r5 = r5.read(r2, r12, r3)     // Catch:{  }
            if (r5 == r3) goto L_0x008d
            return r15
        L_0x008d:
            long r5 = r0.mOffset
            long r5 = r5 + r10
            r0.mOffset = r5
            long r7 = r7 - r10
            if (r3 != r9) goto L_0x009a
            int r5 = r0.bytetoUInt16(r2)
            goto L_0x009f
        L_0x009a:
            long r5 = byte2toUInt32(r2)
            int r5 = (int) r5
        L_0x009f:
            com.samsung.android.motionphoto.utils.ex.ItemReference r6 = new com.samsung.android.motionphoto.utils.ex.ItemReference
            r6.<init>()
            r6.itemID = r4
            java.util.Vector<java.lang.Integer> r4 = r6.referenceItems
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r4.add(r5)
            java.util.Vector<com.samsung.android.motionphoto.utils.ex.ItemReference> r4 = r0.referenceList
            r4.add(r6)
            r18 = r2
            goto L_0x00c6
        L_0x00b7:
            return r15
        L_0x00b8:
            r18 = r2
            long r1 = r0.mOffset
            long r13 = r13 - r4
            long r1 = r1 + r13
            r0.mOffset = r1
            long r7 = r7 - r13
            java.io.InputStream r1 = r0.mData     // Catch:{ Exception -> 0x00ce }
            r1.skip(r13)     // Catch:{ Exception -> 0x00ce }
        L_0x00c6:
            r4 = r15
            r5 = r16
            r2 = r18
            r1 = 4
            goto L_0x0019
        L_0x00ce:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Exception: "
            r1.<init>(r2)
            java.lang.String r0 = r0.toString()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            android.util.Log.e(r11, r0)
        L_0x00e4:
            return r15
        L_0x00e5:
            r15 = r4
            return r15
        L_0x00e7:
            r15 = r4
            if (r10 >= 0) goto L_0x00eb
            return r15
        L_0x00eb:
            r1 = 1
            r0.mFoundirefChunk = r1
            java.lang.String r0 = "Found iref Chunk"
            android.util.Log.i(r11, r0)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.ex.HEIFParser.parseIrefBox(long):int");
    }

    private int parsePitmBox() {
        int i2 = 4;
        byte[] bArr = new byte[4];
        if (parseFullBoxHeader() != 0) {
            return -1;
        }
        this.mRemainChunkSize -= 4;
        if (this.mVersion == 0) {
            i2 = 2;
        }
        try {
            if (this.mData.read(bArr, 0, i2) != i2) {
                return -1;
            }
            this.mOffset += (long) i2;
            this.mRemainChunkSize -= i2;
            if (i2 == 2) {
                this.mCoverImageID = bytetoUInt16(bArr);
            } else {
                this.mCoverImageID = (int) byte2toUInt32(bArr);
            }
            this.mFoundpitmChunk = 1;
            return 0;
        } catch (Exception unused) {
            return -1;
        }
    }

    public MotionPhotoVideoUtils.XMPInformation getCoverImageXMPOffsetAndSize(InputStream inputStream) {
        this.mData = inputStream;
        byte[] bArr = new byte[4];
        while (true) {
            if (this.mFoundiinfChunk != 1 || this.mFoundpitmChunk != 1 || this.mFoundirefChunk != 1 || this.mFoundilocChunk != 1) {
                try {
                    if (this.mData.read(bArr, 0, 4) != 4) {
                        return null;
                    }
                    this.mOffset += 4;
                    long byte2toUInt32 = byte2toUInt32(bArr);
                    try {
                        if (this.mData.read(bArr, 0, 4) != 4) {
                            return null;
                        }
                        this.mOffset += 4;
                        long j2 = byte2toUInt32 - 8;
                        String str = new String(bArr);
                        if (str.equals(MediaDefs.Image.HEIF.HEIF_IINF_BOX)) {
                            parseIinfBox();
                        } else if (str.equals(MediaDefs.Image.HEIF.HEIF_IREF_BOX)) {
                            parseIrefBox(j2);
                        } else if (str.equals(MediaDefs.Image.HEIF.HEIF_PITM_BOX)) {
                            parsePitmBox();
                        } else if (str.equals(MediaDefs.Image.HEIF.HEIF_ILOC_BOX)) {
                            parseIlocBox();
                        } else if (str.equals(MediaDefs.Image.HEIF.HEIF_META_BOX)) {
                            this.mOffset += 4;
                            try {
                                this.mData.skip(4);
                            } catch (Exception e) {
                                Log.e(TAG, "Exception: " + e.toString());
                                return null;
                            }
                        } else {
                            try {
                                this.mData.skip(j2);
                                this.mOffset += j2;
                            } catch (Exception e7) {
                                Log.e(TAG, "Exception: " + e7.toString());
                                return null;
                            }
                        }
                    } catch (Exception unused) {
                        Log.i(TAG, "read fail");
                        return null;
                    }
                } catch (Exception unused2) {
                    return null;
                }
            } else if (this.mXMPMetadataIDs.isEmpty()) {
                return null;
            } else {
                for (int i2 = 0; i2 < this.referenceList.size(); i2++) {
                    ItemReference itemReference = this.referenceList.get(i2);
                    int intValue = itemReference.referenceItems.get(0).intValue();
                    int i7 = itemReference.itemID;
                    if (intValue == this.mCoverImageID && this.mXMPMetadataIDs.contains(Integer.valueOf(i7))) {
                        for (int i8 = 0; i8 < this.locationList.size(); i8++) {
                            ItemLocation itemLocation = this.locationList.get(i8);
                            if (itemLocation.itemID == ((long) i7)) {
                                FileInputStream fileInputStream = (FileInputStream) inputStream;
                                byte[] bArr2 = new byte[((int) itemLocation.length)];
                                fileInputStream.getChannel().position(itemLocation.base_offset + itemLocation.offset);
                                fileInputStream.read(bArr2, 0, (int) itemLocation.length);
                                return new MotionPhotoVideoUtils.XMPInformation(itemLocation.base_offset + itemLocation.offset, itemLocation.length, bArr2);
                            }
                        }
                        continue;
                    }
                }
                return null;
            }
        }
    }
}
