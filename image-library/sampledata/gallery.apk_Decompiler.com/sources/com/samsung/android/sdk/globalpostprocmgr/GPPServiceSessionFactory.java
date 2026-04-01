package com.samsung.android.sdk.globalpostprocmgr;

import android.content.Context;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GPPServiceSessionFactory {
    private Context mContext;

    public GPPServiceSession build() {
        return new GPPServiceSession(this.mContext);
    }

    public GPPServiceSessionFactory setContext(Context context) {
        this.mContext = context;
        return this;
    }

    public GPPServiceSessionFactory setProperty() {
        return this;
    }
}
