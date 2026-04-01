package com.samsung.android.gallery.app.controller.externals;

import android.content.Context;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.camera.GppmHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.media.CreateMediaHelper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.widget.abstraction.SelectableChecker;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class CreateMediaCmd extends BaseCommand implements SelectableChecker<MediaItem> {
    long mDuration = -1;
    protected boolean mExecuteWithEmptyList;
    boolean mFromBixby = false;
    private boolean mFromSelectableChecker;
    private Long mSelectedItemCount;

    /* renamed from: com.samsung.android.gallery.app.controller.externals.CreateMediaCmd$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$module$media$CreateMediaHelper$SupportType;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.samsung.android.gallery.module.media.CreateMediaHelper$SupportType[] r0 = com.samsung.android.gallery.module.media.CreateMediaHelper.SupportType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$module$media$CreateMediaHelper$SupportType = r0
                com.samsung.android.gallery.module.media.CreateMediaHelper$SupportType r1 = com.samsung.android.gallery.module.media.CreateMediaHelper.SupportType.NOT_SUPPORT_CLOUD_VIDEO     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$media$CreateMediaHelper$SupportType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.module.media.CreateMediaHelper$SupportType r1 = com.samsung.android.gallery.module.media.CreateMediaHelper.SupportType.NOT_SUPPORT_CLOUD_ITEM     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$media$CreateMediaHelper$SupportType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.module.media.CreateMediaHelper$SupportType r1 = com.samsung.android.gallery.module.media.CreateMediaHelper.SupportType.NOT_SUPPORT_GIF     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$media$CreateMediaHelper$SupportType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.gallery.module.media.CreateMediaHelper$SupportType r1 = com.samsung.android.gallery.module.media.CreateMediaHelper.SupportType.NOT_SUPPORT_VIDEO_TYPE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$media$CreateMediaHelper$SupportType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.samsung.android.gallery.module.media.CreateMediaHelper$SupportType r1 = com.samsung.android.gallery.module.media.CreateMediaHelper.SupportType.NOT_SUPPORT_VIDEO_FOR_COLLAGE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$media$CreateMediaHelper$SupportType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.samsung.android.gallery.module.media.CreateMediaHelper$SupportType r1 = com.samsung.android.gallery.module.media.CreateMediaHelper.SupportType.NOT_SUPPORT_VIDEO_COUNT_FOR_COLLAGE     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$media$CreateMediaHelper$SupportType     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.samsung.android.gallery.module.media.CreateMediaHelper$SupportType r1 = com.samsung.android.gallery.module.media.CreateMediaHelper.SupportType.NOT_SUPPORT_VIDEO_FOR_GIF     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$media$CreateMediaHelper$SupportType     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.samsung.android.gallery.module.media.CreateMediaHelper$SupportType r1 = com.samsung.android.gallery.module.media.CreateMediaHelper.SupportType.NOT_SUPPORT_CLOUD_VIDEO_FOR_COLLAGE     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.controller.externals.CreateMediaCmd.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPreExecute$0(MediaItem[] mediaItemArr, long j2, Object[] objArr, EventContext eventContext, Integer num) {
        String str = this.TAG;
        Log.d(str, "onPreExecute#PppChecker" + Logger.vt(Integer.valueOf(mediaItemArr.length), num, Long.valueOf(j2)));
        if (num.intValue() > 0) {
            objArr[0] = replacePppItem(eventContext, mediaItemArr);
        }
        super.onPreExecute(eventContext, objArr);
    }

    private boolean postSupportedCheck(MediaItem[] mediaItemArr) {
        boolean isExceedMaxCount = isExceedMaxCount();
        CreateMediaHelper.SupportType checkSupportType = checkSupportType(mediaItemArr, isExceedMaxCount);
        if (isExceedMaxCount) {
            if (checkSupportType == null || checkSupportType == CreateMediaHelper.SupportType.SUPPORT) {
                showCombinationErrorToast(CreateMediaHelper.SupportType.NOT_SUPPORT_EXCEED_MAX_COUNT);
            } else {
                showCombinationErrorToast(CreateMediaHelper.SupportType.NOT_SUPPORT_EXCEED_MAX_COUNT, checkSupportType);
            }
            return false;
        } else if (checkSupportType == CreateMediaHelper.SupportType.SUPPORT) {
            return true;
        } else {
            showToast(checkSupportType);
            return false;
        }
    }

    public CreateMediaHelper.SupportType checkSupportType(MediaItem[] mediaItemArr, boolean z) {
        int i2;
        CreateMediaHelper.SupportType supportType = null;
        int i7 = 0;
        while (true) {
            if (z) {
                i2 = getMaxCountToCheck(mediaItemArr);
            } else {
                i2 = mediaItemArr.length;
            }
            if (i7 >= i2) {
                return supportType;
            }
            supportType = getSupportType(mediaItemArr[i7]);
            if (supportType != CreateMediaHelper.SupportType.SUPPORT) {
                return supportType;
            }
            i7++;
        }
    }

    public abstract void createMedia(MediaItem[] mediaItemArr);

    public Long getAnalyticsValue() {
        return this.mSelectedItemCount;
    }

    public String getExceedMaxCountToastMessage() {
        return getContext().getString(R.string.max_size_reached, new Object[]{Integer.valueOf(getMaxCount())});
    }

    public abstract int getMaxCount();

    public int getMaxCountToCheck(MediaItem[] mediaItemArr) {
        return Math.min(100, mediaItemArr.length);
    }

    public abstract CreateMediaHelper.SupportType getSupportType(MediaItem mediaItem);

    public abstract int getTitleRes();

    public int getToastMessage(CreateMediaHelper.SupportType supportType) {
        switch (AnonymousClass1.$SwitchMap$com$samsung$android$gallery$module$media$CreateMediaHelper$SupportType[supportType.ordinal()]) {
            case 1:
                if (this.mFromSelectableChecker) {
                    return R.string.cant_use_cloud_file_to_create_movies;
                }
                return R.string.cant_add_videos_stored_in_the_cloud_to_movies;
            case 2:
                if (this.mFromSelectableChecker) {
                    return R.string.cant_use_cloud_file_to_create_movies;
                }
                return R.string.cant_add_items_stored_in_the_cloud_to_movies;
            case 3:
                return R.string.cant_use_gif_to_create_movies_gifs_deselected;
            case 4:
                return R.string.create_movie_maker_not_supported_video_type;
            case 5:
                return R.string.cant_add_videos_to_collage;
            case 6:
                return R.string.vidoe_collage_maker_video_count_max_size_reached;
            case 7:
                if (this.mFromSelectableChecker) {
                    return R.string.animated_gif_maker_video_file_not_supported;
                }
                return R.string.cant_add_videos_to_gif;
            case 8:
                return R.string.cant_add_videos_stored_in_the_cloud_to_collages;
            default:
                return R.string.unsupported_file_deselected;
        }
    }

    public boolean isExceedMaxCount() {
        if (this.mSelectedItemCount.longValue() > ((long) getMaxCount())) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: type inference failed for: r9v8, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onExecute(com.samsung.android.gallery.app.controller.EventContext r8, java.lang.Object... r9) {
        /*
            r7 = this;
            r0 = 0
            r1 = r9[r0]
            com.samsung.android.gallery.module.data.MediaItem[] r1 = (com.samsung.android.gallery.module.data.MediaItem[]) r1
            int r2 = r9.length
            r3 = 1
            if (r2 <= r3) goto L_0x001e
            r2 = r9[r3]
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            r7.mFromBixby = r2
            r2 = 2
            r9 = r9[r2]
            java.lang.Long r9 = (java.lang.Long) r9
            long r4 = r9.longValue()
            r7.mDuration = r4
        L_0x001e:
            if (r1 == 0) goto L_0x0026
            int r9 = r1.length
            if (r9 != 0) goto L_0x0024
            goto L_0x0026
        L_0x0024:
            r9 = r0
            goto L_0x0027
        L_0x0026:
            r9 = r3
        L_0x0027:
            r7.mExecuteWithEmptyList = r9
            if (r9 == 0) goto L_0x0066
            r7.mFromSelectableChecker = r3
            com.samsung.android.gallery.support.blackboard.Blackboard r8 = r7.getBlackboard()
            java.lang.String r9 = "data://user/pick/ItemChecker"
            r8.publish(r9, r7)
            android.os.Bundle r8 = new android.os.Bundle
            r8.<init>()
            android.content.Context r9 = r7.getContext()
            int r0 = r7.getTitleRes()
            java.lang.String r9 = r9.getString(r0)
            java.lang.String r0 = "title"
            r8.putString(r0, r9)
            java.lang.String r9 = "maxCount"
            int r0 = r7.getMaxCount()
            r8.putInt(r9, r0)
            r9 = 1025(0x401, float:1.436E-42)
            com.samsung.android.gallery.support.blackboard.key.EventMessage r9 = com.samsung.android.gallery.support.blackboard.key.EventMessage.obtain(r9)
            r9.setData(r8)
            com.samsung.android.gallery.support.blackboard.Blackboard r7 = r7.getBlackboard()
            r7.postEvent(r9)
            return
        L_0x0066:
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            int r3 = r1.length
            r4 = r0
        L_0x0072:
            if (r4 >= r3) goto L_0x0088
            r5 = r1[r4]
            if (r5 == 0) goto L_0x0085
            boolean r6 = r5.isPostProcessing()
            if (r6 == 0) goto L_0x0082
            r9.add(r5)
            goto L_0x0085
        L_0x0082:
            r2.add(r5)
        L_0x0085:
            int r4 = r4 + 1
            goto L_0x0072
        L_0x0088:
            int r3 = r2.size()
            if (r3 != 0) goto L_0x00ae
            r8 = 2131888940(0x7f120b2c, float:1.941253E38)
            r7.showToast((int) r8)
            java.lang.String r7 = r7.TAG
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r0 = "create media skip with ppp "
            r8.<init>(r0)
            java.lang.String r0 = "/"
            c0.C0086a.A(r8, r9, r0)
            int r9 = r1.length
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            com.samsung.android.gallery.support.utils.Log.majorEvent(r7, r8)
            return
        L_0x00ae:
            int r9 = r1.length
            int r3 = r2.size()
            if (r9 == r3) goto L_0x00be
            com.samsung.android.gallery.module.data.MediaItem[] r9 = new com.samsung.android.gallery.module.data.MediaItem[r0]
            java.lang.Object[] r9 = r2.toArray(r9)
            r1 = r9
            com.samsung.android.gallery.module.data.MediaItem[] r1 = (com.samsung.android.gallery.module.data.MediaItem[]) r1
        L_0x00be:
            int r9 = r1.length
            long r2 = (long) r9
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            r7.mSelectedItemCount = r9
            boolean r9 = r7.postSupportedCheck(r1)
            if (r9 != 0) goto L_0x00cd
            goto L_0x00e3
        L_0x00cd:
            r7.createMedia(r1)
            boolean r8 = r8.isSelectionMode()
            if (r8 == 0) goto L_0x00e3
            com.samsung.android.gallery.support.blackboard.Blackboard r7 = r7.getBlackboard()
            r8 = 1003(0x3eb, float:1.406E-42)
            com.samsung.android.gallery.support.blackboard.key.EventMessage r8 = com.samsung.android.gallery.support.blackboard.key.EventMessage.obtain(r8)
            r7.postEvent(r8)
        L_0x00e3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.controller.externals.CreateMediaCmd.onExecute(com.samsung.android.gallery.app.controller.EventContext, java.lang.Object[]):void");
    }

    public void onPreExecute(EventContext eventContext, Object... objArr) {
        if (!GppmHelper.SUPPORT_DONATION_MULTIPLE || !eventContext.isSelectionMode()) {
            super.onPreExecute(eventContext, objArr);
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        MediaItem[] mediaItemArr = objArr[0];
        EventContext eventContext2 = eventContext;
        executePppChecker(eventContext2, mediaItemArr, new a(this, mediaItemArr, currentTimeMillis, objArr, eventContext2));
    }

    public void showCombinationErrorToast(CreateMediaHelper.SupportType... supportTypeArr) {
        StringBuilder sb2 = new StringBuilder();
        for (CreateMediaHelper.SupportType supportType : supportTypeArr) {
            if (supportType == CreateMediaHelper.SupportType.NOT_SUPPORT_EXCEED_MAX_COUNT) {
                sb2.append(getExceedMaxCountToastMessage());
            } else {
                sb2.append(getContext().getString(getToastMessage(supportType)));
            }
            sb2.append("\n");
        }
        sb2.deleteCharAt(sb2.length() - 1);
        showToast(sb2.toString());
    }

    public void showExceedMaxCountToast(Context context) {
        showToast(getExceedMaxCountToastMessage());
    }

    public void showToast(CreateMediaHelper.SupportType supportType) {
        showToast(getToastMessage(supportType));
    }

    public void done(MediaItem[] mediaItemArr) {
        createMedia(mediaItemArr);
    }

    public boolean isSupported(MediaItem mediaItem) {
        if (mediaItem == null) {
            return false;
        }
        CreateMediaHelper.SupportType supportType = getSupportType(mediaItem);
        if (supportType == CreateMediaHelper.SupportType.SUPPORT) {
            return true;
        }
        showToast(supportType);
        return false;
    }
}
