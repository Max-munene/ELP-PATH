package com.example.emtechelppathbackend.security.entities;


import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public enum Permissions {

    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    ADMIN_READ("admin:read") //declaring privileges for admin
    ,
    ADMIN_UPDATE("admin:update"),


    ALUMNI_CREATE("alumni:create"),
    ALUMNI_READ("alumni:read"),
    BRANCH_CHAMPION_CREATE("branchChampion:create"),
    BRANCH_CHAMPION_DELETE("branchChampion:delete"),


    BRANCH_CHAMPION_READ("branchChampion:read") //declaring privileges for BranchChampion
    ,
    BRANCH_CHAMPION_UPDATE("branchChampion:update");
    @Getter
    private final String permission;
}
