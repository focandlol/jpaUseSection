package jpabook.jpausesec;

import org.assertj.core.api.Assertions;
import org.hibernate.internal.log.SubSystemLogging;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    public void testMember() throws Exception{

        Member member = new Member();
        member.setUsername("memberA");
        System.out.println("member = " + member);

        Long saveId = memberRepository.save(member);

        Member findMember = memberRepository.find(saveId);
        System.out.println("findMember = " + findMember);

        Assertions.assertThat(findMember).isEqualTo(member); //영속성에서 가져와서 객체끼리도 같음
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
    }
}