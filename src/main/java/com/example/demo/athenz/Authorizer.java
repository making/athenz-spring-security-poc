package com.example.demo.athenz;

public interface Authorizer {
	boolean access(String action, String resource, String token);
}