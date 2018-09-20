package com.prueba.app.login;

public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String nombre;
    private Boolean loginOK=false;
    

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public JwtAuthenticationResponse(String accessToken,String nombre) {
        this.accessToken = accessToken;
        this.nombre=nombre;
    }

    public JwtAuthenticationResponse(Boolean loginOK, String accessToken,String nombre) {
    	this.loginOK=loginOK;
        this.accessToken = accessToken;
        this.nombre=nombre;
    }
    
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getLoginOK() {
		return loginOK;
	}

	public void setLoginOK(Boolean loginOK) {
		this.loginOK = loginOK;
	}
}