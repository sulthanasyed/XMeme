package com.crio.starter.controller;

import lombok.RequiredArgsConstructor;
import java.util.Optional;
import com.crio.starter.data.Meme;
import com.crio.starter.exchange.MemeCreateResponse;
import com.crio.starter.service.MemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/memes")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemeController {
    
    @Autowired
    MemeService memeService;
    
    @GetMapping
    private ResponseEntity<Object> getTopMemes() {
        return new ResponseEntity<>(memeService.getTopMemes(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createMeme(@RequestBody Meme meme) {
        if(!memeService.validateMeme(meme)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if(memeService.checkIfMemeAlreadyExists(meme)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        MemeCreateResponse response = memeService.saveMeme(meme);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getMemeById(@PathVariable String id) {
        Optional<Meme> memeById = memeService.getMemeById(id);
        System.out.println(id + "" + memeById);
        if(memeById.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(memeById.get(), HttpStatus.OK);
    }
    
}
