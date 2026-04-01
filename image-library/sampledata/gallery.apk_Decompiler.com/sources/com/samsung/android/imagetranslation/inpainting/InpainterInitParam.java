package com.samsung.android.imagetranslation.inpainting;

import android.content.Context;
import com.samsung.android.imagetranslation.TaskListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class InpainterInitParam {
    private final Context m_context;
    private final TaskListener m_taskListener;

    public InpainterInitParam(Context context, TaskListener taskListener) {
        this.m_context = context;
        this.m_taskListener = taskListener;
    }

    public Context getContext() {
        return this.m_context;
    }

    public TaskListener get_taskListener() {
        return this.m_taskListener;
    }
}
