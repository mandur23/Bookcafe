// 이 클래스는 회원 정보를 나타내는 엔티티입니다.
// JPA를 사용하여 데이터베이스의 'member_table'과 매핑됩니다.
// 회원의 이메일, 비밀번호, 이름을 저장하며, 
// MemberDTO 객체를 MemberEntity로 변환하는 메서드를 제공합니다.

package com.example.bookcafe.entity;

import com.example.bookcafe.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity // 이 클래스가 JPA 엔티티임을 나타냄
@Setter // Lombok을 사용하여 setter 메서드 자동 생성
@Getter // Lombok을 사용하여 getter 메서드 자동 생성
@Table(name = "member_table") // 데이터베이스의 'member_table'과 매핑
public class MemberEntity {
    @Id // 이 필드가 기본 키임을 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키의 값 자동 생성 전략 설정
    private Long id; // 회원 ID

    @Column(unique = true, nullable = false) // unique 제약조건 추가 및 null 불가 설정
    private String memberEmail; // 회원 이메일

    @Column // 비밀번호 필드
    private String memberPassword; // 회원 비밀번호

    @Column // 이름 필드
    private String memberName; // 회원 이름

    // MemberDTO 객체를 MemberEntity로 변환하는 메서드
    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail()); // 이메일 설정
        memberEntity.setMemberPassword(memberDTO.getMemberPassword()); // 비밀번호 설정
        memberEntity.setMemberName(memberDTO.getMemberName()); // 이름 설정
        return memberEntity; // 변환된 MemberEntity 반환
    }

    // MemberDTO 객체를 업데이트할 MemberEntity로 변환하는 메서드
    public static MemberEntity toupdateMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDTO.getId()); // ID 설정
        memberEntity.setMemberEmail(memberDTO.getMemberEmail()); // 이메일 설정
        memberEntity.setMemberPassword(memberDTO.getMemberPassword()); // 비밀번호 설정
        memberEntity.setMemberName(memberDTO.getMemberName()); // 이름 설정
        return memberEntity; // 변환된 MemberEntity 반환
    }
}
