package com.samsung.android.gallery.app.ui.list.search.pictures.headerview;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureHeaderView;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.animation.FaceClusterAnimationHelper;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements FaceClusterAnimationHelper.MergeCompletedAnimationListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SearchCreatureHeaderView.AnonymousClass2 f2543a;
    public final /* synthetic */ MediaItem b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Bitmap f2544c;

    public /* synthetic */ c(SearchCreatureHeaderView.AnonymousClass2 r1, MediaItem mediaItem, Bitmap bitmap) {
        this.f2543a = r1;
        this.b = mediaItem;
        this.f2544c = bitmap;
    }

    public final void a() {
        this.f2543a.lambda$onCompletedMerge$0(this.b, this.f2544c);
    }
}
