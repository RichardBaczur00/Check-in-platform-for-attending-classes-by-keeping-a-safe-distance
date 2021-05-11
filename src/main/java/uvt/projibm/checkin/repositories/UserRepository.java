package uvt.projibm.checkin.repositories;

import org.springframework.data.repository.CrudRepository;
import uvt.projibm.checkin.models.User;

//TODO: Extend from JPARepository instead...
public interface UserRepository extends CrudRepository<User, Integer> {
}
