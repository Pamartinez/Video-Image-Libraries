package com.samsung.android.gallery.app.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.dialog.ErrorType;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.io.File;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Pattern;
import o6.B;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateAlbumDialog extends CreateNameDialog {
    protected final String DEFAULT_PATH = (StorageInfo.getDefault().album + File.separator);
    private String mPendedError = null;
    private int mPrevSpinnerPosition;
    Spinner mSpinner;
    View mSpinnerContainer;
    private String mStorage;

    private ErrorType checkNameExists(String str) {
        ErrorType checkNameExist = AlbumHelper.getInstance().checkNameExist((getDefaultPath() + str).trim(), getAlbumType());
        if (checkNameExist.isCreateAlbumErrorCase()) {
            setError(checkNameExist.getStringId());
        }
        return checkNameExist;
    }

    private boolean checkSDPathExist(String str) {
        if (!FileUtils.isSdcardMounted(getContext()) || isKnox()) {
            return false;
        }
        return FileUtils.exists(StorageInfo.getRemovable().dcim + File.separator + str);
    }

    private int getAlbumType() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            return Integer.parseInt(arguments.getString("albumType", String.valueOf(AlbumType.None.toInt())));
        }
        return AlbumType.None.toInt();
    }

    private String getDefaultNameForMergedAlbum(String str) {
        int i2 = 1;
        while (true) {
            StringBuilder t = C0212a.t(str, " ");
            t.append(String.format(Locale.getDefault(), "%d", new Object[]{Integer.valueOf(i2)}));
            String sb2 = t.toString();
            String p6 = C0212a.p(new StringBuilder(), this.DEFAULT_PATH, sb2);
            if (!FileUtils.exists(p6) && !checkSDPathExist(sb2)) {
                return p6.substring(this.DEFAULT_PATH.length());
            }
            i2++;
        }
    }

    private String getDefaultNameLegacy(String str) {
        String str2 = this.DEFAULT_PATH;
        String A10 = C0212a.A(str2, str);
        int i2 = 1;
        while (true) {
            StringBuilder t = C0212a.t(A10, " ");
            t.append(String.format(Locale.getDefault(), "%d", new Object[]{Integer.valueOf(i2)}));
            String sb2 = t.toString();
            if (!FileUtils.exists(sb2)) {
                return sb2.substring(str2.length());
            }
            i2++;
        }
    }

    private String getStorage() {
        String str = this.mStorage;
        if (str == null) {
            return this.DEFAULT_PATH;
        }
        return str;
    }

    private void initSpinner() {
        Context context = getContext();
        if (context != null) {
            View view = this.mSpinnerContainer;
            if (view != null) {
                view.setVisibility(0);
            }
            ArrayAdapter<CharSequence> createFromResource = ArrayAdapter.createFromResource(context, R.array.saving_storage, R.layout.spinner_popup_item);
            createFromResource.setDropDownViewResource(R.layout.spinner_popup_drop_item);
            Spinner spinner = this.mSpinner;
            if (spinner != null) {
                spinner.setAdapter(createFromResource);
                this.mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j2) {
                        CreateAlbumDialog.this.onSpinnerSelected(i2);
                    }

                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
            }
        }
    }

    private ErrorType isDuplicatedName(String str) {
        ErrorType checkNameExists = checkNameExists(str);
        ErrorType errorType = ErrorType.NONE;
        if (checkNameExists != errorType) {
            Log.d(this.TAG, "name is exist or default name");
            return checkNameExists;
        }
        if (!this.mPreviousName.equals(str)) {
            clearError();
        }
        if (isSameOrgName(str)) {
            return ErrorType.ALBUM_NAME_ALREADY_IN_USE;
        }
        return errorType;
    }

    private ErrorType isValidName(String str) {
        if (str.contains("\n")) {
            str = str.replaceAll("\n", " ");
        }
        if (str.equals(".") || str.equals("..")) {
            return ErrorType.UNABLE_TO_CREATE_ALBUM;
        }
        return ErrorType.NONE;
    }

    private String loadTitle() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string = arguments.getString("error", (String) null);
            if (!TextUtils.isEmpty(string)) {
                String string2 = arguments.getString("title", (String) null);
                this.mPendedError = string;
                return string2;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void onSpinnerSelected(int i2) {
        boolean z;
        AnalyticsDetail analyticsDetail;
        if (i2 > 0) {
            z = true;
        } else {
            z = false;
        }
        setStorage(z);
        String str = this.mPendedError;
        if (str != null) {
            setError(str);
            this.mPendedError = null;
            setPositiveButtonEnabled(false);
        } else if (this.mPrevSpinnerPosition != i2 && !isPositiveButtonEnabled()) {
            this.mPrevSpinnerPosition = i2;
            setPositiveButtonEnabled(true);
            clearError();
        }
        AnalyticsEventId analyticsEventId = AnalyticsEventId.EVENT_CREATE_ALBUM_CHOICE_STORAGE;
        if (i2 > 0) {
            analyticsDetail = AnalyticsDetail.STORAGE_SDCARD;
        } else {
            analyticsDetail = AnalyticsDetail.STORAGE_DEVICE;
        }
        postAnalyticsLog(analyticsEventId, analyticsDetail.toString());
    }

    private void publishInternal(String str) {
        getBlackboard().post("data://user/dialog/AlbumName", new Object[]{str, getDefaultPath() + str, Boolean.TRUE});
    }

    private void setStorage(boolean z) {
        String str;
        if (z) {
            str = getString(R.string.new_album_storage_sdcard);
        } else {
            str = this.DEFAULT_PATH;
        }
        this.mStorage = str;
    }

    private boolean showSpinner() {
        if (!FileUtils.isSdcardMounted(getContext()) || isKnox()) {
            return false;
        }
        return true;
    }

    public void bindViews(View view) {
        super.bindViews(view);
        this.mSpinner = (Spinner) view.findViewById(R.id.select_storage);
        this.mSpinnerContainer = view.findViewById(R.id.select_storage_container);
    }

    public String getDefaultName() {
        String loadTitle = loadTitle();
        if (!TextUtils.isEmpty(loadTitle)) {
            return loadTitle;
        }
        String string = getString(R.string.album);
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || !PreferenceFeatures.isEnabled(PreferenceFeatures.MxAlbumsMergeNames)) {
            return getDefaultNameLegacy(string);
        }
        return getDefaultNameForMergedAlbum(string);
    }

    public String getDefaultPath() {
        String str = this.DEFAULT_PATH;
        if (str.equals(getStorage())) {
            return str;
        }
        return StorageInfo.getRemovable().dcim + File.separator;
    }

    public String getHint() {
        return getString(R.string.enter_album_name);
    }

    public int getLayoutId() {
        return R.layout.alert_dialog_create_album;
    }

    public String getTitle() {
        return getString(R.string.new_album);
    }

    public void initDialog() {
        super.initDialog();
        this.mStorage = getDefaultPath();
        if (this.mSpinnerContainer == null) {
            return;
        }
        if (showSpinner()) {
            initSpinner();
        } else {
            setStorage(false);
        }
    }

    public ErrorType isValid(String str) {
        if (isDottedText(str)) {
            return ErrorType.CANNOT_START_WITH_A_PERIOD_FOR_ALBUM_NAME;
        }
        ErrorType isDuplicatedName = isDuplicatedName(str);
        if (isDuplicatedName != ErrorType.NONE) {
            return isDuplicatedName;
        }
        return isValidName(str);
    }

    public void onDetach() {
        super.onDetach();
        Optional.ofNullable(getBlackboard()).ifPresent(new B(14));
    }

    public void publishCancel() {
        postAnalyticsLog(AnalyticsEventId.EVENT_CREATE_ALBUM_CANCEL);
    }

    public void publishData(String str) {
        publishInternal(str);
        postAnalyticsLog(AnalyticsEventId.EVENT_CREATE_ALBUM_CREATE);
    }

    public String replaceLastDots(String str) {
        if (!this.DEFAULT_PATH.equals(this.mStorage)) {
            String[] split = Pattern.compile("[.]+$").split(str);
            if (split.length >= 1) {
                return split[0];
            }
        }
        return str;
    }

    public boolean useCustomTitle() {
        return !showSpinner();
    }
}
