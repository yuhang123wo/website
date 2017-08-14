package com.yu.hang.util.lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.xmlbeans.XmlException;

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
		if (file.toString().endsWith(".doc") || file.toString().endsWith(".docx")) {
			String contents = readWord(file.toString());
			Document doc = new Document();
			Field pathField = new StringField("path", file.toString(), Field.Store.YES);
			doc.add(pathField);
			doc.add(new LongPoint("modified", lastModified));
			doc.add(new TextField("contents", contents, Store.NO));
			if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
				writer.addDocument(doc);
			} else {
				writer.updateDocument(new Term("path", file.toString()), doc);
			}
		}
		try (InputStream stream = Files.newInputStream(file)) {
			Document doc = new Document();
			Field pathField = new StringField("path", file.toString(), Field.Store.YES);
			doc.add(pathField);
			doc.add(new LongPoint("modified", lastModified));
			doc.add(new TextField("contents", new BufferedReader(new InputStreamReader(stream))));

			if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
				writer.addDocument(doc);
			} else {
				writer.updateDocument(new Term("path", file.toString()), doc);
			}
		}
	}

	/**
	 * 
	 * @param indexPath
	 * @param folders
	 * @throws Exception
	 *             void
	 */
	public static void createIndexByFolders(String indexPath, String folders) throws Exception {
		File files = new File(folders);
		File[] ff = files.listFiles();
		for (int i = 0; i < ff.length; i++) {
			create(indexPath, ff[i].getAbsolutePath(), true);
		}
	}

	/**
	 * 读取word
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 * @throws FileNotFoundException
	 * @throws IOException
	 *             String
	 * @throws OpenXML4JException
	 * @throws XmlException
	 */
	public static String readWord(String path) {
		File file = new File(path);
		String filePath = file.getAbsolutePath();
		try {
			if (filePath.endsWith(".doc")) {
				return getText03(file);
			} else {
				return getText07(filePath);
			}
		} catch (OfficeXmlFileException e) {
			return getText07(filePath);
		}
	}

	/**
	 * 读取07Word
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 * @throws OpenXML4JException
	 * @throws XmlException
	 * @throws Exception
	 *             String
	 */
	private static String getText07(String filePath) {
		try {
			OPCPackage opcPackage = POIXMLDocument.openPackage(filePath);
			POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
			String text2007 = extractor.getText();
			return text2007;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 读取03 word
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws Exception
	 *             String
	 */
	private static String getText03(File file) {
		try {
			InputStream is = new FileInputStream(file);
			WordExtractor ex = new WordExtractor(is);
			String text2003 = ex.getText();
			is.close();
			return text2003;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		// createIndexByFolders("D:/bb/index", "D:/a");
		System.out.println(readWord("D:/a/a.doc"));
	}
}
