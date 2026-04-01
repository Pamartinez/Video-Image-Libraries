package androidx.appfunctions.internal;

import android.app.PendingIntent;
import androidx.appfunctions.AppFunctionData;
import androidx.appfunctions.metadata.AppFunctionAllOfTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionComponentsMetadata;
import androidx.appfunctions.metadata.AppFunctionDataTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionObjectTypeMetadata;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1196n;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bg\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002:\u0001\u0011J\u0015\u0010\u0003\u001a\u00028\u00002\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0002\u0010\u0006J\u0015\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00028\u0000H&¢\u0006\u0002\u0010\tJ\b\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000fH\u0016ø\u0001\u0000\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0012À\u0006\u0001"}, d2 = {"Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "T", "", "fromAppFunctionData", "appFunctionData", "Landroidx/appfunctions/AppFunctionData;", "(Landroidx/appfunctions/AppFunctionData;)Ljava/lang/Object;", "toAppFunctionData", "appFunctionSerializable", "(Ljava/lang/Object;)Landroidx/appfunctions/AppFunctionData;", "getAppFunctionComponentsMetadata", "Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "getAppFunctionDataBuilder", "Landroidx/appfunctions/AppFunctionData$Builder;", "qualifiedName", "", "getAppFunctionDataWithSpec", "TypeParameter", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface AppFunctionSerializableFactory<T> {

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002:\u0004\u000f\u0010\u0011\u0012J'\u0010\t\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00028\u0001H'¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\r\u001a\u00028\u00012\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0005H'¢\u0006\u0004\b\r\u0010\u000e\u0001\u0004\u0013\u0014\u0015\u0016ø\u0001\u0000\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0017À\u0006\u0001"}, d2 = {"Landroidx/appfunctions/internal/AppFunctionSerializableFactory$TypeParameter;", "T", "", "Landroidx/appfunctions/AppFunctionData$Builder;", "appFunctionDataBuilder", "", "key", "value", "Lme/x;", "setValueInAppFunctionData", "(Landroidx/appfunctions/AppFunctionData$Builder;Ljava/lang/String;Ljava/lang/Object;)V", "Landroidx/appfunctions/AppFunctionData;", "appFunctionData", "getFromAppFunctionData", "(Landroidx/appfunctions/AppFunctionData;Ljava/lang/String;)Ljava/lang/Object;", "PrimitiveTypeParameter", "PrimitiveListTypeParameter", "SerializableTypeParameter", "SerializableListTypeParameter", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory$TypeParameter$PrimitiveListTypeParameter;", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory$TypeParameter$PrimitiveTypeParameter;", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory$TypeParameter$SerializableListTypeParameter;", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory$TypeParameter$SerializableTypeParameter;", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface TypeParameter<T> {

        @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\b\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u000e\b\u0003\u0010\u0003*\b\u0012\u0002\b\u0003\u0018\u00010\u00022\b\u0012\u0004\u0012\u00028\u00030\u0004B\u0015\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ'\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00028\u0003H\u0017¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0013\u001a\u00028\u00032\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u000bH\u0017¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0018\u001a\u00020\u0017HÖ\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u001a\u0010\u001d\u001a\u00020\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001aHÖ\u0003¢\u0006\u0004\b\u001d\u0010\u001eR\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001f\u001a\u0004\b \u0010!¨\u0006\""}, d2 = {"Landroidx/appfunctions/internal/AppFunctionSerializableFactory$TypeParameter$PrimitiveListTypeParameter;", "I", "", "T", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory$TypeParameter;", "Ljava/lang/Class;", "itemClazz", "<init>", "(Ljava/lang/Class;)V", "Landroidx/appfunctions/AppFunctionData$Builder;", "appFunctionDataBuilder", "", "key", "value", "Lme/x;", "setValueInAppFunctionData", "(Landroidx/appfunctions/AppFunctionData$Builder;Ljava/lang/String;Ljava/util/List;)V", "Landroidx/appfunctions/AppFunctionData;", "appFunctionData", "getFromAppFunctionData", "(Landroidx/appfunctions/AppFunctionData;Ljava/lang/String;)Ljava/util/List;", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/Class;", "getItemClazz", "()Ljava/lang/Class;", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class PrimitiveListTypeParameter<I, T extends List<?>> implements TypeParameter<T> {
            private final Class<I> itemClazz;

            public PrimitiveListTypeParameter(Class<I> cls) {
                j.e(cls, "itemClazz");
                this.itemClazz = cls;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if ((obj instanceof PrimitiveListTypeParameter) && j.a(this.itemClazz, ((PrimitiveListTypeParameter) obj).itemClazz)) {
                    return true;
                }
                return false;
            }

            public int hashCode() {
                return this.itemClazz.hashCode();
            }

            public String toString() {
                return "PrimitiveListTypeParameter(itemClazz=" + this.itemClazz + ')';
            }

            public T getFromAppFunctionData(AppFunctionData appFunctionData, String str) {
                j.e(appFunctionData, "appFunctionData");
                j.e(str, "key");
                Class<I> cls = this.itemClazz;
                if (j.a(cls, String.class)) {
                    return appFunctionData.getStringList(str);
                }
                if (j.a(cls, PendingIntent.class)) {
                    return appFunctionData.getPendingIntentList(str);
                }
                throw new IllegalStateException("Unsupported item type for primitive list: " + this.itemClazz + '.');
            }

            public void setValueInAppFunctionData(AppFunctionData.Builder builder, String str, T t) {
                j.e(builder, "appFunctionDataBuilder");
                j.e(str, "key");
                if (t != null) {
                    Class<I> cls = this.itemClazz;
                    if (j.a(cls, String.class)) {
                        builder.setStringList(str, t);
                    } else if (j.a(cls, PendingIntent.class)) {
                        builder.setPendingIntentList(str, t);
                    } else {
                        throw new IllegalStateException("Unsupported item type for primitive list: " + this.itemClazz + '.');
                    }
                }
            }
        }

        @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\b\u0018\u0000*\u0004\b\u0002\u0010\u00012\b\u0012\u0004\u0012\u00028\u00020\u0002B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J'\u0010\r\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00028\u0002H\u0017¢\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0011\u001a\u00028\u00022\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\tH\u0017¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0016\u001a\u00020\u0015HÖ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u001a\u0010\u001b\u001a\u00020\u001a2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018HÖ\u0003¢\u0006\u0004\b\u001b\u0010\u001cR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f¨\u0006 "}, d2 = {"Landroidx/appfunctions/internal/AppFunctionSerializableFactory$TypeParameter$PrimitiveTypeParameter;", "T", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory$TypeParameter;", "Ljava/lang/Class;", "clazz", "<init>", "(Ljava/lang/Class;)V", "Landroidx/appfunctions/AppFunctionData$Builder;", "appFunctionDataBuilder", "", "key", "value", "Lme/x;", "setValueInAppFunctionData", "(Landroidx/appfunctions/AppFunctionData$Builder;Ljava/lang/String;Ljava/lang/Object;)V", "Landroidx/appfunctions/AppFunctionData;", "appFunctionData", "getFromAppFunctionData", "(Landroidx/appfunctions/AppFunctionData;Ljava/lang/String;)Ljava/lang/Object;", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/Class;", "getClazz", "()Ljava/lang/Class;", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class PrimitiveTypeParameter<T> implements TypeParameter<T> {
            private final Class<T> clazz;

            public PrimitiveTypeParameter(Class<T> cls) {
                j.e(cls, "clazz");
                this.clazz = cls;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if ((obj instanceof PrimitiveTypeParameter) && j.a(this.clazz, ((PrimitiveTypeParameter) obj).clazz)) {
                    return true;
                }
                return false;
            }

            public T getFromAppFunctionData(AppFunctionData appFunctionData, String str) {
                j.e(appFunctionData, "appFunctionData");
                j.e(str, "key");
                Class<T> cls = this.clazz;
                if (j.a(cls, Integer.TYPE)) {
                    return Integer.valueOf(appFunctionData.getInt(str));
                }
                if (j.a(cls, Long.TYPE)) {
                    return Long.valueOf(appFunctionData.getLong(str));
                }
                if (j.a(cls, Float.TYPE)) {
                    return Float.valueOf(appFunctionData.getFloat(str));
                }
                if (j.a(cls, Double.TYPE)) {
                    return Double.valueOf(appFunctionData.getDouble(str));
                }
                if (j.a(cls, Boolean.TYPE)) {
                    return Boolean.valueOf(appFunctionData.getBoolean(str));
                }
                if (j.a(cls, String.class)) {
                    return appFunctionData.getString(str);
                }
                if (j.a(cls, PendingIntent.class)) {
                    return appFunctionData.getPendingIntent(str);
                }
                if (j.a(cls, int[].class)) {
                    return appFunctionData.getIntArray(str);
                }
                if (j.a(cls, long[].class)) {
                    return appFunctionData.getLongArray(str);
                }
                if (j.a(cls, float[].class)) {
                    return appFunctionData.getFloatArray(str);
                }
                if (j.a(cls, double[].class)) {
                    return appFunctionData.getDoubleArray(str);
                }
                if (j.a(cls, boolean[].class)) {
                    return appFunctionData.getBooleanArray(str);
                }
                if (j.a(cls, byte[].class)) {
                    return appFunctionData.getByteArray(str);
                }
                throw new IllegalStateException("Unsupported primitive type: " + this.clazz + '.');
            }

            public int hashCode() {
                return this.clazz.hashCode();
            }

            public void setValueInAppFunctionData(AppFunctionData.Builder builder, String str, T t) {
                j.e(builder, "appFunctionDataBuilder");
                j.e(str, "key");
                if (t != null) {
                    Class<T> cls = this.clazz;
                    if (j.a(cls, Integer.TYPE)) {
                        builder.setInt(str, ((Integer) t).intValue());
                    } else if (j.a(cls, Long.TYPE)) {
                        builder.setLong(str, ((Long) t).longValue());
                    } else if (j.a(cls, Float.TYPE)) {
                        builder.setFloat(str, ((Float) t).floatValue());
                    } else if (j.a(cls, Double.TYPE)) {
                        builder.setDouble(str, ((Double) t).doubleValue());
                    } else if (j.a(cls, Boolean.TYPE)) {
                        builder.setBoolean(str, ((Boolean) t).booleanValue());
                    } else if (j.a(cls, String.class)) {
                        builder.setString(str, (String) t);
                    } else if (j.a(cls, PendingIntent.class)) {
                        builder.setPendingIntent(str, (PendingIntent) t);
                    } else if (j.a(cls, int[].class)) {
                        builder.setIntArray(str, (int[]) t);
                    } else if (j.a(cls, long[].class)) {
                        builder.setLongArray(str, (long[]) t);
                    } else if (j.a(cls, float[].class)) {
                        builder.setFloatArray(str, (float[]) t);
                    } else if (j.a(cls, double[].class)) {
                        builder.setDoubleArray(str, (double[]) t);
                    } else if (j.a(cls, boolean[].class)) {
                        builder.setBooleanArray(str, (boolean[]) t);
                    } else if (j.a(cls, byte[].class)) {
                        builder.setByteArray(str, (byte[]) t);
                    } else {
                        throw new IllegalStateException("Unsupported primitive type: " + this.clazz + '.');
                    }
                }
            }

            public String toString() {
                return "PrimitiveTypeParameter(clazz=" + this.clazz + ')';
            }
        }

        @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\b\u0018\u0000*\b\b\u0002\u0010\u0002*\u00020\u0001*\u000e\b\u0003\u0010\u0004*\b\u0012\u0002\b\u0003\u0018\u00010\u00032\b\u0012\u0004\u0012\u00028\u00030\u0005B#\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00020\u0006\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ'\u0010\u0012\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00028\u0003H\u0017¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0016\u001a\u00028\u00032\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u000eH\u0017¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001b\u001a\u00020\u001aHÖ\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u001a\u0010\u001f\u001a\u00020\u001e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001f\u0010 R\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010!\u001a\u0004\b\"\u0010#R\u001d\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010$\u001a\u0004\b%\u0010&¨\u0006'"}, d2 = {"Landroidx/appfunctions/internal/AppFunctionSerializableFactory$TypeParameter$SerializableListTypeParameter;", "", "I", "", "T", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory$TypeParameter;", "Ljava/lang/Class;", "itemClazz", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "serializableFactory", "<init>", "(Ljava/lang/Class;Landroidx/appfunctions/internal/AppFunctionSerializableFactory;)V", "Landroidx/appfunctions/AppFunctionData$Builder;", "appFunctionDataBuilder", "", "key", "value", "Lme/x;", "setValueInAppFunctionData", "(Landroidx/appfunctions/AppFunctionData$Builder;Ljava/lang/String;Ljava/util/List;)V", "Landroidx/appfunctions/AppFunctionData;", "appFunctionData", "getFromAppFunctionData", "(Landroidx/appfunctions/AppFunctionData;Ljava/lang/String;)Ljava/util/List;", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/Class;", "getItemClazz", "()Ljava/lang/Class;", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "getSerializableFactory", "()Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class SerializableListTypeParameter<I, T extends List<?>> implements TypeParameter<T> {
            private final Class<I> itemClazz;
            private final AppFunctionSerializableFactory<I> serializableFactory;

            public SerializableListTypeParameter(Class<I> cls, AppFunctionSerializableFactory<I> appFunctionSerializableFactory) {
                j.e(cls, "itemClazz");
                j.e(appFunctionSerializableFactory, "serializableFactory");
                this.itemClazz = cls;
                this.serializableFactory = appFunctionSerializableFactory;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof SerializableListTypeParameter)) {
                    return false;
                }
                SerializableListTypeParameter serializableListTypeParameter = (SerializableListTypeParameter) obj;
                if (j.a(this.itemClazz, serializableListTypeParameter.itemClazz) && j.a(this.serializableFactory, serializableListTypeParameter.serializableFactory)) {
                    return true;
                }
                return false;
            }

            public int hashCode() {
                return this.serializableFactory.hashCode() + (this.itemClazz.hashCode() * 31);
            }

            public String toString() {
                return "SerializableListTypeParameter(itemClazz=" + this.itemClazz + ", serializableFactory=" + this.serializableFactory + ')';
            }

            public T getFromAppFunctionData(AppFunctionData appFunctionData, String str) {
                j.e(appFunctionData, "appFunctionData");
                j.e(str, "key");
                List<AppFunctionData> appFunctionDataList = appFunctionData.getAppFunctionDataList(str);
                if (appFunctionDataList == null) {
                    return null;
                }
                Iterable<AppFunctionData> iterable = appFunctionDataList;
                T arrayList = new ArrayList(C1196n.w0(iterable, 10));
                for (AppFunctionData fromAppFunctionData : iterable) {
                    arrayList.add(this.serializableFactory.fromAppFunctionData(fromAppFunctionData));
                }
                return arrayList;
            }

            public void setValueInAppFunctionData(AppFunctionData.Builder builder, String str, T t) {
                j.e(builder, "appFunctionDataBuilder");
                j.e(str, "key");
                if (t != null) {
                    Iterable iterable = (Iterable) t;
                    ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
                    for (Object next : iterable) {
                        AppFunctionSerializableFactory<I> appFunctionSerializableFactory = this.serializableFactory;
                        j.c(next, "null cannot be cast to non-null type I of androidx.appfunctions.internal.AppFunctionSerializableFactory.TypeParameter.SerializableListTypeParameter");
                        arrayList.add(appFunctionSerializableFactory.toAppFunctionData(next));
                    }
                    builder.setAppFunctionDataList(str, arrayList);
                }
            }
        }

        @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\b\u0018\u0000*\b\b\u0002\u0010\u0002*\u00020\u0001*\n\b\u0003\u0010\u0003*\u0004\u0018\u00018\u00022\b\u0012\u0004\u0012\u00028\u00030\u0004B#\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00030\u0005\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ'\u0010\u0011\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00028\u0003H\u0017¢\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0015\u001a\u00028\u00032\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\rH\u0017¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u001a\u001a\u00020\u0019HÖ\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u001a\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001e\u0010\u001fR\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00030\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010 \u001a\u0004\b!\u0010\"R\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010#\u001a\u0004\b$\u0010%¨\u0006&"}, d2 = {"Landroidx/appfunctions/internal/AppFunctionSerializableFactory$TypeParameter$SerializableTypeParameter;", "", "I", "T", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory$TypeParameter;", "Ljava/lang/Class;", "clazz", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "serializableFactory", "<init>", "(Ljava/lang/Class;Landroidx/appfunctions/internal/AppFunctionSerializableFactory;)V", "Landroidx/appfunctions/AppFunctionData$Builder;", "appFunctionDataBuilder", "", "key", "value", "Lme/x;", "setValueInAppFunctionData", "(Landroidx/appfunctions/AppFunctionData$Builder;Ljava/lang/String;Ljava/lang/Object;)V", "Landroidx/appfunctions/AppFunctionData;", "appFunctionData", "getFromAppFunctionData", "(Landroidx/appfunctions/AppFunctionData;Ljava/lang/String;)Ljava/lang/Object;", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/Class;", "getClazz", "()Ljava/lang/Class;", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "getSerializableFactory", "()Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class SerializableTypeParameter<I, T extends I> implements TypeParameter<T> {
            private final Class<T> clazz;
            private final AppFunctionSerializableFactory<I> serializableFactory;

            public SerializableTypeParameter(Class<T> cls, AppFunctionSerializableFactory<I> appFunctionSerializableFactory) {
                j.e(cls, "clazz");
                j.e(appFunctionSerializableFactory, "serializableFactory");
                this.clazz = cls;
                this.serializableFactory = appFunctionSerializableFactory;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof SerializableTypeParameter)) {
                    return false;
                }
                SerializableTypeParameter serializableTypeParameter = (SerializableTypeParameter) obj;
                if (j.a(this.clazz, serializableTypeParameter.clazz) && j.a(this.serializableFactory, serializableTypeParameter.serializableFactory)) {
                    return true;
                }
                return false;
            }

            public T getFromAppFunctionData(AppFunctionData appFunctionData, String str) {
                j.e(appFunctionData, "appFunctionData");
                j.e(str, "key");
                AppFunctionData appFunctionData2 = appFunctionData.getAppFunctionData(str);
                if (appFunctionData2 != null) {
                    return this.serializableFactory.fromAppFunctionData(appFunctionData2);
                }
                return null;
            }

            public int hashCode() {
                return this.serializableFactory.hashCode() + (this.clazz.hashCode() * 31);
            }

            public void setValueInAppFunctionData(AppFunctionData.Builder builder, String str, T t) {
                j.e(builder, "appFunctionDataBuilder");
                j.e(str, "key");
                if (t != null) {
                    builder.setAppFunctionData(str, this.serializableFactory.toAppFunctionData(t));
                }
            }

            public String toString() {
                return "SerializableTypeParameter(clazz=" + this.clazz + ", serializableFactory=" + this.serializableFactory + ')';
            }
        }

        T getFromAppFunctionData(AppFunctionData appFunctionData, String str);

        void setValueInAppFunctionData(AppFunctionData.Builder builder, String str, T t);
    }

    private AppFunctionComponentsMetadata getAppFunctionComponentsMetadata() {
        AppFunctionComponentsMetadata componentsMetadata;
        AppFunctionInventory appFunctionInventory$appfunctions = Dependencies.INSTANCE.getAppFunctionInventory$appfunctions();
        if (appFunctionInventory$appfunctions == null || (componentsMetadata = appFunctionInventory$appfunctions.getComponentsMetadata()) == null) {
            return new AppFunctionComponentsMetadata((Map) null, 1, (e) null);
        }
        return componentsMetadata;
    }

    T fromAppFunctionData(AppFunctionData appFunctionData);

    AppFunctionData.Builder getAppFunctionDataBuilder(String str) {
        j.e(str, "qualifiedName");
        AppFunctionComponentsMetadata appFunctionComponentsMetadata = getAppFunctionComponentsMetadata();
        AppFunctionDataTypeMetadata appFunctionDataTypeMetadata = appFunctionComponentsMetadata.getDataTypes().get(str);
        if (appFunctionDataTypeMetadata == null) {
            return new AppFunctionData.Builder(str, (String) null, 2, (e) null);
        }
        if (appFunctionDataTypeMetadata instanceof AppFunctionObjectTypeMetadata) {
            return new AppFunctionData.Builder((AppFunctionObjectTypeMetadata) appFunctionDataTypeMetadata, appFunctionComponentsMetadata);
        }
        if (appFunctionDataTypeMetadata instanceof AppFunctionAllOfTypeMetadata) {
            return new AppFunctionData.Builder((AppFunctionAllOfTypeMetadata) appFunctionDataTypeMetadata, appFunctionComponentsMetadata);
        }
        throw new IllegalStateException("Unable to serialize " + str + " with " + appFunctionDataTypeMetadata);
    }

    AppFunctionData getAppFunctionDataWithSpec(AppFunctionData appFunctionData, String str) {
        AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata;
        j.e(appFunctionData, "appFunctionData");
        j.e(str, "qualifiedName");
        AppFunctionComponentsMetadata appFunctionComponentsMetadata = getAppFunctionComponentsMetadata();
        AppFunctionDataTypeMetadata appFunctionDataTypeMetadata = appFunctionComponentsMetadata.getDataTypes().get(str);
        if (appFunctionDataTypeMetadata instanceof AppFunctionObjectTypeMetadata) {
            appFunctionObjectTypeMetadata = (AppFunctionObjectTypeMetadata) appFunctionDataTypeMetadata;
        } else {
            appFunctionObjectTypeMetadata = null;
        }
        if (appFunctionObjectTypeMetadata == null) {
            return appFunctionData;
        }
        return appFunctionData.replaceSpecWith$appfunctions(appFunctionObjectTypeMetadata, appFunctionComponentsMetadata);
    }

    AppFunctionData toAppFunctionData(T t);
}
