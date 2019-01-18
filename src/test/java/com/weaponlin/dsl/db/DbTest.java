package com.weaponlin.dsl.db;

import com.weaponlin.dsl.DSL;
import com.weaponlin.dsl.SQLParameter;
import com.weaponlin.dsl.operand.table.TableOperand;
import org.apache.commons.collections.CollectionUtils;

import java.sql.*;
import java.util.List;

import static com.weaponlin.dsl.operand.transform.ColumnOperand.name;
import static com.weaponlin.dsl.operand.transform.PlaceholderOperand.value;

public class DbTest {

    public static void main(String[] args) {
        SQLParameter sqlParameter = DSL.select().column("id", "name", "gender", "age")
                .from(TableOperand.table("user"))
                .where()
                .and(name("age").gt(value(21)))
                .build();
        Connection conn = DbConnection.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(sqlParameter.getSql())) {
            setParameters(pstmt, sqlParameter.getParameters());

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                long id = (Long)rs.getObject("id");
                int age = rs.getInt("age");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                System.out.println(String.format("%d \t %d \t %s \t %s", id, age, name, gender));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setParameters(PreparedStatement pstmt, List<Object> parameters) throws SQLException {
        for (int i = 0; CollectionUtils.isNotEmpty(parameters) && i < parameters.size(); i++) {
            pstmt.setObject(i + 1, parameters.get(i));
        }
    }
}
