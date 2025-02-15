package park.haru.common.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class join_informationDTO {
    @Id
    @Column(name = "imp_uid")
    private String imp_uid;

    @Column(name = "birth")
    private String birth;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "certified")
    private String certified;

    @Column(name = "certified_at")
    private String certified_at;

    @Column(name = "foreigner")
    private String foreigner;

    @Column(name = "foreigner_v2")
    private String foreigner_v2;

    @Column(name = "gender")
    private String gender;

    @Column(name = "merchant_uid")
    private String merchant_uid;

    @Column(name = "name")
    private String name;

    @Column(name = "origin")
    private String origin;

    @Column(name = "pg_provider")
    private String pg_provider;

    @Column(name = "pg_tid")
    private String pg_tid;

    @Column(name = "phone")
    private String phone;

    @Column(name = "unique_in_site")
    private String unique_in_site;

    @Column(name = "unique_key")
    private String unique_key;

    @Column(name = "error_code")
    private String error_code;

    @Column(name = "error_msg")
    private String error_msg;

    @Column(name = "pg_type")
    private String pg_type;

    @Column(name = "success")
    private String success;

    @Column(name = "access_token")
    private String access_token;

    @Column(name = "code")
    private String code;

    @Column(name = "message")
    private String message;

    @Column(name = "response")
    private String response;

    @Column(name = "haruMarket_user_id")
    private String haruMarket_user_id;
}
