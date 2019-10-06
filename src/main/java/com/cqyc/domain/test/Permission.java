package com.cqyc.domain.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author: cqyc
 * Description:
 * Created by cqyc on 19-10-4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {
    private Integer pid;

    private String name;

    private String url;

}
