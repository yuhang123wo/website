package com.yu.hang.util.lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.yu.hang.exception.LucenceException;

public class CreateIndex {

	/**
	 * 创建索引
	 * 
	 * @param indexPath
	 * @param docsPath
	 * @param create
	 * @throws Exception
	 *             void
	 */
	public static void create(String indexPath, String docsPath, boolean create) throws Exception {
		// 定义IndexWriter
		Path path = FileSystems.getDefault().getPath(indexPath);
		Directory directory = FSDirectory.open(path);
		final Path docDir = Paths.get(docsPath);
		if (!Files.isReadable(docDir)) {
			throw new LucenceException("Document directory '" + docDir.toAbsolutePath()
					+ "' does not exist or is not readable, please check the path");
		}
		// 定义分词器
		Analyzer analyzer = new SmartChineseAnalyzer();
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
		IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
		if (create) {
			indexWriterConfig.setOpenMode(OpenMode.CREATE);
		} else {
			// Add new documents to an existing index:
			indexWriterConfig.setOpenMode(OpenMode.CREATE_OR_APPEND);
		}
		indexDocs(indexWriter, docDir);
		indexWriter.close();
		// 关闭
		indexWriter.close();
	}

	/**
	 * 
	 * @param writer
	 * @param path
	 * @throws IOException
	 *             void
	 */
	private static void indexDocs(final IndexWriter writer, Path path) throws IOException {
		if (Files.isDirectory(path)) {
			Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
						throws IOException {
					try {
						indexDoc(writer, file, attrs.lastModifiedTime().toMillis());
					} catch (IOException ignore) {
						ignore.printStackTrace();
					}
					return FileVisitResult.CONTINUE;
				}
			});
		} else {
			indexDoc(writer, path, Files.getLastModifiedTime(path).toMillis());
		}
	}

	/**
	 * 
	 * @param writer
	 * @param file
	 * @param lastModified
	 * @throws IOException
	 *             void
	 */
	private static void indexDoc(IndexWriter writer, Path file, long lastModified)
			throws IOException {
		try (InputStream stream = Files.newInputStream(file)) {
			Document doc = new Document();
			Field pathField = new StringField("path", file.toString(), Field.Store.YES);
			doc.add(pathField);
			doc.add(new LongPoint("modified", lastModified));
			doc.add(new TextField("contents", new BufferedReader(new InputStreamReader(stream,
					StandardCharsets.UTF_8))));

			if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
				writer.addDocument(doc);
			} else {
				writer.updateDocument(new Term("path", file.toString()), doc);
			}
		}
	}

	public static void createIndexByFolders(String indexPath, String folders) throws Exception {
		File files = new File(folders);
		File[] ff = files.listFiles();
		for (int i = 0; i < ff.length; i++) {
			create(indexPath, ff[i].getAbsolutePath(), true);
		}
	}

	public static void main(String[] args) throws Exception {
		createIndexByFolders("D:/bb/index", "D:/a");
	}
}
