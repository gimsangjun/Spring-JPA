package com.jpabook.jpashop.repository;

import com.jpabook.jpashop.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class) // JUnit에게 스프링과 관련된 테스트하겠다고 알려줄거임.
@SpringBootTest // 스프링부트로 테스트를 돌려야하니까
public class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false) // 테스트 끝난다음에 roll back을 자동으로 하나, 이렇게 해주면 DB에 남아있다.
    public void testMember() throws Exception{
        //given
        Member member = new Member();
        member.setName("memberA");
        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.findOne(saveId);

        //then
        Assertions.assertEquals(findMember.getId(), member.getId());
    }

}