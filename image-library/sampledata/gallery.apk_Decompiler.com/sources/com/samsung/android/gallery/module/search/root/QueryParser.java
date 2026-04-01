package com.samsung.android.gallery.module.search.root;

import com.samsung.android.gallery.support.utils.Features;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class QueryParser {
    private List<String> resultList;

    public QueryParser() {
        this.resultList = null;
        this.resultList = new ArrayList();
    }

    public String[] regexParser(String str) {
        if (Features.isEnabled(Features.SUPPORT_QUERY_PARSER)) {
            Matcher matcher = Pattern.compile("\\[([^\\[]+)\\]").matcher(str);
            while (matcher.find()) {
                this.resultList.add(matcher.group().replaceAll("^\\[|\\]$", ""));
            }
            return (String[]) this.resultList.toArray(new String[0]);
        } else if (str.equals("search_suggest_regex_query")) {
            return str.split("search_suggest_regex_query");
        } else {
            return str.split("\n");
        }
    }
}
