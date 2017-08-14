package com.yu.hang.util.lucene;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class CreateSearch {

	public static List<String> search(String indexPath, String target) throws Exception {
		// 定义索引目录
		Path path = FileSystems.getDefault().getPath(indexPath);
		Directory directory = FSDirectory.open(path);
		// 定义索引查看器
		IndexReader indexReader = DirectoryReader.open(directory);
		// 定义搜索器
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		Analyzer analyzer = new SmartChineseAnalyzer();
		// 定义查询字段
		QueryParser qp = new QueryParser("contents", analyzer);
		Query query = qp.parse(target);
		// 命中前10条文档
		List<String> result = new ArrayList<String>();
		if (indexSearcher != null) {
			TopDocs topDocs = indexSearcher.search(query, 10);
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			System.out.println(topDocs.totalHits);
			for (ScoreDoc scoreDoc : scoreDocs) {
				Document doc = indexSearcher.doc(scoreDoc.doc);
				result.add(doc.get("path"));
			}
		}
		// 关闭索引查看器
		indexReader.close();
		return result;

	}

	public static void main(String[] args) throws Exception {

		search("D:/bb/index", "我爱你");
	}
}
