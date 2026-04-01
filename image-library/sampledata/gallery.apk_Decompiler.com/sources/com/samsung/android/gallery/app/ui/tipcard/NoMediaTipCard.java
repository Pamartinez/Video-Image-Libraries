package com.samsung.android.gallery.app.ui.tipcard;

import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.MediaScannerListener;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.SystemEnvironment;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.io.File;
import java.util.LinkedHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NoMediaTipCard extends AbsTipCard {
    private String mFolderPath;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FolderHolder {
        private static final LinkedHashMap<String, Object> FOLDERS = new LinkedHashMap<>();

        /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Object, com.samsung.android.gallery.support.utils.SystemEnvironment$EnvironmentChangeListener] */
        static {
            initialize();
            SystemEnvironment.addObserver("FolderHolder", new Object(), 1);
        }

        public static String getIfExists() {
            for (String next : FOLDERS.keySet()) {
                if (!FileUtils.isFile(next + "/.nomedia")) {
                    if (FileUtils.isDirectory(next + "/.nomedia/")) {
                    }
                }
                return next;
            }
            return null;
        }

        public static String getName(Context context, String str) {
            if (str == null) {
                return "";
            }
            Object obj = FOLDERS.get(str);
            if (obj != null) {
                if (obj instanceof Integer) {
                    return context.getString(((Integer) obj).intValue());
                }
                if (obj instanceof String) {
                    return (String) obj;
                }
            }
            return str;
        }

        public static void initialize() {
            LinkedHashMap<String, Object> linkedHashMap = FOLDERS;
            linkedHashMap.clear();
            StorageInfo storageInfo = StorageInfo.getDefault();
            linkedHashMap.put(storageInfo.root, Integer.valueOf(R.string.new_album_storage_internal_storage));
            String str = storageInfo.dcim;
            Integer valueOf = Integer.valueOf(R.string.dcim);
            linkedHashMap.put(str, valueOf);
            String str2 = storageInfo.camera;
            Integer valueOf2 = Integer.valueOf(R.string.dcim_name);
            linkedHashMap.put(str2, valueOf2);
            linkedHashMap.put(storageInfo.download, Integer.valueOf(R.string.download));
            linkedHashMap.put(storageInfo.screenShot, Integer.valueOf(R.string.screenshot));
            linkedHashMap.put(storageInfo.pictures, "Pictures");
            StorageInfo removable = StorageInfo.getRemovable();
            linkedHashMap.put(removable.root, Integer.valueOf(R.string.new_album_storage_sdcard));
            linkedHashMap.put(removable.dcim, valueOf);
            linkedHashMap.put(removable.camera, valueOf2);
            Log.d("FolderHolder", "initialize", Integer.valueOf(linkedHashMap.size()));
        }
    }

    private void deleteNoMediaFile(String str) {
        SecureFile secureFile = new SecureFile(str);
        if (!secureFile.exists()) {
            return;
        }
        if (secureFile.isDirectory()) {
            if (!FileUtils.isEmptyFolder((File) secureFile)) {
                long currentTimeMillis = System.currentTimeMillis();
                String str2 = secureFile.getAbsolutePath() + "." + TimeUtil.getFileTimestamp(currentTimeMillis) + (currentTimeMillis % 1000);
                Log.d(this.TAG, "renameDir", Logger.getEncodedString(str2));
                secureFile.renameTo(new SecureFile(str2));
            }
            FileUtils.deleteDirectory(secureFile);
            return;
        }
        secureFile.delete();
    }

    private String getPathName(Context context, String str) {
        return FolderHolder.getName(context, str);
    }

    public boolean checkTipCard(Context context) {
        String ifExists = FolderHolder.getIfExists();
        this.mFolderPath = ifExists;
        if (ifExists != null) {
            return true;
        }
        return false;
    }

    public String getCancelBtnString(Context context) {
        return context.getString(R.string.not_now);
    }

    public String getDescription(Context context) {
        return context.getString(R.string.no_media_tip_card_description, new Object[]{getPathName(context, this.mFolderPath)});
    }

    public String getDoneBtnString(Context context) {
        return context.getString(R.string.delete);
    }

    public String getTag() {
        return "NO_MEDIA";
    }

    public String getTipCardCreationLogDetail() {
        return AnalyticsDetail.EVENT_DETAIL_TIP_CARD_PRESENCE_NO_MEDIA.toString();
    }

    public String getTitle(Context context) {
        return context.getResources().getString(R.string.no_media_tip_card_title);
    }

    public void onCancelBtnClicked(Context context, View view, TipCardViewHolder tipCardViewHolder) {
        dismissTipCard(tipCardViewHolder);
    }

    public void onDoneBtnClicked(Context context, View view, TipCardViewHolder tipCardViewHolder) {
        dismissTipCard(tipCardViewHolder);
        deleteNoMediaFile(C0212a.p(new StringBuilder(), this.mFolderPath, "/.nomedia"));
        MediaScanner.scanFolder(this.mFolderPath, (MediaScannerListener) null);
    }
}
