package service;

import model.Article;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArticleCreator {

    public List<Article> insertArticlesIntoDb(int maxCountOfRawEntry) {

        ArrayList<Article> articles = new ArrayList<>();

        for (int i=0; i <= maxCountOfRawEntry; i++) {
            Article article = new Article();
            Random random = new Random();
            article.setName("Батон нарезной в/с=" + String.valueOf(i));
            article.setCode(random.nextInt());
            article.setUserName(String.format("WHS-%s",i));
            article.setGuid(String.valueOf(random.nextLong()));
            articles.add(article);
        }
        return articles;
    }
}
