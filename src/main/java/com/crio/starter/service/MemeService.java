package com.crio.starter.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Collections;
import com.crio.starter.data.Meme;
import com.crio.starter.exchange.MemeCreateResponse;
import com.crio.starter.repository.MemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemeService {
    
    @Autowired
    MemeRepository memeRepository;
    
    public List<Meme> getTopMemes() {
        List<Meme> top100Memes = new ArrayList<>();
        List<Meme> allMemes = memeRepository.findAll();
        Collections.reverse(allMemes);
        if(allMemes.size() <100)
            return allMemes;
        for(int i=0; i<100; i++) {
            top100Memes.add(allMemes.get(i));
        }
        return top100Memes;
    }

    public MemeCreateResponse saveMeme(Meme meme) {
        meme.setId(String.valueOf((int) (Math.random()*10000))); // 0.564728237 -> 5647.28237 -> 5647 -> "5647"
        Meme savedMeme = memeRepository.save(meme);
        MemeCreateResponse response = new MemeCreateResponse(savedMeme.getId());
        return response;
    }

    public Optional<Meme> getMemeById(String id) {
        return memeRepository.findById(id);
    }

    public boolean validateMeme(Meme meme) {
        if(meme.getName() == null && meme.getCaption() == null && meme.getImageUrl() == null) 
            return false;
        return true;
    }

    public boolean checkIfMemeAlreadyExists(Meme meme) {
        List<Meme> memesList = memeRepository.findAll();
        for (Meme memeFromDB : memesList) {
            if(memeFromDB != null && memeFromDB.getName().equalsIgnoreCase(meme.getName()) 
                && memeFromDB.getCaption().equalsIgnoreCase(meme.getCaption()) 
                && memeFromDB.getImageUrl().equalsIgnoreCase(meme.getImageUrl())) {
                    return true;
                }
        }
        return false;
    }
}
