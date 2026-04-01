package com.samsung.android.gallery.app.ui.viewer2.aiedit.items;

import A8.C0545b;
import Ad.C0720a;
import B8.j;
import O9.a;
import android.graphics.Bitmap;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.aiedit.AiEditType;
import com.samsung.android.gallery.module.aiedit.RemasterDetector;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.CodecCompat;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import g7.C0463f;
import h4.C0464a;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterAiEditComposite extends AiEditItem {
    private final ArrayList<AbsRemasterAiEdit> mDetectedRemasterList = new ArrayList<>();
    private final ArrayList<AbsRemasterAiEdit> mImageRemasterList = new ArrayList<>();

    public RemasterAiEditComposite(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, AiEditType.None);
        createImageRemasterList(eventContext, actionInvoker);
        createDetectedList(eventContext, actionInvoker);
    }

    private void createDetectedList(EventContext eventContext, ActionInvoker actionInvoker) {
        if (RemasterDetector.SUPPORT) {
            this.mDetectedRemasterList.add(new RemasterMoireAiEdit(eventContext, actionInvoker));
            this.mDetectedRemasterList.add(new RemasterShadowAiEdit(eventContext, actionInvoker));
            this.mDetectedRemasterList.add(new RemasterReflectionAiEdit(eventContext, actionInvoker));
            if (RemasterDetector.SUPPORT_FLARE_DISTORTION) {
                this.mDetectedRemasterList.add(new RemasterDistortionAiEdit(eventContext, actionInvoker));
                this.mDetectedRemasterList.add(new RemasterFlareAiEdit(eventContext, actionInvoker));
            }
            if (LiveEffectAiEdit.SUPPORT) {
                this.mDetectedRemasterList.add(new LiveEffectAiEdit(eventContext, actionInvoker));
            }
            if (RemasterDetector.SUPPORT_REMASTER_DETECT) {
                this.mDetectedRemasterList.add(new RemasterAiEdit(eventContext, actionInvoker));
                this.mDetectedRemasterList.add(new RemasterGifAiEdit(eventContext, actionInvoker));
            }
            if (RemasterDetector.SUPPORT_UNIFIED && (SdkConfig.lessThan(SdkConfig.SEM.B_MR5) || Features.isEnabled(Features.SUPPORT_AWESOME_INTELLIGENT_FEATURES))) {
                this.mDetectedRemasterList.add(new RemasterPortraitAiEdit(eventContext, actionInvoker));
            }
            if (RemasterDetector.SUPPORT_BEST_FACE) {
                this.mDetectedRemasterList.add(new RemasterBestFaceAiEdit(eventContext, actionInvoker));
            }
        }
    }

    private void createImageRemasterList(EventContext eventContext, ActionInvoker actionInvoker) {
        if (!RemasterDetector.SUPPORT_REMASTER_DETECT) {
            this.mImageRemasterList.add(new RemasterAiEdit(eventContext, actionInvoker));
            if (Features.isEnabled(Features.SUPPORT_ON_DEMAND_REMASTER_GIF)) {
                this.mImageRemasterList.add(new RemasterGifAiEdit(eventContext, actionInvoker));
            }
        }
    }

    private ArrayList<AiEditItem> getDetectedResultList(MediaItem mediaItem) {
        ArrayList arrayList = (ArrayList) this.mDetectedRemasterList.stream().filter(new C0464a(2)).filter(new j(mediaItem, 9)).collect(Collectors.toCollection(new C0720a(1)));
        if (arrayList.isEmpty()) {
            return new ArrayList<>();
        }
        ArrayList<AiEditItem> arrayList2 = new ArrayList<>();
        ArrayList<String> detectImage = RemasterDetector.getInstance().detectImage(mediaItem, (ArrayList) arrayList.stream().mapToInt(new C0545b(26)).boxed().collect(Collectors.toCollection(new C0720a(1))));
        if (!detectImage.isEmpty()) {
            arrayList.stream().filter(new a(detectImage, 1)).forEach(new f4.a(arrayList2, 14));
        }
        return arrayList2;
    }

    private ArrayList<AiEditItem> getGeneralImageResultList(MediaItem mediaItem) {
        ArrayList<AiEditItem> arrayList = new ArrayList<>();
        this.mImageRemasterList.stream().filter(new j(mediaItem, 8)).forEach(new f4.a(arrayList, 14));
        return arrayList;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getDetectedResultList$2(AbsRemasterAiEdit absRemasterAiEdit) {
        if (absRemasterAiEdit.getDetectionType() >= 0) {
            return true;
        }
        return false;
    }

    public AiEditItem getLoadableItem(AiEditType aiEditType) {
        if (!this.mImageRemasterList.stream().anyMatch(new C0463f(aiEditType, 1)) && !this.mDetectedRemasterList.stream().anyMatch(new C0463f(aiEditType, 2))) {
            return null;
        }
        return this;
    }

    public List<AiEditItem> load(MediaItem mediaItem, Bitmap bitmap) {
        if (mediaItem.isCloudOnly() || !CodecCompat.ensureJpegSyntaxCompatible(mediaItem)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(getDetectedResultList(mediaItem));
        arrayList.addAll(getGeneralImageResultList(mediaItem));
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }
}
