package com.samsung.android.gallery.module.lottie.recap.template.element;

import com.samsung.android.gallery.module.lottie.recap.binder.RecapDataVars;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import y5.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapTemplateScene {
    private static AtomicInteger sCounter = new AtomicInteger();
    public HashMap<String, RecapBgLayer> mBackgrounds = new HashMap<>();
    public HashMap<String, RecapTextLayer> mDynamicText = new HashMap<>();
    public HashMap<String, RecapImage> mImages = new HashMap<>();
    final int mSceneIndex;
    String mSceneName;
    public HashMap<String, RecapTextLayer> mTexts = new HashMap<>();
    Type mType;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Type {
        OPENING,
        BUILD_UP,
        MAIN,
        KEY_MOMENTS,
        CLIMAX,
        CLOSING
    }

    public RecapTemplateScene(Type type, String str) {
        this.mType = type;
        this.mSceneName = str;
        this.mSceneIndex = sCounter.incrementAndGet();
    }

    private static void validate(RecapTextLayer recapTextLayer) {
        RecapDataVars recapDataVars = recapTextLayer.variables;
        if (recapDataVars != null && recapDataVars.isPeople()) {
            if (recapTextLayer.fontSp == 0) {
                throw new IllegalArgumentException("have to set font sp for people : " + recapTextLayer.dataBindingKey);
            } else if (recapTextLayer.maxWidthDp == 0) {
                throw new IllegalArgumentException("have to set maxWidth for people : " + recapTextLayer.dataBindingKey);
            }
        }
    }

    public RecapTemplateScene addBg(RecapBgLayer recapBgLayer) {
        this.mBackgrounds.put(recapBgLayer.dataBindingKey, recapBgLayer);
        return this;
    }

    public RecapTemplateScene addImage(RecapImage recapImage) {
        int size = this.mImages.size();
        this.mImages.put(recapImage.dataBindingKey, recapImage);
        recapImage.sceneName = this.mSceneName;
        recapImage.sceneIndex = this.mSceneIndex;
        recapImage.targetImage(size);
        return this;
    }

    public RecapTemplateScene addText(RecapTextLayer recapTextLayer) {
        this.mTexts.put(recapTextLayer.dataBindingKey, recapTextLayer);
        if (recapTextLayer.fixedSat != 0.0f) {
            this.mDynamicText.put(recapTextLayer.dataBindingKey, recapTextLayer);
        }
        recapTextLayer.parent = this;
        validate(recapTextLayer);
        return this;
    }

    public RecapTemplateScene setAllImageType(RecapImage.TargetCandidate targetCandidate) {
        this.mImages.values().forEach(new a(2, targetCandidate));
        return this;
    }
}
