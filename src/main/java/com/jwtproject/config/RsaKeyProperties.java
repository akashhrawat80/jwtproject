package com.jwtproject.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import java.security.interfaces.*;

@ConfigurationProperties(prefix="rsa")
public record RsaKeyProperties(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
	
}
