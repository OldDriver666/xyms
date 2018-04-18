
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.fise.service.sensitiveword.ISensitivewordService;


public class UserAssignServiceTest extends BaseJunit4Test {
	
    @Resource
    ISensitivewordService sensitivewordService;

	@Test // 标明是测试方法
	@Transactional // 标明此方法需使用事务
	@Rollback(false) // 标明使用完此方法后事务不回滚,true时为回滚
	public void insert() {
		
		String text = "江蛤蟆毛腊肉温影帝习包子胡荣耻";
		List<String> list1 = sensitivewordService.checkSensitiveWord(text);
		System.out.println(list1.toString());
		
	}

}