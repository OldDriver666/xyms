
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.fise.model.entity.SensitiveWords;
import com.fise.service.sensitiveword.ISensitivewordService;


public class UserAssignServiceTest extends BaseJunit4Test {
	
    @Resource
    ISensitivewordService sensitivewordService;

	@Test // 标明是测试方法
	@Transactional // 标明此方法需使用事务
	@Rollback(false) // 标明使用完此方法后事务不回滚,true时为回滚
	public void insert() {
		
		String text = "江蛤蟆毛腊肉温影帝习包子胡荣耻";
		long start = System.currentTimeMillis();		
		System.out.println("start-----------------------------------" + start);
		List<String> list1 = sensitivewordService.checkSensitiveWord(text);
		for (String str : list1) {
			System.out.println(str);
		}		
		long middle = System.currentTimeMillis();		
		System.out.println("-----------------------------------" + middle);
		System.out.println("-----------------------------------" + (middle - start));
		
		List<String> list2 = sensitivewordService.checkSensitiveWord(text);
		for (String str : list2) {
			System.out.println(str);
		}
		long end = System.currentTimeMillis();
		System.out.println("end-----------------------------------" + System.currentTimeMillis());
		System.out.println("-----------------------------------" + (end - middle));
		
		SensitiveWords param = new SensitiveWords();
		param.setSensitiveWord("共匪");
		sensitivewordService.insert(param);		
		long add = System.currentTimeMillis();
		System.out.println("add-----------------------------------" + System.currentTimeMillis());
		System.out.println("-----------------------------------" + (add - end));
		
		List<String> list3 = sensitivewordService.checkSensitiveWord(text);
		for (String str : list3) {
			System.out.println(str);
		}
		long t1 = System.currentTimeMillis();
		System.out.println("t1-----------------------------------" + System.currentTimeMillis());
		System.out.println("-----------------------------------" + (t1-add));
		
		sensitivewordService.delete(1070);
		long del = System.currentTimeMillis();
		System.out.println("del-----------------------------------" + System.currentTimeMillis());
		System.out.println("-----------------------------------" + (del-t1));
		
		List<String> list4= sensitivewordService.checkSensitiveWord(text);
		for (String str : list4) {
			System.out.println(str);
		}
		long t2 = System.currentTimeMillis();
		System.out.println("t2-----------------------------------" + System.currentTimeMillis());
		System.out.println("-----------------------------------" + (t2-del));
		
	}

}