package park.haru.config.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User{
    @Id
    private int haruMarket_user_index;
    private String haruMarket_user_id;
    private String haruMarket_user_pw;
    private String haruMarket_user_role;
}
