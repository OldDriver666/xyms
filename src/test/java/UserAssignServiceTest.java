
import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.fise.base.Response;
import com.fise.model.param.AdminQuery;
import com.fise.service.administrator.IAdministratorService;


public class UserAssignServiceTest extends BaseJunit4Test {

    @Resource
    private IAdministratorService adminSvr;
	
	@Test // 标明是测试方法
	@Transactional // 标明此方法需使用事务
	@Rollback(false) // 标明使用完此方法后事务不回滚,true时为回滚
	public void insert() {
		Response resp = new Response();
		AdminQuery param = new AdminQuery();
		param.setAdminId(2);
		resp = adminSvr.queryAdmin(param);
		System.out.println(resp);
        
	}

}