package com.example.ss15_bai4.service;

import com.example.ss15_bai4.model.Order;
import com.example.ss15_bai4.model.Product;
import com.example.ss15_bai4.repository.IOrderRepository;
import com.example.ss15_bai4.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

        //
        if (page < 0) {
            page = 0;
        }

        //
        List<String> allowedSortFields = List.of("orderDate", "totalAmount");

        if (!allowedSortFields.contains(sortBy)) {
            sortBy = "orderDate"; // default
        }

        //
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

        // 1. Validate input
        if (orderId == null || orderId <= 0) {
            throw new IllegalArgumentException("OrderId không hợp lệ");
        }

        // 2. Thực hiện update
        int updated = orderRepository.cancelFraudulentOrder(orderId);

        // 3. Kiểm tra kết quả
        if (updated == 0) {
            throw new RuntimeException(
                    "Không thể hủy đơn (không tồn tại hoặc đã giao)"
            );
        }
    }
    @Override
    public int applyDiscount(String category, Double discount) {

        // Bẫy 1: discount không hợp lệ
        if (discount == null || discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Discount không hợp lệ (0-100)");
        }

        // Bẫy 2: danh mục không có sản phẩm
        int count = productRepository.countByCategoryAndStatus(category, "ACTIVE");

        if (count == 0) {
            throw new RuntimeException("Không tìm thấy sản phẩm nào để cập nhật");
        }

        // Update
        return productRepository.updatePriceByCategory(category, discount);
    }
}