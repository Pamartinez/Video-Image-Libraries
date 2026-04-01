package com.samsung.android.gallery.widget.previewable;

import android.graphics.ImageDecoder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements ImageDecoder.OnHeaderDecodedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ PreviewGif f3220a;

    public /* synthetic */ c(PreviewGif previewGif) {
        this.f3220a = previewGif;
    }

    public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
        this.f3220a.lambda$startPreview$1(imageDecoder, imageInfo, source);
    }
}
