package com.samsung.android.gallery.module.dynamicview;

import A.a;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.srcb.unihal.ActionClipInfo;
import com.samsung.srcb.unihal.SlowFast;
import com.samsung.srcb.unihal.SlowFastInfo;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DynamicViewParser {
    public static DynamicViewInfo createDynamicViewInfo(int i2, List<ActionClipInfo> list, boolean z) {
        if (list == null) {
            return null;
        }
        DynamicViewInfo dynamicViewInfo = new DynamicViewInfo(i2);
        int i7 = 0;
        for (ActionClipInfo next : list) {
            if (z) {
                Log.d("DynamicViewParser", "ActionClipInfo index=" + i7 + " , " + next);
            }
            List<SlowFastInfo> list2 = next.slowFastInfoList;
            if (list2 != null && !list2.isEmpty()) {
                ArrayList arrayList = new ArrayList();
                for (SlowFastInfo next2 : list2) {
                    PlaybackOption createPlaybackInfo = createPlaybackInfo(next2, i2);
                    if (createPlaybackInfo != null) {
                        createPlaybackInfo.setPreOrPostAction(isPreOrPostAction(next, next2));
                        arrayList.add(createPlaybackInfo);
                    }
                }
                if (!arrayList.isEmpty()) {
                    ActionClipInfo actionClipInfo = next;
                    ActionClipInfo actionClipInfo2 = actionClipInfo;
                    dynamicViewInfo.addClip(i7, actionClipInfo.typeId, actionClipInfo.priority, actionClipInfo2.typeScore, (int) actionClipInfo2.startTime, (int) actionClipInfo2.endTime, arrayList);
                    i7++;
                } else {
                    ActionClipInfo actionClipInfo3 = next;
                    Log.w("DynamicViewParser", "no available ActionClipInfo=" + actionClipInfo3 + ", fileDuration=" + i2);
                }
            }
        }
        dynamicViewInfo.clipLoadCompleted();
        return dynamicViewInfo;
    }

    private static PlaybackOption createPlaybackInfo(SlowFastInfo slowFastInfo, int i2) {
        if (isValidRange((int) slowFastInfo.startTime, (int) slowFastInfo.endTime, i2)) {
            return new PlaybackOption((int) slowFastInfo.startTime, (int) slowFastInfo.endTime, getSpeed(SlowFast.getTypeNameById(slowFastInfo.typeId)));
        }
        return null;
    }

    private static float getSpeed(String str) {
        switch (str.hashCode()) {
            case -1303024378:
                if (str.equals("very fast")) {
                    return 4.0f;
                }
                return 1.0f;
            case -1302626645:
                if (str.equals("very slow")) {
                    return 0.25f;
                }
                return 1.0f;
            case -1078030475:
                str.equals("medium");
                return 1.0f;
            case 3135580:
                if (str.equals("fast")) {
                    return 2.0f;
                }
                return 1.0f;
            case 3533313:
                if (str.equals("slow")) {
                    return 0.5f;
                }
                return 1.0f;
            default:
                return 1.0f;
        }
    }

    private static boolean isPreOrPostAction(ActionClipInfo actionClipInfo, SlowFastInfo slowFastInfo) {
        if (actionClipInfo.startTime > slowFastInfo.startTime || actionClipInfo.endTime < slowFastInfo.endTime) {
            return true;
        }
        return false;
    }

    private static boolean isValidRange(int i2, int i7, int i8) {
        if (i2 >= i7 || i2 >= i8 || i7 > i8) {
            return false;
        }
        return true;
    }

    public static DynamicViewInfo parse(byte[] bArr, int i2, boolean z) {
        int i7;
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        try {
            long currentTimeMillis = System.currentTimeMillis();
            DynamicViewInfo createDynamicViewInfo = createDynamicViewInfo(i2, DynamicViewVideoClassifier.unpack(bArr), z);
            StringBuilder sb2 = new StringBuilder("parse");
            if (createDynamicViewInfo != null) {
                i7 = createDynamicViewInfo.getClipCount();
            } else {
                i7 = -1;
            }
            sb2.append(Logger.vt(Integer.valueOf(i7), Long.valueOf(currentTimeMillis)));
            Log.d("DynamicViewParser", sb2.toString());
            return createDynamicViewInfo;
        } catch (Exception | UnsatisfiedLinkError e) {
            a.z(e, new StringBuilder("parse failed. e="), "DynamicViewParser");
            return null;
        }
    }
}
