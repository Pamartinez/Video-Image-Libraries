package com.samsung.android.vexfwk.sdk.common.runtime;

import Ae.b;
import android.graphics.Bitmap;
import android.media.Image;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a1\u0010\u0006\u001a\u00028\u0000\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\u0004\b\u0001\u0010\u0002*\u00028\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00028\u0001¢\u0006\u0004\b\u0006\u0010\u0007\u001a+\u0010\u0006\u001a\u00028\u0000\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u00028\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u0006\u0010\n\u001a+\u0010\u0006\u001a\u00028\u0000\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u00028\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u0006\u0010\r\u001a#\u0010\u0006\u001a\u00028\u0000\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u00028\u00002\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u000e\u001a1\u0010\u000f\u001a\u00028\u0000\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\u0004\b\u0001\u0010\u0002*\u00028\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00028\u0001¢\u0006\u0004\b\u000f\u0010\u0007\u001a+\u0010\u000f\u001a\u00028\u0000\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u00028\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u000f\u0010\n\u001a+\u0010\u000f\u001a\u00028\u0000\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u00028\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000f\u0010\r\u001a#\u0010\u000f\u001a\u00028\u0000\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u00028\u00002\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u000f\u0010\u000e\u001a/\u0010\u0014\u001a\u00028\u0000\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u00028\u00002\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010¢\u0006\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest$Builder;", "T", "B", "", "streamId", "buffer", "addInputBuffer", "(Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest$Builder;ILjava/lang/Object;)Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest$Builder;", "Landroid/media/Image;", "image", "(Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest$Builder;ILandroid/media/Image;)Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest$Builder;", "Landroid/graphics/Bitmap;", "bitmap", "(Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest$Builder;ILandroid/graphics/Bitmap;)Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest$Builder;", "(Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest$Builder;I)Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest$Builder;", "addOutputBuffer", "Lkotlin/Function1;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;", "Lme/x;", "action", "setSettingMetadata", "(Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest$Builder;LAe/b;)Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionRequest$Builder;", "VexFrameworkSDK_forInternalRelease"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkSessionRequestKt {
    public static final <T extends VexFwkSessionRequest.Builder, B> T addInputBuffer(T t, int i2, B b) {
        j.e(t, "<this>");
        t.getInputBuffers().add(VexFwkBuffer.Companion.from(i2, b));
        return t;
    }

    public static final <T extends VexFwkSessionRequest.Builder, B> T addOutputBuffer(T t, int i2, B b) {
        j.e(t, "<this>");
        t.getOutputBuffers().add(VexFwkBuffer.Companion.from(i2, b));
        return t;
    }

    public static final <T extends VexFwkSessionRequest.Builder> T setSettingMetadata(T t, b bVar) {
        j.e(t, "<this>");
        j.e(bVar, "action");
        VexFwkMetadataNative vexFwkMetadataNative = new VexFwkMetadataNative();
        bVar.invoke(vexFwkMetadataNative);
        t.setSettingMetadata(vexFwkMetadataNative);
        return t;
    }

    public static final <T extends VexFwkSessionRequest.Builder> T addInputBuffer(T t, int i2, Image image) {
        j.e(t, "<this>");
        j.e(image, "image");
        t.getInputBuffers().add(VexFwkBuffer.Companion.from(i2, image));
        return t;
    }

    public static final <T extends VexFwkSessionRequest.Builder> T addOutputBuffer(T t, int i2, Image image) {
        j.e(t, "<this>");
        j.e(image, "image");
        t.getOutputBuffers().add(VexFwkBuffer.Companion.from(i2, image));
        return t;
    }

    public static final <T extends VexFwkSessionRequest.Builder> T addInputBuffer(T t, int i2, Bitmap bitmap) {
        j.e(t, "<this>");
        j.e(bitmap, "bitmap");
        t.getInputBuffers().add(VexFwkBuffer.Companion.from(i2, bitmap));
        return t;
    }

    public static final <T extends VexFwkSessionRequest.Builder> T addOutputBuffer(T t, int i2, Bitmap bitmap) {
        j.e(t, "<this>");
        j.e(bitmap, "bitmap");
        t.getOutputBuffers().add(VexFwkBuffer.Companion.from(i2, bitmap));
        return t;
    }

    public static final <T extends VexFwkSessionRequest.Builder> T addInputBuffer(T t, int i2) {
        j.e(t, "<this>");
        t.getInputBuffers().add(VexFwkBuffer.Companion.from(i2));
        return t;
    }

    public static final <T extends VexFwkSessionRequest.Builder> T addOutputBuffer(T t, int i2) {
        j.e(t, "<this>");
        t.getOutputBuffers().add(VexFwkBuffer.Companion.from(i2));
        return t;
    }
}
