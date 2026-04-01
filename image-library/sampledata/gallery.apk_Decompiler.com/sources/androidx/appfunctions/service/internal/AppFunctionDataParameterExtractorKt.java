package androidx.appfunctions.service.internal;

import android.app.PendingIntent;
import androidx.appfunctions.AppFunctionData;
import androidx.appfunctions.metadata.AppFunctionArrayTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionBooleanTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionBytesTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionDataTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionDoubleTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionFloatTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionIntTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionLongTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionObjectTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionPendingIntentTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionReferenceTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionStringTypeMetadata;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import ne.C1196n;
import ne.C1202t;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0001\u001a.\u0010\u0005\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0003¨\u0006\r"}, d2 = {"unsafeGetParameterValue", "", "Landroidx/appfunctions/AppFunctionData;", "parameterMetadata", "Landroidx/appfunctions/metadata/AppFunctionParameterMetadata;", "getArrayTypeParameterValue", "key", "", "arrayDataTypeMetadata", "Landroidx/appfunctions/metadata/AppFunctionArrayTypeMetadata;", "isNullable", "", "isRequired", "appfunctions-service"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AppFunctionDataParameterExtractorKt {
    private static final Object getArrayTypeParameterValue(AppFunctionData appFunctionData, String str, AppFunctionArrayTypeMetadata appFunctionArrayTypeMetadata, boolean z, boolean z3) {
        AppFunctionDataTypeMetadata itemType = appFunctionArrayTypeMetadata.getItemType();
        if (itemType instanceof AppFunctionIntTypeMetadata) {
            if (z3 || z) {
                return appFunctionData.getIntArray(str);
            }
            int[] intArray = appFunctionData.getIntArray(str);
            if (intArray == null) {
                return new int[0];
            }
            return intArray;
        } else if (itemType instanceof AppFunctionLongTypeMetadata) {
            if (z3 || z) {
                return appFunctionData.getLongArray(str);
            }
            long[] longArray = appFunctionData.getLongArray(str);
            if (longArray == null) {
                return new long[0];
            }
            return longArray;
        } else if (itemType instanceof AppFunctionFloatTypeMetadata) {
            if (z3 || z) {
                return appFunctionData.getFloatArray(str);
            }
            float[] floatArray = appFunctionData.getFloatArray(str);
            if (floatArray == null) {
                return new float[0];
            }
            return floatArray;
        } else if (itemType instanceof AppFunctionDoubleTypeMetadata) {
            if (z3 || z) {
                return appFunctionData.getDoubleArray(str);
            }
            double[] doubleArray = appFunctionData.getDoubleArray(str);
            if (doubleArray == null) {
                return new double[0];
            }
            return doubleArray;
        } else if (itemType instanceof AppFunctionBooleanTypeMetadata) {
            if (z3 || z) {
                return appFunctionData.getBooleanArray(str);
            }
            boolean[] booleanArray = appFunctionData.getBooleanArray(str);
            if (booleanArray == null) {
                return new boolean[0];
            }
            return booleanArray;
        } else if (!(itemType instanceof AppFunctionBytesTypeMetadata)) {
            boolean z7 = itemType instanceof AppFunctionStringTypeMetadata;
            C1202t tVar = C1202t.d;
            if (z7) {
                if (z3 || z) {
                    return appFunctionData.getStringList(str);
                }
                List<String> stringList = appFunctionData.getStringList(str);
                if (stringList == null) {
                    return tVar;
                }
                return stringList;
            } else if (itemType instanceof AppFunctionPendingIntentTypeMetadata) {
                if (z3 || z) {
                    return appFunctionData.getPendingIntentList(str);
                }
                List<PendingIntent> pendingIntentList = appFunctionData.getPendingIntentList(str);
                if (pendingIntentList == null) {
                    return tVar;
                }
                return pendingIntentList;
            } else if (itemType instanceof AppFunctionObjectTypeMetadata) {
                if (z3 || z) {
                    List<AppFunctionData> appFunctionDataList = appFunctionData.getAppFunctionDataList(str);
                    if (appFunctionDataList == null) {
                        return null;
                    }
                    Iterable<AppFunctionData> iterable = appFunctionDataList;
                    ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
                    for (AppFunctionData appFunctionData2 : iterable) {
                        String qualifiedName = ((AppFunctionObjectTypeMetadata) itemType).getQualifiedName();
                        if (qualifiedName != null) {
                            arrayList.add(appFunctionData2.deserialize(qualifiedName));
                        } else {
                            throw new IllegalStateException("Required value was null.");
                        }
                    }
                    return arrayList;
                }
                List<AppFunctionData> appFunctionDataList2 = appFunctionData.getAppFunctionDataList(str);
                if (appFunctionDataList2 == null) {
                    return tVar;
                }
                Iterable<AppFunctionData> iterable2 = appFunctionDataList2;
                ArrayList arrayList2 = new ArrayList(C1196n.w0(iterable2, 10));
                for (AppFunctionData appFunctionData3 : iterable2) {
                    String qualifiedName2 = ((AppFunctionObjectTypeMetadata) itemType).getQualifiedName();
                    if (qualifiedName2 != null) {
                        arrayList2.add(appFunctionData3.deserialize(qualifiedName2));
                    } else {
                        throw new IllegalStateException("Required value was null.");
                    }
                }
                return arrayList2;
            } else if (!(itemType instanceof AppFunctionReferenceTypeMetadata)) {
                throw new IllegalStateException("Unknown item DataTypeMetadata: " + itemType.getClass());
            } else if (z3 || z) {
                List<AppFunctionData> appFunctionDataList3 = appFunctionData.getAppFunctionDataList(str);
                if (appFunctionDataList3 == null) {
                    return null;
                }
                Iterable<AppFunctionData> iterable3 = appFunctionDataList3;
                ArrayList arrayList3 = new ArrayList(C1196n.w0(iterable3, 10));
                for (AppFunctionData appFunctionData4 : iterable3) {
                    String referenceDataType = ((AppFunctionReferenceTypeMetadata) itemType).getReferenceDataType();
                    if (referenceDataType != null) {
                        arrayList3.add(appFunctionData4.deserialize(referenceDataType));
                    } else {
                        throw new IllegalStateException("Required value was null.");
                    }
                }
                return arrayList3;
            } else {
                List<AppFunctionData> appFunctionDataList4 = appFunctionData.getAppFunctionDataList(str);
                if (appFunctionDataList4 == null) {
                    return tVar;
                }
                Iterable<AppFunctionData> iterable4 = appFunctionDataList4;
                ArrayList arrayList4 = new ArrayList(C1196n.w0(iterable4, 10));
                for (AppFunctionData appFunctionData5 : iterable4) {
                    String referenceDataType2 = ((AppFunctionReferenceTypeMetadata) itemType).getReferenceDataType();
                    if (referenceDataType2 != null) {
                        arrayList4.add(appFunctionData5.deserialize(referenceDataType2));
                    } else {
                        throw new IllegalStateException("Required value was null.");
                    }
                }
                return arrayList4;
            }
        } else {
            throw new IllegalStateException("List<ByteArray> is not supported");
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object unsafeGetParameterValue(androidx.appfunctions.AppFunctionData r10, androidx.appfunctions.metadata.AppFunctionParameterMetadata r11) {
        /*
            java.lang.String r0 = " is required"
            java.lang.String r1 = "AppFunctions"
            java.lang.String r2 = "Unknown DataTypeMetadata: "
            java.lang.String r3 = "Parameter "
            java.lang.String r4 = "<this>"
            kotlin.jvm.internal.j.e(r10, r4)
            java.lang.String r4 = "parameterMetadata"
            kotlin.jvm.internal.j.e(r11, r4)
            java.lang.String r4 = r11.getName()     // Catch:{ IllegalArgumentException -> 0x003a }
            boolean r5 = r11.isRequired()     // Catch:{ IllegalArgumentException -> 0x003a }
            androidx.appfunctions.metadata.AppFunctionDataTypeMetadata r6 = r11.getDataType()     // Catch:{ IllegalArgumentException -> 0x003a }
            boolean r6 = r6.isNullable()     // Catch:{ IllegalArgumentException -> 0x003a }
            androidx.appfunctions.metadata.AppFunctionDataTypeMetadata r7 = r11.getDataType()     // Catch:{ IllegalArgumentException -> 0x003a }
            boolean r8 = r7 instanceof androidx.appfunctions.metadata.AppFunctionIntTypeMetadata     // Catch:{ IllegalArgumentException -> 0x003a }
            r9 = 0
            if (r8 == 0) goto L_0x0049
            if (r5 != 0) goto L_0x0043
            if (r6 != 0) goto L_0x0043
            java.lang.Integer r10 = r10.getIntOrNull(r4)     // Catch:{ IllegalArgumentException -> 0x003a }
            if (r10 == 0) goto L_0x003d
            int r9 = r10.intValue()     // Catch:{ IllegalArgumentException -> 0x003a }
            goto L_0x003d
        L_0x003a:
            r10 = move-exception
            goto L_0x01a7
        L_0x003d:
            java.lang.Integer r10 = java.lang.Integer.valueOf(r9)     // Catch:{ IllegalArgumentException -> 0x003a }
            goto L_0x014a
        L_0x0043:
            java.lang.Integer r10 = r10.getIntOrNull(r4)     // Catch:{ IllegalArgumentException -> 0x003a }
            goto L_0x014a
        L_0x0049:
            boolean r8 = r7 instanceof androidx.appfunctions.metadata.AppFunctionLongTypeMetadata     // Catch:{ IllegalArgumentException -> 0x003a }
            if (r8 == 0) goto L_0x006a
            if (r5 != 0) goto L_0x0064
            if (r6 != 0) goto L_0x0064
            java.lang.Long r10 = r10.getLongOrNull(r4)     // Catch:{ IllegalArgumentException -> 0x003a }
            if (r10 == 0) goto L_0x005c
            long r4 = r10.longValue()     // Catch:{ IllegalArgumentException -> 0x003a }
            goto L_0x005e
        L_0x005c:
            r4 = 0
        L_0x005e:
            java.lang.Long r10 = java.lang.Long.valueOf(r4)     // Catch:{ IllegalArgumentException -> 0x003a }
            goto L_0x014a
        L_0x0064:
            java.lang.Long r10 = r10.getLongOrNull(r4)     // Catch:{ IllegalArgumentException -> 0x003a }
            goto L_0x014a
        L_0x006a:
            boolean r8 = r7 instanceof androidx.appfunctions.metadata.AppFunctionFloatTypeMetadata     // Catch:{ IllegalArgumentException -> 0x003a }
            if (r8 == 0) goto L_0x008a
            if (r5 != 0) goto L_0x0084
            if (r6 != 0) goto L_0x0084
            java.lang.Float r10 = r10.getFloatOrNull(r4)     // Catch:{ IllegalArgumentException -> 0x003a }
            if (r10 == 0) goto L_0x007d
            float r10 = r10.floatValue()     // Catch:{ IllegalArgumentException -> 0x003a }
            goto L_0x007e
        L_0x007d:
            r10 = 0
        L_0x007e:
            java.lang.Float r10 = java.lang.Float.valueOf(r10)     // Catch:{ IllegalArgumentException -> 0x003a }
            goto L_0x014a
        L_0x0084:
            java.lang.Float r10 = r10.getFloatOrNull(r4)     // Catch:{ IllegalArgumentException -> 0x003a }
            goto L_0x014a
        L_0x008a:
            boolean r8 = r7 instanceof androidx.appfunctions.metadata.AppFunctionDoubleTypeMetadata     // Catch:{ IllegalArgumentException -> 0x003a }
            if (r8 == 0) goto L_0x00ab
            if (r5 != 0) goto L_0x00a5
            if (r6 != 0) goto L_0x00a5
            java.lang.Double r10 = r10.getDoubleOrNull(r4)     // Catch:{ IllegalArgumentException -> 0x003a }
            if (r10 == 0) goto L_0x009d
            double r4 = r10.doubleValue()     // Catch:{ IllegalArgumentException -> 0x003a }
            goto L_0x009f
        L_0x009d:
            r4 = 0
        L_0x009f:
            java.lang.Double r10 = java.lang.Double.valueOf(r4)     // Catch:{ IllegalArgumentException -> 0x003a }
            goto L_0x014a
        L_0x00a5:
            java.lang.Double r10 = r10.getDoubleOrNull(r4)     // Catch:{ IllegalArgumentException -> 0x003a }
            goto L_0x014a
        L_0x00ab:
            boolean r8 = r7 instanceof androidx.appfunctions.metadata.AppFunctionBooleanTypeMetadata     // Catch:{ IllegalArgumentException -> 0x003a }
            if (r8 == 0) goto L_0x00c9
            if (r5 != 0) goto L_0x00c3
            if (r6 != 0) goto L_0x00c3
            java.lang.Boolean r10 = r10.getBooleanOrNull(r4)     // Catch:{ IllegalArgumentException -> 0x003a }
            if (r10 == 0) goto L_0x00bd
            boolean r9 = r10.booleanValue()     // Catch:{ IllegalArgumentException -> 0x003a }
        L_0x00bd:
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r9)     // Catch:{ IllegalArgumentException -> 0x003a }
            goto L_0x014a
        L_0x00c3:
            java.lang.Boolean r10 = r10.getBooleanOrNull(r4)     // Catch:{ IllegalArgumentException -> 0x003a }
            goto L_0x014a
        L_0x00c9:
            boolean r8 = r7 instanceof androidx.appfunctions.metadata.AppFunctionBytesTypeMetadata     // Catch:{ IllegalArgumentException -> 0x003a }
            if (r8 == 0) goto L_0x00e1
            if (r5 != 0) goto L_0x00db
            if (r6 != 0) goto L_0x00db
            byte[] r10 = r10.getByteArray(r4)     // Catch:{ IllegalArgumentException -> 0x003a }
            if (r10 != 0) goto L_0x014a
            byte[] r10 = new byte[r9]     // Catch:{ IllegalArgumentException -> 0x003a }
            goto L_0x014a
        L_0x00db:
            byte[] r10 = r10.getByteArray(r4)     // Catch:{ IllegalArgumentException -> 0x003a }
            goto L_0x014a
        L_0x00e1:
            boolean r5 = r7 instanceof androidx.appfunctions.metadata.AppFunctionStringTypeMetadata     // Catch:{ IllegalArgumentException -> 0x003a }
            if (r5 == 0) goto L_0x00ea
            java.lang.String r10 = r10.getString(r4)     // Catch:{ IllegalArgumentException -> 0x003a }
            goto L_0x014a
        L_0x00ea:
            boolean r5 = r7 instanceof androidx.appfunctions.metadata.AppFunctionPendingIntentTypeMetadata     // Catch:{ IllegalArgumentException -> 0x003a }
            if (r5 == 0) goto L_0x00f3
            android.app.PendingIntent r10 = r10.getPendingIntent(r4)     // Catch:{ IllegalArgumentException -> 0x003a }
            goto L_0x014a
        L_0x00f3:
            boolean r5 = r7 instanceof androidx.appfunctions.metadata.AppFunctionObjectTypeMetadata     // Catch:{ IllegalArgumentException -> 0x003a }
            r6 = 0
            java.lang.String r8 = "Required value was null."
            if (r5 == 0) goto L_0x0115
            androidx.appfunctions.AppFunctionData r10 = r10.getAppFunctionData(r4)     // Catch:{ IllegalArgumentException -> 0x003a }
            if (r10 == 0) goto L_0x0113
            androidx.appfunctions.metadata.AppFunctionObjectTypeMetadata r7 = (androidx.appfunctions.metadata.AppFunctionObjectTypeMetadata) r7     // Catch:{ IllegalArgumentException -> 0x003a }
            java.lang.String r2 = r7.getQualifiedName()     // Catch:{ IllegalArgumentException -> 0x003a }
            if (r2 == 0) goto L_0x010d
            java.lang.Object r10 = r10.deserialize((java.lang.String) r2)     // Catch:{ IllegalArgumentException -> 0x003a }
            goto L_0x014a
        L_0x010d:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch:{ IllegalArgumentException -> 0x003a }
            r10.<init>(r8)     // Catch:{ IllegalArgumentException -> 0x003a }
            throw r10     // Catch:{ IllegalArgumentException -> 0x003a }
        L_0x0113:
            r10 = r6
            goto L_0x014a
        L_0x0115:
            boolean r5 = r7 instanceof androidx.appfunctions.metadata.AppFunctionArrayTypeMetadata     // Catch:{ IllegalArgumentException -> 0x003a }
            if (r5 == 0) goto L_0x012d
            java.lang.String r2 = r11.getName()     // Catch:{ IllegalArgumentException -> 0x003a }
            r4 = r7
            androidx.appfunctions.metadata.AppFunctionArrayTypeMetadata r4 = (androidx.appfunctions.metadata.AppFunctionArrayTypeMetadata) r4     // Catch:{ IllegalArgumentException -> 0x003a }
            boolean r5 = r7.isNullable()     // Catch:{ IllegalArgumentException -> 0x003a }
            boolean r6 = r11.isRequired()     // Catch:{ IllegalArgumentException -> 0x003a }
            java.lang.Object r10 = getArrayTypeParameterValue(r10, r2, r4, r5, r6)     // Catch:{ IllegalArgumentException -> 0x003a }
            goto L_0x014a
        L_0x012d:
            boolean r5 = r7 instanceof androidx.appfunctions.metadata.AppFunctionReferenceTypeMetadata     // Catch:{ IllegalArgumentException -> 0x003a }
            if (r5 == 0) goto L_0x0191
            androidx.appfunctions.AppFunctionData r10 = r10.getAppFunctionData(r4)     // Catch:{ IllegalArgumentException -> 0x003a }
            if (r10 == 0) goto L_0x0113
            androidx.appfunctions.metadata.AppFunctionReferenceTypeMetadata r7 = (androidx.appfunctions.metadata.AppFunctionReferenceTypeMetadata) r7     // Catch:{ IllegalArgumentException -> 0x003a }
            java.lang.String r2 = r7.getReferenceDataType()     // Catch:{ IllegalArgumentException -> 0x003a }
            if (r2 == 0) goto L_0x0144
            java.lang.Object r10 = r10.deserialize((java.lang.String) r2)     // Catch:{ IllegalArgumentException -> 0x003a }
            goto L_0x014a
        L_0x0144:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch:{ IllegalArgumentException -> 0x003a }
            r10.<init>(r8)     // Catch:{ IllegalArgumentException -> 0x003a }
            throw r10     // Catch:{ IllegalArgumentException -> 0x003a }
        L_0x014a:
            if (r10 != 0) goto L_0x0190
            boolean r2 = r11.isRequired()     // Catch:{ IllegalArgumentException -> 0x003a }
            if (r2 == 0) goto L_0x0190
            androidx.appfunctions.metadata.AppFunctionDataTypeMetadata r2 = r11.getDataType()     // Catch:{ IllegalArgumentException -> 0x003a }
            boolean r2 = r2.isNullable()     // Catch:{ IllegalArgumentException -> 0x003a }
            if (r2 == 0) goto L_0x015d
            goto L_0x0190
        L_0x015d:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x003a }
            r10.<init>(r3)     // Catch:{ IllegalArgumentException -> 0x003a }
            java.lang.String r2 = r11.getName()     // Catch:{ IllegalArgumentException -> 0x003a }
            r10.append(r2)     // Catch:{ IllegalArgumentException -> 0x003a }
            r10.append(r0)     // Catch:{ IllegalArgumentException -> 0x003a }
            java.lang.String r10 = r10.toString()     // Catch:{ IllegalArgumentException -> 0x003a }
            android.util.Log.d(r1, r10)     // Catch:{ IllegalArgumentException -> 0x003a }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x003a }
            r10.<init>(r3)     // Catch:{ IllegalArgumentException -> 0x003a }
            java.lang.String r2 = r11.getName()     // Catch:{ IllegalArgumentException -> 0x003a }
            r10.append(r2)     // Catch:{ IllegalArgumentException -> 0x003a }
            r10.append(r0)     // Catch:{ IllegalArgumentException -> 0x003a }
            java.lang.String r10 = r10.toString()     // Catch:{ IllegalArgumentException -> 0x003a }
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x003a }
            java.lang.String r10 = r10.toString()     // Catch:{ IllegalArgumentException -> 0x003a }
            r0.<init>(r10)     // Catch:{ IllegalArgumentException -> 0x003a }
            throw r0     // Catch:{ IllegalArgumentException -> 0x003a }
        L_0x0190:
            return r10
        L_0x0191:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch:{ IllegalArgumentException -> 0x003a }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x003a }
            r0.<init>(r2)     // Catch:{ IllegalArgumentException -> 0x003a }
            java.lang.Class r2 = r7.getClass()     // Catch:{ IllegalArgumentException -> 0x003a }
            r0.append(r2)     // Catch:{ IllegalArgumentException -> 0x003a }
            java.lang.String r0 = r0.toString()     // Catch:{ IllegalArgumentException -> 0x003a }
            r10.<init>(r0)     // Catch:{ IllegalArgumentException -> 0x003a }
            throw r10     // Catch:{ IllegalArgumentException -> 0x003a }
        L_0x01a7:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r3)
            java.lang.String r2 = r11.getName()
            r0.append(r2)
            java.lang.String r2 = " should be the type of "
            r0.append(r2)
            androidx.appfunctions.metadata.AppFunctionDataTypeMetadata r4 = r11.getDataType()
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r1, r0, r10)
            androidx.appfunctions.AppFunctionInvalidArgumentException r10 = new androidx.appfunctions.AppFunctionInvalidArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r3)
            java.lang.String r1 = r11.getName()
            r0.append(r1)
            r0.append(r2)
            androidx.appfunctions.metadata.AppFunctionDataTypeMetadata r11 = r11.getDataType()
            r0.append(r11)
            java.lang.String r11 = r0.toString()
            r10.<init>(r11)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appfunctions.service.internal.AppFunctionDataParameterExtractorKt.unsafeGetParameterValue(androidx.appfunctions.AppFunctionData, androidx.appfunctions.metadata.AppFunctionParameterMetadata):java.lang.Object");
    }
}
