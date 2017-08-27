package com.example.tomokiiwai;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;
import org.springframework.web.servlet.resource.VersionResourceResolver;

import java.util.concurrent.TimeUnit;

/**
 * Web Configuration
 *
 * @author tomoki.iwai
 */
@Configuration
public class D3WebConfig extends WebMvcConfigurerAdapter {
	/**
	 * バージョニングされたリソースパス解決を行うフィルタ
	 *
	 * @return {@link ResourceUrlEncodingFilter}
	 */
	@Bean
	public ResourceUrlEncodingFilter resourceUrlEncodingFilter() {
		return new ResourceUrlEncodingFilter();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// ResourceResolverは、静的リソースにアクセスするための公開パスと
		// サーバー上の物理的な静的リソースを相互に解決するためのインターフェース
		//
		// その具象クラスの一つであるVersionResourceResolverは、2つの方式に基づいたバージョニングをサポートするリゾルバー
		// * 固定バージョニング
		// * MD5バージョニング
		final VersionResourceResolver versionResolver = new VersionResourceResolver()
				// ファイルのMD5ハッシュによるバージョニングをサポートする戦略を採用
				.addContentVersionStrategy("/**/css/**", "/**/js/**", "/**/images/**");

		// リクエストされたURLに対して
		registry.addResourceHandler("/**")
				// 実際の物理ファイルの格納場所をマッピングする
				.addResourceLocations("classpath:static/")
				// HTTPヘッダーにCache-Controlヘッダを出力する
				.setCacheControl(CacheControl.maxAge(30, TimeUnit.DAYS))
				// さらに
				.resourceChain(true)
				// バージョンリゾルバーをセット
				.addResolver(versionResolver);
	}
}
