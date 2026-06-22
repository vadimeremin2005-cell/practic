package app.repository;

import app.dto.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByPerson_Id(int personId);

    Optional<Message> findByIdAndPerson_Id(int id, int personId);
}
