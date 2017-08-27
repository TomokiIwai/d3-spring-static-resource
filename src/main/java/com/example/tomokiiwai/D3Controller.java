package com.example.tomokiiwai;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * コントローラー
 *
 * @author tomoki.iwai
 */
@Controller
public class D3Controller {
	/**
	 * トップページ
	 */
	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
