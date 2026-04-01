package com.samsung.android.gallery.app.ui.list.stories.pictures.header;

import com.samsung.android.gallery.app.ui.list.stories.pictures.header.HeaderSimpleData;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ HeaderSimpleData d;
    public final /* synthetic */ HeaderSimpleData.LocationBuilder e;
    public final /* synthetic */ HeaderSimpleData.MediaCountBuilder f;

    public /* synthetic */ a(HeaderSimpleData headerSimpleData, HeaderSimpleData.LocationBuilder locationBuilder, HeaderSimpleData.MediaCountBuilder mediaCountBuilder) {
        this.d = headerSimpleData;
        this.e = locationBuilder;
        this.f = mediaCountBuilder;
    }

    public final void run() {
        this.d.lambda$reloadData$0(this.e, this.f);
    }
}
