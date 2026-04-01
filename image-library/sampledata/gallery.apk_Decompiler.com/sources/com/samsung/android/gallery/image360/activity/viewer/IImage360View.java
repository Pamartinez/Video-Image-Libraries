package com.samsung.android.gallery.image360.activity.viewer;

import android.content.Context;
import android.graphics.Bitmap;
import com.samsung.android.gallery.image360.engine.IGallery360Viewer;
import com.samsung.android.gallery.image360.widget.IImage360FastOptionView;
import java.util.concurrent.Callable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
interface IImage360View {
    void commitPlayBackFragment(int i2);

    void finishWithToast(int i2);

    Context getContext();

    IImage360FastOptionView getFastOptionView();

    IGallery360Viewer.SaveStatus getSaveStatus(String str);

    String getScreenId();

    void saveCaptureImage(String str, Callable<Void> callable);

    void update360Viewer(Bitmap bitmap, IGallery360Viewer.ViewMode viewMode);
}
