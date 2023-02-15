package com.jpabook.jpashop.repository;

import com.jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    // 내가 알던 방식이랑 다르긴함. interface로 만들어서 자동으로 만들어주는 기능을 사용한거 같은데
    // 스프링 Data JPA를 사용하는것이 아닌 순수한 JPA를 사용하는 것인듯.
    // @PersistenceContext, 요즘은 @RequiredArgsConstructor을 씀.
    private final EntityManager em;

    // 사이드이펙트를 일으키는 커멘드성이기때문에 웬만하면 저장할때는 리턴하지는 않는다.
    // 그래서 id정도만 리턴한다. member객체 그자체를 리턴하는것보다
    public Long save(Member member){

        em.persist(member);
        return member.getId();
    }

    public Member findOne(Long id){
        return em.find(Member.class,id);
    }

    // JPQL사용
    public List<Member> findAll(){
        return em.createQuery("select m from Member m",Member.class)
                .getResultList();
    }

    // JPQL사용
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();
    }
}
