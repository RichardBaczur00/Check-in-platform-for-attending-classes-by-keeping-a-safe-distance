package uvt.projibm.checkin.controllers;

import org.springframework.web.bind.annotation.*;
import uvt.projibm.checkin.exceptions.UserNotAGuestException;
import uvt.projibm.checkin.exceptions.UserNotAStudentException;
import uvt.projibm.checkin.exceptions.UserNotFoundException;
import uvt.projibm.checkin.models.StudentFilter;
import uvt.projibm.checkin.models.User;
import uvt.projibm.checkin.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/users")
    private List<User> getAllUsers() {
        List<User> tmp = new ArrayList<>();

        repo.findAll().forEach(tmp::add);

        return tmp;
    }

    @PostMapping("/users")
    private User addUser(@RequestBody User userToAdd) {
        return repo.save(userToAdd);
    }

    @DeleteMapping("/users/{id}")
    private void deleteUser(@PathVariable Integer id) {
        repo.deleteById(id);
    }

    @GetMapping("/users/{id}")
    private User getUser(@PathVariable Integer id) {
        return repo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @GetMapping("/students")
    private List<User> getStudents(@RequestBody StudentFilter filter) {
        List<User> tmp = new ArrayList<>();

        repo.findAll().forEach(user -> {
            if (user.getRole().equals("student")) {
                if (filter.getField().equals("firstName") && filter.getData().equals(user.getFirstName())) {
                    tmp.add(user);
                } else if (filter.getField().equals("lastName") && filter.getData().equals(user.getLastName())) {
                    tmp.add(user);
                } else if (filter.getField().equals("year")) {
                    Integer filterData = Integer.parseInt(filter.getData());
                    if (filterData.equals(user.getYear())) {
                        tmp.add(user);
                    }
                } else if (filter.getField().equals("department") && filter.getData().equals(user.getDepartment())) {
                    tmp.add(user);
                } else if (filter.getField().equals("section") && filter.getData().equals(user.getSection())) {
                    tmp.add(user);
                } else if (filter.getField().equals("groupName") && filter.getData().equals(user.getGroupName())) {
                    tmp.add(user);
                }
            }
        });

        return tmp;
    }

    @GetMapping("/students/{id}")
    private User getStudentById(@PathVariable Integer id) {
        User currentUser = repo.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        if (!currentUser.getRole().equals("student")) {
            throw new UserNotAStudentException(id);
        }

        return currentUser;
    }

    @PostMapping("/students/{id}")
    private void giveStudentRights(@PathVariable Integer id) {
        User currentUser = repo.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        if (!currentUser.getRole().equals("guest")) {
            throw new UserNotAGuestException(id);
        }

        currentUser.setRole("student");
        repo.save(currentUser);
    }

    @DeleteMapping("/students/{id}")
    private void removeStudentRights(@PathVariable Integer id) {
        User currentUser = repo.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        if (!currentUser.getRole().equals("student")) {
            throw new UserNotAStudentException(id);
        }

        currentUser.setRole("guest");
        repo.save(currentUser);
    }
}
