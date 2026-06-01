package com.example.board.service;

import com.example.board.dto.MemberDTO;
import com.example.board.entity.MemberEntity;
import com.example.board.repository.MemberRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRespository memberRespository;

    public void save(MemberDTO memberDTO){

        MemberEntity memberEntity =
                MemberEntity.toMemberEntity(memberDTO);

        memberRespository.save(memberEntity);
    }

    public boolean login(MemberDTO memberDTO){

        Optional<MemberEntity> optionalMember =
                memberRespository.findByMemberEmail(
                        memberDTO.getMemberEmail());

        if(optionalMember.isPresent()){

            MemberEntity memberEntity =
                    optionalMember.get();

            return memberEntity.getMemberPassword().equals(
                    memberDTO.getMemberPassword());

        }
        return false;
    }
}
