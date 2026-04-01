package com.samsung.android.gallery.plugins.filebrowser;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ j(Object obj, FileInfo fileInfo, Object obj2, int i2) {
        this.d = i2;
        this.f = obj;
        this.e = fileInfo;
        this.g = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                FileListAdapter fileListAdapter = (FileListAdapter) this.g;
                ((FileListFragment) this.f).lambda$update$8(fileListAdapter, (FileInfo) this.e);
                return;
            case 1:
                ((FilePreviewFragment) this.f).lambda$saveZipFile$2((FileInfo) this.e, (String) this.g);
                return;
            case 2:
                ((FileViewHolder) this.f).lambda$bindBitmap$5((FileInfo) this.e, (BitmapHolder) this.g);
                return;
            default:
                ((OnBackPressCompat) this.f).lambda$onFragmentDetached$2((FragmentManager) this.g, (Fragment) this.e);
                return;
        }
    }

    public /* synthetic */ j(Object obj, Object obj2, Object obj3, int i2) {
        this.d = i2;
        this.f = obj;
        this.g = obj2;
        this.e = obj3;
    }
}
