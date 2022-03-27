package com.cheeseocean.common.page;

/**
 * @author xxxcrel
 * Created on 2022-03-27
 */
public class PageRequest {

    private long page;

    private long size;

    private Sort sort;

    public PageRequest(){}
    private PageRequest(Builder builder) {
        setPage(builder.page);
        setSize(builder.size);
        setSort(builder.sort);
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public static final class Builder {
        private long page;
        private long size;
        private Sort sort;

        private Builder() {}

        public Builder page(long val) {
            page = val;
            return this;
        }

        public Builder size(long val) {
            size = val;
            return this;
        }

        public Builder sort(Sort val) {
            sort = val;
            return this;
        }

        public PageRequest build() {
            return new PageRequest(this);
        }
    }
}
