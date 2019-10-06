package com.cqyc.domain.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: cqyc
 * Description:
 * Created by cqyc on 19-10-4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private Integer rid;

    private String rname;

    private Set<Permission> permissions = new HashSet<>();

    private Set<User> users = new HashSet<>();

}
