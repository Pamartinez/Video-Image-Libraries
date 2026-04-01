package com.samsung.android.gallery.bixby.bixby.handler;

import android.content.Context;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.bixby2.Sbixby;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BixbyProxy {
    private Boolean mIsBixby2 = null;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LazyHolder {
        /* access modifiers changed from: private */
        public static final BixbyProxy INSTANCE = new BixbyProxy();
    }

    private void addActionHandler() {
        Sbixby instance = Sbixby.getInstance();
        instance.addActionHandler("CheckAvailableDeeplink", new DeeplinkCheckActionHandler());
        instance.addActionHandler("DELETE_ITEM", new DeleteItemActionHandler());
        instance.addActionHandler("GET_DETAIL_VIEW_CONTENT", new GetViewerContentActionHandler());
        instance.addActionHandler("GET_SELECTED_CONTENTS", new GetSelectedContentsActionHandler());
        instance.addActionHandler("HIGHLIGHT_VIDEO_SUPPORT", new HighLightSupportInfoActionHandler());
        instance.addActionHandler("MOVE_TO_ALBUM_INFO", new MoveToAlbumInfoActionHandler());
        instance.addActionHandler("MOVE_TO_GROUP_INFO", new MoveToGroupInfoActionHandler());
        instance.addActionHandler("SEARCH_BY_CATEGORY_INFO", new SearchInfoActionHandler());
        instance.addActionHandler("SHOW_SINGLE_CONTENT", new ShowSingleContentActionHandler());
        instance.addActionHandler("TRASH_INFO", new TrashInfoActionHandler());
        instance.addActionHandler("DETAIL_VIEW_AI_EDIT", new AiEditActionHandler());
        instance.addActionHandler("DETAIL_VIEW_DELETE", new DeleteActionHandler());
        instance.addActionHandler("DETAIL_VIEW_REMINDER", new ReminderActionHandler());
    }

    public static BixbyProxy getInstance() {
        return LazyHolder.INSTANCE;
    }

    private boolean isBixby2(Context context) {
        if (this.mIsBixby2 == null) {
            this.mIsBixby2 = Boolean.valueOf(!Sbixby.isPathRuleBased(context));
        }
        return this.mIsBixby2.booleanValue();
    }

    public void init(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            if (isBixby2(context)) {
                Sbixby.initialize(context);
                addActionHandler();
            }
            Log.bx("BixbyProxy", "init +" + (System.currentTimeMillis() - currentTimeMillis));
        } catch (Exception e) {
            Log.bxe("BixbyProxy", "init failed. e=" + e.getMessage());
        }
    }
}
