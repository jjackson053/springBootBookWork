package quickPollApp.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import quickPollApp.exception.ResourceNotFoundException;
import quickPollApp.model.Poll;
import quickPollApp.repository.PollRepository;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class PollController {
    @Inject
    private PollRepository pollRepository;

    @RequestMapping(value = "/polls", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        Iterable<Poll> allPolls = pollRepository.findAll();
        return new ResponseEntity<>(pollRepository.findAll(), HttpStatus.OK);

    }
    @RequestMapping(value = "/polls", method = RequestMethod.POST)
    public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll){
        poll = pollRepository.save(poll);

        //Set the location header for the newly created resource
        HttpHeaders responseHeader = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder
                                                .fromCurrentRequest()
                                                .path("/{id}")
                                                .buildAndExpand(poll.getId())
                                                .toUri();
        responseHeader.setLocation(newPollUri);
        return new ResponseEntity<>(null,responseHeader ,HttpStatus.CREATED);

    }

    protected void verifyPoll(Long pollId) throws  ResourceNotFoundException {
        Poll poll = pollRepository.findById(pollId).orElse(null);
        if (poll == null){
            throw new ResourceNotFoundException("Poll with id "+ pollId + " not found");
        }
    }


    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.GET)
    public ResponseEntity<?> getPoll (@PathVariable Long pollId){
        verifyPoll(pollId);
        Poll p = pollRepository.findById(pollId).orElse(null);
        return new ResponseEntity<>(p,HttpStatus.OK);
    }
    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId){
        verifyPoll(pollId);
        //save the entity
         Poll p = pollRepository.save(poll);
         return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll (@PathVariable Long pollId){
       verifyPoll(pollId);
        pollRepository.deleteById(pollId);
       return new ResponseEntity<>(HttpStatus.OK);
    }



}
