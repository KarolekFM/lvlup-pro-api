package com.programmingwizzard.lvlup.paging;

import com.squareup.moshi.Json;

/*@Json(name = "paging")*/
public class Paging {

    @Json(name = "current_page")
    private final int currentPage;

    @Json(name = "total_pages")
    private final int totalPages;

    @Json(name = "items_per_page")
    private final int itemsPerPage;

    @Json(name = "total_items")
    private final int totalItems;

    public Paging(int currentPage, int totalPages, int itemsPerPage, int totalItems) {
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.itemsPerPage = itemsPerPage;
        this.totalItems = totalItems;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public int getTotalItems() {
        return totalItems;
    }
}
