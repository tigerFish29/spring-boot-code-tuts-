package com.spring.springuser.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.springuser.model.UserInfo;

@Repository
public interface  UserRepository extends JpaRepository <UserInfo, Integer> {

    List<UserInfo> findAllByActiveOrderByIdDesc(boolean active);

    Optional<UserInfo> findByIdAndActive(Integer id, boolean active);
    
}
