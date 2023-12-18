package com.bryanlopes.ficonAPI.user;

public record DataUserList(Long id, String name, Double wallet) {
    public DataUserList(User user) {
        this(user.getId(), user.getName(), user.getWallet());
    }
}
