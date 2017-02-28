package com.wechat.webapi.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wechat.webapi.util.BZPCXCode;

@Controller
public class QController extends BaseController {

	protected static final Logger logger = LoggerFactory.getLogger(QController.class);

	@RequestMapping(value = "/q/{key}")
	public void key(@PathVariable String key, HttpServletRequest request, HttpServletResponse response) {
		logger.info("---------------------->>>    "  +  key);
		try {
			System.out.println(" state = : "+BZPCXCode.check(key));
			response.getWriter().write("key:"+key);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		CodeUtils codeUtils = new CodeUtils();
//		Set<String> keys = new HashSet<String>(0);
//		int i = 0;
//		while(true){
//			key = codeUtils.getCharAndNumr(8);
//			keys.add(key);
//			if(keys.size() >= 2000000){
//				break;
//			}
//			i++;
//		}
//		System.out.println("------------" + i);
//		
//		BufferedWriter bufWriter = null;
//        try {
//            bufWriter = new BufferedWriter(new FileWriter(new File("/Users/chenrixiang/Downloads/text.txt")));
//            
//        	for(String _key : keys){
//        		String value = DESUtil.encrypt(_key, "lksdfjlksjlkfjsdkl");
//        		bufWriter.write("http://wxfw.ahfc.gov.cn/F/q/"+value+"   \n");
//    		}
//        } catch (Exception e) {
//        	e.printStackTrace();
//        } finally {
//            if (bufWriter != null) {
//                try {
//                    bufWriter.close();
//                } catch (IOException e) {
//                	e.printStackTrace();
//                }
//            }
//        }
	}
	
}
