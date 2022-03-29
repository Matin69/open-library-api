package com.openlibrary.app;

import lombok.Data;

import java.util.List;

@Data
public class GoogleCollectionResponse<T> {

    private int totalItems;

    private List<T> items;
}
