package ICS223.demo.database;

import ICS223.demo.database.Node;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NodeRepository extends JpaRepository<Node, Long> {
    Node findFirstById(long id);
    Node findFirstByOrderByIdDesc();
}
