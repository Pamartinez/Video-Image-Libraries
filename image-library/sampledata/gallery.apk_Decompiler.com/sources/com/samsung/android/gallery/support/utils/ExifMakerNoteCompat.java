package com.samsung.android.gallery.support.utils;

import A.a;
import N2.j;
import android.content.Context;
import com.samsung.android.gallery.support.R$string;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.o3dp.jpeg3dcontainer.model.Mpf;
import i.C0212a;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ExifMakerNoteCompat {
    int count;
    final byte[] makerNote;
    final HashMap<Integer, NoteEntity> map = new HashMap<>();
    String version = "0";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class LensNameHolder {
        static final HashMap<Integer, Integer> map = new HashMap<Integer, Integer>() {
            {
                put(1, Integer.valueOf(R$string.lens_standard));
                put(2, Integer.valueOf(R$string.lens_wide));
                put(3, Integer.valueOf(R$string.lens_uw));
                put(4, Integer.valueOf(R$string.lens_tele));
                put(5, Integer.valueOf(R$string.lens_stele));
                put(6, Integer.valueOf(R$string.lens_macro));
                put(21, Integer.valueOf(R$string.lens_front_standard));
                put(22, Integer.valueOf(R$string.lens_front_wide));
            }
        };

        public static int get(int i2) {
            return map.getOrDefault(Integer.valueOf(i2), Integer.valueOf(R$string.unknown)).intValue();
        }
    }

    public ExifMakerNoteCompat(byte[] bArr) {
        this.makerNote = bArr;
    }

    public boolean available() {
        if (!"0101".equals(this.version) || !this.map.containsKey(Integer.valueOf(Mpf.IndexIFDTag.ID_MPF_VERSION))) {
            return false;
        }
        return true;
    }

    public long getDeviceType() {
        return getLongValue(2);
    }

    public long getFocalLengthIn35mm() {
        return getLongValue(40986);
    }

    public long getLensType() {
        return getLongValue(Mpf.IndexIFDTag.ID_MPF_VERSION);
    }

    public long getLongValue(int i2) {
        Object obj;
        try {
            NoteEntity noteEntity = this.map.get(Integer.valueOf(i2));
            if (noteEntity != null) {
                obj = noteEntity.valueOf();
            } else {
                obj = null;
            }
            if (obj != null) {
                return ((Long) obj).longValue();
            }
        } catch (Error | Exception unused) {
        }
        return 0;
    }

    public String getReadableLensType(Context context) {
        return context.getString(LensNameHolder.get((int) getLensType()));
    }

    public String getVersion() {
        return this.version;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("MakerNote{");
        sb2.append(getVersion());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(getFocalLengthIn35mm());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(getLensType());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(getDeviceType());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.count);
        sb2.append(",[");
        return C0212a.p(sb2, (String) this.map.values().stream().map(new C0670h(0)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX)), "]}");
    }

    public ExifMakerNoteCompat unpack() {
        byte[] bArr = this.makerNote;
        if (bArr != null && bArr.length > 2) {
            try {
                ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
                short s = order.getShort();
                this.count = s;
                if (s > this.makerNote.length / 8) {
                    Log.e((CharSequence) "ExifMakerNoteCompat", "unpack wrong count", Integer.valueOf(s), Integer.valueOf(this.makerNote.length));
                    return this;
                }
                for (int i2 = 0; i2 < this.count; i2++) {
                    NoteEntity noteEntity = new NoteEntity(order);
                    this.map.put(Integer.valueOf(noteEntity.tag), noteEntity);
                    if (noteEntity.tag == 1) {
                        String str = new String(noteEntity.data);
                        this.version = str;
                        if (!"0101".equals(str)) {
                            break;
                        }
                    }
                }
            } catch (Error | Exception e) {
                a.z(e, new StringBuilder("unpack failed. e="), "ExifMakerNoteCompat");
            }
        }
        return this;
    }

    public static String getReadableLensType(Context context, int i2) {
        return context.getString(LensNameHolder.get(i2));
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class NoteEntity {
        static final int[] DATA_FORMAT_BYTES = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 1, 1, 1};
        int count;
        byte[] data;
        int format;
        int tag;
        Object value;

        public NoteEntity(ByteBuffer byteBuffer) {
            this.tag = (byteBuffer.get() & 255) | ((byteBuffer.get() & 255) << 8);
            this.format = (byteBuffer.get() & 255) | ((byteBuffer.get() & 255) << 8);
            int i2 = byteBuffer.getInt();
            this.count = i2;
            int i7 = i2 * DATA_FORMAT_BYTES[this.format];
            if (i7 <= byteBuffer.remaining()) {
                byte[] bArr = new byte[i7];
                this.data = bArr;
                byteBuffer.get(bArr);
                return;
            }
            StringBuilder sb2 = new StringBuilder("count exceed pos=");
            sb2.append(byteBuffer.position());
            sb2.append(", count=");
            j.x(sb2, this.count, ", size=", i7, ", remained=");
            sb2.append(byteBuffer.remaining());
            throw new IllegalStateException(sb2.toString());
        }

        public String toString() {
            Object obj;
            StringBuilder sb2 = new StringBuilder("NoteEntity{0x");
            sb2.append(Integer.toHexString(this.tag));
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.format);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.count);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            if (this.format == 7) {
                obj = Arrays.toString(this.data);
            } else {
                obj = valueOf();
            }
            sb2.append(obj);
            sb2.append("}");
            return sb2.toString();
        }

        public Object valueOf() {
            if (this.value == null) {
                this.value = valueOf(this.data, this.format);
            }
            return this.value;
        }

        public Object valueOf(byte[] bArr, int i2) {
            if (i2 == 1) {
                return Long.valueOf((long) (bArr[0] & 255));
            }
            if (i2 == 2) {
                return new String(bArr);
            }
            if (i2 == 3) {
                return Long.valueOf(((long) ((bArr[1] & 255) << 8)) | ((long) (bArr[0] & 255)));
            } else if (i2 == 4) {
                long j2 = (long) (bArr[0] & 255);
                return Long.valueOf((((long) (bArr[3] & 255)) << 24) | (((long) (bArr[1] & 255)) << 8) | j2 | (((long) (bArr[2] & 255)) << 16));
            } else if (i2 == 5) {
                return Long.valueOf(ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).getLong());
            } else {
                if (i2 == 6) {
                    return Long.valueOf((long) this.data[0]);
                }
                if (i2 == 7) {
                    return bArr;
                }
                if (i2 == 8) {
                    return Long.valueOf((long) ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).getShort());
                }
                if (i2 == 9) {
                    return Long.valueOf((long) ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).getInt());
                }
                if (i2 == 10) {
                    return Long.valueOf(ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).getLong());
                }
                if (i2 == 11) {
                    return Double.valueOf((double) ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).getFloat());
                }
                if (i2 == 12) {
                    return Double.valueOf(ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).getDouble());
                }
                return null;
            }
        }
    }
}
