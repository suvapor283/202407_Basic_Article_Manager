package com.exam.BAM.service;

import java.util.List;

import com.exam.BAM.dao.ArticleDao;
import com.exam.BAM.dto.Article;

public class ArticleService {
	private ArticleDao articleDao;
	
	public ArticleService() {
		this.articleDao = new ArticleDao();
	}

	public void writeArticle(int MemberId, String title, String body, int viewCnt) {
		articleDao.writeArticle(MemberId, title, body, viewCnt);
	}
	
	public void removeArticle(Article article) {
		articleDao.removeArticle(article);
	}

	public int getIdByCmd(String cmd) {
		String[] cmdBits = cmd.split(" ");

		try {
			int id = Integer.parseInt(cmdBits[2]);
			return id;
		} catch (NumberFormatException e) {
			return 0;
		} catch (Exception e) {
			return 0;
		}
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public List<Article> getArticles(String searchKeyword) {
		return articleDao.getArticles(searchKeyword);
	}

	public int getLastId() {
		return articleDao.getLastId();
	}

	public void increaseViewCnt(Article foundArticle) {
		articleDao.increaseViewCnt(foundArticle);
	}

	public void modifyArticle(Article foundArticle, String title, String body) {
		articleDao.modifyArticle(foundArticle, title, body);
		
	}
}