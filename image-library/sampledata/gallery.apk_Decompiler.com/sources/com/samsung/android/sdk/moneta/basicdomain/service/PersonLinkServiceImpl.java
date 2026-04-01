package com.samsung.android.sdk.moneta.basicdomain.service;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.google.gson.Gson;
import com.samsung.android.sdk.moneta.basicdomain.common.ContentProviderConstants;
import com.samsung.android.sdk.moneta.basicdomain.entity.FaceImageData;
import com.samsung.android.sdk.moneta.basicdomain.entity.Person;
import com.samsung.android.sdk.moneta.basicdomain.entity.PersonLink;
import com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip;
import com.samsung.android.sdk.moneta.basicdomain.entity.UserFeedbackType;
import com.samsung.android.sdk.moneta.common.Logger;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1196n;
import ne.C1202t;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0000\u0018\u0000 .2\u00020\u0001:\u0001.B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u000e\u001a\u00020\r2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0010H\u0016¢\u0006\u0004\b\u000e\u0010\u0012J\u0017\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u001d\u0010\u0014\u001a\u00020\r2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010H\u0016¢\u0006\u0004\b\u0014\u0010\u0012J'\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u00102\b\u0010\u0017\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0018\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ'\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00190\u00102\b\u0010\u0017\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001d\u001a\u00020\u001cH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ)\u0010\"\u001a\u00020\r2\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010!\u001a\u00020 H\u0016¢\u0006\u0004\b\"\u0010#J#\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00190\u00102\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010H\u0016¢\u0006\u0004\b%\u0010&J\u0017\u0010'\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0016¢\u0006\u0004\b'\u0010(J\u0017\u0010)\u001a\u00020\u001c2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b)\u0010*R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010+\u001a\u0004\b,\u0010-¨\u0006/"}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/service/PersonLinkServiceImpl;", "Lcom/samsung/android/sdk/moneta/basicdomain/service/PersonLinkService;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "", "galleryPersonUuid", "", "isLinked", "(Ljava/lang/String;)Z", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/PersonLink;", "personLink", "Lme/x;", "link", "(Lcom/samsung/android/sdk/moneta/basicdomain/entity/PersonLink;)V", "", "personLinkList", "(Ljava/util/List;)V", "uuid", "unlinkByGalleryPersonUuid", "(Ljava/lang/String;)V", "uuids", "rawContactUuid", "name", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/Person;", "getUnlinkedPersonsByName", "(Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;", "", "mediaId", "getUnlinkedPersonsByMediaId", "(Ljava/lang/String;J)Ljava/util/List;", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/UserFeedbackType;", "userFeedbackType", "sendUserFeedback", "(Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/sdk/moneta/basicdomain/entity/UserFeedbackType;)V", "mediaUris", "getAllLinkedPersonsByMediaUris", "(Ljava/util/List;)Ljava/util/List;", "getGalleryPersonUuidByRawContactUuid", "(Ljava/lang/String;)Ljava/lang/String;", "getContactIdByGalleryPersonUuid", "(Ljava/lang/String;)J", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PersonLinkServiceImpl implements PersonLinkService {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG = "PersonLinkServiceImpl";
    private static final Uri URI = Uri.parse("content://com.samsung.android.moneta.feature.basicdomain.BasicDomainProvider");
    private final Context context;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u0018\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\n"}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/service/PersonLinkServiceImpl$Companion;", "", "<init>", "()V", "TAG", "", "URI", "Landroid/net/Uri;", "kotlin.jvm.PlatformType", "Landroid/net/Uri;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public PersonLinkServiceImpl(Context context2) {
        j.e(context2, "context");
        this.context = context2;
    }

    public List<Person> getAllLinkedPersonsByMediaUris(List<String> list) {
        String string;
        List<String> list2 = list;
        j.e(list2, "mediaUris");
        Logger logger = Logger.INSTANCE;
        logger.i$pde_sdk_1_0_40_release(TAG, "[getAllLinkedPersonsByMediaUris] in");
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("image_uri", new ArrayList(list2));
        Bundle call = this.context.getContentResolver().call(URI, ContentProviderConstants.PersonLink.Method.GET_ALL_LINKED_PERSONS_BY_MEDIA_URIS, (String) null, bundle);
        if (call == null || (string = call.getString("person_list")) == null) {
            return C1202t.d;
        }
        Object fromJson = new Gson().fromJson(string, new PersonLinkServiceImpl$getAllLinkedPersonsByMediaUris$1$type$1().getType());
        j.d(fromJson, "fromJson(...)");
        List list3 = (List) fromJson;
        logger.i$pde_sdk_1_0_40_release(TAG, "personList size: " + list3.size());
        Iterable<Person> iterable = list3;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (Person person : iterable) {
            Bundle properties = person.getProperties();
            if (properties == null) {
                properties = new Bundle();
            }
            arrayList.add(Person.copy$default(person, (String) null, (Long) null, (Long) null, (String) null, (RelationShip) null, properties, (String) null, 0, (String) null, (Long) null, (FaceImageData) null, 2015, (Object) null));
        }
        return arrayList;
    }

    public long getContactIdByGalleryPersonUuid(String str) {
        j.e(str, "galleryPersonUuid");
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[getContactIdByGalleryPersonUuid] in");
        Bundle bundle = new Bundle();
        bundle.putString(ContentProviderConstants.PersonLink.ParameterKey.GALLERY_PERSON_UUID, str);
        Long l = null;
        Bundle call = this.context.getContentResolver().call(URI, ContentProviderConstants.PersonLink.Method.GET_CONTACT_ID_BY_GALLERY_PERSON_UUID, (String) null, bundle);
        if (call != null) {
            l = Long.valueOf(call.getLong("contact_id", 0));
        }
        if (l != null) {
            return l.longValue();
        }
        return 0;
    }

    public final Context getContext() {
        return this.context;
    }

    public String getGalleryPersonUuidByRawContactUuid(String str) {
        j.e(str, "rawContactUuid");
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[getGalleryPersonUuidByRawContactUuid] in");
        Bundle bundle = new Bundle();
        bundle.putString(ContentProviderConstants.PersonLink.ParameterKey.RAW_CONTACT_UUID, str);
        String str2 = null;
        Bundle call = this.context.getContentResolver().call(URI, ContentProviderConstants.PersonLink.Method.GET_GALLERY_PERSON_UUID_BY_RAW_CONTACT_UUID, (String) null, bundle);
        if (call != null) {
            str2 = call.getString("uuid");
        }
        if (str2 == null) {
            return "";
        }
        return str2;
    }

    public List<Person> getUnlinkedPersonsByMediaId(String str, long j2) {
        String string;
        Logger logger = Logger.INSTANCE;
        logger.i$pde_sdk_1_0_40_release(TAG, "[getUnlinkedPersonsByMediaId] in");
        Bundle bundle = new Bundle();
        bundle.putString(ContentProviderConstants.PersonLink.ParameterKey.RAW_CONTACT_UUID, str);
        bundle.putLong("media_id", j2);
        Bundle call = this.context.getContentResolver().call(URI, ContentProviderConstants.PersonLink.Method.GET_UNLINKED_PERSONS_BY_MEDIA_ID, (String) null, bundle);
        if (call == null || (string = call.getString("person_list")) == null) {
            return C1202t.d;
        }
        Object fromJson = new Gson().fromJson(string, new PersonLinkServiceImpl$getUnlinkedPersonsByMediaId$1$type$1().getType());
        j.d(fromJson, "fromJson(...)");
        List list = (List) fromJson;
        logger.i$pde_sdk_1_0_40_release(TAG, "personList size: " + list.size());
        Iterable<Person> iterable = list;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (Person person : iterable) {
            Bundle properties = person.getProperties();
            if (properties == null) {
                properties = new Bundle();
            }
            arrayList.add(Person.copy$default(person, (String) null, (Long) null, (Long) null, (String) null, (RelationShip) null, properties, (String) null, 0, (String) null, (Long) null, (FaceImageData) null, 2015, (Object) null));
        }
        return arrayList;
    }

    public List<Person> getUnlinkedPersonsByName(String str, String str2) {
        String string;
        String str3 = str2;
        j.e(str3, "name");
        Logger logger = Logger.INSTANCE;
        logger.i$pde_sdk_1_0_40_release(TAG, "[getUnlinkedPersonsByName] in");
        Bundle bundle = new Bundle();
        bundle.putString(ContentProviderConstants.PersonLink.ParameterKey.RAW_CONTACT_UUID, str);
        bundle.putString("name", str3);
        try {
            Bundle call = this.context.getContentResolver().call(URI, ContentProviderConstants.PersonLink.Method.GET_UNLINKED_PERSONS_BY_NAME, (String) null, bundle);
            if (call == null || (string = call.getString("person_list")) == null) {
                return C1202t.d;
            }
            Object fromJson = new Gson().fromJson(string, new PersonLinkServiceImpl$getUnlinkedPersonsByName$1$type$1().getType());
            j.d(fromJson, "fromJson(...)");
            List list = (List) fromJson;
            logger.i$pde_sdk_1_0_40_release(TAG, "personList size: " + list.size());
            Iterable<Person> iterable = list;
            ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
            for (Person person : iterable) {
                Bundle properties = person.getProperties();
                if (properties == null) {
                    properties = new Bundle();
                }
                arrayList.add(Person.copy$default(person, (String) null, (Long) null, (Long) null, (String) null, (RelationShip) null, properties, (String) null, 0, (String) null, (Long) null, (FaceImageData) null, 2015, (Object) null));
            }
            return arrayList;
        } catch (Exception e) {
            Logger logger2 = Logger.INSTANCE;
            logger2.e$pde_sdk_1_0_40_release(TAG, "[getUnlinkedPersonsByName] Exception: " + e);
            throw e;
        }
    }

    public boolean isLinked(String str) {
        j.e(str, "galleryPersonUuid");
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[isLinked] in");
        Bundle bundle = new Bundle();
        bundle.putString(ContentProviderConstants.PersonLink.ParameterKey.GALLERY_PERSON_UUID, str);
        Boolean bool = null;
        Bundle call = this.context.getContentResolver().call(URI, ContentProviderConstants.PersonLink.Method.IS_LINKED, (String) null, bundle);
        if (call != null) {
            bool = Boolean.valueOf(call.getBoolean("result"));
        }
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public void link(PersonLink personLink) {
        j.e(personLink, "personLink");
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[link] in");
        Bundle bundle = new Bundle();
        bundle.putString(ContentProviderConstants.PersonLink.ParameterKey.GALLERY_PERSON_UUID, personLink.getGalleryPersonUuid());
        bundle.putLong(ContentProviderConstants.PersonLink.ParameterKey.RAW_CONTACT_ID, personLink.getRawContactId());
        this.context.getContentResolver().call(URI, ContentProviderConstants.PersonLink.Method.LINK, (String) null, bundle);
    }

    public void sendUserFeedback(String str, String str2, UserFeedbackType userFeedbackType) {
        j.e(str2, "rawContactUuid");
        j.e(userFeedbackType, "userFeedbackType");
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[sendUserFeedback] in");
        Bundle bundle = new Bundle();
        bundle.putString(ContentProviderConstants.PersonLink.ParameterKey.GALLERY_PERSON_UUID, str);
        bundle.putString(ContentProviderConstants.PersonLink.ParameterKey.RAW_CONTACT_UUID, str2);
        bundle.putString(ContentProviderConstants.PersonLink.ParameterKey.USER_FEEDBACK, userFeedbackType.toString());
        this.context.getContentResolver().call(URI, ContentProviderConstants.PersonLink.Method.SEND_USER_FEEDBACK, (String) null, bundle);
    }

    public void unlinkByGalleryPersonUuid(String str) {
        j.e(str, "uuid");
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[unlink] in");
        Bundle bundle = new Bundle();
        bundle.putString("uuid", str);
        this.context.getContentResolver().call(URI, ContentProviderConstants.PersonLink.Method.UNLINK_BY_GALLERY_PERSON_UUID, (String) null, bundle);
    }

    public void unlinkByGalleryPersonUuid(List<String> list) {
        j.e(list, "uuids");
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[unlink list] in");
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("uuid", new ArrayList(list));
        this.context.getContentResolver().call(URI, ContentProviderConstants.PersonLink.Method.UNLINK_BY_GALLERY_PERSON_UUID_LIST, (String) null, bundle);
    }

    public void link(List<PersonLink> list) {
        j.e(list, "personLinkList");
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[link list] in");
        Bundle bundle = new Bundle();
        bundle.putString(ContentProviderConstants.PersonLink.ParameterKey.LINK_LIST, new Gson().toJson((Object) list));
        this.context.getContentResolver().call(URI, ContentProviderConstants.PersonLink.Method.LINK_LIST, (String) null, bundle);
    }
}
