package com.samsung.android.gallery.bixby.bixbycard;

import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.samsung.android.gallery.support.providers.CmhUri;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.spage.card.CardContent;
import com.samsung.android.sdk.spage.card.CardContentManager;
import com.samsung.android.sdk.spage.card.CardContentProvider;
import com.samsung.android.sdk.spage.card.MediaData;
import com.samsung.android.sdk.spage.card.ShareData;
import com.samsung.android.sdk.spage.card.TextData;
import com.samsung.android.sdk.spage.card.base.FieldData;
import com.samsung.android.sdk.spage.card.event.Event;
import e5.C0451a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GalleryCardProvider extends CardContentProvider {
    private int[] mIds;
    private CardContentManager mManager;

    public GalleryCardProvider() {
        Log.bx("GalleryCardProvider", "GalleryCardProvider");
    }

    private boolean checkProviders() {
        Context appContext = AppResources.getAppContext();
        ContentProviderClient acquireContentProviderClient = appContext.getContentResolver().acquireContentProviderClient(CmhUri.getStory());
        if (acquireContentProviderClient != null) {
            acquireContentProviderClient.close();
            ContentProviderClient acquireContentProviderClient2 = appContext.getContentResolver().acquireContentProviderClient(MediaUri.getInstance().getSecMediaUri());
            if (acquireContentProviderClient2 != null) {
                acquireContentProviderClient2.close();
                return true;
            }
            Log.bxe("GalleryCardProvider", "onUpdate called but SEC MP not ready");
            return false;
        }
        Log.bxe("GalleryCardProvider", "onUpdate called but CMH not ready");
        return false;
    }

    private CardContent getCardContentData(List<GalleryCardData> list, boolean z) {
        CardContent cardContent = new CardContent(99999908);
        if (list == null) {
            return cardContent;
        }
        ArrayList arrayList = new ArrayList();
        for (GalleryCardData next : list) {
            MediaData.MediaItemData mediaItemData = new MediaData.MediaItemData(next.getContentUri(), next.getMimeType());
            mediaItemData.setLaunchIntent(getIntent(next, z));
            arrayList.add(mediaItemData);
        }
        MediaData mediaData = new MediaData();
        mediaData.setList(arrayList);
        if (!z) {
            cardContent.put(CardContent.FIELD_2, (FieldData) mediaData.setShareData(new ShareData()));
            return cardContent;
        }
        cardContent.put(CardContent.FIELD_2, (FieldData) mediaData);
        return cardContent;
    }

    private CardContent getEmptyCardData() {
        CardContent cardContent = new CardContent(99999908);
        cardContent.setExtraState(CardContent.NO_CONTENTS);
        return cardContent;
    }

    private Intent getIntent(GalleryCardData galleryCardData, boolean z) {
        if (z) {
            Intent intent = new Intent("com.android.gallery.action.SHORTCUT_VIEW");
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.external.GalleryExternalActivity");
            intent.putExtra("start-story-detail-view", true);
            intent.putExtra("key-story-album-bucket-id", galleryCardData.getStoryId());
            intent.putExtra("bixby_locationKey", "SEARCH_BY_STORY");
            intent.putExtra("from_bixby", true);
            return intent;
        } else if (galleryCardData.getMimeType().contains("video")) {
            Intent intent2 = new Intent();
            intent2.setAction("android.intent.action.VIEW");
            intent2.setDataAndType(Uri.parse(galleryCardData.getContentUri()), galleryCardData.getMimeType());
            intent2.addFlags(268435456);
            return intent2;
        } else {
            Intent intent3 = new Intent("com.android.gallery.action.SHORTCUT_ITEM_VIEW");
            intent3.setClassName(AppResources.getAppContext().getPackageName(), "com.samsung.android.gallery.app.activity.external.GalleryExternalActivity");
            intent3.addFlags(268468224);
            intent3.addCategory("android.intent.category.DEFAULT");
            intent3.setDataAndType(Uri.parse(galleryCardData.getContentUri()), galleryCardData.getMimeType());
            intent3.putExtra("view_item", true);
            return intent3;
        }
    }

    private boolean isContainsGalleryId() {
        for (int i2 : this.mIds) {
            if (i2 == 99999908) {
                return true;
            }
        }
        return false;
    }

    private boolean updatePhotoData(CardContentManager cardContentManager) {
        List<GalleryCardData> photoData = PhotoDataFetcher.getInstance().getPhotoData();
        if (photoData == null || photoData.isEmpty()) {
            return false;
        }
        cardContentManager.updateCardContent(AppResources.getAppContext(), getCardContentData(photoData, false));
        return true;
    }

    private boolean updateStoryData(CardContentManager cardContentManager) {
        GalleryCardData storyData = StoryDataFetcher.getInstance().getStoryData(AppResources.getAppContext());
        if (storyData == null) {
            return false;
        }
        CardContent cardContentData = getCardContentData(new ArrayList(Collections.singletonList(storyData)), true);
        String title = storyData.getTitle();
        if (title != null) {
            cardContentData.put(CardContent.FIELD_1, (FieldData) new TextData().setText(title));
        }
        cardContentManager.updateCardContent(AppResources.getAppContext(), cardContentData);
        return true;
    }

    public void onDisabled(Context context, int[] iArr) {
        Log.bx("GalleryCardProvider", "onDisabled");
    }

    public void onEnabled(Context context, int[] iArr) {
        Log.bx("GalleryCardProvider", "onEnabled");
    }

    public void onInstantUpdate(Context context, CardContentManager cardContentManager, int i2, int i7) {
        Log.bx("GalleryCardProvider", "onInstantUpdate : cardId = " + i2 + ", update code = " + i7);
    }

    public void onReceiveEvent(Context context, CardContentManager cardContentManager, int i2, Event event) {
        Log.bx("GalleryCardProvider", "onReceiveEvent cardId = " + i2 + " event = " + event);
    }

    public void onUpdate(Context context, CardContentManager cardContentManager, int[] iArr) {
        this.mManager = cardContentManager;
        this.mIds = iArr;
        ThreadUtil.postOnBgThread(new C0451a(5, this));
    }

    public void updateCard() {
        try {
            if (AppResources.getAppContext() != null) {
                if (this.mManager != null) {
                    if (!isContainsGalleryId()) {
                        Log.bxe("GalleryCardProvider", "onUpdate called but gallery card update does not requested");
                        return;
                    } else if (checkProviders()) {
                        if (updateStoryData(this.mManager)) {
                            Log.bx("GalleryCardProvider", "story data updated");
                            return;
                        } else if (updatePhotoData(this.mManager)) {
                            Log.bx("GalleryCardProvider", "photo data updated");
                            return;
                        } else {
                            Log.bx("GalleryCardProvider", "empty card updated");
                            this.mManager.updateCardContent(AppResources.getAppContext(), getEmptyCardData());
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
            Log.bxe("GalleryCardProvider", "onUpdate called but context or manager is null");
        } catch (Exception e) {
            Log.bxe("GalleryCardProvider", "update card failed. " + e.getMessage());
        }
    }
}
