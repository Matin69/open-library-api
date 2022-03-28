package com.openlibrary.app.shelf;

import java.util.List;

public interface ShelvesService {

    List<Shelf> getUserShelves(String userAccessToken);
}
