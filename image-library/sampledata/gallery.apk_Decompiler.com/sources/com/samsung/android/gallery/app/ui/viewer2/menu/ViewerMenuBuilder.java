package com.samsung.android.gallery.app.ui.viewer2.menu;

import A4.I;
import android.text.TextUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewerMenuBuilder {
    private String mLocation;
    private MediaItem mMediaItem;
    private boolean mMediaItemSet = false;
    private final ViewerMenuMap mViewerMenuMap;

    public ViewerMenuBuilder(ViewerMenuMap viewerMenuMap) {
        this.mViewerMenuMap = viewerMenuMap;
    }

    public static ViewerMenuBuilder create(ViewerMenuMap viewerMenuMap) {
        return new ViewerMenuBuilder(viewerMenuMap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$build$0(ViewerMenuItem viewerMenuItem) {
        return !viewerMenuItem.isValid(this.mMediaItem, this.mLocation);
    }

    public List<ViewerMenuItem> build() {
        if (!TextUtils.isEmpty(this.mLocation)) {
            ArrayList arrayList = new ArrayList(this.mViewerMenuMap.get(trimLocationKey(this.mLocation)));
            if (this.mMediaItemSet) {
                arrayList.removeIf(new I(9, this));
            }
            return arrayList;
        }
        throw new IllegalStateException("location empty");
    }

    public ViewerMenuBuilder setLocation(String str) {
        this.mLocation = str;
        return this;
    }

    public ViewerMenuBuilder setMediaItem(MediaItem mediaItem) {
        this.mMediaItem = mediaItem;
        this.mMediaItemSet = true;
        return this;
    }

    public String trimLocationKey(String str) {
        int lastIndexOf = str.lastIndexOf(47);
        int lastIndexOf2 = str.lastIndexOf("//");
        if (lastIndexOf < 0 || (lastIndexOf2 >= 0 && lastIndexOf == lastIndexOf2 + 1)) {
            return str;
        }
        return str.substring(0, lastIndexOf);
    }
}
