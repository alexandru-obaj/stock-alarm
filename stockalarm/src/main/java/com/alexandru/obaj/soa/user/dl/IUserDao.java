package com.alexandru.obaj.soa.user.dl;

import com.alexandru.obaj.soa.user.dl.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends JpaRepository<UserEntity, Integer> {

    UserEntity findByUserId(@Param("userId") String userId);

    UserEntity findByUserIdAndPassword(@Param("userId") String userId, @Param("password") String password);

}
