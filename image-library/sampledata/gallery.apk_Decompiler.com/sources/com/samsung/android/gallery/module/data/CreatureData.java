package com.samsung.android.gallery.module.data;

import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.support.utils.Copyable;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.function.Function;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreatureData implements Copyable<CreatureData> {
    private static final String TAG = "CreatureData";
    static final Function<String, Object> constructor = new j(1);
    public String contactRawId;
    public String creatureName;
    public String creatureUuid;
    public int essentialGroup = -1;
    public long faceGroupId = -1;
    public String faceGroupUuid;
    public long faceOrder = -1;
    public String facePositionRatio;
    public int faceScore;
    public int frameId = -1;
    public int framePos = -1;
    public boolean isCreatureHide;
    public String relationshipType;
    public int uniqueDays = -1;

    public static String computeNameIfAbsent(FileItemInterface fileItemInterface, Supplier<String> supplier) {
        String str = of(fileItemInterface).creatureName;
        if (TextUtils.isEmpty(str)) {
            return supplier.get();
        }
        return str;
    }

    public static void copy(FileItemInterface fileItemInterface, FileItemInterface fileItemInterface2) {
        CreatureData of2 = of(fileItemInterface);
        CreatureData of3 = of(fileItemInterface2);
        of3.creatureName = of2.creatureName;
        of3.relationshipType = of2.relationshipType;
        if (Features.isEnabled(Features.SUPPORT_PDC_CONTACT_LINK)) {
            of3.contactRawId = of2.contactRawId;
            of3.creatureUuid = of2.creatureUuid;
        }
        of3.faceScore = of2.faceScore;
        of3.facePositionRatio = of2.facePositionRatio;
        of3.faceGroupId = of2.faceGroupId;
        if (PreferenceFeatures.OneUi8x.SUPPORT_ESSENTIAL_FACES) {
            of3.essentialGroup = of2.essentialGroup;
            of3.uniqueDays = of2.uniqueDays;
            of3.faceGroupUuid = of2.faceGroupUuid;
        }
        if (PreferenceFeatures.OneUi8x.SUPPORT_CREATURE_THUMB_FROM_VIDEO_FRAME) {
            of3.frameId = of2.frameId;
            of3.framePos = of2.framePos;
        }
    }

    public static boolean hasContactRawId(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || TextUtils.isEmpty(of(fileItemInterface).contactRawId)) {
            return false;
        }
        return true;
    }

    public static boolean hasName(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || TextUtils.isEmpty(of(fileItemInterface).creatureName)) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r3 = of(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isEssential(com.samsung.android.gallery.database.dbtype.FileItemInterface r3) {
        /*
            r0 = 0
            if (r3 == 0) goto L_0x0014
            com.samsung.android.gallery.module.data.CreatureData r3 = of(r3)
            int r1 = r3.essentialGroup
            if (r1 != 0) goto L_0x0010
            int r3 = r3.uniqueDays
            r2 = 5
            if (r3 >= r2) goto L_0x0012
        L_0x0010:
            if (r1 <= 0) goto L_0x0014
        L_0x0012:
            r3 = 1
            return r3
        L_0x0014:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.data.CreatureData.isEssential(com.samsung.android.gallery.database.dbtype.FileItemInterface):boolean");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$static$0(String str) {
        return new CreatureData();
    }

    public static CreatureData of(FileItemInterface fileItemInterface) {
        return (CreatureData) fileItemInterface.tags().computeIfAbsent(TAG, constructor);
    }

    public String toString() {
        return Logger.getEncodedString("Name: " + this.creatureName + ", Relation: " + this.relationshipType + ", ContactID: " + this.contactRawId + ", UUID: " + this.creatureUuid + ", Score: " + this.faceScore + ", GroupId: " + this.faceGroupId + ", EssentialGroup: " + this.essentialGroup + ", UniqueDays: " + this.uniqueDays + ", FG.UUID: " + this.faceGroupUuid);
    }

    public CreatureData copyOf() {
        try {
            return (CreatureData) super.clone();
        } catch (CloneNotSupportedException e) {
            Log.e((CharSequence) TAG, "copyOf failed", (Throwable) e);
            return this;
        }
    }
}
