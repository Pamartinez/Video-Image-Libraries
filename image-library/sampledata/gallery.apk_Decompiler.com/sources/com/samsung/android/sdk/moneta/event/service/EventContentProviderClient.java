package com.samsung.android.sdk.moneta.event.service;

import B1.a;
import Bd.C0725a;
import Tf.n;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.samsung.android.sdk.moneta.common.Constants;
import com.samsung.android.sdk.moneta.common.Logger;
import com.samsung.android.sdk.moneta.common.Util;
import com.samsung.android.sdk.moneta.event.entity.Event;
import com.samsung.android.sdk.moneta.event.entity.EventCategoryEnum;
import com.samsung.android.sdk.moneta.event.entity.EventSubCategoryEnum;
import com.samsung.android.sdk.moneta.event.option.EventOption;
import i.C0212a;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import lg.g;
import me.k;
import me.x;
import ne.C1192j;
import ne.C1202t;
import qe.C1227c;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0000\u0018\u0000 -2\u00020\u0001:\u0001-B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000b\u0010\nJ#\u0010\u000f\u001a\u00020\b2\b\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\fH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J?\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u00132\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00132\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0011H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ'\u0010!\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u001fH\u0002¢\u0006\u0004\b!\u0010\"J\u001e\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00160\u00132\u0006\u0010\u0007\u001a\u00020\u0006H@¢\u0006\u0004\b#\u0010$J$\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00160\u00132\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013H@¢\u0006\u0004\b#\u0010&J\u0016\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00160\u0013H@¢\u0006\u0004\b'\u0010(J4\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00160\u00132\u0006\u0010)\u001a\u00020\f2\u0006\u0010*\u001a\u00020\f2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013H@¢\u0006\u0004\b#\u0010+R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010,¨\u0006."}, d2 = {"Lcom/samsung/android/sdk/moneta/event/service/EventContentProviderClient;", "Lcom/samsung/android/sdk/moneta/event/service/EventService;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Lcom/samsung/android/sdk/moneta/event/option/EventOption;", "option", "", "hasTimeOption", "(Lcom/samsung/android/sdk/moneta/event/option/EventOption;)Z", "hasEventCategoryOption", "", "startTimestamp", "endTimestamp", "isTimeValid", "(Ljava/lang/Long;Ljava/lang/Long;)Z", "", "selection", "", "selectionArgs", "sortOrder", "Lcom/samsung/android/sdk/moneta/event/entity/Event;", "getEventsFromRemote", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;)Ljava/util/List;", "eventCategoryName", "Lcom/samsung/android/sdk/moneta/event/entity/EventCategoryEnum;", "getEventCategoryEnum", "(Ljava/lang/String;)Lcom/samsung/android/sdk/moneta/event/entity/EventCategoryEnum;", "event", "eventCategoryEnum", "Lcom/samsung/android/sdk/moneta/event/entity/EventSubCategoryEnum;", "eventSubCategoryEnum", "getSerializableEvent", "(Ljava/lang/String;Lcom/samsung/android/sdk/moneta/event/entity/EventCategoryEnum;Lcom/samsung/android/sdk/moneta/event/entity/EventSubCategoryEnum;)Lcom/samsung/android/sdk/moneta/event/entity/Event;", "queryEvents", "(Lcom/samsung/android/sdk/moneta/event/option/EventOption;Lqe/c;)Ljava/lang/Object;", "keywords", "(Ljava/util/List;Lqe/c;)Ljava/lang/Object;", "queryAllEvents", "(Lqe/c;)Ljava/lang/Object;", "startTime", "endTime", "(JJLjava/util/List;Lqe/c;)Ljava/lang/Object;", "Landroid/content/Context;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EventContentProviderClient implements EventService {
    public static final Companion Companion = new Companion((e) null);
    private static final String EVENT_CATEGORY_COLUMN = "category";
    private static final String EVENT_COLUMN = "event.event";
    private static final String EVENT_SUB_CATEGORY_COLUMN = "subCategory";
    private static final int INDEX_EVENT_CATEGORY_COLUMN = 1;
    private static final int INDEX_EVENT_COLUMN = 0;
    private static final int INDEX_EVENT_SUB_CATEGORY_COLUMN = 2;
    private static final String KEYWORD_COLUMN = "keyword";
    private static final String OLD_CATEGORY_ENUM_BUSINESS_MEETING = "BUSINESS_MEETING";
    private static final String OLD_CATEGORY_ENUM_SOCIAL_MEETING = "SOCIAL_MEETING";
    private static final String PATH_EVENT = "event";
    private static final String PATH_SEARCH_KEYWORDS = "search_keywords";
    private static final String TAG = "EventContentProviderClient";
    private static final String URI = "content://com.samsung.android.moneta.feature.fourwevent.provider/event";
    private static final String URI_AUTHORITY = "com.samsung.android.moneta.feature.fourwevent.provider";
    private static final String URI_SEARCH_KEYWORDS = "content://com.samsung.android.moneta.feature.fourwevent.provider/search_keywords";
    private final Context context;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0010XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/samsung/android/sdk/moneta/event/service/EventContentProviderClient$Companion;", "", "<init>", "()V", "TAG", "", "URI_AUTHORITY", "PATH_EVENT", "URI", "PATH_SEARCH_KEYWORDS", "URI_SEARCH_KEYWORDS", "EVENT_COLUMN", "KEYWORD_COLUMN", "EVENT_CATEGORY_COLUMN", "EVENT_SUB_CATEGORY_COLUMN", "INDEX_EVENT_COLUMN", "", "INDEX_EVENT_CATEGORY_COLUMN", "INDEX_EVENT_SUB_CATEGORY_COLUMN", "OLD_CATEGORY_ENUM_SOCIAL_MEETING", "OLD_CATEGORY_ENUM_BUSINESS_MEETING", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public EventContentProviderClient(Context context2) {
        j.e(context2, "context");
        this.context = context2;
    }

    private final EventCategoryEnum getEventCategoryEnum(String str) {
        Logger logger = Logger.INSTANCE;
        logger.i$pde_sdk_1_0_40_release(TAG, "getEventCategoryEnum eventCategoryName = " + str);
        if (j.a(str, OLD_CATEGORY_ENUM_SOCIAL_MEETING)) {
            return EventCategoryEnum.SOCIAL;
        }
        if (j.a(str, OLD_CATEGORY_ENUM_BUSINESS_MEETING)) {
            return EventCategoryEnum.BUSINESS;
        }
        return EventCategoryEnum.valueOf(str);
    }

    private final List<Event> getEventsFromRemote(String str, List<String> list, String str2) {
        Object obj;
        String str3;
        String[] strArr;
        String str4;
        Throwable th;
        Logger logger = Logger.INSTANCE;
        logger.d$pde_sdk_1_0_40_release(TAG, "getEventsFromRemote");
        ArrayList arrayList = new ArrayList();
        try {
            ContentResolver contentResolver = this.context.getContentResolver();
            obj = null;
            if (contentResolver != null) {
                Uri parse = Uri.parse(URI);
                String[] strArr2 = {EVENT_COLUMN, "category", EVENT_SUB_CATEGORY_COLUMN};
                if (list != null) {
                    strArr = (String[]) list.toArray(new String[0]);
                    str3 = str2;
                    str4 = str;
                } else {
                    strArr = null;
                    str4 = str;
                    str3 = str2;
                }
                Cursor query = contentResolver.query(parse, strArr2, str4, strArr, str3);
                if (query != null) {
                    Closeable closeable = query;
                    try {
                        Cursor cursor = (Cursor) closeable;
                        logger.d$pde_sdk_1_0_40_release(TAG, "getEventsFromRemote cursor count = " + cursor.getCount());
                        while (cursor.moveToNext()) {
                            String string = cursor.getString(0);
                            j.d(string, "getString(...)");
                            String string2 = cursor.getString(1);
                            j.d(string2, "getString(...)");
                            EventCategoryEnum eventCategoryEnum = getEventCategoryEnum(string2);
                            String string3 = cursor.getString(2);
                            j.d(string3, "getString(...)");
                            arrayList.add(getSerializableEvent(string, eventCategoryEnum, EventSubCategoryEnum.valueOf(string3)));
                        }
                        closeable.close();
                        obj = x.f4917a;
                    } catch (Throwable th2) {
                        a.g(closeable, th);
                        throw th2;
                    }
                }
            }
        } catch (Throwable th3) {
            obj = L2.a.l(th3);
        }
        Throwable a7 = k.a(obj);
        if (a7 != null) {
            Logger.INSTANCE.e$pde_sdk_1_0_40_release(TAG, "getEventsFromRemote e = " + a7.getMessage());
        }
        return arrayList;
    }

    public static /* synthetic */ List getEventsFromRemote$default(EventContentProviderClient eventContentProviderClient, String str, List list, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = null;
        }
        if ((i2 & 2) != 0) {
            list = null;
        }
        if ((i2 & 4) != 0) {
            str2 = null;
        }
        return eventContentProviderClient.getEventsFromRemote(str, list, str2);
    }

    private final Event getSerializableEvent(String str, EventCategoryEnum eventCategoryEnum, EventSubCategoryEnum eventSubCategoryEnum) {
        Event event = (Event) Gd.a.a(new C0725a(3)).a(Event.Companion.serializer(), str);
        return new Event(event.getId(), event.getWhen(), event.getWhat(), event.getWhere(), event.getWho(), eventCategoryEnum, eventSubCategoryEnum);
    }

    /* access modifiers changed from: private */
    public static final x getSerializableEvent$lambda$10(g gVar) {
        j.e(gVar, "$this$Json");
        gVar.f4900c = true;
        gVar.d = true;
        return x.f4917a;
    }

    private final boolean hasEventCategoryOption(EventOption eventOption) {
        boolean z;
        if (eventOption.getEventCategoryEnum() != null) {
            z = true;
        } else {
            z = false;
        }
        Logger logger = Logger.INSTANCE;
        logger.d$pde_sdk_1_0_40_release(TAG, "hasEventCategoryOption " + z);
        return z;
    }

    private final boolean hasTimeOption(EventOption eventOption) {
        boolean z;
        if (eventOption.getStartTimestamp() == null || eventOption.getEndTimestamp() == null) {
            z = false;
        } else {
            z = true;
        }
        Logger logger = Logger.INSTANCE;
        logger.d$pde_sdk_1_0_40_release(TAG, "hasTimeOption " + z);
        return z;
    }

    private final boolean isTimeValid(Long l, Long l8) {
        boolean z = false;
        if (!(l == null || l8 == null)) {
            if (l.longValue() > 0 && l8.longValue() > 0 && l.longValue() < l8.longValue()) {
                z = true;
            }
            Logger logger = Logger.INSTANCE;
            logger.d$pde_sdk_1_0_40_release(TAG, "hasTimeOption " + z);
        }
        return z;
    }

    /* access modifiers changed from: private */
    public static final x queryEvents$lambda$16$lambda$15$lambda$14(g gVar) {
        j.e(gVar, "$this$Json");
        gVar.f4900c = true;
        gVar.d = true;
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x queryEvents$lambda$2$lambda$1$lambda$0(g gVar) {
        j.e(gVar, "$this$Json");
        gVar.f4900c = true;
        gVar.d = true;
        return x.f4917a;
    }

    public /* synthetic */ Object queryAllEvents(C1227c cVar) {
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "queryAllEvents");
        if (Util.INSTANCE.getVersion(this.context) < Constants.PACKAGE_VERSION_ONEUI80) {
            return C1202t.d;
        }
        return getEventsFromRemote$default(this, (String) null, (List) null, (String) null, 7, (Object) null);
    }

    public Object queryEvents(EventOption eventOption, C1227c cVar) {
        Throwable th;
        String str;
        String str2;
        Logger logger = Logger.INSTANCE;
        logger.i$pde_sdk_1_0_40_release(TAG, "queryEvents option");
        Util util = Util.INSTANCE;
        if (util.getVersion(this.context) >= Constants.PACKAGE_VERSION_ONEUI80) {
            boolean hasTimeOption = hasTimeOption(eventOption);
            boolean hasEventCategoryOption = hasEventCategoryOption(eventOption);
            if (!hasTimeOption && !hasEventCategoryOption) {
                logger.i$pde_sdk_1_0_40_release(TAG, "queryEvents there is no option");
                throw new IllegalArgumentException("There is no option!!!");
            } else if (hasTimeOption && !isTimeValid(eventOption.getStartTimestamp(), eventOption.getEndTimestamp())) {
                throw new IllegalArgumentException("Time is not valid");
            } else if (util.getVersion(this.context) < Constants.PACKAGE_VERSION_ONEUI85) {
                ArrayList arrayList = new ArrayList();
                if (hasTimeOption) {
                    Long startTimestamp = eventOption.getStartTimestamp();
                    j.b(startTimestamp);
                    arrayList.add(String.valueOf(startTimestamp.longValue()));
                    Long endTimestamp = eventOption.getEndTimestamp();
                    j.b(endTimestamp);
                    arrayList.add(String.valueOf(endTimestamp.longValue()));
                    str = "startTime >= ? AND endTime <= ?";
                } else {
                    str = "";
                }
                if (hasEventCategoryOption) {
                    if (str.length() > 0) {
                        str2 = " AND category = ?";
                    } else {
                        str2 = "AND category = ?";
                    }
                    str = str.concat(str2);
                    EventCategoryEnum eventCategoryEnum = eventOption.getEventCategoryEnum();
                    j.b(eventCategoryEnum);
                    arrayList.add(eventCategoryEnum.name());
                }
                return getEventsFromRemote$default(this, str, arrayList, (String) null, 4, (Object) null);
            } else {
                try {
                    ArrayList arrayList2 = new ArrayList();
                    ContentResolver contentResolver = this.context.getContentResolver();
                    if (contentResolver != null) {
                        Cursor query = contentResolver.query(Uri.parse(URI_SEARCH_KEYWORDS), (String[]) null, (String) null, (String[]) C1192j.u0(eventOption.getKeywords(), new String[]{String.valueOf(eventOption.getStartTimestamp()), String.valueOf(eventOption.getEndTimestamp())}), (String) null);
                        if (query != null) {
                            Closeable closeable = query;
                            try {
                                Cursor cursor = (Cursor) closeable;
                                logger.d$pde_sdk_1_0_40_release(TAG, "queryEvents with keywords cursor count = " + cursor.getCount());
                                if (cursor.moveToFirst()) {
                                    do {
                                        String string = cursor.getString(0);
                                        j.d(string, "getString(...)");
                                        arrayList2.add(Gd.a.a(new C0725a(4)).a(Event.Companion.serializer(), string));
                                    } while (cursor.moveToNext());
                                }
                                closeable.close();
                                return arrayList2;
                            } catch (Throwable th2) {
                                Throwable th3 = th2;
                                a.g(closeable, th);
                                throw th3;
                            }
                        }
                    }
                    return arrayList2;
                } catch (Throwable th4) {
                    Throwable a7 = k.a(L2.a.l(th4));
                    if (a7 != null) {
                        Logger logger2 = Logger.INSTANCE;
                        logger2.e$pde_sdk_1_0_40_release(TAG, "queryEvents e = " + a7.getMessage());
                    }
                }
            }
        }
        return C1202t.d;
    }

    public Object queryEvents(List list, C1227c cVar) {
        String str;
        Logger logger = Logger.INSTANCE;
        logger.i$pde_sdk_1_0_40_release(TAG, "queryEvents keywords");
        if (Util.INSTANCE.getVersion(this.context) < Constants.PACKAGE_VERSION_ONEUI80) {
            return C1202t.d;
        }
        if (!list.isEmpty()) {
            Iterable<String> iterable = list;
            for (String R02 : iterable) {
                String obj = n.R0(R02).toString();
                Pattern compile = Pattern.compile("\\s+");
                j.d(compile, "compile(...)");
                j.e(obj, "input");
                String replaceAll = compile.matcher(obj).replaceAll(" ");
                j.d(replaceAll, "replaceAll(...)");
                if (n.J0(replaceAll, String.valueOf(new char[]{' '}[0])).size() > 1) {
                    Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "queryEvents keyword should contain no space!!");
                    throw new IllegalArgumentException("The keyword should contain no space!!");
                }
            }
            String str2 = "";
            for (String str3 : iterable) {
                StringBuilder s = C0212a.s(str2);
                if (str2.length() > 0) {
                    str = C0212a.m(" AND keyword LIKE '%", str3, "%'");
                } else {
                    str = C0212a.m("keyword LIKE '%", str3, "%'");
                }
                s.append(str);
                str2 = s.toString();
            }
            Logger logger2 = Logger.INSTANCE;
            logger2.d$pde_sdk_1_0_40_release(TAG, "queryEvents keyword selection = " + str2);
            return getEventsFromRemote$default(this, str2, (List) null, (String) null, 6, (Object) null);
        }
        logger.i$pde_sdk_1_0_40_release(TAG, "queryEvents empty keywords");
        throw new IllegalArgumentException("Empty param!!");
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ae  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object queryEvents(long r9, long r11, java.util.List<java.lang.String> r13, qe.C1227c r14) {
        /*
            r8 = this;
            java.lang.String r14 = "EventContentProviderClient"
            java.lang.String r0 = "queryEvents with keywords cursor count = "
            com.samsung.android.sdk.moneta.common.Util r1 = com.samsung.android.sdk.moneta.common.Util.INSTANCE
            android.content.Context r2 = r8.context
            long r1 = r1.getVersion(r2)
            r3 = 700101000(0x29bab188, double:3.458958527E-315)
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 >= 0) goto L_0x0016
            ne.t r8 = ne.C1202t.d
            return r8
        L_0x0016:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            android.content.Context r8 = r8.context     // Catch:{ all -> 0x0098 }
            android.content.ContentResolver r2 = r8.getContentResolver()     // Catch:{ all -> 0x0098 }
            if (r2 == 0) goto L_0x00a2
            java.lang.String r8 = "content://com.samsung.android.moneta.feature.fourwevent.provider/search_keywords"
            android.net.Uri r3 = android.net.Uri.parse(r8)     // Catch:{ all -> 0x0098 }
            java.lang.String r8 = java.lang.String.valueOf(r9)     // Catch:{ all -> 0x0098 }
            java.lang.String r9 = java.lang.String.valueOf(r11)     // Catch:{ all -> 0x0098 }
            java.lang.String[] r8 = new java.lang.String[]{r8, r9}     // Catch:{ all -> 0x0098 }
            java.util.Collection r13 = (java.util.Collection) r13     // Catch:{ all -> 0x0098 }
            java.lang.Object[] r8 = ne.C1192j.u0(r13, r8)     // Catch:{ all -> 0x0098 }
            r6 = r8
            java.lang.String[] r6 = (java.lang.String[]) r6     // Catch:{ all -> 0x0098 }
            r7 = 0
            r4 = 0
            r5 = 0
            android.database.Cursor r8 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0098 }
            if (r8 == 0) goto L_0x00a2
            java.io.Closeable r8 = (java.io.Closeable) r8     // Catch:{ all -> 0x0098 }
            r9 = r8
            android.database.Cursor r9 = (android.database.Cursor) r9     // Catch:{ all -> 0x008f }
            com.samsung.android.sdk.moneta.common.Logger r10 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE     // Catch:{ all -> 0x008f }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x008f }
            r11.<init>(r0)     // Catch:{ all -> 0x008f }
            int r12 = r9.getCount()     // Catch:{ all -> 0x008f }
            r11.append(r12)     // Catch:{ all -> 0x008f }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x008f }
            r10.d$pde_sdk_1_0_40_release(r14, r11)     // Catch:{ all -> 0x008f }
            boolean r10 = r9.moveToFirst()     // Catch:{ all -> 0x008f }
            if (r10 == 0) goto L_0x0092
        L_0x0067:
            r10 = 0
            java.lang.String r10 = r9.getString(r10)     // Catch:{ all -> 0x008f }
            java.lang.String r11 = "getString(...)"
            kotlin.jvm.internal.j.d(r10, r11)     // Catch:{ all -> 0x008f }
            Bd.a r11 = new Bd.a     // Catch:{ all -> 0x008f }
            r12 = 5
            r11.<init>(r12)     // Catch:{ all -> 0x008f }
            lg.r r11 = Gd.a.a(r11)     // Catch:{ all -> 0x008f }
            com.samsung.android.sdk.moneta.event.entity.Event$Companion r12 = com.samsung.android.sdk.moneta.event.entity.Event.Companion     // Catch:{ all -> 0x008f }
            gg.a r12 = r12.serializer()     // Catch:{ all -> 0x008f }
            java.lang.Object r10 = r11.a(r12, r10)     // Catch:{ all -> 0x008f }
            r1.add(r10)     // Catch:{ all -> 0x008f }
            boolean r10 = r9.moveToNext()     // Catch:{ all -> 0x008f }
            if (r10 != 0) goto L_0x0067
            goto L_0x0092
        L_0x008f:
            r0 = move-exception
            r9 = r0
            goto L_0x009b
        L_0x0092:
            r8.close()     // Catch:{ all -> 0x0098 }
            me.x r8 = me.x.f4917a     // Catch:{ all -> 0x0098 }
            goto L_0x00a8
        L_0x0098:
            r0 = move-exception
            r8 = r0
            goto L_0x00a4
        L_0x009b:
            throw r9     // Catch:{ all -> 0x009c }
        L_0x009c:
            r0 = move-exception
            r10 = r0
            B1.a.g(r8, r9)     // Catch:{ all -> 0x0098 }
            throw r10     // Catch:{ all -> 0x0098 }
        L_0x00a2:
            r8 = 0
            goto L_0x00a8
        L_0x00a4:
            me.j r8 = L2.a.l(r8)
        L_0x00a8:
            java.lang.Throwable r8 = me.k.a(r8)
            if (r8 == 0) goto L_0x00c5
            com.samsung.android.sdk.moneta.common.Logger r9 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "getEventsFromRemote e = "
            r10.<init>(r11)
            java.lang.String r8 = r8.getMessage()
            r10.append(r8)
            java.lang.String r8 = r10.toString()
            r9.e$pde_sdk_1_0_40_release(r14, r8)
        L_0x00c5:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.event.service.EventContentProviderClient.queryEvents(long, long, java.util.List, qe.c):java.lang.Object");
    }
}
