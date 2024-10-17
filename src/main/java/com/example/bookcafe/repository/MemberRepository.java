// 이 인터페이스는 회원 리포지토리를 정의합니다.
// JpaRepository를 상속받아 기본적인 CRUD 기능을 제공합니다.
// MemberEntity 객체를 Long 타입의 ID로 관리합니다.
package com.example.bookcafe.repository;

import com.example.bookcafe.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    //닉네임 정보 조회
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
}
