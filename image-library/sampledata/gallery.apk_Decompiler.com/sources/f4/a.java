package f4;

import android.content.ClipData;
import android.content.ContentProviderOperation;
import android.view.View;
import androidx.core.util.Pair;
import com.samsung.android.gallery.app.ui.abstraction.delegate.AbsDelegate;
import com.samsung.android.gallery.app.ui.abstraction.delegate.DelegateComposite;
import com.samsung.android.gallery.app.ui.dialog.CreateStoryDialog;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.AwesomeIntelligenceHandler;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.AbsRemasterAiEdit;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.database.dal.cmh.helper.CmhStoryApiImpl;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.fastoption2.FastOptionItemParams;
import com.samsung.android.gallery.widget.fastoption2.FastOptionMenuItem;
import com.samsung.android.gallery.widget.fastoption2.FastOptionView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ ArrayList e;

    public /* synthetic */ a(ArrayList arrayList, int i2) {
        this.d = i2;
        this.e = arrayList;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        ArrayList arrayList = this.e;
        switch (i2) {
            case 0:
                DelegateComposite.lambda$onDetachInternal$14(arrayList, (AbsDelegate) obj);
                return;
            case 1:
                DelegateComposite.lambda$onPauseInternal$12(arrayList, (AbsDelegate) obj);
                return;
            case 2:
                DelegateComposite.lambda$onStopInternal$16(arrayList, (AbsDelegate) obj);
                return;
            case 3:
                DelegateComposite.lambda$onEnterTransitionEndInternal$10(arrayList, (AbsDelegate) obj);
                return;
            case 4:
                DelegateComposite.lambda$onApplyWindowInsetsInternal$7(arrayList, (AbsDelegate) obj);
                return;
            case 5:
                DelegateComposite.lambda$onReturnTransitionEndInternal$11(arrayList, (AbsDelegate) obj);
                return;
            case 6:
                DelegateComposite.lambda$onEnterTransitionStartInternal$9(arrayList, (AbsDelegate) obj);
                return;
            case 7:
                DelegateComposite.lambda$onStartInternal$15(arrayList, (AbsDelegate) obj);
                return;
            case 8:
                DelegateComposite.lambda$onResumeInternal$8(arrayList, (AbsDelegate) obj);
                return;
            case 9:
                DelegateComposite.lambda$onDestroyInternal$17(arrayList, (AbsDelegate) obj);
                return;
            case 10:
                arrayList.add(Pair.create((FastOptionView) obj, "fastOption"));
                return;
            case 11:
                arrayList.addAll((List) obj);
                return;
            case 12:
                AwesomeIntelligenceHandler.lambda$load$6(arrayList, (List) obj);
                return;
            case 13:
                arrayList.add((ContentProviderOperation) obj);
                return;
            case 14:
                arrayList.add((AbsRemasterAiEdit) obj);
                return;
            case 15:
                arrayList.add((View) obj);
                return;
            case 16:
                CmhStoryApiImpl.lambda$updateStoryTotalCropInfo$4(arrayList, (Map.Entry) obj);
                return;
            case 17:
                arrayList.add((String) obj);
                return;
            case 18:
                arrayList.add(new FastOptionMenuItem(FastOptionItemParams.builder().setTitleRes(((ViewerMenuItem) obj).getTitleResId()).setMenuId(((ViewerMenuItem) obj).getMenuId()).setTitle(((ViewerMenuItem) obj).getTitle()).setGroupId(((ViewerMenuItem) obj).getGroupId()).build()));
                return;
            case 19:
                CreateStoryDialog.lambda$loadTitle$0(arrayList, (MediaItem) obj);
                return;
            default:
                arrayList.remove((ClipData.Item) obj);
                return;
        }
    }
}
