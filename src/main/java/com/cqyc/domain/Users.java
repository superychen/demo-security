package com.cqyc.domain;

    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.extension.activerecord.Model;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.io.Serializable;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 
    * </p>
*
* @author cqyc
* @since 2019-10-04
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class Users extends Model<Users> {

    private static final long serialVersionUID = 1L;

            @TableId(value = "username", type = IdType.AUTO)
    private String username;

    private String password;

    private Boolean enabled;


    @Override
    protected Serializable pkVal() {
        return this.username;
    }

}
