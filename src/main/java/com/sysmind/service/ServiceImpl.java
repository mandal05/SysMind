package com.sysmind.service;

import com.sysmind.dao.WordDao;
import com.sysmind.model.Request;
import com.sysmind.model.Response;
import com.sysmind.model.WordEntityListResponse;
import com.sysmind.model.WordEntityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



@Service
public class ServiceImpl implements com.sysmind.service.Service {
    @Autowired
    WordDao wordDao;

    @Override
    public Response findIndexes(Request request) {
        Response response = new Response();
        try
        {
            if(request.word.length()==0 || request.wordList.size()==0) {
                response.validationResult.addError("Word list or String is empty", "INPUT");
                return response;
            }


            int wordSize = request.wordList.get(0).length();
            int totalWordSize = request.wordList.size()*wordSize;
            String word = request.word;
            String wordList[] = new String[request.wordList.size()];
            HashMap<String,Integer> wordMap = new HashMap<String,Integer>();
            List<Integer> result = new ArrayList<Integer>();
            int index = 0;

            for (String val : request.wordList) {
                if(val.length() != wordSize)
                {
                    response.validationResult.addError("Words are not of equal size", "INPUT");
                    return response;
                }
                wordList[index++] = val;
            }


            
            for(int i=0; i<wordList.length; i++){
                wordMap.put(wordList[i], wordMap.getOrDefault(wordList[i], 0) + 1);
            }


            index = 0;
            while(index <= word.length() - totalWordSize){
                String sub = word.substring(index, index + totalWordSize);
                HashMap<String,Integer> subStringMap = new HashMap<String,Integer>();
                int k = 0;
                int n = 0;

                while(k<wordList.length){
                    String temp = sub.substring(n, n + wordSize);
                    subStringMap.put(temp, subStringMap.getOrDefault(temp,0) + 1);
                    n = n + wordSize;
                    k++;
                }
                if(wordMap.equals(subStringMap))
                    result.add(index);
                index++;
            }
            response.setIndexes(result);
        }catch(Exception ex)
        {
            response.validationResult.addError("ERROR : An error occured" + ex.getMessage(), "EXCEPTION");
        }
        return response;
    }

    @Override
    public WordEntityResponse saveWord(String word) {
        WordEntityResponse response = new WordEntityResponse();
        try
        {
            response = wordDao.saveWord(word);
        }catch(Exception ex)
        {
            response.setResult(false);
            response.validationResult.addError("ERROR : An error occured" + ex.getMessage(), "EXCEPTION");
        }
        return response;
    }

    @Override
    public WordEntityListResponse getWords() {
        WordEntityListResponse response = new WordEntityListResponse();
        try
        {
            response = wordDao.getWords();
        }catch(Exception ex)
        {
            response.validationResult.addError("ERROR : An error occured" + ex.getMessage(), "EXCEPTION");
        }
        return response;
    }
}
