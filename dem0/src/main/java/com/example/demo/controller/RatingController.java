package com.example.demo.controller;

import com.example.demo.model.Vote;
import com.example.demo.repository.VoteRepository;
import com.example.demo.request.VoteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RatingController {
    @Autowired
    private VoteRepository voteRepository;

    @PostMapping(value = "/vote/update")
    public ResponseEntity<String> updateRating(VoteDTO voteDTO){
        Vote vote = voteRepository.findById(1).get();
        if(vote.getRate() > 5){
            vote.setRate(5);
        }
        voteRepository.save(vote);
        return ResponseEntity.ok("Rating Overrided as 5");
    }
}
