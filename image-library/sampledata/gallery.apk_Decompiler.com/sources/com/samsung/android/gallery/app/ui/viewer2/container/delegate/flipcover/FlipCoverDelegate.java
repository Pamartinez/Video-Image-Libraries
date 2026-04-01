package com.samsung.android.gallery.app.ui.viewer2.container.delegate.flipcover;

import android.graphics.Outline;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.foldable.FoldStateManager;
import com.samsung.android.gallery.module.foldable.FoldableScreen;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.photoview.PhotoPreView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import g6.g;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FlipCoverDelegate extends AbsVuDelegate<IVuContainerView> {
    private final FoldStateManager.FoldChangedListener mFoldChangedListener = new FoldStateManager.FoldChangedListener() {
        public void onScreenChanged(FoldableScreen foldableScreen, FoldableScreen foldableScreen2) {
            if (!FoldableScreen.FLIP_COVER.equals(foldableScreen) || !FoldableScreen.MAIN.equals(foldableScreen2)) {
                Log.w(FlipCoverDelegate.this.TAG, "screen is not opened");
                return;
            }
            ViewUtils.setVisibility(FlipCoverDelegate.this.mRootView, 8);
            Log.d(FlipCoverDelegate.this.TAG, "recreateActivity");
            Utils.recreateActivity(((IVuContainerView) FlipCoverDelegate.this.mView).getActivity());
        }
    };
    private FoldStateManager mFoldStateManager;
    protected PhotoPreView mPreview;
    /* access modifiers changed from: private */
    public View mRootView;

    public FlipCoverDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    private FoldStateManager getFoldStateManager() {
        if (this.mFoldStateManager == null) {
            this.mFoldStateManager = FoldStateManager.getInstance(this.mBlackboard);
        }
        return this.mFoldStateManager;
    }

    private void initPreview(View view) {
        PhotoPreView photoPreView = (PhotoPreView) view.findViewById(R.id.viewer_container_preview);
        this.mPreview = photoPreView;
        photoPreView.setOutlineProvider(new ViewOutlineProvider() {
            public void getOutline(View view, Outline outline) {
                Outline outline2 = outline;
                outline2.setRoundRect(0, 0, view.getWidth(), view.getHeight(), (float) FlipCoverDelegate.this.mPreview.getResources().getDimensionPixelOffset(R.dimen.cover_rounded_corner_radius));
            }
        });
        this.mPreview.setClipToOutline(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(FoldStateManager foldStateManager) {
        foldStateManager.register(this.mFoldChangedListener);
    }

    public void onBindView(View view) {
        super.onBindView(view);
        initPreview(view);
        this.mRootView = view.findViewById(R.id.viewer_root_protection_layout);
    }

    public void onCreate(Bundle bundle) {
        Optional.ofNullable(getFoldStateManager()).ifPresent(new g(25, this));
    }

    public void onDestroy() {
        this.mBlackboard.pop("data://viewer_last_data_from_flip_cover");
        FoldStateManager foldStateManager = this.mFoldStateManager;
        if (foldStateManager != null) {
            foldStateManager.unregister(this.mFoldChangedListener);
            this.mFoldStateManager = null;
        }
    }

    public void onPageSelected(int i2, ViewerObjectComposite viewerObjectComposite) {
        Uri secUri = ContentUri.getSecUri(((ContainerModel) this.mModel).getCurrentMediaItem());
        if (secUri != null) {
            this.mBlackboard.publish("data://viewer_last_data_from_flip_cover", secUri);
        }
    }
}
