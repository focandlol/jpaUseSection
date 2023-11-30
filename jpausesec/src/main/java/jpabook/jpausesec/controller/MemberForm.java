package jpabook.jpausesec.controller;

import jakarta.validation.constraints.NotEmpty;
import jpabook.jpausesec.domain.Address;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String name;

    /*private String city;
    private String street;
    private String zipcode;*/

    private Address address;
}
