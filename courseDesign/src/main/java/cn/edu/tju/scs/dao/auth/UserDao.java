package cn.edu.tju.scs.dao.auth;


import cn.edu.tju.scs.dto.entity.AdminUserDTO;
import cn.edu.tju.scs.dto.entity.UserBasicInfo;
import cn.edu.tju.scs.dto.entity.UserResponseDTO;
import cn.edu.tju.scs.entity.auth.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by haoxiaotian on 2016/9/3 1:52.
 */
public interface UserDao {


    /**
     * 根据用户的uid查找用户的信息
     * @param uid
     * @return
     */
    public UserBasicInfo queryUserByUid(String uid);

    /**
     * 根据用户的uid查找用户的全部信息
     * @param uid
     * @return
     */
    public User queryUser(String uid);

    /**
     * 获取用户的个数
     * @return
     */
    public int queryUserCount();

    /**
     * 根据ID查找指定user信息
     * @param id
     * @return
     */
    public AdminUserDTO queryUserById(int id);

    /**
     * 根据email，查找用户信息
     * @param email
     * @return
     */
    public User queryUserByEmail(String email);

    /**
     * 查询所有用户的信息
     * @return
     */
    public List<UserResponseDTO> queryAllUser();

    /**
     * 查询所有网站客户的信息
     * @return
     */
    public List<UserResponseDTO> queryAllSubscribers();

    /**
     * 查询为 id 的用户
     * @return
     */
    public UserResponseDTO querySpecificUser(Integer id);


    /**
     * 删除为 id 的用户
     * @return
     */
    public Integer deleteUser(Integer id);

    /**
     * 创建默认用户
     * @return
     */
    public int createDefautUser(User user);

    /**
     * 查询用户列表信息（后台）
     * @param limit
     * @param offset
     * @return
     */
    public List<AdminUserDTO> queryAdminUserList(@Param("limit") int limit,
                                                 @Param("offset") int offset);


    /**
     * 修改用户的信息
     * @param mail
     * @param username
     * @param company
     * @param password
     * @param salt
     * @return
     */
    public int updateUser(@Param("id") int id,
                          @Param("mail") String mail,
                          @Param("username") String username,
                          @Param("company") String company,
                          @Param("password") String password,
                          @Param("salt") String salt);

    /**
     * 激活用户
     * @param id
     * @return
     */
    public int activeUser(@Param("id") int id);

    /**
     * 重置密码
     * @param id
     * @param finalPassword
     * @param salt
     */
    public int resetPassword(@Param("id")int id, @Param("password")String finalPassword, @Param("salt")String salt);
}
