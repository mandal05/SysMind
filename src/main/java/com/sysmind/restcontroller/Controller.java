package com.sysmind.restcontroller;

import com.sysmind.model.Request;
import com.sysmind.model.Response;
import com.sysmind.model.WordEntityListResponse;
import com.sysmind.model.WordEntityResponse;
import com.sysmind.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1")
public class Controller {

    @Autowired
    Service service;

    @CrossOrigin
    @PostMapping("/findWordIndex")
    public ResponseEntity<Response> findWord(@RequestBody Request request) {
        Response response = new Response();
        if(request != null && request.word != null && request.wordList != null)
        {
            response = service.findIndexes(request);
        }
        else
        {
            response.validationResult.addError("Not all parameters fullfilled", "VALIDATION");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/storeWord")
    public ResponseEntity<WordEntityResponse> storeWord(@RequestBody String word) {
        WordEntityResponse wordEntityResponse = new WordEntityResponse();
        wordEntityResponse = service.saveWord(word);
        return new ResponseEntity<>(wordEntityResponse, HttpStatus.OK);
    }


    @CrossOrigin
    @GetMapping("/fetchWord")
    public ResponseEntity<WordEntityListResponse> fetchWords() {
        WordEntityListResponse wordEntityListResponse = new WordEntityListResponse();
        wordEntityListResponse = service.getWords();
        return new ResponseEntity<>(wordEntityListResponse, HttpStatus.OK);
    }
}
