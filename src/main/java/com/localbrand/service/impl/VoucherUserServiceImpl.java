package com.localbrand.service.impl;

import com.localbrand.common.ServiceResult;
import com.localbrand.common.Status_Enum;
import com.localbrand.dto.VoucherDTO;
import com.localbrand.dto.VoucherDonate;
import com.localbrand.dto.response.VoucherUserResponseDTO;
import com.localbrand.entity.Voucher;
import com.localbrand.model_mapping.Impl.VoucherMapping;
import com.localbrand.repository.VoucherRepository;
import com.localbrand.repository.VoucherUserRepository;
import com.localbrand.service.VoucherUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class VoucherUserServiceImpl implements VoucherUserService {

    private final VoucherUserRepository voucherUserRepository;
    private final VoucherRepository voucherRepository;
    private final VoucherMapping voucherMapping;

    @Override
    public ServiceResult<List<VoucherUserResponseDTO>> getListVoucherOfUser(Optional<Integer> idUser, Optional<Integer> page, Optional<Integer> limit) {
        Pageable pageable = PageRequest.of(page.orElse(0), limit.orElse(0));
        List<VoucherUserResponseDTO> voucherUserResponseDTOS = this.voucherUserRepository.findAllByIdUser(idUser.orElse(-1), pageable).toList();
        return new ServiceResult<>(HttpStatus.OK, "Get list voucher by user success", voucherUserResponseDTOS);

    }

    @Override
    public ServiceResult<VoucherDonate> getVoucherDonate(Optional<Float> totalMoney) {
        Voucher voucherDonating = this.voucherRepository.findFirstByDonate(Status_Enum.EXISTS.getCode(),totalMoney.orElse(0F)).orElse(null);
        Voucher voucherDonated = this.voucherRepository.findFirstByCondition(Status_Enum.EXISTS.getCode(),totalMoney.orElse(0F)).orElse(null);

        VoucherDonate voucherDonate = new VoucherDonate();

        if(Objects.nonNull(voucherDonating)){
            voucherDonate.setDonating(this.voucherMapping.toDto(voucherDonating));
        }

        if(Objects.nonNull(voucherDonated)){
            voucherDonate.setDonated(this.voucherMapping.toDto(voucherDonated));
        }

        return new ServiceResult<>(HttpStatus.OK, "Get voucher donate success", voucherDonate);
    }
}
