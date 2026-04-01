package com.samsung.android.gallery.app.ui.list.picker.search.suggestion;

import C3.C0391a;
import C3.i;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.search.suggestion.ISuggestionContainerView;
import com.samsung.android.gallery.app.ui.list.search.suggestion.SuggestionContainerPresenter;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestionContainerPickerPresenter<V extends ISuggestionContainerView> extends SuggestionContainerPresenter<V> {
    public SuggestionContainerPickerPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    /* access modifiers changed from: private */
    public void operateClipboard(Object obj, Bundle bundle) {
        ((ISuggestionContainerView) this.mView).operateClipboard(((Boolean) obj).booleanValue());
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("command://OperateClipboardArea", new C0391a(24, this)).setWorkingOnUI());
    }

    public void onNavigationPressed(View view) {
        this.mBlackboard.postEvent(EventMessage.obtain(8506, Boolean.FALSE));
        super.onNavigationPressed(view);
    }

    public void onViewCreated(View view) {
        super.onViewCreated(view);
        SimpleThreadPool.getInstance().execute(new i(19));
    }

    public void updateToolbar(Toolbar toolbar) {
        setNavigationUpButton(toolbar);
    }
}
