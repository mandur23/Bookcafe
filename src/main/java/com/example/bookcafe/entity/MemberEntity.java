// 이 클래스는 회원 정보를 나타내는 엔티티입니다.
// JPA를 사용하여 데이터베이스의 'member_table'과 매핑됩니다.
// 회원의 이메일, 비밀번호, 이름을 저장하며, 
// MemberDTO 객체를 MemberEntity로 변환하는 메서드를 제공합니다.

package com.example.bookcafe.entity;

import com.example.bookcafe.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "member_table")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false) // unique 제약조건 추가
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        return memberEntity;
    }
}
