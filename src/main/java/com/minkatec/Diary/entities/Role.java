package com.minkatec.Diary.entities;

public enum Role {

    ADMIN,  WRITER ,USER, GUEST,AUTHENTICATED;

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
