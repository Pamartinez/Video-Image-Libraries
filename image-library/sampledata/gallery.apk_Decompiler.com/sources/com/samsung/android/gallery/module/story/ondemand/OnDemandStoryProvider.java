package com.samsung.android.gallery.module.story.ondemand;

import A.a;
import D3.j;
import E5.b;
import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import bc.C0584a;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SearchLog;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OnDemandStoryProvider implements StoryGenApi {
    public static final Uri URI = Uri.parse("content://com.samsung.storygenprovider");
    private final AtomicBoolean mCancelled = new AtomicBoolean();
    private final ContentResolver mResolver = AppResources.getAppContext().getContentResolver();
    private final String mTaskId = String.valueOf(System.currentTimeMillis());

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BundleBuilder {
        final Bundle bundle;

        public BundleBuilder(String str) {
            this(new Bundle(), str);
        }

        public Bundle build() {
            return this.bundle;
        }

        public BundleBuilder withFunc(String str) {
            this.bundle.putString("KEY_FUNCTION", str);
            return this;
        }

        public BundleBuilder withInterfaceFunction(String str) {
            this.bundle.putString("KEY_FUNCTION", "FUNC_INTERFACE");
            this.bundle.putString("KEY_TARGET_FUNCTION", str);
            return this;
        }

        public BundleBuilder(Bundle bundle2, String str) {
            this.bundle = bundle2;
            bundle2.putString("KEY_TASK_ID", str);
        }
    }

    private Bundle invokeProvider(String str, Bundle bundle) {
        try {
            return this.mResolver.call("com.samsung.storygenprovider", "storygen_ondemand", str, bundle);
        } catch (Exception e) {
            a.s(e, new StringBuilder("invokeProvider failed e="), "OnDemandStoryProvider");
            return null;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$cancel$0() {
        long currentTimeMillis = System.currentTimeMillis();
        invokeProvider((String) null, new BundleBuilder(this.mTaskId).withFunc("FUNC_CANCEL").build());
        a.A(new Object[]{this.mTaskId, Long.valueOf(currentTimeMillis)}, new StringBuilder(Contract.COMMAND_ID_CANCEL), "OnDemandStoryProvider");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setBlockListAtTrip$2(ArrayList arrayList, ArrayList arrayList2, long j2) {
        if (arrayList != null && !arrayList.isEmpty()) {
            Bundle build = new BundleBuilder(this.mTaskId).withFunc("FUNC_BLOCK_LIST_AT_TRAVEL").build();
            build.putBoolean("delete_story_type_1001", true);
            build.putIntegerArrayList("story_id_list", arrayList);
            invokeProvider((String) null, build);
        }
        if (arrayList2 != null && !arrayList2.isEmpty()) {
            Bundle build2 = new BundleBuilder(this.mTaskId).withFunc("FUNC_BLOCK_LIST_AT_TRAVEL").build();
            build2.putLongArray("secIds", arrayList2.stream().mapToLong(new b(12)).toArray());
            invokeProvider((String) null, build2);
        }
        Log.d("OnDemandStoryProvider", "setBlockListAtTrip" + Logger.vt(this.mTaskId, arrayList, Long.valueOf(j2)) + "");
    }

    public void cancel() {
        Log.d("OnDemandStoryProvider", Contract.COMMAND_ID_CANCEL, this.mTaskId);
        this.mCancelled.set(true);
        ThreadUtil.runOnBgThread(new C0584a(19, this));
    }

    public void generate(Bundle bundle, Consumer<OnDemandResultData> consumer) {
        if (isCancelled()) {
            Log.d("OnDemandStoryProvider", "generate canceled", this.mTaskId);
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        Bundle build = new BundleBuilder(bundle, this.mTaskId).withFunc("FUNC_GENERATE").build();
        OnDemandResultData onDemandResultData = new OnDemandResultData(invokeProvider((String) null, build));
        SearchLog.d("OnDemandStoryProvider", "ODS#generate" + Logger.vt(this.mTaskId, Boolean.valueOf(isCancelled()), Long.valueOf(currentTimeMillis)) + " IN=" + Logger.toString(build) + ", OUT=" + onDemandResultData.toDebugString());
        StringBuilder sb2 = new StringBuilder("generate");
        sb2.append(Logger.vt(this.mTaskId, Boolean.valueOf(isCancelled()), Long.valueOf(currentTimeMillis)));
        sb2.append("");
        Log.d("OnDemandStoryProvider", sb2.toString());
        if (!isCancelled()) {
            consumer.accept(onDemandResultData);
        }
    }

    public List<String> getQuerySuggestion(int i2) {
        List<String> list;
        if (isCancelled()) {
            return Collections.EMPTY_LIST;
        }
        Bundle build = new BundleBuilder(this.mTaskId).withFunc("FUNC_QUERY_SUGGESTION").build();
        build.putInt("KEY_QUERY_SUGGESTION_NUMBER", i2);
        Bundle invokeProvider = invokeProvider((String) null, build);
        if (invokeProvider != null) {
            list = invokeProvider.getStringArrayList("KEY_QUERY_SUGGESTION_RESULT");
        } else {
            list = Collections.EMPTY_LIST;
        }
        if (list != null) {
            return list;
        }
        return Collections.EMPTY_LIST;
    }

    public boolean isCancelled() {
        return this.mCancelled.get();
    }

    public boolean isOnDemandSupportLanguage() {
        long currentTimeMillis = System.currentTimeMillis();
        Bundle invokeProvider = invokeProvider((String) null, new BundleBuilder(this.mTaskId).withInterfaceFunction("isNowOnDemandStoryAvailable").build());
        boolean z = false;
        if (invokeProvider != null && invokeProvider.getBoolean("isNowOnDemandStoryAvailableRet0", false)) {
            z = true;
        }
        a.A(new Object[]{Locale.getDefault(), Boolean.valueOf(z), Long.valueOf(currentTimeMillis)}, new StringBuilder("isOnDemandSupportLanguage"), "OnDemandStoryProvider");
        return z;
    }

    public void setBlockListAtTrip(ArrayList<Integer> arrayList, ArrayList<Long> arrayList2) {
        SimpleThreadPool.getInstance().execute(new j((Object) this, (Object) arrayList, (Serializable) arrayList2, System.currentTimeMillis(), 7));
    }
}
