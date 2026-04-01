package com.samsung.android.gallery.module.publisher;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiConsumer;

/* renamed from: com.samsung.android.gallery.module.publisher.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0623c implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AlbumDataPublisher f3063a;
    public final /* synthetic */ Bundle b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ HashMap f3064c;
    public final /* synthetic */ ArrayList d;

    public /* synthetic */ C0623c(AlbumDataPublisher albumDataPublisher, Bundle bundle, HashMap hashMap, ArrayList arrayList) {
        this.f3063a = albumDataPublisher;
        this.b = bundle;
        this.f3064c = hashMap;
        this.d = arrayList;
    }

    public final void accept(Object obj, Object obj2) {
        this.f3063a.lambda$publishMxAlbumsFileData$13(this.b, this.f3064c, this.d, (String) obj, (int[]) obj2);
    }
}
