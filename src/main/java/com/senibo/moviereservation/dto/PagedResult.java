package com.senibo.moviereservation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Clean pagination response that matches your senior's C# approach
 * This creates a simple, easy-to-understand JSON response
 */
public record PagedResult<T>(

    @JsonProperty("items") List<T> items,

    @JsonProperty("page") int page,

    @JsonProperty("page_size") int pageSize,

    @JsonProperty("total_count") long totalCount,

    @JsonProperty("total_pages") int totalPages,

    @JsonProperty("has_next") boolean hasNext,

    @JsonProperty("has_prev") boolean hasPrev

) {

  /**
   * Converts Spring's Page object to our clean PagedResult
   * This hides all the complex Spring internals from the API response
   */
  public static <T> PagedResult<T> from(Page<T> page) {
    return new PagedResult<>(
        page.getContent(), // The actual items
        page.getNumber(), // Current page number
        page.getSize(), // Items per page
        page.getTotalElements(), // Total items across all pages
        page.getTotalPages(), // Total number of pages
        page.hasNext(), // Is there a next page?
        page.hasPrevious() // Is there a previous page?
    );
  }

}