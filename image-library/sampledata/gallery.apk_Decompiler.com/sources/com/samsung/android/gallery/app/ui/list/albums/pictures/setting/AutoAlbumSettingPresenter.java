package com.samsung.android.gallery.app.ui.list.albums.pictures.setting;

import android.text.TextUtils;
import androidx.preference.DropDownPreference;
import com.samsung.android.gallery.app.controller.album.RenameAutoAlbumCmd;
import com.samsung.android.gallery.app.ui.list.albums.pictures.setting.IAlbumSettingView;
import com.samsung.android.gallery.module.album.AutoAlbumSettingData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.settings.ui.abstaction.IBasePreferenceView;
import com.samsung.android.gallery.settings.ui.delegate.DialogDelegate;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AutoAlbumSettingPresenter<V extends IAlbumSettingView> extends AlbumSettingPresenter<V> {
    private AutoAlbumSettingData mAutoAlbumSettingData;
    private final DialogDelegate.OnDialogListener mDialogListener = new DialogDelegate.OnDialogListener() {
        public void onConfirmed(int i2) {
            AutoAlbumSettingPresenter.this.confirmChange(i2);
        }

        public void onDismiss(DropDownPreference dropDownPreference, int i2) {
            int suggestedContentsRuleType = AutoAlbumSettingPresenter.this.getAutoAlbumSettingData(i2).getSuggestedContentsRuleType();
            dropDownPreference.setValueIndex(suggestedContentsRuleType);
            dropDownPreference.seslGetSpinner().setSelection(suggestedContentsRuleType);
        }
    };

    public AutoAlbumSettingPresenter(V v) {
        super(v);
    }

    /* access modifiers changed from: private */
    public void confirmChange(int i2) {
        getAutoAlbumSettingData(i2).updateSuggestedContentsRuleType(1);
    }

    private void confirmSuggestedContentsRule(DropDownPreference dropDownPreference, int i2) {
        if (dropDownPreference != null) {
            new DialogDelegate((IBasePreferenceView) this.mView).showSuggestedContentsRuleConfirmDialog(dropDownPreference, i2, this.mDialogListener);
        }
    }

    public AutoAlbumSettingData getAutoAlbumSettingData(int i2) {
        if (this.mAutoAlbumSettingData == null) {
            this.mAutoAlbumSettingData = new AutoAlbumSettingData(i2);
        }
        return this.mAutoAlbumSettingData;
    }

    public void onRenameClicked(MediaItem mediaItem) {
        new RenameAutoAlbumCmd().execute(this, mediaItem);
    }

    public void onSelectedCreatureClicked(int i2) {
        String str;
        Features features = Features.SUPPORT_PET_ON_AUTO_ALBUM;
        if (Features.isEnabled(features)) {
            str = "location://search/fileList/Category/CreatureSelect";
        } else {
            str = "location://search/fileList/Category/PeopleSelect";
        }
        UriBuilder appendArg = new UriBuilder(str).appendArg("id", i2);
        if (Features.isEnabled(features)) {
            appendArg.appendArg("creatures_ids", TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, getAutoAlbumSettingData(i2).getCreatureIds()));
        } else {
            appendArg.appendArg("ids", TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, getAutoAlbumSettingData(i2).getPeopleIds()));
        }
        getBlackboard().post("command://MoveURL", appendArg.build());
    }

    public boolean onSuggestedContentsRuleChanged(DropDownPreference dropDownPreference, int i2, int i7) {
        if (i7 == 1) {
            confirmSuggestedContentsRule(dropDownPreference, i2);
            return false;
        }
        getAutoAlbumSettingData(i2).updateSuggestedContentsRuleType(i7);
        return true;
    }
}
