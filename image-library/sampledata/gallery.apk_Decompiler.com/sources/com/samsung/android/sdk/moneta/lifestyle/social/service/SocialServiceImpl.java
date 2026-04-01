package com.samsung.android.sdk.moneta.lifestyle.social.service;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.sdk.moneta.common.Logger;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import com.samsung.android.sdk.moneta.lifestyle.common.TimeRange;
import com.samsung.android.sdk.moneta.lifestyle.social.entity.ContactChannel;
import com.samsung.android.sdk.moneta.lifestyle.social.entity.PreferenceLevel;
import com.samsung.android.sdk.moneta.lifestyle.social.entity.option.FrequentContactRequestOption;
import com.samsung.android.sdk.moneta.lifestyle.social.entity.option.MostContactRequestOption;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1202t;
import qe.C1227c;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\b\b\u0000\u0018\u0000 -2\u00020\u0001:\u0001-B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ!\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ!\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0002¢\u0006\u0004\b\u000e\u0010\u0012J\u0019\u0010\t\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0002¢\u0006\u0004\b\t\u0010\u0013J\u001f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\rH\u0002¢\u0006\u0004\b\u0017\u0010\u0018J(\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H@¢\u0006\u0004\b\u0019\u0010\u001aJ(\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H@¢\u0006\u0004\b\u001b\u0010\u001cJ\u001e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u001e\u001a\u00020\u001dH@¢\u0006\u0004\b\u001f\u0010 J \u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\b\u0010!\u001a\u0004\u0018\u00010\bH@¢\u0006\u0004\b\"\u0010#J\u0016\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H@¢\u0006\u0004\b$\u0010%J\u001e\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010'\u001a\u00020&H@¢\u0006\u0004\b(\u0010)R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010*\u001a\u0004\b+\u0010,¨\u0006."}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/social/service/SocialServiceImpl;", "Lcom/samsung/android/sdk/moneta/lifestyle/social/service/SocialService;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/option/MostContactRequestOption;", "mostContactRequestOption", "", "getMethod", "(Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/option/MostContactRequestOption;)Ljava/lang/String;", "Lcom/samsung/android/sdk/moneta/lifestyle/common/TimeRange;", "timeRange", "Landroid/os/Bundle;", "getParameterBundle", "(Lcom/samsung/android/sdk/moneta/lifestyle/common/TimeRange;Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/option/MostContactRequestOption;)Landroid/os/Bundle;", "Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/option/FrequentContactRequestOption;", "frequentContactRequestOption", "(Lcom/samsung/android/sdk/moneta/lifestyle/common/TimeRange;Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/option/FrequentContactRequestOption;)Landroid/os/Bundle;", "(Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/option/FrequentContactRequestOption;)Ljava/lang/String;", "result", "", "Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/Person;", "convertToPersonList", "(Landroid/os/Bundle;)Ljava/util/List;", "getMostContactPersonList", "(Lcom/samsung/android/sdk/moneta/lifestyle/common/TimeRange;Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/option/MostContactRequestOption;Lqe/c;)Ljava/lang/Object;", "getFrequentContactPersonList", "(Lcom/samsung/android/sdk/moneta/lifestyle/common/TimeRange;Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/option/FrequentContactRequestOption;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/PreferenceLevel;", "preferenceLevel", "getFrequentContactPersonListByPreferenceLevel", "(Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/PreferenceLevel;Lqe/c;)Ljava/lang/Object;", "myPhoneNumber", "getRecentContactPersonList", "(Ljava/lang/String;Lqe/c;)Ljava/lang/Object;", "getUpcomingContactPersonList", "(Lqe/c;)Ljava/lang/Object;", "", "startTimestamp", "getUpcomingEventPersonList", "(JLqe/c;)Ljava/lang/Object;", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SocialServiceImpl implements SocialService {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG = "Lifestyle-SocialServiceImpl";
    private static Uri URI = Uri.parse("content://com.samsung.android.moneta.feature.preference.LifeStyleProvider");
    private final Context context;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u0018\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u000e¢\u0006\u0004\n\u0002\u0010\t¨\u0006\n"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/social/service/SocialServiceImpl$Companion;", "", "<init>", "()V", "TAG", "", "URI", "Landroid/net/Uri;", "kotlin.jvm.PlatformType", "Landroid/net/Uri;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public SocialServiceImpl(Context context2) {
        j.e(context2, "context");
        this.context = context2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: me.j} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: me.j} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: me.j} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.List<com.samsung.android.sdk.moneta.lifestyle.social.entity.Person> convertToPersonList(android.os.Bundle r9) {
        /*
            r8 = this;
            r8 = 46
            r0 = 0
            if (r9 == 0) goto L_0x000f
            java.lang.String r1 = "person_list"
            java.util.ArrayList r9 = r9.getStringArrayList(r1)     // Catch:{ all -> 0x000c }
            goto L_0x0010
        L_0x000c:
            r9 = move-exception
            goto L_0x007b
        L_0x000f:
            r9 = r0
        L_0x0010:
            if (r9 == 0) goto L_0x0080
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x000c }
            r1.<init>()     // Catch:{ all -> 0x000c }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ all -> 0x000c }
        L_0x001b:
            boolean r2 = r9.hasNext()     // Catch:{ all -> 0x000c }
            if (r2 == 0) goto L_0x0081
            java.lang.Object r2 = r9.next()     // Catch:{ all -> 0x000c }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x000c }
            com.samsung.android.sdk.moneta.common.SafeJson r3 = com.samsung.android.sdk.moneta.common.SafeJson.INSTANCE     // Catch:{ all -> 0x000c }
            kotlin.jvm.internal.j.b(r2)     // Catch:{ all -> 0x000c }
            com.samsung.android.sdk.moneta.common.SafeJson r3 = com.samsung.android.sdk.moneta.common.SafeJson.INSTANCE     // Catch:{ all -> 0x0040 }
            lg.b r3 = r3.getJson()     // Catch:{ all -> 0x0040 }
            r3.getClass()     // Catch:{ all -> 0x0040 }
            com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1.PersonEntity$Companion r4 = com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1.PersonEntity.Companion     // Catch:{ all -> 0x0040 }
            gg.a r4 = r4.serializer()     // Catch:{ all -> 0x0040 }
            java.lang.Object r2 = r3.a(r4, r2)     // Catch:{ all -> 0x0040 }
            goto L_0x0045
        L_0x0040:
            r2 = move-exception
            me.j r2 = L2.a.l(r2)     // Catch:{ all -> 0x000c }
        L_0x0045:
            java.lang.Throwable r3 = me.k.a(r2)     // Catch:{ all -> 0x000c }
            if (r3 == 0) goto L_0x0066
            com.samsung.android.sdk.moneta.common.Logger r4 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE     // Catch:{ all -> 0x000c }
            java.lang.String r5 = "SafeJson"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x000c }
            r6.<init>()     // Catch:{ all -> 0x000c }
            java.lang.String r7 = "[decodeFromString] failed. "
            r6.append(r7)     // Catch:{ all -> 0x000c }
            r6.append(r3)     // Catch:{ all -> 0x000c }
            r6.append(r8)     // Catch:{ all -> 0x000c }
            java.lang.String r3 = r6.toString()     // Catch:{ all -> 0x000c }
            r4.e$pde_sdk_1_0_40_release(r5, r3)     // Catch:{ all -> 0x000c }
        L_0x0066:
            boolean r3 = r2 instanceof me.j     // Catch:{ all -> 0x000c }
            if (r3 == 0) goto L_0x006b
            r2 = r0
        L_0x006b:
            com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1.PersonEntity r2 = (com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1.PersonEntity) r2     // Catch:{ all -> 0x000c }
            if (r2 == 0) goto L_0x0074
            com.samsung.android.sdk.moneta.lifestyle.social.entity.Person r2 = com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1.PersonEntityKt.toPerson(r2)     // Catch:{ all -> 0x000c }
            goto L_0x0075
        L_0x0074:
            r2 = r0
        L_0x0075:
            if (r2 == 0) goto L_0x001b
            r1.add(r2)     // Catch:{ all -> 0x000c }
            goto L_0x001b
        L_0x007b:
            me.j r9 = L2.a.l(r9)
            goto L_0x0082
        L_0x0080:
            r1 = r0
        L_0x0081:
            r9 = r1
        L_0x0082:
            boolean r1 = r9 instanceof me.j
            java.lang.String r2 = "Lifestyle-SocialServiceImpl"
            if (r1 != 0) goto L_0x00aa
            r3 = r9
            java.util.List r3 = (java.util.List) r3
            com.samsung.android.sdk.moneta.common.Logger r4 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "[convertToPersonList] personList size: "
            r5.<init>(r6)
            if (r3 == 0) goto L_0x009f
            int r3 = r3.size()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            goto L_0x00a0
        L_0x009f:
            r3 = r0
        L_0x00a0:
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            r4.i$pde_sdk_1_0_40_release(r2, r3)
        L_0x00aa:
            java.lang.Throwable r3 = me.k.a(r9)
            if (r3 == 0) goto L_0x00c6
            com.samsung.android.sdk.moneta.common.Logger r4 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "[convertToPersonList] failed. "
            r5.<init>(r6)
            r5.append(r3)
            r5.append(r8)
            java.lang.String r8 = r5.toString()
            r4.e$pde_sdk_1_0_40_release(r2, r8)
        L_0x00c6:
            if (r1 == 0) goto L_0x00c9
            goto L_0x00ca
        L_0x00c9:
            r0 = r9
        L_0x00ca:
            java.util.List r0 = (java.util.List) r0
            if (r0 != 0) goto L_0x00d0
            ne.t r0 = ne.C1202t.d
        L_0x00d0:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.lifestyle.social.service.SocialServiceImpl.convertToPersonList(android.os.Bundle):java.util.List");
    }

    private final String getMethod(MostContactRequestOption mostContactRequestOption) {
        if ((mostContactRequestOption != null ? mostContactRequestOption.getContactChannel() : null) == null) {
            return ContentProviderConstants.Social.Method.GET_MOST_CONTACT_PERSON_LIST_FROM_ALL_CHANNELS;
        }
        return ContentProviderConstants.Social.Method.GET_MOST_CONTACT_PERSON_LIST;
    }

    private final Bundle getParameterBundle(TimeRange timeRange, MostContactRequestOption mostContactRequestOption) {
        Bundle bundle = new Bundle();
        bundle.putLong("start_timestamp", timeRange.getStartTime());
        bundle.putLong("end_timestamp", timeRange.getEndTime());
        if (mostContactRequestOption != null) {
            ContactChannel contactChannel = mostContactRequestOption.getContactChannel();
            if (contactChannel != null) {
                bundle.putInt(ContentProviderConstants.Social.ParameterKey.CHANNEL, contactChannel.getValue());
            }
            String myPhoneNumber = mostContactRequestOption.getMyPhoneNumber();
            if (myPhoneNumber != null) {
                bundle.putString("my_phone_number", myPhoneNumber);
            }
        }
        return bundle;
    }

    public final Context getContext() {
        return this.context;
    }

    public /* synthetic */ Object getFrequentContactPersonList(TimeRange timeRange, FrequentContactRequestOption frequentContactRequestOption, C1227c cVar) {
        Logger logger = Logger.INSTANCE;
        logger.i$pde_sdk_1_0_40_release(TAG, "[getFrequentContactPerson] in timeRange: " + timeRange + " frequentContactRequestOption: " + frequentContactRequestOption);
        return convertToPersonList(this.context.getContentResolver().call(URI, getMethod(frequentContactRequestOption), (String) null, getParameterBundle(timeRange, frequentContactRequestOption)));
    }

    public /* synthetic */ Object getFrequentContactPersonListByPreferenceLevel(PreferenceLevel preferenceLevel, C1227c cVar) {
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[getFrequentContactPersonByPreferenceLevel] in");
        Bundle bundle = new Bundle();
        bundle.putInt("preference_level", preferenceLevel.getValue());
        return convertToPersonList(this.context.getContentResolver().call(URI, ContentProviderConstants.Social.Method.GET_FREQUENT_CONTACT_PERSON_LIST_BY_PREFERENCE_LEVEL, (String) null, bundle));
    }

    public /* synthetic */ Object getMostContactPersonList(TimeRange timeRange, MostContactRequestOption mostContactRequestOption, C1227c cVar) {
        Logger logger = Logger.INSTANCE;
        logger.i$pde_sdk_1_0_40_release(TAG, "[getMostContactPerson] in timeRange: " + timeRange + " mostContactRequestOption: " + mostContactRequestOption);
        return convertToPersonList(this.context.getContentResolver().call(URI, getMethod(mostContactRequestOption), (String) null, getParameterBundle(timeRange, mostContactRequestOption)));
    }

    public /* synthetic */ Object getRecentContactPersonList(String str, C1227c cVar) {
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[getRecentContactPerson] in");
        Bundle bundle = new Bundle();
        bundle.putString("my_phone_number", str);
        return convertToPersonList(this.context.getContentResolver().call(URI, ContentProviderConstants.Social.Method.GET_RECENT_CONTACT_PERSON_LIST, (String) null, bundle));
    }

    public /* synthetic */ Object getUpcomingContactPersonList(C1227c cVar) {
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[getUpcomingContactPerson] in");
        return convertToPersonList(this.context.getContentResolver().call(URI, ContentProviderConstants.Social.Method.GET_UPCOMING_CONTACT_PERSON_LIST, (String) null, (Bundle) null));
    }

    public Object getUpcomingEventPersonList(long j2, C1227c cVar) {
        Logger.INSTANCE.e$pde_sdk_1_0_40_release(TAG, "[getUpcomingEventPersonList] is deprecated. Do not USE this API");
        return C1202t.d;
    }

    private final String getMethod(FrequentContactRequestOption frequentContactRequestOption) {
        if ((frequentContactRequestOption != null ? frequentContactRequestOption.getContactChannel() : null) == null) {
            return ContentProviderConstants.Social.Method.GET_FREQUENT_CONTACT_PERSON_LIST_FROM_ALL_CHANNELS;
        }
        return ContentProviderConstants.Social.Method.GET_FREQUENT_CONTACT_PERSON_LIST;
    }

    private final Bundle getParameterBundle(TimeRange timeRange, FrequentContactRequestOption frequentContactRequestOption) {
        Bundle bundle = new Bundle();
        bundle.putLong("start_timestamp", timeRange.getStartTime());
        bundle.putLong("end_timestamp", timeRange.getEndTime());
        if (frequentContactRequestOption != null) {
            ContactChannel contactChannel = frequentContactRequestOption.getContactChannel();
            if (contactChannel != null) {
                bundle.putInt(ContentProviderConstants.Social.ParameterKey.CHANNEL, contactChannel.getValue());
            }
            String myPhoneNumber = frequentContactRequestOption.getMyPhoneNumber();
            if (myPhoneNumber != null) {
                bundle.putString("my_phone_number", myPhoneNumber);
            }
        }
        return bundle;
    }
}
