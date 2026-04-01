package com.samsung.android.gallery.widget.search.searchbar;

import U5.d;
import android.text.TextUtils;
import com.samsung.android.gallery.support.utils.Timer;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class InstantSearchHandler {
    private static final int INPUT_TIMER = Timer.getTimerId();
    private boolean mIsInstantSearch;
    private boolean mIsQueryOngoing;
    private String mLastQuery = "";
    private final BiConsumer<String, String> mSubmitCallback;
    private final boolean mSupportEmptyQuery;

    public InstantSearchHandler(boolean z, BiConsumer<String, String> biConsumer) {
        this.mSupportEmptyQuery = z;
        this.mSubmitCallback = biConsumer;
    }

    private void doInstantSearch(CharSequence charSequence) {
        if (this.mSubmitCallback != null && !this.mIsQueryOngoing) {
            String str = this.mLastQuery;
            if (updateLastQuery(charSequence)) {
                this.mIsInstantSearch = true;
                this.mIsQueryOngoing = true;
                this.mSubmitCallback.accept(str, charSequence.toString().trim());
                this.mIsInstantSearch = false;
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$postSearch$0(CharSequence charSequence, int i2) {
        doInstantSearch(charSequence);
    }

    private boolean updateLastQuery(CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        if (this.mSupportEmptyQuery || !TextUtils.isEmpty(charSequence)) {
            String trim = charSequence.toString().trim();
            if (TextUtils.equals(this.mLastQuery, trim)) {
                return false;
            }
            this.mLastQuery = trim;
            return true;
        }
        this.mLastQuery = "";
        return false;
    }

    public String getLastQuery() {
        return this.mLastQuery;
    }

    public boolean isInstantSearch() {
        return this.mIsInstantSearch;
    }

    public boolean isQueryOngoing() {
        return this.mIsQueryOngoing;
    }

    public void postSearch(CharSequence charSequence) {
        Timer.startTimer(INPUT_TIMER, 500, new d(1, this, charSequence));
    }

    public void reset() {
        this.mLastQuery = "";
        this.mIsQueryOngoing = false;
        Timer.stopTimer(INPUT_TIMER);
    }

    public void searchPendingKeyword(CharSequence charSequence) {
        this.mIsQueryOngoing = false;
        doInstantSearch(charSequence);
    }

    public void setLastQuery(String str) {
        String str2;
        if (str != null) {
            str2 = str.trim();
        } else {
            str2 = "";
        }
        this.mLastQuery = str2;
    }
}
