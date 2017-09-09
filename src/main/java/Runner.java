import entitys.Client;
import entitys.Group;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Andrew on 06.09.2017.
 */
public class Runner {
    public static void main( String[] args ) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPATest");
        EntityManager em = emf.createEntityManager();
        try {
            Group group1 = new Group("Course-1");
            Group group2 = new Group("Course-2");
            Client client;
            long gid1, gid2;

            // #1
            System.out.println("------------------ #1 ------------------");

            for (int i = 1; i <= 10; i++) {
                client = new Client("Name" + i, i);
                group1.addClient(client);
            }
            for (int i = 1; i <= 5; i++) {
                client = new Client("Name" + i, i);
                group2.addClient(client);
            }

            em.getTransaction().begin();
            try {
                em.persist(group1); // save groups with clients
                em.persist(group2);
                em.getTransaction().commit();

                System.out.println("New group id #1: " + (gid1 = group1.getId()));
                System.out.println("New group id #2: " + (gid2 = group2.getId()));
            } catch (Exception ex) {
                em.getTransaction().rollback();
                return;
            }

            // #2
            System.out.println("------------------ #2 ------------------");

            Query query = em.createQuery("select c from Group c");
            List<Group> groups = query.getResultList();

            for (Group g : groups) {
                System.out.println("group name: " + g.getName() + ", num of clients " + g.getClients().size() );
            }

        } finally {
            em.close();
            emf.close();
        }
    }
}
