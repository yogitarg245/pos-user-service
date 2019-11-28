package com.pos.user.management.UserService.persistance.repositories;

import com.pos.user.management.UserService.persistance.entities.PosUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PosUsersRepository extends JpaRepository<PosUsersEntity,Long>,RevisionRepository<PosUsersEntity,Long,Long> {
    Optional<PosUsersEntity> findByEmailId(String emailId);
}
