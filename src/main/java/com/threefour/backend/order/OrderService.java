package com.threefour.backend.order;


import org.springframework.stereotype.Service;
import com.threefour.backend.user.UserRepository;
import com.threefour.backend.branch.BranchRepository;
import com.threefour.backend.item.ItemRepository;
import com.threefour.backend.payment.PaymentRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class OrderService {


    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final BranchRepository branchRepository;
    private final ItemRepository itemRepository;
    private final PaymentRepository paymentRepository;
    private final OrderItemRepository orderItemRepository;


    public OrderService(OrderRepository orderRepository, UserRepository userRepository,
                        BranchRepository branchRepository, ItemRepository itemRepository,
                        PaymentRepository paymentRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.branchRepository = branchRepository;
        this.itemRepository = itemRepository;
        this.paymentRepository = paymentRepository;
        this.orderItemRepository = orderItemRepository;
    }


    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    public Order saveRuchitha(Order order) {
        if (order.getUser() != null) {
            com.threefour.backend.user.User managedUser = userRepository.findById(order.getUser().getId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + order.getUser().getId()));
            order.setUser(managedUser);
        }
        if (order.getBranch() != null) {
            com.threefour.backend.branch.Branch managedBranch = branchRepository.findById(order.getBranch().getBranchId())
                    .orElseThrow(() -> new IllegalArgumentException("Branch not found with ID: " + order.getBranch().getBranchId()));
            order.setBranch(managedBranch);
        }
        if (order.getOrderItems() != null) {
            for (OrderItem item : order.getOrderItems()) {
                item.setOrder(order);
                if (item.getProduct() != null) {
                    com.threefour.backend.item.Item managedItem = itemRepository.findById(item.getProduct().getItemId())
                            .orElseThrow(() -> new IllegalArgumentException("Item not found with ID: " + item.getProduct().getItemId()));

                    // Decrease stock quantity in the database
                    int newStock = managedItem.getStockQuantity() - item.getQuantity();
                    if (newStock < 0) {
                        throw new IllegalArgumentException("Insufficient stock for item: " + managedItem.getItemName());
                    }
                    managedItem.setStockQuantity(newStock);
                    itemRepository.save(managedItem); // Update the stock level in the DB

                    item.setProduct(managedItem);
                }
            }
        }
        return orderRepository.save(order);
    }


    public Order getOrderById(int id) {
        return orderRepository.findById(id).orElse(null);
    }


    @Transactional
    public void deleteOrder(int id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found");
        }
        orderRepository.clearPaymentReferenceForOrder(id);
        paymentRepository.deleteByOrder_OrderId(id);
        orderItemRepository.deleteByOrder_OrderId(id);
        orderRepository.deleteById(id);
    }
}