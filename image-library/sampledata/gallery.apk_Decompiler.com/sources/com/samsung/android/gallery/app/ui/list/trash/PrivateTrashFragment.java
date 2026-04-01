package com.samsung.android.gallery.app.ui.list.trash;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import com.samsung.android.gallery.module.secured.PrivateDatabase;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PrivateTrashFragment extends TrashFragment<ITrashView, TrashPresenter<ITrashView>> {
    private final ContentObserver mObserver = new ContentObserver((Handler) null) {
        /* JADX WARNING: type inference failed for: r2v3, types: [java.util.function.Consumer, java.lang.Object] */
        public void onChange(boolean z, Uri uri) {
            Log.i(PrivateTrashFragment.this.TAG, "onChange", uri);
            Optional.ofNullable((TrashPresenter) PrivateTrashFragment.this.mPresenter).ifPresent(new Object());
        }

        public void onChange(boolean z) {
            onChange(z, (Uri) null);
        }
    };

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PrivateTrashPresenter extends TrashPresenter<ITrashView> {
        public PrivateTrashPresenter(Blackboard blackboard, ITrashView iTrashView) {
            super(blackboard, iTrashView);
        }

        public int[] loadCount(Context context) {
            return PrivateDatabase.getInstance().loadTrashCount();
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        GalleryAppBarLayout galleryAppBarLayout = this.mAppBarLayout;
        if (galleryAppBarLayout != null) {
            galleryAppBarLayout.disableAppbarScroll();
        }
    }

    public String getDescription(Context context) {
        return context.getString(R.string.private_album_trash_description);
    }

    public boolean isInMultiTrash() {
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PrivateDatabase.getInstance().registerObserver(this.mObserver);
    }

    public void onDestroy() {
        super.onDestroy();
        PrivateDatabase.getInstance().unregisterObserver(this.mObserver);
    }

    public TrashPresenter createPresenter(ITrashView iTrashView) {
        return new PrivateTrashPresenter(this.mBlackboard, iTrashView);
    }
}
