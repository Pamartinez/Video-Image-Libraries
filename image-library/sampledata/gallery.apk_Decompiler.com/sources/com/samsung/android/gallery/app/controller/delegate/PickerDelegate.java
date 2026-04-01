package com.samsung.android.gallery.app.controller.delegate;

import A.a;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PickerDelegate {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class IntentBuilder {
        private String mAction;
        private final BundleWrapper mArgs;
        private String mClassName;
        private final Bundle mExtras = new Bundle();
        private final String mPackageName;

        public IntentBuilder(Bundle bundle, String str) {
            this.mArgs = new BundleWrapper(bundle);
            this.mPackageName = str;
        }

        private IntentBuilder setMultiPick() {
            this.mExtras.putInt("key-story-album-bucket-id", this.mArgs.getInt("key-story-album-bucket-id"));
            this.mExtras.putBoolean("is-pick-to-add-new-album", this.mArgs.getBoolean("is-pick-to-add-new-album"));
            this.mExtras.putBoolean("is-pick-to-add-shared-album", this.mArgs.getBoolean("is-pick-to-add-shared-album"));
            this.mExtras.putBoolean("is_pick-limited-file-size", this.mArgs.getBoolean("is_pick-limited-file-size"));
            this.mExtras.putLong("pick-limited-file-size", this.mArgs.getLong("pick-limited-file-size"));
            this.mExtras.putInt("pick-max-item", this.mArgs.getInt("pick-max-item"));
            this.mExtras.putBoolean("has_target_cluster", this.mArgs.getBoolean("has_target_cluster"));
            this.mExtras.putBoolean("is-pick-for-story-contents", this.mArgs.getBoolean("is-pick-for-story-contents"));
            this.mExtras.putLong(IParameterKey.DATE_TAKEN, this.mArgs.getLong(IParameterKey.DATE_TAKEN, -1));
            this.mExtras.putString("done_string_replacement", this.mArgs.getString("done_string_replacement", (String) null));
            this.mExtras.putBoolean("disable_timeline_divider", this.mArgs.getBoolean("disable_timeline_divider"));
            this.mExtras.putString("item_is_supported_checker", this.mArgs.getString("item_is_supported_checker", (String) null));
            this.mExtras.putBoolean("album_pictures_pick", this.mArgs.getBoolean("album_pictures_pick"));
            this.mExtras.putInt("bucketId", this.mArgs.getInt("bucketId"));
            this.mExtras.putInt("key-album-type", this.mArgs.getInt("key-album-type"));
            return this;
        }

        private IntentBuilder setSinglePick() {
            this.mExtras.putInt("key-story-album-bucket-id", this.mArgs.getInt("key-story-album-bucket-id"));
            this.mExtras.putString("key-story-list-position", this.mArgs.getString("key-story-list-position", (String) null));
            this.mExtras.putBoolean("FromStoryCover", this.mArgs.getBoolean("FromStoryCover"));
            this.mExtras.putString("key-shared-album-space-id", this.mArgs.getString("key-shared-album-space-id", (String) null));
            this.mExtras.putString("key-shared-album-group-id", this.mArgs.getString("key-shared-album-group-id", (String) null));
            this.mExtras.putString("key-shared-album-list-position", this.mArgs.getString("key-shared-album-list-position", (String) null));
            this.mExtras.putInt("KEY_COVER_PICKER_TYPE", this.mArgs.getInt("KEY_COVER_PICKER_TYPE"));
            this.mExtras.putString("key-current-cover-item", this.mArgs.getString("key-current-cover-item", (String) null));
            this.mExtras.putBoolean("disable_timeline_divider", this.mArgs.getBoolean("disable_timeline_divider"));
            this.mExtras.putString("item_is_supported_checker", this.mArgs.getString("item_is_supported_checker", (String) null));
            this.mExtras.putBoolean("FromSharedAlbumCover", this.mArgs.getBoolean("FromSharedAlbumCover"));
            this.mExtras.putBoolean("crop", this.mArgs.getBoolean("crop"));
            this.mExtras.putBoolean("is-get-rect-result", this.mArgs.getBoolean("is-get-rect-result"));
            this.mExtras.putInt("bucketId", this.mArgs.getInt("bucketId"));
            this.mExtras.putString("bucketIds", this.mArgs.getString("bucketIds", (String) null));
            this.mExtras.putString("key-album-list-position", this.mArgs.getString("key-album-list-position", (String) null));
            this.mExtras.putInt("key-album-type", this.mArgs.getInt("key-album-type"));
            this.mExtras.putBoolean("FromAlbumCover", this.mArgs.getBoolean("FromAlbumCover"));
            this.mExtras.putString("mergedAlbumId", this.mArgs.getString("mergedAlbumId", (String) null));
            return this;
        }

        public Intent build() {
            Intent intent = new Intent();
            if (!TextUtils.isEmpty(this.mClassName)) {
                intent.setClassName(this.mPackageName, this.mClassName);
            }
            if (!TextUtils.isEmpty(this.mAction)) {
                intent.setAction(this.mAction);
            }
            intent.putExtras(this.mExtras);
            return intent;
        }

        public IntentBuilder setAction(String str) {
            this.mAction = str;
            return this;
        }

        public IntentBuilder setClassName(String str) {
            this.mClassName = str;
            return this;
        }

        public IntentBuilder setInsideOnly() {
            this.mExtras.putBoolean("android.intent.extra.LOCAL_ONLY", this.mArgs.getBoolean("android.intent.extra.LOCAL_ONLY"));
            this.mExtras.putBoolean("pick-from-gallery", true);
            return this;
        }

        public IntentBuilder setPickMode(boolean z) {
            if (z) {
                return setSinglePick();
            }
            return setMultiPick();
        }

        public IntentBuilder setSharingRepository(String str) {
            this.mExtras.putString("blackboard_name", str);
            return this;
        }
    }

    private static String getBlackboardTag(Activity activity) {
        return activity.toString();
    }

    public static void handleMultiPickResult(Blackboard blackboard, int i2, Intent intent) {
        if (i2 == -1) {
            MediaItem[] mediaItemArr = (MediaItem[]) blackboard.pop("data://user/selected");
            if (mediaItemArr != null) {
                blackboard.post("data://user/pick/Item", mediaItemArr);
            } else {
                blackboard.post("data://user/pick/Item", (ArrayList) intent.getSerializableExtra("selectedItems"));
            }
        } else {
            blackboard.post("data://user/pick/Item", (Object) null);
        }
    }

    public static void handleOnActivityResult(Blackboard blackboard, int i2, int i7, Intent intent) {
        if (i2 == 2316) {
            handleMultiPickResult(blackboard, i7, intent);
        } else if (i2 == 2317) {
            handleSinglePickResult(blackboard, i7, intent);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: com.samsung.android.gallery.module.data.MediaItem} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: com.samsung.android.gallery.module.data.MediaItem} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: com.samsung.android.gallery.module.data.MediaItem} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void handleSinglePickResult(com.samsung.android.gallery.support.blackboard.Blackboard r5, int r6, android.content.Intent r7) {
        /*
            r0 = -1
            r1 = 0
            java.lang.String r2 = "data://user/selected"
            java.lang.String r3 = "data://user/pick/SingleItem"
            if (r6 != r0) goto L_0x0033
            java.lang.Object r6 = r5.pop(r2)
            com.samsung.android.gallery.module.data.MediaItem[] r6 = (com.samsung.android.gallery.module.data.MediaItem[]) r6
            if (r6 == 0) goto L_0x002f
            int r0 = r6.length
            r2 = 1
            if (r0 != r2) goto L_0x002f
            r0 = 0
            r4 = r6[r0]
            if (r4 == 0) goto L_0x002f
            boolean r1 = isFromCropResult(r7)
            if (r1 == 0) goto L_0x0029
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r6 = r6[r0]
            r1[r0] = r6
            r1[r2] = r7
            goto L_0x002b
        L_0x0029:
            r1 = r6[r0]
        L_0x002b:
            r5.post(r3, r1)
            return
        L_0x002f:
            r5.post(r3, r1)
            return
        L_0x0033:
            r5.erase(r2)
            r5.post(r3, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.controller.delegate.PickerDelegate.handleSinglePickResult(com.samsung.android.gallery.support.blackboard.Blackboard, int, android.content.Intent):void");
    }

    private static boolean isFromCropResult(Intent intent) {
        if (intent == null || !intent.getBooleanExtra("result_from_crop", false)) {
            return false;
        }
        return true;
    }

    public static void startPicker(Activity activity, Bundle bundle) {
        String str;
        int i2;
        boolean equals = "data://user/pick/SingleItem".equals(CommandKey.DATA_REQUEST_REVERT(BundleWrapper.getKey(bundle)));
        IntentBuilder className = new IntentBuilder(bundle, activity.getPackageName()).setClassName("com.samsung.android.gallery.app.activity.external.GalleryExternalActivity");
        if (equals) {
            str = "android.intent.action.PICK";
        } else {
            str = "com.samsung.intent.action.MULTIPLE_PICK";
        }
        Intent build = className.setAction(str).setSharingRepository(getBlackboardTag(activity)).setInsideOnly().setPickMode(equals).build();
        if (equals) {
            i2 = 2317;
        } else {
            i2 = 2316;
        }
        try {
            activity.startActivityForResult(build, i2);
        } catch (Exception e) {
            a.s(e, new StringBuilder("startPicker failed e="), "PickerDelegate");
        }
    }
}
