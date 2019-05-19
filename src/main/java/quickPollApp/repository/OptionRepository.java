package quickPollApp.repository;

import org.springframework.data.repository.CrudRepository;
import quickPollApp.model.Option;

public interface OptionRepository extends CrudRepository<Option,Long> {

}
