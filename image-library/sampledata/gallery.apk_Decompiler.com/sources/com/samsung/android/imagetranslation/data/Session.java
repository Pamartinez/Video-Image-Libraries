package com.samsung.android.imagetranslation.data;

import android.content.Context;
import com.samsung.android.imagetranslation.LttEngineListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Session {
    private Context context;
    private boolean isInpainterModuleEnabled;
    private LttEngineListener lttEngineListener;

    public Session(Context context2, LttEngineListener lttEngineListener2) {
        this.isInpainterModuleEnabled = true;
        this.context = context2;
        this.lttEngineListener = lttEngineListener2;
    }

    public Context getContext() {
        return this.context;
    }

    public LttEngineListener getLttEngineListener() {
        return this.lttEngineListener;
    }

    public boolean isInpainterModuleEnabled() {
        return this.isInpainterModuleEnabled;
    }

    public void setContext(Context context2) {
        this.context = context2;
    }

    public void setInpainterModuleEnabled(boolean z) {
        this.isInpainterModuleEnabled = z;
    }

    public void setLttEngineListener(LttEngineListener lttEngineListener2) {
        this.lttEngineListener = lttEngineListener2;
    }

    public Session(Context context2, LttEngineListener lttEngineListener2, boolean z) {
        this.context = context2;
        this.lttEngineListener = lttEngineListener2;
        this.isInpainterModuleEnabled = z;
    }
}
