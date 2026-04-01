package com.samsung.android.sdk.scs.ai.visual;

import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import com.samsung.android.gallery.settings.activity.e;
import com.samsung.android.sdk.scs.ai.visual.ImageEditorParam;
import java.util.ArrayList;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MultiModalParam {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ContentDataBuilder {
        private final Bundle mParam = new Bundle();

        public Bundle build() {
            return this.mParam;
        }

        public ContentDataBuilder contentName(String str) {
            this.mParam.putString("cdContentName", str);
            return this;
        }

        public ContentDataBuilder image(ParcelFileDescriptor parcelFileDescriptor) {
            this.mParam.putParcelable("cdPfd", parcelFileDescriptor);
            return this;
        }

        public ContentDataBuilder mimeType(String str) {
            this.mParam.putString("cdMimeType", str);
            return this;
        }

        public ContentDataBuilder text(String str) {
            this.mParam.putString("cdText", str);
            return this;
        }

        public ContentDataBuilder type(String str) {
            this.mParam.putString("cdType", str);
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DesignImageModeBuilder extends ImageEditorParam.Builder {
        private final Bundle mDesignImage;

        public DesignImageModeBuilder(ImageEditorParam.Builder builder) {
            super(builder.params);
            Bundle bundle = new Bundle();
            this.mDesignImage = bundle;
            this.params.putString("mmRequestType", "designImage");
            this.params.putBundle("mmDesignImage", bundle);
        }

        public DesignImageModeBuilder category(String str) {
            this.mDesignImage.putString("designImageCategory", str);
            return this;
        }

        public DesignImageModeBuilder imageParam(Bundle bundle) {
            this.mDesignImage.putBundle("designImageImageParam", bundle);
            return this;
        }

        public DesignImageModeBuilder inputTypes(ArrayList<String> arrayList) {
            this.mDesignImage.putStringArrayList("designImageInputTypes", arrayList);
            return this;
        }

        public DesignImageModeBuilder style(String str) {
            this.mDesignImage.putString("designImageStyle", str);
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DesignStickerModeBuilder extends ImageEditorParam.Builder {
        private final Bundle mDesignSticker;

        public DesignStickerModeBuilder(ImageEditorParam.Builder builder) {
            super(builder.params);
            Bundle bundle = new Bundle();
            this.mDesignSticker = bundle;
            this.params.putString("mmRequestType", "designSticker");
            this.params.putBundle("mmDesignSticker", bundle);
        }

        public DesignStickerModeBuilder category(String str) {
            this.mDesignSticker.putString("designStickerCategory", str);
            return this;
        }

        public DesignStickerModeBuilder imageParam(Bundle bundle) {
            this.mDesignSticker.putBundle("designStickerImageParam", bundle);
            return this;
        }

        public DesignStickerModeBuilder inputTypes(ArrayList<String> arrayList) {
            this.mDesignSticker.putStringArrayList("designStickerInputTypes", arrayList);
            return this;
        }

        public DesignStickerModeBuilder style(String str) {
            this.mDesignSticker.putString("designStickerStyle", str);
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MultiModalModeBuilder extends ImageEditorParam.Builder {
        public MultiModalModeBuilder(ImageEditorParam.Builder builder) {
            super(builder.params);
        }

        public DesignImageModeBuilder asDesignImageMode() {
            return new DesignImageModeBuilder(this);
        }

        public DesignStickerModeBuilder asDesignStickerMode() {
            return new DesignStickerModeBuilder(this);
        }

        public MultiModalModeBuilder contentData(ArrayList<Bundle> arrayList) {
            this.params.putParcelableArrayList("mmContentList", arrayList);
            return this;
        }

        public MultiModalModeBuilder outputs(ArrayList<ParcelFileDescriptor> arrayList) {
            this.params.putParcelableArrayList("outputPfdList", arrayList);
            return this;
        }

        public MultiModalModeBuilder promptParam(Bundle bundle) {
            this.params.putBundle("mmPromptParam", bundle);
            return this;
        }

        public MultiModalModeBuilder requestType(String str) {
            this.params.putString("mmRequestType", str);
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PromptParamBuilder {
        private final Bundle mParam = new Bundle();

        public Bundle build() {
            return this.mParam;
        }

        public PromptParamBuilder param(Map<String, String> map) {
            if (map == null) {
                this.mParam.putBundle("ppParam", (Bundle) null);
                return this;
            }
            Bundle bundle = new Bundle();
            map.keySet().forEach(new e(1, bundle, map));
            this.mParam.putBundle("ppParam", bundle);
            return this;
        }
    }
}
