package com.samsung.android.gallery.plugins.filebrowser;

import androidx.fragment.app.FragmentActivity;
import com.samsung.android.gallery.plugins.filebrowser.LogViewFragment;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3094a;

    public /* synthetic */ e(int i2) {
        this.f3094a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f3094a) {
            case 0:
                return ((FragmentActivity) obj).getSupportFragmentManager().getFragments();
            case 1:
                return ((FolderInfo) obj).name;
            case 2:
                return ((FileInfo) obj).name;
            case 3:
                return ((LogViewFragment.LineViewAdapter) obj).getText();
            case 4:
                return ((String) obj).substring(1);
            default:
                return new FolderInfo((String) obj);
        }
    }
}
