package com.openlibrary.app.book;

import lombok.Data;

import java.util.Set;

@Data
public class VolumesCollectionResponse {

    private int totalItems;

    private Set<VolumeResponse> items;
}
