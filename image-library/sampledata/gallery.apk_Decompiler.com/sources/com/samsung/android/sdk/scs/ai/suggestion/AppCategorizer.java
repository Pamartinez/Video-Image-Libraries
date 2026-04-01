package com.samsung.android.sdk.scs.ai.suggestion;

import android.content.Context;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AppCategorizer {
    private static final String TAG = "AppCategorizer";
    private Context mContext;
    private SuggestionProviderExecutor mProviderExecutor;

    public AppCategorizer(Context context) {
        this.mContext = context;
        this.mProviderExecutor = new SuggestionProviderExecutor(context);
    }

    public Task<List<String>> getAppCategories(List<String> list) {
        Log.i(TAG, "getAppCategories() executed");
        AppCategorizationRunnable appCategorizationRunnable = new AppCategorizationRunnable(this.mContext);
        appCategorizationRunnable.setPackageNames(list);
        this.mProviderExecutor.execute(appCategorizationRunnable);
        return appCategorizationRunnable.getTask();
    }

    public Task<List<AppCategoryResult>> getAppCategoryDetails(List<String> list) {
        Log.i(TAG, "getAppCategoryDetails() executed");
        AppCategoryDetailsRunnable appCategoryDetailsRunnable = new AppCategoryDetailsRunnable(this.mContext);
        appCategoryDetailsRunnable.setPackageNames(list);
        this.mProviderExecutor.execute(appCategoryDetailsRunnable);
        return appCategoryDetailsRunnable.getTask();
    }
}
