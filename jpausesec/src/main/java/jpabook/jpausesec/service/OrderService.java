package jpabook.jpausesec.service;

import jpabook.jpausesec.domain.Delivery;
import jpabook.jpausesec.domain.Member;
import jpabook.jpausesec.domain.Order;
import jpabook.jpausesec.domain.OrderItem;
import jpabook.jpausesec.domain.item.Item;
import jpabook.jpausesec.repository.ItemRepository;
import jpabook.jpausesec.repository.MemberRepository;
import jpabook.jpausesec.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    //주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count){

        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order); //delivery, orderItem에 Cascade.All 옵션을 넣어서 order만 persist하면 됨
        return order.getId();
    }

    //취소
    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);
        System.out.println("order = " + order);
        order.cancel();
    }

    //검색
    /*public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAll(orderSearch);
    }*/
}
