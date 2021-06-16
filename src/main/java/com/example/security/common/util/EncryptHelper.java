package com.example.security.common.util;

public interface EncryptHelper {
    String encrypt(String password);
    boolean isMatch(String password, String hashed);
}