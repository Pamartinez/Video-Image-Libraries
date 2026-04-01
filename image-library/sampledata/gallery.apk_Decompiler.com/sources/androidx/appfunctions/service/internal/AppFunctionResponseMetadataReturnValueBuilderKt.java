package androidx.appfunctions.service.internal;

import android.app.PendingIntent;
import android.util.Log;
import androidx.appfunctions.AppFunctionAppUnknownException;
import androidx.appfunctions.AppFunctionData;
import androidx.appfunctions.metadata.AppFunctionAllOfTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionArrayTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionBooleanTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionBytesTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionComponentsMetadata;
import androidx.appfunctions.metadata.AppFunctionDataTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionDoubleTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionFloatTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionIntTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionLongTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionObjectTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionPendingIntentTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionReferenceTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionResponseMetadata;
import androidx.appfunctions.metadata.AppFunctionStringTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionUnitTypeMetadata;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1196n;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0001\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0003\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0004H\u0003¨\u0006\f"}, d2 = {"unsafeBuildReturnValue", "Landroidx/appfunctions/AppFunctionData;", "Landroidx/appfunctions/metadata/AppFunctionResponseMetadata;", "result", "", "componentsMetadata", "Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;", "responseMetadata", "Landroidx/appfunctions/metadata/AppFunctionArrayTypeMetadata;", "builder", "Landroidx/appfunctions/AppFunctionData$Builder;", "appfunctions-service"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AppFunctionResponseMetadataReturnValueBuilderKt {
    public static final AppFunctionData unsafeBuildReturnValue(AppFunctionResponseMetadata appFunctionResponseMetadata, Object obj, AppFunctionComponentsMetadata appFunctionComponentsMetadata) {
        j.e(appFunctionResponseMetadata, "<this>");
        j.e(appFunctionComponentsMetadata, "componentsMetadata");
        if (obj != null) {
            return unsafeBuildReturnValue(appFunctionResponseMetadata.getValueType(), obj, appFunctionResponseMetadata, appFunctionComponentsMetadata);
        }
        try {
            if (appFunctionResponseMetadata.getValueType().isNullable()) {
                return AppFunctionData.EMPTY;
            }
            throw new IllegalStateException("Unexpected null for non-null return type");
        } catch (Exception e) {
            Log.d("AppFunctions", "Something went wrong when building the return value", e);
            throw new AppFunctionAppUnknownException("Something went wrong when executing an app function");
        }
    }

    private static final AppFunctionData unsafeBuildReturnValue(AppFunctionDataTypeMetadata appFunctionDataTypeMetadata, Object obj, AppFunctionResponseMetadata appFunctionResponseMetadata, AppFunctionComponentsMetadata appFunctionComponentsMetadata) {
        AppFunctionData.Builder builder = new AppFunctionData.Builder(appFunctionResponseMetadata, appFunctionComponentsMetadata);
        if (appFunctionDataTypeMetadata instanceof AppFunctionUnitTypeMetadata) {
            return AppFunctionData.EMPTY;
        }
        if (appFunctionDataTypeMetadata instanceof AppFunctionLongTypeMetadata) {
            j.c(obj, "null cannot be cast to non-null type kotlin.Long");
            return builder.setLong("androidAppfunctionsReturnValue", ((Long) obj).longValue()).build();
        } else if (appFunctionDataTypeMetadata instanceof AppFunctionIntTypeMetadata) {
            j.c(obj, "null cannot be cast to non-null type kotlin.Int");
            return builder.setInt("androidAppfunctionsReturnValue", ((Integer) obj).intValue()).build();
        } else if (appFunctionDataTypeMetadata instanceof AppFunctionDoubleTypeMetadata) {
            j.c(obj, "null cannot be cast to non-null type kotlin.Double");
            return builder.setDouble("androidAppfunctionsReturnValue", ((Double) obj).doubleValue()).build();
        } else if (appFunctionDataTypeMetadata instanceof AppFunctionFloatTypeMetadata) {
            j.c(obj, "null cannot be cast to non-null type kotlin.Float");
            return builder.setFloat("androidAppfunctionsReturnValue", ((Float) obj).floatValue()).build();
        } else if (appFunctionDataTypeMetadata instanceof AppFunctionBooleanTypeMetadata) {
            j.c(obj, "null cannot be cast to non-null type kotlin.Boolean");
            return builder.setBoolean("androidAppfunctionsReturnValue", ((Boolean) obj).booleanValue()).build();
        } else if (appFunctionDataTypeMetadata instanceof AppFunctionStringTypeMetadata) {
            j.c(obj, "null cannot be cast to non-null type kotlin.String");
            return builder.setString("androidAppfunctionsReturnValue", (String) obj).build();
        } else if (appFunctionDataTypeMetadata instanceof AppFunctionBytesTypeMetadata) {
            throw new IllegalStateException("Type of a single byte is not supported");
        } else if (appFunctionDataTypeMetadata instanceof AppFunctionPendingIntentTypeMetadata) {
            j.c(obj, "null cannot be cast to non-null type android.app.PendingIntent");
            return builder.setPendingIntent("androidAppfunctionsReturnValue", (PendingIntent) obj).build();
        } else if (appFunctionDataTypeMetadata instanceof AppFunctionObjectTypeMetadata) {
            AppFunctionData.Companion companion = AppFunctionData.Companion;
            String qualifiedName = ((AppFunctionObjectTypeMetadata) appFunctionDataTypeMetadata).getQualifiedName();
            if (qualifiedName != null) {
                return builder.setAppFunctionData("androidAppfunctionsReturnValue", companion.serialize(obj, qualifiedName)).build();
            }
            throw new IllegalStateException("Required value was null.");
        } else if (appFunctionDataTypeMetadata instanceof AppFunctionAllOfTypeMetadata) {
            AppFunctionData.Companion companion2 = AppFunctionData.Companion;
            String qualifiedName2 = ((AppFunctionAllOfTypeMetadata) appFunctionDataTypeMetadata).getQualifiedName();
            if (qualifiedName2 != null) {
                return builder.setAppFunctionData("androidAppfunctionsReturnValue", companion2.serialize(obj, qualifiedName2)).build();
            }
            throw new IllegalStateException("Required value was null.");
        } else if (appFunctionDataTypeMetadata instanceof AppFunctionReferenceTypeMetadata) {
            AppFunctionData.Companion companion3 = AppFunctionData.Companion;
            String referenceDataType = ((AppFunctionReferenceTypeMetadata) appFunctionDataTypeMetadata).getReferenceDataType();
            if (referenceDataType != null) {
                return builder.setAppFunctionData("androidAppfunctionsReturnValue", companion3.serialize(obj, referenceDataType)).build();
            }
            throw new IllegalStateException("Required value was null.");
        } else if (appFunctionDataTypeMetadata instanceof AppFunctionArrayTypeMetadata) {
            return unsafeBuildReturnValue((AppFunctionArrayTypeMetadata) appFunctionDataTypeMetadata, builder, obj);
        } else {
            throw new IllegalStateException("Unknown DataTypeMetadata: " + appFunctionDataTypeMetadata.getClass());
        }
    }

    private static final AppFunctionData unsafeBuildReturnValue(AppFunctionArrayTypeMetadata appFunctionArrayTypeMetadata, AppFunctionData.Builder builder, Object obj) {
        AppFunctionDataTypeMetadata itemType = appFunctionArrayTypeMetadata.getItemType();
        if (itemType instanceof AppFunctionLongTypeMetadata) {
            j.c(obj, "null cannot be cast to non-null type kotlin.LongArray");
            return builder.setLongArray("androidAppfunctionsReturnValue", (long[]) obj).build();
        } else if (itemType instanceof AppFunctionIntTypeMetadata) {
            j.c(obj, "null cannot be cast to non-null type kotlin.IntArray");
            return builder.setIntArray("androidAppfunctionsReturnValue", (int[]) obj).build();
        } else if (itemType instanceof AppFunctionDoubleTypeMetadata) {
            j.c(obj, "null cannot be cast to non-null type kotlin.DoubleArray");
            return builder.setDoubleArray("androidAppfunctionsReturnValue", (double[]) obj).build();
        } else if (itemType instanceof AppFunctionFloatTypeMetadata) {
            j.c(obj, "null cannot be cast to non-null type kotlin.FloatArray");
            return builder.setFloatArray("androidAppfunctionsReturnValue", (float[]) obj).build();
        } else if (itemType instanceof AppFunctionBooleanTypeMetadata) {
            j.c(obj, "null cannot be cast to non-null type kotlin.BooleanArray");
            return builder.setBooleanArray("androidAppfunctionsReturnValue", (boolean[]) obj).build();
        } else if (itemType instanceof AppFunctionStringTypeMetadata) {
            j.c(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.String>");
            return builder.setStringList("androidAppfunctionsReturnValue", (List) obj).build();
        } else if (itemType instanceof AppFunctionBytesTypeMetadata) {
            j.c(obj, "null cannot be cast to non-null type kotlin.ByteArray");
            return builder.setByteArray("androidAppfunctionsReturnValue", (byte[]) obj).build();
        } else if (itemType instanceof AppFunctionPendingIntentTypeMetadata) {
            j.c(obj, "null cannot be cast to non-null type kotlin.collections.List<android.app.PendingIntent>");
            return builder.setPendingIntentList("androidAppfunctionsReturnValue", (List) obj).build();
        } else if (itemType instanceof AppFunctionObjectTypeMetadata) {
            j.c(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
            Iterable iterable = (List) obj;
            ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
            for (Object next : iterable) {
                AppFunctionData.Companion companion = AppFunctionData.Companion;
                String qualifiedName = ((AppFunctionObjectTypeMetadata) itemType).getQualifiedName();
                if (qualifiedName != null) {
                    arrayList.add(companion.serialize(next, qualifiedName));
                } else {
                    throw new IllegalStateException("Required value was null.");
                }
            }
            return builder.setAppFunctionDataList("androidAppfunctionsReturnValue", arrayList).build();
        } else if (itemType instanceof AppFunctionAllOfTypeMetadata) {
            j.c(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
            Iterable iterable2 = (List) obj;
            ArrayList arrayList2 = new ArrayList(C1196n.w0(iterable2, 10));
            for (Object next2 : iterable2) {
                AppFunctionData.Companion companion2 = AppFunctionData.Companion;
                String qualifiedName2 = ((AppFunctionAllOfTypeMetadata) itemType).getQualifiedName();
                if (qualifiedName2 != null) {
                    arrayList2.add(companion2.serialize(next2, qualifiedName2));
                } else {
                    throw new IllegalStateException("Required value was null.");
                }
            }
            return builder.setAppFunctionDataList("androidAppfunctionsReturnValue", arrayList2).build();
        } else if (itemType instanceof AppFunctionReferenceTypeMetadata) {
            j.c(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
            Iterable iterable3 = (List) obj;
            ArrayList arrayList3 = new ArrayList(C1196n.w0(iterable3, 10));
            for (Object next3 : iterable3) {
                AppFunctionData.Companion companion3 = AppFunctionData.Companion;
                String referenceDataType = ((AppFunctionReferenceTypeMetadata) itemType).getReferenceDataType();
                if (referenceDataType != null) {
                    arrayList3.add(companion3.serialize(next3, referenceDataType));
                } else {
                    throw new IllegalStateException("Required value was null.");
                }
            }
            return builder.setAppFunctionDataList("androidAppfunctionsReturnValue", arrayList3).build();
        } else {
            throw new IllegalStateException("Unknown item DataTypeMetadata: " + itemType.getClass());
        }
    }
}
