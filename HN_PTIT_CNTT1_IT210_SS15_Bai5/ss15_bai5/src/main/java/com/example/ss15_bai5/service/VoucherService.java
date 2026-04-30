package com.example.ss15_bai5.service;

import com.example.ss15_bai5.model.UserVoucher;
import com.example.ss15_bai5.model.Voucher;
import com.example.ss15_bai5.model.VoucherStatus;
import com.example.ss15_bai5.repository.UserVoucherRepository;
import com.example.ss15_bai5.repository.VoucherRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VoucherService {

    private final VoucherRepository voucherRepository;
    private final UserVoucherRepository userVoucherRepository;

    @Transactional
    public void applyVoucher(Long userId, String code) {

        Voucher voucher = voucherRepository.findByCodeForUpdate(code)
                .orElseThrow(() -> new RuntimeException("Voucher không tồn tại"));

        if (voucher.getStatus() != VoucherStatus.ACTIVE) {
            throw new RuntimeException("Voucher đã bị vô hiệu hóa");
        }

        if (voucher.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Voucher đã hết hạn");
        }

        if (voucher.getQuantity() <= 0) {
            throw new RuntimeException("Voucher đã hết lượt sử dụng");
        }

        if (userVoucherRepository.existsByUserIdAndVoucherId(userId, voucher.getId())) {
            throw new RuntimeException("Voucher đã được sử dụng");
        }

        voucher.setQuantity(voucher.getQuantity() - 1);

        UserVoucher uv = new UserVoucher();
        uv.setUserId(userId);
        uv.setVoucherId(voucher.getId());
        uv.setUsedAt(LocalDateTime.now());

        userVoucherRepository.save(uv);
    }
}