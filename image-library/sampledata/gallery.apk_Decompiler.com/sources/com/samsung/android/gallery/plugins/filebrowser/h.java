package com.samsung.android.gallery.plugins.filebrowser;

import com.samsung.android.gallery.plugins.filebrowser.LogViewFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ FileBaseFragment e;
    public final /* synthetic */ Object f;

    public /* synthetic */ h(FileBaseFragment fileBaseFragment, Object obj, int i2) {
        this.d = i2;
        this.e = fileBaseFragment;
        this.f = obj;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((FileListFragment) this.e).lambda$update$10((PathListAdapter) this.f);
                return;
            case 1:
                ((FileListFragment) this.e).lambda$onClick$18((FileViewHolder) this.f);
                return;
            default:
                ((LogViewFragment) this.e).lambda$onBindView$0((LogViewFragment.LineViewAdapter) this.f);
                return;
        }
    }
}
