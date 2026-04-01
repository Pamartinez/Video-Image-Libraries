package com.samsung.android.gallery.app.ui.viewer2.contentviewer;

import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import java.io.PrintWriter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IViewerObject {
    void attachActionInvoker(ActionInvoker actionInvoker);

    void bindContentModel(ContentModel contentModel);

    void addActionInvokeListener() {
    }

    void initialize() {
    }

    void onFinalized() {
    }

    void onInitialized() {
    }

    void onDump(PrintWriter printWriter, String str) {
    }
}
