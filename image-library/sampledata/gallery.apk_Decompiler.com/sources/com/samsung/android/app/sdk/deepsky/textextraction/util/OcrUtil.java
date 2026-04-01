package com.samsung.android.app.sdk.deepsky.textextraction.util;

import android.graphics.Rect;
import com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1195m;
import ne.C1199q;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J/\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0013\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/util/OcrUtil;", "", "<init>", "()V", "", "lineTop1", "lineBottom1", "lineTop2", "lineBottom2", "", "calculateLineIoU", "(IIII)F", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;", "ocrData", "sortOcrData", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;)Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;", "", "Landroid/graphics/Rect;", "rectList", "calculateBoundingRect", "(Ljava/util/List;)Landroid/graphics/Rect;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class OcrUtil {
    public static final OcrUtil INSTANCE = new OcrUtil();

    private OcrUtil() {
    }

    private final float calculateLineIoU(int i2, int i7, int i8, int i10) {
        int max = Math.max(0, Math.min(i7, i10) - Math.max(i2, i8));
        return Math.max(((float) max) / (((float) (((i10 - i8) + (i7 - i2)) - max)) + 1.0E-4f), 0.0f);
    }

    public final Rect calculateBoundingRect(List<Rect> list) {
        Integer num;
        int i2;
        int i7;
        j.e(list, "rectList");
        Iterable iterable = list;
        Iterator it = iterable.iterator();
        Integer num2 = null;
        if (!it.hasNext()) {
            num = null;
        } else {
            num = Integer.valueOf(((Rect) it.next()).right);
            while (it.hasNext()) {
                Integer valueOf = Integer.valueOf(((Rect) it.next()).right);
                if (num.compareTo(valueOf) < 0) {
                    num = valueOf;
                }
            }
        }
        if (num != null) {
            i2 = num.intValue();
        } else {
            i2 = 0;
        }
        Iterator it2 = iterable.iterator();
        if (it2.hasNext()) {
            num2 = Integer.valueOf(((Rect) it2.next()).bottom);
            while (it2.hasNext()) {
                Integer valueOf2 = Integer.valueOf(((Rect) it2.next()).bottom);
                if (num2.compareTo(valueOf2) < 0) {
                    num2 = valueOf2;
                }
            }
        }
        if (num2 != null) {
            i7 = num2.intValue();
        } else {
            i7 = 0;
        }
        return new Rect(0, 0, i2, i7);
    }

    public final OcrData sortOcrData(OcrData ocrData) {
        j.e(ocrData, "ocrData");
        if (ocrData.getBlockList().isEmpty()) {
            return ocrData;
        }
        List g12 = C1194l.g1(ocrData.getBlockList(), new OcrUtil$sortOcrData$$inlined$sortedBy$1());
        ArrayList arrayList = new ArrayList();
        ArrayList m12 = C1194l.m1(g12);
        while (!m12.isEmpty()) {
            OcrData.BlockInfo blockInfo = (OcrData.BlockInfo) m12.remove(0);
            ArrayList s0 = C1195m.s0(blockInfo);
            int i2 = blockInfo.getRect().top;
            int i7 = blockInfo.getRect().bottom;
            Iterator it = m12.iterator();
            while (it.hasNext()) {
                OcrData.BlockInfo blockInfo2 = (OcrData.BlockInfo) it.next();
                if (calculateLineIoU(i2, i7, blockInfo2.getRect().top, blockInfo2.getRect().bottom) > 0.7f) {
                    s0.add(blockInfo2);
                    it.remove();
                    i2 = Math.min(i2, blockInfo2.getRect().top);
                    i7 = Math.max(i7, blockInfo2.getRect().bottom);
                }
            }
            if (s0.size() > 1) {
                C1199q.z0(s0, new OcrUtil$sortOcrData$$inlined$sortBy$1());
            }
            arrayList.addAll(s0);
        }
        return OcrData.copy$default(ocrData, (String) null, arrayList, (Rect) null, 5, (Object) null);
    }
}
