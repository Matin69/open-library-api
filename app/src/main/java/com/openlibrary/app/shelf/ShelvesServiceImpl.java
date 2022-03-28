package com.openlibrary.app.shelf;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelvesServiceImpl implements ShelvesService {

    private final GoogleShelfApi googleShelfApi;

    public ShelvesServiceImpl(GoogleShelfApi googleShelfApi) {
        this.googleShelfApi = googleShelfApi;
    }

    @Override
    public List<Shelf> getUserShelves(String userAccessToken) {
        ShelvesCollectionResponse userShelves = googleShelfApi.getMyLibraryShelves(userAccessToken);
        return userShelves.getItems();
    }
}
