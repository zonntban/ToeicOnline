package vn.zon2008.core.test;


import org.testng.annotations.Test;
import vn.zon2008.core.dao.RoleDao;
import vn.zon2008.core.daoimpl.RoleDaoImpl;
import vn.zon2008.persistent.entity.RoleEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zon2008 on 8/16/2020.
 */
public class RoleTest {
    @Test
    public void testFindAll() {
        RoleDao roleDao = new RoleDaoImpl();
        List<RoleEntity> roleList = roleDao.findAll();
        System.out.println(roleList.size());
    }

    @Test
    public void testUpdate(){
        RoleDao roleDao = new RoleDaoImpl();
        RoleEntity entity = new RoleEntity();
        entity.setId(2L);
        entity.setName("CHANGED_USER");
        roleDao.update(entity);
    }

    @Test
    public void testFind(){
        RoleDao roleDao = new RoleDaoImpl();
        RoleEntity entity = roleDao.findByID(5L);
        System.out.println(entity.getName());
    }

    @Test
    public void testFindByProperty(){
        RoleDao roleDao = new RoleDaoImpl();
        List<RoleEntity> list = roleDao.findByProperty("name", "USER", "id", false);
        System.out.println(list.size());
    }

    @Test
    public void testDelete(){
        List<Long> ids = new ArrayList<>();
        ids.add(3L);ids.add(4L);ids.add(5L);
        RoleDao roleDao = new RoleDaoImpl();
        Integer result = roleDao.delete(ids);
        System.out.println(result);
    }
}
