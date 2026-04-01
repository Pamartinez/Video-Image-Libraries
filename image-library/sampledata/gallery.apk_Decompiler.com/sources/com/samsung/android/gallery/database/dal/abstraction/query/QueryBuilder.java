package com.samsung.android.gallery.database.dal.abstraction.query;

import N2.j;
import android.text.TextUtils;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class QueryBuilder {
    private final ArrayList<String[]> mDebugWhere = new ArrayList<>();
    private String mGroupBy;
    private String mHaving;
    private final ArrayList<String> mJoinFrom = new ArrayList<>();
    private String mLimit;
    private final ArrayList<String> mOrderBy = new ArrayList<>();
    private final ArrayList<String> mProjection = new ArrayList<>();
    private final ArrayList<String> mTable = new ArrayList<>();
    private String mTableNameAlias;
    private StringBuilder mWhere = new StringBuilder();
    private final ArrayList<String> mWhereArgs = new ArrayList<>();

    private void buildFrom(StringBuilder sb2) {
        if (this.mTable.size() > 0) {
            sb2.append("\r\n");
            sb2.append(" FROM ");
            convertToString(sb2, this.mTable);
            convertToString(sb2, this.mJoinFrom, (String) null);
        }
    }

    private void buildGroupBy(StringBuilder sb2) {
        if (this.mGroupBy != null) {
            sb2.append("\r\n");
            sb2.append(" GROUP BY ");
            sb2.append(this.mGroupBy);
        }
    }

    private void buildHaving(StringBuilder sb2) {
        if (this.mHaving != null) {
            sb2.append(" HAVING ");
            sb2.append(this.mHaving);
        }
    }

    private void buildLimit(StringBuilder sb2) {
        if (this.mLimit != null) {
            sb2.append(" LIMIT ");
            sb2.append(this.mLimit);
        }
    }

    private void buildOrderBy(StringBuilder sb2) {
        if (!this.mOrderBy.isEmpty()) {
            sb2.append(" ORDER BY ");
            convertToString(sb2, this.mOrderBy);
        }
    }

    private void buildProjection(StringBuilder sb2) {
        if (this.mProjection.size() != 0) {
            convertToString(sb2, this.mProjection);
        } else {
            sb2.append("*");
        }
    }

    private void buildWhere(StringBuilder sb2) {
        if (this.mWhere.length() != 0) {
            sb2.append("\r\n");
            sb2.append(" WHERE ");
            sb2.append(this.mWhere);
        }
    }

    private void convertToString(StringBuilder sb2, ArrayList<String> arrayList) {
        convertToString(sb2, arrayList, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
    }

    public static String escapeString(String str) {
        if (str != null) {
            return str.trim().replaceAll("'", "''");
        }
        return str;
    }

    private int findProjection(String str) {
        for (int i2 = 0; i2 < this.mProjection.size(); i2++) {
            if (this.mProjection.get(i2).contains(" as " + str)) {
                return i2;
            }
        }
        return -1;
    }

    public QueryBuilder addCrossJoin(String str, String str2) {
        ArrayList<String> arrayList = this.mJoinFrom;
        arrayList.add(" CROSS JOIN " + str + " ON " + str2);
        return this;
    }

    public QueryBuilder addFromSelect(String str) {
        ArrayList<String> arrayList = this.mTable;
        arrayList.add("(" + str + ")");
        return this;
    }

    public QueryBuilder addGroupBy(String str) {
        if (this.mGroupBy != null) {
            this.mGroupBy = j.f(new StringBuilder(), this.mGroupBy, ArcCommonLog.TAG_COMMA, str);
            return this;
        }
        this.mGroupBy = str;
        return this;
    }

    public QueryBuilder addInnerJoin(String str, String str2) {
        ArrayList<String> arrayList = this.mJoinFrom;
        arrayList.add(" INNER JOIN " + str + " ON " + str2);
        return this;
    }

    public QueryBuilder addLeftOuterJoin(String str, String str2) {
        ArrayList<String> arrayList = this.mJoinFrom;
        arrayList.add(" LEFT OUTER JOIN " + str + " ON " + str2);
        return this;
    }

    public QueryBuilder addOrderBy(String str) {
        this.mOrderBy.add(str);
        return this;
    }

    public QueryBuilder addOrderByFirst(String str) {
        this.mOrderBy.add(0, str);
        return this;
    }

    public QueryBuilder addProjection(String str) {
        this.mProjection.add(str);
        return this;
    }

    public QueryBuilder addTable(String str) {
        this.mTable.add(str);
        return this;
    }

    public QueryBuilder andCondition(String str) {
        if (!TextUtils.isEmpty(str)) {
            StringBuilder sb2 = this.mWhere;
            if (sb2.indexOf(" " + str + " ") == -1) {
                if (!(this.mWhere.length() == 0 || str.length() == 0)) {
                    this.mWhere.append(" AND ");
                }
                this.mWhere.append(str);
            }
        }
        return this;
    }

    public String build() {
        StringBuilder sb2 = new StringBuilder("SELECT ");
        buildProjection(sb2);
        buildFrom(sb2);
        buildWhere(sb2);
        buildGroupBy(sb2);
        buildHaving(sb2);
        buildOrderBy(sb2);
        buildLimit(sb2);
        return sb2.toString();
    }

    public QueryBuilder clearFrom() {
        this.mTable.clear();
        this.mTableNameAlias = null;
        return this;
    }

    public QueryBuilder clearJoinFrom() {
        this.mJoinFrom.clear();
        return this;
    }

    public QueryBuilder clearLimit() {
        this.mLimit = null;
        return this;
    }

    public QueryBuilder clearOrderBy() {
        this.mOrderBy.clear();
        return this;
    }

    public QueryBuilder clearProjection() {
        this.mProjection.clear();
        return this;
    }

    public QueryBuilder clearSelection() {
        this.mWhere = new StringBuilder();
        return this;
    }

    public QueryBuilder firstOrCondition(String str) {
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        StringBuilder sb2 = this.mWhere;
        sb2.insert(0, "(" + str + ") OR ");
        return this;
    }

    public String getAliasColumnName(String str) {
        if (this.mTableNameAlias == null) {
            return str;
        }
        return j.f(new StringBuilder(), this.mTableNameAlias, ".", str);
    }

    public String[] getArgs() {
        if (this.mWhereArgs.isEmpty()) {
            return null;
        }
        return (String[]) this.mWhereArgs.toArray(new String[0]);
    }

    public String getInnerJoin() {
        StringBuilder sb2 = new StringBuilder();
        convertToString(sb2, this.mJoinFrom);
        return sb2.toString();
    }

    public String getLimit() {
        StringBuilder sb2 = new StringBuilder();
        buildLimit(sb2);
        return sb2.toString();
    }

    public String getOrderBy() {
        StringBuilder sb2 = new StringBuilder();
        buildOrderBy(sb2);
        return sb2.toString();
    }

    public ArrayList<String> getProjectionArray() {
        return this.mProjection;
    }

    public String getTableName() {
        StringBuilder sb2 = new StringBuilder();
        convertToString(sb2, this.mTable);
        return sb2.toString();
    }

    public String getWhere() {
        return this.mWhere.toString();
    }

    public QueryBuilder groupBy(String str) {
        this.mGroupBy = str;
        return this;
    }

    public QueryBuilder having(String str) {
        this.mHaving = str;
        return this;
    }

    public QueryBuilder is(String str) {
        this.mWhereArgs.add(str);
        return this;
    }

    public QueryBuilder limit(String str) {
        this.mLimit = str;
        return this;
    }

    public QueryBuilder removeJoin(String str) {
        int i2 = 0;
        while (true) {
            if (i2 >= this.mJoinFrom.size()) {
                i2 = -1;
                break;
            }
            if (this.mJoinFrom.get(i2).contains("JOIN " + str)) {
                break;
            }
            i2++;
        }
        if (i2 > 0) {
            this.mJoinFrom.remove(i2);
        }
        return this;
    }

    public QueryBuilder removeProjectionByAlias(String... strArr) {
        for (String findProjection : strArr) {
            int findProjection2 = findProjection(findProjection);
            if (findProjection2 >= 0) {
                this.mProjection.remove(findProjection2);
            }
        }
        return this;
    }

    public QueryBuilder replaceProjectionByAlias(String str, String str2) {
        int findProjection = findProjection(str2);
        if (findProjection >= 0) {
            ArrayList<String> arrayList = this.mProjection;
            arrayList.set(findProjection, str + " as " + str2);
            return this;
        }
        addProjection(str, str2);
        return this;
    }

    public QueryBuilder replaceProjectionByAliasWithValue(String str, String str2) {
        int findProjection = findProjection(str2);
        if (findProjection >= 0) {
            this.mProjection.set(findProjection, C0212a.n("'", str, "' as ", str2));
            return this;
        }
        addProjection("'" + str + "'", str2);
        return this;
    }

    public QueryBuilder replaceTable(String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = this.mTable.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next.contains(str)) {
                arrayList.add(str + " AS " + str2 + " indexed by " + str3);
            } else {
                arrayList.add(next);
            }
        }
        this.mTable.clear();
        this.mTable.addAll(arrayList);
        return this;
    }

    public void setWhere(StringBuilder sb2) {
        this.mWhere = sb2;
    }

    private void convertToString(StringBuilder sb2, ArrayList<String> arrayList, String str) {
        if (!arrayList.isEmpty()) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                if (i2 != 0) {
                    if (str != null) {
                        sb2.append(str);
                    }
                    sb2.append(" ");
                }
                sb2.append(arrayList.get(i2));
            }
        }
    }

    public QueryBuilder addProjection(String[] strArr) {
        Collections.addAll(this.mProjection, strArr);
        return this;
    }

    public QueryBuilder addTable(String str, String str2) {
        this.mTableNameAlias = str2;
        ArrayList<String> arrayList = this.mTable;
        arrayList.add(str + " AS " + str2);
        return this;
    }

    public QueryBuilder addProjection(List<String> list) {
        this.mProjection.addAll(list);
        return this;
    }

    public QueryBuilder addProjection(String str, String str2) {
        ArrayList<String> arrayList = this.mProjection;
        arrayList.add(str + " as " + str2);
        return this;
    }

    public QueryBuilder removeProjectionByAlias(String str) {
        int findProjection = findProjection(str);
        if (findProjection >= 0) {
            this.mProjection.remove(findProjection);
        }
        return this;
    }
}
