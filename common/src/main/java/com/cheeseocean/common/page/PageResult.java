package com.cheeseocean.common.page;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 1099331838954070419L;

    private final int requestOffset;

    private final int pageSize;

    private final int totalSize;

    private final List<T> data;

    private final int totalPages;

    private final boolean hasNext;

    public PageResult(int requestOffset, int pageSize, List<T> data, int totalSize) {
        this.requestOffset = requestOffset;
        this.pageSize = pageSize;
        this.data = data;
        this.totalSize = totalSize;
        int remain = totalSize % pageSize;
        this.totalPages = remain > 0 ? (totalSize / pageSize) + 1 : totalSize / pageSize;
        this.hasNext = totalSize - requestOffset - pageSize > 0;
    }

    public int getOffset() {
        return requestOffset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<T> getData() {
        return data;
    }

    public boolean hasNext() {
        return hasNext;
    }

}
