package com.example.ss15_bai3.service;


import com.example.ss15_bai3.repository.IOrderRepository;
import com.example.ss15_bai3.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.ss15_bai3.model.Order;
import com.example.ss15_bai3.model.Product;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;
    private final IOrderRepository orderRepository;

    @Override
    public Page<Order> getOrderHistory(
            Long userId,
            String status,
            int page,
            int size,
            String sortBy,
            String direction
    ) {

        if (page < 0) {
            page = 0;
        }

        List<String> allowedSortFields = List.of("orderDate", "totalAmount");

        if (!allowedSortFields.contains(sortBy)) {
            sortBy = "orderDate";
        }

        Sort.Direction sortDirection =
                direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<Order> result;

        //
        if (status == null || status.equalsIgnoreCase("ALL")) {
            result = orderRepository.findByUserId(userId, pageable);
        } else {
            result = orderRepository.findByUserIdAndStatus(userId, status, pageable);
        }

        // Lỗi tràn viền phân trang
        if (page >= result.getTotalPages() && result.getTotalPages() > 0) {
            pageable = PageRequest.of(result.getTotalPages() - 1, size, Sort.by(sortDirection, sortBy));

            if (status == null || status.equalsIgnoreCase("ALL")) {
                result = orderRepository.findByUserId(userId, pageable);
            } else {
                result = orderRepository.findByUserIdAndStatus(userId, status, pageable);
            }
        }

        return result;
    }

    @Override
    public List<Product> getProductsByCategoryAndMaxPrice(String category, Double maxPrice) {

        return productRepository
                .findByCategoryAndPriceLessThanEqualAndStockQuantityGreaterThan(
                        category,
                        maxPrice,
                        0
                );
    }

    @Override
    public void cancelFraudulentOrder(Long orderId) {

        if (orderId == null || orderId <= 0) {
            throw new IllegalArgumentException("OrderId không hợp lệ");
        }

        int updated = orderRepository.cancelFraudulentOrder(orderId);

        if (updated == 0) {
            throw new RuntimeException(
                    "Không thể hủy đơn (không tồn tại hoặc đã giao)"
            );
        }
    }
}