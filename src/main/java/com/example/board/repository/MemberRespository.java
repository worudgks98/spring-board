package com.example.board.repository;

import com.example.board.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRespository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByMemberEmail(String MemberEmail);
}
