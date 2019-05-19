package quickPollApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import quickPollApp.model.Vote;
import quickPollApp.model.VoteResult;
import quickPollApp.repository.VoteRepository;
import sun.misc.Request;

import javax.inject.Inject;
@RestController
public class ComputeResultController {
    @Inject
    private VoteRepository voteRepository;

    @RequestMapping(value = "/computeresult", method = RequestMethod.GET)
    public ResponseEntity<?> computeResult(@RequestParam Long pollId){
        VoteResult voteResult = new VoteResult();
       Iterable<Vote> allvotes = voteRepository.findByPoll(pollId);
       //Algorithm to count votes

        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);

    }

}
