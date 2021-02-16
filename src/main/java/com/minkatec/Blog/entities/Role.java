package com.minkatec.Blog.entities;

public enum Role {

    ADMIN,WRITER,AUTHENTICATED,READER;

    public String roleName() {
        return "ROLE_" + this.toString();
    }

    public boolean isRole() {
        for(Role role: Role.values()) {
            if(!role.roleName().equals(this)) {
                return false;
            }
        }
        return true;
    }

}
