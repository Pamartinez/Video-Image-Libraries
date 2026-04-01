package com.samsung.o3dp.lib3dphotography.utils;

import android.graphics.ImageDecoder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ImageDecoder.OnHeaderDecodedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BitmapBuilder f4213a;

    public /* synthetic */ a(BitmapBuilder bitmapBuilder) {
        this.f4213a = bitmapBuilder;
    }

    public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
        this.f4213a.lambda$retreiveBitmap$0(imageDecoder, imageInfo, source);
    }
}
