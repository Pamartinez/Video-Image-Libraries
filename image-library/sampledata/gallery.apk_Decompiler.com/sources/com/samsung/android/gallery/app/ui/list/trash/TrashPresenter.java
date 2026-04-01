package com.samsung.android.gallery.app.ui.list.trash;

import A.a;
import T3.e;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.trash.OneTrashMigrationCmd;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPresenter;
import com.samsung.android.gallery.app.ui.list.trash.ITrashView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinder;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.mdebase.db.MdeDatabase;
import com.samsung.android.gallery.module.trash.factory.TrashProviderFactory;
import com.samsung.android.gallery.module.trash.helper.TrashEmptyExpiredTask;
import com.samsung.android.gallery.module.trash.helper.TrashRollbackTask;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileListHolder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ServiceManager;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.scsp.media.api.constant.MediaApiContract;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.io.File;
import java.util.List;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class TrashPresenter<V extends ITrashView> extends PicturesPresenter<V> {
    private final ContentObserver mContentObserver = new ContentObserver(ThreadUtil.createMainThreadHandler()) {
        public void onChange(boolean z, Uri uri) {
            StringCompat access$000 = TrashPresenter.this.TAG;
            Log.d(access$000, "onChange " + uri);
            if (TrashPresenter.this.mHandler.hasMessages(0)) {
                TrashPresenter.this.mHandler.removeMessages(0);
            }
            TrashPresenter.this.mHandler.sendEmptyMessageDelayed(0, 800);
        }
    };
    private final int[] mCount = new int[2];
    /* access modifiers changed from: private */
    public final Handler mHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (message.what == 0) {
                Log.d(TrashPresenter.this.TAG, "refresh trash data");
                TrashPresenter.this.eraseDataCursorKey();
                TrashPresenter.this.forceReloadData();
            }
        }
    };

    public TrashPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private void cleanUpIfEmpty() {
        if (!PreferenceFeatures.OneUi6x.SUPPORT_ONE_TRASH) {
            long currentTimeMillis = System.currentTimeMillis();
            long j2 = PreferenceCache.TrashEmptyTime.getLong();
            if (currentTimeMillis - j2 > MediaApiContract.DAY_IN_MILLI || currentTimeMillis < j2) {
                Log.d(this.TAG, "cleanUpIfEmpty", Integer.valueOf(this.mCount[0]), Integer.valueOf(this.mCount[1]), Long.valueOf(j2));
                new TrashEmptyExpiredTask(getApplicationContext(), false).execute(new Void[0]);
                return;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(AppResources.getAppContext().getFilesDir());
            List<String> trimLastLines = new FileListHolder(C0212a.p(sb2, File.separator, "trash_abnormal.txt")).trimLastLines(500);
            if (trimLastLines != null && trimLastLines.size() > 0) {
                SimpleThreadPool.getInstance().execute(new f(this, trimLastLines, currentTimeMillis));
            }
        }
    }

    /* access modifiers changed from: private */
    public void eraseDataCursorKey() {
        this.mBlackboard.erase(DataKey.DATA_CURSOR("location://trash"));
    }

    private String getTitle(Context context) {
        if (!isEditMode() || getDataCount() <= 0) {
            return context.getString(R.string.trash);
        }
        return null;
    }

    private boolean isDeleteServiceRunning() {
        return ServiceManager.getInstance().hasRunningServiceForTrash(getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$cleanUpIfEmpty$1(List list, long j2) {
        try {
            list.forEach(new e(5));
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "cleanUpIfEmpty#delete abnormal" + Logger.vt(Integer.valueOf(list.size()), Long.valueOf(j2)));
        } catch (Exception e) {
            a.r(e, new StringBuilder("cleanUpIfEmpty#delete abnormal failed. e="), this.TAG);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$onDataPrepared$0(Toolbar toolbar, ThreadPool.JobContext jobContext) {
        updateSubTitle(toolbar, ((ITrashView) this.mView).getAppbarLayout());
        if (LocationKey.isTrash(getLocationKey())) {
            cleanUpIfEmpty();
        }
        return Boolean.TRUE;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$rollbackTrashIfExist$2() {
        new TrashRollbackTask().execute(getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateToolbar$3(Context context, GalleryAppBarLayout galleryAppBarLayout) {
        galleryAppBarLayout.setSubtitle((CharSequence) null);
        galleryAppBarLayout.setTitle((CharSequence) getTitle(context));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$updateToolbar$4(Toolbar toolbar, ThreadPool.JobContext jobContext) {
        return updateSubTitle(toolbar, ((ITrashView) this.mView).getAppbarLayout());
    }

    private void registerContentObserver() {
        Uri secTrashUri = MediaUri.getInstance().getSecTrashUri();
        if (secTrashUri != null) {
            try {
                getContext().getContentResolver().registerContentObserver(secTrashUri, true, this.mContentObserver);
            } catch (Exception e) {
                a.r(e, new StringBuilder("registerContentObserver failed e="), this.TAG);
            }
        }
    }

    private void rollbackTrashIfExist() {
        if (PreferenceFeatures.OneUi6x.SUPPORT_ONE_TRASH && PocFeatures.SUPPORT_QUICK_DELETE) {
            PreferenceCache preferenceCache = PreferenceCache.QuickDeleteService;
            if (preferenceCache.getInt() > 0 && !isDeleteServiceRunning()) {
                preferenceCache.clear();
                SimpleThreadPool.getInstance().submit(new d(this));
            }
        }
    }

    private void unregisterContentObserver() {
        try {
            getContext().getContentResolver().unregisterContentObserver(this.mContentObserver);
        } catch (Exception e) {
            a.r(e, new StringBuilder("unregisterContentObserver failed e="), this.TAG);
        }
    }

    private Object updateSubTitle(Toolbar toolbar, GalleryAppBarLayout galleryAppBarLayout) {
        try {
            if (!isEditMode()) {
                if (!((ITrashView) this.mView).isViewHidden()) {
                    Context context = getContext();
                    if (context != null) {
                        ThreadUtil.postOnUiThread(new e(this, toolbar, galleryAppBarLayout, loadCount(context)));
                        return null;
                    }
                }
            }
            return null;
        } catch (Exception e) {
            a.r(e, new StringBuilder("updateSubtitle failed e="), this.TAG);
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: updateSubtitleOnUi */
    public void lambda$updateSubTitle$5(Toolbar toolbar, GalleryAppBarLayout galleryAppBarLayout, int[] iArr) {
        int i2;
        if (iArr != null) {
            try {
                if (iArr.length > 1) {
                    int[] iArr2 = this.mCount;
                    iArr2[0] = iArr[0];
                    iArr2[1] = iArr[1];
                    Log.d(this.TAG, "updateSubtitleOnUi", Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1]));
                    CharSequence subtitle = toolbar.getSubtitle();
                    String makeSubtitle = makeSubtitle(iArr[0], iArr[1]);
                    if (!TextUtils.equals(subtitle, makeSubtitle)) {
                        toolbar.setSubtitle((CharSequence) makeSubtitle);
                    }
                    if (galleryAppBarLayout != null && !TextUtils.equals(galleryAppBarLayout.getSubTitle(), makeSubtitle)) {
                        galleryAppBarLayout.setSubtitle(makeSubtitle);
                        return;
                    }
                    return;
                }
            } catch (Exception e) {
                a.r(e, new StringBuilder("updateSubtitleOnUi failed e="), this.TAG);
                return;
            }
        }
        toolbar.setSubtitle((CharSequence) null);
        StringCompat stringCompat = this.TAG;
        StringBuilder sb2 = new StringBuilder("updateSubtitleOnUi failed ");
        if (iArr != null) {
            i2 = iArr.length;
        } else {
            i2 = -1;
        }
        sb2.append(i2);
        Log.e(stringCompat, sb2.toString());
    }

    public MenuDataBinding createMenuDataBinding() {
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_trash);
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_empty) {
            public boolean getDefaultVisibility() {
                return !TrashPresenter.this.isEditMode();
            }
        });
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_select) {
            public boolean getDefaultVisibility() {
                return !TrashPresenter.this.isEditMode();
            }
        });
        MenuDataBinder.bindActionViewAs(menuDataBinding, this.mBlackboard);
        return menuDataBinding;
    }

    public MenuHandler createMenuHandler() {
        return new TrashMenuHandler();
    }

    public int getImageCount() {
        return this.mCount[0];
    }

    public String getLabelForAccessibility(Context context) {
        return context.getString(R.string.trash);
    }

    public int getTotalCount() {
        int[] iArr = this.mCount;
        return iArr[0] + iArr[1];
    }

    public int getVideoCount() {
        return this.mCount[1];
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 1029) {
            eraseDataCursorKey();
            forceReloadData();
            return true;
        } else if (i2 == 1076) {
            ((ITrashView) this.mView).resetHeaderView();
            return true;
        } else if (i2 != 1127) {
            return super.handleEvent(eventMessage);
        } else {
            new OneTrashMigrationCmd().execute(this, new Object[0]);
            rollbackTrashIfExist();
            return true;
        }
    }

    public boolean isEditMode() {
        return ArgumentsUtil.getArgValue(getLocationKey(), "editMode", false);
    }

    public int[] loadCount(Context context) {
        boolean z;
        if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_TRASH) && LocationKey.isFamilySharedTrash(getLocationKey())) {
            return new MdeDatabase().getFamilySharedTrashCount();
        }
        if (!SamsungCloudCompat.isSyncOn(context) || Features.isEnabled(Features.IS_UPSM)) {
            z = true;
        } else {
            z = false;
        }
        return TrashProviderFactory.getInstance(context).getTrashCount(z);
    }

    public void onDataPrepared() {
        super.onDataPrepared();
        try {
            GalleryToolbar toolbar = ((ITrashView) this.mView).getToolbar();
            if (toolbar != null) {
                ThreadPool.getInstance().submit(new c(this, toolbar, 1));
            }
        } catch (Exception e) {
            a.r(e, new StringBuilder("onDataPrepared failed e="), this.TAG);
        }
    }

    public void onViewCreate() {
        super.onViewCreate();
        registerContentObserver();
    }

    public void onViewDestroy() {
        super.onViewDestroy();
        unregisterContentObserver();
        this.mHandler.removeCallbacksAndMessages((Object) null);
    }

    public boolean showDeleteAllWarning() {
        return isSelectAll();
    }

    public void updateToolbar(Toolbar toolbar) {
        Context context = getContext();
        if (context != null) {
            toolbar.setTitle((CharSequence) getTitle(context));
            Optional.ofNullable(((ITrashView) this.mView).getAppbarLayout()).ifPresent(new b(this, context));
        }
        if (!isSelectionMode()) {
            setNavigationUpButton(toolbar);
        }
        ThreadPool.getInstance().submit(new c(this, toolbar, 0));
    }
}
