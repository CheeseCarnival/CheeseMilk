package com.cheeseocean.common.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cheeseocean.common.exception.UnexpectedException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_user_auth")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserAuth extends BasicEntity{

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserInfo userInfo;

    @Convert(converter = IdentityTypeConverter.class)
    @Column(name = "identify_type")
    private IdentityType identifyType;

    @Column(name = "identifier")
    private String identifier;

    @Column(name = "credential")
    private String credential;

    @Column(name = "bind_flag")
    private boolean bindFlag;

    public enum IdentityType{
        USERNAME("username"),
        EMAIL("email"),
        PHONE("phone"),
        WECHAT("wechat"),
        QQ("qq"),
        GITHUB("github");

        private final String typeName;
        IdentityType(String typeName){
            this.typeName = typeName;
        }

        public String getTypeName() {
            return typeName;
        }

        public static IdentityType from(String value){
            for (IdentityType identityType : IdentityType.values()) {
                if (identityType.typeName.equals(value)){
                    return identityType;
                }
            }
            throw new UnexpectedException("Not supported IdentityType");
        }
    }
    static class IdentityTypeConverter implements AttributeConverter<IdentityType, String>{
        @Override
        public String convertToDatabaseColumn(IdentityType attribute) {
            return attribute.typeName;
        }

        @Override
        public IdentityType convertToEntityAttribute(String dbData) {
            return IdentityType.from(dbData);
        }
    }
}
