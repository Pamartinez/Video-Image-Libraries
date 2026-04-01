package com.samsung.android.gallery.app.ui.dialog;

import C3.C0392b;
import Gb.b;
import U9.a;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.delegate.SesPickerDelegate;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.dialog.ErrorType;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateSharedAlbumDialog extends CreateNameDialog {
    private String getPredefinedName() {
        if (PreferenceFeatures.OneUi6x.SUPPORT_SHARE_STORY) {
            try {
                String str = (String) getBlackboard().read("data://user/storyInfo", null);
                if (!TextUtils.isEmpty(str) && str.startsWith("story")) {
                    return new JSONObject(str.substring(5)).getString("storyName");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getDefaultName$0(String str, MediaItem mediaItem) {
        String str2;
        if (mediaItem.getTitle() == null) {
            str2 = "";
        } else {
            str2 = mediaItem.getTitle();
        }
        return str2.matches(str);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$getDefaultName$1(String str, MediaItem mediaItem) {
        Matcher matcher = Pattern.compile(str).matcher(mediaItem.getTitle());
        if (!matcher.matches() || matcher.groupCount() != 1) {
            return -1;
        }
        String group = matcher.group(1);
        if (TextUtils.isEmpty(group)) {
            return 1;
        }
        return Integer.parseInt(group);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getDefaultName$2(AtomicInteger atomicInteger, int i2) {
        if (atomicInteger.get() == i2) {
            atomicInteger.getAndIncrement();
            return false;
        } else if (atomicInteger.get() < i2) {
            return true;
        } else {
            return false;
        }
    }

    public String getDefaultName() {
        MediaData open;
        String predefinedName = getPredefinedName();
        if (!TextUtils.isEmpty(predefinedName)) {
            return predefinedName;
        }
        AtomicInteger atomicInteger = new AtomicInteger(1);
        String string = getString(R.string.shared_album_with_index);
        try {
            open = MediaDataFactory.getInstance(getBlackboard()).open("location://sharing/albums", true);
            String replace = string.replace("%d", "(\\d+)");
            open.getAllData().stream().filter(new C0392b(replace, 24)).mapToInt(new a(3, replace)).sorted().anyMatch(new b(6, atomicInteger));
            open.close();
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("getDefaultName failed e="), this.TAG);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        return getString(R.string.shared_album_with_index, Integer.valueOf(atomicInteger.get()));
        throw th;
    }

    public String getHint() {
        return getString(R.string.enter_album_name);
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_SHARED_VIEW_NORMAL.toString();
    }

    public String getTitle() {
        return getString(R.string.create_shared_album);
    }

    public ErrorType isValid(String str) {
        if (!isDottedText(str)) {
            return ErrorType.NONE;
        }
        return ErrorType.CANNOT_START_WITH_A_PERIOD_FOR_ALBUM_NAME;
    }

    public void onTextChanged(CharSequence charSequence, int i2, int i7, int i8) {
        super.onTextChanged(charSequence, i2, i7, i8);
        postAnalyticsLog(AnalyticsEventId.EVENT_CREATE_SHARED_ALBUM_CHANGED_GROUP_NAME);
    }

    public void publishCancel() {
        getBlackboard().post("data://user/dialog/SharedAlbumCreate", (Object) null);
        postAnalyticsLog(AnalyticsEventId.EVENT_CREATE_SHARED_ALBUM_CANCEL);
    }

    public void publishData(String str) {
        if (getActivity() == null) {
            Log.e(this.TAG, "activity is invalid");
            return;
        }
        getBlackboard().post("data://user/dialog/SharedAlbumCreate", str);
        SesPickerDelegate.start(getActivity(), getBlackboard(), str);
        postAnalyticsLog(AnalyticsEventId.EVENT_CREATE_SHARED_ALBUM_OK);
    }
}
