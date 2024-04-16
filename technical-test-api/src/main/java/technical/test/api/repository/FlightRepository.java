package technical.test.api.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import technical.test.api.record.FlightRecord;

import java.util.UUID;

@Repository
public interface FlightRepository extends ReactiveMongoRepository<FlightRecord, UUID> {

    @Query("SELECT * FROM flight")
    Flux<FlightRecord> findAllPaged(Pageable pageable);

}
