package com.fise.service.sensitiveword.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.SensitiveWordsMapper;
import com.fise.model.entity.SensitiveWords;
import com.fise.model.entity.SensitiveWordsExample;
import com.fise.service.sensitiveword.ISensitivewordService;
import com.fise.utils.StringUtil;

@Service
public class SensitivewordServiceImpl implements ISensitivewordService {

	// 脏字库
	public static Set<Character> sensitiveCharSet = null;
	// 敏感词库
	public static Set<String> sensitiveWordSet = null;

	@Autowired
	SensitiveWordsMapper sensitiveWordsDao;

	@Override
	public Response insert(SensitiveWords param) {
		Response resp = new Response();

		sensitiveWordsDao.insertSelective(param);
		init();
		return resp.success();
	}

	@Override
	public Response query(Page<SensitiveWords> param) {
		Response resp = new Response();

		SensitiveWordsExample example = new SensitiveWordsExample();
		SensitiveWordsExample.Criteria criteria = example.createCriteria();

		if (!StringUtil.isEmpty(param.getParam().getSensitiveWord())) {
			criteria.andSensitiveWordEqualTo(param.getParam().getSensitiveWord());
		}

		List<SensitiveWords> list = sensitiveWordsDao.selectByPage(example, param);
		param.setParam(null);
		param.setResult(list);

		return resp.success(param);
	}

	@Override
	public Response delete(Integer id) {
		Response resp = new Response();

		sensitiveWordsDao.deleteByPrimaryKey(id);
		init();
		return resp.success();
	}

	/**
	 * 检查敏感词
	 *
	 * @return
	 */
	@Override
	public List<String> checkSensitiveWord(String text) {
		List<String> sensitiveWords = new ArrayList<>();
		if (sensitiveWordSet == null || sensitiveCharSet == null) {
			init();
		}
		for (int i = 0; i < text.length(); i++) {
			Character word = text.charAt(i);
			if (!sensitiveCharSet.contains(word)) {
				continue;
			}
			int j = i;
			while (j < text.length()) {
				if (!sensitiveCharSet.contains(word)) {
					break;
				}
				String key = text.substring(i, j + 1);
				if (sensitiveWordSet.contains(key)) {
					sensitiveWords.add(key);
				}
				j++;
			}
		}
		return sensitiveWords;
	}

	/**
	 * 初始化敏感词库
	 */
	private void init() {
		// 初始化容器
		sensitiveCharSet = new HashSet<>();
		sensitiveWordSet = new HashSet<>();
		// 读取文件 创建敏感词库
		SensitiveWordsExample sensitiveWordsExample = new SensitiveWordsExample();
		List<SensitiveWords> sensitiveWordsList = sensitiveWordsDao.selectByExample(sensitiveWordsExample);

		for (SensitiveWords sensitiveWords : sensitiveWordsList) {
			String word = sensitiveWords.getSensitiveWord();
			sensitiveWordSet.add(word);
			for (Character c : word.toCharArray()) {
				sensitiveCharSet.add(c);
			}
		}

	}

}
