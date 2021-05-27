package uvt.projibm.checkin.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String Name;

    private String Location;

    private Integer Capacity;

    private String Features;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public Integer getCapacity() {
        return Capacity;
    }

    public void setCapacity(Integer capacity) {
        Capacity = capacity;
    }

    public String getFeatures() {
        return Features;
    }

    public void setFeatures(String features) {
        Features = features;
    }
}
