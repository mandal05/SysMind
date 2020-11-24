package com.sysmind.dao;

import com.sysmind.model.WordEntityListResponse;
import com.sysmind.model.WordEntityResponse;

public interface WordDao {
    public WordEntityResponse saveWord(String word);
    public WordEntityListResponse getWords();
}
