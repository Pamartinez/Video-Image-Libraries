package com.samsung.android.gallery.app.ui.list.search.autoComplete;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.ViewGroup;
import bc.d;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.module.search.autocomplete.AutoCompleteDataLoader;
import com.samsung.android.gallery.module.search.autocomplete.AutoCompleteDataLoaderFactory;
import com.samsung.android.gallery.module.search.autocomplete.AutoCompleteItem;
import com.samsung.android.gallery.module.search.autocomplete.AutoCompleteUriBuilder;
import com.samsung.android.gallery.module.search.filter.SearchFilterUtil;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.search.searchbar.autocomplete.FloatingAutoCompleteView;
import g6.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FloatingAutoCompleteDelegate {
    private final AutoCompleteDataLoader mAutoCompleteDataLoader;
    private final FloatingAutoCompleteView mAutoCompleteView;
    private final Blackboard mBlackboard;
    private final Handler mHandler;
    private String mTargetUrl;

    public FloatingAutoCompleteDelegate(Blackboard blackboard, ViewGroup viewGroup) {
        this(blackboard, new FloatingAutoCompleteView(blackboard, viewGroup, (Consumer<Boolean>) null));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onAutoCompleteItemsLoaded$0(ArrayList arrayList, String str) {
        this.mAutoCompleteView.onDataChanged(arrayList, str);
    }

    /* access modifiers changed from: private */
    public void loadAutoComplete(Object[] objArr) {
        CharSequence charSequence = objArr[0];
        if (charSequence.length() == 0) {
            onAutoCompleteItemsLoaded(new ArrayList(), "");
            return;
        }
        String str = this.mTargetUrl;
        if (str == null) {
            str = BlackboardUtils.readLocationKeyCurrent(this.mBlackboard);
        }
        this.mAutoCompleteDataLoader.load(str, charSequence.toString(), objArr[1].booleanValue(), new f(2, this));
    }

    /* access modifiers changed from: private */
    public void onAutoCompleteItemsLoaded(ArrayList<AutoCompleteItem> arrayList, String str) {
        Iterator<AutoCompleteItem> it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            AutoCompleteItem next = it.next();
            if (TextUtils.equals(next.getTranslatedKeyword(), str)) {
                arrayList.remove(next);
                break;
            }
        }
        ThreadUtil.postOnUiThread(new d((Object) this, (Object) arrayList, (Object) str, 19));
    }

    public void dismissAutoCompleteView() {
        this.mHandler.removeMessages(101);
        this.mAutoCompleteDataLoader.cancelLastRequest();
        this.mAutoCompleteView.dismiss();
    }

    public void handleItemSelect(IMvpBaseView iMvpBaseView, FilterResultsEntity filterResultsEntity) {
        if (filterResultsEntity != null) {
            iMvpBaseView.postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_KEYWORD_ITEM);
            iMvpBaseView.getBlackboard().post("command://MoveURL", AutoCompleteUriBuilder.build(iMvpBaseView.getBlackboard(), filterResultsEntity, SearchFilterUtil.getSelectedFilter(this.mBlackboard), (String) null));
            return;
        }
        Log.w("FloatingAutoCompleteDelegate", "handleItemSelect fail");
    }

    public void onDestroy() {
        this.mAutoCompleteDataLoader.release();
    }

    public void onTextChanged(CharSequence charSequence, boolean z) {
        this.mHandler.removeMessages(101);
        Handler handler = this.mHandler;
        handler.sendMessageDelayed(handler.obtainMessage(101, new Object[]{charSequence, Boolean.valueOf(z)}), 200);
    }

    public void setHeight(int i2) {
        this.mAutoCompleteView.setHeight(i2);
    }

    public FloatingAutoCompleteDelegate(Blackboard blackboard, FloatingAutoCompleteView floatingAutoCompleteView) {
        AutoCompleteDataLoader create = AutoCompleteDataLoaderFactory.create();
        this.mAutoCompleteDataLoader = create;
        this.mHandler = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message message) {
                if (message.what == 101) {
                    FloatingAutoCompleteDelegate.this.loadAutoComplete((Object[]) message.obj);
                }
            }
        };
        this.mBlackboard = blackboard;
        this.mAutoCompleteView = floatingAutoCompleteView;
        create.initMediaData(blackboard);
    }

    public void onTextChanged(String str, String str2) {
        this.mTargetUrl = str;
        onTextChanged((CharSequence) str2, true);
    }
}
