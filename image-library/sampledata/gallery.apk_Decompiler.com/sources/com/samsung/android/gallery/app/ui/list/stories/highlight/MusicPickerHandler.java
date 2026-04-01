package com.samsung.android.gallery.app.ui.list.stories.highlight;

import N2.j;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import com.samsung.android.gallery.app.controller.story.StartSoundPickerCmd;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.EventHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.module.bgm.BgmExtraInfo;
import com.samsung.android.gallery.module.bgm.BgmUriService;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.o3dp.lib3dphotography.i;
import com.sec.android.gallery3d.R;
import e5.C0451a;
import g6.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MusicPickerHandler {
    private final EventHandler mEventHandler;
    private final IStoryHighlightView mView;

    public MusicPickerHandler(IStoryHighlightView iStoryHighlightView) {
        this.mView = iStoryHighlightView;
        this.mEventHandler = iStoryHighlightView.getEventHandler();
    }

    private void handleBgmPickDone(Object obj) {
        BgmExtraInfo bgmExtraInfo;
        Bundle bundle = (Bundle) obj;
        if (this.mView.isDestroyed() || bundle == null) {
            bgmExtraInfo = null;
        } else {
            bgmExtraInfo = BgmExtraInfo.parse(bundle);
            this.mEventHandler.lambda$postEvent$0(Event.CHANGE_BGM, bgmExtraInfo);
        }
        Log.d("MusicPickerHandler", "handleBgmPickDone", bgmExtraInfo);
    }

    private void handleMusicPickDone(Object obj) {
        Uri uri = (Uri) obj;
        if (uri != null) {
            handleMusicPickDone(uri);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleMusicPickDone$0(BgmExtraInfo bgmExtraInfo) {
        if (BgmUriService.isSupportingFormat(bgmExtraInfo.path)) {
            this.mView.getEventHandler().lambda$postEvent$0(Event.CHANGE_BGM, bgmExtraInfo);
        } else {
            Toast.makeText(this.mView.getActivity(), R.string.unsupported_format, 1).show();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleMusicPickDone$1(BgmExtraInfo bgmExtraInfo) {
        this.mView.getEventHandler().lambda$postEvent$0(Event.CHANGE_BGM, bgmExtraInfo);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleMusicPickDone$2(Uri uri) {
        Cursor query;
        Throwable th;
        String[] strArr = {"title", "_data"};
        BgmExtraInfo bgmExtraInfo = new BgmExtraInfo();
        bgmExtraInfo.isFragmentedBgm = false;
        bgmExtraInfo.isMyMusic = true;
        if ("content".equals(uri.getScheme())) {
            try {
                query = AppResources.getAppContext().getContentResolver().query(uri, strArr, (String) null, (String[]) null, (String) null);
                if (query != null) {
                    if (query.moveToNext()) {
                        bgmExtraInfo.bgmName = query.getString(query.getColumnIndex("title"));
                        bgmExtraInfo.path = query.getString(query.getColumnIndex("_data"));
                        ThreadUtil.postOnUiThread(new d(this, bgmExtraInfo, 0));
                    }
                }
                if (query != null) {
                    query.close();
                    return;
                }
                return;
            } catch (SQLiteException | UnsupportedOperationException e) {
                j.u(e, new StringBuilder("music pick failed e="), "MusicPickerHandler");
                return;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } else {
            Uri uri2 = uri;
            bgmExtraInfo.bgmName = FileUtils.getBaseName(uri2.getPath());
            bgmExtraInfo.path = uri2.getPath();
            ThreadUtil.postOnUiThread(new d(this, bgmExtraInfo, 1));
            return;
        }
        throw th;
    }

    /* access modifiers changed from: private */
    public void startSoundPickerCmd() {
        new StartSoundPickerCmd().execute(this.mView.getEventContext(), new Object[0]);
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 1095) {
            handleBgmPickDone(eventMessage.obj);
            return true;
        } else if (i2 == 1096) {
            handleMusicPickDone(eventMessage.obj);
            return true;
        } else if (i2 != 1120) {
            return false;
        } else {
            this.mEventHandler.postEvent(Event.CHECK_AUDIO_PERMISSION, new C0451a(16, this), 1);
            return true;
        }
    }

    private void handleMusicPickDone(Uri uri) {
        SimpleThreadPool.getInstance().execute(new i(20, this, uri));
        this.mView.getEventHandler().lambda$postEvent$0(Event.HIDE_BGM_PICKER, new Object[0]);
    }
}
