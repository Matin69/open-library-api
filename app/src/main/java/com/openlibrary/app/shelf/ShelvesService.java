package com.openlibrary.app.shelf;

import java.util.List;

public interface ShelvesService {

    List<ShelfResponse> getUserShelves(String userAccessToken);
}
