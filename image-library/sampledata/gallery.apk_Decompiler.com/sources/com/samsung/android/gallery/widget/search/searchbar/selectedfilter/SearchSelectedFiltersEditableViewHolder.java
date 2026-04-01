package com.samsung.android.gallery.widget.search.searchbar.selectedfilter;

import android.text.InputFilter;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.LengthFilter;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.R$id;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchSelectedFiltersEditableViewHolder extends RecyclerView.ViewHolder {
    final EditText mEditText;

    public SearchSelectedFiltersEditableViewHolder(View view) {
        super(view);
        EditText editText = (EditText) view.findViewById(R$id.search_filter_edit_text);
        this.mEditText = editText;
        editText.setFilters(new InputFilter[]{new LengthFilter(this.itemView.getContext(), 100)});
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.NewSearchBar)) {
            editText.setTextColor(AppResources.getColor(R$color.new_search_bar_text_color));
        }
    }

    public void clearIME() {
        this.mEditText.clearFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) this.itemView.getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(this.mEditText.getWindowToken(), 0);
        }
    }

    public void requestFocusAndShowIME() {
        this.mEditText.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) this.itemView.getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(this.mEditText, 1);
        }
    }
}
