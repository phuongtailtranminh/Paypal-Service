package me.phuongtm.paypalex.constants;

public class QueryParam {

    /**
     * The number of items to list in the response.
     */
    public static final String COUNT = "count";

    /**
     * The end date and time for the range to show in the response,
     * in Internet date and time format. For example, end_time=2016-03-06T11:00:00Z.
     */
    public static final String END_TIME = "end_time";

    /**
     * The zero-relative start index of the entire list of items that are returned in the response.
     * So, the combination of page=0 and page_size=20 returns the first 20 items.
     * The combination of page=20 and page_size=20 returns the next 20 items.
     */
    public static final String PAGE = "page";

    /**
     * The number of items to return in the response.
     */
    public static final String PAGE_SIZE = "page_size";

    /**
     * Indicates whether to show the total count in the response.
     */
    public static final String TOTAL_COUNT_REQUIRED = "total_count_required";

    /**
     * Sorts the payments in the response by a specified value, such as the create time or update time.
     */
    public static final String SORT_BY = "sort_by";

    /**
     * Sorts the items in the response in ascending or descending order.
     */
    public static final String SORT_ORDER = "sort_order";

    /**
     * The ID of the starting resource in the response. When results are paged, you can use the next_id value
     * as the start_id to continue with the next set of results.
     */
    public static final String START_ID = "start_id";

    /**
     * The start index of the payments to list. Typically, you use the start_index to jump to a specific position
     * in the resource history based on its cart. For example, to start at the second item in a list of results, specify ?start_index=2.
     */
    public static final String START_INDEX = "start_index";

    /**
     * The start date and time for the range to show in the response, in Internet date and time format.
     * For example, start_time=2016-03-06T11:00:00Z.
     */
    public static final String START_TIME = "start_time";

}
