package com.devstack.pos.util;


import org.mindrot.BCrypt;

public class PasswordManager {
    public static String encryptPassword(String planeText){
        return BCrypt.hashpw(planeText,BCrypt.gensalt(10));
    }
    public static boolean checkPassword(String planeText,String hash){
        return BCrypt.checkpw(planeText,hash);
    }
}
