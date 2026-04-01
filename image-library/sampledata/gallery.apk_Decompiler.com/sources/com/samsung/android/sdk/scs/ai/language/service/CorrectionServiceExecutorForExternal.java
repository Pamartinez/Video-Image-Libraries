package com.samsung.android.sdk.scs.ai.language.service;

import android.content.Context;
import android.content.Intent;
import com.samsung.android.sdk.scs.base.utils.BaseConstants;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CorrectionServiceExecutorForExternal extends CorrectionServiceExecutor {
    public CorrectionServiceExecutorForExternal(Context context) {
        super(context);
    }

    public Intent getServiceIntent() {
        return C0280e.a(BaseConstants.SERVICE_ACTION.ACTION_CORRECTION_SERVICE_FOR_EXTERNAL, BaseConstants.PACKAGE_INFO.PACKAGE_SIVS_SERVICES);
    }
}
