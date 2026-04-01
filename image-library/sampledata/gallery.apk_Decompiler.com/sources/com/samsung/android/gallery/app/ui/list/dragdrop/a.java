package com.samsung.android.gallery.app.ui.list.dragdrop;

import android.content.ClipData;
import com.samsung.android.gallery.app.controller.album.SaveObjectCaptureToAlbumCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.dragdrop.NormalDragAndDrop;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements NormalDragAndDrop.OnDataCompletionListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2535a;
    public final /* synthetic */ Object b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ IBaseListView f2536c;
    public final /* synthetic */ ClipData d;

    public /* synthetic */ a(IBaseListView iBaseListView, ClipData clipData, MediaItem mediaItem) {
        this.f2535a = 2;
        this.f2536c = iBaseListView;
        this.d = clipData;
        this.b = mediaItem;
    }

    public final void onDataCompleted() {
        switch (this.f2535a) {
            case 0:
                ((NormalDragAndDrop) this.b).lambda$handleDrop$1(this.f2536c, this.d);
                return;
            case 1:
                ((NormalDragAndDrop) this.b).lambda$handleDrop$2(this.f2536c, this.d);
                return;
            default:
                new SaveObjectCaptureToAlbumCmd().execute(this.f2536c.getPresenter(), this.d, (MediaItem) this.b);
                return;
        }
    }

    public /* synthetic */ a(NormalDragAndDrop normalDragAndDrop, IBaseListView iBaseListView, ClipData clipData, int i2) {
        this.f2535a = i2;
        this.b = normalDragAndDrop;
        this.f2536c = iBaseListView;
        this.d = clipData;
    }
}
