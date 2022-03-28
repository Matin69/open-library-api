package com.openlibrary.app.shelf;

import lombok.Data;

import java.util.List;

@Data
public class ShelvesCollectionResponse {

    private List<Shelf> items;
}
