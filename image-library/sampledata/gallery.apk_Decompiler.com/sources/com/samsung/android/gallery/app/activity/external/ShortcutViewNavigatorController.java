package com.samsung.android.gallery.app.activity.external;

import A.a;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import c0.C0086a;
import com.samsung.android.gallery.app.activity.ViewNavigatorController;
import com.samsung.android.gallery.app.activity.abstraction.IGalleryActivityView;
import com.samsung.android.gallery.app.ui.list.albums.pictures.widget.WidgetAlbumPicturesFragment;
import com.samsung.android.gallery.app.ui.list.search.toolbar.FloatingRecommendationLauncher;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.local.LocalAlbumsApi;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.module.abstraction.FlipCoverSavedData;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.album.ShortcutHelper;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.creature.pet.PetDataManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.dataset.AlbumDataHelper;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.foldable.FoldUtils;
import com.samsung.android.gallery.module.search.root.SearchLocationKeyBuilder;
import com.samsung.android.gallery.module.search.rubin.SearchWordCollector;
import com.samsung.android.gallery.module.story.StoryUriBuilder;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.viewer.VuLauncher;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.helper.DrawerUtil;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sum.core.message.Message;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ShortcutViewNavigatorController extends ViewNavigatorController {
    private int mCoverWidgetDataReloadCount = 0;
    private MediaData mMediaData;
    private FlipCoverSavedData mRestartSavedDataFromFlipCover;
    private boolean mViewerLaunched;

    public ShortcutViewNavigatorController(Blackboard blackboard, IGalleryActivityView iGalleryActivityView) {
        super(blackboard, iGalleryActivityView);
    }

    private boolean findAlbumFromLocalDB(String str, int i2) {
        Cursor albumCursor;
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(i2));
        try {
            albumCursor = new LocalAlbumsApi().getAlbumCursor(true, arrayList);
            if (albumCursor != null) {
                if (albumCursor.getCount() > 0 && albumCursor.moveToFirst() && albumCursor.getInt(albumCursor.getColumnIndex("__bucketID")) == i2) {
                    publishData(albumCursor, str, true);
                    albumCursor.close();
                    return true;
                }
            }
            if (albumCursor == null) {
                return false;
            }
            albumCursor.close();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private UriBuilder getSearchKeywordBaseUriBuilder(String str) {
        return new UriBuilder(LocationKey.getSearchLocationKey("location://search/fileList/Keyword", str)).appendArg("sub", str).appendArg("title", str).appendArg("term", "key_word").appendArg("collect_keyword", str);
    }

    private int getShortcutAlbumId() {
        LaunchIntent launchIntent = getLaunchIntent();
        int shortcutAlbumId = launchIntent.getShortcutAlbumId();
        if (shortcutAlbumId == 0) {
            if (launchIntent.isRecentAlbumShortcut()) {
                return BucketUtils.VirtualBucketHolder.recent;
            }
            if (launchIntent.isFavoriteAlbumShortcut()) {
                return BucketUtils.VirtualBucketHolder.favorite;
            }
            if (launchIntent.getUriData() != null) {
                Uri uriData = launchIntent.getUriData();
                if (uriData.toString().startsWith("/union/")) {
                    return UnsafeCast.toInt(uriData.getLastPathSegment());
                }
            }
        }
        return shortcutAlbumId;
    }

    private String getTargetKeyFromBixby(String str) {
        if (str == null) {
            return null;
        }
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1925070546:
                if (str.equals("SEARCH_BY_GROUP")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1913928796:
                if (str.equals("SEARCH_BY_STORY")) {
                    c5 = 1;
                    break;
                }
                break;
            case -1728914863:
                if (str.equals("SHOW_STORY_VIEW")) {
                    c5 = 2;
                    break;
                }
                break;
            case -1724474916:
                if (str.equals("SEARCH_BY_NAME")) {
                    c5 = 3;
                    break;
                }
                break;
            case -1701229035:
                if (str.equals("SHOW_TIME_VIEW")) {
                    c5 = 4;
                    break;
                }
                break;
            case -834219538:
                if (str.equals("SHOW_TRASH_VIEW")) {
                    c5 = 5;
                    break;
                }
                break;
            case -149210057:
                if (str.equals("SHOW_SLIDE_SHOW_VIEW")) {
                    c5 = 6;
                    break;
                }
                break;
            case 225358103:
                if (str.equals("SHOW_ALBUM_VIEW")) {
                    c5 = 7;
                    break;
                }
                break;
            case 465880015:
                if (str.equals("SEARCH_BY_CATEGORY")) {
                    c5 = 8;
                    break;
                }
                break;
            case 2124396267:
                if (str.equals("SHOW_SEARCH_SUGGESTION_VIEW")) {
                    c5 = 9;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return "location://folder/root";
            case 1:
                return LocationKey.getStoryPicturesAliasKey();
            case 2:
                return "location://story/albums";
            case 3:
                return "location://albums/fileList";
            case 4:
                return "location://timeline";
            case 5:
                return "location://trash";
            case 6:
                return ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(ArgumentsUtil.appendUriKey("location://timeline", "/slideshow", true), Message.KEY_POSITION, "0"), "media_item", (String) null);
            case 7:
                return "location://albums";
            case 8:
                String bixbySearchKeyword = getLaunchIntent().getBixbySearchKeyword();
                if (bixbySearchKeyword != null) {
                    return getSearchKeywordBaseUriBuilder(bixbySearchKeyword).appendArg("from_bixby", true).appendArg("collect_type", SearchWordCollector.Type.BIXBY_VOICE.toString()).build();
                }
                return "location://search";
            case 9:
                return "location://search";
            default:
                return null;
        }
    }

    private boolean isSlideShow(String str) {
        if (str == null || !str.startsWith("location://timeline/slideshow")) {
            return false;
        }
        return true;
    }

    private boolean isUpsm() {
        return Features.isEnabled(Features.IS_UPSM);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadShortcutItem$0(Uri uri, MediaItem[] mediaItemArr, CountDownLatch countDownLatch) {
        Cursor query;
        try {
            query = DbCompat.query(new QueryParams(DbKey.ALL_PICTURES).addUri(uri));
            if (query != null) {
                if (query.getCount() != 0) {
                    MediaItem load = MediaItemLoader.load(query, 0);
                    mediaItemArr[0] = load;
                    this.mBlackboard.publish("data://viewer_first_data", load);
                    this.mBlackboard.publish("data://viewer_first_bitmap", new Object[]{load, ThumbnailLoader.getInstance().loadThumbnailSync(load, ThumbKind.MEDIUM_KIND)});
                    this.mBlackboard.publish(DataKey.DATA("location://quickView"), mediaItemArr);
                    query.close();
                    countDownLatch.countDown();
                    return;
                }
            }
            ShortcutHelper.getInstance().handleResultCanceled(this.mBlackboard);
            if (query != null) {
                query.close();
            }
            countDownLatch.countDown();
            return;
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "loadShortcutItem failed", (Throwable) e);
            ShortcutHelper.getInstance().handleResultCanceled(this.mBlackboard);
            countDownLatch.countDown();
            return;
        } catch (Throwable th) {
            countDownLatch.countDown();
            throw th;
        }
        throw th;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveToAlbumsForLocalDbUpdate$6(String str, int i2) {
        this.mCoverWidgetDataReloadCount++;
        publishAlbumData(str, i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveToAlbumsForLocalDbUpdate$7(ImageView imageView) {
        Log.d(this.TAG, "BackGroundView removed ", ViewUtils.getVisibilityCode(imageView), Integer.valueOf(ViewUtils.getWidth(imageView)), Integer.valueOf(ViewUtils.getHeight(imageView)));
        ViewUtils.removeSelf(imageView);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveToAlbumsForLocalDbUpdate$8(String str, int i2) {
        ImageView imageView = new ImageView(this.mActivityView.getContext());
        imageView.setBackgroundResource(R.color.daynight_default_background);
        Log.d(this.TAG, "BackGroundView create");
        ((ViewGroup) this.mActivityView.getActivity().getWindow().getDecorView()).addView(imageView, new ViewGroup.LayoutParams(-1, -1));
        ThreadUtil.postOnUiThreadDelayed(new p(this, str, i2, 0), 500);
        ThreadUtil.postOnUiThreadDelayed(new e(2, this, imageView), 1000);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveToTrash$4() {
        try {
            this.mBlackboard.erase(DataKey.DATA_CURSOR("location://trash"));
            this.mBlackboard.erase(CommandKey.DATA_REQUEST("location://trash"));
        } catch (Exception e) {
            String str = this.TAG;
            Log.e(str, "unable to remove previous data, e=" + e.getMessage());
        } catch (Throwable th) {
            this.mBlackboard.post("command://MoveURL", "location://trash");
            throw th;
        }
        this.mBlackboard.post("command://MoveURL", "location://trash");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:57:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ void lambda$publishAlbumData$5(int r7, java.lang.String r8) {
        /*
            r6 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r7)
            r0.add(r1)
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r1 = new com.samsung.android.gallery.database.dal.abstraction.query.QueryParams
            java.lang.String r2 = com.samsung.android.gallery.database.dal.abstraction.DbKey.ALBUMS
            r1.<init>((java.lang.String) r2)
            r2 = 1
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r1 = r1.setShowHidden(r2)
            boolean r2 = com.samsung.android.gallery.support.utils.PreferenceFeatures.OneUi5x.MX_ALBUMS
            r3 = 0
            if (r2 == 0) goto L_0x005f
            java.lang.String r2 = "type"
            int r2 = com.samsung.android.gallery.support.utils.ArgumentsUtil.getArgValue((java.lang.String) r8, (java.lang.String) r2, (int) r3)
            boolean r2 = com.samsung.android.gallery.database.dbtype.AlbumType.isMergedAlbum(r2)
            if (r2 == 0) goto L_0x005f
            java.lang.String r2 = "ids"
            r4 = 0
            java.lang.String r2 = com.samsung.android.gallery.support.utils.ArgumentsUtil.getArgValue((java.lang.String) r8, (java.lang.String) r2, (java.lang.String) r4)
            if (r2 == 0) goto L_0x005b
            java.lang.String r4 = ","
            java.lang.String[] r2 = r2.split(r4)
            java.util.stream.Stream r2 = java.util.Arrays.stream(r2)
            A8.b r4 = new A8.b
            r5 = 3
            r4.<init>(r5)
            java.util.stream.IntStream r2 = r2.mapToInt(r4)
            java.util.stream.Stream r2 = r2.boxed()
            java.util.stream.Collector r4 = java.util.stream.Collectors.toList()
            java.lang.Object r2 = r2.collect(r4)
            java.util.List r2 = (java.util.List) r2
            r0.addAll(r2)
            r1.addAlbumIds((java.util.Collection<java.lang.Integer>) r2)
            goto L_0x0062
        L_0x005b:
            r1.addAlbumId(r7)
            goto L_0x0062
        L_0x005f:
            r1.addAlbumId(r7)
        L_0x0062:
            android.database.Cursor r1 = com.samsung.android.gallery.database.dal.DbCompat.query((com.samsung.android.gallery.database.dal.abstraction.query.QueryParams) r1)     // Catch:{ Exception -> 0x008f }
            if (r1 == 0) goto L_0x0094
            int r2 = r1.getCount()     // Catch:{ all -> 0x0092 }
            if (r2 <= 0) goto L_0x0094
            boolean r2 = r1.moveToFirst()     // Catch:{ all -> 0x0092 }
            if (r2 == 0) goto L_0x0094
            java.lang.String r2 = "__albumID"
            int r2 = r1.getColumnIndex(r2)     // Catch:{ all -> 0x0092 }
            int r2 = r1.getInt(r2)     // Catch:{ all -> 0x0092 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x0092 }
            boolean r0 = r0.contains(r2)     // Catch:{ all -> 0x0092 }
            if (r0 == 0) goto L_0x0094
            r6.publishData(r1, r8, r3)     // Catch:{ all -> 0x0092 }
        L_0x008b:
            r1.close()     // Catch:{ Exception -> 0x008f }
            return
        L_0x008f:
            r8 = move-exception
            goto L_0x0107
        L_0x0092:
            r8 = move-exception
            goto L_0x00fc
        L_0x0094:
            boolean r0 = r6.findAlbumFromLocalDB(r8, r7)     // Catch:{ all -> 0x0092 }
            if (r0 == 0) goto L_0x00ac
            java.lang.String r8 = r6.TAG     // Catch:{ all -> 0x0092 }
            java.lang.String r0 = "found album from local db "
            java.lang.Integer r2 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x0092 }
            java.lang.Object[] r2 = new java.lang.Object[]{r2}     // Catch:{ all -> 0x0092 }
            com.samsung.android.gallery.support.utils.Log.d(r8, r0, r2)     // Catch:{ all -> 0x0092 }
            if (r1 == 0) goto L_0x00fb
            goto L_0x008b
        L_0x00ac:
            java.lang.String r0 = r6.TAG     // Catch:{ all -> 0x0092 }
            java.lang.String r2 = "publishAlbumData : Fail to find an album. "
            java.lang.Integer r3 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x0092 }
            if (r1 == 0) goto L_0x00bb
            int r4 = r1.getCount()     // Catch:{ all -> 0x0092 }
            goto L_0x00bc
        L_0x00bb:
            r4 = -1
        L_0x00bc:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0092 }
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r4}     // Catch:{ all -> 0x0092 }
            com.samsung.android.gallery.support.utils.Log.w((java.lang.CharSequence) r0, (java.lang.String) r2, (java.lang.Object[]) r3)     // Catch:{ all -> 0x0092 }
            com.samsung.android.gallery.module.abstraction.LaunchIntent r0 = r6.getLaunchIntent()     // Catch:{ all -> 0x0092 }
            boolean r0 = r0.getVirtualAlbum()     // Catch:{ all -> 0x0092 }
            if (r0 != 0) goto L_0x00f5
            com.samsung.android.gallery.support.blackboard.Blackboard r0 = r6.mBlackboard     // Catch:{ all -> 0x0092 }
            boolean r0 = com.samsung.android.gallery.module.abstraction.LaunchIntent.isFlipCoverWidgetTheme(r0)     // Catch:{ all -> 0x0092 }
            if (r0 == 0) goto L_0x00eb
            com.samsung.android.gallery.app.activity.abstraction.IGalleryActivityView r0 = r6.mActivityView     // Catch:{ all -> 0x0092 }
            android.app.Activity r0 = r0.getActivity()     // Catch:{ all -> 0x0092 }
            boolean r0 = com.samsung.android.gallery.module.foldable.FoldUtils.isFlipCoverScreen(r0)     // Catch:{ all -> 0x0092 }
            if (r0 == 0) goto L_0x00eb
            int r0 = r6.mCoverWidgetDataReloadCount     // Catch:{ all -> 0x0092 }
            r2 = 3
            if (r0 >= r2) goto L_0x00eb
            goto L_0x00f5
        L_0x00eb:
            com.samsung.android.gallery.module.album.ShortcutHelper r8 = com.samsung.android.gallery.module.album.ShortcutHelper.getInstance()     // Catch:{ all -> 0x0092 }
            com.samsung.android.gallery.support.blackboard.Blackboard r0 = r6.mBlackboard     // Catch:{ all -> 0x0092 }
            r8.handleResultCanceled(r0)     // Catch:{ all -> 0x0092 }
            goto L_0x00f8
        L_0x00f5:
            r6.moveToAlbumsForLocalDbUpdate(r8, r7)     // Catch:{ all -> 0x0092 }
        L_0x00f8:
            if (r1 == 0) goto L_0x00fb
            goto L_0x008b
        L_0x00fb:
            return
        L_0x00fc:
            if (r1 == 0) goto L_0x0106
            r1.close()     // Catch:{ all -> 0x0102 }
            goto L_0x0106
        L_0x0102:
            r0 = move-exception
            r8.addSuppressed(r0)     // Catch:{ Exception -> 0x008f }
        L_0x0106:
            throw r8     // Catch:{ Exception -> 0x008f }
        L_0x0107:
            java.lang.String r0 = r6.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "publishAlbumData : failed query > "
            r1.<init>(r2)
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            com.samsung.android.gallery.support.utils.Log.w((java.lang.CharSequence) r0, (java.lang.String) r7, (java.lang.Throwable) r8)
            com.samsung.android.gallery.module.album.ShortcutHelper r7 = com.samsung.android.gallery.module.album.ShortcutHelper.getInstance()
            com.samsung.android.gallery.support.blackboard.Blackboard r6 = r6.mBlackboard
            r7.handleResultCanceled(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.activity.external.ShortcutViewNavigatorController.lambda$publishAlbumData$5(int, java.lang.String):void");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishEventForCreaturePictures$1(LaunchIntent launchIntent) {
        MediaItem mediaItem;
        long creatureId = launchIntent.getCreatureId();
        if (launchIntent.getCreatureType() == 1) {
            mediaItem = PetDataManager.loadHeaderItem(IdentityCreatureUtil.createPetUnifiedId(creatureId));
        } else {
            mediaItem = PeopleDataManager.loadHeaderItem(IdentityCreatureUtil.createPeopleUnifiedId(creatureId));
        }
        if (mediaItem != null) {
            Blackboard blackboard = this.mBlackboard;
            blackboard.post("command://MoveURL", new SearchLocationKeyBuilder(mediaItem, blackboard).build());
            return;
        }
        Log.w((CharSequence) this.TAG, a.e(creatureId, "Creature id (", ") not existed!!"), Integer.valueOf(launchIntent.getCreatureType()));
        Utils.showToast((Context) this.mActivityView.getActivity(), (int) R.string.no_people_found);
        this.mBlackboard.post("command://request_suicide", (Object) null);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ void lambda$startStoryPicturesFromBixby$3() {
        /*
            r3 = this;
            com.samsung.android.gallery.module.abstraction.LaunchIntent r0 = r3.getLaunchIntent()
            int r0 = r0.getStoryAlbumBucketId()
            java.lang.String r1 = com.samsung.android.gallery.database.dal.abstraction.DbKey.STORIES
            com.samsung.android.gallery.app.activity.external.s r2 = new com.samsung.android.gallery.app.activity.external.s
            r2.<init>(r0)
            android.database.Cursor r1 = com.samsung.android.gallery.database.dal.DbCompat.query(r1, r2)
            if (r1 == 0) goto L_0x002a
            boolean r2 = r1.moveToNext()     // Catch:{ all -> 0x0020 }
            if (r2 == 0) goto L_0x002a
            com.samsung.android.gallery.module.data.MediaItem r2 = com.samsung.android.gallery.module.data.MediaItemBuilder.create((android.database.Cursor) r1)     // Catch:{ all -> 0x0020 }
            goto L_0x002b
        L_0x0020:
            r3 = move-exception
            r1.close()     // Catch:{ all -> 0x0025 }
            goto L_0x0029
        L_0x0025:
            r0 = move-exception
            r3.addSuppressed(r0)
        L_0x0029:
            throw r3
        L_0x002a:
            r2 = 0
        L_0x002b:
            if (r1 == 0) goto L_0x0030
            r1.close()
        L_0x0030:
            boolean r1 = com.samsung.android.gallery.module.data.MediaItemStory.isRecap(r2)
            if (r1 == 0) goto L_0x003a
            r3.moveToRecap(r2)
            return
        L_0x003a:
            r3.moveToStoryHighlight(r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.activity.external.ShortcutViewNavigatorController.lambda$startStoryPicturesFromBixby$3():void");
    }

    private void loadShortcutItem() {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = true;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Uri uriData = getLaunchIntent().getUriData();
        Log.d(this.TAG, "loadShortcutItem {" + uriData + "}");
        MediaItem[] mediaItemArr = new MediaItem[1];
        SimpleThreadPool.getInstance().execute(new r(this, uriData, mediaItemArr, countDownLatch));
        try {
            countDownLatch.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
            Log.e(this.TAG, "loadShortcutItem time-out");
        }
        String str = this.TAG;
        StringBuilder sb2 = new StringBuilder("loadShortcutItem ViewerData{");
        if (mediaItemArr[0] == null) {
            z = false;
        }
        sb2.append(z);
        sb2.append("} +");
        sb2.append(System.currentTimeMillis() - currentTimeMillis);
        Log.p(str, sb2.toString());
        if (mediaItemArr[0] != null) {
            new VuLauncher(this.mBlackboard).launch("location://quickView", 0, mediaItemArr[0]);
        }
    }

    private boolean moveToAlbumPictures() {
        LaunchIntent launchIntent = getLaunchIntent();
        int shortcutAlbumId = launchIntent.getShortcutAlbumId();
        publishAlbumData(new UriBuilder("location://albums/fileList").appendArg("id", shortcutAlbumId).appendArg("type", launchIntent.getAlbumType()).appendArg("ids", launchIntent.getAlbumBucketIds()).appendArg("filterStorageType", ((Integer) launchIntent.getExtra("filterStorageType", 0)).intValue()).appendArg("shortcut_album", false).build(), shortcutAlbumId);
        return true;
    }

    private void moveToAlbumsForLocalDbUpdate(String str, int i2) {
        preloadDataCursor("location://albums");
        this.mBlackboard.post("command://MoveURL", "location://albums");
        Log.w(this.TAG, "MOVE_URL to albums temporary for album local db update");
        ThreadUtil.runOnUiThread(new p(this, str, i2, 2));
    }

    private boolean moveToFolder() {
        int shortcutAlbumId = getLaunchIntent().getShortcutAlbumId();
        this.mBlackboard.post("command://MoveURL", ArgumentsUtil.appendArgs(C0086a.i(shortcutAlbumId, "location://folder/root/"), "id", String.valueOf(shortcutAlbumId)));
        return true;
    }

    private void moveToRecap(MediaItem mediaItem) {
        this.mBlackboard.post("command://MoveURL", new StoryUriBuilder(this.mBlackboard, mediaItem, true, "location://recap").withFromLocation("location://story/albums").appendArg("media_item", BlackboardUtils.publishMediaItem(this.mBlackboard, mediaItem)).build());
    }

    private void moveToStoryHighlight(MediaItem mediaItem, int i2) {
        this.mBlackboard.post("command://MoveURL", new StoryUriBuilder(this.mBlackboard, (ThumbnailInterface) mediaItem).withStoryId(i2).build());
    }

    private boolean moveToTrash() {
        ThreadUtil.postOnBgThread(new q(this, 1));
        return true;
    }

    private void publishAlbumData(String str, int i2) {
        SimpleThreadPool.getInstance().execute(new p(i2, this, str));
    }

    private void publishData(Cursor cursor, String str, boolean z) {
        MediaItem mediaItem;
        if (cursor == null || cursor.getCount() <= 0) {
            ShortcutHelper.getInstance().handleResultCanceled(this.mBlackboard);
            return;
        }
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || !AlbumType.isMergedAlbum(ArgumentsUtil.getArgValue(str, "type", 0))) {
            if (z) {
                mediaItem = MediaItemBuilder.createAlbumCover(cursor);
            } else {
                mediaItem = MediaItemLoader.load(cursor, 0);
            }
            if (PreferenceFeatures.OneUi5x.MX_ALBUMS && AlbumType.isAutoAlbum(ArgumentsUtil.getArgValue(str, "type", 0))) {
                mediaItem.setAlbumType(AlbumType.AutoUpdated);
            } else if (BucketUtils.isVirtualAlbum(mediaItem.getAlbumID())) {
                mediaItem.setAlbumType(AlbumType.Virtual);
            }
        } else {
            int argValue = ArgumentsUtil.getArgValue(str, "id", 0);
            mediaItem = new AlbumDataHelper("location://albums/fileList").createNameMergedItem(argValue, cursor);
            mediaItem.setAlbumShowInfo(ArgumentsUtil.getArgValue(str, "show_album_info", true));
            str = ArgumentsUtil.removeArg(ArgumentsUtil.appendArgs(str, "mergedAlbumId", String.valueOf(argValue)), "id");
        }
        if (mediaItem.isAlbumHide()) {
            Log.d(this.TAG, "publishData skip by hidden album");
            ShortcutHelper.getInstance().handleResultCanceled(this.mBlackboard, true);
            return;
        }
        if (((Boolean) getLaunchIntent().getExtra("key-is-custom-cover", Boolean.FALSE)).booleanValue()) {
            mediaItem = AlbumDataHelper.createCoverItem(mediaItem, getLaunchIntent().getUriData().toString(), (String) getLaunchIntent().getExtra("key-is-custom-cover-rect", null));
        }
        this.mBlackboard.publish("data://albums/current", mediaItem);
        this.mBlackboard.post("command://MoveURL", str);
        if (this.mRestartSavedDataFromFlipCover != null && LaunchIntent.isFlipCoverWidgetTheme(this.mBlackboard)) {
            String str2 = this.mRestartSavedDataFromFlipCover.location;
            if (str2 != null) {
                if (Logger.isAllowPrivateLog()) {
                    Log.p(this.TAG, "publish saved viewer location : ".concat(str2));
                }
                this.mBlackboard.post("command://MoveURL", str2);
                return;
            }
            Log.e(this.TAG, "fail to recover saved viewer");
        }
    }

    private boolean publishEventForBixbyActivity() {
        String bixbyLocationKey = getLaunchIntent().getBixbyLocationKey();
        String targetKeyFromBixby = getTargetKeyFromBixby(bixbyLocationKey);
        if (TextUtils.isEmpty(targetKeyFromBixby)) {
            a.u("publishEventForBixbyActivity failed. Bixby DeepLink failure LK=", bixbyLocationKey, this.TAG);
            return false;
        } else if (LocationKey.isAlbumPictures(targetKeyFromBixby)) {
            return moveToAlbumPictures();
        } else {
            if (LocationKey.isFolder(targetKeyFromBixby)) {
                return moveToFolder();
            }
            if (LocationKey.isStoryPictures(targetKeyFromBixby) || LocationKey.isStoryHighlight(targetKeyFromBixby)) {
                return startStoryPicturesFromBixby();
            }
            if (LocationKey.isTrash(targetKeyFromBixby)) {
                return moveToTrash();
            }
            if (isSlideShow(targetKeyFromBixby)) {
                this.mViewerLaunched = false;
                this.mMediaData = MediaDataFactory.getInstance(this.mBlackboard).open("location://timeline");
                return true;
            }
            this.mViewerLaunched = true;
            this.mBlackboard.post("command://MoveURL", targetKeyFromBixby);
            return true;
        }
    }

    private boolean publishEventForCreaturePictures(LaunchIntent launchIntent) {
        SimpleThreadPool.getInstance().execute(new e(3, this, launchIntent));
        return true;
    }

    private boolean publishEventForQuickSearchShortcut() {
        if (PreferenceFeatures.OneUi8x.COLLECTION_TAB) {
            new FloatingRecommendationLauncher().executeFloatingRecommendation(this.mBlackboard);
            return true;
        }
        this.mBlackboard.post("command://MoveURL", "location://search");
        return true;
    }

    private boolean publishEventForSearchFromGalaxyFinder() {
        String galaxyFinderSearchKeyword = getLaunchIntent().getGalaxyFinderSearchKeyword();
        String str = this.TAG;
        Log.s(str, "publishEventForSearchFromGalaxyFinder {" + Logger.getEncodedString(galaxyFinderSearchKeyword) + "}");
        if (TextUtils.isEmpty(galaxyFinderSearchKeyword)) {
            return false;
        }
        this.mBlackboard.post("command://MoveURL", getSearchKeywordBaseUriBuilder(galaxyFinderSearchKeyword).appendArg("collect_type", SearchWordCollector.Type.KEYWORD_INPUT.toString()).build());
        return true;
    }

    private boolean publishEventForShortcutAlbum() {
        int shortcutAlbumId = getShortcutAlbumId();
        if (shortcutAlbumId == 0) {
            Log.w(this.TAG, "publishEventForShortcutAlbum : album id is invalid");
            return false;
        }
        LaunchIntent launchIntent = getLaunchIntent();
        String shortcutAlbumTarget = ShortcutHelper.getInstance().getShortcutAlbumTarget(launchIntent, shortcutAlbumId, launchIntent.getAlbumType());
        if (shortcutAlbumTarget != null && LaunchIntent.isFlipCoverWidgetTheme(this.mBlackboard) && !FoldUtils.isFlipCoverScreen(this.mActivityView.getActivity())) {
            shortcutAlbumTarget = ArgumentsUtil.removeArg(shortcutAlbumTarget, "shortcut_album");
            this.mRestartSavedDataFromFlipCover = (FlipCoverSavedData) this.mBlackboard.pop("data://viewer_recover_data_from_flip_cover");
        }
        if (shortcutAlbumTarget == null) {
            Log.w(this.TAG, "publishEventForShortcutAlbum : target is null!!");
            return false;
        } else if (shortcutAlbumTarget.startsWith("location://virtual/album")) {
            this.mBlackboard.post("command://MoveURL", shortcutAlbumTarget);
            return true;
        } else {
            publishAlbumData(shortcutAlbumTarget, shortcutAlbumId);
            return true;
        }
    }

    private boolean publishEventForShortcutItem() {
        if (getLaunchIntent().getUriData() == null) {
            return false;
        }
        loadShortcutItem();
        return true;
    }

    private boolean startStoryPicturesFromBixby() {
        ThreadUtil.postOnBgThread(new q(this, 0));
        return true;
    }

    private boolean supportTrash() {
        return Features.isEnabled(Features.SUPPORT_TRASH);
    }

    public boolean commitFragmentByLocationKey(String str, String str2) {
        if (!"location://albums/fileList".equals(str2) || !LaunchIntent.isFlipCoverWidgetTheme(this.mBlackboard) || !FoldUtils.isFlipCoverScreen(this.mActivityView.getActivity())) {
            return super.commitFragmentByLocationKey(str, str2);
        }
        return setMvpFragment(new WidgetAlbumPicturesFragment(), str, str2);
    }

    public void onDestroy() {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.close();
            this.mMediaData = null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004d, code lost:
        if (moveToTrash() != false) goto L_0x0023;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0021, code lost:
        if (publishEventForQuickSearchShortcut() != false) goto L_0x0023;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onNavigatorCreated() {
        /*
            r5 = this;
            com.samsung.android.gallery.module.abstraction.LaunchIntent r0 = r5.getLaunchIntent()
            boolean r1 = r0.isViewItem()
            if (r1 == 0) goto L_0x000f
            boolean r0 = r5.publishEventForShortcutItem()
            goto L_0x0063
        L_0x000f:
            boolean r1 = r0.isQuickSearchShortcut()
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x0027
            boolean r0 = r5.isUpsm()
            if (r0 != 0) goto L_0x0025
            boolean r0 = r5.publishEventForQuickSearchShortcut()
            if (r0 == 0) goto L_0x0025
        L_0x0023:
            r0 = r3
            goto L_0x0063
        L_0x0025:
            r0 = r2
            goto L_0x0063
        L_0x0027:
            boolean r1 = r0.isSearchFromGalaxyFinder()
            if (r1 == 0) goto L_0x0032
            boolean r0 = r5.publishEventForSearchFromGalaxyFinder()
            goto L_0x0063
        L_0x0032:
            boolean r1 = r0.isFromBixby()
            if (r1 == 0) goto L_0x003d
            boolean r0 = r5.publishEventForBixbyActivity()
            goto L_0x0063
        L_0x003d:
            boolean r1 = r0.isTrashView()
            if (r1 == 0) goto L_0x0050
            boolean r0 = r5.supportTrash()
            if (r0 == 0) goto L_0x0025
            boolean r0 = r5.moveToTrash()
            if (r0 == 0) goto L_0x0025
            goto L_0x0023
        L_0x0050:
            long r1 = r0.getCreatureId()
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L_0x005f
            boolean r0 = r5.publishEventForCreaturePictures(r0)
            goto L_0x0063
        L_0x005f:
            boolean r0 = r5.publishEventForShortcutAlbum()
        L_0x0063:
            if (r0 != 0) goto L_0x006e
            com.samsung.android.gallery.module.album.ShortcutHelper r0 = com.samsung.android.gallery.module.album.ShortcutHelper.getInstance()
            com.samsung.android.gallery.support.blackboard.Blackboard r1 = r5.mBlackboard
            r0.handleResultCanceled(r1)
        L_0x006e:
            com.samsung.android.gallery.support.blackboard.Blackboard r0 = r5.mBlackboard
            com.samsung.android.gallery.app.activity.abstraction.IGalleryActivityView r1 = r5.mActivityView
            android.app.Activity r1 = r1.getActivity()
            android.content.Context r1 = r1.getApplicationContext()
            java.lang.String r2 = "data://app_context"
            r0.publishIfEmpty(r2, r1)
            com.samsung.android.gallery.support.blackboard.Blackboard r5 = r5.mBlackboard
            com.samsung.android.gallery.module.thumbnail.ThumbnailLoader r0 = com.samsung.android.gallery.module.thumbnail.ThumbnailLoader.getInstance()
            long r0 = r0.getElapsedTime()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            java.lang.String r1 = "lifecycle://on_thumbnail_load_done"
            r5.publishIfEmpty(r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.activity.external.ShortcutViewNavigatorController.onNavigatorCreated():void");
    }

    public void onRequestQuickViewItem(Object obj, Bundle bundle) {
        loadShortcutItem();
    }

    public void onTimelineDataLoaded(Object obj, Bundle bundle) {
        String targetKeyFromBixby = getTargetKeyFromBixby(getLaunchIntent().getBixbyLocationKey());
        if (!this.mViewerLaunched && this.mMediaData != null && isSlideShow(targetKeyFromBixby)) {
            this.mBlackboard.post("command://MoveURL", ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(ArgumentsUtil.appendUriKey("location://timeline", "/slideshow", true), Message.KEY_POSITION, "0"), "media_item", BlackboardUtils.publishMediaItem(this.mBlackboard, this.mMediaData.read(0))));
            this.mViewerLaunched = true;
        }
    }

    public boolean supportDrawerLayout() {
        if (!DrawerUtil.supportDrawerLayout(getContext()) || !getLaunchIntent().isFromBixby()) {
            return false;
        }
        return true;
    }
}
