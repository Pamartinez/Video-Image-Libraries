package com.samsung.android.gallery.database.dal.abstraction.query;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Query {
    private final ArrayList<Query> mIntersectQueryList = new ArrayList<>();
    private String mLimitString;
    private String mOrderByString;
    private final QueryBuilder mQueryBuilder;
    private final ArrayList<Query> mUnionAllQueryList = new ArrayList<>();
    private final ArrayList<Query> mUnionQueryList = new ArrayList<>();

    public Query(QueryBuilder queryBuilder) {
        this.mQueryBuilder = queryBuilder;
    }

    private String getLimitString() {
        if (this.mLimitString == null) {
            this.mLimitString = this.mQueryBuilder.getLimit();
            this.mQueryBuilder.clearLimit();
        }
        return this.mLimitString;
    }

    private String getOrderByString() {
        if (this.mOrderByString == null) {
            this.mOrderByString = this.mQueryBuilder.getOrderBy();
            this.mQueryBuilder.clearOrderBy();
        }
        return this.mOrderByString;
    }

    public String buildSql() {
        return toString();
    }

    public QueryBuilder getQueryBuilder() {
        return this.mQueryBuilder;
    }

    public void intersect(Query query) {
        this.mIntersectQueryList.add(query);
        query.getQueryBuilder().clearOrderBy();
        query.getQueryBuilder().clearLimit();
    }

    public String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder();
        String str2 = null;
        if (this.mQueryBuilder != null) {
            if (!this.mUnionAllQueryList.isEmpty() || !this.mIntersectQueryList.isEmpty() || !this.mUnionQueryList.isEmpty()) {
                str2 = getOrderByString();
                str = getLimitString();
            } else {
                str = null;
            }
            sb2.append(this.mQueryBuilder.build());
        } else {
            str = null;
        }
        Iterator<Query> it = this.mUnionQueryList.iterator();
        while (it.hasNext()) {
            sb2.append(" \nUNION\n ");
            sb2.append(it.next().toString());
        }
        Iterator<Query> it2 = this.mUnionAllQueryList.iterator();
        while (it2.hasNext()) {
            sb2.append(" \nUNION ALL\n ");
            sb2.append(it2.next().toString());
        }
        Iterator<Query> it3 = this.mIntersectQueryList.iterator();
        while (it3.hasNext()) {
            sb2.append(" \nINTERSECT\n ");
            sb2.append(it3.next().toString());
        }
        if (!TextUtils.isEmpty(str2)) {
            sb2.append(str2);
        }
        if (!TextUtils.isEmpty(str)) {
            sb2.append(str);
        }
        return sb2.toString();
    }

    public Query unionAll(Query query) {
        this.mUnionAllQueryList.add(query);
        query.getQueryBuilder().clearOrderBy();
        query.getQueryBuilder().clearLimit();
        return this;
    }

    public Query(Query query) {
        QueryBuilder queryBuilder = new QueryBuilder();
        this.mQueryBuilder = queryBuilder;
        queryBuilder.addTable("(" + query.buildSql() + ")");
    }
}
