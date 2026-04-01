package com.samsung.android.sdk.moneta.lifestyle.entertainment.service;

import L2.a;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.sdk.moneta.common.Logger;
import com.samsung.android.sdk.moneta.common.SafeJson;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import com.samsung.android.sdk.moneta.lifestyle.common.TimeRange;
import com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.MediaArtist;
import com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.MediaType;
import com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.Music;
import com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.TimeOfDay;
import com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.Video;
import com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.wrapper.v1.MediaEntity;
import com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.wrapper.v1.MediaEntityKt;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import lg.C1174b;
import me.k;
import ne.C1194l;
import ne.C1202t;
import qe.C1227c;
import te.C1295a;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0000\u0018\u0000 '2\u00020\u0001:\u0001'B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J#\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000e0\rH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J#\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\n0\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0012\u0010\fJ\u001b\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000e0\rH\u0002¢\u0006\u0004\b\u0013\u0010\u0010J\u001e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0015\u001a\u00020\u0014H@¢\u0006\u0004\b\u0018\u0010\u0019J\u001e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00162\u0006\u0010\u0015\u001a\u00020\u0014H@¢\u0006\u0004\b\u001b\u0010\u0019J&\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00162\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0014H@¢\u0006\u0004\b\u001d\u0010\u001eJ$\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b2\u0006\u0010\u0007\u001a\u00020\u0006H@¢\u0006\u0004\b\u001f\u0010 J$\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000e0\b2\u0006\u0010\u0007\u001a\u00020\u0006H@¢\u0006\u0004\b!\u0010 J$\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\n0\b2\u0006\u0010\u0007\u001a\u00020\u0006H@¢\u0006\u0004\b\"\u0010 J$\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000e0\b2\u0006\u0010\u0007\u001a\u00020\u0006H@¢\u0006\u0004\b#\u0010 R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010$\u001a\u0004\b%\u0010&¨\u0006("}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/service/EntertainmentServiceImpl;", "Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/service/EntertainmentService;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/MediaType;", "mediaType", "", "Ljava/time/DayOfWeek;", "", "getDayOfWeek", "(Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/MediaType;)Ljava/util/Map;", "", "", "getEmptyDayOfWeekAverageMap", "()Ljava/util/Map;", "Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/TimeOfDay;", "getTimeOfDay", "getEmptyTimeOfDayAverageMap", "Lcom/samsung/android/sdk/moneta/lifestyle/common/TimeRange;", "timeRange", "", "Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/Music;", "getTopMusicList", "(Lcom/samsung/android/sdk/moneta/lifestyle/common/TimeRange;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/Video;", "getTopVideoList", "Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/MediaArtist;", "getTopArtists", "(Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/MediaType;Lcom/samsung/android/sdk/moneta/lifestyle/common/TimeRange;Lqe/c;)Ljava/lang/Object;", "rankDayOfWeek", "(Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/MediaType;Lqe/c;)Ljava/lang/Object;", "rankDayOfWeekWithAverage", "rankTimeOfDay", "rankTimeOfDayWithAverage", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EntertainmentServiceImpl implements EntertainmentService {
    public static final Companion Companion = new Companion((e) null);
    private static int DAY_OF_WEEK_COUNT_IN_28_DAYS = 4;
    private static final String TAG = "Lifestyle-EntertainmentServiceImpl";
    private static int TIME_OF_DAY_COUNT_IN_28_DAYS = 28;
    private static Uri URI = Uri.parse("content://com.samsung.android.moneta.feature.preference.LifeStyleProvider");
    private final Context context;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u0018\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u000e¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/service/EntertainmentServiceImpl$Companion;", "", "<init>", "()V", "TAG", "", "URI", "Landroid/net/Uri;", "kotlin.jvm.PlatformType", "Landroid/net/Uri;", "DAY_OF_WEEK_COUNT_IN_28_DAYS", "", "TIME_OF_DAY_COUNT_IN_28_DAYS", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
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

    public EntertainmentServiceImpl(Context context2) {
        j.e(context2, "context");
        this.context = context2;
    }

    private final Map<DayOfWeek, Integer> getDayOfWeek(MediaType mediaType) {
        int i2;
        Bundle bundle = new Bundle();
        bundle.putInt(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE, mediaType.getValue());
        Bundle call = this.context.getContentResolver().call(URI, ContentProviderConstants.Entertainment.Method.RANK_DAY_OF_WEEK, (String) null, bundle);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (DayOfWeek dayOfWeek : EntriesMappings.entries$0) {
            String obj = dayOfWeek.toString();
            if (call != null) {
                i2 = call.getInt(obj);
            } else {
                i2 = 0;
            }
            linkedHashMap.put(dayOfWeek, Integer.valueOf(i2));
        }
        return linkedHashMap;
    }

    private final Map<DayOfWeek, Double> getEmptyDayOfWeekAverageMap() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (DayOfWeek put : EntriesMappings.entries$0) {
            linkedHashMap.put(put, Double.valueOf(MapUtil.INVALID_LOCATION));
        }
        return linkedHashMap;
    }

    private final Map<TimeOfDay, Double> getEmptyTimeOfDayAverageMap() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (TimeOfDay put : TimeOfDay.getEntries()) {
            linkedHashMap.put(put, Double.valueOf(MapUtil.INVALID_LOCATION));
        }
        return linkedHashMap;
    }

    private final Map<TimeOfDay, Integer> getTimeOfDay(MediaType mediaType) {
        int i2;
        Bundle bundle = new Bundle();
        bundle.putInt(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE, mediaType.getValue());
        Bundle call = this.context.getContentResolver().call(URI, ContentProviderConstants.Entertainment.Method.RANK_TIME_OF_DAY, (String) null, bundle);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str : ContentProviderConstants.Entertainment.ResultKey.INSTANCE.getTIME_OF_DAY()) {
            if (call != null) {
                i2 = call.getInt(str);
            } else {
                i2 = 0;
            }
            linkedHashMap.put(TimeOfDay.valueOf(str), Integer.valueOf(i2));
        }
        return linkedHashMap;
    }

    public final Context getContext() {
        return this.context;
    }

    public Object getTopArtists(MediaType mediaType, TimeRange timeRange, C1227c cVar) {
        ArrayList<String> arrayList;
        Object obj;
        MediaArtist mediaArtist;
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[getTopArtists] in");
        Bundle bundle = new Bundle();
        bundle.putLong("start_timestamp", timeRange.getStartTime());
        bundle.putLong("end_timestamp", timeRange.getEndTime());
        bundle.putInt(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE, mediaType.getValue());
        ArrayList arrayList2 = null;
        Bundle call = this.context.getContentResolver().call(URI, ContentProviderConstants.Entertainment.Method.GET_TOP_ARTISTS, (String) null, bundle);
        if (call != null) {
            arrayList = call.getStringArrayList(ContentProviderConstants.Entertainment.ResultKey.TOP_ARTISTS);
        } else {
            arrayList = null;
        }
        if (arrayList != null) {
            ArrayList arrayList3 = new ArrayList();
            for (String str : arrayList) {
                SafeJson safeJson = SafeJson.INSTANCE;
                j.b(str);
                try {
                    C1174b json = SafeJson.INSTANCE.getJson();
                    json.getClass();
                    obj = json.a(MediaEntity.Companion.serializer(), str);
                } catch (Throwable th) {
                    obj = a.l(th);
                }
                Throwable a7 = k.a(obj);
                if (a7 != null) {
                    Logger.INSTANCE.e$pde_sdk_1_0_40_release(SafeJson.TAG, "[decodeFromString] failed. " + a7 + '.');
                }
                if (obj instanceof me.j) {
                    obj = null;
                }
                MediaEntity mediaEntity = (MediaEntity) obj;
                if (mediaEntity != null) {
                    mediaArtist = MediaEntityKt.toMediaArtist(mediaEntity);
                } else {
                    mediaArtist = null;
                }
                if (mediaArtist != null) {
                    arrayList3.add(mediaArtist);
                }
            }
            arrayList2 = arrayList3;
        }
        if (arrayList2 != null) {
            return C1194l.k1(arrayList2);
        }
        return C1202t.d;
    }

    public Object getTopMusicList(TimeRange timeRange, C1227c cVar) {
        ArrayList<String> arrayList;
        Object obj;
        Music music;
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[getTopMusicList] in");
        Bundle bundle = new Bundle();
        bundle.putLong("start_timestamp", timeRange.getStartTime());
        bundle.putLong("end_timestamp", timeRange.getEndTime());
        ArrayList arrayList2 = null;
        Bundle call = this.context.getContentResolver().call(URI, ContentProviderConstants.Entertainment.Method.GET_TOP_MUSIC_LIST, (String) null, bundle);
        if (call != null) {
            arrayList = call.getStringArrayList(ContentProviderConstants.Entertainment.ResultKey.TOP_MUSIC_LIST);
        } else {
            arrayList = null;
        }
        if (arrayList != null) {
            ArrayList arrayList3 = new ArrayList();
            for (String str : arrayList) {
                SafeJson safeJson = SafeJson.INSTANCE;
                j.b(str);
                try {
                    C1174b json = SafeJson.INSTANCE.getJson();
                    json.getClass();
                    obj = json.a(MediaEntity.Companion.serializer(), str);
                } catch (Throwable th) {
                    obj = a.l(th);
                }
                Throwable a7 = k.a(obj);
                if (a7 != null) {
                    Logger.INSTANCE.e$pde_sdk_1_0_40_release(SafeJson.TAG, "[decodeFromString] failed. " + a7 + '.');
                }
                if (obj instanceof me.j) {
                    obj = null;
                }
                MediaEntity mediaEntity = (MediaEntity) obj;
                if (mediaEntity != null) {
                    music = MediaEntityKt.toMusic(mediaEntity);
                } else {
                    music = null;
                }
                if (music != null) {
                    arrayList3.add(music);
                }
            }
            arrayList2 = arrayList3;
        }
        if (arrayList2 != null) {
            return C1194l.k1(arrayList2);
        }
        return C1202t.d;
    }

    public Object getTopVideoList(TimeRange timeRange, C1227c cVar) {
        ArrayList<String> arrayList;
        Object obj;
        Video video;
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[getTopVideoList] in");
        Bundle bundle = new Bundle();
        bundle.putLong("start_timestamp", timeRange.getStartTime());
        bundle.putLong("end_timestamp", timeRange.getEndTime());
        ArrayList arrayList2 = null;
        Bundle call = this.context.getContentResolver().call(URI, ContentProviderConstants.Entertainment.Method.GET_TOP_VIDEO_LIST, (String) null, bundle);
        if (call != null) {
            arrayList = call.getStringArrayList(ContentProviderConstants.Entertainment.ResultKey.TOP_VIDEO_LIST);
        } else {
            arrayList = null;
        }
        if (arrayList != null) {
            ArrayList arrayList3 = new ArrayList();
            for (String str : arrayList) {
                SafeJson safeJson = SafeJson.INSTANCE;
                j.b(str);
                try {
                    C1174b json = SafeJson.INSTANCE.getJson();
                    json.getClass();
                    obj = json.a(MediaEntity.Companion.serializer(), str);
                } catch (Throwable th) {
                    obj = a.l(th);
                }
                Throwable a7 = k.a(obj);
                if (a7 != null) {
                    Logger.INSTANCE.e$pde_sdk_1_0_40_release(SafeJson.TAG, "[decodeFromString] failed. " + a7 + '.');
                }
                if (obj instanceof me.j) {
                    obj = null;
                }
                MediaEntity mediaEntity = (MediaEntity) obj;
                if (mediaEntity != null) {
                    video = MediaEntityKt.toVideo(mediaEntity);
                } else {
                    video = null;
                }
                if (video != null) {
                    arrayList3.add(video);
                }
            }
            arrayList2 = arrayList3;
        }
        if (arrayList2 != null) {
            return C1194l.k1(arrayList2);
        }
        return C1202t.d;
    }

    public Object rankDayOfWeek(MediaType mediaType, C1227c cVar) {
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[rankDayOfWeek] in");
        return getDayOfWeek(mediaType);
    }

    public Object rankDayOfWeekWithAverage(MediaType mediaType, C1227c cVar) {
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[rankDayOfWeekWithAverage] in");
        Map<DayOfWeek, Double> emptyDayOfWeekAverageMap = getEmptyDayOfWeekAverageMap();
        for (Map.Entry next : getDayOfWeek(mediaType).entrySet()) {
            Logger logger = Logger.INSTANCE;
            logger.i$pde_sdk_1_0_40_release(TAG, "[rankDayOfWeekWithAverage] " + next);
            emptyDayOfWeekAverageMap.put(next.getKey(), new Double((double) (((Number) next.getValue()).intValue() / DAY_OF_WEEK_COUNT_IN_28_DAYS)));
        }
        return emptyDayOfWeekAverageMap;
    }

    public Object rankTimeOfDay(MediaType mediaType, C1227c cVar) {
        Logger.INSTANCE.e$pde_sdk_1_0_40_release(TAG, "[rankTimeOfDay] in");
        return getTimeOfDay(mediaType);
    }

    public Object rankTimeOfDayWithAverage(MediaType mediaType, C1227c cVar) {
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[rankTimeOfDayWithAverage] in");
        Map<TimeOfDay, Double> emptyTimeOfDayAverageMap = getEmptyTimeOfDayAverageMap();
        for (Map.Entry next : getTimeOfDay(mediaType).entrySet()) {
            Logger logger = Logger.INSTANCE;
            logger.i$pde_sdk_1_0_40_release(TAG, "[rankTimeOfDayWithAverage] " + next);
            emptyTimeOfDayAverageMap.put(next.getKey(), new Double((double) (((Number) next.getValue()).intValue() / TIME_OF_DAY_COUNT_IN_28_DAYS)));
        }
        return emptyTimeOfDayAverageMap;
    }
}
