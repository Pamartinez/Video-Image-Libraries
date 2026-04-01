package com.samsung.android.sdk.moneta.basicdomain.service;

import com.samsung.android.sdk.moneta.basicdomain.entity.Person;
import com.samsung.android.sdk.moneta.basicdomain.entity.PersonLink;
import com.samsung.android.sdk.moneta.basicdomain.entity.UserFeedbackType;
import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H'¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\n\u001a\u00020\t2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\fH'¢\u0006\u0004\b\n\u0010\u000eJ\u0017\u0010\u0010\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0002H'¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0010\u001a\u00020\t2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\fH'¢\u0006\u0004\b\u0010\u0010\u000eJ'\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0014\u001a\u00020\u0002H&¢\u0006\u0004\b\u0016\u0010\u0017J'\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00150\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0019\u001a\u00020\u0018H&¢\u0006\u0004\b\u001a\u0010\u001bJ)\u0010\u001e\u001a\u00020\t2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u001cH&¢\u0006\u0004\b\u001e\u0010\u001fJ#\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00150\f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00020\fH&¢\u0006\u0004\b!\u0010\"J\u0017\u0010#\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0002H&¢\u0006\u0004\b#\u0010$J\u0017\u0010%\u001a\u00020\u00182\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b%\u0010&¨\u0006'"}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/service/PersonLinkService;", "", "", "galleryPersonUuid", "", "isLinked", "(Ljava/lang/String;)Z", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/PersonLink;", "personLink", "Lme/x;", "link", "(Lcom/samsung/android/sdk/moneta/basicdomain/entity/PersonLink;)V", "", "personLinkList", "(Ljava/util/List;)V", "uuid", "unlinkByGalleryPersonUuid", "(Ljava/lang/String;)V", "uuids", "rawContactUuid", "name", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/Person;", "getUnlinkedPersonsByName", "(Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;", "", "mediaId", "getUnlinkedPersonsByMediaId", "(Ljava/lang/String;J)Ljava/util/List;", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/UserFeedbackType;", "userFeedbackType", "sendUserFeedback", "(Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/sdk/moneta/basicdomain/entity/UserFeedbackType;)V", "mediaUris", "getAllLinkedPersonsByMediaUris", "(Ljava/util/List;)Ljava/util/List;", "getGalleryPersonUuidByRawContactUuid", "(Ljava/lang/String;)Ljava/lang/String;", "getContactIdByGalleryPersonUuid", "(Ljava/lang/String;)J", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface PersonLinkService {
    List<Person> getAllLinkedPersonsByMediaUris(List<String> list);

    long getContactIdByGalleryPersonUuid(String str);

    String getGalleryPersonUuidByRawContactUuid(String str);

    List<Person> getUnlinkedPersonsByMediaId(String str, long j2);

    List<Person> getUnlinkedPersonsByName(String str, String str2);

    boolean isLinked(String str);

    void link(PersonLink personLink);

    void link(List<PersonLink> list);

    void sendUserFeedback(String str, String str2, UserFeedbackType userFeedbackType);

    void unlinkByGalleryPersonUuid(String str);

    void unlinkByGalleryPersonUuid(List<String> list);
}
