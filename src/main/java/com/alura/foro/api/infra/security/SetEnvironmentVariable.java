package com.alura.foro.api.infra.security;

/*
 * Tried to run the app after a while and it returned an error
 * saying environment variable wasn't set so I tried this
 */
public class SetEnvironmentVariable {
    public static void main(String[] args) {
        System.setProperty("JWT_SECRET", "1234");

        String apiSecuritySecret = System.getProperty("JWT_SECRET");
        System.out.println("JWT_SECRET is set to: " + apiSecuritySecret);
    }
}
