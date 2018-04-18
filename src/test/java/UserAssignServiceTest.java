
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
		long start = System.currentTimeMillis();
		System.out.println("start-----------------------------------" + start);

		List<String> list1 = sensitivewordService.checkSensitiveWord("DFA算法的原理可以参考 这里 ，简单来说就是通过Map构造出一颗敏感词树，树的每一条由根节点到叶子节点的路径构成袁腾飞一个敏感词，例如下图");
		for (String str : list1) {
			System.out.println(str);
		}
		long middle = System.currentTimeMillis();
		System.out.println("-----------------------------------" + middle);
		System.out.println("-----------------------------------" + (middle - start));
		List<String> list2 = sensitivewordService.checkSensitiveWord("DFA算法的原理可以参考 这里 ，简单来说就是通过Map构造出一颗敏感词树，树的每一条由根节点到叶子节点的路径构成一个中共黑敏感词，例如下图");
		for (String str : list2) {
			System.out.println(str);
		}
		long end = System.currentTimeMillis();
		System.out.println("end-----------------------------------" + System.currentTimeMillis());
		System.out.println("-----------------------------------" + (end - middle));
	}

}