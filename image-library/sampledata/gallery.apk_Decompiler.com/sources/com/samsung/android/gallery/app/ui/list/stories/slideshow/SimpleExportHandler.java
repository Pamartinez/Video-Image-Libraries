package com.samsung.android.gallery.app.ui.list.stories.slideshow;

import J6.a;
import J6.b;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.story.HighlightEncodeCmd;
import com.samsung.android.gallery.app.ui.list.stories.highlight.ExportHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.story.ExportOptions;
import com.samsung.android.gallery.module.story.ExportType;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SimpleExportHandler extends ExportHandler {
    public SimpleExportHandler(IStoryHighlightView iStoryHighlightView, EventContext eventContext) {
        super(iStoryHighlightView, eventContext);
    }

    private ExportOptions createExportOptions(boolean z, int i2, int i7) {
        int i8;
        int i10 = i7 * i2;
        int intValue = ((Integer) this.mEventHandler.requestData(DataRequest.TOTAL_PLAY_TIME, Integer.valueOf(i10))).intValue();
        ExportOptions bgmExtraInfo = new ExportOptions().setOutPath(getOutPath(z)).setTheme(this.mEventHandler.getEffectTheme()).setFilterName(this.mEventHandler.getFilter().getRawName()).setBgmExtraInfo(this.mEventHandler.getBgmExtraInfo());
        if (z) {
            i8 = 1797;
        } else {
            i8 = 1801;
        }
        ExportOptions size = bgmExtraInfo.setRequestCode(i8).setKenBurnsMap(this.mEventHandler.requestData(DataRequest.VIEW_PAGER_ENCODING_INFO, new Object[]{Integer.valueOf(i2)}, new HashMap())).setPlayTimeMs(Math.min(intValue, i10)).setSize(getExportSize(this.mView.getContext()));
        int i11 = -1;
        ExportOptions ratio = size.setRatio(-1);
        if (ExportHandler.HIDDEN_SHARE && z) {
            i11 = configureUniqueKey();
        }
        return ratio.setUniqueKey(i11);
    }

    private String getOutPath(boolean z) {
        if (z) {
            return getSavePath(true);
        }
        return StoryHelper.createSaveFilePath(StorageInfo.getDefault().movies);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startSimpleExport$0(MediaItem mediaItem, ExportType exportType, boolean z, int i2, int i7) {
        if (mediaItem != null) {
            executeExportDone(mediaItem, exportType, false);
            Log.d(this.TAG, "startSimpleExport, item exist", exportType);
            return;
        }
        showWarningCountIf(this.mView.getMediaData());
        pauseHighlightForSave();
        new HighlightEncodeCmd().execute(this.mEventContext, createExportOptions(z, i2, i7));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startSimpleExport$1(boolean z, int i2, int i7) {
        int i8;
        MediaItem loadExportedItem = loadExportedItem(-1);
        setExportedItem(loadExportedItem);
        if (z) {
            i8 = 1802;
        } else {
            i8 = 1801;
        }
        ThreadUtil.postOnUiThread(new b(this, loadExportedItem, ExportType.getType(i8), z, i2, i7));
    }

    private void showWarningCountIf(MediaData mediaData) {
        if (mediaData != null && mediaData.getCount() > 100) {
            Utils.showToast(this.mView.getContext(), "Up to 100 contents are saved as highlights.");
        }
    }

    public boolean isSave(int i2) {
        if (super.isSave(i2) || i2 == 1801) {
            return true;
        }
        return false;
    }

    public void startSimpleExport(boolean z, int i2, int i7) {
        Log.d(this.TAG, "startSimpleExport");
        SimpleThreadPool.getInstance().execute(new a(this, z, i2, i7, 0));
    }
}
