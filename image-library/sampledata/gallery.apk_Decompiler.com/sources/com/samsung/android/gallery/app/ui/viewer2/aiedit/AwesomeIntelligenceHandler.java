package com.samsung.android.gallery.app.ui.viewer2.aiedit;

import B4.c;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.AiEditItem;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.AiEditItemFactory;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.app.ui.viewer2.menu.AwesomeIntelligenceMenuItem;
import com.samsung.android.gallery.module.aiedit.AiEditExecutor;
import com.samsung.android.gallery.module.aiedit.AiEditThread;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.awesome.AwesomeIntelligenceDialog;
import com.samsung.android.gallery.widget.awesome.IAwesomeItem;
import com.samsung.android.gallery.widget.dialog.AiProgressDialogCompat;
import com.samsung.android.gallery.widget.fastoption2.FastOptionView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.o3dp.lib3dphotography.i;
import com.samsung.scsp.media.api.d;
import com.sec.android.gallery3d.R;
import f4.a;
import g7.k;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AwesomeIntelligenceHandler extends ViewerObject implements FragmentLifeCycle {
    private final Object LOCK = new Object();
    private ArrayList<IAwesomeItem> mAwesomeItems;
    private AwesomeIntelligenceDialog mDialog;
    private AiProgressDialogCompat mProgressDialog;

    private void execute() {
        if (!isActivityDestroyed()) {
            if (this.mAwesomeItems == null) {
                AiEditThread.post(new k(this, 0));
            } else {
                this.mThread.runOnUiThread(new k(this, 1));
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: executeInternal */
    public void lambda$execute$5() {
        ArrayList<IAwesomeItem> arrayList;
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (!this.mModel.isFragmentResumed() || (arrayList = this.mAwesomeItems) == null || mediaItem == null) {
            Log.e((CharSequence) this.TAG, "failed to execute dialog: ", this.mAwesomeItems, mediaItem);
        } else if (arrayList.isEmpty()) {
            showNoSuggestionToast(mediaItem.isImage());
        } else {
            if (this.mDialog == null) {
                this.mDialog = new AwesomeIntelligenceDialog(this.mModel.getContext(), this.mAwesomeItems);
            }
            if (!this.mDialog.isShowing()) {
                showDialog(ResourceCompat.isLandscape((Context) this.mModel.getActivity()));
            }
        }
    }

    private View getAnchorView() {
        Integer findMenuId = this.mModel.getContainerModel().findMenuId(AwesomeIntelligenceMenuItem.class);
        if (findMenuId == null) {
            return null;
        }
        if (!ResourceCompat.isLandscape((Context) this.mModel.getActivity()) || this.mModel.isInMultiWindowMode() || LocationKey.isSecondDepthGroupPanelView(this.mModel.getContainerModel().getLocationKey())) {
            FastOptionView fastOptionView = this.mModel.getContainerModel().getFastOptionView();
            if (fastOptionView != null) {
                return fastOptionView.findFastOptionItemView(findMenuId.intValue());
            }
        } else {
            EventContext eventContext = this.mModel.getContainerModel().getEventContext();
            if (!(eventContext == null || eventContext.getToolbar() == null)) {
                return eventContext.getToolbar().findViewById(findMenuId.intValue());
            }
        }
        return null;
    }

    private int getAnchorX() {
        View anchorView = getAnchorView();
        if (anchorView == null) {
            return -1;
        }
        Resources resources = anchorView.getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.fast_menu_icon_size);
        return ((ViewUtils.getGlobalVisileRect(anchorView).centerX() - this.mModel.getContainerModel().getSystemInsets().left) - dimensionPixelOffset) - resources.getDimensionPixelOffset(R.dimen.awesome_lighting_effect_padding);
    }

    private ArrayList<AiEditItem> getCandidateList(boolean z) {
        EventContext eventContext = this.mModel.getContainerModel().getEventContext();
        if (eventContext != null) {
            return AiEditItemFactory.createAwesomeList(eventContext, this.mActionInvoker, z);
        }
        Log.w(this.TAG, "failed to get candidate list");
        return new ArrayList<>();
    }

    private void hideProgress() {
        AiProgressDialogCompat aiProgressDialogCompat = this.mProgressDialog;
        if (aiProgressDialogCompat != null) {
            aiProgressDialogCompat.dismissAiProgress();
            this.mProgressDialog = null;
        }
    }

    private boolean isActivityDestroyed() {
        if (this.mModel.getActivity() == null || this.mModel.getActivity().isDestroyed()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        execute();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$execute$2(boolean z) {
        showProgress(this.mModel.getContext(), z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$execute$3() {
        lambda$execute$5();
        hideProgress();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$execute$4() {
        synchronized (this.LOCK) {
            try {
                Log.i(this.TAG, "executes Awesome intelligence");
                MediaItem mediaItem = this.mModel.getMediaItem();
                if (mediaItem != null) {
                    this.mThread.runOnUiThread(new c((Object) this, mediaItem.isImage(), 21));
                    this.mAwesomeItems = load(mediaItem, this.mModel.getBitmap());
                    this.mThread.runOnUiThread(new k(this, 2));
                } else {
                    Log.e(this.TAG, "failed to execute the awesome intelligence dialog");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$load$6(ArrayList arrayList, List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            AiEditItem aiEditItem = (AiEditItem) it.next();
            aiEditItem.setExecutor(AiEditExecutor.AwesomeIntelligence);
            arrayList.add(aiEditItem);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onConfigurationChanged$1(Configuration configuration) {
        boolean z;
        if (configuration.orientation == 2) {
            z = true;
        } else {
            z = false;
        }
        showDialog(z);
    }

    /* JADX INFO: finally extract failed */
    private ArrayList<IAwesomeItem> load(MediaItem mediaItem, Bitmap bitmap) {
        Trace.beginSection("AwesomeIntelligence");
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList<IAwesomeItem> arrayList = new ArrayList<>();
        try {
            ArrayList<AiEditItem> candidateList = getCandidateList(mediaItem.isImage());
            Iterator<AiEditItem> it = candidateList.iterator();
            while (it.hasNext()) {
                AiEditItem next = it.next();
                Trace.beginSection("#" + next.TAG);
                Optional.ofNullable(next.load(mediaItem, bitmap)).ifPresent(new a(arrayList, 12));
                Trace.endSection();
            }
            StringCompat stringCompat = this.TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("load");
            Long valueOf = Long.valueOf(mediaItem.getFileId());
            sb2.append(Logger.vt(valueOf, arrayList.size() + "/" + candidateList.size(), Long.valueOf(currentTimeMillis)));
            Log.d(stringCompat, sb2.toString());
            arrayList.sort(Comparator.comparing(new d(20)));
            Trace.endSection();
            return arrayList;
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    private void reset() {
        synchronized (this.LOCK) {
            try {
                this.mAwesomeItems = null;
                AwesomeIntelligenceDialog awesomeIntelligenceDialog = this.mDialog;
                if (awesomeIntelligenceDialog != null) {
                    awesomeIntelligenceDialog.dismiss();
                    this.mDialog = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void showDialog(boolean z) {
        boolean z3;
        if (this.mDialog != null) {
            if (!z || this.mModel.isInMultiWindowMode() || LocationKey.isSecondDepthGroupPanelView(this.mModel.getContainerModel().getLocationKey())) {
                z3 = false;
            } else {
                z3 = true;
            }
            this.mDialog.show(z3, getAnchorX());
        }
    }

    private void showNoSuggestionToast(boolean z) {
        int i2;
        if (z) {
            i2 = R.string.no_editing_suggestion_image;
        } else {
            i2 = R.string.no_editing_suggestion_video;
        }
        Utils.showToast(this.mModel.getContext(), i2);
    }

    private void showProgress(Context context, boolean z) {
        int i2;
        if (this.mProgressDialog == null) {
            AiProgressDialogCompat aiProgressDialogCompat = new AiProgressDialogCompat(getBlackboard(), true);
            this.mProgressDialog = aiProgressDialogCompat;
            aiProgressDialogCompat.setOverlayViewState(false);
            if (z) {
                i2 = R.string.awesome_analyzing_image;
            } else {
                i2 = R.string.awesome_analyzing_video;
            }
            this.mProgressDialog.showAiProgress(context, i2, (Runnable) null);
            return;
        }
        Log.w(this.TAG, "already show");
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.EXECUTE_AWESOME_INTELLIGENCE, new B7.d(12, this));
    }

    public boolean handleBlackboardEvent(EventMessage eventMessage) {
        if (eventMessage.what != 3059 || eventMessage.obj != null) {
            return false;
        }
        execute();
        return false;
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        super.invalidate(mediaItem, i2, mediaItem2, i7);
        if (!MediaItemUtil.equals(mediaItem2, mediaItem)) {
            reset();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        AwesomeIntelligenceDialog awesomeIntelligenceDialog = this.mDialog;
        if (awesomeIntelligenceDialog != null && awesomeIntelligenceDialog.isShowing()) {
            this.mDialog.dismiss();
            this.mThread.runOnUiThread(new i(28, this, configuration), 1);
        }
    }

    public void onViewDetached() {
        AwesomeIntelligenceDialog awesomeIntelligenceDialog = this.mDialog;
        if (awesomeIntelligenceDialog != null && awesomeIntelligenceDialog.isShowing()) {
            this.mDialog.dismiss();
        }
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        reset();
    }
}
