package cn.icmyfuture.iarc.data.service;

import cn.icmyfuture.iarc.data.config.CacheConfig;
import cn.icmyfuture.iarc.data.dao.UserDao;
import cn.icmyfuture.iarc.data.solr.document.SolrUser;
import cn.icmyfuture.iarc.data.solr.repository.SolrUserRepository;
import cn.icmyfuture.iarc.entity.User;
import cn.icmyfuture.iarc.service.api.UserService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private SolrUserRepository userRepository;

    @Override
    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        Cache cache = cacheManager.getCache(CacheConfig.getAllUsers);
        String key = String.format("%s", CacheConfig.getAllUsers);
        Cache.ValueWrapper wrapper = cache.get(key);
        List<User> users;
        if (wrapper == null) {
            users = userDao.getAllUsers();
            cache.put(key, users);
            return users;
        }
        return (List<User>) wrapper.get();
    }

    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public List<User> getUsersByName(String name) {
        List<SolrUser> users = userRepository.findByNameContaining(name);
        List<User> results = new ArrayList<>();
        if(users != null) {
            users.forEach( user -> {
                User usr = new User();
                usr.setId(Integer.parseInt(user.getId()));
                usr.setName(user.getName());
                results.add(usr);
            });
        }
        return results;
    }
}
