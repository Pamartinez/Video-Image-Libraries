package com.samsung.android.sum.core.filter;

import A9.a;
import com.samsung.android.gallery.settings.activity.e;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MediaFilterRetriever {
    private static final String TAG = Def.tagOf((Class<?>) MediaFilterRetriever.class);
    private final Map<Predictor, PredicateHandler> predictorMap = new HashMap();

    @FunctionalInterface
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface PredicateHandler {
        void onPredicate(MediaFilter mediaFilter, MediaFilter mediaFilter2);
    }

    @FunctionalInterface
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Predictor {
        boolean predicate(MediaFilter mediaFilter);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$retrieve$0(DecorateFilter decorateFilter, MediaFilter mediaFilter, Predictor predictor, PredicateHandler predicateHandler) {
        if (predictor.predicate(decorateFilter)) {
            predicateHandler.onPredicate(decorateFilter, mediaFilter);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$retrieve$1(ImgpDecorateFilter imgpDecorateFilter, MediaFilter mediaFilter, Predictor predictor, PredicateHandler predicateHandler) {
        if (predictor.predicate(imgpDecorateFilter)) {
            predicateHandler.onPredicate(imgpDecorateFilter, mediaFilter);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$retrieve$2(MediaFilterGroup mediaFilterGroup, MediaFilter mediaFilter) {
        mediaFilter.accept(this, mediaFilterGroup);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$retrieve$3(MediaFilterPlaceHolder mediaFilterPlaceHolder, MediaFilter mediaFilter, Predictor predictor, PredicateHandler predicateHandler) {
        if (predictor.predicate(mediaFilterPlaceHolder)) {
            predicateHandler.onPredicate(mediaFilterPlaceHolder, mediaFilter);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$retrieve$4(MediaFilter mediaFilter, MediaFilter mediaFilter2, Predictor predictor, PredicateHandler predicateHandler) {
        if (predictor.predicate(mediaFilter)) {
            predicateHandler.onPredicate(mediaFilter, mediaFilter2);
        }
    }

    public MediaFilterRetriever addPredicateHandler(Predictor predictor, PredicateHandler predicateHandler) {
        this.predictorMap.put(predictor, predicateHandler);
        return this;
    }

    public void retrieve(MediaFilter mediaFilter) {
        String str = TAG;
        SLog.d(str, "retrieve root mediaFilter: " + mediaFilter);
        retrieve(mediaFilter, (MediaFilter) null);
    }

    public void retrieve(DecorateFilter decorateFilter, MediaFilter mediaFilter) {
        String str = TAG;
        SLog.d(str, "retrieve DecorateFilter: " + decorateFilter);
        this.predictorMap.forEach(new a(16, decorateFilter, mediaFilter));
        if (decorateFilter.getSuccessorFilter() != null) {
            decorateFilter.getSuccessorFilter().accept(this, decorateFilter);
        }
    }

    public void retrieve(ImgpDecorateFilter imgpDecorateFilter, MediaFilter mediaFilter) {
        String str = TAG;
        SLog.d(str, "retrieve ImgpDecorateFilter: " + imgpDecorateFilter);
        this.predictorMap.forEach(new a(17, imgpDecorateFilter, mediaFilter));
        if (imgpDecorateFilter.getPreFilter() != null) {
            imgpDecorateFilter.getPreFilter().accept(this, imgpDecorateFilter);
        }
        if (imgpDecorateFilter.getPostFilter() != null) {
            imgpDecorateFilter.getPostFilter().accept(this, imgpDecorateFilter);
        }
        if (imgpDecorateFilter.getSuccessorFilter() != null) {
            imgpDecorateFilter.getSuccessorFilter().accept(this, imgpDecorateFilter);
        }
    }

    public void retrieve(MediaFilterGroup mediaFilterGroup, MediaFilter mediaFilter) {
        String str = TAG;
        SLog.d(str, "retrieve MediaFilterGroup: " + mediaFilterGroup);
        mediaFilterGroup.stream().forEach(new e(6, this, mediaFilterGroup));
    }

    public void retrieve(MediaFilterPlaceHolder mediaFilterPlaceHolder, MediaFilter mediaFilter) {
        String str = TAG;
        SLog.d(str, "retrieve MediaFilterPlaceHolder: " + mediaFilterPlaceHolder);
        this.predictorMap.forEach(new a(18, mediaFilterPlaceHolder, mediaFilter));
    }

    public void retrieve(MediaFilter mediaFilter, MediaFilter mediaFilter2) {
        if (mediaFilter instanceof ImgpDecorateFilter) {
            retrieve((ImgpDecorateFilter) mediaFilter, mediaFilter2);
        } else if (mediaFilter instanceof DecorateFilter) {
            retrieve((DecorateFilter) mediaFilter, mediaFilter2);
        } else if (mediaFilter instanceof MediaFilterGroup) {
            retrieve((MediaFilterGroup) mediaFilter, mediaFilter2);
        } else if (mediaFilter instanceof MediaFilterPlaceHolder) {
            retrieve((MediaFilterPlaceHolder) mediaFilter, mediaFilter2);
        } else {
            String str = TAG;
            SLog.d(str, "retrieve MediaFilter: " + mediaFilter);
            this.predictorMap.forEach(new a(15, mediaFilter, mediaFilter2));
        }
    }
}
