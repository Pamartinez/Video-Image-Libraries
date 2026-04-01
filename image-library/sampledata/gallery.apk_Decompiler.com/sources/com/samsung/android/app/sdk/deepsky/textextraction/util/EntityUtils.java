package com.samsung.android.app.sdk.deepsky.textextraction.util;

import android.app.RemoteAction;
import android.os.Bundle;
import android.view.textclassifier.TextClassification;
import com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1192j;
import ne.C1194l;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/util/EntityUtils;", "", "<init>", "()V", "getEntityExtras", "Landroid/os/Bundle;", "classification", "Landroid/view/textclassifier/TextClassification;", "action", "Landroid/app/RemoteAction;", "getEntityStartIndex", "", "getEntityEndIndex", "getEntityText", "", "getEntityTypeId", "getEntityActionId", "getEntityIsPoiEntity", "", "isDateTimeEntity", "entityType", "Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityType;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EntityUtils {
    public static final EntityUtils INSTANCE = new EntityUtils();

    private EntityUtils() {
    }

    private final Bundle getEntityExtras(TextClassification textClassification, RemoteAction remoteAction) {
        int i2;
        Integer o0;
        int[] intArray = textClassification.getExtras().getIntArray("entity-index");
        if (intArray == null || (o0 = C1192j.o0(textClassification.getActions().indexOf(remoteAction), intArray)) == null) {
            i2 = 0;
        } else {
            i2 = o0.intValue();
        }
        ArrayList parcelableArrayList = textClassification.getExtras().getParcelableArrayList("entities");
        if (parcelableArrayList != null) {
            return (Bundle) C1194l.O0(i2, parcelableArrayList);
        }
        return null;
    }

    public final String getEntityActionId(TextClassification textClassification, RemoteAction remoteAction) {
        String string;
        j.e(textClassification, "classification");
        j.e(remoteAction, "action");
        Bundle entityExtras = getEntityExtras(textClassification, remoteAction);
        if (entityExtras == null || (string = entityExtras.getString("action-id", "")) == null) {
            return "";
        }
        return string;
    }

    public final int getEntityEndIndex(TextClassification textClassification, RemoteAction remoteAction) {
        j.e(textClassification, "classification");
        j.e(remoteAction, "action");
        Bundle entityExtras = getEntityExtras(textClassification, remoteAction);
        if (entityExtras != null) {
            return entityExtras.getInt("end-index", 0);
        }
        return 0;
    }

    public final boolean getEntityIsPoiEntity(TextClassification textClassification, RemoteAction remoteAction) {
        j.e(textClassification, "classification");
        j.e(remoteAction, "action");
        Bundle entityExtras = getEntityExtras(textClassification, remoteAction);
        if (entityExtras != null) {
            return entityExtras.getBoolean("is-poi-entity", false);
        }
        return false;
    }

    public final int getEntityStartIndex(TextClassification textClassification, RemoteAction remoteAction) {
        j.e(textClassification, "classification");
        j.e(remoteAction, "action");
        Bundle entityExtras = getEntityExtras(textClassification, remoteAction);
        if (entityExtras != null) {
            return entityExtras.getInt("start-index", 0);
        }
        return 0;
    }

    public final String getEntityText(TextClassification textClassification, RemoteAction remoteAction) {
        String string;
        j.e(textClassification, "classification");
        j.e(remoteAction, "action");
        Bundle entityExtras = getEntityExtras(textClassification, remoteAction);
        if (entityExtras == null || (string = entityExtras.getString("text", "")) == null) {
            return "";
        }
        return string;
    }

    public final String getEntityTypeId(TextClassification textClassification, RemoteAction remoteAction) {
        String string;
        j.e(textClassification, "classification");
        j.e(remoteAction, "action");
        Bundle entityExtras = getEntityExtras(textClassification, remoteAction);
        if (entityExtras == null || (string = entityExtras.getString("entity-type", "")) == null) {
            return "";
        }
        return string;
    }

    public final boolean isDateTimeEntity(EntityType entityType) {
        j.e(entityType, "entityType");
        if (entityType == EntityType.DATE || entityType == EntityType.DATE_TIME) {
            return true;
        }
        return false;
    }
}
