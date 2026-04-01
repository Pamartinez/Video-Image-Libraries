package com.samsung.android.gallery.app.ui.list.stories.highlight.delegate;

import D5.c;
import T3.a;
import android.content.res.Configuration;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowInsets;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import l4.b;
import n4.C0489a;
import n4.C0491c;
import n5.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ControlDelegate<V extends IStoryHighlightView> extends Delegate {
    List<Delegate> mDelegators;

    public ControlDelegate(V v) {
        super(v);
        this.mDelegators = create(v);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$handlePostEvent$7(Event event, Object[] objArr, Delegate delegate) {
        if (!delegate.isListening(event)) {
            return false;
        }
        delegate.handlePostEvent(event, objArr);
        return true;
    }

    public List<Delegate> create(V v) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DefaultThemeDelegate(v));
        arrayList.add(new ViewPagerDelegate(v));
        arrayList.add(new WindowDecoDelegate(v));
        arrayList.add(new OsdUiDelegate(v));
        arrayList.add(new FilmStripViewDelegate(v));
        arrayList.add(new VideoPreviewDelegate(v));
        arrayList.add(new BottomDecoViewDelegate(v));
        if (v.getOptions().supportBgm()) {
            arrayList.add(new BgmPlayerDelegate(v));
        }
        arrayList.add(new GuideDecoViewDelegate(v));
        arrayList.add(new BottomSheetDelegate(v));
        arrayList.add(new LastPageDelegate(v));
        arrayList.add(new FilterApplyDelegate(v));
        if (v.getOptions().supportFilter()) {
            arrayList.add(new ThemeViewDelegate(v));
        }
        if (v.getOptions().supportBgmPicker()) {
            arrayList.add(new BgmPickerDelegate(v));
        }
        if (v.getOptions().supportLiveEffect()) {
            arrayList.add(new StoryLiveEffectDelegate(v));
        }
        arrayList.add(new AudioPermissionDelegate(v));
        Log.d("ControlDelegate", "create", Boolean.valueOf(v.getOptions().supportBgm()), Boolean.valueOf(PreferenceFeatures.isEnabled(PreferenceFeatures.StoriesFilter)));
        return arrayList;
    }

    public void handleDensityChange(int i2) {
        this.mDelegators.forEach(new C0489a(i2, 3));
    }

    public void handleOrientationChange(int i2) {
        this.mDelegators.forEach(new C0489a(i2, 2));
    }

    public void handlePostEvent(Event event, Object... objArr) {
        if (this.mDelegators.stream().filter(new a(8, event, objArr)).count() == 0) {
            String str = this.TAG;
            Log.w(str, "handlePostEvent not processed" + Logger.v(event, Integer.valueOf(this.mDelegators.size())) + "\n" + ((String) this.mDelegators.stream().map(new e(7)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX))));
        }
    }

    public Object handleRequestData(DataRequest dataRequest, Object... objArr) {
        for (int i2 = 0; i2 < this.mDelegators.size(); i2++) {
            Delegate delegate = this.mDelegators.get(i2);
            if (delegate.hasRequestProvider(dataRequest)) {
                return delegate.handleRequestData(dataRequest, objArr);
            }
        }
        return null;
    }

    public void handleResolutionChange(int i2) {
        this.mDelegators.forEach(new C0489a(i2, 4));
    }

    public void initView(View view) {
        this.mDelegators.forEach(new c(view, 4));
    }

    public void onApplyWindowInsets(View view, WindowInsets windowInsets) {
        this.mDelegators.forEach(new C0491c(8, view, windowInsets));
    }

    public void onAttach() {
        this.mDelegators.forEach(new m7.c(22));
    }

    public boolean onBackPressed() {
        for (int i2 = 0; i2 < this.mDelegators.size(); i2++) {
            if (this.mDelegators.get(i2).onBackPressed()) {
                return true;
            }
        }
        return false;
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.mDelegators.forEach(new o4.a(3, configuration));
    }

    public void onDataChangedOnUi() {
        this.mDelegators.forEach(new m7.c(21));
    }

    public void onDestroy() {
        this.mDelegators.forEach(new m7.c(24));
    }

    public void onDestroyView() {
        this.mDelegators.forEach(new m7.c(20));
    }

    public void onHeaderUpdated() {
        this.mDelegators.forEach(new m7.c(25));
    }

    public boolean onKeyEvent(int i2, KeyEvent keyEvent) {
        for (int i7 = 0; i7 < this.mDelegators.size(); i7++) {
            if (this.mDelegators.get(i7).onKeyEvent(i2, keyEvent)) {
                return true;
            }
        }
        return false;
    }

    public void onMultiWindowModeChanged(boolean z) {
        this.mDelegators.forEach(new b(z, 6));
    }

    public void onPause() {
        super.onPause();
        this.mDelegators.forEach(new m7.c(23));
    }

    public void onResume() {
        super.onResume();
        this.mDelegators.forEach(new m7.c(26));
    }

    public void setScreenMode() {
        this.mDelegators.forEach(new m7.c(19));
    }

    public void handleEvent(Event event, Object... objArr) {
    }
}
