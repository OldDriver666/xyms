import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fise.utils.excel.ExcelImporterUtils;
import com.fise.utils.excel.ExportUtils;
import com.google.common.collect.Lists;



public class MyTest {
	public static void main(String[] args) throws Exception {
		
	}
	
	@RequestMapping(value = "/do_import")
	@ResponseBody
	public Map<String, Object> doImport(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException, Exception{

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		//获取父类泛型类
		Class<BillSaleBalance> entityClass = (Class<BillSaleBalance>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		// 忽略在excel有字段填写的值
		List<String> ignoreProperties = Lists.newArrayList();
		for (Entry<String, String> entry : ExcelImporterUtils.getExcelCells(entityClass,entityClass.getSimpleName()).entrySet()) {
			ignoreProperties.add(entry.getKey());
		}
		//导入明细
		List<BillSaleBalance> unfrozenList = ExcelImporterUtils.importSheet(file.getInputStream(), BillSaleBalance.class,entityClass.getSimpleName());
		if (null == unfrozenList || unfrozenList.size() == 0) {
			map.put("success", false);
			map.put("data", unfrozenList);
			return map;
		}
		return map;
	}
	
	/**
	 * 
	 * @param req 
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/export_sale_dtl")
	public void exportSaleDtl(HttpServletRequest req, Model model, HttpServletResponse response) throws Exception {
		//导出列
		List<Map> ColumnsList = ExportUtils.getColumnList(req.getParameter("exportColumns"));
		//查询导出List
//		List<Map> dataMapList = ExportUtils.getDataList(this.getDataList(req, model));
//		ExportUtils.ExportData(req.getParameter("fileName"), ColumnsList, dataMapList, response);
	}
}
