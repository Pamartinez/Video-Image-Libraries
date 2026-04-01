package com.samsung.android.sdk.scs.ai.suggestion;

import android.content.Context;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FolderNameSuggester {
    private static final String TAG = "FolderNameSuggester";
    private Context mContext;
    private SuggestionProviderExecutor mProviderExecutor;

    public FolderNameSuggester(Context context) {
        this.mContext = context;
        this.mProviderExecutor = new SuggestionProviderExecutor(context);
    }

    public Task<List<String>> suggest(List<String> list) {
        Log.d(TAG, "suggest() executed");
        FolderNameSuggestionRunnable folderNameSuggestionRunnable = new FolderNameSuggestionRunnable(this.mContext);
        folderNameSuggestionRunnable.setPackageNames(list);
        this.mProviderExecutor.execute(folderNameSuggestionRunnable);
        return folderNameSuggestionRunnable.getTask();
    }
}
