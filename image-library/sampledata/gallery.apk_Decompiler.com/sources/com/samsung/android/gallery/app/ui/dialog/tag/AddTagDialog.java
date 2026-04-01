package com.samsung.android.gallery.app.ui.dialog.tag;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.samsung.android.gallery.app.ui.dialog.abstraction.MvpDialog;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.widget.CharacterFilter;
import com.samsung.android.gallery.widget.LengthFilter;
import com.samsung.android.gallery.widget.listview.GalleryRecyclerView;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.sec.android.gallery3d.R;
import o6.p;
import x7.l;
import z4.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AddTagDialog extends MvpDialog<IAddTagDialogView, AddTagDialogPresenter> implements IAddTagDialogView, TextView.OnEditorActionListener {
    /* access modifiers changed from: private */
    public AddTagAdapter mAddTagAdapter;
    ImageView mBackground;
    BottomNavigationView mBottomNavigationView;
    private final RecyclerView.AdapterDataObserver mDataObserver = new RecyclerView.AdapterDataObserver() {
        public void onChanged() {
            int i2;
            AddTagDialog addTagDialog = AddTagDialog.this;
            addTagDialog.setHeaderVisibility(addTagDialog.mEditTextView.getText().toString(), AddTagDialog.this.mAddTagAdapter.getItemCount());
            if (AddTagDialog.this.mAddTagAdapter.getItemCount() > 0) {
                i2 = 0;
            } else {
                i2 = 4;
            }
            AddTagDialog.this.mBackground.setVisibility(i2);
            AddTagDialog.this.mMyTagListView.setVisibility(i2);
        }
    };
    private View mDoneButton;
    private MenuItem mDoneMenu;
    EditText mEditTextView;
    TextView mHeaderTextView;
    /* access modifiers changed from: private */
    public boolean mIsDoneButtonEnabled = false;
    /* access modifiers changed from: private */
    public boolean mIsLandScape = false;
    GalleryRecyclerView mMyTagListView;
    NestedScrollView mScrollView;
    LinearLayout mTagLayout;
    private final TextWatcher mTextWatcher = new TextWatcher() {
        private String removeUnexpectedStr(String str) {
            return str.replaceAll("\\W|_", "");
        }

        public void afterTextChanged(Editable editable) {
            String removeUnexpectedStr = removeUnexpectedStr(editable.toString());
            if (!editable.toString().equals(removeUnexpectedStr)) {
                if (TextUtils.isEmpty(removeUnexpectedStr)) {
                    AddTagDialog.this.showErrorToast();
                }
                AddTagDialog.this.mEditTextView.setText(removeUnexpectedStr);
                EditText editText = AddTagDialog.this.mEditTextView;
                editText.setSelection(editText.getText().length());
            }
            if (AddTagDialog.this.mAddTagAdapter != null) {
                AddTagDialog.this.mAddTagAdapter.getFilter().filter(removeUnexpectedStr);
            }
        }

        public void onTextChanged(CharSequence charSequence, int i2, int i7, int i8) {
            int i10;
            if (AddTagDialog.this.mAddTagAdapter != null) {
                i10 = AddTagDialog.this.mAddTagAdapter.getItemCount();
            } else {
                i10 = 0;
            }
            AddTagDialog.this.setHeaderVisibility(charSequence, i10);
            AddTagDialog.this.mIsDoneButtonEnabled = !TextUtils.isEmpty(charSequence.toString().trim());
            AddTagDialog.this.updateDoneButton();
        }

        public void beforeTextChanged(CharSequence charSequence, int i2, int i7, int i8) {
        }
    };
    private Toast mToast;
    GalleryToolbar mToolbar;

    private void createEditTextView() {
        this.mEditTextView.addTextChangedListener(this.mTextWatcher);
        this.mEditTextView.requestFocus();
        this.mEditTextView.setFilters(getInputFilters());
        this.mEditTextView.setOnEditorActionListener(this);
        this.mEditTextView.setPrivateImeOptions("disableEmoticonInput=true;disablePrediction=true");
    }

    private void createHeaderTextView() {
        this.mHeaderTextView.setText(R.string.tags);
    }

    private void createMyTagView() {
        this.mAddTagAdapter = new AddTagAdapter(getContext().getApplicationContext(), this);
        this.mMyTagListView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mMyTagListView.drawBottomColor();
        this.mMyTagListView.setAdapter(this.mAddTagAdapter);
        this.mAddTagAdapter.registerAdapterDataObserver(this.mDataObserver);
    }

    private int getEditTextTopPadding() {
        if (this.mIsLandScape) {
            return 0;
        }
        return getResources().getDimensionPixelOffset(R.dimen.moreinfo_addtags_edittext_margin_top);
    }

    private InputFilter[] getInputFilters() {
        Context appContext = AppResources.getAppContext();
        return new InputFilter[]{new CharacterFilter(appContext), new LengthFilter(appContext, 50)};
    }

    private void hideSoftInput() {
        InputMethodManager inputMethodManager;
        Context context = getContext();
        if (context != null && (inputMethodManager = (InputMethodManager) context.getSystemService("input_method")) != null) {
            inputMethodManager.hideSoftInputFromWindow(this.mEditTextView.getWindowToken(), 0);
        }
    }

    private void initMenu() {
        boolean z;
        initToolbar();
        if (getContext() != null) {
            if (getContext().getResources().getConfiguration().orientation == 2) {
                z = true;
            } else {
                z = false;
            }
            this.mIsLandScape = z;
        }
        updateView();
    }

    private void initToolbar() {
        this.mToolbar.inflateMenu(R.menu.menu_bottom_appbar_edit_done);
        this.mToolbar.getMenu().findItem(R.id.menu_edit_app_bar_done).setShowAsAction(2);
        this.mToolbar.getMenu().findItem(R.id.menu_edit_app_bar_cancel).setShowAsAction(2);
        this.mToolbar.setOnMenuItemClickListener(new p(28, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindViews$0(View view) {
        onCancelClicked();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindViews$1(View view) {
        onDoneClicked();
    }

    /* access modifiers changed from: private */
    public boolean onOptionsMenuSelected(MenuItem menuItem) {
        hideSoftInput();
        switch (menuItem.getItemId()) {
            case R.id.menu_edit_app_bar_cancel:
                ((AddTagDialogPresenter) this.mPresenter).onNegativeClicked();
                return true;
            case R.id.menu_edit_app_bar_done:
                ((AddTagDialogPresenter) this.mPresenter).onPositiveClicked();
                return true;
            default:
                return true;
        }
    }

    private void setHeaderVisibility(boolean z) {
        TextView textView = this.mHeaderTextView;
        if (textView != null) {
            textView.setVisibility(z ? 0 : 8);
        }
    }

    /* access modifiers changed from: private */
    public void showErrorToast() {
        Toast toast = this.mToast;
        if (toast != null) {
            toast.cancel();
        }
        Toast makeText = Toast.makeText(getApplicationContext(), R.string.cant_use_special_character, 0);
        this.mToast = makeText;
        makeText.show();
    }

    /* access modifiers changed from: private */
    public void showSoftInput() {
        InputMethodManager inputMethodManager;
        Context context = getContext();
        if (context != null && (inputMethodManager = (InputMethodManager) context.getSystemService("input_method")) != null) {
            inputMethodManager.showSoftInput(this.mEditTextView, 1);
        }
    }

    private void updateBottomDoneButton() {
        float f;
        if (this.mDoneButton == null) {
            this.mDoneButton = this.mBottomNavigationView.findViewById(R.id.menu_edit_app_bar_done);
        }
        View view = this.mDoneButton;
        if (this.mIsDoneButtonEnabled) {
            f = 1.0f;
        } else {
            f = 0.4f;
        }
        view.setAlpha(f);
        this.mDoneButton.setEnabled(this.mIsDoneButtonEnabled);
    }

    private void updateBottomMargin() {
        ViewTreeObserver viewTreeObserver = this.mToolbar.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    int i2;
                    int i7;
                    int dimensionPixelOffset = AddTagDialog.this.getResources().getDimensionPixelOffset(R.dimen.bottom_tab_height);
                    NestedScrollView nestedScrollView = AddTagDialog.this.mScrollView;
                    int i8 = 0;
                    if (nestedScrollView != null) {
                        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) nestedScrollView.getLayoutParams();
                        if (AddTagDialog.this.mIsLandScape) {
                            i2 = 0;
                        } else {
                            i2 = dimensionPixelOffset;
                        }
                        layoutParams.bottomMargin = i2;
                        if (AddTagDialog.this.mIsLandScape) {
                            i7 = AddTagDialog.this.mToolbar.getHeight();
                        } else {
                            i7 = 0;
                        }
                        layoutParams.topMargin = i7;
                        AddTagDialog.this.mScrollView.setLayoutParams(layoutParams);
                    }
                    ImageView imageView = AddTagDialog.this.mBackground;
                    if (imageView != null) {
                        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) imageView.getLayoutParams();
                        if (AddTagDialog.this.mIsLandScape) {
                            dimensionPixelOffset = 0;
                        }
                        layoutParams2.bottomMargin = dimensionPixelOffset;
                        if (AddTagDialog.this.mIsLandScape) {
                            i8 = AddTagDialog.this.mToolbar.getHeight();
                        }
                        layoutParams2.topMargin = i8;
                        AddTagDialog.this.mBackground.setLayoutParams(layoutParams2);
                    }
                    AddTagDialog.this.mToolbar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void updateDoneButton() {
        if (this.mIsLandScape) {
            updateMenuDoneButton();
        } else {
            updateBottomDoneButton();
        }
    }

    private void updateEditTextTopPadding() {
        EditText editText = this.mEditTextView;
        editText.setPadding(editText.getPaddingLeft(), getEditTextTopPadding(), this.mEditTextView.getPaddingRight(), this.mEditTextView.getPaddingBottom());
    }

    private void updateMenuDoneButton() {
        if (this.mDoneMenu == null) {
            this.mDoneMenu = this.mToolbar.getMenu().findItem(R.id.menu_edit_app_bar_done);
        }
        this.mDoneMenu.setEnabled(this.mIsDoneButtonEnabled);
    }

    private void updateMenuVisibility() {
        if (this.mIsLandScape) {
            this.mBottomNavigationView.setVisibility(8);
            this.mToolbar.setVisibility(0);
            return;
        }
        this.mBottomNavigationView.setVisibility(0);
        this.mToolbar.setVisibility(8);
    }

    private void updateView() {
        updateMenuVisibility();
        updateBottomMargin();
        updateDoneButton();
        updateEditTextTopPadding();
    }

    public void bindViews(View view) {
        this.mBottomNavigationView = (BottomNavigationView) view.findViewById(R.id.edit_bottom_navigation);
        this.mEditTextView = (EditText) view.findViewById(R.id.add_tag_edit);
        this.mMyTagListView = (GalleryRecyclerView) view.findViewById(R.id.recycler_list);
        this.mToolbar = (GalleryToolbar) view.findViewById(R.id.toolbar);
        this.mBackground = (ImageView) view.findViewById(R.id.background_layer);
        this.mTagLayout = (LinearLayout) view.findViewById(R.id.add_tag_layout);
        this.mScrollView = (NestedScrollView) view.findViewById(R.id.add_tag_scroll_view);
        this.mHeaderTextView = (TextView) view.findViewById(R.id.gallery_header_title_text);
        view.findViewById(R.id.menu_edit_app_bar_cancel).setOnClickListener(new a(this, 0));
        view.findViewById(R.id.menu_edit_app_bar_done).setOnClickListener(new a(this, 1));
    }

    public String getInputText() {
        return this.mEditTextView.getText().toString();
    }

    public int getLayoutId() {
        return R.layout.add_tag_dialog;
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_ADD_TAG_VIEW.toString();
    }

    public int getSoftInputMode() {
        return 16;
    }

    public void onCancel(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/AddTag", (Object) null);
    }

    public void onCancelClicked() {
        hideSoftInput();
        ((AddTagDialogPresenter) this.mPresenter).onNegativeClicked();
    }

    public void onConfigurationChanged(Configuration configuration) {
        boolean z;
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 2) {
            z = true;
        } else {
            z = false;
        }
        if (z != this.mIsLandScape) {
            this.mIsLandScape = z;
            updateView();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDestroyView() {
        super.onDestroyView();
        AddTagAdapter addTagAdapter = this.mAddTagAdapter;
        if (addTagAdapter != null) {
            addTagAdapter.unregisterAdapterDataObserver(this.mDataObserver);
        }
        Toast toast = this.mToast;
        if (toast != null) {
            toast.cancel();
        }
    }

    public void onDoneClicked() {
        hideSoftInput();
        ((AddTagDialogPresenter) this.mPresenter).onPositiveClicked();
    }

    public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
        if (i2 != 6 && (keyEvent == null || keyEvent.getKeyCode() != 66)) {
            return true;
        }
        hideSoftInput();
        ((AddTagDialogPresenter) this.mPresenter).onPositiveClicked();
        return true;
    }

    public void onItemClicked(String str) {
        ((AddTagDialogPresenter) this.mPresenter).onItemClicked(str);
    }

    public void onResume() {
        super.onResume();
        this.mEditTextView.requestFocus();
        this.mEditTextView.postDelayed(new l(8, this), 300);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        createEditTextView();
        createMyTagView();
        createHeaderTextView();
        initMenu();
    }

    public void updateTagName(String str) {
        this.mEditTextView.setText(str);
        EditText editText = this.mEditTextView;
        editText.setSelection(editText.getText().length());
    }

    public AddTagDialogPresenter createDialogPresenter(IAddTagDialogView iAddTagDialogView) {
        return new AddTagDialogPresenter(iAddTagDialogView);
    }

    /* access modifiers changed from: private */
    public void setHeaderVisibility(CharSequence charSequence, int i2) {
        boolean z = false;
        boolean z3 = i2 > 0;
        if (TextUtils.isEmpty(charSequence) && z3) {
            z = true;
        }
        setHeaderVisibility(z);
    }
}
