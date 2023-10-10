package com.example.ecommerce.infrastructure.member;

import com.example.ecommerce.domain.member.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberEntityRepository extends JpaRepository<MemberEntity, Long> {

    @Query("select m from MemberEntity m where m.id = :id and m.isDeleted = false")
    Optional<MemberEntity> findMemberEntityById(@Param("id") Long id);

    @Query("select m from MemberEntity m where m.username = :username and m.isDeleted = false")
    Optional<MemberEntity> findMemberEntityByUsername(@Param("username") String username);

    @Query("select m from MemberEntity m join fetch m.membership where m.id = :id and m.isDeleted = false")
    Optional<MemberEntity> findByIdFetchMembership(@Param("id") Long id);

    @Query("select m from MemberEntity m join fetch m.membership where m.username = :username and m.isDeleted = false")
    Optional<MemberEntity> findByUsernameFetchMembership(@Param("username") String username);

    boolean existsByUsername(String username);

//    Page<MemberEntity> findAllPaging(Pageable pageable);
}
