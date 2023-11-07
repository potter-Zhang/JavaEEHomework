package demo.dao;

import demo.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Repository
@Service
public interface UserRepository extends JpaRepository<User, String> {
    @Query("select id, password from user where id = ?1 limit 1")
    Optional<User> findById(String id);

    @Query("delete from user where id = ?1")
    void deleteById(String id);

}
