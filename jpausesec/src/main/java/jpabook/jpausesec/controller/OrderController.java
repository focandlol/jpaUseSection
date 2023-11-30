package jpabook.jpausesec.controller;

import jpabook.jpausesec.domain.Member;
import jpabook.jpausesec.domain.Order;
import jpabook.jpausesec.domain.item.Item;
import jpabook.jpausesec.domain.item.OrderDto;
import jpabook.jpausesec.repository.OrderSearch;
import jpabook.jpausesec.service.ItemService;
import jpabook.jpausesec.service.MemberService;
import jpabook.jpausesec.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model){

        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members",members);
        model.addAttribute("items",items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
            //@ModelAttribute OrderDto orderDto,
            @RequestParam("count") int count){
        log.info("ggggg");
        /*for (OrderDto dto : orderDto.getOrderDtoList()) {
            log.info("asdfsdf");
            log.info("dto.getItemId() = {}",dto.getItemId());
            log.info("dto.getMemberId() = {}",dto.getMemberId());
        }*/
        orderService.order(memberId,itemId,count);
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute OrderSearch orderSearch, Model model){
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders",orders);

        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable Long orderId){
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
