package park.haru.common.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class harumarket_productcategory {
    @Id
    @Column(name = "haruMarket_productCategory_index")
    long haruMarket_productCategory_index;
    @Column(name = "haruMarket_productCategory_name")
    String haruMarket_productCategory_name;
    @Column(name = "haruMarket_productCategory_view")
    int haruMarket_productCategory_view;
}
