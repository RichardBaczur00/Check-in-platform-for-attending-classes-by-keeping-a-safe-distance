package uvt.projibm.checkin.repositories;

import org.springframework.data.repository.CrudRepository;
import uvt.projibm.checkin.models.Classroom;

public interface ClassroomRepository extends CrudRepository<Classroom, Integer> {
}
