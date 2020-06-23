package com.test.code.controler;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//import com.google.code.kaptcha.Producer;
//import com.google.code.kaptcha.util.Config;

@Controller
public class UtilController extends BasicController {

	Properties props = new Properties();
	//Producer kaptchaProducer = null;
	{
		ImageIO.setUseCache(false); // 关闭缓存
		props.put("kaptcha.border", "no"); // 设置是否有边框
		props.put("kaptcha.textproducer.font.color", "black"); // 字体颜色
		props.put("kaptcha.textproducer.char.spacer", "5");
//		Config config = new Config(props);
//		kaptchaProducer = config.getProducerImpl();
	}
    
	/**
	 * 
	* <p>getVerifyCode</p>
	* <p>Description:获取图文验证码 </p>
	* <p>Copyright: runlin 2018年7月6日</p>
	* <p>Company: runlin </p>
	* @author email:bleach.song@runlin.cn
	* @date 2018年7月6日 下午1:25:40
	* @version 1.0
	* @param request
	* @param response
	 */
	@RequestMapping(value = "/verifyCode.html")
	public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) {

		response.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		// return a jpeg
		response.setContentType("image/jpeg");
		// create the text for the image 验证码文字
//		String capText = kaptchaProducer.createText();
//		request.getSession().setAttribute(Constant.SESSIONVERIFYCODE, capText); // 将验证码放入session中
//		// create the image with the text
//		BufferedImage bi = kaptchaProducer.createImage(capText);
		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
			//ImageIO.write(bi, "jpg", out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
