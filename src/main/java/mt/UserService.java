package mt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mt.model.User;
import mt.model.UserRepository;
import mt.tenant.TenantThreadLocal;

@Service
@Transactional(readOnly = true)
public class UserService
{
    @Autowired
    private UserRepository repository;

    public Iterable<User> list()
    {
        return repository.findAll();
    }

    @Transactional
    public User create(User user)
    {
        return repository.save(user);
    }

    @Transactional
    public User[] test2()
    {
        TenantThreadLocal.setTenant("at");
        User atUser = new User("atkoc2", "password", true);
        repository.save(atUser);

        TenantThreadLocal.setTenant("hu");
        User huUser = new User("hukoc2", "password", true);
        repository.save(huUser);

        return new User[]{atUser, huUser};
    }
}
