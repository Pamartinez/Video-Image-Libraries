package com.samsung.android.sdk.moneta.lifestyle.healthandwellness.service;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.sdk.moneta.common.Logger;
import com.samsung.android.sdk.moneta.common.SafeJson;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import com.samsung.android.sdk.moneta.lifestyle.common.TimeRange;
import com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.TimeOfDay;
import com.samsung.android.sdk.moneta.lifestyle.healthandwellness.entity.Exercise;
import com.samsung.android.sdk.moneta.lifestyle.healthandwellness.entity.wrapper.v1.ExerciseEntity;
import com.samsung.android.sdk.moneta.lifestyle.healthandwellness.entity.wrapper.v1.ExerciseEntityKt;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import gg.a;
import java.time.DayOfWeek;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kg.C;
import kg.C1122c;
import kg.C1141w;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import lg.C1174b;
import me.k;
import me.x;
import ne.C1194l;
import ne.C1196n;
import ne.C1202t;
import ne.C1203u;
import qe.C1227c;
import te.C1295a;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0000\u0018\u0000 #2\u00020\u0001:\u0001#B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J?\u0010\r\u001a*\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\bj\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n`\f2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u001e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0010\u001a\u00020\u000fH@¢\u0006\u0004\b\u0011\u0010\u0012J\u001e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0014\u001a\u00020\u0013H@¢\u0006\u0004\b\u0011\u0010\u0015J\u001e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0017\u001a\u00020\u0016H@¢\u0006\u0004\b\u0011\u0010\u0018J*\u0010\u001a\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u00192\u0006\u0010\u0010\u001a\u00020\u000fH@¢\u0006\u0004\b\u001a\u0010\u0012J$\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001b0\u00192\u0006\u0010\u001c\u001a\u00020\u001bH@¢\u0006\u0004\b\u001d\u0010\u001eJ$\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u001b0\u00192\u0006\u0010\u001c\u001a\u00020\u001bH@¢\u0006\u0004\b\u001f\u0010\u001eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010 \u001a\u0004\b!\u0010\"¨\u0006$"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/healthandwellness/service/HealthServiceImpl;", "Lcom/samsung/android/sdk/moneta/lifestyle/healthandwellness/service/HealthService;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "", "monthFrequentExerciseEntityListMap", "Ljava/util/HashMap;", "Ljava/time/Month;", "", "Lcom/samsung/android/sdk/moneta/lifestyle/healthandwellness/entity/Exercise;", "Lkotlin/collections/HashMap;", "convertExerciseListMap", "(Ljava/lang/String;)Ljava/util/HashMap;", "Lcom/samsung/android/sdk/moneta/lifestyle/common/TimeRange;", "timeRange", "getMostFrequentExercises", "(Lcom/samsung/android/sdk/moneta/lifestyle/common/TimeRange;Lqe/c;)Ljava/lang/Object;", "Ljava/time/DayOfWeek;", "dayOfWeek", "(Ljava/time/DayOfWeek;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/TimeOfDay;", "timeOfDay", "(Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/TimeOfDay;Lqe/c;)Ljava/lang/Object;", "", "getFrequentExercisesByMonth", "", "exerciseGroupType", "rankDayOfWeek", "(ILqe/c;)Ljava/lang/Object;", "rankTimeOfDay", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class HealthServiceImpl implements HealthService {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG = "Lifestyle-HealthServiceImpl";
    private static Uri URI = Uri.parse("content://com.samsung.android.moneta.feature.preference.LifeStyleProvider");
    private final Context context;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u0018\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u000e¢\u0006\u0004\n\u0002\u0010\t¨\u0006\n"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/healthandwellness/service/HealthServiceImpl$Companion;", "", "<init>", "()V", "TAG", "", "URI", "Landroid/net/Uri;", "kotlin.jvm.PlatformType", "Landroid/net/Uri;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public /* synthetic */ class EntriesMappings {
        public static final /* synthetic */ C1295a entries$0 = c.t(DayOfWeek.values());
    }

    public HealthServiceImpl(Context context2) {
        j.e(context2, "context");
        this.context = context2;
    }

    private final HashMap<Month, List<Exercise>> convertExerciseListMap(String str) {
        Object obj;
        HashMap<Month, List<Exercise>> hashMap = new HashMap<>();
        try {
            C1174b json = SafeJson.INSTANCE.getJson();
            json.getClass();
            obj = json.a(new C((a) new C1141w("java.time.Month", Month.values()), (a) new C1122c(ExerciseEntity.Companion.serializer()), 0), str);
        } catch (Throwable th) {
            obj = L2.a.l(th);
        }
        Throwable a7 = k.a(obj);
        if (a7 != null) {
            Logger logger = Logger.INSTANCE;
            logger.e$pde_sdk_1_0_40_release(SafeJson.TAG, "[decodeFromString] failed. " + a7 + '.');
        }
        if (obj instanceof me.j) {
            obj = null;
        }
        HashMap hashMap2 = (HashMap) obj;
        if (hashMap2 != null) {
            ArrayList arrayList = new ArrayList(hashMap2.size());
            for (Map.Entry entry : hashMap2.entrySet()) {
                Object key = entry.getKey();
                Iterable<ExerciseEntity> iterable = (Iterable) entry.getValue();
                ArrayList arrayList2 = new ArrayList(C1196n.w0(iterable, 10));
                for (ExerciseEntity exercise : iterable) {
                    arrayList2.add(ExerciseEntityKt.toExercise(exercise));
                }
                hashMap.put(key, arrayList2);
                arrayList.add(x.f4917a);
            }
        }
        return hashMap;
    }

    public final Context getContext() {
        return this.context;
    }

    public /* synthetic */ Object getFrequentExercisesByMonth(TimeRange timeRange, C1227c cVar) {
        String string;
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[getFrequentExercisesByMonth] in");
        Bundle bundle = new Bundle();
        bundle.putLong("start_timestamp", timeRange.getStartTime());
        bundle.putLong("end_timestamp", timeRange.getEndTime());
        Bundle call = this.context.getContentResolver().call(URI, ContentProviderConstants.HealthAndWellness.Method.GET_FREQUENT_EXERCISES_BY_MONTH, (String) null, bundle);
        if (call == null || (string = call.getString(ContentProviderConstants.HealthAndWellness.ResultKey.FREQUENT_EXERCISES_BY_MONTH)) == null) {
            return C1203u.d;
        }
        return convertExerciseListMap(string);
    }

    public Object getMostFrequentExercises(TimeRange timeRange, C1227c cVar) {
        Object obj;
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[getMostFrequentExercises] in");
        Bundle bundle = new Bundle();
        bundle.putLong("start_timestamp", timeRange.getStartTime());
        bundle.putLong("end_timestamp", timeRange.getEndTime());
        ArrayList arrayList = null;
        Bundle call = this.context.getContentResolver().call(URI, ContentProviderConstants.HealthAndWellness.Method.GET_MOST_FREQUENT_EXERCISES, (String) null, bundle);
        ArrayList<String> stringArrayList = call != null ? call.getStringArrayList(ContentProviderConstants.HealthAndWellness.ResultKey.MOST_FREQUENT_EXERCISES) : null;
        if (stringArrayList != null) {
            ArrayList arrayList2 = new ArrayList();
            for (String str : stringArrayList) {
                SafeJson safeJson = SafeJson.INSTANCE;
                j.b(str);
                try {
                    C1174b json = SafeJson.INSTANCE.getJson();
                    json.getClass();
                    obj = json.a(ExerciseEntity.Companion.serializer(), str);
                } catch (Throwable th) {
                    obj = L2.a.l(th);
                }
                Throwable a7 = k.a(obj);
                if (a7 != null) {
                    Logger.INSTANCE.e$pde_sdk_1_0_40_release(SafeJson.TAG, "[decodeFromString] failed. " + a7 + '.');
                }
                if (obj instanceof me.j) {
                    obj = null;
                }
                ExerciseEntity exerciseEntity = (ExerciseEntity) obj;
                Exercise exercise = exerciseEntity != null ? ExerciseEntityKt.toExercise(exerciseEntity) : null;
                if (exercise != null) {
                    arrayList2.add(exercise);
                }
            }
            arrayList = arrayList2;
        }
        return arrayList != null ? C1194l.k1(arrayList) : C1202t.d;
    }

    public Object rankDayOfWeek(int i2, C1227c cVar) {
        int i7;
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[rankDayOfWeek] in");
        Bundle bundle = new Bundle();
        bundle.putInt(ContentProviderConstants.HealthAndWellness.ParameterKey.EXERCISE_GROUP_TYPE, i2);
        Bundle call = this.context.getContentResolver().call(URI, ContentProviderConstants.HealthAndWellness.Method.RANK_DAY_OF_WEEK, (String) null, bundle);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (DayOfWeek dayOfWeek : EntriesMappings.entries$0) {
            String obj = dayOfWeek.toString();
            if (call != null) {
                i7 = call.getInt(obj);
            } else {
                i7 = 0;
            }
            linkedHashMap.put(dayOfWeek, new Integer(i7));
        }
        return linkedHashMap;
    }

    public Object rankTimeOfDay(int i2, C1227c cVar) {
        int i7;
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[rankTimeOfDay] in");
        Bundle bundle = new Bundle();
        bundle.putInt(ContentProviderConstants.HealthAndWellness.ParameterKey.EXERCISE_GROUP_TYPE, i2);
        Bundle call = this.context.getContentResolver().call(URI, ContentProviderConstants.HealthAndWellness.Method.RANK_TIME_OF_DAY, (String) null, bundle);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str : ContentProviderConstants.HealthAndWellness.ResultKey.INSTANCE.getTIME_OF_DAY()) {
            if (call != null) {
                i7 = call.getInt(str);
            } else {
                i7 = 0;
            }
            linkedHashMap.put(TimeOfDay.valueOf(str), new Integer(i7));
        }
        return linkedHashMap;
    }

    public Object getMostFrequentExercises(DayOfWeek dayOfWeek, C1227c cVar) {
        Object obj;
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[getMostFrequentExercises] in");
        Bundle bundle = new Bundle();
        bundle.putString(ContentProviderConstants.HealthAndWellness.ParameterKey.DAY_OF_WEEK, dayOfWeek.name());
        ArrayList arrayList = null;
        Bundle call = this.context.getContentResolver().call(URI, ContentProviderConstants.HealthAndWellness.Method.GET_MOST_FREQUENT_EXERCISES_BY_DAY_OF_WEEK, (String) null, bundle);
        ArrayList<String> stringArrayList = call != null ? call.getStringArrayList(ContentProviderConstants.HealthAndWellness.ResultKey.MOST_FREQUENT_EXERCISES_BY_DAY_OF_WEEK) : null;
        if (stringArrayList != null) {
            ArrayList arrayList2 = new ArrayList();
            for (String str : stringArrayList) {
                SafeJson safeJson = SafeJson.INSTANCE;
                j.b(str);
                try {
                    C1174b json = SafeJson.INSTANCE.getJson();
                    json.getClass();
                    obj = json.a(ExerciseEntity.Companion.serializer(), str);
                } catch (Throwable th) {
                    obj = L2.a.l(th);
                }
                Throwable a7 = k.a(obj);
                if (a7 != null) {
                    Logger.INSTANCE.e$pde_sdk_1_0_40_release(SafeJson.TAG, "[decodeFromString] failed. " + a7 + '.');
                }
                if (obj instanceof me.j) {
                    obj = null;
                }
                ExerciseEntity exerciseEntity = (ExerciseEntity) obj;
                Exercise exercise = exerciseEntity != null ? ExerciseEntityKt.toExercise(exerciseEntity) : null;
                if (exercise != null) {
                    arrayList2.add(exercise);
                }
            }
            arrayList = arrayList2;
        }
        return arrayList != null ? C1194l.k1(arrayList) : C1202t.d;
    }

    public Object getMostFrequentExercises(TimeOfDay timeOfDay, C1227c cVar) {
        Object obj;
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[getMostFrequentExercises] in");
        Bundle bundle = new Bundle();
        bundle.putString(ContentProviderConstants.HealthAndWellness.ParameterKey.TIME_OF_DAY, timeOfDay.name());
        ArrayList arrayList = null;
        Bundle call = this.context.getContentResolver().call(URI, ContentProviderConstants.HealthAndWellness.Method.GET_MOST_FREQUENT_EXERCISES_BY_TIME_OF_DAY, (String) null, bundle);
        ArrayList<String> stringArrayList = call != null ? call.getStringArrayList(ContentProviderConstants.HealthAndWellness.ResultKey.MOST_FREQUENT_EXERCISES_BY_TIME_OF_DAY) : null;
        if (stringArrayList != null) {
            ArrayList arrayList2 = new ArrayList();
            for (String str : stringArrayList) {
                SafeJson safeJson = SafeJson.INSTANCE;
                j.b(str);
                try {
                    C1174b json = SafeJson.INSTANCE.getJson();
                    json.getClass();
                    obj = json.a(ExerciseEntity.Companion.serializer(), str);
                } catch (Throwable th) {
                    obj = L2.a.l(th);
                }
                Throwable a7 = k.a(obj);
                if (a7 != null) {
                    Logger.INSTANCE.e$pde_sdk_1_0_40_release(SafeJson.TAG, "[decodeFromString] failed. " + a7 + '.');
                }
                if (obj instanceof me.j) {
                    obj = null;
                }
                ExerciseEntity exerciseEntity = (ExerciseEntity) obj;
                Exercise exercise = exerciseEntity != null ? ExerciseEntityKt.toExercise(exerciseEntity) : null;
                if (exercise != null) {
                    arrayList2.add(exercise);
                }
            }
            arrayList = arrayList2;
        }
        return arrayList != null ? C1194l.k1(arrayList) : C1202t.d;
    }
}
