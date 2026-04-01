package androidx.work;

import B1.a;
import He.C0748d;
import c0.C0086a;
import com.samsung.android.sdk.bixby2.state.StateHandler;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import ne.C1192j;
import ne.C1194l;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000e\u0018\u0000 !2\u00020\u0001:\u0002\"!B\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004B\u001b\b\u0010\u0012\u0010\u0010\u0007\u001a\f\u0012\u0004\u0012\u00020\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0004\b\u0003\u0010\bJ\u001d\u0010\f\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\u0006¢\u0006\u0004\b\u000e\u0010\u000fJ)\u0010\u0013\u001a\u00020\n\"\u0004\b\u0000\u0010\u00102\u0006\u0010\t\u001a\u00020\u00062\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0016\u001a\u00020\u0015H\u0007¢\u0006\u0004\b\u0016\u0010\u0017J\u001a\u0010\u0018\u001a\u00020\n2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u001a\u0010\u0017J\u000f\u0010\u001b\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u001b\u0010\u001cR\"\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00058\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u001dR\u001f\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00058F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u0006#"}, d2 = {"Landroidx/work/Data;", "", "other", "<init>", "(Landroidx/work/Data;)V", "", "", "values", "(Ljava/util/Map;)V", "key", "", "defaultValue", "getBoolean", "(Ljava/lang/String;Z)Z", "getString", "(Ljava/lang/String;)Ljava/lang/String;", "T", "Ljava/lang/Class;", "klass", "hasKeyWithValueOfType", "(Ljava/lang/String;Ljava/lang/Class;)Z", "", "size", "()I", "equals", "(Ljava/lang/Object;)Z", "hashCode", "toString", "()Ljava/lang/String;", "Ljava/util/Map;", "getKeyValueMap", "()Ljava/util/Map;", "keyValueMap", "Companion", "Builder", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Data {
    public static final Companion Companion = new Companion((e) null);
    public static final Data EMPTY = new Builder().build();
    /* access modifiers changed from: private */
    public final Map<String, Object> values;

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\n\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\f\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011J#\u0010\u0010\u001a\u00020\u00002\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0012¢\u0006\u0004\b\u0010\u0010\u0014J!\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0004\b\u0015\u0010\bJ\r\u0010\u0016\u001a\u00020\u000e¢\u0006\u0004\b\u0016\u0010\u0017R\"\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00188\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0019¨\u0006\u001a"}, d2 = {"Landroidx/work/Data$Builder;", "", "<init>", "()V", "", "key", "value", "putDirect", "(Ljava/lang/String;Ljava/lang/Object;)Landroidx/work/Data$Builder;", "", "putBoolean", "(Ljava/lang/String;Z)Landroidx/work/Data$Builder;", "putString", "(Ljava/lang/String;Ljava/lang/String;)Landroidx/work/Data$Builder;", "Landroidx/work/Data;", "data", "putAll", "(Landroidx/work/Data;)Landroidx/work/Data$Builder;", "", "values", "(Ljava/util/Map;)Landroidx/work/Data$Builder;", "put", "build", "()Landroidx/work/Data;", "", "Ljava/util/Map;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private final Map<String, Object> values = new LinkedHashMap();

        private final Builder putDirect(String str, Object obj) {
            this.values.put(str, obj);
            return this;
        }

        public final Data build() {
            Data data = new Data((Map<String, ?>) this.values);
            Data.Companion.toByteArrayInternalV1(data);
            return data;
        }

        public final Builder put(String str, Object obj) {
            boolean z;
            boolean z3;
            boolean z7;
            boolean z9;
            boolean z10;
            boolean z11;
            boolean z12;
            boolean z13;
            boolean z14;
            boolean z15;
            boolean z16;
            boolean z17;
            j.e(str, "key");
            Map<String, Object> map = this.values;
            if (obj == null) {
                obj = null;
            } else {
                Class<?> cls = obj.getClass();
                w wVar = v.f4727a;
                C0748d b = wVar.b(cls);
                boolean z18 = true;
                if (b.equals(wVar.b(Boolean.TYPE))) {
                    z = true;
                } else {
                    z = b.equals(wVar.b(Byte.TYPE));
                }
                if (z) {
                    z3 = true;
                } else {
                    z3 = b.equals(wVar.b(Integer.TYPE));
                }
                if (z3) {
                    z7 = true;
                } else {
                    z7 = b.equals(wVar.b(Long.TYPE));
                }
                if (z7) {
                    z9 = true;
                } else {
                    z9 = b.equals(wVar.b(Float.TYPE));
                }
                if (z9) {
                    z10 = true;
                } else {
                    z10 = b.equals(wVar.b(Double.TYPE));
                }
                if (z10) {
                    z11 = true;
                } else {
                    z11 = b.equals(wVar.b(String.class));
                }
                if (z11) {
                    z12 = true;
                } else {
                    z12 = b.equals(wVar.b(Boolean[].class));
                }
                if (z12) {
                    z13 = true;
                } else {
                    z13 = b.equals(wVar.b(Byte[].class));
                }
                if (z13) {
                    z14 = true;
                } else {
                    z14 = b.equals(wVar.b(Integer[].class));
                }
                if (z14) {
                    z15 = true;
                } else {
                    z15 = b.equals(wVar.b(Long[].class));
                }
                if (z15) {
                    z16 = true;
                } else {
                    z16 = b.equals(wVar.b(Float[].class));
                }
                if (z16) {
                    z17 = true;
                } else {
                    z17 = b.equals(wVar.b(Double[].class));
                }
                if (!z17) {
                    z18 = b.equals(wVar.b(String[].class));
                }
                if (!z18) {
                    if (b.equals(wVar.b(boolean[].class))) {
                        obj = Data_Kt.convertPrimitiveArray((boolean[]) obj);
                    } else if (b.equals(wVar.b(byte[].class))) {
                        obj = Data_Kt.convertPrimitiveArray((byte[]) obj);
                    } else if (b.equals(wVar.b(int[].class))) {
                        obj = Data_Kt.convertPrimitiveArray((int[]) obj);
                    } else if (b.equals(wVar.b(long[].class))) {
                        obj = Data_Kt.convertPrimitiveArray((long[]) obj);
                    } else if (b.equals(wVar.b(float[].class))) {
                        obj = Data_Kt.convertPrimitiveArray((float[]) obj);
                    } else if (b.equals(wVar.b(double[].class))) {
                        obj = Data_Kt.convertPrimitiveArray((double[]) obj);
                    } else {
                        throw new IllegalArgumentException("Key " + str + " has invalid type " + b);
                    }
                }
            }
            map.put(str, obj);
            return this;
        }

        public final Builder putAll(Data data) {
            j.e(data, "data");
            putAll((Map<String, ? extends Object>) data.values);
            return this;
        }

        public final Builder putBoolean(String str, boolean z) {
            j.e(str, "key");
            return putDirect(str, Boolean.valueOf(z));
        }

        public final Builder putString(String str, String str2) {
            j.e(str, "key");
            return putDirect(str, str2);
        }

        public final Builder putAll(Map<String, ? extends Object> map) {
            j.e(map, StateHandler.VALUES);
            for (Map.Entry next : map.entrySet()) {
                put((String) next.getKey(), next.getValue());
            }
            return this;
        }
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0011\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\n\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0006\n\u0004\b\f\u0010\rR\u0014\u0010\u000f\u001a\u00020\u000e8\u0006XT¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0012\u001a\u00020\u00118\u0002XT¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\u00148\u0002XT¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00148\u0002XT¢\u0006\u0006\n\u0004\b\u0017\u0010\u0016R\u0014\u0010\u0019\u001a\u00020\u00188\u0002XT¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u00188\u0002XT¢\u0006\u0006\n\u0004\b\u001b\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u00188\u0002XT¢\u0006\u0006\n\u0004\b\u001c\u0010\u001aR\u0014\u0010\u001d\u001a\u00020\u00188\u0002XT¢\u0006\u0006\n\u0004\b\u001d\u0010\u001aR\u0014\u0010\u001e\u001a\u00020\u00188\u0002XT¢\u0006\u0006\n\u0004\b\u001e\u0010\u001aR\u0014\u0010\u001f\u001a\u00020\u00188\u0002XT¢\u0006\u0006\n\u0004\b\u001f\u0010\u001aR\u0014\u0010 \u001a\u00020\u00188\u0002XT¢\u0006\u0006\n\u0004\b \u0010\u001aR\u0014\u0010!\u001a\u00020\u00188\u0002XT¢\u0006\u0006\n\u0004\b!\u0010\u001aR\u0014\u0010\"\u001a\u00020\u00188\u0002XT¢\u0006\u0006\n\u0004\b\"\u0010\u001aR\u0014\u0010#\u001a\u00020\u00188\u0002XT¢\u0006\u0006\n\u0004\b#\u0010\u001aR\u0014\u0010$\u001a\u00020\u00188\u0002XT¢\u0006\u0006\n\u0004\b$\u0010\u001aR\u0014\u0010%\u001a\u00020\u00188\u0002XT¢\u0006\u0006\n\u0004\b%\u0010\u001aR\u0014\u0010&\u001a\u00020\u00188\u0002XT¢\u0006\u0006\n\u0004\b&\u0010\u001aR\u0014\u0010'\u001a\u00020\u00188\u0002XT¢\u0006\u0006\n\u0004\b'\u0010\u001aR\u0014\u0010(\u001a\u00020\u00188\u0002XT¢\u0006\u0006\n\u0004\b(\u0010\u001a¨\u0006)"}, d2 = {"Landroidx/work/Data$Companion;", "", "<init>", "()V", "Landroidx/work/Data;", "data", "", "toByteArrayInternalV1", "(Landroidx/work/Data;)[B", "bytes", "fromByteArray", "([B)Landroidx/work/Data;", "EMPTY", "Landroidx/work/Data;", "", "MAX_DATA_BYTES", "I", "", "NULL_STRING_V1", "Ljava/lang/String;", "", "STREAM_MAGIC", "S", "STREAM_VERSION", "", "TYPE_BOOLEAN", "B", "TYPE_BOOLEAN_ARRAY", "TYPE_BYTE", "TYPE_BYTE_ARRAY", "TYPE_DOUBLE", "TYPE_DOUBLE_ARRAY", "TYPE_FLOAT", "TYPE_FLOAT_ARRAY", "TYPE_INTEGER", "TYPE_INTEGER_ARRAY", "TYPE_LONG", "TYPE_LONG_ARRAY", "TYPE_NULL", "TYPE_STRING", "TYPE_STRING_ARRAY", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private static final boolean fromByteArray$isObjectStream(ByteArrayInputStream byteArrayInputStream) {
            byte[] bArr = new byte[2];
            byteArrayInputStream.read(bArr);
            byte b = (byte) -21267;
            boolean z = false;
            if (bArr[0] == ((byte) 16777132) && bArr[1] == b) {
                z = true;
            }
            byteArrayInputStream.reset();
            return z;
        }

        private static final void fromByteArray$readHeader(DataInputStream dataInputStream) {
            short readShort = dataInputStream.readShort();
            if (readShort == -21521) {
                short readShort2 = dataInputStream.readShort();
                if (readShort2 != 1) {
                    throw new IllegalStateException(C0086a.i(readShort2, "Unsupported version number: ").toString());
                }
                return;
            }
            throw new IllegalStateException(C0086a.i(readShort, "Magic number doesn't match: ").toString());
        }

        private static final Object fromByteArray$readValue(DataInputStream dataInputStream, byte b) {
            if (b == 0) {
                return null;
            }
            if (b == 1) {
                return Boolean.valueOf(dataInputStream.readBoolean());
            }
            if (b == 2) {
                return Byte.valueOf(dataInputStream.readByte());
            }
            if (b == 3) {
                return Integer.valueOf(dataInputStream.readInt());
            }
            if (b == 4) {
                return Long.valueOf(dataInputStream.readLong());
            }
            if (b == 5) {
                return Float.valueOf(dataInputStream.readFloat());
            }
            if (b == 6) {
                return Double.valueOf(dataInputStream.readDouble());
            }
            if (b == 7) {
                return dataInputStream.readUTF();
            }
            int i2 = 0;
            if (b == 8) {
                int readInt = dataInputStream.readInt();
                Boolean[] boolArr = new Boolean[readInt];
                while (i2 < readInt) {
                    boolArr[i2] = Boolean.valueOf(dataInputStream.readBoolean());
                    i2++;
                }
                return boolArr;
            } else if (b == 9) {
                int readInt2 = dataInputStream.readInt();
                Byte[] bArr = new Byte[readInt2];
                while (i2 < readInt2) {
                    bArr[i2] = Byte.valueOf(dataInputStream.readByte());
                    i2++;
                }
                return bArr;
            } else if (b == 10) {
                int readInt3 = dataInputStream.readInt();
                Integer[] numArr = new Integer[readInt3];
                while (i2 < readInt3) {
                    numArr[i2] = Integer.valueOf(dataInputStream.readInt());
                    i2++;
                }
                return numArr;
            } else if (b == 11) {
                int readInt4 = dataInputStream.readInt();
                Long[] lArr = new Long[readInt4];
                while (i2 < readInt4) {
                    lArr[i2] = Long.valueOf(dataInputStream.readLong());
                    i2++;
                }
                return lArr;
            } else if (b == 12) {
                int readInt5 = dataInputStream.readInt();
                Float[] fArr = new Float[readInt5];
                while (i2 < readInt5) {
                    fArr[i2] = Float.valueOf(dataInputStream.readFloat());
                    i2++;
                }
                return fArr;
            } else if (b == 13) {
                int readInt6 = dataInputStream.readInt();
                Double[] dArr = new Double[readInt6];
                while (i2 < readInt6) {
                    dArr[i2] = Double.valueOf(dataInputStream.readDouble());
                    i2++;
                }
                return dArr;
            } else if (b == 14) {
                int readInt7 = dataInputStream.readInt();
                String[] strArr = new String[readInt7];
                while (i2 < readInt7) {
                    String readUTF = dataInputStream.readUTF();
                    if (j.a(readUTF, "androidx.work.Data-95ed6082-b8e9-46e8-a73f-ff56f00f5d9d")) {
                        readUTF = null;
                    }
                    strArr[i2] = readUTF;
                    i2++;
                }
                return strArr;
            } else {
                throw new IllegalStateException(C0086a.i(b, "Unsupported type "));
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v0, resolved type: java.lang.Boolean} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v1, resolved type: java.lang.String} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v2, resolved type: java.lang.String} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v4, resolved type: java.lang.String} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v6, resolved type: java.lang.Double} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v7, resolved type: java.lang.Double} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v9, resolved type: java.lang.Float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v10, resolved type: java.lang.Float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v12, resolved type: java.lang.Long} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v13, resolved type: java.lang.Long} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v15, resolved type: java.lang.Integer} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v16, resolved type: java.lang.Integer} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v18, resolved type: java.lang.Byte} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v19, resolved type: java.lang.Byte} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v21, resolved type: java.lang.Boolean} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v22, resolved type: java.lang.Boolean} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v24, resolved type: java.lang.String} */
        /* JADX WARNING: type inference failed for: r12v0 */
        /* JADX WARNING: type inference failed for: r12v25 */
        /* JADX WARNING: type inference failed for: r12v26 */
        /* JADX WARNING: type inference failed for: r12v27 */
        /* JADX WARNING: type inference failed for: r12v28 */
        /* JADX WARNING: type inference failed for: r12v29 */
        /* JADX WARNING: type inference failed for: r12v30 */
        /* JADX WARNING: type inference failed for: r12v31 */
        /* JADX WARNING: Failed to insert additional move for type inference */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static final void toByteArrayInternalV1$writeArray(java.io.DataOutputStream r14, java.lang.Object[] r15) {
            /*
                java.lang.Class r0 = r15.getClass()
                kotlin.jvm.internal.w r1 = kotlin.jvm.internal.v.f4727a
                He.d r0 = r1.b(r0)
                java.lang.Class<java.lang.Boolean[]> r2 = java.lang.Boolean[].class
                He.d r2 = r1.b(r2)
                boolean r2 = r0.equals(r2)
                r3 = 14
                r4 = 13
                r5 = 12
                r6 = 11
                r7 = 10
                r8 = 9
                r9 = 8
                if (r2 == 0) goto L_0x0026
                r0 = r9
                goto L_0x0079
            L_0x0026:
                java.lang.Class<java.lang.Byte[]> r2 = java.lang.Byte[].class
                He.d r2 = r1.b(r2)
                boolean r2 = r0.equals(r2)
                if (r2 == 0) goto L_0x0034
                r0 = r8
                goto L_0x0079
            L_0x0034:
                java.lang.Class<java.lang.Integer[]> r2 = java.lang.Integer[].class
                He.d r2 = r1.b(r2)
                boolean r2 = r0.equals(r2)
                if (r2 == 0) goto L_0x0042
                r0 = r7
                goto L_0x0079
            L_0x0042:
                java.lang.Class<java.lang.Long[]> r2 = java.lang.Long[].class
                He.d r2 = r1.b(r2)
                boolean r2 = r0.equals(r2)
                if (r2 == 0) goto L_0x0050
                r0 = r6
                goto L_0x0079
            L_0x0050:
                java.lang.Class<java.lang.Float[]> r2 = java.lang.Float[].class
                He.d r2 = r1.b(r2)
                boolean r2 = r0.equals(r2)
                if (r2 == 0) goto L_0x005e
                r0 = r5
                goto L_0x0079
            L_0x005e:
                java.lang.Class<java.lang.Double[]> r2 = java.lang.Double[].class
                He.d r2 = r1.b(r2)
                boolean r2 = r0.equals(r2)
                if (r2 == 0) goto L_0x006c
                r0 = r4
                goto L_0x0079
            L_0x006c:
                java.lang.Class<java.lang.String[]> r2 = java.lang.String[].class
                He.d r2 = r1.b(r2)
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x011f
                r0 = r3
            L_0x0079:
                r14.writeByte(r0)
                int r1 = r15.length
                r14.writeInt(r1)
                int r1 = r15.length
                r2 = 0
                r10 = r2
            L_0x0083:
                if (r10 >= r1) goto L_0x011e
                r11 = r15[r10]
                r12 = 0
                if (r0 != r9) goto L_0x009e
                boolean r13 = r11 instanceof java.lang.Boolean
                if (r13 == 0) goto L_0x0091
                r12 = r11
                java.lang.Boolean r12 = (java.lang.Boolean) r12
            L_0x0091:
                if (r12 == 0) goto L_0x0098
                boolean r11 = r12.booleanValue()
                goto L_0x0099
            L_0x0098:
                r11 = r2
            L_0x0099:
                r14.writeBoolean(r11)
                goto L_0x011a
            L_0x009e:
                if (r0 != r8) goto L_0x00b4
                boolean r13 = r11 instanceof java.lang.Byte
                if (r13 == 0) goto L_0x00a7
                r12 = r11
                java.lang.Byte r12 = (java.lang.Byte) r12
            L_0x00a7:
                if (r12 == 0) goto L_0x00ae
                byte r11 = r12.byteValue()
                goto L_0x00af
            L_0x00ae:
                r11 = r2
            L_0x00af:
                r14.writeByte(r11)
                goto L_0x011a
            L_0x00b4:
                if (r0 != r7) goto L_0x00c9
                boolean r13 = r11 instanceof java.lang.Integer
                if (r13 == 0) goto L_0x00bd
                r12 = r11
                java.lang.Integer r12 = (java.lang.Integer) r12
            L_0x00bd:
                if (r12 == 0) goto L_0x00c4
                int r11 = r12.intValue()
                goto L_0x00c5
            L_0x00c4:
                r11 = r2
            L_0x00c5:
                r14.writeInt(r11)
                goto L_0x011a
            L_0x00c9:
                if (r0 != r6) goto L_0x00df
                boolean r13 = r11 instanceof java.lang.Long
                if (r13 == 0) goto L_0x00d2
                r12 = r11
                java.lang.Long r12 = (java.lang.Long) r12
            L_0x00d2:
                if (r12 == 0) goto L_0x00d9
                long r11 = r12.longValue()
                goto L_0x00db
            L_0x00d9:
                r11 = 0
            L_0x00db:
                r14.writeLong(r11)
                goto L_0x011a
            L_0x00df:
                if (r0 != r5) goto L_0x00f4
                boolean r13 = r11 instanceof java.lang.Float
                if (r13 == 0) goto L_0x00e8
                r12 = r11
                java.lang.Float r12 = (java.lang.Float) r12
            L_0x00e8:
                if (r12 == 0) goto L_0x00ef
                float r11 = r12.floatValue()
                goto L_0x00f0
            L_0x00ef:
                r11 = 0
            L_0x00f0:
                r14.writeFloat(r11)
                goto L_0x011a
            L_0x00f4:
                if (r0 != r4) goto L_0x010a
                boolean r13 = r11 instanceof java.lang.Double
                if (r13 == 0) goto L_0x00fd
                r12 = r11
                java.lang.Double r12 = (java.lang.Double) r12
            L_0x00fd:
                if (r12 == 0) goto L_0x0104
                double r11 = r12.doubleValue()
                goto L_0x0106
            L_0x0104:
                r11 = 0
            L_0x0106:
                r14.writeDouble(r11)
                goto L_0x011a
            L_0x010a:
                if (r0 != r3) goto L_0x011a
                boolean r13 = r11 instanceof java.lang.String
                if (r13 == 0) goto L_0x0113
                r12 = r11
                java.lang.String r12 = (java.lang.String) r12
            L_0x0113:
                if (r12 != 0) goto L_0x0117
                java.lang.String r12 = "androidx.work.Data-95ed6082-b8e9-46e8-a73f-ff56f00f5d9d"
            L_0x0117:
                r14.writeUTF(r12)
            L_0x011a:
                int r10 = r10 + 1
                goto L_0x0083
            L_0x011e:
                return
            L_0x011f:
                java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r2 = "Unsupported value type "
                r0.<init>(r2)
                java.lang.Class r15 = r15.getClass()
                He.d r15 = r1.b(r15)
                java.lang.String r15 = r15.n()
                r0.append(r15)
                java.lang.String r15 = r0.toString()
                r14.<init>(r15)
                throw r14
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.work.Data.Companion.toByteArrayInternalV1$writeArray(java.io.DataOutputStream, java.lang.Object[]):void");
        }

        private static final void toByteArrayInternalV1$writeEntry(DataOutputStream dataOutputStream, String str, Object obj) {
            if (obj == null) {
                dataOutputStream.writeByte(0);
            } else if (obj instanceof Boolean) {
                dataOutputStream.writeByte(1);
                dataOutputStream.writeBoolean(((Boolean) obj).booleanValue());
            } else if (obj instanceof Byte) {
                dataOutputStream.writeByte(2);
                dataOutputStream.writeByte(((Number) obj).byteValue());
            } else if (obj instanceof Integer) {
                dataOutputStream.writeByte(3);
                dataOutputStream.writeInt(((Number) obj).intValue());
            } else if (obj instanceof Long) {
                dataOutputStream.writeByte(4);
                dataOutputStream.writeLong(((Number) obj).longValue());
            } else if (obj instanceof Float) {
                dataOutputStream.writeByte(5);
                dataOutputStream.writeFloat(((Number) obj).floatValue());
            } else if (obj instanceof Double) {
                dataOutputStream.writeByte(6);
                dataOutputStream.writeDouble(((Number) obj).doubleValue());
            } else if (obj instanceof String) {
                dataOutputStream.writeByte(7);
                dataOutputStream.writeUTF((String) obj);
            } else if (obj instanceof Object[]) {
                toByteArrayInternalV1$writeArray(dataOutputStream, (Object[]) obj);
            } else {
                throw new IllegalArgumentException("Unsupported value type " + v.f4727a.b(obj.getClass()).o());
            }
            dataOutputStream.writeUTF(str);
        }

        private static final void toByteArrayInternalV1$writeHeader(DataOutputStream dataOutputStream) {
            dataOutputStream.writeShort(-21521);
            dataOutputStream.writeShort(1);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:23:0x004c, code lost:
            r2 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
            B1.a.g(r7, r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0050, code lost:
            throw r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x007d, code lost:
            r2 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
            B1.a.g(r7, r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x0081, code lost:
            throw r2;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final androidx.work.Data fromByteArray(byte[] r7) {
            /*
                r6 = this;
                java.lang.String r6 = "Error in Data#fromByteArray: "
                java.lang.String r0 = "bytes"
                kotlin.jvm.internal.j.e(r7, r0)
                int r0 = r7.length
                r1 = 10240(0x2800, float:1.4349E-41)
                if (r0 > r1) goto L_0x009f
                int r0 = r7.length
                if (r0 != 0) goto L_0x0012
                androidx.work.Data r6 = androidx.work.Data.EMPTY
                return r6
            L_0x0012:
                java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
                r0.<init>()
                java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch:{ IOException -> 0x0049, ClassNotFoundException -> 0x0047 }
                r1.<init>(r7)     // Catch:{ IOException -> 0x0049, ClassNotFoundException -> 0x0047 }
                boolean r7 = fromByteArray$isObjectStream(r1)     // Catch:{ IOException -> 0x0049, ClassNotFoundException -> 0x0047 }
                r2 = 0
                if (r7 == 0) goto L_0x0051
                java.io.ObjectInputStream r7 = new java.io.ObjectInputStream     // Catch:{ IOException -> 0x0049, ClassNotFoundException -> 0x0047 }
                r7.<init>(r1)     // Catch:{ IOException -> 0x0049, ClassNotFoundException -> 0x0047 }
                int r1 = r7.readInt()     // Catch:{ all -> 0x0041 }
            L_0x002c:
                if (r2 >= r1) goto L_0x0043
                java.lang.String r3 = r7.readUTF()     // Catch:{ all -> 0x0041 }
                java.lang.String r4 = "readUTF()"
                kotlin.jvm.internal.j.d(r3, r4)     // Catch:{ all -> 0x0041 }
                java.lang.Object r4 = r7.readObject()     // Catch:{ all -> 0x0041 }
                r0.put(r3, r4)     // Catch:{ all -> 0x0041 }
                int r2 = r2 + 1
                goto L_0x002c
            L_0x0041:
                r1 = move-exception
                goto L_0x004b
            L_0x0043:
                r7.close()     // Catch:{ IOException -> 0x0049, ClassNotFoundException -> 0x0047 }
                goto L_0x0099
            L_0x0047:
                r7 = move-exception
                goto L_0x0082
            L_0x0049:
                r7 = move-exception
                goto L_0x008e
            L_0x004b:
                throw r1     // Catch:{ all -> 0x004c }
            L_0x004c:
                r2 = move-exception
                B1.a.g(r7, r1)     // Catch:{ IOException -> 0x0049, ClassNotFoundException -> 0x0047 }
                throw r2     // Catch:{ IOException -> 0x0049, ClassNotFoundException -> 0x0047 }
            L_0x0051:
                java.io.DataInputStream r7 = new java.io.DataInputStream     // Catch:{ IOException -> 0x0049, ClassNotFoundException -> 0x0047 }
                r7.<init>(r1)     // Catch:{ IOException -> 0x0049, ClassNotFoundException -> 0x0047 }
                fromByteArray$readHeader(r7)     // Catch:{ all -> 0x0076 }
                int r1 = r7.readInt()     // Catch:{ all -> 0x0076 }
            L_0x005d:
                if (r2 >= r1) goto L_0x0078
                byte r3 = r7.readByte()     // Catch:{ all -> 0x0076 }
                java.lang.Object r3 = fromByteArray$readValue(r7, r3)     // Catch:{ all -> 0x0076 }
                java.lang.String r4 = r7.readUTF()     // Catch:{ all -> 0x0076 }
                java.lang.String r5 = "key"
                kotlin.jvm.internal.j.d(r4, r5)     // Catch:{ all -> 0x0076 }
                r0.put(r4, r3)     // Catch:{ all -> 0x0076 }
                int r2 = r2 + 1
                goto L_0x005d
            L_0x0076:
                r1 = move-exception
                goto L_0x007c
            L_0x0078:
                r7.close()     // Catch:{ IOException -> 0x0049, ClassNotFoundException -> 0x0047 }
                goto L_0x0099
            L_0x007c:
                throw r1     // Catch:{ all -> 0x007d }
            L_0x007d:
                r2 = move-exception
                B1.a.g(r7, r1)     // Catch:{ IOException -> 0x0049, ClassNotFoundException -> 0x0047 }
                throw r2     // Catch:{ IOException -> 0x0049, ClassNotFoundException -> 0x0047 }
            L_0x0082:
                java.lang.String r1 = androidx.work.Data_Kt.TAG
                androidx.work.Logger r2 = androidx.work.Logger.get()
                r2.error(r1, r6, r7)
                goto L_0x0099
            L_0x008e:
                java.lang.String r1 = androidx.work.Data_Kt.TAG
                androidx.work.Logger r2 = androidx.work.Logger.get()
                r2.error(r1, r6, r7)
            L_0x0099:
                androidx.work.Data r6 = new androidx.work.Data
                r6.<init>((java.util.Map<java.lang.String, ?>) r0)
                return r6
            L_0x009f:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "Data cannot occupy more than 10240 bytes when serialized"
                r6.<init>(r7)
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.work.Data.Companion.fromByteArray(byte[]):androidx.work.Data");
        }

        public final byte[] toByteArrayInternalV1(Data data) {
            j.e(data, "data");
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                try {
                    toByteArrayInternalV1$writeHeader(dataOutputStream);
                    dataOutputStream.writeInt(data.size());
                    for (Map.Entry entry : data.values.entrySet()) {
                        toByteArrayInternalV1$writeEntry(dataOutputStream, (String) entry.getKey(), entry.getValue());
                    }
                    dataOutputStream.flush();
                    if (dataOutputStream.size() <= 10240) {
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        dataOutputStream.close();
                        j.d(byteArray, "{\n                ByteAr…          }\n            }");
                        return byteArray;
                    }
                    throw new IllegalStateException("Data cannot occupy more than 10240 bytes when serialized");
                } catch (Throwable th) {
                    a.g(dataOutputStream, th);
                    throw th;
                }
            } catch (IOException e) {
                Logger.get().error(Data_Kt.TAG, "Error in Data#toByteArray: ", e);
                return new byte[0];
            }
        }

        private Companion() {
        }
    }

    public Data(Data data) {
        j.e(data, "other");
        this.values = new HashMap(data.values);
    }

    public static final Data fromByteArray(byte[] bArr) {
        return Companion.fromByteArray(bArr);
    }

    public static final byte[] toByteArrayInternalV1(Data data) {
        return Companion.toByteArrayInternalV1(data);
    }

    public boolean equals(Object obj) {
        boolean z;
        if (this == obj) {
            return true;
        }
        if (obj == null || !Data.class.equals(obj.getClass())) {
            return false;
        }
        Data data = (Data) obj;
        Set<String> keySet = this.values.keySet();
        if (!j.a(keySet, data.values.keySet())) {
            return false;
        }
        for (String next : keySet) {
            Object obj2 = this.values.get(next);
            Object obj3 = data.values.get(next);
            if (obj2 != null && obj3 != null) {
                if (obj2 instanceof Object[]) {
                    Object[] objArr = (Object[]) obj2;
                    if (obj3 instanceof Object[]) {
                        z = C1192j.e0(objArr, (Object[]) obj3);
                        continue;
                    }
                }
                z = obj2.equals(obj3);
                continue;
            } else if (obj2 == obj3) {
                z = true;
                continue;
            } else {
                z = false;
                continue;
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    public final boolean getBoolean(String str, boolean z) {
        j.e(str, "key");
        Object valueOf = Boolean.valueOf(z);
        Object obj = this.values.get(str);
        if (obj instanceof Boolean) {
            valueOf = obj;
        }
        return ((Boolean) valueOf).booleanValue();
    }

    public final Map<String, Object> getKeyValueMap() {
        Map<String, Object> unmodifiableMap = Collections.unmodifiableMap(this.values);
        j.d(unmodifiableMap, "unmodifiableMap(values)");
        return unmodifiableMap;
    }

    public final String getString(String str) {
        j.e(str, "key");
        Object obj = this.values.get(str);
        if (obj instanceof String) {
            return (String) obj;
        }
        return null;
    }

    public final <T> boolean hasKeyWithValueOfType(String str, Class<T> cls) {
        j.e(str, "key");
        j.e(cls, "klass");
        Object obj = this.values.get(str);
        if (obj == null || !cls.isAssignableFrom(obj.getClass())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i2;
        int i7 = 0;
        for (Map.Entry next : this.values.entrySet()) {
            Object value = next.getValue();
            if (value instanceof Object[]) {
                i2 = Objects.hashCode(next.getKey()) ^ Arrays.deepHashCode((Object[]) value);
            } else {
                i2 = next.hashCode();
            }
            i7 += i2;
        }
        return i7 * 31;
    }

    public final int size() {
        return this.values.size();
    }

    public String toString() {
        String str = "Data {" + C1194l.R0(this.values.entrySet(), (String) null, (String) null, (String) null, Data$toString$1$content$1.INSTANCE, 31) + "}";
        j.d(str, "StringBuilder().apply(builderAction).toString()");
        return str;
    }

    public Data(Map<String, ?> map) {
        j.e(map, StateHandler.VALUES);
        this.values = new HashMap(map);
    }
}
