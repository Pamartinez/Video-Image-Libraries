package com.samsung.android.gallery.widget.smartalbum;

import S1.g;
import V3.b;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewStub;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.cache.LayoutCache;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SmartAlbumHolder {
    protected final String TAG = getClass().getSimpleName();
    private final Blackboard mBlackboard;
    private final String mLocationKey;
    private boolean mPickerMode;
    private final String mScreenId;
    protected SmartAlbumLayout mSmartAlbumLayout;
    private final ViewStub mViewStub;

    public SmartAlbumHolder(ViewStub viewStub, Blackboard blackboard, String str, String str2) {
        this.mViewStub = viewStub;
        this.mBlackboard = blackboard;
        this.mLocationKey = str;
        this.mScreenId = str2;
        this.mPickerMode = !PickerUtil.isNormalLaunchMode(blackboard);
    }

    private SmartAlbumLayout createLayout(ViewStub viewStub) {
        SmartAlbumLayout smartAlbumLayout = (SmartAlbumLayout) LayoutCache.getInstance().getView(viewStub.getLayoutResource());
        if (smartAlbumLayout == null) {
            return (SmartAlbumLayout) LayoutInflater.from(viewStub.getContext()).inflate(this.mViewStub.getLayoutResource(), (ViewGroup) null, false);
        }
        return smartAlbumLayout;
    }

    private void inflateSmartAlbum() {
        ViewGroup viewGroup;
        int i2;
        if (this.mSmartAlbumLayout != null) {
            Log.e(this.TAG, "inflateSmartAlbum already inflated");
            return;
        }
        Trace.beginSection("inflateSmartAlbum");
        ViewStub viewStub = this.mViewStub;
        if (viewStub != null) {
            viewGroup = (ViewGroup) viewStub.getParent();
        } else {
            viewGroup = null;
        }
        if (viewGroup != null) {
            Context context = this.mViewStub.getContext();
            if (context != null) {
                if (this.mPickerMode) {
                    ViewStub viewStub2 = this.mViewStub;
                    if (PickerUtil.supportSearch()) {
                        i2 = context.getResources().getDimensionPixelSize(R$dimen.smart_album_layout_top_margin_in_picker);
                    } else {
                        i2 = context.getResources().getDimensionPixelSize(R$dimen.smart_album_layout_top_margin_in_picker_without_search);
                    }
                    ViewMarginUtils.setTopMargin(viewStub2, i2);
                    SmartAlbumLayout createLayout = createLayout(this.mViewStub);
                    this.mSmartAlbumLayout = createLayout;
                    createLayout.updateViewInPickMode(this.mBlackboard, this.mLocationKey, this.mScreenId);
                } else {
                    SmartAlbumLayout createLayout2 = createLayout(this.mViewStub);
                    this.mSmartAlbumLayout = createLayout2;
                    createLayout2.updateView(this.mBlackboard, this.mLocationKey, this.mScreenId);
                }
                if (ThreadUtil.isMainThread()) {
                    ViewUtils.replaceView(viewGroup, this.mViewStub, this.mSmartAlbumLayout);
                } else {
                    viewGroup.post(new b(21, this));
                }
            } else {
                return;
            }
        }
        Trace.endSection();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$inflateSmartAlbum$0() {
        ViewUtils.replaceView((ViewGroup) this.mViewStub.getParent(), this.mViewStub, this.mSmartAlbumLayout);
    }

    private SmartAlbumLayout restoreLayout(Blackboard blackboard, SmartAlbumLayout smartAlbumLayout, String str) {
        boolean z;
        Context context = this.mViewStub.getContext();
        if (smartAlbumLayout == null || context == null) {
            Log.d(this.TAG, "restoreLayout failed. null layout");
            return null;
        }
        SmartAlbumLayout createLayout = createLayout(this.mViewStub);
        ViewGroup viewGroup = (ViewGroup) smartAlbumLayout.getParent();
        if (viewGroup == null) {
            ViewUtils.replaceView((ViewGroup) this.mViewStub.getParent(), this.mViewStub, createLayout);
            String str2 = this.TAG;
            StringBuilder sb2 = new StringBuilder("parent of viewStub=");
            if (this.mViewStub.getParent() != null) {
                z = true;
            } else {
                z = false;
            }
            sb2.append(z);
            Log.d(str2, sb2.toString());
        } else {
            int indexOfChild = viewGroup.indexOfChild(smartAlbumLayout);
            ViewUtils.removeView(viewGroup, smartAlbumLayout);
            viewGroup.addView(createLayout, indexOfChild, smartAlbumLayout.getLayoutParams());
        }
        if (this.mPickerMode) {
            createLayout.updateViewInPickMode(blackboard, str, smartAlbumLayout.getScreenId());
            g gVar = (g) createLayout.getLayoutParams();
            gVar.topMargin = context.getResources().getDimensionPixelOffset(R$dimen.smart_album_layout_top_margin_in_picker);
            gVar.bottomMargin = context.getResources().getDimensionPixelOffset(R$dimen.smart_album_layout_bottom_margin_in_picker);
            createLayout.setLayoutParams(gVar);
        } else {
            createLayout.updateView(blackboard, str, smartAlbumLayout.getScreenId());
        }
        createLayout.restoreState(smartAlbumLayout);
        return createLayout;
    }

    public boolean exitSelectionMode() {
        SmartAlbumLayout smartAlbumLayout;
        if (!(this.mViewStub == null || (smartAlbumLayout = this.mSmartAlbumLayout) == null)) {
            if (!smartAlbumLayout.isSmartAlbumTitleAvailable()) {
                this.mSmartAlbumLayout.updateSmartAlbumVisible(true);
                return false;
            }
            this.mSmartAlbumLayout.updateTitle();
        }
        return true;
    }

    public void inflateSmartAlbumIfNecessary() {
        if (this.mSmartAlbumLayout == null) {
            synchronized (SmartAlbumHolder.class) {
                try {
                    if (this.mSmartAlbumLayout == null) {
                        inflateSmartAlbum();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public void restore() {
        if (this.mViewStub != null) {
            this.mSmartAlbumLayout = restoreLayout(this.mBlackboard, this.mSmartAlbumLayout, this.mLocationKey);
        }
    }

    public void setAlpha(float f) {
        if (this.mViewStub != null && f > 0.0f) {
            inflateSmartAlbumIfNecessary();
            SmartAlbumLayout smartAlbumLayout = this.mSmartAlbumLayout;
            if (smartAlbumLayout != null) {
                float f5 = ((f - 0.33f) * 1.0f) / 0.65999997f;
                if (Float.compare(f5, smartAlbumLayout.getAlpha()) != 0) {
                    this.mSmartAlbumLayout.setAlpha(f5);
                }
            }
        }
    }

    public void setEnabled(boolean z) {
        if (this.mViewStub != null) {
            inflateSmartAlbumIfNecessary();
            SmartAlbumLayout smartAlbumLayout = this.mSmartAlbumLayout;
            if (smartAlbumLayout != null) {
                smartAlbumLayout.setClickEnabled(z);
            }
        }
    }

    public void updateLayout() {
        SmartAlbumLayout smartAlbumLayout;
        if (this.mViewStub != null && (smartAlbumLayout = this.mSmartAlbumLayout) != null) {
            smartAlbumLayout.updateItemsLayout();
        }
    }

    public boolean updateSelectionCount(CharSequence charSequence) {
        if (this.mViewStub == null) {
            return true;
        }
        inflateSmartAlbumIfNecessary();
        SmartAlbumLayout smartAlbumLayout = this.mSmartAlbumLayout;
        if (smartAlbumLayout != null) {
            if (!smartAlbumLayout.isSmartAlbumTitleAvailable()) {
                this.mSmartAlbumLayout.updateSmartAlbumVisible(false);
                return false;
            }
            this.mSmartAlbumLayout.updateSelectionCountTitle(charSequence);
        }
        return true;
    }

    public boolean viewInflated() {
        if (this.mSmartAlbumLayout != null) {
            return true;
        }
        return false;
    }
}
