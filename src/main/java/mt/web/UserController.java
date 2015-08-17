package mt.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mt.UserService;
import mt.model.User;
import mt.tenant.TenantThreadLocal;

@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UserService userService;

    @RequestMapping(method = GET)
    public Iterable<User> list()
    {
        return userService.list();
    }

    @RequestMapping(method = POST)
    public User add(@RequestBody User user)
    {
        return userService.create(user);
    }

    /**
     * This test works.
     */
    @RequestMapping(value = "/test1", method = GET)
    public User[] test1()
    {
        TenantThreadLocal.setTenant("at");
        User atUser = new User("atkoc1", "password", true);
        userService.create(atUser);

        TenantThreadLocal.setTenant("hu");
        User huUser = new User("hukoc1", "password", true);
        userService.create(huUser);

        return new User[]{atUser, huUser};
    }

    /**
     * This test does NOT work since the transaction is opened after {@link TenantThreadLocal} is set.
     */
    @RequestMapping(value = "/test2", method = GET)
    public User[] test2()
    {
        return userService.test2();
    }

}
