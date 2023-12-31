package com.bujikun.fsd.capstone.eHealthcare.security.model.mapping;

import com.bujikun.fsd.capstone.eHealthcare.security.model.SecurityUserDetails;
import lombok.NoArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.nio.ByteBuffer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
public class SecurityUserDetailsResultSetExtractor implements ResultSetExtractor<SecurityUserDetails> {
    @Override
    public SecurityUserDetails extractData(ResultSet rs) throws SQLException, DataAccessException {
        SecurityUserDetails securityUserDetails = null;
        Set<String> permissionNames = new HashSet<>();
        while (rs.next()) {
            if (rs.isFirst()) {
                securityUserDetails = new SecurityUserDetails(
                       toUUID( rs.getBytes("id")),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("fullname"),
                        rs.getBigDecimal("balance"),
                        rs.getString("account_number"),
                        rs.getBoolean("is_account_locked"),
                        rs.getBoolean("is_account_expired"),
                        rs.getBoolean("is_credentials_expired"),
                        rs.getBoolean("is_enabled"),
                        permissionNames

                );
            }
            securityUserDetails.getPermissionNames().add(rs.getString("permission_name"));
        }
        return securityUserDetails;
    }

    public UUID toUUID(byte[] source) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(source);
        Long high = byteBuffer.getLong();
        Long low = byteBuffer.getLong();
        return new UUID(high,low);
    }
}
