package entitys;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Andrew on 06.09.2017.
 */
@Entity
@Table(name = "Groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Client> clients;

    public Group() {
    }

    public Group(String name) {
        this.name = name;
        clients = new ArrayList<Client>();
    }

    public void addClient(Client client) {
        client.setGroup(this);
        clients.add(client);

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Client> getClients() {
        return Collections.unmodifiableList(clients);
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", clients=" + clients +
                '}';
    }
}
