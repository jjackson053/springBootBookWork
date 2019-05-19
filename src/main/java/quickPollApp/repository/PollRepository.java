package quickPollApp.repository;

import org.springframework.data.repository.CrudRepository;
import quickPollApp.model.Poll;

public interface PollRepository extends CrudRepository<Poll, Long> {

}
