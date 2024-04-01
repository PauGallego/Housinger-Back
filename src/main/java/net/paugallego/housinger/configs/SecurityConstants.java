package net.paugallego.housinger.configs;

public class SecurityConstants {
    public static final String SECRET = "kYp3s6v9y$B&E)H@McQeThWmZq4t7w!z%C*F-JaNdrgUjXn2r5u8x/A?D(G+KbPe";
    public static final long EXPIRATION_TIME = 604_000_000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER = "Authorization";
    public static final String SIGN_IN_ENDPOINT = "/v1/auth/sign-in";
    public static final String SIGN_UP_ENDPOINT = "/auth/sign-up";
}
