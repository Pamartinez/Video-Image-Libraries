package com.samsung.android.gallery.app.controller.externals;

import N2.j;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.samsung.android.gallery.database.dal.local.table.HistoryTable;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.media.CreateMediaHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringResources;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateMovieCmd extends CreateMediaCmd {
    private static final String MOVIE_MAKER_CLASS_NAME;
    private static final String MOVIE_MAKER_PACKAGE_NAME;

    static {
        String str;
        String str2;
        SdkConfig.SEM sem = SdkConfig.SEM.S;
        if (SdkConfig.atLeast(sem)) {
            str = "com.sec.android.app.vepreload";
        } else {
            str = "com.samsung.app.highlightplayer";
        }
        MOVIE_MAKER_PACKAGE_NAME = str;
        if (SdkConfig.atLeast(sem)) {
            str2 = "com.sec.android.app.vepreload.createmovie.activity.HighlightEditActivity";
        } else {
            str2 = "com.samsung.app.highlightplayer.activity.HighlightEditActivity";
        }
        MOVIE_MAKER_CLASS_NAME = str2;
    }

    public CreateMediaHelper.SupportType checkSupportType(MediaItem[] mediaItemArr, boolean z) {
        int i2;
        int i7 = 0;
        while (true) {
            if (z) {
                i2 = getMaxCountToCheck(mediaItemArr);
            } else {
                i2 = mediaItemArr.length;
            }
            if (i7 >= i2) {
                return CreateMediaHelper.SupportType.SUPPORT;
            }
            CreateMediaHelper.SupportType supportType = getSupportType(mediaItemArr[i7]);
            if (supportType != CreateMediaHelper.SupportType.SUPPORT) {
                return supportType;
            }
            i7++;
        }
    }

    public void createMedia(MediaItem[] mediaItemArr) {
        if (isUpsm()) {
            showToast((int) R.string.cannot_use_this_function_while_mpsm_is_on);
        } else if (mediaItemArr == null) {
            Log.d(this.TAG, "selected items are null");
        } else if (guidePackageEnabling(MOVIE_MAKER_PACKAGE_NAME)) {
            Log.w(this.TAG, "goto settings due to package disabled");
        } else {
            try {
                getContext().startActivity(getMovieMakerIntent(mediaItemArr));
            } catch (ActivityNotFoundException unused) {
                String str = this.TAG;
                StringBuilder sb2 = new StringBuilder("Activity Not Found ");
                String str2 = MOVIE_MAKER_PACKAGE_NAME;
                j.y(sb2, str2, str);
                if (Features.isEnabled(Features.SUPPORT_DOWNLOAD_STORY_VIDEO_EDITOR) || Features.isEnabled(Features.SUPPORT_MULTI_VIDEO_EDIT)) {
                    guideDownloadPackage(str2, StringResources.getVideoEditorAppName());
                } else {
                    showToast((int) R.string.no_app_for_action);
                }
            } catch (Exception unused2) {
                Log.e(this.TAG, "There is problem in startMovieMaker");
            }
            addOperationHistory(HistoryTable.ActionKeyword.CREATE_MOVIE, mediaItemArr);
        }
    }

    public /* bridge */ /* synthetic */ void done(MediaItem[] mediaItemArr) {
        super.done(mediaItemArr);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_CREATE_MOVIE.toString();
    }

    public String getExceedMaxCountToastMessage() {
        return getContext().getString(R.string.create_movie_maker_max_reached, new Object[]{Integer.valueOf(getMaxCount())});
    }

    public int getMaxCount() {
        return 60;
    }

    public Intent getMovieMakerIntent(MediaItem[] mediaItemArr) {
        ArrayList arrayList = new ArrayList();
        for (MediaItem mediaItem : mediaItemArr) {
            if (isSupported(mediaItem)) {
                arrayList.add(ContentUri.getUri(mediaItem));
            }
            if (arrayList.size() == 60) {
                break;
            }
        }
        Intent intent = new Intent("android.intent.action.EDIT");
        intent.setComponent(new ComponentName(MOVIE_MAKER_PACKAGE_NAME, MOVIE_MAKER_CLASS_NAME));
        intent.putExtra("create_app", "create_movie");
        intent.putParcelableArrayListExtra("selectedItems", arrayList);
        intent.putExtra("from_empty_list", this.mExecuteWithEmptyList);
        if (this.mFromBixby) {
            intent.putExtra("isAppLaunchedByBixBy", true);
            intent.putExtra("selectedDuration", this.mDuration);
        }
        setIntentWithCommonExtra(intent);
        return intent;
    }

    public CreateMediaHelper.SupportType getSupportType(MediaItem mediaItem) {
        return CreateMediaHelper.supportMovieMaker(mediaItem.getMediaType(), mediaItem.getMimeType(), mediaItem.isCloudOnly());
    }

    public int getTitleRes() {
        return R.string.create_movie_menu;
    }

    public /* bridge */ /* synthetic */ boolean isSupported(MediaItem mediaItem) {
        return super.isSupported(mediaItem);
    }

    public /* bridge */ /* synthetic */ void showExceedMaxCountToast(Context context) {
        super.showExceedMaxCountToast(context);
    }
}
