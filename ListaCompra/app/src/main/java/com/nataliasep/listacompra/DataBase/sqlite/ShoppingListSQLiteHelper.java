package com.germangascon.pruebasqlite.db.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.germangascon.pruebasqlite.utils.HashGenerator;

import java.security.NoSuchAlgorithmException;

/**
 * UsersSQLiteHelper
 * License: 🅮 Public Domain
 * <p>PruebaSQLite</p>
 * @author Germán Gascón
 * @version 0.3, 2023-01-11
 * @since 0.1, 2019-01-28
 **/
public class UsersSQLiteHelper extends SQLiteOpenHelper {
    private static UsersSQLiteHelper sInstance;
    private static final String DB_NAME = "users.db";
    private static final int DB_VERSION = 1;

    private static final String sqlCreate = "CREATE TABLE user (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "username TEXT NOT NULL, " +
            "password TEXT NOT NULL); ";

    private static final String sqlInsert;

    static {
        try {
            sqlInsert = "INSERT INTO user (username, password) " +
                                "VALUES ('ejemplo', '" + HashGenerator.getSHAString("secreto") + "');";
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized UsersSQLiteHelper getInstance(Context context) {
        if (sInstance == null) {
            // Usamos el contexto de la aplicación para asegurarnos que no se pierde
            // el contexto, por ejemplo de una Activity.
            sInstance = new UsersSQLiteHelper(context.getApplicationContext());
        }
        return  sInstance;
    }

    // Definimos el constructor privado para asegurarnos que no lo utilice nadie desde fuera
    // Así forzamos a utilizar getInstance()
    private UsersSQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Este método sólo se ejecuta si la base de datos no existe
        db.execSQL(sqlCreate);
        // Creamos un usuario por defecto
        db.execSQL(sqlInsert);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Aquí irán las sentencias de actualización de la base de datos
    }
}
