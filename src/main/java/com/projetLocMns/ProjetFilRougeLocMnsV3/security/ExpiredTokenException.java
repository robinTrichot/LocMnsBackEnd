package com.projetLocMns.ProjetFilRougeLocMnsV3.security;

public class ExpiredTokenException extends Exception{

    public ExpiredTokenException(String message) {
        super(message);

    }
}
