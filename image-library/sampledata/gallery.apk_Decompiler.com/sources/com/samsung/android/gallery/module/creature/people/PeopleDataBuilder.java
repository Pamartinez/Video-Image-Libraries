package com.samsung.android.gallery.module.creature.people;

import android.database.Cursor;
import android.graphics.RectF;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.StringTokenizer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class PeopleDataBuilder {
    public static PeopleData createPeopleData(Cursor cursor) {
        long j2;
        String string = cursor.getString(cursor.getColumnIndex("__creatureName"));
        long j3 = cursor.getLong(cursor.getColumnIndex("__creatureID"));
        long j8 = cursor.getLong(cursor.getColumnIndex("__creatureFaceGroupID"));
        if (Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY)) {
            j2 = cursor.getLong(cursor.getColumnIndex("__creatureFaceRecommendedID"));
        } else {
            j2 = 0;
        }
        return new PeopleData(getPosRatioRectF(cursor.getString(cursor.getColumnIndex("__creatureFacePosRatio"))), string, j3, j8, j2, cursor.getLong(cursor.getColumnIndex("__creatureFaceData")));
    }

    private static RectF getPosRatioRectF(String str) {
        RectF rectF = new RectF();
        if (str != null && !str.isEmpty()) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            rectF.left = Float.parseFloat(stringTokenizer.nextToken());
            rectF.top = Float.parseFloat(stringTokenizer.nextToken());
            rectF.right = Float.parseFloat(stringTokenizer.nextToken());
            rectF.bottom = Float.parseFloat(stringTokenizer.nextToken());
        }
        return rectF;
    }
}
