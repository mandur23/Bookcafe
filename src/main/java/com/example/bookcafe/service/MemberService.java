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


    // 회원가입 처리 메소드
    public String registerMember(MemberDTO memberDTO) {

        // 새로운 회원 저장
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberRepository.save(memberEntity);

        return "회원가입이 성공적으로 완료되었습니다.";
    }

    public MemberDTO updateForm(String myEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(myEmail);
        if(optionalMemberEntity.isPresent()) {
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        }
        else {
            return null;
        }
    }
    public String MemberFix(MemberDTO memberDTO) {
        Optional<MemberEntity> existingMember = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());

        if (existingMember.isPresent() && !existingMember.get().getId().equals(memberDTO.getId())) {
            return "이미 사용 중인 이메일입니다."; // 중복 이메일 메시지 반환
        }

        MemberEntity memberEntity = existingMember.orElse(new MemberEntity());

        // 이름 변경 시에만 수정
        if (!memberEntity.getMemberName().equals(memberDTO.getMemberName())) {
            memberEntity.setMemberName(memberDTO.getMemberName());
        }

        // 비밀번호 변경 시에만 수정
        if (!memberDTO.getMemberPassword().isEmpty()) {
            memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        }

        memberRepository.save(memberEntity); // 수정된 회원 정보를 데이터베이스에 저장
        return "회원 정보가 성공적으로 수정되었습니다."; // 성공 메시지 반환
    }
    public String deleteMember(String email) {
        Optional<MemberEntity> existingMember = memberRepository.findByMemberEmail(email);
        
        if (existingMember.isPresent()) {
            memberRepository.delete(existingMember.get()); // 회원 삭제
            return "회원 탈퇴가 완료되었습니다."; // 성공 메시지 반환
        } else {
            return "회원 정보를 찾을 수 없습니다."; // 오류 메시지 반환
        }
    }
}
