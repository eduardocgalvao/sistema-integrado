package com.grupo.sistema.model;

public class Sessao {
    private static Sessao instance = null;
    private Usuario usuarioLogado;

    private Sessao() {}

    public static Sessao getInstance() {
        if (instance == null) {
            synchronized (Sessao.class) {
                if (instance == null) instance = new Sessao();
            }
        }
        return instance;
    }

    public Usuario getUsuario() {
        return usuarioLogado;
    }

    public void setUsuario(Usuario u) {
        this.usuarioLogado = u;
    }

    public boolean isAdmin() {
        return usuarioLogado != null && "ADMIN".equalsIgnoreCase(usuarioLogado.getPapel());
    }

    public void logout() {
        usuarioLogado = null;
    }
}
