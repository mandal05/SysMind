package com.sysmind.service;

import com.sysmind.model.Request;
import com.sysmind.model.Response;
import com.sysmind.model.WordEntityListResponse;
import com.sysmind.model.WordEntityResponse;

public interface Service {
    public Response findIndexes(Request request);
    public WordEntityResponse saveWord(String word);
    public WordEntityListResponse getWords();
}
