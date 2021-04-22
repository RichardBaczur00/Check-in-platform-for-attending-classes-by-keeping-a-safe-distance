package uvt.projibm.checkin.repositories;

import org.springframework.data.repository.CrudRepository;
import uvt.projibm.checkin.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
