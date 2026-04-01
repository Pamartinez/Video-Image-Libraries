package com.samsung.android.gallery.app.controller.story;

import Ad.C0720a;
import G6.a;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.app.sdk.deepsky.objectcapture.utils.ServiceManagerUtil;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EditNotifiedContentCmd extends BaseCommand {
    private final ArrayList<MediaItem> mCollectedList = new ArrayList<>();
    protected EventContext mHandler;
    private int mPrivateData;
    int mStoryType;

    private boolean collectDataFromCursor(MediaItem mediaItem) {
        Cursor query;
        try {
            query = DbCompat.query(DbKey.STORY_NOTIFICATION_FILES, new a(mediaItem, 4));
            if (query != null) {
                if (query.moveToFirst()) {
                    this.mPrivateData = getPrivateData(query);
                    do {
                        this.mCollectedList.add(MediaItemLoader.load(query));
                    } while (query.moveToNext());
                    query.close();
                    return true;
                }
            }
            if (query == null) {
                return false;
            }
            query.close();
            return false;
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("collectDataFromCursor failed e="), this.TAG);
            return false;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private Intent createAgifIntent(ArrayList<Uri> arrayList, MediaItem mediaItem) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("selectedItems", arrayList);
        Intent intent = new Intent("com.sec.android.mimage.photoretouching.motionphoto");
        intent.putExtras(bundle);
        intent.putExtra("selectedCount", arrayList.size());
        intent.putExtra("story_id", MediaItemStory.getStoryId(mediaItem));
        return intent;
    }

    private Intent createCollageIntent(ArrayList<Uri> arrayList, int i2) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("selectedItems", arrayList);
        Intent intent = new Intent("com.sec.android.mimage.photoretouching.multigrid");
        intent.putExtras(bundle);
        intent.putExtra("selectedCount", arrayList.size());
        if (i2 >= 0) {
            intent.putExtra("layout_number", i2);
        }
        return intent;
    }

    private int getPrivateData(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("__private_data");
        if (columnIndex < 0) {
            return -1;
        }
        return cursor.getInt(columnIndex);
    }

    private boolean kindOfCollage() {
        if (this.mStoryType == StoryType.COLLAGE.getValue() || this.mStoryType == StoryType.COLLAGE_THEN_AND_NOW.getValue() || this.mStoryType == StoryType.REDISCOVER_DAY.getValue()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$onExecute$0(MediaItem mediaItem, Context context, ThreadPool.JobContext jobContext) {
        if (!collectDataFromCursor(mediaItem)) {
            return null;
        }
        executeInternal(context, mediaItem, this.mCollectedList, this.mPrivateData);
        return null;
    }

    private void startAgifEditor(Context context, MediaItem mediaItem) {
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add(Uri.parse(mediaItem.getPath()));
            ((Activity) context).startActivityForResult(createAgifIntent(arrayList, mediaItem), 785);
        } catch (ActivityNotFoundException unused) {
            guideDownloadPackage(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, getContext().getString(R.string.photo_editor));
            Log.d(this.TAG, "Start agif editor : package is not installed.");
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("Start agif editor failed e="), this.TAG);
        }
    }

    private void startCollageEditor(Context context, ArrayList<Uri> arrayList, int i2) {
        try {
            ((Activity) context).startActivityForResult(createCollageIntent(arrayList, i2), 784);
        } catch (ActivityNotFoundException unused) {
            guideDownloadPackage(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, getContext().getString(R.string.photo_editor));
            Log.d(this.TAG, "Start collage editor : package is not installed");
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("Start collage editor failed e="), this.TAG);
        }
    }

    private void startEditor(Context context, MediaItem mediaItem, ArrayList<MediaItem> arrayList, int i2) {
        ArrayList arrayList2 = (ArrayList) arrayList.stream().map(new Gb.a(4)).collect(Collectors.toCollection(new C0720a(1)));
        if (kindOfCollage()) {
            startCollageEditor(context, arrayList2, i2);
        } else if (this.mStoryType == StoryType.AGIF.getValue()) {
            startAgifEditor(context, mediaItem);
        } else if (this.mStoryType == StoryType.VIDEO_COLLAGE.getValue()) {
            startVideoCollageEditor(context, arrayList2);
        }
    }

    public void executeInternal(Context context, MediaItem mediaItem, ArrayList<MediaItem> arrayList, int i2) {
        startEditor(context, mediaItem, arrayList, i2);
    }

    public String getEventId() {
        if (kindOfCollage()) {
            return AnalyticsEventId.EVENT_NOTIFICATION_PREVIEW_COLLAGE_EDIT.toString();
        }
        if (this.mStoryType == StoryType.AGIF.getValue()) {
            return AnalyticsEventId.EVENT_NOTIFICATION_PREVIEW_ANIMATE_EDIT.toString();
        }
        return null;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        Activity activity = getActivity();
        MediaItem mediaItem = objArr[0];
        this.mStoryType = MediaItemStory.getStoryType(mediaItem);
        this.mHandler = eventContext;
        ThreadPool.getInstance().submit(new E5.a(this, mediaItem, activity, 3));
    }

    private void startVideoCollageEditor(Context context, ArrayList<Uri> arrayList) {
    }
}
