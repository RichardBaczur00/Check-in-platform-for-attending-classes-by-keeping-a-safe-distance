package uvt.projibm.checkin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import uvt.projibm.checkin.models.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
@RestController
public class CheckinApplication {

	@Autowired
	private Repository userRepository;

	@Autowired
	private ClassRepository classRepository;

	@Autowired
	private ClassroomRepository classroomRepository;

	public static void main(String[] args) {
		SpringApplication.run(CheckinApplication.class, args);
	}

	@GetMapping("/index")
	public String index() {
		return "Hello World";
	}

	@PostMapping("/addUser")
	public @ResponseBody String addNewUser(@RequestParam String f_name,
										   @RequestParam String l_name,
										   @RequestParam Integer year,
										   @RequestParam String Department,
										   @RequestParam String Section,
										   @RequestParam String GroupName) {
		User tmp = new User();

		tmp.setFirstName(f_name);
		tmp.setLastName(l_name);
		tmp.setYear(year);
		tmp.setDepartment(Department);
		tmp.setSection(Section);
		tmp.setGroupName(GroupName);
		userRepository.save(tmp);
		return String.format("<b>Saved user %s %s</b>", f_name, l_name);
	}

	@PostMapping("/addClass")
	public @ResponseBody String addNewClass(@RequestParam String name,
											@RequestParam Integer year,
											@RequestParam Integer teacher_id,
											@RequestParam String section,
											@RequestParam Integer classroom_id) {
		Clasa tmp = new Clasa();
		Set<User> empty_set = new Set<User>() {
			@Override
			public int size() {
				return 0;
			}

			@Override
			public boolean isEmpty() {
				return false;
			}

			@Override
			public boolean contains(Object o) {
				return false;
			}

			@Override
			public Iterator<User> iterator() {
				return null;
			}

			@Override
			public Object[] toArray() {
				return new Object[0];
			}

			@Override
			public <T> T[] toArray(T[] a) {
				return null;
			}

			@Override
			public boolean add(User user) {
				return false;
			}

			@Override
			public boolean remove(Object o) {
				return false;
			}

			@Override
			public boolean containsAll(Collection<?> c) {
				return false;
			}

			@Override
			public boolean addAll(Collection<? extends User> c) {
				return false;
			}

			@Override
			public boolean retainAll(Collection<?> c) {
				return false;
			}

			@Override
			public boolean removeAll(Collection<?> c) {
				return false;
			}

			@Override
			public void clear() {

			}

			@Override
			public boolean equals(Object o) {
				return false;
			}

			@Override
			public int hashCode() {
				return 0;
			}
		};

		tmp.setName(name);
		tmp.setYear(year);
		Optional<User> teacher_option = userRepository.findById(teacher_id);
		tmp.setTeacher((teacher_option.orElseGet(User::new)));
		tmp.setSection(section);
		tmp.setStudents(empty_set);
		Optional<Classroom> classroom_option = classroomRepository.findById(classroom_id);
		tmp.setClassroom(classroom_option.orElseGet(Classroom::new));

		classRepository.save(tmp);


		return "Done";
	}

	@PostMapping("/addClassroom")
	public @ResponseBody String addNewClassroom(@RequestParam String name,
												@RequestParam String location,
												@RequestParam Integer capacity,
												@RequestParam Long features) {
		Classroom tmp = new Classroom();

		tmp.setName(name);
		tmp.setLocation(location);
		tmp.setCapacity(capacity);
		tmp.setFeatures(features);
		classroomRepository.save(tmp);

		return "Done";
	}


}
