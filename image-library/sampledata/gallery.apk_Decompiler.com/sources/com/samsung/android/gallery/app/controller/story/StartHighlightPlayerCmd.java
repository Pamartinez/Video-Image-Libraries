package com.samsung.android.gallery.app.controller.story;

import A.a;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringResources;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartHighlightPlayerCmd extends BaseCommand {
    private static final String CLASS_NAME_HV;
    private static final String CLASS_NAME_HV_FOR_DREAM;
    protected static final String PACKAGE_NAME_HV;
    private static final boolean SUGGEST_EFFECT_ON_STORY = PreferenceFeatures.isEnabled(PreferenceFeatures.SuggestedEffectOnStory);

    static {
        String str;
        String str2;
        SdkConfig.SEM sem = SdkConfig.SEM.S;
        if (SdkConfig.atLeast(sem)) {
            str = "com.sec.android.app.vepreload";
        } else {
            str = "com.samsung.app.highlightplayer";
        }
        PACKAGE_NAME_HV = str;
        CLASS_NAME_HV = C0212a.A(str, ".activity.HighlightPlayerActivity");
        if (SdkConfig.atLeast(sem)) {
            str2 = "com.sec.android.app.vepreload.createmovie.activity.HighlightEditActivity";
        } else {
            str2 = "com.samsung.app.highlightplayer.activity.HighlightEditActivity";
        }
        CLASS_NAME_HV_FOR_DREAM = str2;
    }

    private ArrayList<Uri> getContentsUris(EventContext eventContext) {
        FileItemInterface fileItemInterface;
        MediaItem[] allItems = eventContext.getAllItems();
        ArrayList<Uri> arrayList = new ArrayList<>();
        for (MediaItem mediaItem : allItems) {
            if (mediaItem != null) {
                if (SUGGEST_EFFECT_ON_STORY) {
                    fileItemInterface = MediaItemStory.getEffectItem(mediaItem);
                } else {
                    fileItemInterface = null;
                }
                if (fileItemInterface == null || !FileUtils.exists(fileItemInterface.getPath())) {
                    arrayList.add(ContentUri.getUri(mediaItem));
                } else {
                    arrayList.add(Uri.fromFile(new File(fileItemInterface.getPath())));
                }
            }
        }
        Log.d(this.TAG, "getAllItems size=" + arrayList.size());
        return arrayList;
    }

    public Intent createIntent(EventContext eventContext, int i2) {
        Intent intent = new Intent();
        intent.putExtra("eventid", String.valueOf(i2));
        intent.putParcelableArrayListExtra("selectedItems", getContentsUris(eventContext));
        intent.putExtra("create_app", "edit_movie");
        String str = PACKAGE_NAME_HV;
        intent.setClassName(str, CLASS_NAME_HV_FOR_DREAM);
        setIntentWithCommonExtra(intent);
        if (PackageMonitorCompat.getInstance().resolveActivity(intent, 65536) == null) {
            intent.setClassName(str, CLASS_NAME_HV);
        }
        return intent;
    }

    public String getEventId() {
        if (PreferenceFeatures.OneUi5x.STORY_ONE_UI_50) {
            return AnalyticsEventId.EVENT_STORY_HIGHLIGHT_MOVE_TO_REEL.toString();
        }
        if (PreferenceFeatures.OneUi30.MEMORIES) {
            return AnalyticsEventId.EVENT_MENU_CREATE_HIGHLIGHT_REEL_STORY_PICTURE.toString();
        }
        return AnalyticsEventId.EVENT_CHANNEL_DETAIL_PLAY_HIGHLIGHT_VIDEO.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        Context context = getContext();
        if (isUpsm()) {
            showToast((int) R.string.cannot_use_this_function_while_mpsm_is_on);
        } else {
            startHighlightPlayer(context, createIntent(eventContext, objArr[0].intValue()));
        }
    }

    public final void showErrorMessage(Exception exc) {
        showToast((int) R.string.no_app_for_action);
        a.s(exc, new StringBuilder("StartHighlightPlayerCmd failed e="), this.TAG);
    }

    public void startHighlightPlayer(Context context, Intent intent) {
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            if (Features.isEnabled(Features.SUPPORT_DOWNLOAD_STORY_VIDEO_EDITOR)) {
                guideDownloadPackage(PACKAGE_NAME_HV, StringResources.getVideoEditorAppName());
            } else {
                showErrorMessage(e);
            }
        } catch (SecurityException e7) {
            showErrorMessage(e7);
        }
    }
}
