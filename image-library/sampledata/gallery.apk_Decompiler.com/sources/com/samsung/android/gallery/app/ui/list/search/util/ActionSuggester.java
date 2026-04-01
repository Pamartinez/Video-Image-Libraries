package com.samsung.android.gallery.app.ui.list.search.util;

import C3.C0392b;
import K5.a;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.trash.LaunchTrashBinCmd;
import com.samsung.android.gallery.support.utils.Features;
import com.sec.android.gallery3d.R;
import java.util.Arrays;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum ActionSuggester {
    ;
    
    private static final String FEEDBACK_SELECTED_ACTION_FEATURE = "feedback_selected_action_feature";
    private final String mActionId;

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.ui.list.search.util.ActionSuggester$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass1 extends ActionSuggester {
        public /* synthetic */ AnonymousClass1() {
            this("TRASH", 0, "gallery_recycle_bin");
        }

        public int getIcon() {
            return R.drawable.gallery_ic_detail_delete;
        }

        public int getTitle() {
            return R.string.go_to_trash;
        }

        public void run(EventContext eventContext) {
            ActionSuggester.super.run(eventContext);
            new LaunchTrashBinCmd().execute(eventContext, new Object[0]);
        }

        private AnonymousClass1(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    public static ActionSuggester get(String str) {
        return (ActionSuggester) Arrays.stream(values()).filter(new C0392b(str, 6)).findFirst().orElse((Object) null);
    }

    private String getActionId() {
        return this.mActionId;
    }

    public abstract int getIcon();

    public abstract int getTitle();

    public void launchActionSuggesterLogging(EventContext eventContext) {
        if (Features.isEnabled(Features.SUPPORT_SCS_SEARCH_FEEDBACK)) {
            Optional.ofNullable(eventContext.getBlackboard()).ifPresent(new a(0, this));
        }
    }

    public void run(EventContext eventContext) {
        launchActionSuggesterLogging(eventContext);
    }

    private ActionSuggester(String str) {
        this.mActionId = str;
    }
}
