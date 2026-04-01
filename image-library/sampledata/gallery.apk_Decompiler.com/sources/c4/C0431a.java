package c4;

import N2.j;
import android.os.Bundle;
import android.view.Window;
import androidx.fragment.app.FragmentActivity;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageItemPicker;
import com.samsung.android.gallery.database.dbtype.ScreenShotFilterType;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.dynamicview.DynamicViewPlayInfo;
import com.samsung.android.gallery.module.story.ondemand.OnDemandStory;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils;
import com.samsung.android.sdk.ocr.OCRResultUtils;
import com.samsung.android.sdk.pen.ocr.SpenOcrBlockData;
import com.samsung.android.sdk.scs.ai.language.Result;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceRunnable;
import com.samsung.android.sivs.ai.sdkcommon.translation.LanguageDirection;
import i.C0212a;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/* renamed from: c4.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0431a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2491a;

    public /* synthetic */ C0431a(int i2) {
        this.f2491a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f2491a) {
            case 0:
                return C0212a.m("(", (String) obj, ")");
            case 1:
                return Boolean.valueOf(((DynamicViewPlayInfo) obj).supportBgm());
            case 2:
                return C0212a.A((String) obj, "=?");
            case 3:
                return C0212a.A((String) obj, "=?");
            case 4:
                return C0212a.A((String) obj, "=?");
            case 5:
                return C0212a.A((String) obj, "=?");
            case 6:
                return ((MediaItem) ((MediaItem) obj)).getTitle();
            case 7:
                return MediaItemBuilder.create((String) obj);
            case 8:
                return Long.valueOf(((CollageItemPicker.Content) obj).item.getFileId());
            case 9:
                return Boolean.valueOf(((MediaItem) ((MediaItem) obj)).isVideo());
            case 10:
                return ((FragmentActivity) obj).getWindow();
            case 11:
                return ((Window) obj).getSharedElementReenterTransition();
            case 12:
                return ((ScreenShotFilterType) obj).mEventId;
            case 13:
                return ((ScreenShotFilterType) obj).key();
            case 14:
                return Integer.valueOf(((StoryType) obj).getValue());
            case 15:
                return ((String) obj).split("=");
            case 16:
                return OnDemandStory.lambda$computeIfAbsent$0((Map.Entry) obj);
            case 17:
                return SharedTransition.d((Blackboard) obj);
            case 18:
                return SharedTransition.lambda$getTransitionSetMap$2((String) obj);
            case 19:
                return (MotionPhotoVideoUtils.SEFInfo) ((Map.Entry) obj).getKey();
            case 20:
                return (byte[]) ((Map.Entry) obj).getValue();
            case 21:
                return OCRResultUtils.getCenterPoint(((SpenOcrBlockData) obj).getRect());
            case 22:
                return LlmServiceRunnable.singleResultMapper((List) obj);
            case 23:
                return LlmServiceRunnable.listResultMapper((List) obj);
            case 24:
                return new Result((Bundle) obj);
            case 25:
                return Collections.unmodifiableSet((Set) obj);
            case 26:
                return j.d("(", ((LanguageDirection) obj).getSourceLanguage(), ArcCommonLog.TAG_COMMA, ((LanguageDirection) obj).getTargetLanguage(), ")");
            case 27:
                return ((LanguageDirection) obj).getSourceLanguage();
            case 28:
                return (LanguageDirection) ((Map.Entry) obj).getKey();
            default:
                return ((LanguageDirection) obj).getTargetLanguage();
        }
    }
}
