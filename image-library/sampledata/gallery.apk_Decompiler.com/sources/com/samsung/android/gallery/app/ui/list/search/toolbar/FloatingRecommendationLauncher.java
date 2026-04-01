package com.samsung.android.gallery.app.ui.list.search.toolbar;

import B2.i;
import J5.b;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.PixelCopy;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.search.rubin.SearchWordCollector;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.search.constants.ForegroundMode;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FloatingRecommendationLauncher {
    private final View.AccessibilityDelegate mAccessibilityDelegate = new View.AccessibilityDelegate() {
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (accessibilityEvent.getEventType() == 1) {
                FloatingRecommendationLauncher.this.captureScreenAndExecuteFloatingRecommendation(false, (String) null);
            }
            return super.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
        }
    };
    private IBaseListView mView;

    public FloatingRecommendationLauncher() {
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$captureScreenAndExecuteFloatingRecommendation$0(Bitmap bitmap, boolean z, String str, int i2) {
        if (i2 == 0) {
            this.mView.getBlackboard().publish("data:///CapturedBitmap", bitmap);
            moveToRecommendation(this.mView.getBlackboard(), z, str);
            return;
        }
        Log.e("FloatingRecommendationLauncher", "PixelCopy failed.");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$stealSearchEditTextTouchEvent$1(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            captureScreenAndExecuteFloatingRecommendation(false, (String) null);
        }
        return true;
    }

    private void moveToRecommendation(Blackboard blackboard, boolean z, String str) {
        boolean z3;
        if (!PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            blackboard.post("command://MoveURL", "location://search/FloatingRecommendation");
        } else if (TextUtils.isEmpty(str)) {
            UriBuilder appendArg = new UriBuilder(LocationKey.getSearchLocationKey("location://search/fileList/Keyword", "")).appendArg("term", "key_word").appendArg("collect_type", SearchWordCollector.Type.KEYWORD_INPUT.toString()).appendArg("disableTimeline", PreferenceFeatures.OneUi7x.DISABLE_TIMELINE_ON_KEYWORD).appendArg("searchMode", ForegroundMode.SEARCH.name());
            if (!PocFeatures.ASK_SCREENSHOT || !z) {
                z3 = false;
            } else {
                z3 = true;
            }
            blackboard.post("command://MoveURL", appendArg.appendArg("ask_screenshot", z3).build());
        } else {
            blackboard.post("command://MoveURL", str);
        }
    }

    private void stealSearchEditTextTouchEvent(SearchToolbar searchToolbar) {
        searchToolbar.getSearchTextView().setOnTouchListener(new i(11, this));
    }

    public void captureScreenAndExecuteFloatingRecommendation(Object obj) {
        Object[] objArr = (Object[]) obj;
        captureScreenAndExecuteFloatingRecommendation(((Boolean) objArr[0]).booleanValue(), objArr.length > 1 ? (String) objArr[1] : null);
    }

    public void executeFloatingRecommendation(Blackboard blackboard) {
        blackboard.publish("data:///CapturedBitmap", BitmapUtils.createBitmap(100, 100, Bitmap.Config.RGB_565, AppResources.getColor(R.color.default_fw_background)));
        moveToRecommendation(blackboard, false, (String) null);
    }

    public void setTouchListener(SearchToolbar searchToolbar) {
        stealSearchEditTextTouchEvent(searchToolbar);
        searchToolbar.setAccessibilityDelegate(this.mAccessibilityDelegate);
    }

    public FloatingRecommendationLauncher(IBaseListView iBaseListView) {
        this.mView = iBaseListView;
    }

    /* access modifiers changed from: private */
    public void captureScreenAndExecuteFloatingRecommendation(boolean z, String str) {
        IBaseListView iBaseListView = this.mView;
        View rootView = (iBaseListView == null || iBaseListView.getView() == null) ? null : this.mView.getView().getRootView();
        if (rootView == null) {
            Log.d("FloatingRecommendationLauncher", "rootView is null.");
            return;
        }
        Bitmap createBitmap = Bitmap.createBitmap(rootView.getWidth(), rootView.getHeight(), Bitmap.Config.RGB_565);
        Rect rect = new Rect();
        rootView.getGlobalVisibleRect(rect);
        PixelCopy.request(this.mView.getActivity().getWindow(), rect, createBitmap, new b(this, createBitmap, z, str), new Handler(Looper.getMainLooper()));
    }
}
