package com.localbrand.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartListResponseDTO {

    private List<CartProductResponseDTO> cartProducts;

    private List<CartComboResponseDTO> cartCombos;

    private Boolean isNextPage;

    private Float totalMoney;
}
