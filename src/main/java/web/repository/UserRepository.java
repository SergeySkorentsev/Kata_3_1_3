package web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.id = :id")
    User getUserById(@Param("id") int id);
    @Query("select u from User u where u.email = :username")
    User findUserByUsername (@Param("username") String username);
}
