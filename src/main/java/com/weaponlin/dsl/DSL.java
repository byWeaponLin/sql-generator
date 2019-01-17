package com.weaponlin.dsl;

import com.weaponlin.dsl.builder.DeleteBuilder;
import com.weaponlin.dsl.builder.InsertBuilder;
import com.weaponlin.dsl.builder.SelectBuilder;
import com.weaponlin.dsl.builder.UpdateBuilder;

/**
 * TODO
 */
public class DSL {

    public static SelectBuilder select() {
        return new SelectBuilder();
    }

    public static InsertBuilder insert() {
        return new InsertBuilder();
    }


    public static UpdateBuilder update() {
        return new UpdateBuilder();
    }

    public static DeleteBuilder delete() {
        return new DeleteBuilder();
    }
}
