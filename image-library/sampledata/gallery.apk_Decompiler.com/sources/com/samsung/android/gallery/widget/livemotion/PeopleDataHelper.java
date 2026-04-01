package com.samsung.android.gallery.widget.livemotion;

import A4.A;
import B8.d;
import H.a;
import android.graphics.RectF;
import android.view.View;
import com.samsung.android.gallery.module.creature.people.PeopleData;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.ocr.MOCRLang;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PeopleDataHelper {
    private final ConcurrentHashMap<Long, ArrayList<PeopleData>> mPeopleData = new ConcurrentHashMap<>();
    private final Stack<Long> mRequests = new Stack<>();

    private RectF getDisplayRectF(View view, MediaItem mediaItem) {
        boolean z;
        int i2;
        int i7;
        float height;
        int height2;
        int i8;
        int i10;
        if (mediaItem == null) {
            return new RectF(0.0f, 0.0f, (float) view.getWidth(), (float) view.getHeight());
        }
        boolean z3 = false;
        if (mediaItem.getOrientation() % MOCRLang.KHMER == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            i2 = mediaItem.getWidth();
        } else {
            i2 = mediaItem.getHeight();
        }
        if (z) {
            i7 = mediaItem.getHeight();
        } else {
            i7 = mediaItem.getWidth();
        }
        if (i7 > i2) {
            z3 = true;
        }
        if (z3) {
            height = (float) view.getWidth();
            height2 = mediaItem.getWidth();
        } else {
            height = (float) view.getHeight();
            height2 = mediaItem.getHeight();
        }
        float f = height / ((float) height2);
        if (z3) {
            i8 = view.getWidth();
        } else {
            i8 = (int) (((float) i2) * f);
        }
        if (z3) {
            i10 = (int) (((float) i7) * f);
        } else {
            i10 = view.getHeight();
        }
        return new RectF(0.0f, 0.0f, (float) i8, (float) i10);
    }

    private List<PeopleData> getPeopleData(MediaItem mediaItem) {
        if (mediaItem != null) {
            return this.mPeopleData.get(Long.valueOf(mediaItem.getFileId()));
        }
        return null;
    }

    private boolean intersect(RectF rectF, RectF rectF2) {
        RectF centerCropRect = RectUtils.getCenterCropRect(rectF);
        if (centerCropRect.intersect(rectF2)) {
            RectF rectF3 = new RectF();
            rectF3.left = Math.max(centerCropRect.left, rectF2.left);
            rectF3.right = Math.min(centerCropRect.right, rectF2.right);
            rectF3.top = Math.max(centerCropRect.top, rectF2.top);
            rectF3.bottom = Math.min(centerCropRect.bottom, rectF2.bottom);
            if ((rectF3.height() * rectF3.width()) / (rectF2.height() * rectF2.width()) > 0.7f) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadPeopleData$2(MediaItem mediaItem, ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        if (arrayList != null && !arrayList.isEmpty()) {
            arrayList.stream().limit(5).forEach(new d(arrayList2, 8));
        }
        this.mPeopleData.put(Long.valueOf(mediaItem.getFileId()), arrayList2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepare$0(MediaItem mediaItem) {
        if (this.mRequests.remove(Long.valueOf(mediaItem.getFileId()))) {
            loadPeopleData(mediaItem);
        }
    }

    private void loadPeopleData(MediaItem mediaItem) {
        PeopleDataManager.load(mediaItem.getFileId(), new A(26, (Object) this, (Object) mediaItem));
    }

    public PeopleData find(View view, MediaItem mediaItem) {
        List<PeopleData> peopleData;
        if (mediaItem == null || view == null || view.getWidth() <= 0 || (peopleData = getPeopleData(mediaItem)) == null || peopleData.isEmpty()) {
            return null;
        }
        for (PeopleData next : peopleData) {
            RectF displayRectF = getDisplayRectF(view, mediaItem);
            next.setFaceRectF(displayRectF, mediaItem.getOrientation());
            if (intersect(displayRectF, next.getFaceRectF())) {
                return next;
            }
        }
        return null;
    }

    public void prepare(MediaItem mediaItem) {
        if (mediaItem != null && mediaItem.isImage() && !this.mPeopleData.containsKey(Long.valueOf(mediaItem.getFileId()))) {
            if (this.mRequests.size() > 0) {
                this.mRequests.pop();
            }
            this.mRequests.push(Long.valueOf(mediaItem.getFileId()));
            ThreadUtil.postOnUiThreadDelayed(new a(25, this, mediaItem), 30);
        }
    }
}
