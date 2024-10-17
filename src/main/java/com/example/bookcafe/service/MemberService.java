package com.example.bookcafe.service;

import com.example.bookcafe.dto.MemberDTO;
import com.example.bookcafe.entity.MemberEntity;
import com.example.bookcafe.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public void idsave(MemberDTO memberDTO) {
        //save 메소드 호출 (조건. entity로 넘겨야함)
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
    }
    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> byMemberEmail =memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if(byMemberEmail.isPresent()) {
            //조회됨
            MemberEntity memberEntity = byMemberEmail.get();
            if(memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
                // 비밀번호 일치
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            }
            else {
                //비밀번호 불일치
                return null;
            }
        }
        else{
            //조회 없음
            return null;
        }
    }
}
