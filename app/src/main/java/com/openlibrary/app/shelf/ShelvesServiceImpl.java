package com.openlibrary.app.shelf;

import com.openlibrary.app.GoogleCollectionResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelvesServiceImpl implements ShelvesService {

    private final GoogleShelfApi googleShelfApi;

    public ShelvesServiceImpl(GoogleShelfApi googleShelfApi) {
        this.googleShelfApi = googleShelfApi;
    }

    @Override
    public List<ShelfResponse> getUserShelves(String userAccessToken) {
        GoogleCollectionResponse<ShelfResponse> userShelves = googleShelfApi.getMyLibraryShelves(userAccessToken);
        return userShelves.getItems();
    }
}
