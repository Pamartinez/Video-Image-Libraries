package com.samsung.android.gallery.module.search.autocomplete;

import M4.d;
import N2.j;
import R6.c;
import V9.a;
import android.database.Cursor;
import android.os.CancellationSignal;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.module.search.engine.SearchEngineFactory;
import com.samsung.android.gallery.module.search.root.AllScreenshotFilterResultsEntity;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.type.IntelligentSearchIndexField;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ScsAutoCompleteDataLoader extends AutoCompleteDataLoader {
    private final LinkedBlockingQueue<CancellationSignal> mCancelSignals = new LinkedBlockingQueue<>();
    private Future<?> mLastRequestedTask;

    private void addCancelSignal(CancellationSignal cancellationSignal) {
        this.mCancelSignals.add(cancellationSignal);
    }

    private SearchFilter buildSearchFilter(String str, String str2, CancellationSignal cancellationSignal, boolean z) {
        SearchFilter.Builder cancellationSignal2 = new SearchFilter.Builder(str2).setFilterEnable(false).setCancellationSignal(cancellationSignal);
        String argValue = ArgumentsUtil.getArgValue(str, "SelectedFilter", (String) null);
        if (argValue != null) {
            cancellationSignal2.selectedFilter(argValue);
        }
        String argValue2 = ArgumentsUtil.getArgValue(str, "term", (String) null);
        if (z && argValue2 != null) {
            if ("all_screenshot".equals(argValue2)) {
                AllScreenshotFilterResultsEntity allScreenshotFilterResultsEntity = new AllScreenshotFilterResultsEntity("all");
                cancellationSignal2.setMainEntityInfo(new String[]{allScreenshotFilterResultsEntity.getSelection(), TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, allScreenshotFilterResultsEntity.getRawLabelsList())});
            } else {
                cancellationSignal2.setMainEntityInfo(new String[]{argValue2.concat(" = ?"), getSub(str, argValue2)});
            }
        }
        return cancellationSignal2.build(AppResources.getAppContext());
    }

    private CancellationSignal createCancelSignal(String str) {
        CancellationSignal cancellationSignal = new CancellationSignal();
        cancellationSignal.setOnCancelListener(new a(this, str));
        return cancellationSignal;
    }

    private String getSub(String str, String str2) {
        String argValue = ArgumentsUtil.getArgValue(str, "sub", (String) null);
        if (IntelligentSearchIndexField.CREATURE_TERM.contains(str2)) {
            return String.valueOf(Long.valueOf(IdentityCreatureUtil.getUnifiedIdentityId(argValue)));
        }
        return argValue;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createCancelSignal$0(String str) {
        String str2 = this.TAG;
        Log.e(str2, " AutoComplete query is canceled. [" + Logger.getEncodedString(str) + "]");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadInBackground$1(SearchFilter searchFilter, BiConsumer biConsumer) {
        Cursor searchAutoComplete;
        try {
            searchAutoComplete = SearchEngineFactory.create(AppResources.getAppContext()).searchAutoComplete(searchFilter);
            if (TextUtils.equals(this.mLastRequestedKeyword, searchFilter.getRawKeyword()) && !Thread.currentThread().isInterrupted()) {
                onLoadDone(biConsumer, searchAutoComplete);
            }
            if (searchAutoComplete != null) {
                searchAutoComplete.close();
                return;
            }
            return;
        } catch (Exception e) {
            j.v("AutoComplete : ", e, this.TAG);
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private Future<?> loadInBackground(BiConsumer<ArrayList<AutoCompleteItem>, String> biConsumer, SearchFilter searchFilter) {
        String str = this.TAG;
        Log.s(str, "loadInBackground [" + Logger.getEncodedString(this.mLastRequestedKeyword) + "]");
        return SimpleThreadPool.getInstance().submit(new c(this, searchFilter, biConsumer, 18));
    }

    private void onLoadDone(BiConsumer<ArrayList<AutoCompleteItem>, String> biConsumer, Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        arrayList.addAll(new SearchAutoCompleteItemComposer().readAll(cursor));
        if (!Thread.currentThread().isInterrupted()) {
            biConsumer.accept(arrayList, this.mLastRequestedKeyword);
        }
        String str = this.TAG;
        Log.s(str, "AutoComplete result" + Logger.vt(j.g(new StringBuilder("s:"), arrayList), Long.valueOf(currentTimeMillis)));
    }

    private void sendCancelSignals() {
        this.mCancelSignals.forEach(new d(16));
        this.mCancelSignals.clear();
    }

    public void cancelLastRequest() {
        boolean z;
        String str = this.TAG;
        StringBuilder sb2 = new StringBuilder("Cancel Request [");
        sb2.append(Logger.getEncodedString(this.mLastRequestedKeyword));
        sb2.append("][");
        if (this.mLastRequestedTask != null) {
            z = true;
        } else {
            z = false;
        }
        sb2.append(z);
        sb2.append("]");
        Log.s(str, sb2.toString());
        Future<?> future = this.mLastRequestedTask;
        if (future != null) {
            future.cancel(true);
            this.mLastRequestedTask = null;
        }
        sendCancelSignals();
    }

    public void load(String str, String str2, boolean z, BiConsumer<ArrayList<AutoCompleteItem>, String> biConsumer) {
        cancelLastRequest();
        this.mLastRequestedKeyword = str2;
        CancellationSignal createCancelSignal = createCancelSignal(str2);
        SearchFilter buildSearchFilter = buildSearchFilter(str, str2, createCancelSignal, z);
        addCancelSignal(createCancelSignal);
        this.mLastRequestedTask = loadInBackground(biConsumer, buildSearchFilter);
    }

    public void initMediaData(Blackboard blackboard) {
    }
}
