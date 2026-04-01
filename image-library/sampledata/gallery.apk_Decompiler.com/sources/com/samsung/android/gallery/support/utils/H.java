package com.samsung.android.gallery.support.utils;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.share.ShareSheetDelegate;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.imagetranslation.data.LttOcrResult;
import com.samsung.android.imagetranslation.util.OcrDataToSceneTextConverter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class H implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3163a;
    public final /* synthetic */ int b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f3164c;
    public final /* synthetic */ Object d;

    public /* synthetic */ H(int i2, MediaItem mediaItem, MediaData mediaData) {
        this.f3163a = 3;
        this.b = i2;
        this.f3164c = mediaItem;
        this.d = mediaData;
    }

    public final Object apply(Object obj) {
        switch (this.f3163a) {
            case 0:
                return ((PackageMonitorCompat) this.f3164c).lambda$getApplicationInfo$0((String) this.d, this.b, (String) obj);
            case 1:
                return ((OcrDataToSceneTextConverter) this.f3164c).lambda$convertStLines$3((ArrayList) this.d, this.b, (LttOcrResult.LineInfo) obj);
            case 2:
                return ((com.samsung.android.livetranslation.util.OcrDataToSceneTextConverter) this.f3164c).lambda$convertStLines$3((ArrayList) this.d, this.b, (LttOcrResult.LineInfo) obj);
            default:
                return ShareSheetDelegate.lambda$onPrepareShareSheet$1(this.b, (MediaItem) this.f3164c, (MediaData) this.d, (Integer) obj);
        }
    }

    public /* synthetic */ H(Object obj, Serializable serializable, int i2, int i7) {
        this.f3163a = i7;
        this.f3164c = obj;
        this.d = serializable;
        this.b = i2;
    }
}
