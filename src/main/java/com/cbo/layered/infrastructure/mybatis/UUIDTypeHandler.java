package com.cbo.layered.infrastructure.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;
import java.util.UUID;

public class UUIDTypeHandler extends BaseTypeHandler<UUID> {

    /**
     * Sets a non-null UUID parameter on the PreparedStatement.
     *
     * @param ps The PreparedStatement to set the parameter on
     * @param i The parameter index (1-based)
     * @param parameter The UUID value to set
     * @param jdbcType The JDBC type of the parameter
     * @throws SQLException If a database access error occurs
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, UUID parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.toString());
    }

    /**
     * Gets a UUID from the ResultSet by column name.
     *
     * @param rs The ResultSet to get the value from
     * @param columnName The name of the column to get the value from
     * @return The UUID value, or null if the column value was null
     * @throws SQLException If a database access error occurs
     */
    @Override
    public UUID getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return value == null ? null : UUID.fromString(value);
    }

    /**
     * Gets a UUID from the ResultSet by column index.
     *
     * @param rs The ResultSet to get the value from
     * @param columnIndex The index of the column to get the value from (1-based)
     * @return The UUID value, or null if the column value was null
     * @throws SQLException If a database access error occurs
     */
    @Override
    public UUID getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return value == null ? null : UUID.fromString(value);
    }

    /**
     * Gets a UUID from a CallableStatement (used for stored procedures).
     *
     * @param cs The CallableStatement to get the value from
     * @param columnIndex The index of the parameter to get (1-based)
     * @return The UUID value, or null if the parameter value was null
     * @throws SQLException If a database access error occurs
     */
    @Override
    public UUID getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return value == null ? null : UUID.fromString(value);
    }
}