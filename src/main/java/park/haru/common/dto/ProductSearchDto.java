package park.haru.common.dto;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@ToString
public class ProductSearchDto {
    @Id
    @Column(name = "haruMarket_productCategory_index")
    private int haruMarket_productCategory_index;
    @Column(name = "harumarket_product_name")
    private String harumarket_product_name;
    @Column(name = "page")
    private int page;
}
