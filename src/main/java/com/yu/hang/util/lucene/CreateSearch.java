package com.yu.hang.util.lucene;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class CreateSearch {

	public static void search(String indexPath, String target) throws Exception {
		// 定义索引目录
		Path path = FileSystems.getDefault().getPath(indexPath);
		Directory directory = FSDirectory.open(path);
		// 定义索引查看器
		IndexReader indexReader = DirectoryReader.open(directory);
		// 定义搜索器
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		//定义查询字段
        Term term = new Term("contents",target);
        Query query = new TermQuery(term);
        //命中前10条文档
        TopDocs topDocs = indexSearcher.search(query,10);
        //打印命中数
        System.out.println("命中数："+topDocs.totalHits);
        //取出文档
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        //遍历取出数据
        for (ScoreDoc scoreDoc : scoreDocs){
            //通过indexSearcher的doc方法取出文档
            Document doc = indexSearcher.doc(scoreDoc.doc);
            System.out.println("sellPoint:"+doc);
        }
  
        //关闭索引查看器
        indexReader.close();

	}

	public static void main(String[] args) throws Exception {
		
		search("D:/bb/index", "a");
	}
}
